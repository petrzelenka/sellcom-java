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
package org.sellcom.core.collection.concurrent;

import static java.lang.Boolean.TRUE;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.Test;

public class ExpiringHashSetTest {

	@Test
	public void testEntriesExpiration() {
		ExpiringHashMap<Integer, Boolean> expiringMap = new ExpiringHashMap<>(10, SECONDS);

		expiringMap.setClock(fixedClock(1000));
		expiringMap.put(1, TRUE);
		expiringMap.setClock(fixedClock(2000));
		expiringMap.put(2, TRUE);
		expiringMap.setClock(fixedClock(3000));
		expiringMap.put(3, TRUE);
		expiringMap.setClock(fixedClock(4000));
		expiringMap.put(4, TRUE);
		expiringMap.setClock(fixedClock(5000));
		expiringMap.put(5, TRUE);

		expiringMap.setClock(fixedClock(10100));
		assertThat(expiringMap.get(1), is(equalTo(TRUE)));
		assertThat(expiringMap.get(2), is(equalTo(TRUE)));
		assertThat(expiringMap.get(3), is(equalTo(TRUE)));
		assertThat(expiringMap.get(4), is(equalTo(TRUE)));
		assertThat(expiringMap.get(5), is(equalTo(TRUE)));

		expiringMap.setClock(fixedClock(11100));
		assertThat(expiringMap.get(1), is(nullValue()));
		assertThat(expiringMap.get(2), is(equalTo(TRUE)));
		assertThat(expiringMap.get(3), is(equalTo(TRUE)));
		assertThat(expiringMap.get(4), is(equalTo(TRUE)));
		assertThat(expiringMap.get(5), is(equalTo(TRUE)));

		expiringMap.setClock(fixedClock(12100));
		assertThat(expiringMap.get(1), is(nullValue()));
		assertThat(expiringMap.get(2), is(nullValue()));
		assertThat(expiringMap.get(3), is(equalTo(TRUE)));
		assertThat(expiringMap.get(4), is(equalTo(TRUE)));
		assertThat(expiringMap.get(5), is(equalTo(TRUE)));

		expiringMap.setClock(fixedClock(13100));
		assertThat(expiringMap.get(1), is(nullValue()));
		assertThat(expiringMap.get(2), is(nullValue()));
		assertThat(expiringMap.get(3), is(nullValue()));
		assertThat(expiringMap.get(4), is(equalTo(TRUE)));
		assertThat(expiringMap.get(5), is(equalTo(TRUE)));

		expiringMap.setClock(fixedClock(14100));
		assertThat(expiringMap.get(1), is(nullValue()));
		assertThat(expiringMap.get(2), is(nullValue()));
		assertThat(expiringMap.get(3), is(nullValue()));
		assertThat(expiringMap.get(4), is(nullValue()));
		assertThat(expiringMap.get(5), is(equalTo(TRUE)));

		expiringMap.setClock(fixedClock(15100));
		assertThat(expiringMap.get(1), is(nullValue()));
		assertThat(expiringMap.get(2), is(nullValue()));
		assertThat(expiringMap.get(3), is(nullValue()));
		assertThat(expiringMap.get(4), is(nullValue()));
		assertThat(expiringMap.get(5), is(nullValue()));
	}


	private static Clock fixedClock(long millis) {
		return Clock.fixed(Instant.ofEpochMilli(millis), ZoneId.of("UTC"));
	}

}
