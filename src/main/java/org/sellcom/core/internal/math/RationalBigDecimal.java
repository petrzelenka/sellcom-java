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

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RationalBigDecimal {

	public static final RationalBigDecimal ONE = of(BigDecimal.ONE);

	public static final RationalBigDecimal ZERO = of(BigDecimal.ZERO);

	private BigDecimal denominator;

	private BigDecimal numerator;


	private RationalBigDecimal(BigDecimal numerator, BigDecimal denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}


	public RationalBigDecimal add(RationalBigDecimal other) {
		return (denominator.equals(other.denominator)) ? valueOf(numerator.add(other.numerator), denominator) : valueOf(numerator.multiply(other.denominator).add(other.numerator.multiply(denominator)), denominator.multiply(other.denominator));
	}

	public RationalBigDecimal multiply(RationalBigDecimal other) {
		return valueOf(numerator.multiply(other.numerator), denominator.multiply(other.denominator));
	}

	public static RationalBigDecimal of(BigDecimal value) {
		return new RationalBigDecimal(value, BigDecimal.ONE);
	}

	public BigDecimal toBigDecimal(int scale) {
		return numerator.divide(denominator, scale, RoundingMode.HALF_UP);
	}

	public static RationalBigDecimal valueOf(BigDecimal numerator, BigDecimal denominator) {
		return new RationalBigDecimal(numerator, denominator);
	}

}
