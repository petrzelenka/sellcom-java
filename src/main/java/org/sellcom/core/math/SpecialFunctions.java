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

import org.sellcom.core.Contract;
import org.sellcom.core.internal.math.ExpMclaughlinEvaluator;
import org.sellcom.core.internal.math.MathEvaluators;

/**
 * Particular mathematical functions.
 *
 * @since 1.6
 */
public class SpecialFunctions {

	private SpecialFunctions() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the smallest (closest to negative infinity) value that is greater than or equal to the dual (base {@code 2}) logarithm of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not positive
	 *
	 * @since 1.6
	 */
	public static int ceilLd(int x) {
		Contract.checkArgument(x > 0, "X must be positive: {0}", x);

		return Integer.SIZE - Integer.numberOfLeadingZeros(x - 1);
	}

	/**
	 * Returns the smallest (closest to negative infinity) value that is greater than or equal to the dual (base {@code 2}) logarithm of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not positive
	 *
	 * @since 1.6
	 */
	public static long ceilLd(long x) {
		Contract.checkArgument(x > 0L, "X must be positive: {0}", x);

		return Long.SIZE - Long.numberOfLeadingZeros(x - 1L);
	}

	/**
	 * Returns the Euler's number <em>e</em> raised to the power of the given number.
	 *
	 * @throws ArithmeticException if {@code x} is &gt; {@code Long.MAX_VALUE}
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.6
	 */
	public static BigDecimal exp(BigDecimal x, int scale) {
		Contract.checkArgument(x != null, "X must not be null");

		if (x.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ONE;
		}
		if (x.compareTo(BigDecimal.ZERO) < 0) {
			return BigDecimal.ONE.divide(exp(x.negate(), scale), scale, RoundingMode.HALF_UP);
		}

		if (x.compareTo(BigDecimal.ONE) < 0) {
			// Use Maclaughlin series

			return MathEvaluators.evaluateMaclaughin(
					new ExpMclaughlinEvaluator(),
					x,
					scale);
		} else {
			// Decompose the exponent into its integral and fractional parts and use the following formula:
			//
			//     e^x = (e^(1 + f/i))^i
			//
			// For more details, see "Chapter 12: Big Numbers" of the following book:
			//
			//     Ronald Mak. Java Number Cruncher: The Java Programmer's Guide to Numerical Computing.
			//     Prentice Hall, Upper Saddle River, NJ (USA), 2002.

			int internalScale = scale + 6;

			BigDecimal fractionalPart = MoreMath.fractionalPart(x);
			BigDecimal integralPart = MoreMath.integralPart(x);
			BigDecimal innerExponent = BigDecimal.ONE.add(fractionalPart.divide(integralPart, internalScale, RoundingMode.HALF_UP));

			BigDecimal result = MoreMath.pow(
					MathEvaluators.evaluateMaclaughin(
							new ExpMclaughlinEvaluator(),
							innerExponent,
							internalScale),
					integralPart.longValueExact(),
					internalScale);

			return result.setScale(scale, RoundingMode.HALF_UP);
		}
	}

	/**
	 * Returns the largest (closest to positive infinity) value that is smaller than or equal to the dual (base {@code 2}) logarithm of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not positive
	 *
	 * @since 1.6
	 */
	public static int floorLd(int x) {
		Contract.checkArgument(x > 0, "X must be positive: {0}", x);

		return Integer.SIZE - Integer.numberOfLeadingZeros(x) - 1;
	}

	/**
	 * Returns the largest (closest to positive infinity) value that is smaller than or equal to the dual (base {@code 2}) logarithm of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not positive
	 *
	 * @since 1.6
	 */
	public static long floorLd(long x) {
		Contract.checkArgument(x > 0L, "X must be positive: {0}", x);

		return Long.SIZE - Long.numberOfLeadingZeros(x) - 1L;
	}

	/**
	 * Returns the dual (base {@code 2}) logarithm of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.6
	 */
	public static double ld(double x) {
		Contract.checkArgument(Double.isFinite(x), "X must be finite: {0}", x);

		return Math.log(x) / Math.log(2.0);
	}

	/**
	 * Returns the natural (base {@code e}) logarithm of the given number.
	 *
	 * @throws ArithmeticException if {@code x} is &le; 0
	 * @throws IllegalArgumentException if {@code x} is {@code null}
	 *
	 * @since 1.6
	 */
	public static BigDecimal ln(BigDecimal x, int scale) {
		Contract.checkArgument(x != null, "X must not be null");
		Contract.check(x.compareTo(BigDecimal.ZERO) > 0, ArithmeticException.class, "X must be positive: {0}", x);

		if (x.compareTo(BigDecimal.valueOf(1000)) < 0) {
			// Use Newton-Raphson method

			int internalScale = scale + 3;

			return MathEvaluators.evaluateNewtonRaphson(
					y -> {
						BigDecimal eToY = SpecialFunctions.exp(y, internalScale);

						return eToY.subtract(x).divide(eToY, internalScale, RoundingMode.HALF_UP);
					}, // (e^y - x) / e^y
					x,
					x, // Initial guess
					scale);
		} else {
			// Calculate the magnitude of the argument and use the following formula:
			//
			//     ln(x) = m * ln(x^(1 / m))
			//
			// For more details, see "Chapter 12: Big Numbers" of the following book:
			//
			//     Ronald Mak. Java Number Cruncher: The Java Programmer's Guide to Numerical Computing.
			//     Prentice Hall, Upper Saddle River, NJ (USA), 2002.

			int internalScale = scale + 3;

			int magnitudeOfX = MoreMath.magnitude(x);
			BigDecimal result = BigDecimal.valueOf(magnitudeOfX).multiply(ln(MoreMath.root(x, magnitudeOfX, internalScale), internalScale));

			return result.setScale(scale, RoundingMode.HALF_UP);
		}
	}

	/**
	 * Returns the natural (base {@code e}) logarithm of the given number.
	 *
	 * @throws IllegalArgumentException if {@code x} is not finite
	 *
	 * @since 1.6
	 */
	public static double ln(double x) {
		Contract.checkArgument(Double.isFinite(x), "X must be finite: {0}", x);

		return Math.log(x);
	}

}
