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
import java.math.RoundingMode;
import java.util.Objects;

import org.sellcom.core.Contract;

/**
 * Fraction of two {@code long}s.
 * <p>
 * The value of the fraction is always kept reduced to the lowest possible terms.
 *
 * @since 1.6
 */
public class LongFraction extends Number implements Comparable<LongFraction> {

	/**
	 * Fraction of {@code 1/1}.
	 *
	 * @since 1.6
	 */
	public static final LongFraction ONE = valueOf(1L);

	/**
	 * Fraction of {@code 1/2}.
	 *
	 * @since 1.6
	 */
	public static final LongFraction ONE_HALF = valueOf(1L, 2L);

	/**
	 * Fraction of {@code 1/4}.
	 *
	 * @since 1.6
	 */
	public static final LongFraction ONE_QUARTER = valueOf(1L, 4L);

	/**
	 * Fraction of {@code 1/3}.
	 *
	 * @since 1.6
	 */
	public static final LongFraction ONE_THIRD = valueOf(1L, 3L);

	/**
	 * Fraction of {@code 3/4}.
	 *
	 * @since 1.6
	 */
	public static final LongFraction THREE_QUARTERS = valueOf(3L, 4L);

	/**
	 * Fraction of {@code 2/3}.
	 *
	 * @since 1.6
	 */
	public static final LongFraction TWO_THIRDS = valueOf(2L, 3L);

	/**
	 * Fraction of {@code 0/1}.
	 *
	 * @since 1.6
	 */
	public static final LongFraction ZERO = valueOf(0L);

	private static final long serialVersionUID = 3883659606867197439L;

	private long denominator;

	private long numerator;


	private LongFraction(long numerator, long denominator) {
		Contract.checkArgument(denominator != 0L, "Denominator must not be zero");

		this.numerator = numerator;
		this.denominator = denominator;

		normalizeAndReduce();
	}


	/**
	 * Returns the absolute value of this fraction.
	 *
	 * @throws ArithmeticException if {@code numerator} is equal to {@code Integer.MIN_VALUE}
	 *
	 * @since 1.6
	 */
	public LongFraction abs() {
		return (numerator < 0L)
				? valueOf(MoreMath.absExact(numerator), denominator)
				: this;
	}

	/**
	 * Returns the sum of this fraction and the given number.
	 *
	 * @since 1.6
	 */
	public LongFraction add(long value) {
		if (value == 0L) {
			return this;
		}

		return valueOf(Math.addExact(numerator, Math.multiplyExact(value, denominator)), denominator);
	}

	/**
	 * Returns the sum of this fraction and the given fraction.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.6
	 */
	public LongFraction add(LongFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		if (other.equals(ZERO)) {
			return this;
		}

		if (denominator == other.denominator) {
			return valueOf(Math.addExact(numerator, other.numerator), denominator);
		}

		long commonDenominator = MoreMath.lcm(denominator, other.denominator);
		long numeratorCoefficient = commonDenominator / denominator;
		long otherNumeratorCoefficient = commonDenominator / other.denominator;

		return valueOf(Math.addExact(Math.multiplyExact(numeratorCoefficient, numerator), Math.multiplyExact(otherNumeratorCoefficient, other.numerator)), commonDenominator);
	}

	@Override
	public int compareTo(LongFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		long reductor = MoreMath.gcd(denominator, other.denominator);

		return Long.compare(
				Math.multiplyExact(numerator, other.denominator / reductor),
				Math.multiplyExact(denominator / reductor, other.numerator));
	}

	/**
	 * Returns the quotient of this fraction and the given number.
	 *
	 * @throws ArithmeticException if {@code value} is equal to {@code 0}
	 *
	 * @since 1.6
	 */
	public LongFraction divide(long value) {
		Contract.check(value != 0, ArithmeticException.class, "Division by zero");

		if (value == 1L) {
			return this;
		}

		return valueOf(numerator, Math.multiplyExact(value, denominator));
	}

	/**
	 * Returns the quotient of this fraction and the given fraction.
	 *
	 * @throws ArithmeticException if {@code other} is equal to {@code 0}
	 *
	 * @since 1.6
	 */
	public LongFraction divide(LongFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");
		Contract.check(!other.equals(ZERO), ArithmeticException.class, "Division by zero");

		if (other.equals(ONE)) {
			return this;
		}

		return valueOf(Math.multiplyExact(numerator, other.denominator), Math.multiplyExact(denominator, other.numerator));
	}

	@Override
	public double doubleValue() {
		return (double) numerator / (double) denominator;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (other instanceof LongFraction) {
			LongFraction otherFraction = (LongFraction) other;

			return (numerator == otherFraction.numerator)
				&& (denominator == otherFraction.denominator);
		}

		return false;
	}

	@Override
	public float floatValue() {
		return (float) doubleValue();
	}

	/**
	 * Returns the denominator of this fraction.
	 *
	 * @since 1.6
	 */
	public long getDenominator() {
		return denominator;
	}

	/**
	 * Returns the numerator of this fraction.
	 *
	 * @since 1.6
	 */
	public long getNumerator() {
		return numerator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numerator, denominator);
	}

	@Override
	public int intValue() {
		return (int) doubleValue();
	}

	/**
	 * Checks whether the value of this fraction is an integer.
	 *
	 * @since 1.6
	 */
	public boolean isInteger() {
		return (denominator == 1L);
	}

	/**
	 * Returns the {@code int} value of this fraction.
	 *
	 * @throws IllegalArgumentException if the value of this fraction does not exactly fit an {@code int}
	 *
	 * @since 1.6
	 */
	public int intValueExact() {
		return MoreMath.toIntExact(doubleValue());
	}

	@Override
	public long longValue() {
		return (long) doubleValue();
	}

	/**
	 * Returns the {@code long} value of this fraction.
	 *
	 * @throws IllegalArgumentException if the value of this fraction does not exactly fit a {@code long}
	 *
	 * @since 1.6
	 */
	public long longValueExact() {
		return MoreMath.toLongExact(doubleValue());
	}

	/**
	 * Returns the product of this fraction and the given number.
	 *
	 * @since 1.6
	 */
	public LongFraction multiply(long value) {
		if (value == 0L) {
			return ZERO;
		}
		if (value == 1L) {
			return this;
		}

		return valueOf(Math.multiplyExact(value, numerator), denominator);
	}

	/**
	 * Returns the product of this fraction and the given fraction.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.6
	 */
	public LongFraction multiply(LongFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		if (other.equals(ZERO)) {
			return ZERO;
		}
		if (other.equals(ONE)) {
			return this;
		}

		return valueOf(Math.multiplyExact(numerator, other.numerator), Math.multiplyExact(denominator, other.denominator));
	}

	/**
	 * Returns the opposite of this fraction.
	 *
	 * @throws ArithmeticException if {@code numerator} is equal to {@code Integer.MIN_VALUE}
	 *
	 * @since 1.6
	 */
	public LongFraction negate() {
		return valueOf(Math.negateExact(numerator), denominator);
	}

	/**
	 * Returns a fraction obtained from the given text string.
	 *
	 * @throws IllegalArgumentException if {@code text} is {@code null}
	 * @throws NumberFormatException if {@code text} is not a valid fraction
	 *
	 * @since 1.6
	 */
	public static LongFraction parse(String text) {
		Contract.checkArgument(text != null, "Text must not be null");

		int separatorIndex = text.indexOf('/');
		if (separatorIndex == -1) {
			throw new NumberFormatException("Invalid fraction: " + text);
		}

		try {
			long numerator = Long.parseLong(text.substring(0, separatorIndex).trim());
			long denominator = Long.parseLong(text.substring(separatorIndex + 1).trim());

			if (denominator == 0L) {
				throw new NumberFormatException("Invalid fraction: " + text);
			}

			return new LongFraction(numerator, denominator);
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			throw new NumberFormatException("Invalid fraction: " + text);
		}
	}

	/**
	 * Returns this fraction raised to the power of the given exponent.
	 *
	 * @since 1.6
	 */
	public LongFraction pow(int exponent) {
		if (exponent == 0) {
			return ONE;
		}
		if (exponent == 1) {
			return this;
		}

		if (exponent < 0) {
			return valueOf(MoreMath.powExact(denominator, -exponent), MoreMath.powExact(numerator, -exponent));
		} else {
			return valueOf(MoreMath.powExact(numerator, exponent), MoreMath.powExact(denominator, exponent));
		}
	}

	/**
	 * Return the reciprocal of this fraction.
	 *
	 * @throws ArithmeticException if the value of this fraction is equal to {@code 0}
	 *
	 * @since 1.6
	 */
	public LongFraction reciprocal() {
		Contract.check(numerator != 0L, ArithmeticException.class, "Division by zero");

		return valueOf(denominator, numerator);
	}

	/**
	 * Returns the integer value nearest to the value of this fraction.
	 *
	 * @throws IllegalArgumentException if {@code roundingMode} is {@code null}
	 *
	 * @since 1.6
	 */
	public long round(RoundingMode roundingMode) {
		Contract.checkArgument(roundingMode != null, "Rounding mode must not be null");

		return toBigDecimal(0, roundingMode).longValueExact();
	}

	/**
	 * Returns the signum of this value.
	 *
	 * @since 1.6
	 */
	public int signum() {
		return Long.signum(numerator);
	}

	/**
	 * Returns the difference of this fraction and the given number.
	 *
	 * @since 1.6
	 */
	public LongFraction subtract(long value) {
		if (value == 0L) {
			return this;
		}

		return valueOf(Math.subtractExact(numerator, Math.multiplyExact(value, denominator)), denominator);
	}

	/**
	 * Returns the difference of this fraction and the given fraction.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.6
	 */
	public LongFraction subtract(LongFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		if (other.equals(ZERO)) {
			return this;
		}

		if (denominator == other.denominator) {
			return valueOf(Math.subtractExact(numerator, other.numerator), denominator);
		}

		long commonDenominator = MoreMath.lcm(denominator, other.denominator);
		long numeratorCoefficient = commonDenominator / denominator;
		long otherNumeratorCoefficient = commonDenominator / other.denominator;

		return valueOf(Math.subtractExact(Math.multiplyExact(numeratorCoefficient, numerator), Math.multiplyExact(otherNumeratorCoefficient, other.numerator)), commonDenominator);
	}

	/**
	 * Returns the {@code BigDecimal} value of this fraction.
	 *
	 * @throws IllegalArgumentException if the value of this fraction does not have a terminating decimal expansion
	 *
	 * @since 1.6
	 */
	public BigDecimal toBigDecimal() {
		return new BigDecimal(numerator).divide(new BigDecimal(denominator));
	}

	/**
	 * Returns the {@code BigDecimal} value of this fraction.
	 *
	 * @throws IllegalArgumentException if {@code roundingMode} is {@code null}
	 *
	 * @since 1.6
	 */
	public BigDecimal toBigDecimal(int scale, RoundingMode roundingMode) {
		Contract.checkArgument(roundingMode != null, "Rounding mode must not be null");

		return new BigDecimal(numerator).divide(new BigDecimal(denominator), scale, roundingMode);
	}

	@Override
	public String toString() {
		return String.format("%d/%d", numerator, denominator);
	}

	/**
	 * Returns a fraction with the given value.
	 *
	 * @since 1.6
	 */
	public static LongFraction valueOf(long value) {
		return new LongFraction(value, 1L);
	}

	/**
	 * Returns a fraction with the given numerator and denominator.
	 *
	 * @throws IllegalArgumentException if {@code denominator} is {@code 0}
	 *
	 * @since 1.6
	 */
	public static LongFraction valueOf(long numerator, long denominator) {
		return new LongFraction(numerator, denominator);
	}


	private void normalizeAndReduce() {
		// Make denominator equal to 1 if numerator is equal to 0
		if (numerator == 0L) {
			denominator = 1L;

			return;
		}

		// Make denominator positive
		if (denominator < 0L) {
			numerator = Math.negateExact(numerator);
			denominator = Math.negateExact(denominator);
		}

		// Reduce
		long reductor = MoreMath.gcd(numerator, denominator);
		if (reductor > 1L) {
			numerator /= reductor;
			denominator /= reductor;
		}
	}

}
