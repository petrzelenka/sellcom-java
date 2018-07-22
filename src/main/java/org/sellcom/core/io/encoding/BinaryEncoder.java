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
package org.sellcom.core.io.encoding;

import static java.nio.charset.StandardCharsets.US_ASCII;

import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.sellcom.core.Contract;

/**
 * Encoder for a binary encoding scheme.
 *
 * @since 1.0
 */
public abstract class BinaryEncoder {

	/**
	 * Encodes the given bytes.
	 *
	 * @throws IllegalArgumentException if {@code input} are {@code null}
	 *
	 * @since 1.0
	 */
	public byte[] encode(byte[] input) {
		Contract.checkArgument(input != null, "Input must not be null");

		return encodeToString(input).getBytes(US_ASCII);
	}

	/**
	 * Encodes the bytes in the given buffer.
	 *
	 * @throws IllegalArgumentException if {@code input} is {@code null}
	 *
	 * @since 1.0
	 */
	public ByteBuffer encode(ByteBuffer input) {
		Contract.checkArgument(input != null, "Input must not be null");

		byte[] bytes = new byte[input.remaining()];
		input.get(bytes);

		return ByteBuffer.wrap(encode(bytes));
	}

	/**
	 * Encodes the given {@code BigInteger} into a string.
	 *
	 * @throws IllegalArgumentException if {@code input} is {@code null}
	 *
	 * @since 1.0
	 */
	public String encodeToString(BigInteger input) {
		Contract.checkArgument(input != null, "Input must not be null");

		byte[] bytes = input.toByteArray();
		if (bytes[0] == 0) {
			bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
		}

		return encodeToString(bytes);
	}

	/**
	 * Encodes the given bytes into a string.
	 *
	 * @throws IllegalArgumentException if {@code input} are {@code null}
	 *
	 * @since 1.0
	 */
	public abstract String encodeToString(byte[] input);

	/**
	 * Checks whether this encoder supports streaming.
	 *
	 * @since 1.0
	 */
	public boolean supportsStreaming() {
		return false;
	}

	/**
	 * Returns an {@code OutputStream} encoding the bytes written to it into the given {@code OutputStream}.
	 *
	 * @throws UnsupportedOperationException if this encoder does not support streaming
	 *
	 * @since 1.0
	 */
	public OutputStream wrap(OutputStream destination) {
		throw new UnsupportedOperationException();
	}

}
