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
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SparseDoubleArrayTest {

	private static final double AUSTRALIAN_DOLLAR = 1.4782;

	private static final double CANADIAN_DOLLAR = 1.4643;

	private static final double DANISH_KRONE = 7.4374;

	private static final double DEFAULT_VALUE = -1.0;

	private static final double EURO = 1.0;

	private static final double HONG_KONG_DOLLAR = 8.6210;

	private static final double JAPANESE_YEN = 114.83;

	private static final double NORWEGIAN_KRONE = 9.5092;

	private static final double POLISH_ZLOTY = 4.3630;

	private static final double POUND_STERLING = 0.844;

	private static final double RENMINBI = 7.3908;

	private static final double SINGAPORE_DOLLAR = 1.5015;

	private static final double SWEDISH_KRONA = 9.5673;

	private static final double SWISS_FRANC = 1.0823;

	private static final double THAI_BAHT = 38.712;

	private static final double TOLERANCE = 1E-6;

	private static final double UNITED_STATES_DOLLAR = 1.1113;


	@Test
	public void testSetAndRemove() {
		SparseDoubleArray currencies = new SparseDoubleArray();

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		currencies.set(840, UNITED_STATES_DOLLAR);
		currencies.set(978, EURO);
		currencies.set(826, POUND_STERLING);
		currencies.set(392, JAPANESE_YEN);
		currencies.set(156, RENMINBI);

		assertThat(currencies.size(), is(equalTo(5)));

		assertThat(currencies.size(), is(equalTo(5)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(840), is(true));
		assertThat(currencies.getOrDefault(840, DEFAULT_VALUE), is(equalTo(UNITED_STATES_DOLLAR)));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(equalTo(840)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(equalTo(840)));

		assertThat(currencies.containsElement(EURO, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(978), is(true));
		assertThat(currencies.getOrDefault(978, DEFAULT_VALUE), is(equalTo(EURO)));
		assertThat(currencies.indexOf(EURO, TOLERANCE), is(equalTo(978)));
		assertThat(currencies.lastIndexOf(EURO, TOLERANCE), is(equalTo(978)));

		assertThat(currencies.containsElement(POUND_STERLING, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(826), is(true));
		assertThat(currencies.getOrDefault(826, DEFAULT_VALUE), is(equalTo(POUND_STERLING)));
		assertThat(currencies.indexOf(POUND_STERLING, TOLERANCE), is(equalTo(826)));
		assertThat(currencies.lastIndexOf(POUND_STERLING, TOLERANCE), is(equalTo(826)));

		assertThat(currencies.containsElement(JAPANESE_YEN, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(392), is(true));
		assertThat(currencies.getOrDefault(392, DEFAULT_VALUE), is(equalTo(JAPANESE_YEN)));
		assertThat(currencies.indexOf(JAPANESE_YEN, TOLERANCE), is(equalTo(392)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN, TOLERANCE), is(equalTo(392)));

		assertThat(currencies.containsElement(RENMINBI, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(156), is(true));
		assertThat(currencies.getOrDefault(156, DEFAULT_VALUE), is(equalTo(RENMINBI)));
		assertThat(currencies.indexOf(RENMINBI, TOLERANCE), is(equalTo(156)));
		assertThat(currencies.lastIndexOf(RENMINBI, TOLERANCE), is(equalTo(156)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(36), is(false));
		assertThat(currencies.getOrDefault(36, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(124), is(false));
		assertThat(currencies.getOrDefault(124, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(SWISS_FRANC, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(756), is(false));
		assertThat(currencies.getOrDefault(756, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(SWISS_FRANC, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(344), is(false));
		assertThat(currencies.getOrDefault(344, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(THAI_BAHT, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(764), is(false));
		assertThat(currencies.getOrDefault(764, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(THAI_BAHT, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(THAI_BAHT, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(702), is(false));
		assertThat(currencies.getOrDefault(702, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(SWEDISH_KRONA, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(752), is(false));
		assertThat(currencies.getOrDefault(752, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(SWEDISH_KRONA, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(578), is(false));
		assertThat(currencies.getOrDefault(578, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(POLISH_ZLOTY, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(985), is(false));
		assertThat(currencies.getOrDefault(985, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(POLISH_ZLOTY, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(DANISH_KRONE, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(208), is(false));
		assertThat(currencies.getOrDefault(208, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(DANISH_KRONE, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE, TOLERANCE), is(lessThan(0)));

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		currencies.set( 36, AUSTRALIAN_DOLLAR);
		currencies.set(124, CANADIAN_DOLLAR);
		currencies.set(756, SWISS_FRANC);
		currencies.set(344, HONG_KONG_DOLLAR);
		currencies.set(764, THAI_BAHT);

		assertThat(currencies.size(), is(equalTo(10)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(840), is(true));
		assertThat(currencies.getOrDefault(840, DEFAULT_VALUE), is(equalTo(UNITED_STATES_DOLLAR)));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(equalTo(840)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(equalTo(840)));

		assertThat(currencies.containsElement(EURO, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(978), is(true));
		assertThat(currencies.getOrDefault(978, DEFAULT_VALUE), is(equalTo(EURO)));
		assertThat(currencies.indexOf(EURO, TOLERANCE), is(equalTo(978)));
		assertThat(currencies.lastIndexOf(EURO, TOLERANCE), is(equalTo(978)));

		assertThat(currencies.containsElement(POUND_STERLING, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(826), is(true));
		assertThat(currencies.getOrDefault(826, DEFAULT_VALUE), is(equalTo(POUND_STERLING)));
		assertThat(currencies.indexOf(POUND_STERLING, TOLERANCE), is(equalTo(826)));
		assertThat(currencies.lastIndexOf(POUND_STERLING, TOLERANCE), is(equalTo(826)));

		assertThat(currencies.containsElement(JAPANESE_YEN, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(392), is(true));
		assertThat(currencies.getOrDefault(392, DEFAULT_VALUE), is(equalTo(JAPANESE_YEN)));
		assertThat(currencies.indexOf(JAPANESE_YEN, TOLERANCE), is(equalTo(392)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN, TOLERANCE), is(equalTo(392)));

		assertThat(currencies.containsElement(RENMINBI, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(156), is(true));
		assertThat(currencies.getOrDefault(156, DEFAULT_VALUE), is(equalTo(RENMINBI)));
		assertThat(currencies.indexOf(RENMINBI, TOLERANCE), is(equalTo(156)));
		assertThat(currencies.lastIndexOf(RENMINBI, TOLERANCE), is(equalTo(156)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(36), is(true));
		assertThat(currencies.getOrDefault(36, DEFAULT_VALUE), is(equalTo(AUSTRALIAN_DOLLAR)));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(equalTo(36)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(equalTo(36)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(124), is(true));
		assertThat(currencies.getOrDefault(124, DEFAULT_VALUE), is(equalTo(CANADIAN_DOLLAR)));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR, TOLERANCE), is(equalTo(124)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR, TOLERANCE), is(equalTo(124)));

		assertThat(currencies.containsElement(SWISS_FRANC, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(756), is(true));
		assertThat(currencies.getOrDefault(756, DEFAULT_VALUE), is(equalTo(SWISS_FRANC)));
		assertThat(currencies.indexOf(SWISS_FRANC, TOLERANCE), is(equalTo(756)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC, TOLERANCE), is(equalTo(756)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(344), is(true));
		assertThat(currencies.getOrDefault(344, DEFAULT_VALUE), is(equalTo(HONG_KONG_DOLLAR)));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR, TOLERANCE), is(equalTo(344)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR, TOLERANCE), is(equalTo(344)));

		assertThat(currencies.containsElement(THAI_BAHT, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(764), is(true));
		assertThat(currencies.getOrDefault(764, DEFAULT_VALUE), is(equalTo(THAI_BAHT)));
		assertThat(currencies.indexOf(THAI_BAHT, TOLERANCE), is(equalTo(764)));
		assertThat(currencies.lastIndexOf(THAI_BAHT, TOLERANCE), is(equalTo(764)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(702), is(false));
		assertThat(currencies.getOrDefault(702, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(SWEDISH_KRONA, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(752), is(false));
		assertThat(currencies.getOrDefault(752, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(SWEDISH_KRONA, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(578), is(false));
		assertThat(currencies.getOrDefault(578, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(POLISH_ZLOTY, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(985), is(false));
		assertThat(currencies.getOrDefault(985, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(POLISH_ZLOTY, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(DANISH_KRONE, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(208), is(false));
		assertThat(currencies.getOrDefault(208, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(DANISH_KRONE, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE, TOLERANCE), is(lessThan(0)));

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		currencies.set(702, SINGAPORE_DOLLAR);
		currencies.set(752, SWEDISH_KRONA);
		currencies.set(578, NORWEGIAN_KRONE);
		currencies.set(985, POLISH_ZLOTY);
		currencies.set(208, DANISH_KRONE);

		assertThat(currencies.size(), is(equalTo(15)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(840), is(true));
		assertThat(currencies.getOrDefault(840, DEFAULT_VALUE), is(equalTo(UNITED_STATES_DOLLAR)));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(equalTo(840)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(equalTo(840)));

		assertThat(currencies.containsElement(EURO, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(978), is(true));
		assertThat(currencies.getOrDefault(978, DEFAULT_VALUE), is(equalTo(EURO)));
		assertThat(currencies.indexOf(EURO, TOLERANCE), is(equalTo(978)));
		assertThat(currencies.lastIndexOf(EURO, TOLERANCE), is(equalTo(978)));

		assertThat(currencies.containsElement(POUND_STERLING, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(826), is(true));
		assertThat(currencies.getOrDefault(826, DEFAULT_VALUE), is(equalTo(POUND_STERLING)));
		assertThat(currencies.indexOf(POUND_STERLING, TOLERANCE), is(equalTo(826)));
		assertThat(currencies.lastIndexOf(POUND_STERLING, TOLERANCE), is(equalTo(826)));

		assertThat(currencies.containsElement(JAPANESE_YEN, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(392), is(true));
		assertThat(currencies.getOrDefault(392, DEFAULT_VALUE), is(equalTo(JAPANESE_YEN)));
		assertThat(currencies.indexOf(JAPANESE_YEN, TOLERANCE), is(equalTo(392)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN, TOLERANCE), is(equalTo(392)));

		assertThat(currencies.containsElement(RENMINBI, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(156), is(true));
		assertThat(currencies.getOrDefault(156, DEFAULT_VALUE), is(equalTo(RENMINBI)));
		assertThat(currencies.indexOf(RENMINBI, TOLERANCE), is(equalTo(156)));
		assertThat(currencies.lastIndexOf(RENMINBI, TOLERANCE), is(equalTo(156)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(36), is(true));
		assertThat(currencies.getOrDefault(36, DEFAULT_VALUE), is(equalTo(AUSTRALIAN_DOLLAR)));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(equalTo(36)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(equalTo(36)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(124), is(true));
		assertThat(currencies.getOrDefault(124, DEFAULT_VALUE), is(equalTo(CANADIAN_DOLLAR)));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR, TOLERANCE), is(equalTo(124)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR, TOLERANCE), is(equalTo(124)));

		assertThat(currencies.containsElement(SWISS_FRANC, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(756), is(true));
		assertThat(currencies.getOrDefault(756, DEFAULT_VALUE), is(equalTo(SWISS_FRANC)));
		assertThat(currencies.indexOf(SWISS_FRANC, TOLERANCE), is(equalTo(756)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC, TOLERANCE), is(equalTo(756)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(344), is(true));
		assertThat(currencies.getOrDefault(344, DEFAULT_VALUE), is(equalTo(HONG_KONG_DOLLAR)));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR, TOLERANCE), is(equalTo(344)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR, TOLERANCE), is(equalTo(344)));

		assertThat(currencies.containsElement(THAI_BAHT, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(764), is(true));
		assertThat(currencies.getOrDefault(764, DEFAULT_VALUE), is(equalTo(THAI_BAHT)));
		assertThat(currencies.indexOf(THAI_BAHT, TOLERANCE), is(equalTo(764)));
		assertThat(currencies.lastIndexOf(THAI_BAHT, TOLERANCE), is(equalTo(764)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(702), is(true));
		assertThat(currencies.getOrDefault(702, DEFAULT_VALUE), is(equalTo(SINGAPORE_DOLLAR)));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR, TOLERANCE), is(equalTo(702)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR, TOLERANCE), is(equalTo(702)));

		assertThat(currencies.containsElement(SWEDISH_KRONA, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(752), is(true));
		assertThat(currencies.getOrDefault(752, DEFAULT_VALUE), is(equalTo(SWEDISH_KRONA)));
		assertThat(currencies.indexOf(SWEDISH_KRONA, TOLERANCE), is(equalTo(752)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA, TOLERANCE), is(equalTo(752)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(578), is(true));
		assertThat(currencies.getOrDefault(578, DEFAULT_VALUE), is(equalTo(NORWEGIAN_KRONE)));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE, TOLERANCE), is(equalTo(578)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE, TOLERANCE), is(equalTo(578)));

		assertThat(currencies.containsElement(POLISH_ZLOTY, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(985), is(true));
		assertThat(currencies.getOrDefault(985, DEFAULT_VALUE), is(equalTo(POLISH_ZLOTY)));
		assertThat(currencies.indexOf(POLISH_ZLOTY, TOLERANCE), is(equalTo(985)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY, TOLERANCE), is(equalTo(985)));

		assertThat(currencies.containsElement(DANISH_KRONE, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(208), is(true));
		assertThat(currencies.getOrDefault(208, DEFAULT_VALUE), is(equalTo(DANISH_KRONE)));
		assertThat(currencies.indexOf(DANISH_KRONE, TOLERANCE), is(equalTo(208)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE, TOLERANCE), is(equalTo(208)));

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		assertThat(currencies.remove(156, DEFAULT_VALUE), is(equalTo(RENMINBI)));
		assertThat(currencies.remove(764, DEFAULT_VALUE), is(equalTo(THAI_BAHT)));
		assertThat(currencies.remove(702, DEFAULT_VALUE), is(equalTo(SINGAPORE_DOLLAR)));

		assertThat(currencies.size(), is(equalTo(12)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(840), is(true));
		assertThat(currencies.getOrDefault(840, DEFAULT_VALUE), is(equalTo(UNITED_STATES_DOLLAR)));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(equalTo(840)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(equalTo(840)));

		assertThat(currencies.containsElement(EURO, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(978), is(true));
		assertThat(currencies.getOrDefault(978, DEFAULT_VALUE), is(equalTo(EURO)));
		assertThat(currencies.indexOf(EURO, TOLERANCE), is(equalTo(978)));
		assertThat(currencies.lastIndexOf(EURO, TOLERANCE), is(equalTo(978)));

		assertThat(currencies.containsElement(POUND_STERLING, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(826), is(true));
		assertThat(currencies.getOrDefault(826, DEFAULT_VALUE), is(equalTo(POUND_STERLING)));
		assertThat(currencies.indexOf(POUND_STERLING, TOLERANCE), is(equalTo(826)));
		assertThat(currencies.lastIndexOf(POUND_STERLING, TOLERANCE), is(equalTo(826)));

		assertThat(currencies.containsElement(JAPANESE_YEN, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(392), is(true));
		assertThat(currencies.getOrDefault(392, DEFAULT_VALUE), is(equalTo(JAPANESE_YEN)));
		assertThat(currencies.indexOf(JAPANESE_YEN, TOLERANCE), is(equalTo(392)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN, TOLERANCE), is(equalTo(392)));

		assertThat(currencies.containsElement(RENMINBI, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(156), is(false));
		assertThat(currencies.getOrDefault(156, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(RENMINBI, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(RENMINBI, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(36), is(true));
		assertThat(currencies.getOrDefault(36, DEFAULT_VALUE), is(equalTo(AUSTRALIAN_DOLLAR)));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(equalTo(36)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(equalTo(36)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(124), is(true));
		assertThat(currencies.getOrDefault(124, DEFAULT_VALUE), is(equalTo(CANADIAN_DOLLAR)));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR, TOLERANCE), is(equalTo(124)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR, TOLERANCE), is(equalTo(124)));

		assertThat(currencies.containsElement(SWISS_FRANC, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(756), is(true));
		assertThat(currencies.getOrDefault(756, DEFAULT_VALUE), is(equalTo(SWISS_FRANC)));
		assertThat(currencies.indexOf(SWISS_FRANC, TOLERANCE), is(equalTo(756)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC, TOLERANCE), is(equalTo(756)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(344), is(true));
		assertThat(currencies.getOrDefault(344, DEFAULT_VALUE), is(equalTo(HONG_KONG_DOLLAR)));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR, TOLERANCE), is(equalTo(344)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR, TOLERANCE), is(equalTo(344)));

		assertThat(currencies.containsElement(THAI_BAHT, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(764), is(false));
		assertThat(currencies.getOrDefault(764, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(THAI_BAHT, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(THAI_BAHT, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(702), is(false));
		assertThat(currencies.getOrDefault(702, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(SWEDISH_KRONA, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(752), is(true));
		assertThat(currencies.getOrDefault(752, DEFAULT_VALUE), is(equalTo(SWEDISH_KRONA)));
		assertThat(currencies.indexOf(SWEDISH_KRONA, TOLERANCE), is(equalTo(752)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA, TOLERANCE), is(equalTo(752)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(578), is(true));
		assertThat(currencies.getOrDefault(578, DEFAULT_VALUE), is(equalTo(NORWEGIAN_KRONE)));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE, TOLERANCE), is(equalTo(578)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE, TOLERANCE), is(equalTo(578)));

		assertThat(currencies.containsElement(POLISH_ZLOTY, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(985), is(true));
		assertThat(currencies.getOrDefault(985, DEFAULT_VALUE), is(equalTo(POLISH_ZLOTY)));
		assertThat(currencies.indexOf(POLISH_ZLOTY, TOLERANCE), is(equalTo(985)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY, TOLERANCE), is(equalTo(985)));

		assertThat(currencies.containsElement(DANISH_KRONE, TOLERANCE), is(true));
		assertThat(currencies.containsIndex(208), is(true));
		assertThat(currencies.getOrDefault(208, DEFAULT_VALUE), is(equalTo(DANISH_KRONE)));
		assertThat(currencies.indexOf(DANISH_KRONE, TOLERANCE), is(equalTo(208)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE, TOLERANCE), is(equalTo(208)));

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		currencies.clear();

		assertThat(currencies.size(), is(equalTo(0)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(840), is(false));
		assertThat(currencies.getOrDefault(840, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(EURO, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(978), is(false));
		assertThat(currencies.getOrDefault(978, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(EURO, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(EURO, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(POUND_STERLING, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(826), is(false));
		assertThat(currencies.getOrDefault(826, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(POUND_STERLING, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(POUND_STERLING, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(JAPANESE_YEN, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(392), is(false));
		assertThat(currencies.getOrDefault(392, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(JAPANESE_YEN, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(RENMINBI, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(156), is(false));
		assertThat(currencies.getOrDefault(156, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(RENMINBI, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(RENMINBI, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(36), is(false));
		assertThat(currencies.getOrDefault(36, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(124), is(false));
		assertThat(currencies.getOrDefault(124, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(SWISS_FRANC, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(756), is(false));
		assertThat(currencies.getOrDefault(756, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(SWISS_FRANC, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(344), is(false));
		assertThat(currencies.getOrDefault(344, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(THAI_BAHT, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(764), is(false));
		assertThat(currencies.getOrDefault(764, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(THAI_BAHT, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(THAI_BAHT, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(702), is(false));
		assertThat(currencies.getOrDefault(702, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(SWEDISH_KRONA, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(752), is(false));
		assertThat(currencies.getOrDefault(752, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(SWEDISH_KRONA, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(578), is(false));
		assertThat(currencies.getOrDefault(578, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(POLISH_ZLOTY, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(985), is(false));
		assertThat(currencies.getOrDefault(985, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(POLISH_ZLOTY, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY, TOLERANCE), is(lessThan(0)));

		assertThat(currencies.containsElement(DANISH_KRONE, TOLERANCE), is(false));
		assertThat(currencies.containsIndex(208), is(false));
		assertThat(currencies.getOrDefault(208, DEFAULT_VALUE), is(DEFAULT_VALUE));
		assertThat(currencies.indexOf(DANISH_KRONE, TOLERANCE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE, TOLERANCE), is(lessThan(0)));
	}

}
