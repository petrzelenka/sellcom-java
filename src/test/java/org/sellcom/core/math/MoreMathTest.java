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
import static org.junit.Assert.assertThat;
import static org.sellcom.core.math.MoreMath.ceilToMultiple;
import static org.sellcom.core.math.MoreMath.floorToMultiple;
import static org.sellcom.core.math.MoreMath.gcd;
import static org.sellcom.core.math.MoreMath.lcm;
import static org.sellcom.core.math.MoreMath.roundToMultiple;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.sellcom.core.internal.test.TestUtils;
import org.sellcom.core.internal.util.RandomUtils;

public class MoreMathTest {

	@RunWith(Parameterized.class)
	public static class ParameterizedGcdAndLcmTest_bigInteger {

		private final BigInteger expectedGcd;

		private final BigInteger expectedLcm;

		private final BigInteger x;

		private final BigInteger y;


		public ParameterizedGcdAndLcmTest_bigInteger(ParameterValues values) {
			x = values.getX();
			y = values.getY();
			expectedGcd = values.getExpectedGcd();
			expectedLcm = values.getExpectedLcm();
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					new ParameterValues(0, 0, 0, 0),
					new ParameterValues(9, 28, 1, 252),
					new ParameterValues(18, 0, 18, 0),
					new ParameterValues(27, 12, 3, 108),
					new ParameterValues(42, 56, 14, 168),
					new ParameterValues(48, 180, 12, 720),
					new ParameterValues(54, 24, 6, 216),
			});
		}

		@Test
		public void test() {
			assertThat(gcd(x, y), is(equalTo(expectedGcd)));
			assertThat(gcd(x.negate(), y), is(equalTo(expectedGcd)));
			assertThat(gcd(x, y.negate()), is(equalTo(expectedGcd)));
			assertThat(gcd(x.negate(), y.negate()), is(equalTo(expectedGcd)));
			assertThat(gcd(y, x), is(equalTo(expectedGcd)));
			assertThat(gcd(y.negate(), x), is(equalTo(expectedGcd)));
			assertThat(gcd(y, x.negate()), is(equalTo(expectedGcd)));
			assertThat(gcd(y.negate(), x.negate()), is(equalTo(expectedGcd)));

			assertThat(lcm(x, y), is(equalTo(expectedLcm)));
			assertThat(lcm(x.negate(), y), is(equalTo(expectedLcm)));
			assertThat(lcm(x, y.negate()), is(equalTo(expectedLcm)));
			assertThat(lcm(x.negate(), y.negate()), is(equalTo(expectedLcm)));
			assertThat(lcm(y, x), is(equalTo(expectedLcm)));
			assertThat(lcm(y.negate(), x), is(equalTo(expectedLcm)));
			assertThat(lcm(y, x.negate()), is(equalTo(expectedLcm)));
			assertThat(lcm(y.negate(), x.negate()), is(equalTo(expectedLcm)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final BigInteger expectedGcd;

			private final BigInteger expectedLcm;

			private final BigInteger x;

			private final BigInteger y;


			public ParameterValues(int x, int y, int expectedGcd, int expectedLcm) {
				this.x = BigInteger.valueOf(x);
				this.y = BigInteger.valueOf(y);
				this.expectedGcd = BigInteger.valueOf(expectedGcd);
				this.expectedLcm = BigInteger.valueOf(expectedLcm);
			}


			public BigInteger getExpectedGcd() {
				return expectedGcd;
			}

			public BigInteger getExpectedLcm() {
				return expectedLcm;
			}

			public BigInteger getX() {
				return x;
			}

			public BigInteger getY() {
				return y;
			}

		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class RandomCeilToMultipleTest {

		private static final int REPEAT_COUNT = 100_000;

		private static final Random random = new Random();


		@Test
		public void test_bigDecimal() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				BigInteger upperLimit = BigInteger.valueOf((long) Math.sqrt(Long.MAX_VALUE));
				BigDecimal base = new BigDecimal(RandomUtils.nextBigInteger(random, BigInteger.valueOf(2L), upperLimit));
				BigDecimal multiple = new BigDecimal(RandomUtils.nextBigInteger(random, BigInteger.ONE, upperLimit));
				BigDecimal expectedValue = base.multiply(multiple);

				assertThat(ceilToMultiple(expectedValue.subtract(BigDecimal.ONE), base), is(equalTo(expectedValue)));
				assertThat(ceilToMultiple(expectedValue, base), is(equalTo(expectedValue)));
			});
		}

		@Test
		public void test_bigInteger() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				BigInteger upperLimit = BigInteger.valueOf((long) Math.sqrt(Long.MAX_VALUE));
				BigInteger base = RandomUtils.nextBigInteger(random, BigInteger.valueOf(2L), upperLimit);
				BigInteger multiple = RandomUtils.nextBigInteger(random, BigInteger.ONE, upperLimit);
				BigInteger expectedValue = base.multiply(multiple);

				assertThat(ceilToMultiple(expectedValue.subtract(BigInteger.ONE), base), is(equalTo(expectedValue)));
				assertThat(ceilToMultiple(expectedValue, base), is(equalTo(expectedValue)));
			});
		}

		@Test
		public void test_int() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				int upperLimit = (int) Math.sqrt(Integer.MAX_VALUE);
				int base = RandomUtils.nextInt(random, 2, upperLimit);
				int multiple = RandomUtils.nextInt(random, 1, upperLimit);
				int expectedValue = base * multiple;

				assertThat(ceilToMultiple(expectedValue - 1, base), is(equalTo(expectedValue)));
				assertThat(ceilToMultiple(expectedValue, base), is(equalTo(expectedValue)));
			});
		}

		@Test
		public void test_long() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				long upperLimit = (long) Math.sqrt(Long.MAX_VALUE);
				long base = RandomUtils.nextLong(random, 2L, upperLimit);
				long multiple = RandomUtils.nextLong(random, 1L, upperLimit);
				long expectedValue = base * multiple;

				assertThat(ceilToMultiple(expectedValue - 1, base), is(equalTo(expectedValue)));
				assertThat(ceilToMultiple(expectedValue, base), is(equalTo(expectedValue)));
			});
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class RandomFloorToMultipleTest {

		private static final int REPEAT_COUNT = 100_000;

		private static final Random random = new Random();


		@Test
		public void test_bigDecimal() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				BigInteger upperLimit = BigInteger.valueOf((long) Math.sqrt(Long.MAX_VALUE));
				BigDecimal base = new BigDecimal(RandomUtils.nextBigInteger(random, BigInteger.valueOf(2L), upperLimit));
				BigDecimal multiple = new BigDecimal(RandomUtils.nextBigInteger(random, BigInteger.ONE, upperLimit));
				BigDecimal expectedValue = base.multiply(multiple);

				assertThat(floorToMultiple(expectedValue, base), is(equalTo(expectedValue)));
				assertThat(floorToMultiple(expectedValue.add(BigDecimal.ONE), base), is(equalTo(expectedValue)));
			});
		}

		@Test
		public void test_bigInteger() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				BigInteger upperLimit = BigInteger.valueOf((long) Math.sqrt(Long.MAX_VALUE));
				BigInteger base = RandomUtils.nextBigInteger(random, BigInteger.valueOf(2L), upperLimit);
				BigInteger multiple = RandomUtils.nextBigInteger(random, BigInteger.ONE, upperLimit);
				BigInteger expectedValue = base.multiply(multiple);

				assertThat(floorToMultiple(expectedValue, base), is(equalTo(expectedValue)));
				assertThat(floorToMultiple(expectedValue.add(BigInteger.ONE), base), is(equalTo(expectedValue)));
			});
		}

		@Test
		public void test_int() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				int upperLimit = (int) Math.sqrt(Integer.MAX_VALUE);
				int base = RandomUtils.nextInt(random, 2, upperLimit);
				int multiple = RandomUtils.nextInt(random, 1, upperLimit);
				int expectedValue = base * multiple;

				assertThat(floorToMultiple(expectedValue, base), is(equalTo(expectedValue)));
				assertThat(floorToMultiple(expectedValue + 1, base), is(equalTo(expectedValue)));
			});
		}

		@Test
		public void test_long() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				long upperLimit = (long) Math.sqrt(Long.MAX_VALUE);
				long base = RandomUtils.nextLong(random, 2L, upperLimit);
				long multiple = RandomUtils.nextLong(random, 1L, upperLimit);
				long expectedValue = base * multiple;

				assertThat(floorToMultiple(expectedValue, base), is(equalTo(expectedValue)));
				assertThat(floorToMultiple(expectedValue + 1, base), is(equalTo(expectedValue)));
			});
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class RandomGcdAndLcmTest {

		private static final int REPEAT_COUNT = 100_000;

		private static final Random random = new Random();


		@Test
		public void test_int() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				int x = RandomUtils.nextShort(random, (short) 0, Short.MAX_VALUE);
				int y = RandomUtils.nextShort(random, (short) 0, Short.MAX_VALUE);
				int expectedGcd = MoreMath.gcd(BigInteger.valueOf(x), BigInteger.valueOf(y)).intValueExact();
				int expectedLcm = MoreMath.lcm(BigInteger.valueOf(x), BigInteger.valueOf(y)).intValueExact();

				assertThat(gcd(x, y), is(equalTo(expectedGcd)));
				assertThat(gcd(-x, y), is(equalTo(expectedGcd)));
				assertThat(gcd(x, -y), is(equalTo(expectedGcd)));
				assertThat(gcd(-x, -y), is(equalTo(expectedGcd)));
				assertThat(gcd(y, x), is(equalTo(expectedGcd)));
				assertThat(gcd(-y, x), is(equalTo(expectedGcd)));
				assertThat(gcd(y, -x), is(equalTo(expectedGcd)));
				assertThat(gcd(-y, -x), is(equalTo(expectedGcd)));

				assertThat(lcm(x, y), is(equalTo(expectedLcm)));
				assertThat(lcm(-x, y), is(equalTo(expectedLcm)));
				assertThat(lcm(x, -y), is(equalTo(expectedLcm)));
				assertThat(lcm(-x, -y), is(equalTo(expectedLcm)));
				assertThat(lcm(y, x), is(equalTo(expectedLcm)));
				assertThat(lcm(-y, x), is(equalTo(expectedLcm)));
				assertThat(lcm(y, -x), is(equalTo(expectedLcm)));
				assertThat(lcm(-y, -x), is(equalTo(expectedLcm)));
			});
		}

		@Test
		public void test_long() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				long x = RandomUtils.nextInt(random, 0, Integer.MAX_VALUE);
				long y = RandomUtils.nextInt(random, 0, Integer.MAX_VALUE);
				long expectedGcd = MoreMath.gcd(BigInteger.valueOf(x), BigInteger.valueOf(y)).longValueExact();
				long expectedLcm = MoreMath.lcm(BigInteger.valueOf(x), BigInteger.valueOf(y)).longValueExact();

				assertThat(gcd(x, y), is(equalTo(expectedGcd)));
				assertThat(gcd(-x, y), is(equalTo(expectedGcd)));
				assertThat(gcd(x, -y), is(equalTo(expectedGcd)));
				assertThat(gcd(-x, -y), is(equalTo(expectedGcd)));
				assertThat(gcd(y, x), is(equalTo(expectedGcd)));
				assertThat(gcd(-y, x), is(equalTo(expectedGcd)));
				assertThat(gcd(y, -x), is(equalTo(expectedGcd)));
				assertThat(gcd(-y, -x), is(equalTo(expectedGcd)));

				assertThat(lcm(x, y), is(equalTo(expectedLcm)));
				assertThat(lcm(-x, y), is(equalTo(expectedLcm)));
				assertThat(lcm(x, -y), is(equalTo(expectedLcm)));
				assertThat(lcm(-x, -y), is(equalTo(expectedLcm)));
				assertThat(lcm(y, x), is(equalTo(expectedLcm)));
				assertThat(lcm(-y, x), is(equalTo(expectedLcm)));
				assertThat(lcm(y, -x), is(equalTo(expectedLcm)));
				assertThat(lcm(-y, -x), is(equalTo(expectedLcm)));
			});
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class RandomRoundToMultipleTest {

		private static final int REPEAT_COUNT = 100_000;

		private static final Random random = new Random();


		@Test
		public void test_bigDecimal() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				BigInteger upperLimit = BigInteger.valueOf((long) Math.sqrt(Long.MAX_VALUE));
				BigDecimal base = new BigDecimal(RandomUtils.nextBigInteger(random, BigInteger.valueOf(2L), upperLimit));
				BigDecimal multiple = new BigDecimal(RandomUtils.nextBigInteger(random, BigInteger.ONE, upperLimit));
				BigDecimal expectedValue = base.multiply(multiple);

				assertThat(roundToMultiple(expectedValue.subtract(BigDecimal.ONE), base), is(equalTo(expectedValue)));
				assertThat(roundToMultiple(expectedValue, base), is(equalTo(expectedValue)));
				assertThat(roundToMultiple(expectedValue.add(BigDecimal.ONE), base), is(equalTo(expectedValue)));
			});
		}

		@Test
		public void test_bigInteger() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				BigInteger upperLimit = BigInteger.valueOf((long) Math.sqrt(Long.MAX_VALUE));
				BigInteger base = RandomUtils.nextBigInteger(random, BigInteger.valueOf(3L), upperLimit);
				BigInteger multiple = RandomUtils.nextBigInteger(random, BigInteger.ONE, upperLimit);
				BigInteger expectedValue = base.multiply(multiple);

				assertThat(roundToMultiple(expectedValue.subtract(BigInteger.ONE), base), is(equalTo(expectedValue)));
				assertThat(roundToMultiple(expectedValue, base), is(equalTo(expectedValue)));
				assertThat(roundToMultiple(expectedValue.add(BigInteger.ONE), base), is(equalTo(expectedValue)));
			});
		}

		@Test
		public void test_int() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				int upperLimit = (int) Math.sqrt(Integer.MAX_VALUE);
				int base = RandomUtils.nextInt(random, 3, upperLimit);
				int multiple = RandomUtils.nextInt(random, 1, upperLimit);
				int expectedValue = base * multiple;

				assertThat(roundToMultiple(expectedValue - 1, base), is(equalTo(expectedValue)));
				assertThat(roundToMultiple(expectedValue, base), is(equalTo(expectedValue)));
				assertThat(roundToMultiple(expectedValue + 1, base), is(equalTo(expectedValue)));
			});
		}

		@Test
		public void test_long() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				long upperLimit = (long) Math.sqrt(Long.MAX_VALUE);
				long base = RandomUtils.nextLong(random, 3L, upperLimit);
				long multiple = RandomUtils.nextLong(random, 1L, upperLimit);
				long expectedValue = base * multiple;

				assertThat(roundToMultiple(expectedValue - 1, base), is(equalTo(expectedValue)));
				assertThat(roundToMultiple(expectedValue, base), is(equalTo(expectedValue)));
				assertThat(roundToMultiple(expectedValue + 1, base), is(equalTo(expectedValue)));
			});
		}

	}

}
