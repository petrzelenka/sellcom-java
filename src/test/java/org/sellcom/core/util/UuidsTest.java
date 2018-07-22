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
package org.sellcom.core.util;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import org.junit.Test;
import org.sellcom.core.internal.test.TestUtils;

public class UuidsTest {

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class Random8x4Test {

		private static final int REPEAT_COUNT = 100_000;


		@Test
		public void test() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				UUID uuid = UUID.randomUUID();

				assertThat(Uuids.parse8x4(Uuids.format8x4(uuid)), is(equalTo(uuid)));
			});
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	public static class RandomBase58Test {

		private static final int REPEAT_COUNT = 100_000;


		@Test
		public void test() {
			TestUtils.repeat(REPEAT_COUNT, () -> {
				UUID uuid = UUID.randomUUID();

				assertThat(Uuids.parseBase58(Uuids.formatBase58(uuid)), is(equalTo(uuid)));
			});
		}

	}

}
