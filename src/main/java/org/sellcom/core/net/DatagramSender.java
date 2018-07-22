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
import static org.sellcom.core.net.TrafficClass.NORMAL_SERVICE;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.sellcom.core.Contract;
import org.sellcom.core.Threads;
import org.sellcom.core.internal.collection.concurrent.SimpleDelayed;
import org.sellcom.core.io.Io;

/**
 * Sender of UDP datagrams.
 *
 * @since 1.0
 */
public class DatagramSender implements NetworkSender {

	private static final int DEFAULT_SEND_BUFFER_SIZE = 0x2000; // 8 KB

	private static final int DEFAULT_SENDER_THREAD_PRIORITY = 5;

	private DatagramChannel channel;

	private InetSocketAddress localEndPoint = new InetSocketAddress(0);

	private NetworkInterface networkInterface;

	private final DelayQueue<DelayedNetworkMessage> pendingMessages = new DelayQueue<>();

	private final ProtocolFamily protocolFamily;

	private int sendBufferSize = DEFAULT_SEND_BUFFER_SIZE;

	private int sendRepeatCount = 0;

	private int sendRepeatInterval = 100; // milliseconds

	private ExecutorService senderExecutor;

	private int senderThreadPriority = DEFAULT_SENDER_THREAD_PRIORITY;

	private int senderThreads = 1;

	private volatile State state = STOPPED;

	private int timeToLive = 16;

	private TrafficClass trafficClass = NORMAL_SERVICE;


	private DatagramSender(ProtocolFamily protocolFamily) {
		this.protocolFamily = protocolFamily;
	}


	/**
	 * Creates a datagram sender.
	 *
	 * @throws IllegalArgumentException if {@code protocolFamily} is {@code null}
	 *
	 * @since 1.0
	 */
	public static DatagramSender create(ProtocolFamily protocolFamily) {
		Contract.checkArgument(protocolFamily != null, "Protocol family must not be null");

		return new DatagramSender(protocolFamily);
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
			pendingMessages.offer(new DelayedNetworkMessage(message,
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
			pendingMessages.offer(new DelayedNetworkMessage(message,
					Math.multiplyExact(i, sendRepeatInterval)));
		}
	}

	@Override
	public void start() throws IOException {
		Contract.checkState(state == STOPPED, "Sender has already been started");

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
	 * Sets the local address and port of this sender.
	 *
	 * @throws IllegalArgumentException if {@code localAddress} is {@code null}
	 * @throws IllegalArgumentException if {@code localPort} is invalid
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSender withLocalAddressAndPort(InetAddress localAddress, int localPort) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument(localAddress != null, "Local address must not be null");
		Contract.checkArgument((localPort >= 0) && (localPort <= 65535), "Local port must be valid: {0}", localPort);

		localEndPoint = new InetSocketAddress(localAddress, localPort);

		return this;
	}

	/**
	 * Sets the local port of this sender.
	 *
	 * @throws IllegalArgumentException if {@code localPort} is invalid
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSender withLocalPort(int localPort) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument((localPort >= 0) && (localPort <= 65535), "Local port must be valid: {0}", localPort);

		localEndPoint = new InetSocketAddress(localPort);

		return this;
	}

	/**
	 * Enables multicast on this sender over the given network interface.
	 *
	 * @throws IllegalArgumentException if {@code networkInterface} is {@code null}
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSender withMulticast(NetworkInterface networkInterface) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument(networkInterface != null, "Network interface must not be null");

		this.networkInterface = networkInterface;

		return this;
	}

	/**
	 * Sets the send buffer size of this sender.
	 *
	 * @throws IllegalArgumentException if {@code bufferSize} is negative
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSender withSendBufferSize(int bufferSize) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument(bufferSize >= 0, "Buffer size must not be negative: {0}", bufferSize);

		sendBufferSize = bufferSize;

		return this;
	}

	/**
	 * Sets the send repeat count of this sender.
	 *
	 * @throws IllegalArgumentException if {@code repeatCount} is negative
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSender withSendRepeatCount(int repeatCount) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument(repeatCount >= 0, "Repeat count must not be negative: {0}", repeatCount);

		sendRepeatCount = repeatCount;

		return this;
	}

	/**
	 * Sets the send repeat interval (in milliseconds) of this sender.
	 *
	 * @throws IllegalArgumentException if {@code repeatInterval} is negative
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSender withSendRepeatInterval(int repeatInterval) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument(repeatInterval >= 0, "Repeat interval must not be negative: {0}", repeatInterval);

		sendRepeatInterval = repeatInterval;

		return this;
	}

	/**
	 * Sets the priority of the sender threads in this sender.
	 *
	 * @throws IllegalArgumentException if {@code threadPriority} is not valid
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 *
	 * @see Thread#MAX_PRIORITY
	 * @see Thread#MIN_PRIORITY
	 */
	public DatagramSender withSenderThreadPriority(int threadPriority) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument((threadPriority >= MIN_PRIORITY) && (threadPriority <= MAX_PRIORITY), "Thread priority must be valid: {0}", threadPriority);

		senderThreadPriority = threadPriority;

		return this;
	}

	/**
	 * Sets the number of the sender threads in this sender.
	 *
	 * @throws IllegalArgumentException if {@code threads} is not positive
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSender withSenderThreads(int threads) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument(threads > 0, "Number of threads must be positive: {0}", threads);

		senderThreads = threads;

		return this;
	}

	/**
	 * Sets the TTL of multicast packets sent by this sender.
	 *
	 * @throws IllegalArgumentException if {@code timeToLive} is not positive
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSender withTimeToLive(int timeToLive) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument(timeToLive > 0, "Time to live must be positive: {0}", timeToLive);

		this.timeToLive = timeToLive;

		return this;
	}

	/**
	 * Sets the traffic class of datagrams sent by this sender.
	 *
	 * @throws IllegalArgumentException if {@code trafficClass} is {@code null}
	 * @throws IllegalStateException if this sender has already been started
	 *
	 * @since 1.0
	 */
	public DatagramSender withTrafficClass(TrafficClass trafficClass) {
		Contract.checkState(state == STOPPED, "Sender has already been started");
		Contract.checkArgument(trafficClass != null, "Traffic class address must not be null");

		this.trafficClass = trafficClass;

		return this;
	}


	private void clearQueue() {
		pendingMessages.clear();
	}

	private void createChannel() throws IOException {
		channel = DatagramChannel.open(protocolFamily);
		channel.configureBlocking(true);
		channel.setOption(StandardSocketOptions.IP_TOS, trafficClass.getValue());
		channel.setOption(StandardSocketOptions.SO_BROADCAST, true);
		channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
		channel.setOption(StandardSocketOptions.SO_SNDBUF, sendBufferSize);

		if (networkInterface != null) {
			channel.setOption(StandardSocketOptions.IP_MULTICAST_IF, networkInterface);
			channel.setOption(StandardSocketOptions.IP_MULTICAST_LOOP, true);
			channel.setOption(StandardSocketOptions.IP_MULTICAST_TTL, timeToLive);
		}

		channel.bind(localEndPoint);
	}

	private Thread createSenderThread(Runnable sender) {
		Thread thread = new Thread(sender);
		thread.setDaemon(true);
		thread.setName("DatagramSender.SenderThread@" + System.identityHashCode(thread));
		thread.setPriority(senderThreadPriority);

		return thread;
	}

	private void destroyChannel() {
		Io.close(channel);
	}

	private void startBackgroundThreads() {
		senderExecutor = Executors.newFixedThreadPool(senderThreads, this::createSenderThread);
		for (int i = 0; i < senderThreads; i++) {
			senderExecutor.submit(new Sender());
		}
	}

	private void stopBackgroundThreads() {
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

	private class Sender implements Runnable {

		@Override
		public void run() {
			while (!Thread.interrupted()) {
				try {
					DelayedNetworkMessage message = pendingMessages.take();
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
