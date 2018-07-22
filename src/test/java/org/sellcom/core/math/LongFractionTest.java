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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.math.LongFraction.ONE;
import static org.sellcom.core.math.LongFraction.ONE_HALF;
import static org.sellcom.core.math.LongFraction.ONE_QUARTER;
import static org.sellcom.core.math.LongFraction.ONE_THIRD;
import static org.sellcom.core.math.LongFraction.THREE_QUARTERS;
import static org.sellcom.core.math.LongFraction.TWO_THIRDS;
import static org.sellcom.core.math.LongFraction.ZERO;
import static org.sellcom.core.math.LongFraction.parse;
import static org.sellcom.core.math.LongFraction.valueOf;

import java.math.RoundingMode;

import org.junit.Test;

public class LongFractionTest {

	@Test
	public void testAdd_fraction() {
		assertThat(ONE_THIRD.add(ONE), is(equalTo(valueOf(4L, 3L))));
		assertThat(ONE_THIRD.add(ONE_HALF), is(equalTo(valueOf(5L, 6L))));
		assertThat(ONE_THIRD.add(ONE_QUARTER), is(equalTo(valueOf(7L, 12L))));
		assertThat(ONE_THIRD.add(ONE_THIRD), is(equalTo(TWO_THIRDS)));
		assertThat(ONE_THIRD.add(THREE_QUARTERS), is(equalTo(valueOf(13L, 12L))));
		assertThat(ONE_THIRD.add(TWO_THIRDS), is(equalTo(ONE)));
		assertThat(ONE_THIRD.add(ZERO), is(equalTo(ONE_THIRD)));
	}

	@Test
	public void testAdd_integer() {
		assertThat(ONE_THIRD.add(1L), is(equalTo(valueOf(4L, 3L))));
	}

	@Test
	public void testCompareTo() {
		assertThat(ONE_THIRD.compareTo(ONE), is(lessThan(0)));
		assertThat(ONE_THIRD.compareTo(ONE_HALF), is(lessThan(0)));
		assertThat(ONE_THIRD.compareTo(ONE_QUARTER), is(greaterThan(0)));
		assertThat(ONE_THIRD.compareTo(ONE_THIRD), is(equalTo(0)));
		assertThat(ONE_THIRD.compareTo(THREE_QUARTERS), is(lessThan(0)));
		assertThat(ONE_THIRD.compareTo(TWO_THIRDS), is(lessThan(0)));
		assertThat(ONE_THIRD.compareTo(ZERO), is(greaterThan(0)));
	}

	@Test
	public void testDivide_fraction() {
		assertThat(ONE_THIRD.divide(ONE), is(equalTo(ONE_THIRD)));
		assertThat(ONE_THIRD.divide(ONE_HALF), is(equalTo(valueOf(2L, 3L))));
		assertThat(ONE_THIRD.divide(ONE_QUARTER), is(equalTo(valueOf(4L, 3L))));
		assertThat(ONE_THIRD.divide(ONE_THIRD), is(equalTo(ONE)));
		assertThat(ONE_THIRD.divide(THREE_QUARTERS), is(equalTo(valueOf(4L, 9L))));
		assertThat(ONE_THIRD.divide(TWO_THIRDS), is(equalTo(ONE_HALF)));
	}

	@Test
	public void testDivide_integer() {
		assertThat(TWO_THIRDS.divide(2L), is(equalTo(ONE_THIRD)));
	}

	@Test
	public void testMultiply_fraction() {
		assertThat(ONE_THIRD.multiply(ONE), is(equalTo(ONE_THIRD)));
		assertThat(ONE_THIRD.multiply(ONE_HALF), is(equalTo(valueOf(1L, 6L))));
		assertThat(ONE_THIRD.multiply(ONE_QUARTER), is(equalTo(valueOf(1L, 12L))));
		assertThat(ONE_THIRD.multiply(ONE_THIRD), is(equalTo(valueOf(1L, 9L))));
		assertThat(ONE_THIRD.multiply(THREE_QUARTERS), is(equalTo(ONE_QUARTER)));
		assertThat(ONE_THIRD.multiply(TWO_THIRDS), is(equalTo(valueOf(2L, 9L))));
		assertThat(ONE_THIRD.multiply(ZERO), is(equalTo(ZERO)));
	}

	@Test
	public void testMultiply_integer() {
		assertThat(ONE_THIRD.multiply(2L), is(equalTo(TWO_THIRDS)));
	}

	@Test
	public void testParse() {
		assertThat(parse("4/7"), is(equalTo(valueOf(4L, 7L))));
	}

	@Test
	public void testPow() {
		assertThat(ONE_HALF.pow(3), is(equalTo(valueOf(1L, 8L))));
		assertThat(TWO_THIRDS.pow(2), is(equalTo(valueOf(4L, 9L))));
	}

	@Test
	public void testRound() {
		assertThat(ONE.round(RoundingMode.HALF_UP), is(equalTo(1L)));
		assertThat(ONE_HALF.round(RoundingMode.HALF_UP), is(equalTo(1L)));
		assertThat(ONE_QUARTER.round(RoundingMode.HALF_UP), is(equalTo(0L)));
		assertThat(ONE_THIRD.round(RoundingMode.HALF_UP), is(equalTo(0L)));
		assertThat(THREE_QUARTERS.round(RoundingMode.HALF_UP), is(equalTo(1L)));
		assertThat(TWO_THIRDS.round(RoundingMode.HALF_UP), is(equalTo(1L)));
		assertThat(ZERO.round(RoundingMode.HALF_UP), is(equalTo(0L)));
	}

	@Test
	public void testSubtract_fraction() {
		assertThat(ONE_THIRD.subtract(ONE), is(equalTo(valueOf(-2L, 3L))));
		assertThat(ONE_THIRD.subtract(ONE_HALF), is(equalTo(valueOf(-1L, 6L))));
		assertThat(ONE_THIRD.subtract(ONE_QUARTER), is(equalTo(valueOf(1L, 12L))));
		assertThat(ONE_THIRD.subtract(ONE_THIRD), is(equalTo(ZERO)));
		assertThat(ONE_THIRD.subtract(THREE_QUARTERS), is(equalTo(valueOf(-5L, 12L))));
		assertThat(ONE_THIRD.subtract(TWO_THIRDS), is(equalTo(valueOf(-1L, 3L))));
		assertThat(ONE_THIRD.subtract(ZERO), is(equalTo(ONE_THIRD)));
	}

	@Test
	public void testSubtract_integer() {
		assertThat(valueOf(4L, 3L).subtract(1L), is(equalTo(ONE_THIRD)));
	}

}
