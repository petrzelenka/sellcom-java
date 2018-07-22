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
package org.sellcom.core.internal.io.encoding;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.sellcom.core.internal.test.TestUtils;
import org.sellcom.core.internal.util.RandomUtils;
import org.sellcom.core.io.encoding.BinaryDecoder;
import org.sellcom.core.io.encoding.BinaryEncoder;
import org.sellcom.core.io.encoding.StandardBinaryEncodings;

public class Base58BitcoinEncodingTest {

	private static final BinaryDecoder BASE16_DECODER = StandardBinaryEncodings.createBase16Decoder();

	private static final BinaryDecoder DECODER = StandardBinaryEncodings.createBase58BitcoinDecoder();

	private static final BinaryEncoder ENCODER = StandardBinaryEncodings.createBase58BitcoinEncoder();

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class CompatibilityTest {

		private byte[] decodedValue;

		private String encodedValue;


		public CompatibilityTest(ParameterValues values) {
			encodedValue = values.getEncodedValue();
			decodedValue = values.getDecodedValue();
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					new ParameterValues(BASE16_DECODER.decode("61"), "2g"),
					new ParameterValues(BASE16_DECODER.decode("626262"), "a3gV"),
					new ParameterValues(BASE16_DECODER.decode("636363"), "aPEr"),
					new ParameterValues(BASE16_DECODER.decode("10C8511E"), "Rt5zm"),
					new ParameterValues(BASE16_DECODER.decode("572E4794"), "3EFU7m"),
					new ParameterValues(BASE16_DECODER.decode("516B6FCD0F"), "ABnLTmg"),
					new ParameterValues(BASE16_DECODER.decode("BF4F89001E670274DD"), "3SEo3LWLoPntC"),
					new ParameterValues(BASE16_DECODER.decode("ECAC89CAD93923C02321"), "EJDM8drfXA6uyA"),
					new ParameterValues(BASE16_DECODER.decode("73696D706C792061206C6F6E6720737472696E67"), "2cFupjhnEsSn59qHXstmK2ffpLv2"),
					new ParameterValues(BASE16_DECODER.decode("00EB15231DFCEB60925886B67D065299925915AEB172C06647"), "1NS17iag9jJgTHD1VXjvLCEnZuQ3rJDE9L"),
			});
		}

		@Test
		public void testDecoder() {
			assertThat(DECODER.decode(encodedValue), is(equalTo(decodedValue)));
		}

		@Test
		public void testEncoder() {
			assertThat(ENCODER.encodeToString(decodedValue), is(equalTo(encodedValue)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final byte[] decodedValue;

			private final String encodedValue;


			public ParameterValues(byte[] decodedValue, String encodedValue) {
				this.decodedValue = decodedValue;
				this.encodedValue = encodedValue;
			}


			public byte[] getDecodedValue() {
				return decodedValue;
			}

			public String getEncodedValue() {
				return encodedValue;
			}

		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class RandomTest {

		private static final int REPEAT_COUNT = 100_000;

		private static final Random random = new Random();


		@Test
		public void test() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				byte[] bytes = RandomUtils.nextBytes(random, RandomUtils.nextInt(random, 0, 17));

				assertThat(DECODER.decode(ENCODER.encode(bytes)), is(equalTo(bytes)));
			});
		}

	}

}
