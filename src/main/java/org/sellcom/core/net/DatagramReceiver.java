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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.sellcom.core.Contract;
import org.sellcom.core.Threads;
import org.sellcom.core.io.Io;

/**
 * Receiver of UDP datagrams.
 *
 * @since 1.0
 */
public class DatagramReceiver implements NetworkReceiver {

	private static final int DEFAULT_HANDLER_THREAD_PRIORITY = 5;

	private static final int DEFAULT_RECEIVE_BUFFER_SIZE = 0x2000; // 8 KB

	private static final int DEFAULT_RECEIVER_THREAD_PRIORITY = 5;

	private DatagramChannel channel;

	private final Map<InetAddress, MembershipKey> groups = new HashMap<>();

	private ExecutorService handlerExecutor;

	private int handlerThreadPriority = DEFAULT_HANDLER_THREAD_PRIORITY;

	private int handlerThreads = 1;

	private InetSocketAddress localEndPoint = new InetSocketAddress(0);

	private Consumer<NetworkMessage> messageConsumer;

	private NetworkInterface networkInterface;

	private final BlockingQueue<NetworkMessage> pendingMessages = new LinkedBlockingQueue<>();

	private final ProtocolFamily protocolFamily;

	private int receiveBufferSize = DEFAULT_RECEIVE_BUFFER_SIZE;

	private ExecutorService receiverExecutor;

	private int receiverThreadPriority = DEFAULT_RECEIVER_THREAD_PRIORITY;

	private int receiverThreads = 1;

	private volatile State state = STOPPED;


	private DatagramReceiver(ProtocolFamily protocolFamily) {
		this.protocolFamily = protocolFamily;
	}


	/**
	 * Creates a datagram receiver.
	 *
	 * @throws IllegalArgumentException if {@code protocolFamily} is {@code null}
	 *
	 * @since 1.0
	 */
	public static DatagramReceiver create(ProtocolFamily protocolFamily) {
		Contract.checkArgument(protocolFamily != null, "Protocol family must not be null");

		return new DatagramReceiver(protocolFamily);
	}

	/**
	 * Returns the multicast groups associated with this receiver.
	 *
	 * @since 1.0
	 */
	public Set<InetAddress> getGroups() {
		return groups.keySet();
	}

	/**
	 * Returns the priority of the handler threads in this receiver.
	 *
	 * @since 1.0
	 */
	public int getHandlerThreadPriority() {
		return handlerThreadPriority;
	}

	/**
	 * Returns the number of the handler threads in this receiver.
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
	 * Returns the priority of the receiver threads in this receiver.
	 *
	 * @since 1.0
	 */
	public int getReceiverThreadPriority() {
		return receiverThreadPriority;
	}

	/**
	 * Returns the number of the receiver threads in this receiver.
	 *
	 * @since 1.0
	 */
	public int getReceiverThreads() {
		return receiverThreads;
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public void start() throws IOException {
		Contract.checkState(state == STOPPED, "Receiver has already been started");

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
			clearQueue();
			destroyChannel();

			state = STOPPED;
		}
	}

	/**
	 * Sets the priority of the handler threads in this receiver.
	 *
	 * @throws IllegalArgumentException if {@code threadPriority} is not valid
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 *
	 * @see Thread#MAX_PRIORITY
	 * @see Thread#MIN_PRIORITY
	 */
	public DatagramReceiver withHandlerThreadPriority(int threadPriority) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument((threadPriority >= MIN_PRIORITY) && (threadPriority <= MAX_PRIORITY), "Thread priority must be valid: {0}", threadPriority);

		handlerThreadPriority = threadPriority;

		return this;
	}

	/**
	 * Sets the multicast groups associated with this receiver.
	 *
	 * @throws IllegalArgumentException if {@code groups} are {@code null}
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramReceiver withGroups(Set<InetAddress> groups) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument(groups != null, "Groups must not be null");

		groups.forEach(group -> this.groups.put(group, null));

		return this;
	}

	/**
	 * Sets the number of the handler threads in this receiver.
	 *
	 * @throws IllegalArgumentException if {@code threads} is not positive
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramReceiver withHandlerThreads(int threads) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument(threads > 0, "Number of threads must be positive: {0}", threads);

		handlerThreads = threads;

		return this;
	}

	/**
	 * Sets the local address and port of this receiver.
	 *
	 * @throws IllegalArgumentException if {@code localAddress} is {@code null}
	 * @throws IllegalArgumentException if {@code localPort} is invalid
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramReceiver withLocalAddressAndPort(InetAddress localAddress, int localPort) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument(localAddress != null, "Local address must not be null");
		Contract.checkArgument((localPort >= 0) && (localPort <= 65535), "Local port must be valid: {0}", localPort);

		localEndPoint = new InetSocketAddress(localAddress, localPort);

		return this;
	}

	/**
	 * Sets the local port of this receiver.
	 *
	 * @throws IllegalArgumentException if {@code localPort} is invalid
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramReceiver withLocalPort(int localPort) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument((localPort >= 0) && (localPort <= 65535), "Local port must be valid: {0}", localPort);

		localEndPoint = new InetSocketAddress(localPort);

		return this;
	}

	/**
	 * Sets the message consumer of this receiver.
	 *
	 * @throws IllegalArgumentException if {@code messageConsumer} is {@code null}
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramReceiver withMessageConsumer(Consumer<NetworkMessage> messageConsumer) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument(messageConsumer != null, "Message consumer must not be null");

		this.messageConsumer = messageConsumer;

		return this;
	}

	/**
	 * Sets the network interface associated with this receiver.
	 *
	 * @throws IllegalArgumentException if {@code networkInterface} is {@code null}
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramReceiver withNetworkInterface(NetworkInterface networkInterface) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument(networkInterface != null, "Network interface must not be null");

		this.networkInterface = networkInterface;

		return this;
	}

	/**
	 * Sets the receive buffer size of this receiver.
	 *
	 * @throws IllegalArgumentException if {@code bufferSize} is negative
	 * @throws IllegalStateException if this receiver has already been started
	 *
	 * @since 1.0
	 */
	public DatagramReceiver withReceiveBufferSize(int bufferSize) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
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
	public DatagramReceiver withReceiverThreadPriority(int threadPriority) {
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
	public DatagramReceiver withReceiverThreads(int threads) {
		Contract.checkState(state == STOPPED, "Receiver has already been started");
		Contract.checkArgument(threads > 0, "Number of threads must be positive: {0}", threads);

		receiverThreads = threads;

		return this;
	}


	private void clearQueue() {
		pendingMessages.clear();
	}

	private void createChannel() throws IOException {
		channel = DatagramChannel.open(protocolFamily);
		channel.configureBlocking(true);
		channel.setOption(StandardSocketOptions.IP_MULTICAST_LOOP, true);
		channel.setOption(StandardSocketOptions.SO_RCVBUF, receiveBufferSize);
		channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
		channel.bind(localEndPoint);

		for (InetAddress group : groups.keySet()) {
			groups.put(group, channel.join(group, networkInterface));
		}
	}

	private Thread createHandlerThread(Runnable handler) {
		Thread thread = new Thread(handler);
		thread.setDaemon(true);
		thread.setName("DatagramSender.HandlerThread@" + System.identityHashCode(thread));
		thread.setPriority(handlerThreadPriority);

		return thread;
	}

	private Thread createReceiverThread(Runnable receiver) {
		Thread thread = new Thread(receiver);
		thread.setDaemon(true);
		thread.setName("DatagramSender.ReceiverThread@" + System.identityHashCode(thread));
		thread.setPriority(receiverThreadPriority);

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
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private class Handler implements Runnable {

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				try {
					messageConsumer.accept(pendingMessages.take());
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
						if (!pendingMessages.offer(NetworkMessage.fromByteBuffer(buffer, (InetSocketAddress) remoteAddress))) {
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

}
