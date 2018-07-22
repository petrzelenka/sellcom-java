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
package org.sellcom.core.collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.collection.MoreCollections.countFrequencies;
import static org.sellcom.core.collection.MoreCollections.sortByValues;

import java.time.Month;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class MoreCollectionsTest {

	@Test
	public void testCountFrequencies() {
		List<String> values = Arrays.asList(
			"New Zealand",
			"Australia",
			"Finland",
			"Australia",
			"Australia",
			"Canada",
			"Canada",
			"Canada",
			"Austria",
			"Australia"
		);

		Map<String, Integer> frequencies = countFrequencies(values);
		assertThat(frequencies.get("Australia"), is(equalTo(4)));
		assertThat(frequencies.get("Austria"), is(equalTo(1)));
		assertThat(frequencies.get("Canada"), is(equalTo(3)));
		assertThat(frequencies.get("Finland"), is(equalTo(1)));
		assertThat(frequencies.get("New Zealand"), is(equalTo(1)));
	}

	@Test
	public void testSortByValue_comparable() {
		Map<MonthDay, String> nameDays = new TreeMap<>();

		nameDays.put(MonthDay.of(Month.MARCH, 7), "Tomáš");
		nameDays.put(MonthDay.of(Month.FEBRUARY, 24), "Matěj");
		nameDays.put(MonthDay.of(Month.APRIL, 23), "Vojtěch");
		nameDays.put(MonthDay.of(Month.MAY, 26), "Filip");
		nameDays.put(MonthDay.of(Month.JUNE, 24), "Jan");
		nameDays.put(MonthDay.of(Month.JULY, 25), "Jakub");
		nameDays.put(MonthDay.of(Month.OCTOBER, 18), "Lukáš");
		nameDays.put(MonthDay.of(Month.NOVEMBER, 30), "Ondřej");
		nameDays.put(MonthDay.of(Month.DECEMBER, 24), "Adam");
		nameDays.put(MonthDay.of(Month.DECEMBER, 30), "David");

		nameDays.put(MonthDay.of(Month.MARCH, 10), "Viktorie");
		nameDays.put(MonthDay.of(Month.APRIL, 8), "Ema");
		nameDays.put(MonthDay.of(Month.MAY, 15), "Sofie");
		nameDays.put(MonthDay.of(Month.JULY, 14), "Karolína");
		nameDays.put(MonthDay.of(Month.JULY, 24), "Kristýna");
		nameDays.put(MonthDay.of(Month.JULY, 26), "Anna");
		nameDays.put(MonthDay.of(Month.SEPTEMBER, 2), "Adéla");
		nameDays.put(MonthDay.of(Month.OCTOBER, 5), "Eliška");
		nameDays.put(MonthDay.of(Month.OCTOBER, 15), "Tereza");
		nameDays.put(MonthDay.of(Month.DECEMBER, 21), "Natálie");

		Map<MonthDay, String> nameDaysSortedByNames = sortByValues(nameDays);
		Iterator<String> valueIterator = nameDaysSortedByNames.values().iterator();

		assertThat(valueIterator.next(), is(equalTo("Adam")));
		assertThat(valueIterator.next(), is(equalTo("Adéla")));
		assertThat(valueIterator.next(), is(equalTo("Anna")));
		assertThat(valueIterator.next(), is(equalTo("David")));
		assertThat(valueIterator.next(), is(equalTo("Eliška")));
		assertThat(valueIterator.next(), is(equalTo("Ema")));
		assertThat(valueIterator.next(), is(equalTo("Filip")));
		assertThat(valueIterator.next(), is(equalTo("Jakub")));
		assertThat(valueIterator.next(), is(equalTo("Jan")));
		assertThat(valueIterator.next(), is(equalTo("Karolína")));
		assertThat(valueIterator.next(), is(equalTo("Kristýna")));
		assertThat(valueIterator.next(), is(equalTo("Lukáš")));
		assertThat(valueIterator.next(), is(equalTo("Matěj")));
		assertThat(valueIterator.next(), is(equalTo("Natálie")));
		assertThat(valueIterator.next(), is(equalTo("Ondřej")));
		assertThat(valueIterator.next(), is(equalTo("Sofie")));
		assertThat(valueIterator.next(), is(equalTo("Tereza")));
		assertThat(valueIterator.next(), is(equalTo("Tomáš")));
		assertThat(valueIterator.next(), is(equalTo("Viktorie")));
		assertThat(valueIterator.next(), is(equalTo("Vojtěch")));
	}

	@Test
	public void testSortByValue_comparator() {
		Map<MonthDay, String> nameDays = new TreeMap<>();

		nameDays.put(MonthDay.of(Month.MARCH, 7), "Tomáš");
		nameDays.put(MonthDay.of(Month.FEBRUARY, 24), "Matěj");
		nameDays.put(MonthDay.of(Month.APRIL, 23), "Vojtěch");
		nameDays.put(MonthDay.of(Month.MAY, 26), "Filip");
		nameDays.put(MonthDay.of(Month.JUNE, 24), "Jan");
		nameDays.put(MonthDay.of(Month.JULY, 25), "Jakub");
		nameDays.put(MonthDay.of(Month.OCTOBER, 18), "Lukáš");
		nameDays.put(MonthDay.of(Month.NOVEMBER, 30), "Ondřej");
		nameDays.put(MonthDay.of(Month.DECEMBER, 24), "Adam");
		nameDays.put(MonthDay.of(Month.DECEMBER, 30), "David");

		nameDays.put(MonthDay.of(Month.MARCH, 10), "Viktorie");
		nameDays.put(MonthDay.of(Month.APRIL, 8), "Ema");
		nameDays.put(MonthDay.of(Month.MAY, 15), "Sofie");
		nameDays.put(MonthDay.of(Month.JULY, 14), "Karolína");
		nameDays.put(MonthDay.of(Month.JULY, 24), "Kristýna");
		nameDays.put(MonthDay.of(Month.JULY, 26), "Anna");
		nameDays.put(MonthDay.of(Month.SEPTEMBER, 2), "Adéla");
		nameDays.put(MonthDay.of(Month.OCTOBER, 5), "Eliška");
		nameDays.put(MonthDay.of(Month.OCTOBER, 15), "Tereza");
		nameDays.put(MonthDay.of(Month.DECEMBER, 21), "Natálie");

		Map<MonthDay, String> nameDaysSortedByNames = sortByValues(nameDays, Comparator.<String>naturalOrder().reversed());
		Iterator<String> valueIterator = nameDaysSortedByNames.values().iterator();

		assertThat(valueIterator.next(), is(equalTo("Vojtěch")));
		assertThat(valueIterator.next(), is(equalTo("Viktorie")));
		assertThat(valueIterator.next(), is(equalTo("Tomáš")));
		assertThat(valueIterator.next(), is(equalTo("Tereza")));
		assertThat(valueIterator.next(), is(equalTo("Sofie")));
		assertThat(valueIterator.next(), is(equalTo("Ondřej")));
		assertThat(valueIterator.next(), is(equalTo("Natálie")));
		assertThat(valueIterator.next(), is(equalTo("Matěj")));
		assertThat(valueIterator.next(), is(equalTo("Lukáš")));
		assertThat(valueIterator.next(), is(equalTo("Kristýna")));
		assertThat(valueIterator.next(), is(equalTo("Karolína")));
		assertThat(valueIterator.next(), is(equalTo("Jan")));
		assertThat(valueIterator.next(), is(equalTo("Jakub")));
		assertThat(valueIterator.next(), is(equalTo("Filip")));
		assertThat(valueIterator.next(), is(equalTo("Ema")));
		assertThat(valueIterator.next(), is(equalTo("Eliška")));
		assertThat(valueIterator.next(), is(equalTo("David")));
		assertThat(valueIterator.next(), is(equalTo("Anna")));
		assertThat(valueIterator.next(), is(equalTo("Adéla")));
		assertThat(valueIterator.next(), is(equalTo("Adam")));
	}

}
