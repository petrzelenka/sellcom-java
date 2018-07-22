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
import static org.sellcom.core.math.MoreMath.pow;
import static org.sellcom.core.math.MoreMath.root;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

public class BigDecimalMathTest {

	private static final BigDecimal THREE = BigDecimal.valueOf(3L);

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class ParameterizedPowTest {

		private final BigDecimal expectedValue;

		private final long n;

		private final int scale;

		private final BigDecimal x;


		public ParameterizedPowTest(ParameterValues values) {
			expectedValue = values.getExpectedValue();
			n = values.getN();
			scale = values.getScale();
			x = values.getX();
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					// Positive exponent tests
					new ParameterValues(new BigDecimal("-9.9"), 3, 3, new BigDecimal("-970.299")),
					new ParameterValues(new BigDecimal("-9.8"), 3, 3, new BigDecimal("-941.192")),
					new ParameterValues(new BigDecimal("-9.7"), 3, 3, new BigDecimal("-912.673")),
					new ParameterValues(new BigDecimal("-9.6"), 3, 3, new BigDecimal("-884.736")),
					new ParameterValues(new BigDecimal("-9.5"), 3, 3, new BigDecimal("-857.375")),
					new ParameterValues(new BigDecimal("-9.4"), 3, 3, new BigDecimal("-830.584")),
					new ParameterValues(new BigDecimal("-9.3"), 3, 3, new BigDecimal("-804.357")),
					new ParameterValues(new BigDecimal("-9.2"), 3, 3, new BigDecimal("-778.688")),
					new ParameterValues(new BigDecimal("-9.1"), 3, 3, new BigDecimal("-753.571")),
					new ParameterValues(new BigDecimal("-9.0"), 3, 3, new BigDecimal("-729.000")),

					new ParameterValues(new BigDecimal("-8.9"), 3, 3, new BigDecimal("-704.969")),
					new ParameterValues(new BigDecimal("-8.8"), 3, 3, new BigDecimal("-681.472")),
					new ParameterValues(new BigDecimal("-8.7"), 3, 3, new BigDecimal("-658.503")),
					new ParameterValues(new BigDecimal("-8.6"), 3, 3, new BigDecimal("-636.056")),
					new ParameterValues(new BigDecimal("-8.5"), 3, 3, new BigDecimal("-614.125")),
					new ParameterValues(new BigDecimal("-8.4"), 3, 3, new BigDecimal("-592.704")),
					new ParameterValues(new BigDecimal("-8.3"), 3, 3, new BigDecimal("-571.787")),
					new ParameterValues(new BigDecimal("-8.2"), 3, 3, new BigDecimal("-551.368")),
					new ParameterValues(new BigDecimal("-8.1"), 3, 3, new BigDecimal("-531.441")),
					new ParameterValues(new BigDecimal("-8.0"), 3, 3, new BigDecimal("-512.000")),

					new ParameterValues(new BigDecimal("-7.9"), 3, 3, new BigDecimal("-493.039")),
					new ParameterValues(new BigDecimal("-7.8"), 3, 3, new BigDecimal("-474.552")),
					new ParameterValues(new BigDecimal("-7.7"), 3, 3, new BigDecimal("-456.533")),
					new ParameterValues(new BigDecimal("-7.6"), 3, 3, new BigDecimal("-438.976")),
					new ParameterValues(new BigDecimal("-7.5"), 3, 3, new BigDecimal("-421.875")),
					new ParameterValues(new BigDecimal("-7.4"), 3, 3, new BigDecimal("-405.224")),
					new ParameterValues(new BigDecimal("-7.3"), 3, 3, new BigDecimal("-389.017")),
					new ParameterValues(new BigDecimal("-7.2"), 3, 3, new BigDecimal("-373.248")),
					new ParameterValues(new BigDecimal("-7.1"), 3, 3, new BigDecimal("-357.911")),
					new ParameterValues(new BigDecimal("-7.0"), 3, 3, new BigDecimal("-343.000")),

					new ParameterValues(new BigDecimal("-6.9"), 3, 3, new BigDecimal("-328.509")),
					new ParameterValues(new BigDecimal("-6.8"), 3, 3, new BigDecimal("-314.432")),
					new ParameterValues(new BigDecimal("-6.7"), 3, 3, new BigDecimal("-300.763")),
					new ParameterValues(new BigDecimal("-6.6"), 3, 3, new BigDecimal("-287.496")),
					new ParameterValues(new BigDecimal("-6.5"), 3, 3, new BigDecimal("-274.625")),
					new ParameterValues(new BigDecimal("-6.4"), 3, 3, new BigDecimal("-262.144")),
					new ParameterValues(new BigDecimal("-6.3"), 3, 3, new BigDecimal("-250.047")),
					new ParameterValues(new BigDecimal("-6.2"), 3, 3, new BigDecimal("-238.328")),
					new ParameterValues(new BigDecimal("-6.1"), 3, 3, new BigDecimal("-226.981")),
					new ParameterValues(new BigDecimal("-6.0"), 3, 3, new BigDecimal("-216.000")),

					new ParameterValues(new BigDecimal("-5.9"), 3, 3, new BigDecimal("-205.379")),
					new ParameterValues(new BigDecimal("-5.8"), 3, 3, new BigDecimal("-195.112")),
					new ParameterValues(new BigDecimal("-5.7"), 3, 3, new BigDecimal("-185.193")),
					new ParameterValues(new BigDecimal("-5.6"), 3, 3, new BigDecimal("-175.616")),
					new ParameterValues(new BigDecimal("-5.5"), 3, 3, new BigDecimal("-166.375")),
					new ParameterValues(new BigDecimal("-5.4"), 3, 3, new BigDecimal("-157.464")),
					new ParameterValues(new BigDecimal("-5.3"), 3, 3, new BigDecimal("-148.877")),
					new ParameterValues(new BigDecimal("-5.2"), 3, 3, new BigDecimal("-140.608")),
					new ParameterValues(new BigDecimal("-5.1"), 3, 3, new BigDecimal("-132.651")),
					new ParameterValues(new BigDecimal("-5.0"), 3, 3, new BigDecimal("-125.000")),

					new ParameterValues(new BigDecimal("-4.9"), 3, 3, new BigDecimal("-117.649")),
					new ParameterValues(new BigDecimal("-4.8"), 3, 3, new BigDecimal("-110.592")),
					new ParameterValues(new BigDecimal("-4.7"), 3, 3, new BigDecimal("-103.823")),
					new ParameterValues(new BigDecimal("-4.6"), 3, 3, new BigDecimal("-97.336")),
					new ParameterValues(new BigDecimal("-4.5"), 3, 3, new BigDecimal("-91.125")),
					new ParameterValues(new BigDecimal("-4.4"), 3, 3, new BigDecimal("-85.184")),
					new ParameterValues(new BigDecimal("-4.3"), 3, 3, new BigDecimal("-79.507")),
					new ParameterValues(new BigDecimal("-4.2"), 3, 3, new BigDecimal("-74.088")),
					new ParameterValues(new BigDecimal("-4.1"), 3, 3, new BigDecimal("-68.921")),
					new ParameterValues(new BigDecimal("-4.0"), 3, 3, new BigDecimal("-64.000")),

					new ParameterValues(new BigDecimal("-3.9"), 3, 3, new BigDecimal("-59.319")),
					new ParameterValues(new BigDecimal("-3.8"), 3, 3, new BigDecimal("-54.872")),
					new ParameterValues(new BigDecimal("-3.7"), 3, 3, new BigDecimal("-50.653")),
					new ParameterValues(new BigDecimal("-3.6"), 3, 3, new BigDecimal("-46.656")),
					new ParameterValues(new BigDecimal("-3.5"), 3, 3, new BigDecimal("-42.875")),
					new ParameterValues(new BigDecimal("-3.4"), 3, 3, new BigDecimal("-39.304")),
					new ParameterValues(new BigDecimal("-3.3"), 3, 3, new BigDecimal("-35.937")),
					new ParameterValues(new BigDecimal("-3.2"), 3, 3, new BigDecimal("-32.768")),
					new ParameterValues(new BigDecimal("-3.1"), 3, 3, new BigDecimal("-29.791")),
					new ParameterValues(new BigDecimal("-3.0"), 3, 3, new BigDecimal("-27.000")),

					new ParameterValues(new BigDecimal("-2.9"), 3, 3, new BigDecimal("-24.389")),
					new ParameterValues(new BigDecimal("-2.8"), 3, 3, new BigDecimal("-21.952")),
					new ParameterValues(new BigDecimal("-2.7"), 3, 3, new BigDecimal("-19.683")),
					new ParameterValues(new BigDecimal("-2.6"), 3, 3, new BigDecimal("-17.576")),
					new ParameterValues(new BigDecimal("-2.5"), 3, 3, new BigDecimal("-15.625")),
					new ParameterValues(new BigDecimal("-2.4"), 3, 3, new BigDecimal("-13.824")),
					new ParameterValues(new BigDecimal("-2.3"), 3, 3, new BigDecimal("-12.167")),
					new ParameterValues(new BigDecimal("-2.2"), 3, 3, new BigDecimal("-10.648")),
					new ParameterValues(new BigDecimal("-2.1"), 3, 3, new BigDecimal("-9.261")),
					new ParameterValues(new BigDecimal("-2.0"), 3, 3, new BigDecimal("-8.000")),

					new ParameterValues(new BigDecimal("-1.9"), 3, 3, new BigDecimal("-6.859")),
					new ParameterValues(new BigDecimal("-1.8"), 3, 3, new BigDecimal("-5.832")),
					new ParameterValues(new BigDecimal("-1.7"), 3, 3, new BigDecimal("-4.913")),
					new ParameterValues(new BigDecimal("-1.6"), 3, 3, new BigDecimal("-4.096")),
					new ParameterValues(new BigDecimal("-1.5"), 3, 3, new BigDecimal("-3.375")),
					new ParameterValues(new BigDecimal("-1.4"), 3, 3, new BigDecimal("-2.744")),
					new ParameterValues(new BigDecimal("-1.3"), 3, 3, new BigDecimal("-2.197")),
					new ParameterValues(new BigDecimal("-1.2"), 3, 3, new BigDecimal("-1.728")),
					new ParameterValues(new BigDecimal("-1.1"), 3, 3, new BigDecimal("-1.331")),
					new ParameterValues(new BigDecimal("-1.0"), 3, 3, new BigDecimal("-1.000")),

					new ParameterValues(new BigDecimal("-0.9"), 3, 3, new BigDecimal("-0.729")),
					new ParameterValues(new BigDecimal("-0.8"), 3, 3, new BigDecimal("-0.512")),
					new ParameterValues(new BigDecimal("-0.7"), 3, 3, new BigDecimal("-0.343")),
					new ParameterValues(new BigDecimal("-0.6"), 3, 3, new BigDecimal("-0.216")),
					new ParameterValues(new BigDecimal("-0.5"), 3, 3, new BigDecimal("-0.125")),
					new ParameterValues(new BigDecimal("-0.4"), 3, 3, new BigDecimal("-0.064")),
					new ParameterValues(new BigDecimal("-0.3"), 3, 3, new BigDecimal("-0.027")),
					new ParameterValues(new BigDecimal("-0.2"), 3, 3, new BigDecimal("-0.008")),
					new ParameterValues(new BigDecimal("-0.1"), 3, 3, new BigDecimal("-0.001")),

					new ParameterValues(new BigDecimal("0.0"), 3, 3, new BigDecimal("0.000")),

					new ParameterValues(new BigDecimal("0.1"), 3, 3, new BigDecimal("0.001")),
					new ParameterValues(new BigDecimal("0.2"), 3, 3, new BigDecimal("0.008")),
					new ParameterValues(new BigDecimal("0.3"), 3, 3, new BigDecimal("0.027")),
					new ParameterValues(new BigDecimal("0.4"), 3, 3, new BigDecimal("0.064")),
					new ParameterValues(new BigDecimal("0.5"), 3, 3, new BigDecimal("0.125")),
					new ParameterValues(new BigDecimal("0.6"), 3, 3, new BigDecimal("0.216")),
					new ParameterValues(new BigDecimal("0.7"), 3, 3, new BigDecimal("0.343")),
					new ParameterValues(new BigDecimal("0.8"), 3, 3, new BigDecimal("0.512")),
					new ParameterValues(new BigDecimal("0.9"), 3, 3, new BigDecimal("0.729")),

					new ParameterValues(new BigDecimal("1.0"), 3, 3, new BigDecimal("1.000")),
					new ParameterValues(new BigDecimal("1.1"), 3, 3, new BigDecimal("1.331")),
					new ParameterValues(new BigDecimal("1.2"), 3, 3, new BigDecimal("1.728")),
					new ParameterValues(new BigDecimal("1.3"), 3, 3, new BigDecimal("2.197")),
					new ParameterValues(new BigDecimal("1.4"), 3, 3, new BigDecimal("2.744")),
					new ParameterValues(new BigDecimal("1.5"), 3, 3, new BigDecimal("3.375")),
					new ParameterValues(new BigDecimal("1.6"), 3, 3, new BigDecimal("4.096")),
					new ParameterValues(new BigDecimal("1.7"), 3, 3, new BigDecimal("4.913")),
					new ParameterValues(new BigDecimal("1.8"), 3, 3, new BigDecimal("5.832")),
					new ParameterValues(new BigDecimal("1.9"), 3, 3, new BigDecimal("6.859")),

					new ParameterValues(new BigDecimal("2.0"), 3, 3, new BigDecimal("8.000")),
					new ParameterValues(new BigDecimal("2.1"), 3, 3, new BigDecimal("9.261")),
					new ParameterValues(new BigDecimal("2.2"), 3, 3, new BigDecimal("10.648")),
					new ParameterValues(new BigDecimal("2.3"), 3, 3, new BigDecimal("12.167")),
					new ParameterValues(new BigDecimal("2.4"), 3, 3, new BigDecimal("13.824")),
					new ParameterValues(new BigDecimal("2.5"), 3, 3, new BigDecimal("15.625")),
					new ParameterValues(new BigDecimal("2.6"), 3, 3, new BigDecimal("17.576")),
					new ParameterValues(new BigDecimal("2.7"), 3, 3, new BigDecimal("19.683")),
					new ParameterValues(new BigDecimal("2.8"), 3, 3, new BigDecimal("21.952")),
					new ParameterValues(new BigDecimal("2.9"), 3, 3, new BigDecimal("24.389")),

					new ParameterValues(new BigDecimal("3.0"), 3, 3, new BigDecimal("27.000")),
					new ParameterValues(new BigDecimal("3.1"), 3, 3, new BigDecimal("29.791")),
					new ParameterValues(new BigDecimal("3.2"), 3, 3, new BigDecimal("32.768")),
					new ParameterValues(new BigDecimal("3.3"), 3, 3, new BigDecimal("35.937")),
					new ParameterValues(new BigDecimal("3.4"), 3, 3, new BigDecimal("39.304")),
					new ParameterValues(new BigDecimal("3.5"), 3, 3, new BigDecimal("42.875")),
					new ParameterValues(new BigDecimal("3.6"), 3, 3, new BigDecimal("46.656")),
					new ParameterValues(new BigDecimal("3.7"), 3, 3, new BigDecimal("50.653")),
					new ParameterValues(new BigDecimal("3.8"), 3, 3, new BigDecimal("54.872")),
					new ParameterValues(new BigDecimal("3.9"), 3, 3, new BigDecimal("59.319")),

					new ParameterValues(new BigDecimal("4.0"), 3, 3, new BigDecimal("64.000")),
					new ParameterValues(new BigDecimal("4.1"), 3, 3, new BigDecimal("68.921")),
					new ParameterValues(new BigDecimal("4.2"), 3, 3, new BigDecimal("74.088")),
					new ParameterValues(new BigDecimal("4.3"), 3, 3, new BigDecimal("79.507")),
					new ParameterValues(new BigDecimal("4.4"), 3, 3, new BigDecimal("85.184")),
					new ParameterValues(new BigDecimal("4.5"), 3, 3, new BigDecimal("91.125")),
					new ParameterValues(new BigDecimal("4.6"), 3, 3, new BigDecimal("97.336")),
					new ParameterValues(new BigDecimal("4.7"), 3, 3, new BigDecimal("103.823")),
					new ParameterValues(new BigDecimal("4.8"), 3, 3, new BigDecimal("110.592")),
					new ParameterValues(new BigDecimal("4.9"), 3, 3, new BigDecimal("117.649")),

					new ParameterValues(new BigDecimal("5.0"), 3, 3, new BigDecimal("125.000")),
					new ParameterValues(new BigDecimal("5.1"), 3, 3, new BigDecimal("132.651")),
					new ParameterValues(new BigDecimal("5.2"), 3, 3, new BigDecimal("140.608")),
					new ParameterValues(new BigDecimal("5.3"), 3, 3, new BigDecimal("148.877")),
					new ParameterValues(new BigDecimal("5.4"), 3, 3, new BigDecimal("157.464")),
					new ParameterValues(new BigDecimal("5.5"), 3, 3, new BigDecimal("166.375")),
					new ParameterValues(new BigDecimal("5.6"), 3, 3, new BigDecimal("175.616")),
					new ParameterValues(new BigDecimal("5.7"), 3, 3, new BigDecimal("185.193")),
					new ParameterValues(new BigDecimal("5.8"), 3, 3, new BigDecimal("195.112")),
					new ParameterValues(new BigDecimal("5.9"), 3, 3, new BigDecimal("205.379")),

					new ParameterValues(new BigDecimal("6.0"), 3, 3, new BigDecimal("216.000")),
					new ParameterValues(new BigDecimal("6.1"), 3, 3, new BigDecimal("226.981")),
					new ParameterValues(new BigDecimal("6.2"), 3, 3, new BigDecimal("238.328")),
					new ParameterValues(new BigDecimal("6.3"), 3, 3, new BigDecimal("250.047")),
					new ParameterValues(new BigDecimal("6.4"), 3, 3, new BigDecimal("262.144")),
					new ParameterValues(new BigDecimal("6.5"), 3, 3, new BigDecimal("274.625")),
					new ParameterValues(new BigDecimal("6.6"), 3, 3, new BigDecimal("287.496")),
					new ParameterValues(new BigDecimal("6.7"), 3, 3, new BigDecimal("300.763")),
					new ParameterValues(new BigDecimal("6.8"), 3, 3, new BigDecimal("314.432")),
					new ParameterValues(new BigDecimal("6.9"), 3, 3, new BigDecimal("328.509")),

					new ParameterValues(new BigDecimal("7.0"), 3, 3, new BigDecimal("343.000")),
					new ParameterValues(new BigDecimal("7.1"), 3, 3, new BigDecimal("357.911")),
					new ParameterValues(new BigDecimal("7.2"), 3, 3, new BigDecimal("373.248")),
					new ParameterValues(new BigDecimal("7.3"), 3, 3, new BigDecimal("389.017")),
					new ParameterValues(new BigDecimal("7.4"), 3, 3, new BigDecimal("405.224")),
					new ParameterValues(new BigDecimal("7.5"), 3, 3, new BigDecimal("421.875")),
					new ParameterValues(new BigDecimal("7.6"), 3, 3, new BigDecimal("438.976")),
					new ParameterValues(new BigDecimal("7.7"), 3, 3, new BigDecimal("456.533")),
					new ParameterValues(new BigDecimal("7.8"), 3, 3, new BigDecimal("474.552")),
					new ParameterValues(new BigDecimal("7.9"), 3, 3, new BigDecimal("493.039")),

					new ParameterValues(new BigDecimal("8.0"), 3, 3, new BigDecimal("512.000")),
					new ParameterValues(new BigDecimal("8.1"), 3, 3, new BigDecimal("531.441")),
					new ParameterValues(new BigDecimal("8.2"), 3, 3, new BigDecimal("551.368")),
					new ParameterValues(new BigDecimal("8.3"), 3, 3, new BigDecimal("571.787")),
					new ParameterValues(new BigDecimal("8.4"), 3, 3, new BigDecimal("592.704")),
					new ParameterValues(new BigDecimal("8.5"), 3, 3, new BigDecimal("614.125")),
					new ParameterValues(new BigDecimal("8.6"), 3, 3, new BigDecimal("636.056")),
					new ParameterValues(new BigDecimal("8.7"), 3, 3, new BigDecimal("658.503")),
					new ParameterValues(new BigDecimal("8.8"), 3, 3, new BigDecimal("681.472")),
					new ParameterValues(new BigDecimal("8.9"), 3, 3, new BigDecimal("704.969")),

					new ParameterValues(new BigDecimal("9.0"), 3, 3, new BigDecimal("729.000")),
					new ParameterValues(new BigDecimal("9.1"), 3, 3, new BigDecimal("753.571")),
					new ParameterValues(new BigDecimal("9.2"), 3, 3, new BigDecimal("778.688")),
					new ParameterValues(new BigDecimal("9.3"), 3, 3, new BigDecimal("804.357")),
					new ParameterValues(new BigDecimal("9.4"), 3, 3, new BigDecimal("830.584")),
					new ParameterValues(new BigDecimal("9.5"), 3, 3, new BigDecimal("857.375")),
					new ParameterValues(new BigDecimal("9.6"), 3, 3, new BigDecimal("884.736")),
					new ParameterValues(new BigDecimal("9.7"), 3, 3, new BigDecimal("912.673")),
					new ParameterValues(new BigDecimal("9.8"), 3, 3, new BigDecimal("941.192")),
					new ParameterValues(new BigDecimal("9.9"), 3, 3, new BigDecimal("970.299")),

					// Negative exponent tests
					new ParameterValues(new BigDecimal("-9.9"), -3, 6, new BigDecimal("-0.001031")),
					new ParameterValues(new BigDecimal("-9.8"), -3, 6, new BigDecimal("-0.001062")),
					new ParameterValues(new BigDecimal("-9.7"), -3, 6, new BigDecimal("-0.001096")),
					new ParameterValues(new BigDecimal("-9.6"), -3, 6, new BigDecimal("-0.001130")),
					new ParameterValues(new BigDecimal("-9.5"), -3, 6, new BigDecimal("-0.001166")),
					new ParameterValues(new BigDecimal("-9.4"), -3, 6, new BigDecimal("-0.001204")),
					new ParameterValues(new BigDecimal("-9.3"), -3, 6, new BigDecimal("-0.001243")),
					new ParameterValues(new BigDecimal("-9.2"), -3, 6, new BigDecimal("-0.001284")),
					new ParameterValues(new BigDecimal("-9.1"), -3, 6, new BigDecimal("-0.001327")),
					new ParameterValues(new BigDecimal("-9.0"), -3, 6, new BigDecimal("-0.001372")),

					new ParameterValues(new BigDecimal("-8.9"), -3, 6, new BigDecimal("-0.001419")),
					new ParameterValues(new BigDecimal("-8.8"), -3, 6, new BigDecimal("-0.001467")),
					new ParameterValues(new BigDecimal("-8.7"), -3, 6, new BigDecimal("-0.001519")),
					new ParameterValues(new BigDecimal("-8.6"), -3, 6, new BigDecimal("-0.001572")),
					new ParameterValues(new BigDecimal("-8.5"), -3, 6, new BigDecimal("-0.001628")),
					new ParameterValues(new BigDecimal("-8.4"), -3, 6, new BigDecimal("-0.001687")),
					new ParameterValues(new BigDecimal("-8.3"), -3, 6, new BigDecimal("-0.001749")),
					new ParameterValues(new BigDecimal("-8.2"), -3, 6, new BigDecimal("-0.001814")),
					new ParameterValues(new BigDecimal("-8.1"), -3, 6, new BigDecimal("-0.001882")),
					new ParameterValues(new BigDecimal("-8.0"), -3, 6, new BigDecimal("-0.001953")),

					new ParameterValues(new BigDecimal("-7.9"), -3, 6, new BigDecimal("-0.002028")),
					new ParameterValues(new BigDecimal("-7.8"), -3, 6, new BigDecimal("-0.002107")),
					new ParameterValues(new BigDecimal("-7.7"), -3, 6, new BigDecimal("-0.002190")),
					new ParameterValues(new BigDecimal("-7.6"), -3, 6, new BigDecimal("-0.002278")),
					new ParameterValues(new BigDecimal("-7.5"), -3, 6, new BigDecimal("-0.002370")),
					new ParameterValues(new BigDecimal("-7.4"), -3, 6, new BigDecimal("-0.002468")),
					new ParameterValues(new BigDecimal("-7.3"), -3, 6, new BigDecimal("-0.002571")),
					new ParameterValues(new BigDecimal("-7.2"), -3, 6, new BigDecimal("-0.002679")),
					new ParameterValues(new BigDecimal("-7.1"), -3, 6, new BigDecimal("-0.002794")),
					new ParameterValues(new BigDecimal("-7.0"), -3, 6, new BigDecimal("-0.002915")),

					new ParameterValues(new BigDecimal("-6.9"), -3, 6, new BigDecimal("-0.003044")),
					new ParameterValues(new BigDecimal("-6.8"), -3, 6, new BigDecimal("-0.003180")),
					new ParameterValues(new BigDecimal("-6.7"), -3, 6, new BigDecimal("-0.003325")),
					new ParameterValues(new BigDecimal("-6.6"), -3, 6, new BigDecimal("-0.003478")),
					new ParameterValues(new BigDecimal("-6.5"), -3, 6, new BigDecimal("-0.003641")),
					new ParameterValues(new BigDecimal("-6.4"), -3, 6, new BigDecimal("-0.003815")),
					new ParameterValues(new BigDecimal("-6.3"), -3, 6, new BigDecimal("-0.003999")),
					new ParameterValues(new BigDecimal("-6.2"), -3, 6, new BigDecimal("-0.004196")),
					new ParameterValues(new BigDecimal("-6.1"), -3, 6, new BigDecimal("-0.004406")),
					new ParameterValues(new BigDecimal("-6.0"), -3, 6, new BigDecimal("-0.004630")),

					new ParameterValues(new BigDecimal("-5.9"), -3, 6, new BigDecimal("-0.004869")),
					new ParameterValues(new BigDecimal("-5.8"), -3, 6, new BigDecimal("-0.005125")),
					new ParameterValues(new BigDecimal("-5.7"), -3, 6, new BigDecimal("-0.005400")),
					new ParameterValues(new BigDecimal("-5.6"), -3, 6, new BigDecimal("-0.005694")),
					new ParameterValues(new BigDecimal("-5.5"), -3, 6, new BigDecimal("-0.006011")),
					new ParameterValues(new BigDecimal("-5.4"), -3, 6, new BigDecimal("-0.006351")),
					new ParameterValues(new BigDecimal("-5.3"), -3, 6, new BigDecimal("-0.006717")),
					new ParameterValues(new BigDecimal("-5.2"), -3, 6, new BigDecimal("-0.007112")),
					new ParameterValues(new BigDecimal("-5.1"), -3, 6, new BigDecimal("-0.007539")),
					new ParameterValues(new BigDecimal("-5.0"), -3, 6, new BigDecimal("-0.008000")),

					new ParameterValues(new BigDecimal("-4.9"), -3, 6, new BigDecimal("-0.008500")),
					new ParameterValues(new BigDecimal("-4.8"), -3, 6, new BigDecimal("-0.009042")),
					new ParameterValues(new BigDecimal("-4.7"), -3, 6, new BigDecimal("-0.009632")),
					new ParameterValues(new BigDecimal("-4.6"), -3, 6, new BigDecimal("-0.010274")),
					new ParameterValues(new BigDecimal("-4.5"), -3, 6, new BigDecimal("-0.010974")),
					new ParameterValues(new BigDecimal("-4.4"), -3, 6, new BigDecimal("-0.011739")),
					new ParameterValues(new BigDecimal("-4.3"), -3, 6, new BigDecimal("-0.012578")),
					new ParameterValues(new BigDecimal("-4.2"), -3, 6, new BigDecimal("-0.013497")),
					new ParameterValues(new BigDecimal("-4.1"), -3, 6, new BigDecimal("-0.014509")),
					new ParameterValues(new BigDecimal("-4.0"), -3, 6, new BigDecimal("-0.015625")),

					new ParameterValues(new BigDecimal("-3.9"), -3, 6, new BigDecimal("-0.016858")),
					new ParameterValues(new BigDecimal("-3.8"), -3, 6, new BigDecimal("-0.018224")),
					new ParameterValues(new BigDecimal("-3.7"), -3, 6, new BigDecimal("-0.019742")),
					new ParameterValues(new BigDecimal("-3.6"), -3, 6, new BigDecimal("-0.021433")),
					new ParameterValues(new BigDecimal("-3.5"), -3, 6, new BigDecimal("-0.023324")),
					new ParameterValues(new BigDecimal("-3.4"), -3, 6, new BigDecimal("-0.025443")),
					new ParameterValues(new BigDecimal("-3.3"), -3, 6, new BigDecimal("-0.027826")),
					new ParameterValues(new BigDecimal("-3.2"), -3, 6, new BigDecimal("-0.030518")),
					new ParameterValues(new BigDecimal("-3.1"), -3, 6, new BigDecimal("-0.033567")),
					new ParameterValues(new BigDecimal("-3.0"), -3, 6, new BigDecimal("-0.037037")),

					new ParameterValues(new BigDecimal("-2.9"), -3, 6, new BigDecimal("-0.041002")),
					new ParameterValues(new BigDecimal("-2.8"), -3, 6, new BigDecimal("-0.045554")),
					new ParameterValues(new BigDecimal("-2.7"), -3, 6, new BigDecimal("-0.050805")),
					new ParameterValues(new BigDecimal("-2.6"), -3, 6, new BigDecimal("-0.056896")),
					new ParameterValues(new BigDecimal("-2.5"), -3, 6, new BigDecimal("-0.064000")),
					new ParameterValues(new BigDecimal("-2.4"), -3, 6, new BigDecimal("-0.072338")),
					new ParameterValues(new BigDecimal("-2.3"), -3, 6, new BigDecimal("-0.082190")),
					new ParameterValues(new BigDecimal("-2.2"), -3, 6, new BigDecimal("-0.093914")),
					new ParameterValues(new BigDecimal("-2.1"), -3, 6, new BigDecimal("-0.107980")),
					new ParameterValues(new BigDecimal("-2.0"), -3, 6, new BigDecimal("-0.125000")),

					new ParameterValues(new BigDecimal("-1.9"), -3, 6, new BigDecimal("-0.145794")),
					new ParameterValues(new BigDecimal("-1.8"), -3, 6, new BigDecimal("-0.171468")),
					new ParameterValues(new BigDecimal("-1.7"), -3, 6, new BigDecimal("-0.203542")),
					new ParameterValues(new BigDecimal("-1.6"), -3, 6, new BigDecimal("-0.244141")),
					new ParameterValues(new BigDecimal("-1.5"), -3, 6, new BigDecimal("-0.296296")),
					new ParameterValues(new BigDecimal("-1.4"), -3, 6, new BigDecimal("-0.364431")),
					new ParameterValues(new BigDecimal("-1.3"), -3, 6, new BigDecimal("-0.455166")),
					new ParameterValues(new BigDecimal("-1.2"), -3, 6, new BigDecimal("-0.578704")),
					new ParameterValues(new BigDecimal("-1.1"), -3, 6, new BigDecimal("-0.751315")),
					new ParameterValues(new BigDecimal("-1.0"), -3, 6, new BigDecimal("-1.000000")),

					new ParameterValues(new BigDecimal("-0.9"), -3, 6, new BigDecimal("-1.371742")),
					new ParameterValues(new BigDecimal("-0.8"), -3, 6, new BigDecimal("-1.953125")),
					new ParameterValues(new BigDecimal("-0.7"), -3, 6, new BigDecimal("-2.915452")),
					new ParameterValues(new BigDecimal("-0.6"), -3, 6, new BigDecimal("-4.629630")),
					new ParameterValues(new BigDecimal("-0.5"), -3, 6, new BigDecimal("-8.000000")),
					new ParameterValues(new BigDecimal("-0.4"), -3, 6, new BigDecimal("-15.625000")),
					new ParameterValues(new BigDecimal("-0.3"), -3, 6, new BigDecimal("-37.037037")),
					new ParameterValues(new BigDecimal("-0.2"), -3, 6, new BigDecimal("-125.000000")),
					new ParameterValues(new BigDecimal("-0.1"), -3, 6, new BigDecimal("-1000.000000")),

					// NOTE: Power with a negative exponent is not defined for 0

					new ParameterValues(new BigDecimal("0.1"), -3, 6, new BigDecimal("1000.000000")),
					new ParameterValues(new BigDecimal("0.2"), -3, 6, new BigDecimal("125.000000")),
					new ParameterValues(new BigDecimal("0.3"), -3, 6, new BigDecimal("37.037037")),
					new ParameterValues(new BigDecimal("0.4"), -3, 6, new BigDecimal("15.625000")),
					new ParameterValues(new BigDecimal("0.5"), -3, 6, new BigDecimal("8.000000")),
					new ParameterValues(new BigDecimal("0.6"), -3, 6, new BigDecimal("4.629630")),
					new ParameterValues(new BigDecimal("0.7"), -3, 6, new BigDecimal("2.915452")),
					new ParameterValues(new BigDecimal("0.8"), -3, 6, new BigDecimal("1.953125")),
					new ParameterValues(new BigDecimal("0.9"), -3, 6, new BigDecimal("1.371742")),

					new ParameterValues(new BigDecimal("1.0"), -3, 6, new BigDecimal("1.000000")),
					new ParameterValues(new BigDecimal("1.1"), -3, 6, new BigDecimal("0.751315")),
					new ParameterValues(new BigDecimal("1.2"), -3, 6, new BigDecimal("0.578704")),
					new ParameterValues(new BigDecimal("1.3"), -3, 6, new BigDecimal("0.455166")),
					new ParameterValues(new BigDecimal("1.4"), -3, 6, new BigDecimal("0.364431")),
					new ParameterValues(new BigDecimal("1.5"), -3, 6, new BigDecimal("0.296296")),
					new ParameterValues(new BigDecimal("1.6"), -3, 6, new BigDecimal("0.244141")),
					new ParameterValues(new BigDecimal("1.7"), -3, 6, new BigDecimal("0.203542")),
					new ParameterValues(new BigDecimal("1.8"), -3, 6, new BigDecimal("0.171468")),
					new ParameterValues(new BigDecimal("1.9"), -3, 6, new BigDecimal("0.145794")),

					new ParameterValues(new BigDecimal("2.0"), -3, 6, new BigDecimal("0.125000")),
					new ParameterValues(new BigDecimal("2.1"), -3, 6, new BigDecimal("0.107980")),
					new ParameterValues(new BigDecimal("2.2"), -3, 6, new BigDecimal("0.093914")),
					new ParameterValues(new BigDecimal("2.3"), -3, 6, new BigDecimal("0.082190")),
					new ParameterValues(new BigDecimal("2.4"), -3, 6, new BigDecimal("0.072338")),
					new ParameterValues(new BigDecimal("2.5"), -3, 6, new BigDecimal("0.064000")),
					new ParameterValues(new BigDecimal("2.6"), -3, 6, new BigDecimal("0.056896")),
					new ParameterValues(new BigDecimal("2.7"), -3, 6, new BigDecimal("0.050805")),
					new ParameterValues(new BigDecimal("2.8"), -3, 6, new BigDecimal("0.045554")),
					new ParameterValues(new BigDecimal("2.9"), -3, 6, new BigDecimal("0.041002")),

					new ParameterValues(new BigDecimal("3.0"), -3, 6, new BigDecimal("0.037037")),
					new ParameterValues(new BigDecimal("3.1"), -3, 6, new BigDecimal("0.033567")),
					new ParameterValues(new BigDecimal("3.2"), -3, 6, new BigDecimal("0.030518")),
					new ParameterValues(new BigDecimal("3.3"), -3, 6, new BigDecimal("0.027826")),
					new ParameterValues(new BigDecimal("3.4"), -3, 6, new BigDecimal("0.025443")),
					new ParameterValues(new BigDecimal("3.5"), -3, 6, new BigDecimal("0.023324")),
					new ParameterValues(new BigDecimal("3.6"), -3, 6, new BigDecimal("0.021433")),
					new ParameterValues(new BigDecimal("3.7"), -3, 6, new BigDecimal("0.019742")),
					new ParameterValues(new BigDecimal("3.8"), -3, 6, new BigDecimal("0.018224")),
					new ParameterValues(new BigDecimal("3.9"), -3, 6, new BigDecimal("0.016858")),

					new ParameterValues(new BigDecimal("4.0"), -3, 6, new BigDecimal("0.015625")),
					new ParameterValues(new BigDecimal("4.1"), -3, 6, new BigDecimal("0.014509")),
					new ParameterValues(new BigDecimal("4.2"), -3, 6, new BigDecimal("0.013497")),
					new ParameterValues(new BigDecimal("4.3"), -3, 6, new BigDecimal("0.012578")),
					new ParameterValues(new BigDecimal("4.4"), -3, 6, new BigDecimal("0.011739")),
					new ParameterValues(new BigDecimal("4.5"), -3, 6, new BigDecimal("0.010974")),
					new ParameterValues(new BigDecimal("4.6"), -3, 6, new BigDecimal("0.010274")),
					new ParameterValues(new BigDecimal("4.7"), -3, 6, new BigDecimal("0.009632")),
					new ParameterValues(new BigDecimal("4.8"), -3, 6, new BigDecimal("0.009042")),
					new ParameterValues(new BigDecimal("4.9"), -3, 6, new BigDecimal("0.008500")),

					new ParameterValues(new BigDecimal("5.0"), -3, 6, new BigDecimal("0.008000")),
					new ParameterValues(new BigDecimal("5.1"), -3, 6, new BigDecimal("0.007539")),
					new ParameterValues(new BigDecimal("5.2"), -3, 6, new BigDecimal("0.007112")),
					new ParameterValues(new BigDecimal("5.3"), -3, 6, new BigDecimal("0.006717")),
					new ParameterValues(new BigDecimal("5.4"), -3, 6, new BigDecimal("0.006351")),
					new ParameterValues(new BigDecimal("5.5"), -3, 6, new BigDecimal("0.006011")),
					new ParameterValues(new BigDecimal("5.6"), -3, 6, new BigDecimal("0.005694")),
					new ParameterValues(new BigDecimal("5.7"), -3, 6, new BigDecimal("0.005400")),
					new ParameterValues(new BigDecimal("5.8"), -3, 6, new BigDecimal("0.005125")),
					new ParameterValues(new BigDecimal("5.9"), -3, 6, new BigDecimal("0.004869")),

					new ParameterValues(new BigDecimal("6.0"), -3, 6, new BigDecimal("0.004630")),
					new ParameterValues(new BigDecimal("6.1"), -3, 6, new BigDecimal("0.004406")),
					new ParameterValues(new BigDecimal("6.2"), -3, 6, new BigDecimal("0.004196")),
					new ParameterValues(new BigDecimal("6.3"), -3, 6, new BigDecimal("0.003999")),
					new ParameterValues(new BigDecimal("6.4"), -3, 6, new BigDecimal("0.003815")),
					new ParameterValues(new BigDecimal("6.5"), -3, 6, new BigDecimal("0.003641")),
					new ParameterValues(new BigDecimal("6.6"), -3, 6, new BigDecimal("0.003478")),
					new ParameterValues(new BigDecimal("6.7"), -3, 6, new BigDecimal("0.003325")),
					new ParameterValues(new BigDecimal("6.8"), -3, 6, new BigDecimal("0.003180")),
					new ParameterValues(new BigDecimal("6.9"), -3, 6, new BigDecimal("0.003044")),

					new ParameterValues(new BigDecimal("7.0"), -3, 6, new BigDecimal("0.002915")),
					new ParameterValues(new BigDecimal("7.1"), -3, 6, new BigDecimal("0.002794")),
					new ParameterValues(new BigDecimal("7.2"), -3, 6, new BigDecimal("0.002679")),
					new ParameterValues(new BigDecimal("7.3"), -3, 6, new BigDecimal("0.002571")),
					new ParameterValues(new BigDecimal("7.4"), -3, 6, new BigDecimal("0.002468")),
					new ParameterValues(new BigDecimal("7.5"), -3, 6, new BigDecimal("0.002370")),
					new ParameterValues(new BigDecimal("7.6"), -3, 6, new BigDecimal("0.002278")),
					new ParameterValues(new BigDecimal("7.7"), -3, 6, new BigDecimal("0.002190")),
					new ParameterValues(new BigDecimal("7.8"), -3, 6, new BigDecimal("0.002107")),
					new ParameterValues(new BigDecimal("7.9"), -3, 6, new BigDecimal("0.002028")),

					new ParameterValues(new BigDecimal("8.0"), -3, 6, new BigDecimal("0.001953")),
					new ParameterValues(new BigDecimal("8.1"), -3, 6, new BigDecimal("0.001882")),
					new ParameterValues(new BigDecimal("8.2"), -3, 6, new BigDecimal("0.001814")),
					new ParameterValues(new BigDecimal("8.3"), -3, 6, new BigDecimal("0.001749")),
					new ParameterValues(new BigDecimal("8.4"), -3, 6, new BigDecimal("0.001687")),
					new ParameterValues(new BigDecimal("8.5"), -3, 6, new BigDecimal("0.001628")),
					new ParameterValues(new BigDecimal("8.6"), -3, 6, new BigDecimal("0.001572")),
					new ParameterValues(new BigDecimal("8.7"), -3, 6, new BigDecimal("0.001519")),
					new ParameterValues(new BigDecimal("8.8"), -3, 6, new BigDecimal("0.001467")),
					new ParameterValues(new BigDecimal("8.9"), -3, 6, new BigDecimal("0.001419")),

					new ParameterValues(new BigDecimal("9.0"), -3, 6, new BigDecimal("0.001372")),
					new ParameterValues(new BigDecimal("9.1"), -3, 6, new BigDecimal("0.001327")),
					new ParameterValues(new BigDecimal("9.2"), -3, 6, new BigDecimal("0.001284")),
					new ParameterValues(new BigDecimal("9.3"), -3, 6, new BigDecimal("0.001243")),
					new ParameterValues(new BigDecimal("9.4"), -3, 6, new BigDecimal("0.001204")),
					new ParameterValues(new BigDecimal("9.5"), -3, 6, new BigDecimal("0.001166")),
					new ParameterValues(new BigDecimal("9.6"), -3, 6, new BigDecimal("0.001130")),
					new ParameterValues(new BigDecimal("9.7"), -3, 6, new BigDecimal("0.001096")),
					new ParameterValues(new BigDecimal("9.8"), -3, 6, new BigDecimal("0.001062")),
					new ParameterValues(new BigDecimal("9.9"), -3, 6, new BigDecimal("0.001031")),
			});
		}

		@Test
		public void test() {
			BigDecimal result = pow(x, n, scale);
			BigDecimal scaledResult = result.setScale(expectedValue.scale(), RoundingMode.HALF_UP);

			assertThat(scaledResult, is(equalTo(expectedValue)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final BigDecimal expectedValue;

			private final long n;

			private final int scale;

			private final BigDecimal x;


			public ParameterValues(BigDecimal x, long n, int scale, BigDecimal expectedValue) {
				this.x = x;
				this.n = n;
				this.scale = scale;
				this.expectedValue = expectedValue;
			}


			public BigDecimal getExpectedValue() {
				return expectedValue;
			}

			public long getN() {
				return n;
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
	public static class ParameterizedRootTest {

		private final BigDecimal expectedValue;

		private final int n;

		private final int scale;

		private final BigDecimal x;


		public ParameterizedRootTest(ParameterValues values) {
			expectedValue = values.getExpectedValue();
			n = values.getN();
			scale = values.getScale();
			x = values.getX();
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					// Precision tests
					new ParameterValues(THREE, 2,  0, new BigDecimal("2")),
					new ParameterValues(THREE, 2,  1, new BigDecimal("1.7")),
					new ParameterValues(THREE, 2,  2, new BigDecimal("1.73")),
					new ParameterValues(THREE, 2,  3, new BigDecimal("1.732")),
					new ParameterValues(THREE, 2,  4, new BigDecimal("1.7321")),
					new ParameterValues(THREE, 2,  5, new BigDecimal("1.73205")),
					new ParameterValues(THREE, 2,  5, new BigDecimal("1.73205")),
					new ParameterValues(THREE, 2,  6, new BigDecimal("1.732051")),
					new ParameterValues(THREE, 2,  7, new BigDecimal("1.7320508")),
					new ParameterValues(THREE, 2,  8, new BigDecimal("1.73205081")),
					new ParameterValues(THREE, 2,  9, new BigDecimal("1.732050808")),
					new ParameterValues(THREE, 2, 10, new BigDecimal("1.7320508076")),
					new ParameterValues(THREE, 2, 11, new BigDecimal("1.73205080757")),
					new ParameterValues(THREE, 2, 12, new BigDecimal("1.732050807569")),
					new ParameterValues(THREE, 2, 13, new BigDecimal("1.7320508075689")),
					new ParameterValues(THREE, 2, 14, new BigDecimal("1.73205080756888")),
					new ParameterValues(THREE, 2, 15, new BigDecimal("1.732050807568877")),
					new ParameterValues(THREE, 2, 16, new BigDecimal("1.7320508075688773")),
					new ParameterValues(THREE, 2, 17, new BigDecimal("1.73205080756887729")),
					new ParameterValues(THREE, 2, 18, new BigDecimal("1.732050807568877294")),
					new ParameterValues(THREE, 2, 19, new BigDecimal("1.7320508075688772935")),
					new ParameterValues(THREE, 2, 20, new BigDecimal("1.73205080756887729353")),
					new ParameterValues(THREE, 2, 21, new BigDecimal("1.732050807568877293527")),
					new ParameterValues(THREE, 2, 22, new BigDecimal("1.7320508075688772935274")),
					new ParameterValues(THREE, 2, 23, new BigDecimal("1.73205080756887729352745")),
					new ParameterValues(THREE, 2, 24, new BigDecimal("1.732050807568877293527446")),
					new ParameterValues(THREE, 2, 25, new BigDecimal("1.7320508075688772935274463")),
					new ParameterValues(THREE, 2, 26, new BigDecimal("1.73205080756887729352744634")),
					new ParameterValues(THREE, 2, 27, new BigDecimal("1.732050807568877293527446342")),
					new ParameterValues(THREE, 2, 28, new BigDecimal("1.7320508075688772935274463415")),
					new ParameterValues(THREE, 2, 29, new BigDecimal("1.73205080756887729352744634151")),
					new ParameterValues(THREE, 2, 30, new BigDecimal("1.732050807568877293527446341506")),
					new ParameterValues(THREE, 2, 31, new BigDecimal("1.7320508075688772935274463415059")),
					new ParameterValues(THREE, 2, 32, new BigDecimal("1.73205080756887729352744634150587")),
					new ParameterValues(THREE, 2, 33, new BigDecimal("1.732050807568877293527446341505872")),
					new ParameterValues(THREE, 2, 34, new BigDecimal("1.7320508075688772935274463415058724")),
					new ParameterValues(THREE, 2, 35, new BigDecimal("1.73205080756887729352744634150587237")),
					new ParameterValues(THREE, 2, 36, new BigDecimal("1.732050807568877293527446341505872367")),
					new ParameterValues(THREE, 2, 37, new BigDecimal("1.7320508075688772935274463415058723669")),
					new ParameterValues(THREE, 2, 38, new BigDecimal("1.73205080756887729352744634150587236694")),
					new ParameterValues(THREE, 2, 39, new BigDecimal("1.732050807568877293527446341505872366943")),
					new ParameterValues(THREE, 2, 40, new BigDecimal("1.7320508075688772935274463415058723669428")),
					new ParameterValues(THREE, 2, 41, new BigDecimal("1.73205080756887729352744634150587236694281")),
					new ParameterValues(THREE, 2, 42, new BigDecimal("1.732050807568877293527446341505872366942805")),
					new ParameterValues(THREE, 2, 43, new BigDecimal("1.7320508075688772935274463415058723669428053")),
					new ParameterValues(THREE, 2, 44, new BigDecimal("1.73205080756887729352744634150587236694280525")),
					new ParameterValues(THREE, 2, 45, new BigDecimal("1.732050807568877293527446341505872366942805254")),
					new ParameterValues(THREE, 2, 46, new BigDecimal("1.7320508075688772935274463415058723669428052538")),
					new ParameterValues(THREE, 2, 47, new BigDecimal("1.73205080756887729352744634150587236694280525381")),
					new ParameterValues(THREE, 2, 48, new BigDecimal("1.732050807568877293527446341505872366942805253810")),
					new ParameterValues(THREE, 2, 49, new BigDecimal("1.7320508075688772935274463415058723669428052538104")),
					new ParameterValues(THREE, 2, 50, new BigDecimal("1.73205080756887729352744634150587236694280525381038")),
					new ParameterValues(THREE, 2, 51, new BigDecimal("1.732050807568877293527446341505872366942805253810381")),
					new ParameterValues(THREE, 2, 52, new BigDecimal("1.7320508075688772935274463415058723669428052538103806")),
					new ParameterValues(THREE, 2, 53, new BigDecimal("1.73205080756887729352744634150587236694280525381038063")),
					new ParameterValues(THREE, 2, 54, new BigDecimal("1.732050807568877293527446341505872366942805253810380628")),
					new ParameterValues(THREE, 2, 55, new BigDecimal("1.7320508075688772935274463415058723669428052538103806281")),
					new ParameterValues(THREE, 2, 56, new BigDecimal("1.73205080756887729352744634150587236694280525381038062806")),
					new ParameterValues(THREE, 2, 57, new BigDecimal("1.732050807568877293527446341505872366942805253810380628056")),
					new ParameterValues(THREE, 2, 58, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558")),
					new ParameterValues(THREE, 2, 59, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805581")),
					new ParameterValues(THREE, 2, 60, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055807")),
					new ParameterValues(THREE, 2, 61, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558070")),
					new ParameterValues(THREE, 2, 62, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580698")),
					new ParameterValues(THREE, 2, 63, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979")),
					new ParameterValues(THREE, 2, 64, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069795")),
					new ParameterValues(THREE, 2, 65, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945")),
					new ParameterValues(THREE, 2, 66, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979452")),
					new ParameterValues(THREE, 2, 67, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519")),
					new ParameterValues(THREE, 2, 68, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193")),
					new ParameterValues(THREE, 2, 69, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933")),
					new ParameterValues(THREE, 2, 70, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330")),
					new ParameterValues(THREE, 2, 71, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193302")),
					new ParameterValues(THREE, 2, 72, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933017")),
					new ParameterValues(THREE, 2, 73, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330169")),
					new ParameterValues(THREE, 2, 74, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193301691")),
					new ParameterValues(THREE, 2, 75, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933016909")),
					new ParameterValues(THREE, 2, 76, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330169088")),
					new ParameterValues(THREE, 2, 77, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193301690880")),
					new ParameterValues(THREE, 2, 78, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933016908800")),
					new ParameterValues(THREE, 2, 79, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330169088000")),
					new ParameterValues(THREE, 2, 80, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193301690880004")),
					new ParameterValues(THREE, 2, 81, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933016908800037")),
					new ParameterValues(THREE, 2, 82, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330169088000371")),
					new ParameterValues(THREE, 2, 83, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193301690880003708")),
					new ParameterValues(THREE, 2, 84, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933016908800037081")),
					new ParameterValues(THREE, 2, 85, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330169088000370811")),
					new ParameterValues(THREE, 2, 86, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193301690880003708115")),
					new ParameterValues(THREE, 2, 87, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933016908800037081146")),
					new ParameterValues(THREE, 2, 88, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330169088000370811462")),
					new ParameterValues(THREE, 2, 89, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193301690880003708114619")),
					new ParameterValues(THREE, 2, 90, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933016908800037081146187")),
					new ParameterValues(THREE, 2, 91, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330169088000370811461868")),
					new ParameterValues(THREE, 2, 92, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193301690880003708114618676")),
					new ParameterValues(THREE, 2, 93, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933016908800037081146186757")),
					new ParameterValues(THREE, 2, 94, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330169088000370811461867572")),
					new ParameterValues(THREE, 2, 95, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193301690880003708114618675725")),
					new ParameterValues(THREE, 2, 96, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933016908800037081146186757249")),
					new ParameterValues(THREE, 2, 97, new BigDecimal("1.7320508075688772935274463415058723669428052538103806280558069794519330169088000370811461867572486")),
					new ParameterValues(THREE, 2, 98, new BigDecimal("1.73205080756887729352744634150587236694280525381038062805580697945193301690880003708114618675724858")),
					new ParameterValues(THREE, 2, 99, new BigDecimal("1.732050807568877293527446341505872366942805253810380628055806979451933016908800037081146186757248576")),

					// Values tests
					new ParameterValues(new BigDecimal("-9.9"), 3, 6, new BigDecimal("-2.147229")),
					new ParameterValues(new BigDecimal("-9.8"), 3, 6, new BigDecimal("-2.139975")),
					new ParameterValues(new BigDecimal("-9.7"), 3, 6, new BigDecimal("-2.132671")),
					new ParameterValues(new BigDecimal("-9.6"), 3, 6, new BigDecimal("-2.125317")),
					new ParameterValues(new BigDecimal("-9.5"), 3, 6, new BigDecimal("-2.117912")),
					new ParameterValues(new BigDecimal("-9.4"), 3, 6, new BigDecimal("-2.110454")),
					new ParameterValues(new BigDecimal("-9.3"), 3, 6, new BigDecimal("-2.102944")),
					new ParameterValues(new BigDecimal("-9.2"), 3, 6, new BigDecimal("-2.095379")),
					new ParameterValues(new BigDecimal("-9.1"), 3, 6, new BigDecimal("-2.087759")),
					new ParameterValues(new BigDecimal("-9.0"), 3, 6, new BigDecimal("-2.080084")),

					new ParameterValues(new BigDecimal("-8.9"), 3, 6, new BigDecimal("-2.072351")),
					new ParameterValues(new BigDecimal("-8.8"), 3, 6, new BigDecimal("-2.064560")),
					new ParameterValues(new BigDecimal("-8.7"), 3, 6, new BigDecimal("-2.056710")),
					new ParameterValues(new BigDecimal("-8.6"), 3, 6, new BigDecimal("-2.048800")),
					new ParameterValues(new BigDecimal("-8.5"), 3, 6, new BigDecimal("-2.040828")),
					new ParameterValues(new BigDecimal("-8.4"), 3, 6, new BigDecimal("-2.032793")),
					new ParameterValues(new BigDecimal("-8.3"), 3, 6, new BigDecimal("-2.024694")),
					new ParameterValues(new BigDecimal("-8.2"), 3, 6, new BigDecimal("-2.016530")),
					new ParameterValues(new BigDecimal("-8.1"), 3, 6, new BigDecimal("-2.008299")),
					new ParameterValues(new BigDecimal("-8.0"), 3, 6, new BigDecimal("-2.000000")),

					new ParameterValues(new BigDecimal("-7.9"), 3, 6, new BigDecimal("-1.991632")),
					new ParameterValues(new BigDecimal("-7.8"), 3, 6, new BigDecimal("-1.983192")),
					new ParameterValues(new BigDecimal("-7.7"), 3, 6, new BigDecimal("-1.974681")),
					new ParameterValues(new BigDecimal("-7.6"), 3, 6, new BigDecimal("-1.966095")),
					new ParameterValues(new BigDecimal("-7.5"), 3, 6, new BigDecimal("-1.957434")),
					new ParameterValues(new BigDecimal("-7.4"), 3, 6, new BigDecimal("-1.948695")),
					new ParameterValues(new BigDecimal("-7.3"), 3, 6, new BigDecimal("-1.939877")),
					new ParameterValues(new BigDecimal("-7.2"), 3, 6, new BigDecimal("-1.930979")),
					new ParameterValues(new BigDecimal("-7.1"), 3, 6, new BigDecimal("-1.921997")),
					new ParameterValues(new BigDecimal("-7.0"), 3, 6, new BigDecimal("-1.912931")),

					new ParameterValues(new BigDecimal("-6.9"), 3, 6, new BigDecimal("-1.903778")),
					new ParameterValues(new BigDecimal("-6.8"), 3, 6, new BigDecimal("-1.894536")),
					new ParameterValues(new BigDecimal("-6.7"), 3, 6, new BigDecimal("-1.885204")),
					new ParameterValues(new BigDecimal("-6.6"), 3, 6, new BigDecimal("-1.875777")),
					new ParameterValues(new BigDecimal("-6.5"), 3, 6, new BigDecimal("-1.866256")),
					new ParameterValues(new BigDecimal("-6.4"), 3, 6, new BigDecimal("-1.856636")),
					new ParameterValues(new BigDecimal("-6.3"), 3, 6, new BigDecimal("-1.846915")),
					new ParameterValues(new BigDecimal("-6.2"), 3, 6, new BigDecimal("-1.837091")),
					new ParameterValues(new BigDecimal("-6.1"), 3, 6, new BigDecimal("-1.827160")),
					new ParameterValues(new BigDecimal("-6.0"), 3, 6, new BigDecimal("-1.817121")),

					new ParameterValues(new BigDecimal("-5.9"), 3, 6, new BigDecimal("-1.806969")),
					new ParameterValues(new BigDecimal("-5.8"), 3, 6, new BigDecimal("-1.796702")),
					new ParameterValues(new BigDecimal("-5.7"), 3, 6, new BigDecimal("-1.786316")),
					new ParameterValues(new BigDecimal("-5.6"), 3, 6, new BigDecimal("-1.775808")),
					new ParameterValues(new BigDecimal("-5.5"), 3, 6, new BigDecimal("-1.765174")),
					new ParameterValues(new BigDecimal("-5.4"), 3, 6, new BigDecimal("-1.754411")),
					new ParameterValues(new BigDecimal("-5.3"), 3, 6, new BigDecimal("-1.743513")),
					new ParameterValues(new BigDecimal("-5.2"), 3, 6, new BigDecimal("-1.732478")),
					new ParameterValues(new BigDecimal("-5.1"), 3, 6, new BigDecimal("-1.721301")),
					new ParameterValues(new BigDecimal("-5.0"), 3, 6, new BigDecimal("-1.709976")),

					new ParameterValues(new BigDecimal("-4.9"), 3, 6, new BigDecimal("-1.698499")),
					new ParameterValues(new BigDecimal("-4.8"), 3, 6, new BigDecimal("-1.686865")),
					new ParameterValues(new BigDecimal("-4.7"), 3, 6, new BigDecimal("-1.675069")),
					new ParameterValues(new BigDecimal("-4.6"), 3, 6, new BigDecimal("-1.663103")),
					new ParameterValues(new BigDecimal("-4.5"), 3, 6, new BigDecimal("-1.650964")),
					new ParameterValues(new BigDecimal("-4.4"), 3, 6, new BigDecimal("-1.638643")),
					new ParameterValues(new BigDecimal("-4.3"), 3, 6, new BigDecimal("-1.626133")),
					new ParameterValues(new BigDecimal("-4.2"), 3, 6, new BigDecimal("-1.613429")),
					new ParameterValues(new BigDecimal("-4.1"), 3, 6, new BigDecimal("-1.600521")),
					new ParameterValues(new BigDecimal("-4.0"), 3, 6, new BigDecimal("-1.587401")),

					new ParameterValues(new BigDecimal("-3.9"), 3, 6, new BigDecimal("-1.574061")),
					new ParameterValues(new BigDecimal("-3.8"), 3, 6, new BigDecimal("-1.560491")),
					new ParameterValues(new BigDecimal("-3.7"), 3, 6, new BigDecimal("-1.546680")),
					new ParameterValues(new BigDecimal("-3.6"), 3, 6, new BigDecimal("-1.532619")),
					new ParameterValues(new BigDecimal("-3.5"), 3, 6, new BigDecimal("-1.518294")),
					new ParameterValues(new BigDecimal("-3.4"), 3, 6, new BigDecimal("-1.503695")),
					new ParameterValues(new BigDecimal("-3.3"), 3, 6, new BigDecimal("-1.488806")),
					new ParameterValues(new BigDecimal("-3.2"), 3, 6, new BigDecimal("-1.473613")),
					new ParameterValues(new BigDecimal("-3.1"), 3, 6, new BigDecimal("-1.458100")),
					new ParameterValues(new BigDecimal("-3.0"), 3, 6, new BigDecimal("-1.442250")),

					new ParameterValues(new BigDecimal("-2.9"), 3, 6, new BigDecimal("-1.426043")),
					new ParameterValues(new BigDecimal("-2.8"), 3, 6, new BigDecimal("-1.409460")),
					new ParameterValues(new BigDecimal("-2.7"), 3, 6, new BigDecimal("-1.392477")),
					new ParameterValues(new BigDecimal("-2.6"), 3, 6, new BigDecimal("-1.375069")),
					new ParameterValues(new BigDecimal("-2.5"), 3, 6, new BigDecimal("-1.357209")),
					new ParameterValues(new BigDecimal("-2.4"), 3, 6, new BigDecimal("-1.338866")),
					new ParameterValues(new BigDecimal("-2.3"), 3, 6, new BigDecimal("-1.320006")),
					new ParameterValues(new BigDecimal("-2.2"), 3, 6, new BigDecimal("-1.300591")),
					new ParameterValues(new BigDecimal("-2.1"), 3, 6, new BigDecimal("-1.280579")),
					new ParameterValues(new BigDecimal("-2.0"), 3, 6, new BigDecimal("-1.259921")),

					new ParameterValues(new BigDecimal("-1.9"), 3, 6, new BigDecimal("-1.238562")),
					new ParameterValues(new BigDecimal("-1.8"), 3, 6, new BigDecimal("-1.216440")),
					new ParameterValues(new BigDecimal("-1.7"), 3, 6, new BigDecimal("-1.193483")),
					new ParameterValues(new BigDecimal("-1.6"), 3, 6, new BigDecimal("-1.169607")),
					new ParameterValues(new BigDecimal("-1.5"), 3, 6, new BigDecimal("-1.144714")),
					new ParameterValues(new BigDecimal("-1.4"), 3, 6, new BigDecimal("-1.118689")),
					new ParameterValues(new BigDecimal("-1.3"), 3, 6, new BigDecimal("-1.091393")),
					new ParameterValues(new BigDecimal("-1.2"), 3, 6, new BigDecimal("-1.062659")),
					new ParameterValues(new BigDecimal("-1.1"), 3, 6, new BigDecimal("-1.032280")),
					new ParameterValues(new BigDecimal("-1.0"), 3, 6, new BigDecimal("-1.000000")),

					new ParameterValues(new BigDecimal("-0.9"), 3, 6, new BigDecimal("-0.965489")),
					new ParameterValues(new BigDecimal("-0.8"), 3, 6, new BigDecimal("-0.928318")),
					new ParameterValues(new BigDecimal("-0.7"), 3, 6, new BigDecimal("-0.887904")),
					new ParameterValues(new BigDecimal("-0.6"), 3, 6, new BigDecimal("-0.843433")),
					new ParameterValues(new BigDecimal("-0.5"), 3, 6, new BigDecimal("-0.793701")),
					new ParameterValues(new BigDecimal("-0.4"), 3, 6, new BigDecimal("-0.736806")),
					new ParameterValues(new BigDecimal("-0.3"), 3, 6, new BigDecimal("-0.669433")),
					new ParameterValues(new BigDecimal("-0.2"), 3, 6, new BigDecimal("-0.584804")),
					new ParameterValues(new BigDecimal("-0.1"), 3, 6, new BigDecimal("-0.464159")),

					new ParameterValues(new BigDecimal("0.0"), 3, 6, new BigDecimal("0.000000")),

					new ParameterValues(new BigDecimal("0.1"), 3, 6, new BigDecimal("0.464159")),
					new ParameterValues(new BigDecimal("0.2"), 3, 6, new BigDecimal("0.584804")),
					new ParameterValues(new BigDecimal("0.3"), 3, 6, new BigDecimal("0.669433")),
					new ParameterValues(new BigDecimal("0.4"), 3, 6, new BigDecimal("0.736806")),
					new ParameterValues(new BigDecimal("0.5"), 3, 6, new BigDecimal("0.793701")),
					new ParameterValues(new BigDecimal("0.6"), 3, 6, new BigDecimal("0.843433")),
					new ParameterValues(new BigDecimal("0.7"), 3, 6, new BigDecimal("0.887904")),
					new ParameterValues(new BigDecimal("0.8"), 3, 6, new BigDecimal("0.928318")),
					new ParameterValues(new BigDecimal("0.9"), 3, 6, new BigDecimal("0.965489")),

					new ParameterValues(new BigDecimal("1.0"), 3, 6, new BigDecimal("1.000000")),
					new ParameterValues(new BigDecimal("1.1"), 3, 6, new BigDecimal("1.032280")),
					new ParameterValues(new BigDecimal("1.2"), 3, 6, new BigDecimal("1.062659")),
					new ParameterValues(new BigDecimal("1.3"), 3, 6, new BigDecimal("1.091393")),
					new ParameterValues(new BigDecimal("1.4"), 3, 6, new BigDecimal("1.118689")),
					new ParameterValues(new BigDecimal("1.5"), 3, 6, new BigDecimal("1.144714")),
					new ParameterValues(new BigDecimal("1.6"), 3, 6, new BigDecimal("1.169607")),
					new ParameterValues(new BigDecimal("1.7"), 3, 6, new BigDecimal("1.193483")),
					new ParameterValues(new BigDecimal("1.8"), 3, 6, new BigDecimal("1.216440")),
					new ParameterValues(new BigDecimal("1.9"), 3, 6, new BigDecimal("1.238562")),

					new ParameterValues(new BigDecimal("2.0"), 3, 6, new BigDecimal("1.259921")),
					new ParameterValues(new BigDecimal("2.1"), 3, 6, new BigDecimal("1.280579")),
					new ParameterValues(new BigDecimal("2.2"), 3, 6, new BigDecimal("1.300591")),
					new ParameterValues(new BigDecimal("2.3"), 3, 6, new BigDecimal("1.320006")),
					new ParameterValues(new BigDecimal("2.4"), 3, 6, new BigDecimal("1.338866")),
					new ParameterValues(new BigDecimal("2.5"), 3, 6, new BigDecimal("1.357209")),
					new ParameterValues(new BigDecimal("2.6"), 3, 6, new BigDecimal("1.375069")),
					new ParameterValues(new BigDecimal("2.7"), 3, 6, new BigDecimal("1.392477")),
					new ParameterValues(new BigDecimal("2.8"), 3, 6, new BigDecimal("1.409460")),
					new ParameterValues(new BigDecimal("2.9"), 3, 6, new BigDecimal("1.426043")),

					new ParameterValues(new BigDecimal("3.0"), 3, 6, new BigDecimal("1.442250")),
					new ParameterValues(new BigDecimal("3.1"), 3, 6, new BigDecimal("1.458100")),
					new ParameterValues(new BigDecimal("3.2"), 3, 6, new BigDecimal("1.473613")),
					new ParameterValues(new BigDecimal("3.3"), 3, 6, new BigDecimal("1.488806")),
					new ParameterValues(new BigDecimal("3.4"), 3, 6, new BigDecimal("1.503695")),
					new ParameterValues(new BigDecimal("3.5"), 3, 6, new BigDecimal("1.518294")),
					new ParameterValues(new BigDecimal("3.6"), 3, 6, new BigDecimal("1.532619")),
					new ParameterValues(new BigDecimal("3.7"), 3, 6, new BigDecimal("1.546680")),
					new ParameterValues(new BigDecimal("3.8"), 3, 6, new BigDecimal("1.560491")),
					new ParameterValues(new BigDecimal("3.9"), 3, 6, new BigDecimal("1.574061")),

					new ParameterValues(new BigDecimal("4.0"), 3, 6, new BigDecimal("1.587401")),
					new ParameterValues(new BigDecimal("4.1"), 3, 6, new BigDecimal("1.600521")),
					new ParameterValues(new BigDecimal("4.2"), 3, 6, new BigDecimal("1.613429")),
					new ParameterValues(new BigDecimal("4.3"), 3, 6, new BigDecimal("1.626133")),
					new ParameterValues(new BigDecimal("4.4"), 3, 6, new BigDecimal("1.638643")),
					new ParameterValues(new BigDecimal("4.5"), 3, 6, new BigDecimal("1.650964")),
					new ParameterValues(new BigDecimal("4.6"), 3, 6, new BigDecimal("1.663103")),
					new ParameterValues(new BigDecimal("4.7"), 3, 6, new BigDecimal("1.675069")),
					new ParameterValues(new BigDecimal("4.8"), 3, 6, new BigDecimal("1.686865")),
					new ParameterValues(new BigDecimal("4.9"), 3, 6, new BigDecimal("1.698499")),

					new ParameterValues(new BigDecimal("5.0"), 3, 6, new BigDecimal("1.709976")),
					new ParameterValues(new BigDecimal("5.1"), 3, 6, new BigDecimal("1.721301")),
					new ParameterValues(new BigDecimal("5.2"), 3, 6, new BigDecimal("1.732478")),
					new ParameterValues(new BigDecimal("5.3"), 3, 6, new BigDecimal("1.743513")),
					new ParameterValues(new BigDecimal("5.4"), 3, 6, new BigDecimal("1.754411")),
					new ParameterValues(new BigDecimal("5.5"), 3, 6, new BigDecimal("1.765174")),
					new ParameterValues(new BigDecimal("5.6"), 3, 6, new BigDecimal("1.775808")),
					new ParameterValues(new BigDecimal("5.7"), 3, 6, new BigDecimal("1.786316")),
					new ParameterValues(new BigDecimal("5.8"), 3, 6, new BigDecimal("1.796702")),
					new ParameterValues(new BigDecimal("5.9"), 3, 6, new BigDecimal("1.806969")),

					new ParameterValues(new BigDecimal("6.0"), 3, 6, new BigDecimal("1.817121")),
					new ParameterValues(new BigDecimal("6.1"), 3, 6, new BigDecimal("1.827160")),
					new ParameterValues(new BigDecimal("6.2"), 3, 6, new BigDecimal("1.837091")),
					new ParameterValues(new BigDecimal("6.3"), 3, 6, new BigDecimal("1.846915")),
					new ParameterValues(new BigDecimal("6.4"), 3, 6, new BigDecimal("1.856636")),
					new ParameterValues(new BigDecimal("6.5"), 3, 6, new BigDecimal("1.866256")),
					new ParameterValues(new BigDecimal("6.6"), 3, 6, new BigDecimal("1.875777")),
					new ParameterValues(new BigDecimal("6.7"), 3, 6, new BigDecimal("1.885204")),
					new ParameterValues(new BigDecimal("6.8"), 3, 6, new BigDecimal("1.894536")),
					new ParameterValues(new BigDecimal("6.9"), 3, 6, new BigDecimal("1.903778")),

					new ParameterValues(new BigDecimal("7.0"), 3, 6, new BigDecimal("1.912931")),
					new ParameterValues(new BigDecimal("7.1"), 3, 6, new BigDecimal("1.921997")),
					new ParameterValues(new BigDecimal("7.2"), 3, 6, new BigDecimal("1.930979")),
					new ParameterValues(new BigDecimal("7.3"), 3, 6, new BigDecimal("1.939877")),
					new ParameterValues(new BigDecimal("7.4"), 3, 6, new BigDecimal("1.948695")),
					new ParameterValues(new BigDecimal("7.5"), 3, 6, new BigDecimal("1.957434")),
					new ParameterValues(new BigDecimal("7.6"), 3, 6, new BigDecimal("1.966095")),
					new ParameterValues(new BigDecimal("7.7"), 3, 6, new BigDecimal("1.974681")),
					new ParameterValues(new BigDecimal("7.8"), 3, 6, new BigDecimal("1.983192")),
					new ParameterValues(new BigDecimal("7.9"), 3, 6, new BigDecimal("1.991632")),

					new ParameterValues(new BigDecimal("8.0"), 3, 6, new BigDecimal("2.000000")),
					new ParameterValues(new BigDecimal("8.1"), 3, 6, new BigDecimal("2.008299")),
					new ParameterValues(new BigDecimal("8.2"), 3, 6, new BigDecimal("2.016530")),
					new ParameterValues(new BigDecimal("8.3"), 3, 6, new BigDecimal("2.024694")),
					new ParameterValues(new BigDecimal("8.4"), 3, 6, new BigDecimal("2.032793")),
					new ParameterValues(new BigDecimal("8.5"), 3, 6, new BigDecimal("2.040828")),
					new ParameterValues(new BigDecimal("8.6"), 3, 6, new BigDecimal("2.048800")),
					new ParameterValues(new BigDecimal("8.7"), 3, 6, new BigDecimal("2.056710")),
					new ParameterValues(new BigDecimal("8.8"), 3, 6, new BigDecimal("2.064560")),
					new ParameterValues(new BigDecimal("8.9"), 3, 6, new BigDecimal("2.072351")),

					new ParameterValues(new BigDecimal("9.0"), 3, 6, new BigDecimal("2.080084")),
					new ParameterValues(new BigDecimal("9.1"), 3, 6, new BigDecimal("2.087759")),
					new ParameterValues(new BigDecimal("9.2"), 3, 6, new BigDecimal("2.095379")),
					new ParameterValues(new BigDecimal("9.3"), 3, 6, new BigDecimal("2.102944")),
					new ParameterValues(new BigDecimal("9.4"), 3, 6, new BigDecimal("2.110454")),
					new ParameterValues(new BigDecimal("9.5"), 3, 6, new BigDecimal("2.117912")),
					new ParameterValues(new BigDecimal("9.6"), 3, 6, new BigDecimal("2.125317")),
					new ParameterValues(new BigDecimal("9.7"), 3, 6, new BigDecimal("2.132671")),
					new ParameterValues(new BigDecimal("9.8"), 3, 6, new BigDecimal("2.139975")),
					new ParameterValues(new BigDecimal("9.9"), 3, 6, new BigDecimal("2.147229")),
			});
		}

		@Test
		public void test() {
			BigDecimal result = root(x, n, scale);
			BigDecimal scaledResult = result.setScale(expectedValue.scale(), RoundingMode.HALF_UP);

			assertThat(scaledResult, is(equalTo(expectedValue)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final BigDecimal expectedValue;

			private final int n;

			private final int scale;

			private final BigDecimal x;


			public ParameterValues(BigDecimal x, int n, int scale, BigDecimal expectedValue) {
				this.x = x;
				this.n = n;
				this.scale = scale;
				this.expectedValue = expectedValue;
			}


			public BigDecimal getExpectedValue() {
				return expectedValue;
			}

			public int getN() {
				return n;
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
