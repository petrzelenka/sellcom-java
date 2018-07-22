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
package org.sellcom.core.internal.math;

import static java.math.BigDecimal.ONE;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.sellcom.core.Contract;

public class MathEvaluators {

	private MathEvaluators() {
		// Utility class, not to be instantiated
	}


	public static BigDecimal evaluateMaclaughin(BiFunction<BigDecimal, Integer, RationalBigDecimal> termFunction, BigDecimal x, int scale) {
		Contract.checkArgument(termFunction != null, "Term function must not be null");
		Contract.checkArgument(x != null, "X must not be null");

		BigDecimal acceptableError = ONE.movePointLeft(scale + 1);
		RationalBigDecimal value = RationalBigDecimal.ZERO;

		RationalBigDecimal currentTerm;
		int i = 0;
		do {
			currentTerm = termFunction.apply(x, i);
			i += 1;
			value = value.add(currentTerm);
		} while (currentTerm.toBigDecimal(scale + 3).abs().compareTo(acceptableError) > 0);

		return value.toBigDecimal(scale);
	}

	public static BigDecimal evaluateNewtonRaphson(Function<BigDecimal, BigDecimal> termFunction, BigDecimal x, BigDecimal initialGuess, int scale) {
		Contract.checkArgument(termFunction != null, "Term function must not be null");
		Contract.checkArgument(x != null, "X must not be null");
		Contract.checkArgument(initialGuess != null, "Initial guess must not be null");

		BigDecimal acceptableError = ONE.movePointLeft(scale + 1);
		BigDecimal value = initialGuess;

		BigDecimal currentTerm;
		do {
			currentTerm = termFunction.apply(value);
			value = value.subtract(currentTerm);
		} while (currentTerm.abs().compareTo(acceptableError) > 0);

		return value.setScale(scale, RoundingMode.HALF_UP);
	}

}
