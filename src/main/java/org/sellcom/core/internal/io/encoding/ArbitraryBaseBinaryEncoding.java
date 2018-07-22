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

import java.math.BigInteger;
import java.util.Arrays;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;
import org.sellcom.core.io.encoding.BinaryCodingException;
import org.sellcom.core.io.encoding.BinaryDecoder;
import org.sellcom.core.io.encoding.BinaryEncoder;
import org.sellcom.core.io.encoding.BinaryEncoding;
import org.sellcom.core.util.MoreArrays;

public abstract class ArbitraryBaseBinaryEncoding extends BinaryEncoding {

	private final BigInteger BASE;

	private int[] DECODE_TABLE;

	private final char[] ENCODE_TABLE;


	protected ArbitraryBaseBinaryEncoding(char[] alphabet) {
		Contract.checkArgument(alphabet != null, "Alphabet must not be null");

		BASE = BigInteger.valueOf(alphabet.length);
		DECODE_TABLE = createDecodeTable(ENCODE_TABLE = alphabet);
	}


	@Override
	public BinaryDecoder newDecoder() {
		return new Decoder();
	}

	@Override
	public BinaryEncoder newEncoder() {
		return new Encoder();
	}


	private static int[] createDecodeTable(char[] encodeTable) {
		int[] decodeTable = new int[128];

		Arrays.fill(decodeTable, -1);
		for (int i = 0, j = encodeTable.length; i < j; i += 1) {
			decodeTable[encodeTable[i]] = i;
		}

		return decodeTable;
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private class Decoder extends BinaryDecoder {

		@Override
		public byte[] decode(String input) {
			Contract.checkArgument(input != null, "Input must not be null");

			if (Strings.isNullOrEmpty(input)) {
				return new byte[0];
			}

			// Count leading zeros in input
			int inputLeadingZeros = 0;
			while ((inputLeadingZeros < input.length()) && (input.charAt(inputLeadingZeros) == ENCODE_TABLE[0])) {
				inputLeadingZeros += 1;
			}

			BigInteger value = BigInteger.ZERO;
			for (int i = 0, j = input.length(); i < j; i += 1) {
				char currentChar = input.charAt(i);

				int addend = (currentChar < 128) ? DECODE_TABLE[currentChar] : -1;
				if (addend == -1) {
					throw new BinaryCodingException(String.format("Illegal byte at position %d: 0x%02X", i, (int) currentChar));
				}

				value = value.multiply(BASE);
				value = value.add(BigInteger.valueOf(addend));
			}

			byte[] output = value.toByteArray();

			// Count leading zeros in output
			int outputLeadingZeros = 0;
			while ((outputLeadingZeros < output.length) && (output[outputLeadingZeros] == 0)) {
				outputLeadingZeros += 1;
			}

			// Preserve leading zeros
			if (outputLeadingZeros >= inputLeadingZeros) {
				return Arrays.copyOfRange(output, outputLeadingZeros - inputLeadingZeros, output.length);
			} else {
				return MoreArrays.concat(new byte[inputLeadingZeros - outputLeadingZeros], output);
			}
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private class Encoder extends BinaryEncoder {

		@Override
		public String encodeToString(byte[] input) {
			Contract.checkArgument(input != null, "Input must not be null");

			// Count leading zeros
			int leadingZeros = 0;
			while ((leadingZeros < input.length) && (input[leadingZeros] == 0)) {
				leadingZeros += 1;
			}

			StringBuilder builder = new StringBuilder();
			if (input.length > 0) {
				BigInteger value = new BigInteger(1, input);
				while (value.compareTo(BigInteger.ZERO) > 0) {
					BigInteger[] tmp = value.divideAndRemainder(BASE);
					builder.append(ENCODE_TABLE[tmp[1].intValue()]);
					value = tmp[0];
				}
			}

			// Preserve leading zeros
			while ((leadingZeros -= 1) >= 0) {
				builder.append(ENCODE_TABLE[0]);
			}

			return builder.reverse().toString();
		}

	}

}
