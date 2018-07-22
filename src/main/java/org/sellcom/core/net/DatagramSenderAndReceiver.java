/*
 * Copyright (c) 2015-2018 Petr Zelenka <petr.zelenka@sellcom.org>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sellcom.core.net;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.sellcom.core.net.NetworkEndPoint.State.STARTED;
import static org.sellcom.core.net.NetworkEndPoint.State.STARTING;
import static org.sellcom.core.net.NetworkEndPoint.State.STOPPED;
import static org.sellcom.core.net.NetworkEndPoint.State.STOPPING;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ProtocolFamily;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.sellcom.core.Contract;
import org.sellcom.core.Threads;
import org.sellcom.core.internal.collection.concurrent.SimpleDelayed;
import org.sellcom.core.io.Io;

/**
 * Combined receiver and sender of UDP datagrams.
 *
 * @since 1.0
 */
public class DatagramSenderAndReceiver implements NetworkReceiver, NetworkSender {

	private static final int DEFAULT_HANDLER_THREAD_PRIORITY = 5;

	private static final int DEFAULT_RECEIVE_BUFFER_SIZE = 0x2000; // 8 KB

	private static final int DEFAULT_RECEIVER_THREAD_PRIORITY = 5;

	private static final int DEFAULT_SEND_BUFFER_SIZE = 0x2000; // 8 KB

	private static final int DEFAULT_SENDER_THREAD_PRIORITY = 5;

	private DatagramChannel channel;

	private final Map<InetAddress, MembershipKey> groups = new HashMap<>();

	private ExecutorService handlerExecutor;

	private int handlerThreadPriority = DEFAULT_HANDLER_THREAD_PRIORITY;

	private int handlerThreads = 1;

	private InetSocketAddress localEndPoint = new InetSocketAddress(0);

	private Consumer<NetworkMessage> messageConsumer;

	private NetworkInterface networkInterface;

	private final BlockingQueue<NetworkMessage> pendingIncomingMessages = new LinkedBlockingQueue<>();

	private final DelayQueue<DelayedNetworkMessage> pendingOutgoingMessages = new DelayQueue<>();

	private final ProtocolFamily protocolFamily;

	private int receiveBufferSize = DEFAULT_RECEIVE_BUFFER_SIZE;

	private ExecutorService receiverExecutor;

	private int receiverThreadPriority = DEFAULT_RECEIVER_THREAD_PRIORITY;

	private int receiverThreads = 1;

	private int sendBufferSize = DEFAULT_SEND_BUFFER_SIZE;

	private int sendRepeatCount = 0;

	private int sendRepeatInterval = 100; // milliseconds

	private ExecutorService senderExecutor;

	private int senderThreadPriority = DEFAULT_SENDER_THREAD_PRIORITY;

	private int senderThreads = 1;

	private volatile State state = STOPPED;

	private int timeToLive = 16;

	private TrafficClass trafficClass = TrafficClass.NORMAL_SERVICE;


	private DatagramSenderAndReceiver(ProtocolFamily protocolFamily) {
		this.protocolFamily = protocolFamily;
	}


	/**
	 * Creates a datagram sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code protocolFamily} is {@code null}
	 *
	 * @since 1.0
	 */
	public static DatagramSenderAndReceiver create(ProtocolFamily protocolFamily) {
		Contract.checkArgument(protocolFamily != null, "Protocol family must not be null");

		return new DatagramSenderAndReceiver(protocolFamily);
	}

	/**
	 * Returns the multicast groups associated with this sender/receiver.
	 *
	 * @since 1.0
	 */
	public Set<InetAddress> getGroups() {
		return groups.keySet();
	}

	/**
	 * Returns the priority of the handler threads in this sender/receiver.
	 *
	 * @since 1.0
	 */
	public int getHandlerThreadPriority() {
		return handlerThreadPriority;
	}

	/**
	 * Returns the number of the handler threads in this sender/receiver.
	 *
	 * @since 1.0
	 */
	public int getHandlerThreads() {
		return handlerThreads;
	}

	@Override
	public InetAddress getLocalAddress() {
		return localEndPoint.getAddress();
	}

	@Override
	public InetSocketAddress getLocalEndPoint() {
		return localEndPoint;
	}

	@Override
	public int getLocalPort() {
		return localEndPoint.getPort();
	}

	@Override
	public NetworkInterface getNetworkInterface() {
		return networkInterface;
	}

	@Override
	public int getReceiveBufferSize() {
		return receiveBufferSize;
	}

	/**
	 * Returns the priority of the receiver threads in this sender/receiver.
	 *
	 * @since 1.0
	 */
	public int getReceiverThreadPriority() {
		return receiverThreadPriority;
	}

	/**
	 * Returns the number of the receiver threads in this sender/receiver.
	 *
	 * @since 1.0
	 */
	public int getReceiverThreads() {
		return receiverThreads;
	}

	@Override
	public int getSendBufferSize() {
		return sendBufferSize;
	}

	/**
	 * Returns the send repeat count of this sender.
	 *
	 * @since 1.0
	 */
	public int getSendRepeatCount() {
		return sendRepeatCount;
	}

	/**
	 * Returns the send repeat interval (in milliseconds) of this sender.
	 *
	 * @since 1.0
	 */
	public long getSendRepeatInterval() {
		return sendRepeatInterval;
	}

	/**
	 * Returns the priority of the sender threads in this sender.
	 *
	 * @since 1.0
	 */
	public int getSenderThreadPriority() {
		return senderThreadPriority;
	}

	/**
	 * Returns the number of the sender threads in this sender.
	 *
	 * @since 1.0
	 */
	public int getSenderThreads() {
		return senderThreads;
	}

	@Override
	public State getState() {
		return state;
	}

	/**
	 * Returns the TTL of multicast packets sent by this sender.
	 *
	 * @since 1.0
	 */
	public int getTimeToLive() {
		return timeToLive;
	}

	/**
	 * Returns the traffic class of datagrams sent by this sender.
	 *
	 * @since 1.0
	 */
	public TrafficClass getTrafficClass() {
		return trafficClass;
	}

	@Override
	public void sendDelayed(NetworkMessage message, long initialDelay, TimeUnit unit) {
		Contract.checkState((state == STARTED) || (state == STOPPING), "Sender has not yet been started");
		Contract.checkArgument(message != null, "Message must not be null");
		Contract.checkArgument(message.getRemoteEndPoint() != null, "Message's remote end point must not be null");
		Contract.checkArgument(message.getUuid() != null, "Message's UUID must not be null");
		Contract.checkArgument(initialDelay >= 0L, "Initial delay must not be negative");
		Contract.checkArgument(unit != null, "Unit must not be null");

		for (int i = 0, j = sendRepeatCount; i <= j; i++) {
			pendingOutgoingMessages.offer(new DelayedNetworkMessage(message,
					Math.addExact(unit.toMillis(initialDelay), Math.multiplyExact(i, sendRepeatInterval))));
		}
	}

	@Override
	public void sendImmediately(NetworkMessage message) {
		Contract.checkState((state == STARTED) || (state == STOPPING), "Sender has not yet been started");
		Contract.checkArgument(message != null, "Message must not be null");
		Contract.checkArgument(message.getRemoteEndPoint() != null, "Message's remote end point must not be null");
		Contract.checkArgument(message.getUuid() != null, "Message's UUID must not be null");

		for (int i = 0, j = sendRepeatCount; i <= j; i++) {
			pendingOutgoingMessages.offer(new DelayedNetworkMessage(message,
					Math.multiplyExact(i, sendRepeatInterval)));
		}
	}

	@Override
	public void start() throws IOException {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");

		state = STARTING;

		try {
			createChannel();
			startBackgroundThreads();
		} catch (IOException e) {
			state = STARTED;

			stop();
		}

		state = STARTED;
	}

	@Override
	public void stop() {
		if (state == STARTED) {
			state = STOPPING;

			stopBackgroundThreads();
			clearQueues();
			destroyChannel();

			state = STOPPED;
		}
	}

	/**
	 * Sets the priority of the handler threads in this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code threadPriority} is not valid
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 *
	 * @see Thread#MAX_PRIORITY
	 * @see Thread#MIN_PRIORITY
	 */
	public DatagramSenderAndReceiver withHandlerThreadPriority(int threadPriority) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument((threadPriority >= MIN_PRIORITY) && (threadPriority <= MAX_PRIORITY), "Thread priority must be valid: {0}", threadPriority);

		handlerThreadPriority = threadPriority;

		return this;
	}

	/**
	 * Sets the multicast groups associated with this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code groups} are {@code null}
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withGroups(Set<InetAddress> groups) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(groups != null, "Groups must not be null");

		groups.forEach(group -> this.groups.put(group, null));

		return this;
	}

	/**
	 * Sets the number of the handler threads in this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code threads} is not positive
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withHandlerThreads(int threads) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(threads > 0, "Number of threads must be positive: {0}", threads);

		handlerThreads = threads;

		return this;
	}

	/**
	 * Sets the local address and port of this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code localAddress} is {@code null}
	 * @throws IllegalArgumentException if {@code localPort} is invalid
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withLocalAddressAndPort(InetAddress localAddress, int localPort) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(localAddress != null, "Local address must not be null");
		Contract.checkArgument((localPort >= 0) && (localPort <= 65535), "Local port must be valid: {0}", localPort);

		localEndPoint = new InetSocketAddress(localAddress, localPort);

		return this;
	}

	/**
	 * Sets the local port of this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code localPort} is invalid
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withLocalPort(int localPort) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument((localPort >= 0) && (localPort <= 65535), "Local port must be valid: {0}", localPort);

		localEndPoint = new InetSocketAddress(localPort);

		return this;
	}

	/**
	 * Sets the message consumer of this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code messageConsumer} is {@code null}
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withMessageConsumer(Consumer<NetworkMessage> messageConsumer) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(messageConsumer != null, "Message consumer must not be null");

		this.messageConsumer = messageConsumer;

		return this;
	}

	/**
	 * Enables multicast on this sender/receiver over the given network interface.
	 *
	 * @throws IllegalArgumentException if {@code networkInterface} is {@code null}
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withMulticast(NetworkInterface networkInterface) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(networkInterface != null, "Network interface must not be null");

		this.networkInterface = networkInterface;

		return this;
	}

	/**
	 * Sets the network interface associated with this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code networkInterface} is {@code null}
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withNetworkInterface(NetworkInterface networkInterface) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(networkInterface != null, "Network interface must not be null");

		this.networkInterface = networkInterface;

		return this;
	}

	/**
	 * Sets the receive buffer size of this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code bufferSize} is negative
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withReceiveBufferSize(int bufferSize) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(bufferSize >= 0, "Buffer size must not be negative: {0}", bufferSize);

		receiveBufferSize = bufferSize;

		return this;
	}

	/**
	 * Sets the priority of the receiver threads in this receiver.
	 *
	 * @throws IllegalArgumentException if {@code threadPriority} is not valid
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 *
	 * @see Thread#MAX_PRIORITY
	 * @see Thread#MIN_PRIORITY
	 */
	public DatagramSenderAndReceiver withReceiverThreadPriority(int threadPriority) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument((threadPriority >= MIN_PRIORITY) && (threadPriority <= MAX_PRIORITY), "Thread priority must be valid: {0}", threadPriority);

		receiverThreadPriority = threadPriority;

		return this;
	}

	/**
	 * Sets the number of the receiver threads in this receiver.
	 *
	 * @throws IllegalArgumentException if {@code threads} is not positive
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withReceiverThreads(int threads) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument(threads > 0, "Number of threads must be positive: {0}", threads);

		receiverThreads = threads;

		return this;
	}

	/**
	 * Sets the send buffer size of this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code bufferSize} is negative
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withSendBufferSize(int bufferSize) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(bufferSize >= 0, "Buffer size must not be negative: {0}", bufferSize);

		sendBufferSize = bufferSize;

		return this;
	}

	/**
	 * Sets the send repeat count of this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code repeatCount} is negative
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withSendRepeatCount(int repeatCount) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(repeatCount >= 0, "Repeat count must not be negative: {0}", repeatCount);

		sendRepeatCount = repeatCount;

		return this;
	}

	/**
	 * Sets the send repeat interval (in milliseconds) of this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code repeatInterval} is negative
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withSendRepeatInterval(int repeatInterval) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(repeatInterval >= 0, "Repeat interval must not be negative: {0}", repeatInterval);

		sendRepeatInterval = repeatInterval;

		return this;
	}

	/**
	 * Sets the priority of the sender threads in this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code threadPriority} is not valid
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 *
	 * @see Thread#MAX_PRIORITY
	 * @see Thread#MIN_PRIORITY
	 */
	public DatagramSenderAndReceiver withSenderThreadPriority(int threadPriority) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument((threadPriority >= MIN_PRIORITY) && (threadPriority <= MAX_PRIORITY), "Thread priority must be valid: {0}", threadPriority);

		senderThreadPriority = threadPriority;

		return this;
	}

	/**
	 * Sets the number of the sender threads in this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code threads} is not positive
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withSenderThreads(int threads) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(threads > 0, "Number of threads must be positive: {0}", threads);

		senderThreads = threads;

		return this;
	}

	/**
	 * Sets the TTL of multicast packets sent by this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code timeToLive} is not positive
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withTimeToLive(int timeToLive) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(timeToLive > 0, "Time to live must be positive: {0}", timeToLive);

		this.timeToLive = timeToLive;

		return this;
	}

	/**
	 * Sets the traffic class of datagrams sent by this sender/receiver.
	 *
	 * @throws IllegalArgumentException if {@code trafficClass} is {@code null}
	 * @throws IllegalStateException if this sender/receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSenderAndReceiver withTrafficClass(TrafficClass trafficClass) {
		Contract.checkState(state == STOPPED, "Sender/receiver has already been started");
		Contract.checkArgument(trafficClass != null, "Traffic class address must not be null");

		this.trafficClass = trafficClass;

		return this;
	}


	private void clearQueues() {
		pendingIncomingMessages.clear();
		pendingOutgoingMessages.clear();
	}

	private void createChannel() throws IOException {
		channel = DatagramChannel.open(protocolFamily);
		channel.configureBlocking(true);
		channel.setOption(StandardSocketOptions.IP_MULTICAST_LOOP, true);
		channel.setOption(StandardSocketOptions.IP_TOS, trafficClass.getValue());
		channel.setOption(StandardSocketOptions.SO_BROADCAST, true);
		channel.setOption(StandardSocketOptions.SO_RCVBUF, receiveBufferSize);
		channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
		channel.setOption(StandardSocketOptions.SO_SNDBUF, sendBufferSize);

		if (networkInterface != null) {
			channel.setOption(StandardSocketOptions.IP_MULTICAST_IF, networkInterface);
			channel.setOption(StandardSocketOptions.IP_MULTICAST_LOOP, true);
			channel.setOption(StandardSocketOptions.IP_MULTICAST_TTL, timeToLive);
		}

		channel.bind(localEndPoint);

		for (InetAddress group : groups.keySet()) {
			groups.put(group, channel.join(group, networkInterface));
		}
	}

	private Thread createHandlerThread(Runnable handler) {
		Thread thread = new Thread(handler);
		thread.setDaemon(true);
		thread.setName("DatagramSenderAndReceiver.HandlerThread@" + System.identityHashCode(thread));
		thread.setPriority(handlerThreadPriority);

		return thread;
	}

	private Thread createReceiverThread(Runnable receiver) {
		Thread thread = new Thread(receiver);
		thread.setDaemon(true);
		thread.setName("DatagramSenderAndReceiver.ReceiverThread@" + System.identityHashCode(thread));
		thread.setPriority(receiverThreadPriority);

		return thread;
	}

	private Thread createSenderThread(Runnable sender) {
		Thread thread = new Thread(sender);
		thread.setDaemon(true);
		thread.setName("DatagramSenderAndReceiver.SenderThread@" + System.identityHashCode(thread));
		thread.setPriority(senderThreadPriority);

		return thread;
	}

	private void destroyChannel() {
		groups.keySet().stream()
				.map(group -> groups.put(group, null))
				.filter(((Predicate<MembershipKey>) Objects::isNull).negate())
				.forEach(MembershipKey::drop);

		Io.close(channel);
	}

	private void startBackgroundThreads() {
		senderExecutor = Executors.newFixedThreadPool(senderThreads, this::createSenderThread);
		for (int i = 0; i < senderThreads; i++) {
			senderExecutor.submit(new Sender());
		}

		handlerExecutor = Executors.newFixedThreadPool(handlerThreads, this::createHandlerThread);
		for (int i = 0; i < handlerThreads; i++) {
			handlerExecutor.submit(new Handler());
		}

		receiverExecutor = Executors.newFixedThreadPool(receiverThreads, this::createReceiverThread);
		for (int i = 0; i < receiverThreads; i++) {
			receiverExecutor.submit(new Receiver());
		}
	}

	private void stopBackgroundThreads() {
		receiverExecutor.shutdownNow();

		handlerExecutor.shutdownNow();

		try {
			senderExecutor.shutdown();
			senderExecutor.awaitTermination(
					Math.addExact(1000, Math.multiplyExact(sendRepeatCount, sendRepeatInterval)),
					MILLISECONDS);
		} catch (InterruptedException e) {
			senderExecutor.shutdownNow();
			Threads.preserveInterruptedStatus(e);
		}
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class DelayedNetworkMessage extends SimpleDelayed<NetworkMessage> {

		DelayedNetworkMessage(NetworkMessage message, long delay) {
			super(message, delay, MILLISECONDS);
		}


		ByteBuffer getByteBuffer() {
			return getValue().toByteBuffer();
		}

		InetSocketAddress getRemoteEndPoint() {
			return getValue().getRemoteEndPoint();
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private class Handler implements Runnable {

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				try {
					messageConsumer.accept(pendingIncomingMessages.take());
				} catch (InterruptedException e) {
					Threads.preserveInterruptedStatus(e);
				}
			}
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private class Receiver implements Runnable {

		@Override
		public void run() {
			ByteBuffer buffer = ByteBuffer.allocate(receiveBufferSize);
			while (!Thread.interrupted()) {
				try {
					SocketAddress remoteAddress = channel.receive(buffer);
					buffer.flip();
					if (remoteAddress instanceof InetSocketAddress) {
						if (!pendingIncomingMessages.offer(NetworkMessage.fromByteBuffer(buffer, (InetSocketAddress) remoteAddress))) {
							; // Ignore
						}
					}
				} catch (IOException e) {
					; // Ignore
				}

				buffer.clear();
			}
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private class Sender implements Runnable {

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				try {
					DelayedNetworkMessage message = pendingOutgoingMessages.take();
					channel.send(message.getByteBuffer(), message.getRemoteEndPoint());
				} catch (InterruptedException e) {
					Threads.preserveInterruptedStatus(e);
				} catch (IOException e) {
					; // Ignore
				}
			}
		}

	}

}
