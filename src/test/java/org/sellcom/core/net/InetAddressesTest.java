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
package org.sellcom.core.net;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

public class InetAddressesTest {

	@RunWith(Parameterized.class)
	public static class ParameterizedIpV4Test {

		private final boolean expectedValue;

		private final String input;


		public ParameterizedIpV4Test(ParameterValues values) {
			expectedValue = values.getExpectedValue();
			input = values.getInput();
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					new ParameterValues("127.0.0.1", true),
					new ParameterValues("10.0.0.1", true),
					new ParameterValues("192.168.1.1", true),
					new ParameterValues("0.0.0.0", true),
					new ParameterValues("255.255.255.255", true),

					new ParameterValues("10002.3.4", false),
					new ParameterValues("1.2.3.4.5", false),
					new ParameterValues("256.0.0.0", false),
					new ParameterValues("260.0.0.0", false),
			});
		}

		@Test
		public void test() {
			assertThat(InetAddresses.isInet4Address(input), is(equalTo(expectedValue)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final boolean expectedValue;

			private final String input;


			public ParameterValues(String input, boolean expectedValue) {
				this.input = input;
				this.expectedValue = expectedValue;
			}


			public boolean getExpectedValue() {
				return expectedValue;
			}

			public String getInput() {
				return input;
			}

		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class ParameterizedIpV6Test {

		private final boolean expectedValue;

		private final String input;


		public ParameterizedIpV6Test(ParameterValues values) {
			expectedValue = values.getExpectedValue();
			input = values.getInput();
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					new ParameterValues("1:2:3:4:5:6:7:8", true),
					new ParameterValues("1:2:3:4:5:6:77:88", true),
					new ParameterValues("::ffff:10.0.0.1", true),
					new ParameterValues("::ffff:1.2.3.4", true),
					new ParameterValues("::ffff:0.0.0.0", true),
					new ParameterValues("::ffff:255.255.255.255", true),
					new ParameterValues("fe08::7:8", true),
					new ParameterValues("ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff", true),

					new ParameterValues("1:2:3:4:5:6:7:8:9", false),
					new ParameterValues("1:2:3:4:5:6::7:8", false),
					new ParameterValues(":1:2:3:4:5:6:7:8", false),
					new ParameterValues("1:2:3:4:5:6:7:8:", false),
					new ParameterValues("::1:2:3:4:5:6:7:8", false),
					new ParameterValues("1:2:3:4:5:6:7:8::", false),
					new ParameterValues("1:2:3:4:5:6:7:88888", false),
					new ParameterValues("2001:db8:3:4:5::192.0.2.33", false),
					new ParameterValues("fe08::7:8%", false),
					new ParameterValues("fe08::7:8i", false),
					new ParameterValues("fe08::7:8interface", false),
			});
		}

		@Test
		public void test() {
			assertThat(InetAddresses.isInet6Address(input), is(equalTo(expectedValue)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final boolean expectedValue;

			private final String input;


			public ParameterValues(String input, boolean expectedValue) {
				this.input = input;
				this.expectedValue = expectedValue;
			}


			public boolean getExpectedValue() {
				return expectedValue;
			}

			public String getInput() {
				return input;
			}

		}

	}

}
