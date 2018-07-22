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
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

import org.sellcom.core.Contract;

/**
 * Fraction of two {@code BigInteger}s.
 * <p>
 * The value of the fraction is always kept reduced to the lowest possible terms.
 *
 * @since 1.6
 */
public class BigFraction extends Number implements Comparable<BigFraction> {

	/**
	 * Fraction of {@code 1/1}.
	 *
	 * @since 1.6
	 */
	public static final BigFraction ONE = valueOf(1L);

	/**
	 * Fraction of {@code 1/2}.
	 *
	 * @since 1.6
	 */
	public static final BigFraction ONE_HALF = valueOf(1L, 2L);

	/**
	 * Fraction of {@code 1/4}.
	 *
	 * @since 1.6
	 */
	public static final BigFraction ONE_QUARTER = valueOf(1L, 4L);

	/**
	 * Fraction of {@code 1/3}.
	 *
	 * @since 1.6
	 */
	public static final BigFraction ONE_THIRD = valueOf(1L, 3L);

	/**
	 * Fraction of {@code 3/4}.
	 *
	 * @since 1.6
	 */
	public static final BigFraction THREE_QUARTERS = valueOf(3L, 4L);

	/**
	 * Fraction of {@code 2/3}.
	 *
	 * @since 1.6
	 */
	public static final BigFraction TWO_THIRDS = valueOf(2L, 3L);

	/**
	 * Fraction of {@code 0/1}.
	 *
	 * @since 1.6
	 */
	public static final BigFraction ZERO = valueOf(0L);

	private static final long serialVersionUID = 8711587306362518263L;

	private BigInteger denominator;

	private BigInteger numerator;


	private BigFraction(BigInteger numerator, BigInteger denominator) {
		Contract.checkArgument(denominator.compareTo(BigInteger.ZERO) != 0, "Denominator must not be zero");

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
	public BigFraction abs() {
		return (numerator.compareTo(BigInteger.ZERO) < 0)
				? valueOf(MoreMath.absExact(numerator), denominator)
				: this;
	}

	/**
	 * Returns the sum of this fraction and the given fraction.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.6
	 */
	public BigFraction add(BigFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		if (other.equals(ZERO)) {
			return this;
		}

		if (denominator.compareTo(other.denominator) == 0) {
			return valueOf(numerator.add(other.numerator), denominator);
		}

		BigInteger commonDenominator = MoreMath.lcm(denominator, other.denominator);
		BigInteger numeratorCoefficient = commonDenominator.divide(denominator);
		BigInteger otherNumeratorCoefficient = commonDenominator.divide(other.denominator);

		return valueOf(numeratorCoefficient.multiply(numerator).add(otherNumeratorCoefficient.multiply(other.numerator)), commonDenominator);
	}

	/**
	 * Returns the sum of this fraction and the given number.
	 *
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 *
	 * @since 1.6
	 */
	public BigFraction add(BigInteger value) {
		Contract.checkArgument(value != null, "Value must not be null");

		if (value.compareTo(BigInteger.ZERO) == 0) {
			return this;
		}

		return valueOf(numerator.add(value.multiply(denominator)), denominator);
	}

	/**
	 * Returns the sum of this fraction and the given number.
	 *
	 * @since 1.6
	 */
	public BigFraction add(long value) {
		if (value == 0L) {
			return this;
		}

		return valueOf(numerator.add(BigInteger.valueOf(value).multiply(denominator)), denominator);
	}

	@Override
	public int compareTo(BigFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		BigInteger reductor = MoreMath.gcd(denominator, other.denominator);

		return numerator.multiply(other.denominator.divide(reductor)).compareTo(denominator.divide(reductor).multiply(other.numerator));
	}

	/**
	 * Returns the quotient of this fraction and the given fraction.
	 *
	 * @throws ArithmeticException if {@code other} is equal to {@code 0}
	 *
	 * @since 1.6
	 */
	public BigFraction divide(BigFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");
		Contract.check(!other.equals(ZERO), ArithmeticException.class, "Division by zero");

		if (other.equals(ONE)) {
			return this;
		}

		return valueOf(numerator.multiply(other.denominator), denominator.multiply(other.numerator));
	}

	/**
	 * Returns the quotient of this fraction and the given number.
	 *
	 * @throws ArithmeticException if {@code value} is equal to {@code 0}
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 *
	 * @since 1.6
	 */
	public BigFraction divide(BigInteger value) {
		Contract.checkArgument(value != null, "Value must not be null");
		Contract.check(value.compareTo(BigInteger.ZERO) != 0, ArithmeticException.class, "Division by zero");

		if (value.compareTo(BigInteger.ONE) == 0) {
			return this;
		}

		return valueOf(numerator, value.multiply(denominator));
	}

	/**
	 * Returns the quotient of this fraction and the given number.
	 *
	 * @throws ArithmeticException if {@code value} is equal to {@code 0}
	 *
	 * @since 1.6
	 */
	public BigFraction divide(long value) {
		Contract.check(value != 0, ArithmeticException.class, "Division by zero");

		if (value == 1L) {
			return this;
		}

		return valueOf(numerator, BigInteger.valueOf(value).multiply(denominator));
	}

	@Override
	public double doubleValue() {
		return new BigDecimal(numerator).divide(new BigDecimal(denominator), MathContext.DECIMAL64).doubleValue();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (other instanceof BigFraction) {
			BigFraction otherFraction = (BigFraction) other;

			return Objects.equals(numerator, otherFraction.numerator)
				&& Objects.equals(denominator, otherFraction.denominator);
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
	public BigInteger getDenominator() {
		return denominator;
	}

	/**
	 * Returns the numerator of this fraction.
	 *
	 * @since 1.6
	 */
	public BigInteger getNumerator() {
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
		return (denominator == BigInteger.ZERO);
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
	 * Returns the product of this fraction and the given fraction.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.6
	 */
	public BigFraction multiply(BigFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		if (other.equals(ZERO)) {
			return ZERO;
		}
		if (other.equals(ONE)) {
			return this;
		}

		return valueOf(numerator.multiply(other.numerator), denominator.multiply(other.denominator));
	}

	/**
	 * Returns the product of this fraction and the given number.
	 *
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 *
	 * @since 1.6
	 */
	public BigFraction multiply(BigInteger value) {
		Contract.checkArgument(value != null, "Value must not be null");

		if (value.compareTo(BigInteger.ZERO) == 0) {
			return ZERO;
		}
		if (value.compareTo(BigInteger.ONE) == 0) {
			return this;
		}

		return valueOf(value.multiply(numerator), denominator);
	}

	/**
	 * Returns the product of this fraction and the given number.
	 *
	 * @since 1.6
	 */
	public BigFraction multiply(long value) {
		if (value == 0L) {
			return ZERO;
		}
		if (value == 1L) {
			return this;
		}

		return valueOf(BigInteger.valueOf(value).multiply(numerator), denominator);
	}

	/**
	 * Returns the opposite of this fraction.
	 *
	 * @throws ArithmeticException if {@code numerator} is equal to {@code Integer.MIN_VALUE}
	 *
	 * @since 1.6
	 */
	public BigFraction negate() {
		return valueOf(numerator.negate(), denominator);
	}

	/**
	 * Returns a fraction obtained from the given text string.
	 *
	 * @throws IllegalArgumentException if {@code text} is {@code null}
	 * @throws NumberFormatException if {@code text} is not a valid fraction
	 *
	 * @since 1.6
	 */
	public static BigFraction parse(String text) {
		Contract.checkArgument(text != null, "Text must not be null");

		int separatorIndex = text.indexOf('/');
		if (separatorIndex == -1) {
			throw new NumberFormatException("Invalid fraction: " + text);
		}

		try {
			BigInteger numerator = new BigInteger(text.substring(0, separatorIndex).trim());
			BigInteger denominator = new BigInteger(text.substring(separatorIndex + 1).trim());

			if (denominator.compareTo(BigInteger.ZERO) == 0) {
				throw new NumberFormatException("Invalid fraction: " + text);
			}

			return new BigFraction(numerator, denominator);
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			throw new NumberFormatException("Invalid fraction: " + text);
		}
	}

	/**
	 * Returns this fraction raised to the power of the given exponent.
	 *
	 * @since 1.6
	 */
	public BigFraction pow(int exponent) {
		if (exponent == 0L) {
			return ONE;
		}
		if (exponent == 1L) {
			return this;
		}

		if (exponent < 0L) {
			return valueOf(denominator.pow(-exponent), numerator.pow(-exponent));
		} else {
			return valueOf(numerator.pow(exponent), denominator.pow(exponent));
		}
	}

	/**
	 * Return the reciprocal of this fraction.
	 *
	 * @throws ArithmeticException if the value of this fraction is equal to {@code 0}
	 *
	 * @since 1.6
	 */
	public BigFraction reciprocal() {
		Contract.check(numerator.compareTo(BigInteger.ZERO) != 0, ArithmeticException.class, "Division by zero");

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
		return numerator.signum();
	}

	/**
	 * Returns the difference of this fraction and the given fraction.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.6
	 */
	public BigFraction subtract(BigFraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		if (other.equals(ZERO)) {
			return this;
		}

		if (denominator.compareTo(other.denominator) == 0) {
			return valueOf(numerator.subtract(other.numerator), denominator);
		}

		BigInteger commonDenominator = MoreMath.lcm(denominator, other.denominator);
		BigInteger numeratorCoefficient = commonDenominator.divide(denominator);
		BigInteger otherNumeratorCoefficient = commonDenominator.divide(other.denominator);

		return valueOf(numeratorCoefficient.multiply(numerator).subtract(otherNumeratorCoefficient.multiply(other.numerator)), commonDenominator);
	}

	/**
	 * Returns the difference of this fraction and the given number.
	 *
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 *
	 * @since 1.6
	 */
	public BigFraction subtract(BigInteger value) {
		Contract.checkArgument(value != null, "Value must not be null");

		if (value.compareTo(BigInteger.ZERO) == 0) {
			return this;
		}

		return valueOf(numerator.subtract(value.multiply(denominator)), denominator);
	}

	/**
	 * Returns the difference of this fraction and the given number.
	 *
	 * @since 1.6
	 */
	public BigFraction subtract(long value) {
		if (value == 0L) {
			return this;
		}

		return valueOf(numerator.subtract(BigInteger.valueOf(value).multiply(denominator)), denominator);
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
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 *
	 * @since 1.6
	 */
	public static BigFraction valueOf(BigInteger value) {
		Contract.checkArgument(value != null, "Value must not be null");

		return new BigFraction(value, BigInteger.ONE);
	}

	/**
	 * Returns a fraction with the given numerator and denominator.
	 *
	 * @throws IllegalArgumentException if {@code denominator} is {@code null}
	 * @throws IllegalArgumentException if {@code denominator} is {@code 0}
	 * @throws IllegalArgumentException if {@code numerator} is {@code null}
	 *
	 * @since 1.6
	 */
	public static BigFraction valueOf(BigInteger numerator, BigInteger denominator) {
		Contract.checkArgument(numerator != null, "Numerator must not be null");
		Contract.checkArgument(denominator != null, "Denominator must not be null");

		return new BigFraction(numerator, denominator);
	}

	/**
	 * Returns a fraction with the given value.
	 *
	 * @since 1.6
	 */
	public static BigFraction valueOf(long value) {
		return new BigFraction(BigInteger.valueOf(value), BigInteger.ONE);
	}

	/**
	 * Returns a fraction with the given numerator and denominator.
	 *
	 * @throws IllegalArgumentException if {@code denominator} is {@code 0}
	 *
	 * @since 1.6
	 */
	public static BigFraction valueOf(long numerator, long denominator) {
		return new BigFraction(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
	}


	private void normalizeAndReduce() {
		// Make denominator equal to 1 if numerator is equal to 0
		if (numerator.compareTo(BigInteger.ZERO) == 0) {
			denominator = BigInteger.ONE;

			return;
		}

		// Make denominator positive
		if (denominator.compareTo(BigInteger.ZERO) < 0) {
			numerator = MoreMath.negateExact(numerator);
			denominator = MoreMath.negateExact(denominator);
		}

		// Reduce
		BigInteger reductor = MoreMath.gcd(numerator, denominator);
		if (reductor.compareTo(BigInteger.ONE) > 0) {
			numerator = numerator.divide(reductor);
			denominator = denominator.divide(reductor);
		}
	}

}
