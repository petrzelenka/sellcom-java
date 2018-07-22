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

import static java.math.BigDecimal.ONE;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.math.SpecialFunctions.ceilLd;
import static org.sellcom.core.math.SpecialFunctions.exp;
import static org.sellcom.core.math.SpecialFunctions.floorLd;
import static org.sellcom.core.math.SpecialFunctions.ln;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

public class SpecialFunctionsTest {

	private static final BigDecimal TWO = BigDecimal.valueOf(2L);

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@Test
	public void testCeilLd_int() {
		for (int power = 2, exponent = 1; power > 0; ) {
			assertThat(ceilLd(power), is(equalTo(exponent)));
			assertThat(ceilLd(power + 1), is(equalTo(exponent + 1)));

			power *= 2;
			exponent += 1;
		}
	}

	@Test
	public void testCeilLd_long() {
		for (long power = 2L, exponent = 1L; power > 0L; ) {
			assertThat(ceilLd(power), is(equalTo(exponent)));
			assertThat(ceilLd(power + 1), is(equalTo(exponent + 1)));

			power *= 2;
			exponent += 1;
		}
	}

	@Test
	public void testFloorLd_int() {
		for (int power = 2, exponent = 1; power > 0; ) {
			assertThat(floorLd(power), is(equalTo(exponent)));
			assertThat(floorLd(power + 1), is(equalTo(exponent)));

			power *= 2;
			exponent += 1;
		}
	}

	@Test
	public void testFloorLd_long() {
		for (long power = 2L, exponent = 1L; power > 0L; ) {
			assertThat(floorLd(power), is(equalTo(exponent)));
			assertThat(floorLd(power + 1), is(equalTo(exponent)));

			power *= 2;
			exponent += 1;
		}
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class ParameterizedExpTest {

		private final BigDecimal expectedValue;

		private final int scale;

		private final BigDecimal x;


		public ParameterizedExpTest(ParameterValues values) {
			expectedValue = values.getExpectedValue();
			scale = values.getScale();
			x = values.getX();
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					// Precision tests
					new ParameterValues(ONE,  0, new BigDecimal("3")),
					new ParameterValues(ONE,  1, new BigDecimal("2.7")),
					new ParameterValues(ONE,  2, new BigDecimal("2.72")),
					new ParameterValues(ONE,  3, new BigDecimal("2.718")),
					new ParameterValues(ONE,  4, new BigDecimal("2.7183")),
					new ParameterValues(ONE,  5, new BigDecimal("2.71828")),
					new ParameterValues(ONE,  6, new BigDecimal("2.718282")),
					new ParameterValues(ONE,  7, new BigDecimal("2.7182818")),
					new ParameterValues(ONE,  8, new BigDecimal("2.71828183")),
					new ParameterValues(ONE,  9, new BigDecimal("2.718281828")),
					new ParameterValues(ONE, 10, new BigDecimal("2.7182818285")),
					new ParameterValues(ONE, 11, new BigDecimal("2.71828182846")),
					new ParameterValues(ONE, 12, new BigDecimal("2.718281828459")),
					new ParameterValues(ONE, 13, new BigDecimal("2.7182818284590")),
					new ParameterValues(ONE, 14, new BigDecimal("2.71828182845905")),
					new ParameterValues(ONE, 15, new BigDecimal("2.718281828459045")),
					new ParameterValues(ONE, 16, new BigDecimal("2.7182818284590452")),
					new ParameterValues(ONE, 17, new BigDecimal("2.71828182845904524")),
					new ParameterValues(ONE, 18, new BigDecimal("2.718281828459045235")),
					new ParameterValues(ONE, 19, new BigDecimal("2.7182818284590452354")),
					new ParameterValues(ONE, 20, new BigDecimal("2.71828182845904523536")),
					new ParameterValues(ONE, 21, new BigDecimal("2.718281828459045235360")),
					new ParameterValues(ONE, 22, new BigDecimal("2.7182818284590452353603")),
					new ParameterValues(ONE, 23, new BigDecimal("2.71828182845904523536029")),
					new ParameterValues(ONE, 24, new BigDecimal("2.718281828459045235360287")),
					new ParameterValues(ONE, 25, new BigDecimal("2.7182818284590452353602875")),
					new ParameterValues(ONE, 26, new BigDecimal("2.71828182845904523536028747")),
					new ParameterValues(ONE, 27, new BigDecimal("2.718281828459045235360287471")),
					new ParameterValues(ONE, 28, new BigDecimal("2.7182818284590452353602874714")),
					new ParameterValues(ONE, 29, new BigDecimal("2.71828182845904523536028747135")),
					new ParameterValues(ONE, 30, new BigDecimal("2.718281828459045235360287471353")),
					new ParameterValues(ONE, 31, new BigDecimal("2.7182818284590452353602874713527")),
					new ParameterValues(ONE, 32, new BigDecimal("2.71828182845904523536028747135266")),
					new ParameterValues(ONE, 33, new BigDecimal("2.718281828459045235360287471352662")),
					new ParameterValues(ONE, 34, new BigDecimal("2.7182818284590452353602874713526625")),
					new ParameterValues(ONE, 35, new BigDecimal("2.71828182845904523536028747135266250")),
					new ParameterValues(ONE, 36, new BigDecimal("2.718281828459045235360287471352662498")),
					new ParameterValues(ONE, 37, new BigDecimal("2.7182818284590452353602874713526624978")),
					new ParameterValues(ONE, 38, new BigDecimal("2.71828182845904523536028747135266249776")),
					new ParameterValues(ONE, 39, new BigDecimal("2.718281828459045235360287471352662497757")),
					new ParameterValues(ONE, 40, new BigDecimal("2.7182818284590452353602874713526624977572")),
					new ParameterValues(ONE, 41, new BigDecimal("2.71828182845904523536028747135266249775725")),
					new ParameterValues(ONE, 42, new BigDecimal("2.718281828459045235360287471352662497757247")),
					new ParameterValues(ONE, 43, new BigDecimal("2.7182818284590452353602874713526624977572471")),
					new ParameterValues(ONE, 44, new BigDecimal("2.71828182845904523536028747135266249775724709")),
					new ParameterValues(ONE, 45, new BigDecimal("2.718281828459045235360287471352662497757247094")),
					new ParameterValues(ONE, 46, new BigDecimal("2.7182818284590452353602874713526624977572470937")),
					new ParameterValues(ONE, 47, new BigDecimal("2.71828182845904523536028747135266249775724709370")),
					new ParameterValues(ONE, 48, new BigDecimal("2.718281828459045235360287471352662497757247093700")),
					new ParameterValues(ONE, 48, new BigDecimal("2.7182818284590452353602874713526624977572470937000")),
					new ParameterValues(ONE, 50, new BigDecimal("2.71828182845904523536028747135266249775724709369996")),
					new ParameterValues(ONE, 51, new BigDecimal("2.718281828459045235360287471352662497757247093699960")),
					new ParameterValues(ONE, 52, new BigDecimal("2.7182818284590452353602874713526624977572470936999596")),
					new ParameterValues(ONE, 53, new BigDecimal("2.71828182845904523536028747135266249775724709369995957")),
					new ParameterValues(ONE, 54, new BigDecimal("2.718281828459045235360287471352662497757247093699959575")),
					new ParameterValues(ONE, 55, new BigDecimal("2.7182818284590452353602874713526624977572470936999595750")),
					new ParameterValues(ONE, 56, new BigDecimal("2.71828182845904523536028747135266249775724709369995957497")),
					new ParameterValues(ONE, 57, new BigDecimal("2.718281828459045235360287471352662497757247093699959574967")),
					new ParameterValues(ONE, 58, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749670")),
					new ParameterValues(ONE, 59, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496697")),
					new ParameterValues(ONE, 60, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966968")),
					new ParameterValues(ONE, 61, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676")),
					new ParameterValues(ONE, 62, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696763")),
					new ParameterValues(ONE, 63, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967628")),
					new ParameterValues(ONE, 64, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277")),
					new ParameterValues(ONE, 65, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772")),
					new ParameterValues(ONE, 66, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724")),
					new ParameterValues(ONE, 67, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277241")),
					new ParameterValues(ONE, 68, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772408")),
					new ParameterValues(ONE, 69, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724077")),
					new ParameterValues(ONE, 70, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766")),
					new ParameterValues(ONE, 71, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663")),
					new ParameterValues(ONE, 72, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630")),
					new ParameterValues(ONE, 73, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766304")),
					new ParameterValues(ONE, 74, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035")),
					new ParameterValues(ONE, 75, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630354")),
					new ParameterValues(ONE, 76, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535")),
					new ParameterValues(ONE, 77, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035355")),
					new ParameterValues(ONE, 78, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353548")),
					new ParameterValues(ONE, 79, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535476")),
					new ParameterValues(ONE, 80, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759")),
					new ParameterValues(ONE, 81, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353547595")),
					new ParameterValues(ONE, 82, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475946")),
					new ParameterValues(ONE, 83, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457")),
					new ParameterValues(ONE, 84, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571")),
					new ParameterValues(ONE, 85, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945714")),
					new ParameterValues(ONE, 86, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138")),
					new ParameterValues(ONE, 87, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382")),
					new ParameterValues(ONE, 88, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713822")),
					new ParameterValues(ONE, 89, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138218")),
					new ParameterValues(ONE, 90, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382179")),
					new ParameterValues(ONE, 91, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785")),
					new ParameterValues(ONE, 92, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138217853")),
					new ParameterValues(ONE, 93, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525")),
					new ParameterValues(ONE, 94, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785252")),
					new ParameterValues(ONE, 95, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138217852517")),
					new ParameterValues(ONE, 96, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166")),
					new ParameterValues(ONE, 97, new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664")),
					new ParameterValues(ONE, 98, new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663035354759457138217852516643")),
					new ParameterValues(ONE, 99, new BigDecimal("2.718281828459045235360287471352662497757247093699959574966967627724076630353547594571382178525166427")),

					// Values tests
					new ParameterValues(new BigDecimal("0.0"), 6, new BigDecimal("1.000000")),
					new ParameterValues(new BigDecimal("0.1"), 6, new BigDecimal("1.105171")),
					new ParameterValues(new BigDecimal("0.2"), 6, new BigDecimal("1.221403")),
					new ParameterValues(new BigDecimal("0.3"), 6, new BigDecimal("1.349859")),
					new ParameterValues(new BigDecimal("0.4"), 6, new BigDecimal("1.491825")),
					new ParameterValues(new BigDecimal("0.5"), 6, new BigDecimal("1.648721")),
					new ParameterValues(new BigDecimal("0.6"), 6, new BigDecimal("1.822119")),
					new ParameterValues(new BigDecimal("0.7"), 6, new BigDecimal("2.013753")),
					new ParameterValues(new BigDecimal("0.8"), 6, new BigDecimal("2.225541")),
					new ParameterValues(new BigDecimal("0.9"), 6, new BigDecimal("2.459603")),

					new ParameterValues(new BigDecimal("1.0"), 6, new BigDecimal("2.718282")),
					new ParameterValues(new BigDecimal("1.1"), 6, new BigDecimal("3.004166")),
					new ParameterValues(new BigDecimal("1.2"), 6, new BigDecimal("3.320117")),
					new ParameterValues(new BigDecimal("1.3"), 6, new BigDecimal("3.669297")),
					new ParameterValues(new BigDecimal("1.4"), 6, new BigDecimal("4.055200")),
					new ParameterValues(new BigDecimal("1.5"), 6, new BigDecimal("4.481689")),
					new ParameterValues(new BigDecimal("1.6"), 6, new BigDecimal("4.953032")),
					new ParameterValues(new BigDecimal("1.7"), 6, new BigDecimal("5.473947")),
					new ParameterValues(new BigDecimal("1.8"), 6, new BigDecimal("6.049647")),
					new ParameterValues(new BigDecimal("1.9"), 6, new BigDecimal("6.685894")),

					new ParameterValues(new BigDecimal("2.0"), 6, new BigDecimal("7.389056")),
					new ParameterValues(new BigDecimal("2.1"), 6, new BigDecimal("8.166170")),
					new ParameterValues(new BigDecimal("2.2"), 6, new BigDecimal("9.025013")),
					new ParameterValues(new BigDecimal("2.3"), 6, new BigDecimal("9.974182")),
					new ParameterValues(new BigDecimal("2.4"), 6, new BigDecimal("11.023176")),
					new ParameterValues(new BigDecimal("2.5"), 6, new BigDecimal("12.182494")),
					new ParameterValues(new BigDecimal("2.6"), 6, new BigDecimal("13.463738")),
					new ParameterValues(new BigDecimal("2.7"), 6, new BigDecimal("14.879732")),
					new ParameterValues(new BigDecimal("2.8"), 6, new BigDecimal("16.444647")),
					new ParameterValues(new BigDecimal("2.9"), 6, new BigDecimal("18.174145")),

					new ParameterValues(new BigDecimal("3.0"), 6, new BigDecimal("20.085537")),
					new ParameterValues(new BigDecimal("3.1"), 6, new BigDecimal("22.197951")),
					new ParameterValues(new BigDecimal("3.2"), 6, new BigDecimal("24.532530")),
					new ParameterValues(new BigDecimal("3.3"), 6, new BigDecimal("27.112639")),
					new ParameterValues(new BigDecimal("3.4"), 6, new BigDecimal("29.964100")),
					new ParameterValues(new BigDecimal("3.5"), 6, new BigDecimal("33.115452")),
					new ParameterValues(new BigDecimal("3.6"), 6, new BigDecimal("36.598234")),
					new ParameterValues(new BigDecimal("3.7"), 6, new BigDecimal("40.447304")),
					new ParameterValues(new BigDecimal("3.8"), 6, new BigDecimal("44.701184")),
					new ParameterValues(new BigDecimal("3.9"), 6, new BigDecimal("49.402449")),

					new ParameterValues(new BigDecimal("4.0"), 6, new BigDecimal("54.598150")),
					new ParameterValues(new BigDecimal("4.1"), 6, new BigDecimal("60.340288")),
					new ParameterValues(new BigDecimal("4.2"), 6, new BigDecimal("66.686331")),
					new ParameterValues(new BigDecimal("4.3"), 6, new BigDecimal("73.699794")),
					new ParameterValues(new BigDecimal("4.4"), 6, new BigDecimal("81.450869")),
					new ParameterValues(new BigDecimal("4.5"), 6, new BigDecimal("90.017131")),
					new ParameterValues(new BigDecimal("4.6"), 6, new BigDecimal("99.484316")),
					new ParameterValues(new BigDecimal("4.7"), 6, new BigDecimal("109.947172")),
					new ParameterValues(new BigDecimal("4.8"), 6, new BigDecimal("121.510418")),
					new ParameterValues(new BigDecimal("4.9"), 6, new BigDecimal("134.289780")),

					new ParameterValues(new BigDecimal("5.0"), 6, new BigDecimal("148.413159")),
					new ParameterValues(new BigDecimal("5.1"), 6, new BigDecimal("164.021907")),
					new ParameterValues(new BigDecimal("5.2"), 6, new BigDecimal("181.272242")),
					new ParameterValues(new BigDecimal("5.3"), 6, new BigDecimal("200.336810")),
					new ParameterValues(new BigDecimal("5.4"), 6, new BigDecimal("221.406416")),
					new ParameterValues(new BigDecimal("5.5"), 6, new BigDecimal("244.691932")),
					new ParameterValues(new BigDecimal("5.6"), 6, new BigDecimal("270.426407")),
					new ParameterValues(new BigDecimal("5.7"), 6, new BigDecimal("298.867401")),
					new ParameterValues(new BigDecimal("5.8"), 6, new BigDecimal("330.299560")),
					new ParameterValues(new BigDecimal("5.9"), 6, new BigDecimal("365.037468")),

					new ParameterValues(new BigDecimal("6.0"), 6, new BigDecimal("403.428793")),
					new ParameterValues(new BigDecimal("6.1"), 6, new BigDecimal("445.857770")),
					new ParameterValues(new BigDecimal("6.2"), 6, new BigDecimal("492.749041")),
					new ParameterValues(new BigDecimal("6.3"), 6, new BigDecimal("544.571910")),
					new ParameterValues(new BigDecimal("6.4"), 6, new BigDecimal("601.845038")),
					new ParameterValues(new BigDecimal("6.5"), 6, new BigDecimal("665.141633")),
					new ParameterValues(new BigDecimal("6.6"), 6, new BigDecimal("735.095189")),
					new ParameterValues(new BigDecimal("6.7"), 6, new BigDecimal("812.405825")),
					new ParameterValues(new BigDecimal("6.8"), 6, new BigDecimal("897.847292")),
					new ParameterValues(new BigDecimal("6.9"), 6, new BigDecimal("992.274716")),

					new ParameterValues(new BigDecimal("7.0"), 6, new BigDecimal("1096.633158")),
					new ParameterValues(new BigDecimal("7.1"), 6, new BigDecimal("1211.967074")),
					new ParameterValues(new BigDecimal("7.2"), 6, new BigDecimal("1339.430764")),
					new ParameterValues(new BigDecimal("7.3"), 6, new BigDecimal("1480.299928")),
					new ParameterValues(new BigDecimal("7.4"), 6, new BigDecimal("1635.984430")),
					new ParameterValues(new BigDecimal("7.5"), 6, new BigDecimal("1808.042414")),
					new ParameterValues(new BigDecimal("7.6"), 6, new BigDecimal("1998.195895")),
					new ParameterValues(new BigDecimal("7.7"), 6, new BigDecimal("2208.347992")),
					new ParameterValues(new BigDecimal("7.8"), 6, new BigDecimal("2440.601978")),
					new ParameterValues(new BigDecimal("7.9"), 6, new BigDecimal("2697.282328")),

					new ParameterValues(new BigDecimal("8.0"), 6, new BigDecimal("2980.957987")),
					new ParameterValues(new BigDecimal("8.1"), 6, new BigDecimal("3294.468075")),
					new ParameterValues(new BigDecimal("8.2"), 6, new BigDecimal("3640.950307")),
					new ParameterValues(new BigDecimal("8.3"), 6, new BigDecimal("4023.872394")),
					new ParameterValues(new BigDecimal("8.4"), 6, new BigDecimal("4447.066748")),
					new ParameterValues(new BigDecimal("8.5"), 6, new BigDecimal("4914.768840")),
					new ParameterValues(new BigDecimal("8.6"), 6, new BigDecimal("5431.659591")),
					new ParameterValues(new BigDecimal("8.7"), 6, new BigDecimal("6002.912217")),
					new ParameterValues(new BigDecimal("8.8"), 6, new BigDecimal("6634.244006")),
					new ParameterValues(new BigDecimal("8.9"), 6, new BigDecimal("7331.973539")),

					new ParameterValues(new BigDecimal("9.0"), 6, new BigDecimal("8103.083928")),
					new ParameterValues(new BigDecimal("9.1"), 6, new BigDecimal("8955.292703")),
					new ParameterValues(new BigDecimal("9.2"), 6, new BigDecimal("9897.129059")),
					new ParameterValues(new BigDecimal("9.3"), 6, new BigDecimal("10938.019208")),
					new ParameterValues(new BigDecimal("9.4"), 6, new BigDecimal("12088.380730")),
					new ParameterValues(new BigDecimal("9.5"), 6, new BigDecimal("13359.726830")),
					new ParameterValues(new BigDecimal("9.6"), 6, new BigDecimal("14764.781566")),
					new ParameterValues(new BigDecimal("9.7"), 6, new BigDecimal("16317.607198")),
					new ParameterValues(new BigDecimal("9.8"), 6, new BigDecimal("18033.744928")),
					new ParameterValues(new BigDecimal("9.9"), 6, new BigDecimal("19930.370438")),
			});
		}

		@Test
		public void test() {
			BigDecimal result = exp(x, scale);
			BigDecimal scaledResult = result.setScale(expectedValue.scale(), RoundingMode.HALF_UP);

			assertThat(scaledResult, is(equalTo(expectedValue)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final BigDecimal expectedValue;

			private final int scale;

			private final BigDecimal x;


			public ParameterValues(BigDecimal x, int scale, BigDecimal expectedValue) {
				this.x = x;
				this.scale = scale;
				this.expectedValue = expectedValue;
			}


			public BigDecimal getExpectedValue() {
				return expectedValue;
			}

			public BigDecimal getX() {
				return x;
			}

			public int getScale() {
				return scale;
			}

		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class ParameterizedLnTest {

		private final BigDecimal expectedValue;

		private final int scale;

		private final BigDecimal x;


		public ParameterizedLnTest(ParameterValues values) {
			expectedValue = values.getExpectedValue();
			scale = values.getScale();
			x = values.getX();
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					// Precision tests
					new ParameterValues(TWO,  0, new BigDecimal("1")),
					new ParameterValues(TWO,  1, new BigDecimal("0.7")),
					new ParameterValues(TWO,  2, new BigDecimal("0.69")),
					new ParameterValues(TWO,  3, new BigDecimal("0.693")),
					new ParameterValues(TWO,  4, new BigDecimal("0.6931")),
					new ParameterValues(TWO,  5, new BigDecimal("0.69315")),
					new ParameterValues(TWO,  6, new BigDecimal("0.693147")),
					new ParameterValues(TWO,  7, new BigDecimal("0.6931472")),
					new ParameterValues(TWO,  8, new BigDecimal("0.69314718")),
					new ParameterValues(TWO,  9, new BigDecimal("0.693147181")),
					new ParameterValues(TWO, 10, new BigDecimal("0.6931471806")),
					new ParameterValues(TWO, 11, new BigDecimal("0.69314718056")),
					new ParameterValues(TWO, 12, new BigDecimal("0.693147180560")),
					new ParameterValues(TWO, 13, new BigDecimal("0.6931471805599")),
					new ParameterValues(TWO, 14, new BigDecimal("0.69314718055995")),
					new ParameterValues(TWO, 15, new BigDecimal("0.693147180559945")),
					new ParameterValues(TWO, 16, new BigDecimal("0.6931471805599453")),
					new ParameterValues(TWO, 17, new BigDecimal("0.69314718055994531")),
					new ParameterValues(TWO, 18, new BigDecimal("0.693147180559945309")),
					new ParameterValues(TWO, 19, new BigDecimal("0.6931471805599453094")),
					new ParameterValues(TWO, 20, new BigDecimal("0.69314718055994530942")),
					new ParameterValues(TWO, 21, new BigDecimal("0.693147180559945309417")),
					new ParameterValues(TWO, 22, new BigDecimal("0.6931471805599453094172")),
					new ParameterValues(TWO, 23, new BigDecimal("0.69314718055994530941723")),
					new ParameterValues(TWO, 24, new BigDecimal("0.693147180559945309417232")),
					new ParameterValues(TWO, 25, new BigDecimal("0.6931471805599453094172321")),
					new ParameterValues(TWO, 26, new BigDecimal("0.69314718055994530941723212")),
					new ParameterValues(TWO, 27, new BigDecimal("0.693147180559945309417232121")),
					new ParameterValues(TWO, 28, new BigDecimal("0.6931471805599453094172321215")),
					new ParameterValues(TWO, 29, new BigDecimal("0.69314718055994530941723212146")),
					new ParameterValues(TWO, 30, new BigDecimal("0.693147180559945309417232121458")),
					new ParameterValues(TWO, 31, new BigDecimal("0.6931471805599453094172321214582")),
					new ParameterValues(TWO, 32, new BigDecimal("0.69314718055994530941723212145818")),
					new ParameterValues(TWO, 33, new BigDecimal("0.693147180559945309417232121458177")),
					new ParameterValues(TWO, 34, new BigDecimal("0.6931471805599453094172321214581766")),
					new ParameterValues(TWO, 35, new BigDecimal("0.69314718055994530941723212145817657")),
					new ParameterValues(TWO, 36, new BigDecimal("0.693147180559945309417232121458176568")),
					new ParameterValues(TWO, 37, new BigDecimal("0.6931471805599453094172321214581765681")),
					new ParameterValues(TWO, 38, new BigDecimal("0.69314718055994530941723212145817656808")),
					new ParameterValues(TWO, 39, new BigDecimal("0.693147180559945309417232121458176568076")),
					new ParameterValues(TWO, 40, new BigDecimal("0.6931471805599453094172321214581765680755")),
					new ParameterValues(TWO, 41, new BigDecimal("0.69314718055994530941723212145817656807550")),
					new ParameterValues(TWO, 42, new BigDecimal("0.693147180559945309417232121458176568075500")),
					new ParameterValues(TWO, 43, new BigDecimal("0.6931471805599453094172321214581765680755001")),
					new ParameterValues(TWO, 44, new BigDecimal("0.69314718055994530941723212145817656807550013")),
					new ParameterValues(TWO, 45, new BigDecimal("0.693147180559945309417232121458176568075500134")),
					new ParameterValues(TWO, 46, new BigDecimal("0.6931471805599453094172321214581765680755001344")),
					new ParameterValues(TWO, 47, new BigDecimal("0.69314718055994530941723212145817656807550013436")),
					new ParameterValues(TWO, 48, new BigDecimal("0.693147180559945309417232121458176568075500134360")),
					new ParameterValues(TWO, 49, new BigDecimal("0.6931471805599453094172321214581765680755001343603")),
					new ParameterValues(TWO, 50, new BigDecimal("0.69314718055994530941723212145817656807550013436026")),
					new ParameterValues(TWO, 51, new BigDecimal("0.693147180559945309417232121458176568075500134360255")),
					new ParameterValues(TWO, 52, new BigDecimal("0.6931471805599453094172321214581765680755001343602553")),
					new ParameterValues(TWO, 53, new BigDecimal("0.69314718055994530941723212145817656807550013436025525")),
					new ParameterValues(TWO, 54, new BigDecimal("0.693147180559945309417232121458176568075500134360255254")),
					new ParameterValues(TWO, 55, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541")),
					new ParameterValues(TWO, 56, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412")),
					new ParameterValues(TWO, 57, new BigDecimal("0.693147180559945309417232121458176568075500134360255254121")),
					new ParameterValues(TWO, 58, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541207")),
					new ParameterValues(TWO, 59, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068")),
					new ParameterValues(TWO, 60, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680")),
					new ParameterValues(TWO, 61, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800")),
					new ParameterValues(TWO, 62, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068001")),
					new ParameterValues(TWO, 63, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009")),
					new ParameterValues(TWO, 64, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800095")),
					new ParameterValues(TWO, 65, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949")),
					new ParameterValues(TWO, 66, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493")),
					new ParameterValues(TWO, 67, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094934")),
					new ParameterValues(TWO, 68, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339")),
					new ParameterValues(TWO, 69, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493394")),
					new ParameterValues(TWO, 70, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936")),
					new ParameterValues(TWO, 71, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362")),
					new ParameterValues(TWO, 72, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393622")),
					new ParameterValues(TWO, 73, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936220")),
					new ParameterValues(TWO, 74, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362197")),
					new ParameterValues(TWO, 75, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393621970")),
					new ParameterValues(TWO, 76, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936219697")),
					new ParameterValues(TWO, 77, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362196969")),
					new ParameterValues(TWO, 78, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393621969695")),
					new ParameterValues(TWO, 79, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936219696947")),
					new ParameterValues(TWO, 80, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362196969472")),
					new ParameterValues(TWO, 81, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393621969694716")),
					new ParameterValues(TWO, 82, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936219696947156")),
					new ParameterValues(TWO, 83, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362196969471561")),
					new ParameterValues(TWO, 84, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393621969694715606")),
					new ParameterValues(TWO, 85, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936219696947156059")),
					new ParameterValues(TWO, 86, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362196969471560586")),
					new ParameterValues(TWO, 87, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393621969694715605863")),
					new ParameterValues(TWO, 88, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936219696947156058633")),
					new ParameterValues(TWO, 89, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362196969471560586333")),
					new ParameterValues(TWO, 90, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393621969694715605863327")),
					new ParameterValues(TWO, 91, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936219696947156058633270")),
					new ParameterValues(TWO, 92, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362196969471560586332700")),
					new ParameterValues(TWO, 93, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393621969694715605863326996")),
					new ParameterValues(TWO, 94, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936219696947156058633269964")),
					new ParameterValues(TWO, 95, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362196969471560586332699642")),
					new ParameterValues(TWO, 96, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393621969694715605863326996419")),
					new ParameterValues(TWO, 97, new BigDecimal("0.6931471805599453094172321214581765680755001343602552541206800094933936219696947156058633269964187")),
					new ParameterValues(TWO, 98, new BigDecimal("0.69314718055994530941723212145817656807550013436025525412068000949339362196969471560586332699641869")),
					new ParameterValues(TWO, 99, new BigDecimal("0.693147180559945309417232121458176568075500134360255254120680009493393621969694715605863326996418688")),

					// Values tests
					// NOTE: Logarithm is not defined for 0
					new ParameterValues(new BigDecimal("0.1"), 6, new BigDecimal("-2.302585")),
					new ParameterValues(new BigDecimal("0.2"), 6, new BigDecimal("-1.609438")),
					new ParameterValues(new BigDecimal("0.3"), 6, new BigDecimal("-1.203973")),
					new ParameterValues(new BigDecimal("0.4"), 6, new BigDecimal("-0.916291")),
					new ParameterValues(new BigDecimal("0.5"), 6, new BigDecimal("-0.693147")),
					new ParameterValues(new BigDecimal("0.6"), 6, new BigDecimal("-0.510826")),
					new ParameterValues(new BigDecimal("0.7"), 6, new BigDecimal("-0.356675")),
					new ParameterValues(new BigDecimal("0.8"), 6, new BigDecimal("-0.223144")),
					new ParameterValues(new BigDecimal("0.9"), 6, new BigDecimal("-0.105361")),

					new ParameterValues(new BigDecimal("1.0"), 6, new BigDecimal("0.000000")),
					new ParameterValues(new BigDecimal("1.1"), 6, new BigDecimal("0.095310")),
					new ParameterValues(new BigDecimal("1.2"), 6, new BigDecimal("0.182322")),
					new ParameterValues(new BigDecimal("1.3"), 6, new BigDecimal("0.262364")),
					new ParameterValues(new BigDecimal("1.4"), 6, new BigDecimal("0.336472")),
					new ParameterValues(new BigDecimal("1.5"), 6, new BigDecimal("0.405465")),
					new ParameterValues(new BigDecimal("1.6"), 6, new BigDecimal("0.470004")),
					new ParameterValues(new BigDecimal("1.7"), 6, new BigDecimal("0.530628")),
					new ParameterValues(new BigDecimal("1.8"), 6, new BigDecimal("0.587787")),
					new ParameterValues(new BigDecimal("1.9"), 6, new BigDecimal("0.641854")),

					new ParameterValues(new BigDecimal("2.0"), 6, new BigDecimal("0.693147")),
					new ParameterValues(new BigDecimal("2.1"), 6, new BigDecimal("0.741937")),
					new ParameterValues(new BigDecimal("2.2"), 6, new BigDecimal("0.788457")),
					new ParameterValues(new BigDecimal("2.3"), 6, new BigDecimal("0.832909")),
					new ParameterValues(new BigDecimal("2.4"), 6, new BigDecimal("0.875469")),
					new ParameterValues(new BigDecimal("2.5"), 6, new BigDecimal("0.916291")),
					new ParameterValues(new BigDecimal("2.6"), 6, new BigDecimal("0.955511")),
					new ParameterValues(new BigDecimal("2.7"), 6, new BigDecimal("0.993252")),
					new ParameterValues(new BigDecimal("2.8"), 6, new BigDecimal("1.029619")),
					new ParameterValues(new BigDecimal("2.9"), 6, new BigDecimal("1.064711")),

					new ParameterValues(new BigDecimal("3.0"), 6, new BigDecimal("1.098612")),
					new ParameterValues(new BigDecimal("3.1"), 6, new BigDecimal("1.131402")),
					new ParameterValues(new BigDecimal("3.2"), 6, new BigDecimal("1.163151")),
					new ParameterValues(new BigDecimal("3.3"), 6, new BigDecimal("1.193922")),
					new ParameterValues(new BigDecimal("3.4"), 6, new BigDecimal("1.223775")),
					new ParameterValues(new BigDecimal("3.5"), 6, new BigDecimal("1.252763")),
					new ParameterValues(new BigDecimal("3.6"), 6, new BigDecimal("1.280934")),
					new ParameterValues(new BigDecimal("3.7"), 6, new BigDecimal("1.308333")),
					new ParameterValues(new BigDecimal("3.8"), 6, new BigDecimal("1.335001")),
					new ParameterValues(new BigDecimal("3.9"), 6, new BigDecimal("1.360977")),

					new ParameterValues(new BigDecimal("4.0"), 6, new BigDecimal("1.386294")),
					new ParameterValues(new BigDecimal("4.1"), 6, new BigDecimal("1.410987")),
					new ParameterValues(new BigDecimal("4.2"), 6, new BigDecimal("1.435085")),
					new ParameterValues(new BigDecimal("4.3"), 6, new BigDecimal("1.458615")),
					new ParameterValues(new BigDecimal("4.4"), 6, new BigDecimal("1.481605")),
					new ParameterValues(new BigDecimal("4.5"), 6, new BigDecimal("1.504077")),
					new ParameterValues(new BigDecimal("4.6"), 6, new BigDecimal("1.526056")),
					new ParameterValues(new BigDecimal("4.7"), 6, new BigDecimal("1.547563")),
					new ParameterValues(new BigDecimal("4.8"), 6, new BigDecimal("1.568616")),
					new ParameterValues(new BigDecimal("4.9"), 6, new BigDecimal("1.589235")),

					new ParameterValues(new BigDecimal("5.0"), 6, new BigDecimal("1.609438")),
					new ParameterValues(new BigDecimal("5.1"), 6, new BigDecimal("1.629241")),
					new ParameterValues(new BigDecimal("5.2"), 6, new BigDecimal("1.648659")),
					new ParameterValues(new BigDecimal("5.3"), 6, new BigDecimal("1.667707")),
					new ParameterValues(new BigDecimal("5.4"), 6, new BigDecimal("1.686399")),
					new ParameterValues(new BigDecimal("5.5"), 6, new BigDecimal("1.704748")),
					new ParameterValues(new BigDecimal("5.6"), 6, new BigDecimal("1.722767")),
					new ParameterValues(new BigDecimal("5.7"), 6, new BigDecimal("1.740466")),
					new ParameterValues(new BigDecimal("5.8"), 6, new BigDecimal("1.757858")),
					new ParameterValues(new BigDecimal("5.9"), 6, new BigDecimal("1.774952")),

					new ParameterValues(new BigDecimal("6.0"), 6, new BigDecimal("1.791759")),
					new ParameterValues(new BigDecimal("6.1"), 6, new BigDecimal("1.808289")),
					new ParameterValues(new BigDecimal("6.2"), 6, new BigDecimal("1.824549")),
					new ParameterValues(new BigDecimal("6.3"), 6, new BigDecimal("1.840550")),
					new ParameterValues(new BigDecimal("6.4"), 6, new BigDecimal("1.856298")),
					new ParameterValues(new BigDecimal("6.5"), 6, new BigDecimal("1.871802")),
					new ParameterValues(new BigDecimal("6.6"), 6, new BigDecimal("1.887070")),
					new ParameterValues(new BigDecimal("6.7"), 6, new BigDecimal("1.902108")),
					new ParameterValues(new BigDecimal("6.8"), 6, new BigDecimal("1.916923")),
					new ParameterValues(new BigDecimal("6.9"), 6, new BigDecimal("1.931521")),

					new ParameterValues(new BigDecimal("7.0"), 6, new BigDecimal("1.945910")),
					new ParameterValues(new BigDecimal("7.1"), 6, new BigDecimal("1.960095")),
					new ParameterValues(new BigDecimal("7.2"), 6, new BigDecimal("1.974081")),
					new ParameterValues(new BigDecimal("7.3"), 6, new BigDecimal("1.987874")),
					new ParameterValues(new BigDecimal("7.4"), 6, new BigDecimal("2.001480")),
					new ParameterValues(new BigDecimal("7.5"), 6, new BigDecimal("2.014903")),
					new ParameterValues(new BigDecimal("7.6"), 6, new BigDecimal("2.028148")),
					new ParameterValues(new BigDecimal("7.7"), 6, new BigDecimal("2.041220")),
					new ParameterValues(new BigDecimal("7.8"), 6, new BigDecimal("2.054124")),
					new ParameterValues(new BigDecimal("7.9"), 6, new BigDecimal("2.066863")),

					new ParameterValues(new BigDecimal("8.0"), 6, new BigDecimal("2.079442")),
					new ParameterValues(new BigDecimal("8.1"), 6, new BigDecimal("2.091864")),
					new ParameterValues(new BigDecimal("8.2"), 6, new BigDecimal("2.104134")),
					new ParameterValues(new BigDecimal("8.3"), 6, new BigDecimal("2.116256")),
					new ParameterValues(new BigDecimal("8.4"), 6, new BigDecimal("2.128232")),
					new ParameterValues(new BigDecimal("8.5"), 6, new BigDecimal("2.140066")),
					new ParameterValues(new BigDecimal("8.6"), 6, new BigDecimal("2.151762")),
					new ParameterValues(new BigDecimal("8.7"), 6, new BigDecimal("2.163323")),
					new ParameterValues(new BigDecimal("8.8"), 6, new BigDecimal("2.174752")),
					new ParameterValues(new BigDecimal("8.9"), 6, new BigDecimal("2.186051")),

					new ParameterValues(new BigDecimal("9.0"), 6, new BigDecimal("2.197225")),
					new ParameterValues(new BigDecimal("9.1"), 6, new BigDecimal("2.208274")),
					new ParameterValues(new BigDecimal("9.2"), 6, new BigDecimal("2.219203")),
					new ParameterValues(new BigDecimal("9.3"), 6, new BigDecimal("2.230014")),
					new ParameterValues(new BigDecimal("9.4"), 6, new BigDecimal("2.240710")),
					new ParameterValues(new BigDecimal("9.5"), 6, new BigDecimal("2.251292")),
					new ParameterValues(new BigDecimal("9.6"), 6, new BigDecimal("2.261763")),
					new ParameterValues(new BigDecimal("9.7"), 6, new BigDecimal("2.272126")),
					new ParameterValues(new BigDecimal("9.8"), 6, new BigDecimal("2.282382")),
					new ParameterValues(new BigDecimal("9.9"), 6, new BigDecimal("2.292535")),
			});
		}

		@Test
		public void test() {
			BigDecimal result = ln(x, scale);
			BigDecimal scaledResult = result.setScale(expectedValue.scale(), RoundingMode.HALF_UP);

			assertThat(scaledResult, is(equalTo(expectedValue)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final BigDecimal expectedValue;

			private final int scale;

			private final BigDecimal x;


			public ParameterValues(BigDecimal x, int scale, BigDecimal expectedValue) {
				this.x = x;
				this.scale = scale;
				this.expectedValue = expectedValue;
			}


			public BigDecimal getExpectedValue() {
				return expectedValue;
			}

			public BigDecimal getX() {
				return x;
			}

			public int getScale() {
				return scale;
			}

		}

	}

}
