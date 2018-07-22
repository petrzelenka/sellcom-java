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
package org.sellcom.core.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.math.MathEvaluators;

/**
 * Mathematical operations.
 *
 * @since 1.0
 */
public class MoreMath {

	private MoreMath() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the absolute difference of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigDecimal absDifference(BigDecimal x, BigDecimal y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return absExact(subtractExact(x, y));
	}

	/**
	 * Returns the absolute difference of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger absDifference(BigInteger x, BigInteger y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return absExact(subtractExact(x, y));
	}

	/**
	 * Returns the absolute difference of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 * @throws IllegalArgumentException if {@code y} is not finite
	 *
	 * @since 1.0
	 */
	public static double absDifference(double x, double y) {
		Contract.checkArgument(Double.isFinite(x), "X must be finite: {0}", x);
		Contract.checkArgument(Double.isFinite(y), "Y must be finite: {0}", y);

		return Math.abs(x - y);
	}

	/**
	 * Returns the absolute difference of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 * @throws IllegalArgumentException if {@code y} is not finite
	 *
	 * @since 1.0
	 */
	public static float absDifference(float x, float y) {
		Contract.checkArgument(Float.isFinite(x), "X must be finite: {0}", x);
		Contract.checkArgument(Float.isFinite(y), "Y must be finite: {0}", y);

		return Math.abs(x - y);
	}

	/**
	 * Returns the absolute difference of the given numbers.
	 *
	 * @throws ArithmeticException if the result does not exactly fit an {@code int}
	 *
	 * @since 1.0
	 */
	public static int absDifference(int x, int y) {
		return absExact(subtractExact(x, y));
	}

	/**
	 * Returns the absolute difference of the given numbers.
	 *
	 * @throws ArithmeticException if the result does not exactly fit an {@code int}
	 *
	 * @since 1.0
	 */
	public static long absDifference(long x, long y) {
		return absExact(subtractExact(x, y));
	}

	/**
	 * Returns the absolute value of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigDecimal absExact(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.abs();
	}

	/**
	 * Returns the absolute value of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger absExact(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.abs();
	}

	/**
	 * Returns the absolute value of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code NaN}
	 *
	 * @since 1.0
	 */
	public static double absExact(double x) {
		Contract.checkArgument(!Double.isNaN(x), "X must not be NaN");

		return Math.abs(x);
	}

	/**
	 * Returns the absolute value of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code NaN}
	 *
	 * @since 1.0
	 */
	public static float absExact(float x) {
		Contract.checkArgument(!Float.isNaN(x), "X must not be NaN");

		return Math.abs(x);
	}

	/**
	 * Returns the absolute value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} is equal to {@code Integer.MIN_VALUE}
	 *
	 * @since 1.0
	 */
	public static int absExact(int x) {
		Contract.check(x != Integer.MIN_VALUE, ArithmeticException.class, "Integer overflow");

		return Math.abs(x);
	}

	/**
	 * Returns the absolute value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} is equal to {@code Long.MIN_VALUE}
	 *
	 * @since 1.0
	 */
	public static long absExact(long x) {
		Contract.check(x != Long.MIN_VALUE, ArithmeticException.class, "Integer overflow");

		return Math.abs(x);
	}

	/**
	 * Returns the sum of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigDecimal addExact(BigDecimal x, BigDecimal y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return x.add(y);
	}

	/**
	 * Returns the sum of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger addExact(BigInteger x, BigInteger y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return x.add(y);
	}

	/**
	 * Returns the sum of the given numbers.
	 *
	 * @throws ArithmeticException if the result does not exactly fit an {@code int}
	 *
	 * @since 1.0
	 */
	public static int addExact(int x, int y) {
		int result = x + y;
		if (((x ^ result) & (y ^ result)) < 0) {
			throw new ArithmeticException("Integer overflow");
		}

		return result;
	}

	/**
	 * Returns the sum of the given numbers.
	 *
	 * @throws ArithmeticException if the result does not exactly fit a {@code long}
	 *
	 * @since 1.0
	 */
	public static long addExact(long x, long y) {
		long result = x + y;
		if (((x ^ result) & (y ^ result)) < 0L) {
			throw new ArithmeticException("Integer overflow");
		}

		return result;
	}

	/**
	 * Returns the smallest (closest to negative infinity) multiple of the given base that is greater or equal to the given number.
	 *
	 * @throws IllegalArgumentException if {@code number} is {@code null}
	 * @throws IllegalArgumentException if {@code base} is {@code null} or is not positive
	 *
	 * @since 1.4
	 */
	public static BigDecimal ceilToMultiple(BigDecimal number, BigDecimal base) {
		Contract.checkArgument(number != null, "Number must not be null");
		Contract.checkArgument(base != null, "Base must not be null");
		Contract.checkArgument(base.compareTo(BigDecimal.ZERO) > 0, "Base must be positive: {0}", base);

		return roundToMultiple(number, base, RoundingMode.CEILING);
	}

	/**
	 * Returns the smallest (closest to negative infinity) multiple of the given base that is greater or equal to the given number.
	 *
	 * @throws IllegalArgumentException if {@code number} is {@code null}
	 * @throws IllegalArgumentException if {@code base} is {@code null} or is not positive
	 *
	 * @since 1.4
	 */
	public static BigInteger ceilToMultiple(BigInteger number, BigInteger base) {
		Contract.checkArgument(number != null, "Number must not be null");
		Contract.checkArgument(base != null, "Base must not be null");
		Contract.checkArgument(base.compareTo(BigInteger.ZERO) > 0, "Base must be positive: {0}", base);

		return roundToMultiple(number, base, RoundingMode.CEILING);
	}

	/**
	 * Returns the smallest (closest to negative infinity) multiple of the given base that is greater or equal to the given number.
	 *
	 * @throws IllegalArgumentException if {@code base} is not positive
	 *
	 * @since 1.4
	 */
	public static int ceilToMultiple(int number, int base) {
		Contract.checkArgument(base > 0, "Base must be positive: {0}", base);

		// NOTE: Do not use IEEE-754 as the double precision is not sufficient enough to ensure correct results.

		return toIntExact(ceilToMultiple(BigInteger.valueOf(number), BigInteger.valueOf(base)));
	}

	/**
	 * Returns the smallest (closest to negative infinity) multiple of the given base that is greater or equal to the given number.
	 *
	 * @throws IllegalArgumentException if {@code base} is not positive
	 *
	 * @since 1.4
	 */
	public static long ceilToMultiple(long number, long base) {
		Contract.checkArgument(base > 0L, "Base must be positive: {0}", base);

		// NOTE: Do not use IEEE-754 as the double precision is not sufficient enough to ensure correct results.

		return toLongExact(ceilToMultiple(BigInteger.valueOf(number), BigInteger.valueOf(base)));
	}

	/**
	 * Returns the given number decremented by one.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger decrementExact(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.subtract(BigInteger.ONE);
	}

	/**
	 * Returns the given number decremented by one.
	 *
	 * @throws ArithmeticException if the result does not exactly fit an {@code int}
	 *
	 * @since 1.0
	 */
	public static int decrementExact(int x) {
		Contract.check(x != Integer.MIN_VALUE, ArithmeticException.class, "Integer overflow");

		return x - 1;
	}

	/**
	 * Returns the given number decremented by one.
	 *
	 * @throws ArithmeticException if the result does not exactly fit a {@code long}
	 *
	 * @since 1.0
	 */
	public static long decrementExact(long x) {
		Contract.check(x != Long.MIN_VALUE, ArithmeticException.class, "Integer overflow");

		return x - 1L;
	}

	/**
	 * Checks whether the given numbers are considered equal in the context of the given tolerance.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 * @throws IllegalArgumentException if {@code y} is not finite
	 * @throws IllegalArgumentException if {@code tolerance} is not finite
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static boolean equals(double x, double y, double tolerance) {
		Contract.checkArgument(Double.isFinite(tolerance), "Tolerance must be finite: {0}", tolerance);
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		return absDifference(x, y) <= tolerance;
	}

	/**
	 * Checks whether the given numbers are considered equal in the context of the given tolerance.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 * @throws IllegalArgumentException if {@code y} is not finite
	 * @throws IllegalArgumentException if {@code tolerance} is not finite
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static boolean equals(float x, float y, float tolerance) {
		Contract.checkArgument(Float.isFinite(tolerance), "Tolerance must be finite: {0}", tolerance);
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		return absDifference(x, y) <= tolerance;
	}

	/**
	 * Returns the largest (closest to positive infinity) multiple of the given base that is smaller or equal to the given number.
	 *
	 * @throws IllegalArgumentException if {@code number} is {@code null}
	 * @throws IllegalArgumentException if {@code base} is {@code null} or is not positive
	 *
	 * @since 1.4
	 */
	public static BigDecimal floorToMultiple(BigDecimal number, BigDecimal base) {
		Contract.checkArgument(number != null, "Number must not be null");
		Contract.checkArgument(base != null, "Base must not be null");
		Contract.checkArgument(base.compareTo(BigDecimal.ZERO) > 0, "Base must be positive: {0}", base);

		return roundToMultiple(number, base, RoundingMode.FLOOR);
	}

	/**
	 * Returns the largest (closest to positive negative) multiple of the given base that is smaller or equal to the given number.
	 *
	 * @throws IllegalArgumentException if {@code number} is {@code null}
	 * @throws IllegalArgumentException if {@code base} is {@code null} or is not positive
	 *
	 * @since 1.4
	 */
	public static BigInteger floorToMultiple(BigInteger number, BigInteger base) {
		Contract.checkArgument(number != null, "Number must not be null");
		Contract.checkArgument(base != null, "Base must not be null");
		Contract.checkArgument(base.compareTo(BigInteger.ZERO) > 0, "Base must be positive: {0}", base);

		return roundToMultiple(number, base, RoundingMode.FLOOR);
	}

	/**
	 * Returns the largest (closest to positive negative) multiple of the given base that is smaller or equal to the given number.
	 *
	 * @throws IllegalArgumentException if {@code base} is not positive
	 *
	 * @since 1.4
	 */
	public static int floorToMultiple(int number, int base) {
		Contract.checkArgument(base > 0, "Base must be positive: {0}", base);

		// NOTE: Do not use IEEE-754 as the double precision is not sufficient enough to ensure correct results

		return toIntExact(floorToMultiple(BigInteger.valueOf(number), BigInteger.valueOf(base)));
	}

	/**
	 * Returns the largest (closest to positive negative) multiple of the given base that is smaller or equal to the given number.
	 *
	 * @throws IllegalArgumentException if {@code base} is not positive
	 *
	 * @since 1.4
	 */
	public static long floorToMultiple(long number, long base) {
		Contract.checkArgument(base > 0L, "Base must be positive: {0}", base);

		// NOTE: Do not use IEEE-754 as the double precision is not sufficient enough to ensure correct results

		return toLongExact(floorToMultiple(BigInteger.valueOf(number), BigInteger.valueOf(base)));
	}

	/**
	 * Returns the fractional part of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigDecimal fractionalPart(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.remainder(BigDecimal.ONE);
	}

	/**
	 * Returns the fractional part of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.0
	 */
	public static double fractionalPart(double x) {
		Contract.checkArgument(Double.isFinite(x), "X must be finite: {0}", x);

		return x % 1.0;
	}

	/**
	 * Returns the fractional part of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.0
	 */
	public static float fractionalPart(float x) {
		Contract.checkArgument(Float.isFinite(x), "X must be finite: {0}", x);

		return x % 1.0F;
	}

	/**
	 * Returns the greatest common divisor of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger gcd(BigInteger x, BigInteger y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return x.gcd(y);
	}

	/**
	 * Returns the greatest common divisor of the given numbers.
	 *
	 * @throws ArithmeticException if {@code x} is equal to {@code Integer.MIN_VALUE}
	 * @throws ArithmeticException if {@code y} is equal to {@code Integer.MIN_VALUE}
	 *
	 * @since 1.0
	 */
	public static int gcd(int x, int y) { // Stein's algorithm
		if (x < 0) {
			x = absExact(x);
		}
		if (y < 0) {
			y = absExact(y);
		}

		if (x == 0) {
			return y;
		}
		if (y == 0) {
			return x;
		}
		if (x == y) {
			return y;
		}

		int shift = 0;
		while (((x | y) & 1) == 0) {
			x >>= 1;
			y >>= 1;
			shift += 1;
		}

		while ((x & 1) == 0) {
			x >>= 1;
		}

		do {
			while ((y & 1) == 0) {
				y >>= 1;
			}

			if (x > y) {
				int tmp = x;
				x = y;
				y = tmp;
			}

			y = subtractExact(y, x);
		} while (y != 0);

		return x << shift;
	}

	/**
	 * Returns the greatest common divisor of the given numbers.
	 *
	 * @throws ArithmeticException if {@code x} is equal to {@code Long.MIN_VALUE}
	 * @throws ArithmeticException if {@code y} is equal to {@code Long.MIN_VALUE}
	 *
	 * @since 1.0
	 */
	public static long gcd(long x, long y) { // Stein's algorithm
		if (x < 0L) {
			x = absExact(x);
		}
		if (y < 0L) {
			y = absExact(y);
		}

		if (x == 0L) {
			return y;
		}
		if (y == 0L) {
			return x;
		}
		if (x == y) {
			return y;
		}

		int shift = 0;
		while (((x | y) & 1L) == 0L) {
			x >>= 1;
			y >>= 1;
			shift += 1;
		}

		while ((x & 1L) == 0L) {
			x >>= 1;
		}

		do {
			while ((y & 1L) == 0L) {
				y >>= 1;
			}

			if (x > y) {
				long tmp = x;
				x = y;
				y = tmp;
			}

			y = subtractExact(y, x);
		} while (y != 0L);

		return x << shift;
	}

	/**
	 * Returns the given number incremented by one.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger incrementExact(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.add(BigInteger.ONE);
	}

	/**
	 * Returns the given number incremented by one.
	 *
	 * @throws ArithmeticException if the result does not exactly fit an {@code int}
	 *
	 * @since 1.0
	 */
	public static int incrementExact(int x) {
		Contract.check(x != Integer.MAX_VALUE, ArithmeticException.class, "Integer overflow");

		return x + 1;
	}

	/**
	 * Returns the given number incremented by one.
	 *
	 * @throws ArithmeticException if the result does not exactly fit a {@code long}
	 *
	 * @since 1.0
	 */
	public static long incrementExact(long x) {
		Contract.check(x != Long.MAX_VALUE, ArithmeticException.class, "Integer overflow");

		return x + 1L;
	}

	/**
	 * Returns the integral part of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.6
	 */
	public static BigDecimal integralPart(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.setScale(0, RoundingMode.DOWN);
	}

	/**
	 * Returns the integral part of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.6
	 */
	public static long integralPart(double x) {
		Contract.checkArgument(Double.isFinite(x), "X must be finite: {0}", x);

		return (x < 0) ? (long) Math.ceil(x) : (long) Math.floor(x);
	}

	/**
	 * Returns the integral part of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.6
	 */
	public static int integralPart(float x) {
		Contract.checkArgument(Float.isFinite(x), "X must be finite: {0}", x);

		return (x < 0) ? (int) Math.ceil(x) : (int) Math.floor(x);
	}

	/**
	 * Checks whether the given number is even.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isEven(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.getLowestSetBit() != 0;
	}

	/**
	 * Checks whether the given number is even.
	 *
	 * @since 1.0
	 */
	public static boolean isEven(int x) {
		return (x & 1) == 0;
	}

	/**
	 * Checks whether the given number is even.
	 *
	 * @since 1.0
	 */
	public static boolean isEven(long x) {
		return (x & 1L) == 0L;
	}

	/**
	 * Checks whether the given number is odd.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isOdd(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.getLowestSetBit() == 1;
	}

	/**
	 * Checks whether the given number is odd.
	 *
	 * @since 1.0
	 */
	public static boolean isOdd(int x) {
		return (x & 1) != 0;
	}

	/**
	 * Checks whether the given number is odd.
	 *
	 * @since 1.0
	 */
	public static boolean isOdd(long x) {
		return (x & 1L) != 0L;
	}

	/**
	 * Returns the least common multiple of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger lcm(BigInteger x, BigInteger y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		if (x.equals(BigInteger.ZERO) && (y.equals(BigInteger.ZERO))) {
			return BigInteger.ZERO;
		} else {
			return (absExact(x).divide(gcd(x, y))).multiply(absExact(y));
		}
	}

	/**
	 * Returns the least common multiple of the given numbers.
	 *
	 * @throws ArithmeticException if {@code x} is equal to {@code Integer.MIN_VALUE}
	 * @throws ArithmeticException if {@code y} is equal to {@code Integer.MIN_VALUE}
	 *
	 * @since 1.0
	 */
	public static int lcm(int x, int y) {
		if ((x == 0) && (y == 0)) {
			return 0;
		} else {
			return multiplyExact(absExact(x) / gcd(x, y), absExact(y));
		}
	}

	/**
	 * Returns the least common multiple of the given numbers.
	 *
	 * @throws ArithmeticException if {@code x} is equal to {@code Long.MIN_VALUE}
	 * @throws ArithmeticException if {@code y} is equal to {@code Long.MIN_VALUE}
	 *
	 * @since 1.0
	 */
	public static long lcm(long x, long y) {
		if ((x == 0L) && (y == 0L)) {
			return 0L;
		} else {
			return multiplyExact(absExact(x) / gcd(x, y), absExact(y));
		}
	}

	/**
	 * Returns the magnitude (the number of digits left to the decimal point) of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.6
	 */
	public static int magnitude(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.precision() - x.scale();
	}

	/**
	 * Returns the product of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigDecimal multiplyExact(BigDecimal x, BigDecimal y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return x.multiply(y);
	}

	/**
	 * Returns the product of the given numbers.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger multiplyExact(BigInteger x, BigInteger y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return x.multiply(y);
	}

	/**
	 * Returns the product of the given numbers.
	 *
	 * @throws ArithmeticException if the result does not exactly fit an {@code int}
	 *
	 * @since 1.0
	 */
	public static int multiplyExact(int x, int y) {
		return toIntExact((long) x * (long) y);
	}

	/**
	 * Returns the product of the given numbers.
	 *
	 * @throws ArithmeticException if the result does not exactly fit a {@code long}
	 *
	 * @since 1.0
	 */
	public static long multiplyExact(long x, long y) {
		long result = x * y;
		if ((((Math.abs(x) | Math.abs(y)) >>> 31) != 0L)) {
			if (((y != 0L) && ((result / y) != x)) || ((x == Long.MIN_VALUE) && (y == -1L))) {
				throw new ArithmeticException("Integer overflow");
			}
		}

		return result;
	}

	/**
	 * Returns the negation of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigDecimal negateExact(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.negate();
	}

	/**
	 * Returns the negation of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger negateExact(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.negate();
	}

	/**
	 * Returns the negation of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code NaN}
	 *
	 * @since 1.0
	 */
	public static double negateExact(double x) {
		Contract.checkArgument(!Double.isNaN(x), "X must not be NaN");

		return -x;
	}

	/**
	 * Returns the negation of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code NaN}
	 *
	 * @since 1.0
	 */
	public static float negateExact(float x) {
		Contract.checkArgument(!Float.isNaN(x), "X must not be NaN");

		return -x;
	}

	/**
	 * Returns the negation of the given number.
	 *
	 * @throws ArithmeticException if {@code x} is equal to {@code Integer.MIN_VALUE}
	 *
	 * @since 1.0
	 */
	public static int negateExact(int x) {
		Contract.check(x != Integer.MIN_VALUE, ArithmeticException.class, "Integer overflow");

		return -x;
	}

	/**
	 * Returns the negation of the given number.
	 *
	 * @throws ArithmeticException if {@code x} is equal to {@code Long.MIN_VALUE}
	 *
	 * @since 1.0
	 */
	public static long negateExact(long x) {
		Contract.check(x != Long.MIN_VALUE, ArithmeticException.class, "Integer overflow");

		return -x;
	}

	private static BigDecimal multiplyAndScale(BigDecimal x, BigDecimal y, int scale) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return x.multiply(y).setScale(scale, RoundingMode.HALF_UP);
	}

	/**
	 * Returns the <em>n</em>-th power of the given number.
	 * <p>
	 * Powering {@code BigDecimal.ZERO} to the value of {@code 0} returns {@code BigDecimal.ONE}.
	 *
	 * @throws ArithmeticException if {@code n} is out of range
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.6
	 */
	public static BigDecimal pow(BigDecimal x, long n, int scale) {
		Contract.checkArgument(x != null, "X must not be null");

		if (n == 0L) {
			return BigDecimal.ONE;
		}
		if (n < 0L) {
			return BigDecimal.ONE.divide(pow(x, -n, scale), scale, RoundingMode.HALF_UP);
		}

		int internalScale = scale + 3;

		BigDecimal result = BigDecimal.ONE;
		while (n > 0) {
			if (isOdd(n)) {
				result = multiplyAndScale(result, x, internalScale);
			}
			if (n > 1L) { // Skip unnecessary operations in the last step
				x = multiplyAndScale(x, x, internalScale);
			}

			n >>= 1;
		}

		return result.setScale(scale, RoundingMode.HALF_UP);
	}

	/**
	 * Returns the given base raised to the power of the given exponent.
	 *
	 * @throws ArithmeticException if the result does not exactly fit an {@code int}
	 * @throws IllegalArgumentException if {@code exponent} is negative
	 *
	 * @since 1.6
	 */
	public static int powExact(int base, int exponent) {
		Contract.checkArgument(exponent >= 0, "Exponent must not be negative: {0}", exponent);

		if (exponent == 0) {
			return 1;
		}

		return toIntExact(Math.pow(base, exponent));
	}

	/**
	 * Returns the given base raised to the power of the given exponent.
	 *
	 * @throws ArithmeticException if the result does not exactly fit an {@code int}
	 * @throws IllegalArgumentException if {@code exponent} is negative
	 *
	 * @since 1.6
	 */
	public static long powExact(long base, int exponent) {
		Contract.checkArgument(exponent >= 0, "Exponent must not be negative: {0}", exponent);

		if (exponent == 0) {
			return 1L;
		}

		return toLongExact(Math.pow(base, exponent));
	}

	/**
	 * Returns the <em>n</em>-th root of the given number.
	 *
	 * @throws ArithmeticException if {@code x} is negative and {@code n} is even
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code n} is not positive
	 *
	 * @since 1.6
	 */
	public static BigDecimal root(BigDecimal x, int n, int scale) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(n > 0, "N must be positive: {0}", n);

		if (isEven(n)) {
			Contract.check(x.compareTo(BigDecimal.ZERO) >= 0, ArithmeticException.class, "X must not be negative for even N: {0}, {1}", x, n);
		}

		if (n == 1) {
			return x;
		}
		if (x.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}
		if (x.compareTo(BigDecimal.ONE) == 0) {
			return BigDecimal.ONE;
		}

		BigDecimal bigDecimalN = BigDecimal.valueOf(n);
		int internalScale = scale + 3;

		return MathEvaluators.evaluateNewtonRaphson(
				y -> y.pow(n).subtract(x).divide(bigDecimalN.multiply(y.pow(n - 1)), internalScale, RoundingMode.HALF_UP), // (y^n - x) / (n · y^{n - 1})
				x,
				x.divide(bigDecimalN, internalScale, RoundingMode.HALF_UP), // Initial guess
				scale);
	}

	/**
	 * Returns a multiple of the given base that is closest to the given number.
	 *
	 * @throws IllegalArgumentException if {@code number} is {@code null}
	 * @throws IllegalArgumentException if {@code base} is {@code null} or is not positive
	 *
	 * @since 1.4
	 */
	public static BigDecimal roundToMultiple(BigDecimal number, BigDecimal base) {
		Contract.checkArgument(number != null, "Number must not be null");
		Contract.checkArgument(base != null, "Base must not be null");
		Contract.checkArgument(base.compareTo(BigDecimal.ZERO) > 0, "Base must be positive: {0}", base);

		return roundToMultiple(number, base, RoundingMode.HALF_UP);
	}

	/**
	 * Returns a multiple of the given base that is closest to the given number.
	 *
	 * @throws IllegalArgumentException if {@code number} is {@code null}
	 * @throws IllegalArgumentException if {@code base} is {@code null} or is not positive
	 * @throws IllegalArgumentException if {@code roundingMode} is {@code null}
	 *
	 * @since 1.4
	 */
	public static BigDecimal roundToMultiple(BigDecimal number, BigDecimal base, RoundingMode roundingMode) {
		Contract.checkArgument(number != null, "Number must not be null");
		Contract.checkArgument(base != null, "Base must not be null");
		Contract.checkArgument(base.compareTo(BigDecimal.ZERO) > 0, "Base must be positive: {0}", base);
		Contract.checkArgument(roundingMode != null, "Rounding mode must not be null");

		return base.multiply(number.divide(base, 0, roundingMode));
	}

	/**
	 * Returns a multiple of the given base that is closest to the given number.
	 *
	 * @throws IllegalArgumentException if {@code number} is {@code null}
	 * @throws IllegalArgumentException if {@code base} is {@code null} or is not positive
	 *
	 * @since 1.4
	 */
	public static BigInteger roundToMultiple(BigInteger number, BigInteger base) {
		Contract.checkArgument(number != null, "Number must not be null");
		Contract.checkArgument(base != null, "Base must not be null");
		Contract.checkArgument(base.compareTo(BigInteger.ZERO) > 0, "Base must be positive: {0}", base);

		return roundToMultiple(number, base, RoundingMode.HALF_UP);
	}

	/**
	 * Returns a multiple of the given base that is closest to the given number.
	 *
	 * @throws IllegalArgumentException if {@code number} is {@code null}
	 * @throws IllegalArgumentException if {@code base} is {@code null} or is not positive
	 * @throws IllegalArgumentException if {@code roundingMode} is {@code null}
	 *
	 * @since 1.4
	 */
	public static BigInteger roundToMultiple(BigInteger number, BigInteger base, RoundingMode roundingMode) {
		Contract.checkArgument(number != null, "Number must not be null");
		Contract.checkArgument(base != null, "Base must not be null");
		Contract.checkArgument(base.compareTo(BigInteger.ZERO) > 0, "Base must be positive: {0}", base);
		Contract.checkArgument(roundingMode != null, "Rounding mode must not be null");

		return base.multiply(new BigDecimal(number).divide(new BigDecimal(base), 0, roundingMode).toBigInteger());
	}

	/**
	 * Returns a multiple of the given base that is closest to the given number.
	 *
	 * @throws IllegalArgumentException if {@code base} is not positive
	 *
	 * @since 1.4
	 */
	public static int roundToMultiple(int number, int base) {
		Contract.checkArgument(base > 0, "Base must be positive: {0}", base);

		return Math.multiplyExact(base, Math.round((float) number / (float) base));
	}

	/**
	 * Returns a multiple of the given base that is closest to the given number.
	 *
	 * @throws IllegalArgumentException if {@code base} is not positive
	 *
	 * @since 1.4
	 */
	public static long roundToMultiple(long number, long base) {
		Contract.checkArgument(base > 0L, "Base must be positive: {0}", base);

		return Math.multiplyExact(base, Math.round((double) number / (double) base));
	}

	/**
	 * Returns the signum of the given value.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int signum(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.signum();
	}

	/**
	 * Returns the signum of the given value.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int signum(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.signum();
	}

	/**
	 * Returns the signum of the given value.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code NaN}
	 *
	 * @since 1.0
	 */
	public static int signum(double x) {
		Contract.checkArgument(!Double.isNaN(x), "X must not be NaN");

		return toIntExact(Math.signum(x));
	}

	/**
	 * Returns the signum of the given value.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code NaN}
	 *
	 * @since 1.0
	 */
	public static int signum(float x) {
		Contract.checkArgument(!Float.isNaN(x), "X must not be NaN");

		return toIntExact(Math.signum(x));
	}

	/**
	 * Returns the signum of the given value.
	 *
	 * @since 1.0
	 */
	public static int signum(int x) {
		return Integer.signum(x);
	}

	/**
	 * Returns the signum of the given value.
	 *
	 * @since 1.0
	 */
	public static int signum(long x) {
		return Long.signum(x);
	}

	/**
	 * Returns the difference of the given arguments.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigDecimal subtractExact(BigDecimal x, BigDecimal y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return x.subtract(y);
	}

	/**
	 * Returns the difference of the given arguments.
	 *
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 * @throws IllegalArgumentException if {@code y} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger subtractExact(BigInteger x, BigInteger y) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(y != null, "Y must not be null");

		return x.subtract(y);
	}

	/**
	 * Returns the difference of the given arguments.
	 *
	 * @throws ArithmeticException if the result does not exactly fit an {@code int}
	 *
	 * @since 1.0
	 */
	public static int subtractExact(int x, int y) {
		int result = x - y;
		if (((x ^ y) & (x ^ result)) < 0) {
			throw new ArithmeticException("Integer overflow");
		}

		return result;
	}

	/**
	 * Returns the difference of the given arguments.
	 *
	 * @throws ArithmeticException if the result does not exactly fit a {@code long}
	 *
	 * @since 1.0
	 */
	public static long subtractExact(long x, long y) {
		long result = x - y;
		if (((x ^ y) & (x ^ result)) < 0L) {
			throw new ArithmeticException("Integer overflow");
		}

		return result;
	}

	/**
	 * Returns the {@code BigInteger} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static BigInteger toBigIntegerExact(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.toBigIntegerExact();
	}

	/**
	 * Returns the {@code int} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws ArithmeticException if {@code x} does not exactly fit an {@code int}
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int toIntExact(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.intValueExact();
	}

	/**
	 * Returns the {@code int} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} does not exactly fit an {@code int}
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int toIntExact(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.intValueExact();
	}

	/**
	 * Returns the {@code int} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws ArithmeticException if {@code x} does not exactly fit an {@code int}
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.0
	 */
	public static int toIntExact(double x) {
		Contract.check(fractionalPart(x) == 0.0, ArithmeticException.class, "Not an integer");
		Contract.check((int) x == x, ArithmeticException.class, "Integer overflow");

		return (int) x;
	}

	/**
	 * Returns the {@code int} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws ArithmeticException if {@code x} does not exactly fit an {@code int}
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.0
	 */
	public static int toIntExact(float x) {
		Contract.check(fractionalPart(x) == 0.0F, ArithmeticException.class, "Not an integer");
		Contract.check((int) x == x, ArithmeticException.class, "Integer overflow");

		return (int) x;
	}

	/**
	 * Returns the {@code int} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} does not exactly fit an {@code int}
	 *
	 * @since 1.0
	 */
	public static int toIntExact(long x) {
		Contract.check((int) x == x, ArithmeticException.class, "Integer overflow");

		return (int) x;
	}

	/**
	 * Returns the {@code long} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code long}
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static long toLongExact(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.longValueExact();
	}

	/**
	 * Returns the {@code long} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code long}
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static long toLongExact(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.longValueExact();
	}

	/**
	 * Returns the {@code long} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code long}
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.0
	 */
	public static long toLongExact(double x) {
		Contract.check(fractionalPart(x) == 0.0, ArithmeticException.class, "Not an integer");
		Contract.check((int) x == x, ArithmeticException.class, "Integer overflow");

		return (long) x;
	}

	/**
	 * Returns the {@code long} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code long}
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.0
	 */
	public static long toLongExact(float x) {
		Contract.check(fractionalPart(x) == 0.0F, ArithmeticException.class, "Not an integer");
		Contract.check((int) x == x, ArithmeticException.class, "Integer overflow");

		return (long) x;
	}

	/**
	 * Returns the {@code short} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code short}
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static short toShortExact(BigDecimal x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.shortValueExact();
	}

	/**
	 * Returns the {@code short} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code short}
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.0
	 */
	public static short toShortExact(BigInteger x) {
		Contract.checkArgument(x != null, "X must not be null");

		return x.shortValueExact();
	}

	/**
	 * Returns the {@code short} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code short}
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.0
	 */
	public static short toShortExact(double x) {
		Contract.check(fractionalPart(x) == 0.0, ArithmeticException.class, "Not an integer");
		Contract.check((int) x == x, ArithmeticException.class, "Integer overflow");

		return (short) x;
	}

	/**
	 * Returns the {@code short} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} has a non-zero fractional part
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code short}
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.0
	 */
	public static short toShortExact(float x) {
		Contract.check(fractionalPart(x) == 0.0F, ArithmeticException.class, "Not an integer");
		Contract.check((int) x == x, ArithmeticException.class, "Integer overflow");

		return (short) x;
	}

	/**
	 * Returns the {@code short} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code short}
	 *
	 * @since 1.0
	 */
	public static short toShortExact(int x) {
		Contract.check((short) x == x, ArithmeticException.class, "Integer overflow");

		return (short) x;
	}

	/**
	 * Returns the {@code short} value of the given number.
	 *
	 * @throws ArithmeticException if {@code x} does not exactly fit a {@code short}
	 *
	 * @since 1.0
	 */
	public static short toShortExact(long x) {
		Contract.check((short) x == x, ArithmeticException.class, "Integer overflow");

		return (short) x;
	}

}
