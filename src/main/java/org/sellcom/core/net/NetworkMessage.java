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

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.util.RandomUtils;
import org.sellcom.core.io.UnexpectedDataEndException;

/**
 * Network message.
 *
 * @since 1.0
 */
public class NetworkMessage {

	/**
	 * Length of the network message header (in bytes).
	 *
	 * @since 1.0
	 */
	public static final int HEADER_LENGTH = 20;

	// NOTE: NetworkMessage uses a custom process of generating random UUIDs
	// as it has been discovered in practice that the efficiency of the
	// SecureRandom instance underlying the standard UUID.randomUUID() method
	// is unpredictable.
	//
	// Considering that the random UUIDs are typically only used to detect
	// and discard duplicate messages, the SHA-1 PRNG seems to be a good
	// source of randomness because of its efficiency and general availability
	// even though it is usually considered to be cryptographically weak.
	private static final Random random;

	static {
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			throw new AssertionError("SecureRandom threw NoSuchAlgorithmException for SHA1PRNG");
		}
	}

	private ByteBuffer buffer;

	private byte[] payload = new byte[0];

	private InetSocketAddress remoteEndPoint;

	private UUID uuid;

	private boolean valid = true;


	private NetworkMessage() {
		// Not to be instantiated directly
	}


	/**
	 * Creates an empty network message.
	 *
	 * @since 1.0
	 */
	public static NetworkMessage create() {
		return new NetworkMessage();
	}

	/**
	 * Creates a {@code NetworkMessage} from the given {@code ByteBuffer} containing the raw data.
	 *
	 * @throws IllegalArgumentException if {@code buffer} is {@code null}
	 * @throws IllegalArgumentException if {@code remoteEndPoint} is {@code null}
	 *
	 * @since 1.0
	 */
	public static NetworkMessage fromByteBuffer(ByteBuffer buffer, InetSocketAddress remoteEndPoint) throws IOException {
		Contract.checkArgument(buffer != null, "Buffer must not be null");
		Contract.checkArgument(remoteEndPoint != null, "Remote end point must not be null");

		if (buffer.remaining() < NetworkMessage.HEADER_LENGTH) {
			throw new UnexpectedDataEndException(String.format("Expected %d bytes but got only %d", NetworkMessage.HEADER_LENGTH, buffer.remaining()));
		}

		NetworkMessage message = new NetworkMessage();
		message.remoteEndPoint = remoteEndPoint;
		message.uuid = new UUID(buffer.getLong(), buffer.getLong());

		int payloadLength = buffer.getInt();
		if (buffer.remaining() != payloadLength) {
			throw new UnexpectedDataEndException(String.format("Expected %d bytes but got only %d", payloadLength, buffer.remaining()));
		}

		message.payload = new byte[buffer.remaining()];
		buffer.get(message.payload);

		return message;
	}

	/**
	 * Returns the payload of this message.
	 *
	 * @since 1.0
	 */
	public byte[] getPayload() {
		return Arrays.copyOf(payload, payload.length);
	}

	/**
	 * Returns the address of the remote end point associated with this message.
	 *
	 * @since 1.0
	 */
	public InetAddress getRemoteAddress() {
		return (remoteEndPoint == null) ? null : remoteEndPoint.getAddress();
	}

	/**
	 * Returns the remote end point associated with this message.
	 *
	 * @since 1.0
	 */
	public InetSocketAddress getRemoteEndPoint() {
		return remoteEndPoint;
	}

	/**
	 * Returns the host name of the remote end point associated with this message.
	 *
	 * @since 1.0
	 */
	public String getRemoteHostName() {
		return (remoteEndPoint == null) ? null : remoteEndPoint.getHostName();
	}

	/**
	 * Returns the port of the remote end point associated with this message.
	 *
	 * @since 1.0
	 */
	public Integer getRemotePort() {
		return (remoteEndPoint == null) ? null : remoteEndPoint.getPort();
	}

	/**
	 * Returns the UUID of this message.
	 *
	 * @since 1.0
	 */
	public UUID getUuid() {
		return uuid;
	}

	/**
	 * Checks whether the message is valid.
	 *
	 * @since 1.0
	 */
	public boolean isValid() {
		return valid;
	}

	/**
	 * Returns a {@code ByteBuffer} containing the raw data of this message.
	 *
	 * @throws IllegalStateException if the UUID has not yet been set
	 *
	 * @since 1.0
	 */
	public ByteBuffer toByteBuffer() {
		Contract.checkState(uuid != null, "UUID has not yet been set");

		if (buffer == null) {
			ByteBuffer buffer = ByteBuffer.allocate(payload.length + HEADER_LENGTH);
			buffer.putLong(uuid.getMostSignificantBits());
			buffer.putLong(uuid.getLeastSignificantBits());
			buffer.putInt(payload.length);
			buffer.put(payload);
			buffer.flip();

			this.buffer = buffer;
		}

		return buffer.duplicate();
	}

	/**
	 * Sets the payload of this message.
	 *
	 * @throws IllegalArgumentException if {@code payload} is {@code null}
	 *
	 * @since 1.0
	 */
	public NetworkMessage withPayload(byte[] payload) {
		Contract.checkArgument(payload != null, "Payload must not be null");

		this.payload = Arrays.copyOf(payload, payload.length);

		return this;
	}

	/**
	 * Sets the UUID of this message to a random value.
	 *
	 * @since 1.0
	 */
	public NetworkMessage withRandomUuid() {
		uuid = RandomUtils.nextUuid(random);

		return this;
	}

	/**
	 * Sets the remote end point associated with this message.
	 *
	 * @throws IllegalArgumentException if {@code remoteEndPoint} is {@code null}
	 * @throws IllegalArgumentException if {@code remoteEndPoint.getPort()} is {@code 0}
	 *
	 * @since 1.0
	 */
	public NetworkMessage withRemoteEndPoint(InetSocketAddress remoteEndPoint) {
		Contract.checkArgument(remoteEndPoint != null, "Remote end point must not be null");
		Contract.checkArgument(remoteEndPoint.getPort() != 0, "Remote post must not be 0");

		this.remoteEndPoint = remoteEndPoint;

		return this;
	}

	/**
	 * Sets the UUID of this message.
	 *
	 * @throws IllegalArgumentException if {@code uuid} is {@code null}
	 *
	 * @since 1.0
	 */
	public NetworkMessage withUuid(UUID uuid) {
		Contract.checkArgument(uuid != null, "Identifier must not be null");

		this.uuid = uuid;

		return this;
	}

}
