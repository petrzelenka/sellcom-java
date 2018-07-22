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

import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;

import org.sellcom.core.Contract;
import org.sellcom.core.util.MoreArrays;

/**
 * Decoder for a binary encoding scheme.
 *
 * @since 1.0
 */
public abstract class BinaryDecoder {

	/**
	 * Decodes the given bytes.
	 *
	 * @throws BinaryCodingException if {@code input} are invalid for this encoding scheme
	 * @throws IllegalArgumentException if {@code bytes} are {@code null}
	 *
	 * @since 1.0
	 */
	public byte[] decode(byte[] input) {
		Contract.checkArgument(input != null, "Input must not be null");

		return decode(new String(input, US_ASCII));
	}

	/**
	 * Decodes the bytes in the given buffer.
	 *
	 * @throws BinaryCodingException if the bytes in {@code input} are invalid for this encoding scheme
	 * @throws IllegalArgumentException if {@code buffer} is {@code null}
	 *
	 * @since 1.0
	 */
	public ByteBuffer decode(ByteBuffer input) {
		Contract.checkArgument(input != null, "Input must not be null");

		byte[] bytes = new byte[input.remaining()];
		input.get(bytes);

		return ByteBuffer.wrap(decode(bytes));
	}

	/**
	 * Decodes the given string.
	 *
	 * @throws BinaryCodingException if the bytes represented by {@code string} are invalid for this encoding scheme
	 * @throws IllegalArgumentException if {@code input} is {@code null}
	 *
	 * @since 1.0
	 */
	public abstract byte[] decode(String input);

	/**
	 * Decodes the given string.
	 *
	 * @throws BinaryCodingException if the bytes represented by {@code string} are invalid for this encoding scheme
	 * @throws IllegalArgumentException if {@code input} is {@code null}
	 *
	 * @since 1.0
	 */
	public BigInteger decodeBigInteger(String input) {
		Contract.checkArgument(input != null, "Input must not be null");

		return new BigInteger(MoreArrays.concat(new byte[] { 0 }, decode(input)));
	}

	/**
	 * Checks whether this decoder supports streaming.
	 *
	 * @since 1.0
	 */
	public boolean supportsStreaming() {
		return false;
	}

	/**
	 * Returns an {@code InputStream} decoding bytes from the given {@code InputStream}.
	 *
	 * @throws UnsupportedOperationException if this decoder does not support streaming
	 *
	 * @since 1.0
	 */
	public InputStream wrap(InputStream stream) {
		throw new UnsupportedOperationException();
	}

}
