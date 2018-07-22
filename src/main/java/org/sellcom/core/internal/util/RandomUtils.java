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
package org.sellcom.core.internal.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Random;
import java.util.UUID;

public class RandomUtils {

	private RandomUtils() {
		// Utility class, not to be instantiated
	}


	public static BigDecimal nextBigDecimal(Random random, BigDecimal from, BigDecimal to, int scale) {
		return from.add(_nextBigDecimal(random, to.subtract(from), scale));
	}

	public static BigInteger nextBigInteger(Random random, BigInteger from, BigInteger to) {
		return from.add(_nextBigInteger(random, to.subtract(from)));
	}

	public static byte nextByte(Random random, byte from, byte to) {
		return (byte) nextInt(random, from, to);
	}

	public static byte[] nextBytes(Random random, int count) {
		byte[] bytes = new byte[count];
		random.nextBytes(bytes);

		return bytes;
	}

	public static int nextInt(Random random, int from, int to) {
		return from + random.nextInt(to - from);
	}

	public static long nextLong(Random random, long from, long to) {
		return from + _nextLong(random, to - from);
	}

	public static short nextShort(Random random, short from, short to) {
		return (short) nextInt(random, from, to);
	}

	public static String nextString(Random random, String alphabet, int length) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			builder.append(alphabet.charAt(nextInt(random, 0, alphabet.length())));
		}

		return builder.toString();
	}

	public static UUID nextUuid(Random random) {
		byte[] randomBytes = nextBytes(random, 16);
		randomBytes[6]  &= 0x0f; // Clear version
		randomBytes[6]  |= 0x40; // Set version to 4
		randomBytes[8]  &= 0x3f; // Clear variant
		randomBytes[8]  |= 0x80; // Set variant to IETF

		long msb = 0L;
		for (int i = 0; i < 8; i++) {
				msb = (msb << 8) | (randomBytes[i] & 0xFF);
		}
		long lsb = 0L;
		for (int i = 8; i < 16; i++) {
				lsb = (lsb << 8) | (randomBytes[i] & 0xFF);
		}

		return new UUID(msb, lsb);
	}


	private static BigDecimal _nextBigDecimal(Random random, BigDecimal upperBound, int scale) {
		BigDecimal result = new BigDecimal(random.nextDouble()).multiply(upperBound);

		return result.setScale(scale, RoundingMode.HALF_UP);
	}

	private static BigInteger _nextBigInteger(Random random, BigInteger upperBound) {
		BigInteger result;

		do {
			result = new BigInteger(upperBound.bitLength(), random);
		} while (result.compareTo(upperBound) >= 0);

		return result;
	}

	private static long _nextLong(Random random, long upperBound) {
		long bits, result;

		do {
			bits = (random.nextLong() << 1) >>> 1;
			result = bits % upperBound;
		} while ((bits + result + (upperBound - 1)) < 0L);

		return result;
	}

}
