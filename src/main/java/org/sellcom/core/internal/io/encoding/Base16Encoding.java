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
package org.sellcom.core.internal.io.encoding;

import java.io.ByteArrayOutputStream;

import org.sellcom.core.Contract;
import org.sellcom.core.io.encoding.BinaryCodingException;
import org.sellcom.core.io.encoding.BinaryDecoder;
import org.sellcom.core.io.encoding.BinaryEncoder;
import org.sellcom.core.io.encoding.BinaryEncoding;
import org.sellcom.core.math.MoreMath;

public final class Base16Encoding extends BinaryEncoding {

	@Override
	public BinaryDecoder newDecoder() {
		return new Decoder();
	}

	@Override
	public BinaryEncoder newEncoder() {
		return new Encoder();
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class Decoder extends BinaryDecoder {

		@Override
		public byte[] decode(String input) {
			Contract.checkArgument(input != null, "Input must not be null");
			Contract.check(MoreMath.isEven(input.length()), BinaryCodingException.class, "Odd number of bytes: {0}", input.length());

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			for (int i = 0, j = input.length(); i < j; ) {
				byte hiNibble = (byte) Character.digit(input.charAt(i), 16);
				if (hiNibble == -1) {
					throw new BinaryCodingException(String.format("Illegal byte at position %d: 0x%02X", i, (int) input.charAt(i)));
				}
				i += 1;

				byte loNibble = (byte) Character.digit(input.charAt(i), 16);
				if (loNibble == -1) {
					throw new BinaryCodingException(String.format("Illegal byte at position %d: 0x%02X", i, (int) input.charAt(i)));
				}
				i += 1;

				stream.write((hiNibble << 4) | loNibble);
			}

			return stream.toByteArray();
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class Encoder extends BinaryEncoder {

		@Override
		public String encodeToString(byte[] input) {
			Contract.checkArgument(input != null, "Input must not be null");

			StringBuilder builder = new StringBuilder();
			for (int i = 0, j = input.length; i < j; i += 1) {
				byte currentByte = input[i];

				builder.append(Character.toUpperCase(Character.forDigit((currentByte >> 4) & 0x0F, 16)));
				builder.append(Character.toUpperCase(Character.forDigit((currentByte >> 0) & 0x0F, 16)));
			}

			return builder.toString();
		}

	}

}
