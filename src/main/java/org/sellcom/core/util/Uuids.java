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
package org.sellcom.core.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.ByteBuffer;
import java.util.UUID;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;
import org.sellcom.core.io.encoding.BinaryDecoder;
import org.sellcom.core.io.encoding.BinaryEncoder;
import org.sellcom.core.io.encoding.StandardBinaryEncodings;

/**
 * Operations with {@link UUID}s.
 *
 * @since 1.0
 */
public class Uuids {

	private static final BinaryDecoder BASE58_DECODER = StandardBinaryEncodings.createBase58FlickrDecoder();

	private static final BinaryEncoder BASE58_ENCODER = StandardBinaryEncodings.createBase58FlickrEncoder();


	private Uuids() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the given UUID formatted as a string of 8x4 hexadecimal digits separated by dashes.
	 *
	 * @throws IllegalArgumentException if {@code uuid} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String format8x4(UUID uuid) {
		Contract.checkArgument(uuid != null, "UUID must not be null");

		StringBuilder builder = new StringBuilder(uuid.toString());
		builder.insert(0x04, "-");
		builder.insert(0x1D, "-");
		builder.insert(0x22, "-");

		return Strings.toUpperCase(builder.toString());
	}

	/**
	 * Returns the given UUID formatted as a Base-58-encoded string.
	 *
	 * @throws IllegalArgumentException if {@code uuid} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String formatBase58(UUID uuid) {
		Contract.checkArgument(uuid != null, "UUID must not be null");

		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.putLong(uuid.getMostSignificantBits());
		buffer.putLong(uuid.getLeastSignificantBits());
		buffer.flip();

		return new String(BASE58_ENCODER.encode(buffer).array(), UTF_8);
	}

	/**
	 * Returns a type 3 (name-based) UUID based on the given bytes.
	 *
	 * @throws IllegalArgumentException if {@code bytes} are {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static UUID fromBytes(byte[] bytes) {
		Contract.checkArgument(!MoreArrays.isNullOrEmpty(bytes), "Bytes must not be null or empty");

		return UUID.nameUUIDFromBytes(bytes);
	}

	/**
	 * Returns a type 3 (name-based) UUID based on the given string.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static UUID fromString(String string) {
		Contract.checkArgument(!Strings.isNullOrEmpty(string), "String must not be null or empty");

		return UUID.nameUUIDFromBytes(string.getBytes(UTF_8));
	}

	/**
	 * Parses an UUID from the given string of 8x4 hexadecimal digits separated by dashes.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static UUID parse8x4(String string) {
		Contract.checkArgument(!Strings.isNullOrEmpty(string), "String must not be null or empty");

		StringBuilder builder = new StringBuilder(string);
		builder.deleteCharAt(0x22);
		builder.deleteCharAt(0X1D);
		builder.deleteCharAt(0x04);

		return UUID.fromString(builder.toString());
	}

	/**
	 * Parses an UUID from the given Base-58-encoded string.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 *
	 * @since 1.0
	 */
	public static UUID parseBase58(String string) {
		Contract.checkArgument(string != null, "String must not be null");

		ByteBuffer buffer = ByteBuffer.wrap(BASE58_DECODER.decode(string));

		return new UUID(buffer.getLong(), buffer.getLong());
	}

}
