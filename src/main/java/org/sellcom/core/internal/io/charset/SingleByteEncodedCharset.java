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
package org.sellcom.core.internal.io.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

import org.sellcom.core.collection.Iterables;

public abstract class SingleByteEncodedCharset extends Charset {

	private final int[] decodeTable;

	private final SparseIntArray encodeTable;


	protected SingleByteEncodedCharset(String canonicalName, String[] aliases, int[] decodeTable) {
		super(canonicalName, aliases);

		encodeTable = createEncodeTable(this.decodeTable = decodeTable);
	}


	@Override
	public CharsetDecoder newDecoder() {
		return new Decoder(this);
	}

	@Override
	public CharsetEncoder newEncoder() {
		return new Encoder(this);
	}


	private static SparseIntArray createEncodeTable(int[] decodeTable) {
		SortedMap<Integer, Integer> mappings = new TreeMap<>();
		for (int i = 0, j = decodeTable.length; i < j; i += 1) {
			if (decodeTable[i] != -1) {
				mappings.put(decodeTable[i], i);
			}
		}

		SparseIntArray encodeTable = new SparseIntArray();
		encodeTable.indices = toIntArray(mappings.keySet());
		encodeTable.values = toIntArray(mappings.values());

		return encodeTable;
	}

	private static int[] toIntArray(Iterable<Integer> iterable) {
		return Iterables.stream(iterable)
				.mapToInt(Integer::intValue)
				.toArray();
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private class Decoder extends CharsetDecoder {

		protected Decoder(Charset charset) {
			super(charset, 1.0F, 1.0F);
		}


		@Override
		protected CoderResult decodeLoop(ByteBuffer source, CharBuffer destination) {
			int sourceRemaining = source.remaining();
			int destinationRemaining = destination.remaining();

			while (sourceRemaining > 0) {
				if (destinationRemaining < 1) {
					return CoderResult.OVERFLOW;
				}

				int currentByte = Byte.toUnsignedInt(source.get());
				int currentChar = decodeTable[currentByte];
				if (currentChar == -1) {
					source.position(source.position() - 1);

					return CoderResult.malformedForLength(1);
				}

				destination.put((char) currentChar);

				sourceRemaining -= 1;
				destinationRemaining -= 1;
			}

			return CoderResult.UNDERFLOW;
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private class Encoder extends CharsetEncoder {

		protected Encoder(Charset charset) {
			super(charset, 1.0F, 1.0F);
		}


		@Override
		protected CoderResult encodeLoop(CharBuffer source, ByteBuffer destination) {
			int sourceRemaining = source.remaining();
			int destinationRemaining = destination.remaining();

			while (sourceRemaining > 0) {
				if (destinationRemaining < 1) {
					return CoderResult.OVERFLOW;
				}

				int currentChar = source.get();
				int currentByte = encodeTable.get(currentChar);
				if (currentByte == -1) {
					source.position(source.position() - 1);

					return CoderResult.unmappableForLength(1);
				}

				destination.put((byte) currentByte);

				sourceRemaining -= 1;
				destinationRemaining -= 1;
			}

			return CoderResult.UNDERFLOW;
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class SparseIntArray {

		private int[] indices;

		private int[] values;


		private int get(int index) {
			int valueIndex = Arrays.binarySearch(indices, index);

			return (valueIndex == -1) ? -1 : values[valueIndex];
		}

	}

}
