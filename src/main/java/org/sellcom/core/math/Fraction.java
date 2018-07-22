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
 * Fraction of two {@code int}s.
 * <p>
 * The value of the fraction is always kept reduced to the lowest possible terms.
 *
 * @since 1.6
 */
public class Fraction extends Number implements Comparable<Fraction> {

	/**
	 * Fraction of {@code 1/1}.
	 *
	 * @since 1.6
	 */
	public static final Fraction ONE = valueOf(1);

	/**
	 * Fraction of {@code 1/2}.
	 *
	 * @since 1.6
	 */
	public static final Fraction ONE_HALF = valueOf(1, 2);

	/**
	 * Fraction of {@code 1/4}.
	 *
	 * @since 1.6
	 */
	public static final Fraction ONE_QUARTER = valueOf(1, 4);

	/**
	 * Fraction of {@code 1/3}.
	 *
	 * @since 1.6
	 */
	public static final Fraction ONE_THIRD = valueOf(1, 3);

	/**
	 * Fraction of {@code 3/4}.
	 *
	 * @since 1.6
	 */
	public static final Fraction THREE_QUARTERS = valueOf(3, 4);

	/**
	 * Fraction of {@code 2/3}.
	 *
	 * @since 1.6
	 */
	public static final Fraction TWO_THIRDS = valueOf(2, 3);

	/**
	 * Fraction of {@code 0/1}.
	 *
	 * @since 1.6
	 */
	public static final Fraction ZERO = valueOf(0);

	private static final long serialVersionUID = 7020987637819229668L;

	private int denominator;

	private int numerator;


	private Fraction(int numerator, int denominator) {
		Contract.checkArgument(denominator != 0, "Denominator must not be zero");

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
	public Fraction abs() {
		return (numerator < 0)
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
	public Fraction add(Fraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		if (other.equals(ZERO)) {
			return this;
		}

		if (denominator == other.denominator) {
			return valueOf(Math.addExact(numerator, other.numerator), denominator);
		}

		int commonDenominator = MoreMath.lcm(denominator, other.denominator);
		int numeratorCoefficient = commonDenominator / denominator;
		int otherNumeratorCoefficient = commonDenominator / other.denominator;

		return valueOf(Math.addExact(Math.multiplyExact(numeratorCoefficient, numerator), Math.multiplyExact(otherNumeratorCoefficient, other.numerator)), commonDenominator);
	}

	/**
	 * Returns the sum of this fraction and the given number.
	 *
	 * @since 1.6
	 */
	public Fraction add(int value) {
		if (value == 0) {
			return this;
		}

		return valueOf(Math.addExact(numerator, Math.multiplyExact(value, denominator)), denominator);
	}

	@Override
	public int compareTo(Fraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		int reductor = MoreMath.gcd(denominator, other.denominator);

		return Integer.compare(
				Math.multiplyExact(numerator, other.denominator / reductor),
				Math.multiplyExact(denominator / reductor, other.numerator));
	}

	/**
	 * Returns the quotient of this fraction and the given fraction.
	 *
	 * @throws ArithmeticException if {@code other} is equal to {@code 0}
	 *
	 * @since 1.6
	 */
	public Fraction divide(Fraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");
		Contract.check(!other.equals(ZERO), ArithmeticException.class, "Division by zero");

		if (other.equals(ONE)) {
			return this;
		}

		return valueOf(Math.multiplyExact(numerator, other.denominator), Math.multiplyExact(denominator, other.numerator));
	}

	/**
	 * Returns the quotient of this fraction and the given number.
	 *
	 * @throws ArithmeticException if {@code value} is equal to {@code 0}
	 *
	 * @since 1.6
	 */
	public Fraction divide(int value) {
		Contract.check(value != 0, ArithmeticException.class, "Division by zero");

		if (value == 1) {
			return this;
		}

		return valueOf(numerator, Math.multiplyExact(value, denominator));
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

		if (other instanceof Fraction) {
			Fraction otherFraction = (Fraction) other;

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
	public int getDenominator() {
		return denominator;
	}

	/**
	 * Returns the numerator of this fraction.
	 *
	 * @since 1.6
	 */
	public int getNumerator() {
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
		return (denominator == 1);
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
	public Fraction multiply(Fraction other) {
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
	 * Returns the product of this fraction and the given number.
	 *
	 * @since 1.6
	 */
	public Fraction multiply(int value) {
		if (value == 0) {
			return ZERO;
		}
		if (value == 1) {
			return this;
		}

		return valueOf(Math.multiplyExact(value, numerator), denominator);
	}

	/**
	 * Returns the opposite of this fraction.
	 *
	 * @throws ArithmeticException if {@code numerator} is equal to {@code Integer.MIN_VALUE}
	 *
	 * @since 1.6
	 */
	public Fraction negate() {
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
	public static Fraction parse(String text) {
		Contract.checkArgument(text != null, "Text must not be null");

		int separatorIndex = text.indexOf('/');
		if (separatorIndex == -1) {
			throw new NumberFormatException("Invalid fraction: " + text);
		}

		try {
			int numerator = Integer.parseInt(text.substring(0, separatorIndex).trim());
			int denominator = Integer.parseInt(text.substring(separatorIndex + 1).trim());

			if (denominator == 0) {
				throw new NumberFormatException("Invalid fraction: " + text);
			}

			return new Fraction(numerator, denominator);
		} catch (IndexOutOfBoundsException | NumberFormatException e) {
			throw new NumberFormatException("Invalid fraction: " + text);
		}
	}

	/**
	 * Returns this fraction raised to the power of the given exponent.
	 *
	 * @since 1.6
	 */
	public Fraction pow(int exponent) {
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
	public Fraction reciprocal() {
		Contract.check(numerator != 0, ArithmeticException.class, "Division by zero");

		return valueOf(denominator, numerator);
	}

	/**
	 * Returns the integer value nearest to the value of this fraction.
	 *
	 * @throws IllegalArgumentException if {@code roundingMode} is {@code null}
	 *
	 * @since 1.6
	 */
	public int round(RoundingMode roundingMode) {
		Contract.checkArgument(roundingMode != null, "Rounding mode must not be null");

		return toBigDecimal(0, roundingMode).intValueExact();
	}

	/**
	 * Returns the signum of this value.
	 *
	 * @since 1.6
	 */
	public int signum() {
		return Integer.signum(numerator);
	}

	/**
	 * Returns the difference of this fraction and the given fraction.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.6
	 */
	public Fraction subtract(Fraction other) {
		Contract.checkArgument(other != null, "Other fraction must not be null");

		if (other.equals(ZERO)) {
			return this;
		}

		if (denominator == other.denominator) {
			return valueOf(Math.subtractExact(numerator, other.numerator), denominator);
		}

		int commonDenominator = MoreMath.lcm(denominator, other.denominator);
		int numeratorCoefficient = commonDenominator / denominator;
		int otherNumeratorCoefficient = commonDenominator / other.denominator;

		return valueOf(Math.subtractExact(Math.multiplyExact(numeratorCoefficient, numerator), Math.multiplyExact(otherNumeratorCoefficient, other.numerator)), commonDenominator);
	}

	/**
	 * Returns the difference of this fraction and the given number.
	 *
	 * @since 1.6
	 */
	public Fraction subtract(int value) {
		if (value == 0) {
			return this;
		}

		return valueOf(Math.subtractExact(numerator, Math.multiplyExact(value, denominator)), denominator);
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
	public static Fraction valueOf(int value) {
		return new Fraction(value, 1);
	}

	/**
	 * Returns a fraction with the given numerator and denominator.
	 *
	 * @throws IllegalArgumentException if {@code denominator} is {@code 0}
	 *
	 * @since 1.6
	 */
	public static Fraction valueOf(int numerator, int denominator) {
		return new Fraction(numerator, denominator);
	}


	private void normalizeAndReduce() {
		// Make denominator equal to 1 if numerator is equal to 0
		if (numerator == 0) {
			denominator = 1;

			return;
		}

		// Make denominator positive
		if (denominator < 0) {
			numerator = Math.negateExact(numerator);
			denominator = Math.negateExact(denominator);
		}

		// Reduce
		int reductor = MoreMath.gcd(numerator, denominator);
		if (reductor > 1) {
			numerator /= reductor;
			denominator /= reductor;
		}
	}

}
