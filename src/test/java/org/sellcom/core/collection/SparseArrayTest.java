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
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SparseArrayTest {

	private static final String AUSTRALIAN_DOLLAR = "Australian dollar";

	private static final String CANADIAN_DOLLAR = "Canadian dollar";

	private static final String DANISH_KRONE = "Danish krone";

	private static final String EURO = "Euro";

	private static final String HONG_KONG_DOLLAR = "Hong Kong dollar";

	private static final String JAPANESE_YEN = "Japanese yen";

	private static final String NORWEGIAN_KRONE = "Norwegian krone";

	private static final String POLISH_ZLOTY = "Polish złoty";

	private static final String POUND_STERLING = "Pound sterling";

	private static final String RENMINBI = "Renminbi";

	private static final String SINGAPORE_DOLLAR = "Singapore dollar";

	private static final String SWEDISH_KRONA = "Swedish krona";

	private static final String SWISS_FRANC = "Swiss franc";

	private static final String THAI_BAHT = "Thai baht";

	private static final String UNITED_STATES_DOLLAR = "United States dollar";


	@Test
	public void testSetAndRemove() {
		SparseArray<String> currencies = new SparseArray<>();

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		currencies.set(840, UNITED_STATES_DOLLAR);
		currencies.set(978, EURO);
		currencies.set(826, POUND_STERLING);
		currencies.set(392, JAPANESE_YEN);
		currencies.set(156, RENMINBI);

		assertThat(currencies.size(), is(equalTo(5)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR), is(true));
		assertThat(currencies.containsIndex(840), is(true));
		assertThat(currencies.get(840), is(equalTo(UNITED_STATES_DOLLAR)));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR), is(equalTo(840)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR), is(equalTo(840)));

		assertThat(currencies.containsElement(EURO), is(true));
		assertThat(currencies.containsIndex(978), is(true));
		assertThat(currencies.get(978), is(equalTo(EURO)));
		assertThat(currencies.indexOf(EURO), is(equalTo(978)));
		assertThat(currencies.lastIndexOf(EURO), is(equalTo(978)));

		assertThat(currencies.containsElement(POUND_STERLING), is(true));
		assertThat(currencies.containsIndex(826), is(true));
		assertThat(currencies.get(826), is(equalTo(POUND_STERLING)));
		assertThat(currencies.indexOf(POUND_STERLING), is(equalTo(826)));
		assertThat(currencies.lastIndexOf(POUND_STERLING), is(equalTo(826)));

		assertThat(currencies.containsElement(JAPANESE_YEN), is(true));
		assertThat(currencies.containsIndex(392), is(true));
		assertThat(currencies.get(392), is(equalTo(JAPANESE_YEN)));
		assertThat(currencies.indexOf(JAPANESE_YEN), is(equalTo(392)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN), is(equalTo(392)));

		assertThat(currencies.containsElement(RENMINBI), is(true));
		assertThat(currencies.containsIndex(156), is(true));
		assertThat(currencies.get(156), is(equalTo(RENMINBI)));
		assertThat(currencies.indexOf(RENMINBI), is(equalTo(156)));
		assertThat(currencies.lastIndexOf(RENMINBI), is(equalTo(156)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR), is(false));
		assertThat(currencies.containsIndex(36), is(false));
		assertThat(currencies.get(36), is(nullValue()));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR), is(false));
		assertThat(currencies.containsIndex(124), is(false));
		assertThat(currencies.get(124), is(nullValue()));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(SWISS_FRANC), is(false));
		assertThat(currencies.containsIndex(756), is(false));
		assertThat(currencies.get(756), is(nullValue()));
		assertThat(currencies.indexOf(SWISS_FRANC), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC), is(lessThan(0)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR), is(false));
		assertThat(currencies.containsIndex(344), is(false));
		assertThat(currencies.get(344), is(nullValue()));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(THAI_BAHT), is(false));
		assertThat(currencies.containsIndex(764), is(false));
		assertThat(currencies.get(764), is(nullValue()));
		assertThat(currencies.indexOf(THAI_BAHT), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(THAI_BAHT), is(lessThan(0)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR), is(false));
		assertThat(currencies.containsIndex(702), is(false));
		assertThat(currencies.get(702), is(nullValue()));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(SWEDISH_KRONA), is(false));
		assertThat(currencies.containsIndex(752), is(false));
		assertThat(currencies.get(752), is(nullValue()));
		assertThat(currencies.indexOf(SWEDISH_KRONA), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA), is(lessThan(0)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE), is(false));
		assertThat(currencies.containsIndex(578), is(false));
		assertThat(currencies.get(578), is(nullValue()));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE), is(lessThan(0)));

		assertThat(currencies.containsElement(POLISH_ZLOTY), is(false));
		assertThat(currencies.containsIndex(985), is(false));
		assertThat(currencies.get(985), is(nullValue()));
		assertThat(currencies.indexOf(POLISH_ZLOTY), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY), is(lessThan(0)));

		assertThat(currencies.containsElement(DANISH_KRONE), is(false));
		assertThat(currencies.containsIndex(208), is(false));
		assertThat(currencies.get(208), is(nullValue()));
		assertThat(currencies.indexOf(DANISH_KRONE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE), is(lessThan(0)));

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		currencies.set( 36, AUSTRALIAN_DOLLAR);
		currencies.set(124, CANADIAN_DOLLAR);
		currencies.set(756, SWISS_FRANC);
		currencies.set(344, HONG_KONG_DOLLAR);
		currencies.set(764, THAI_BAHT);

		assertThat(currencies.size(), is(equalTo(10)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR), is(true));
		assertThat(currencies.containsIndex(840), is(true));
		assertThat(currencies.get(840), is(equalTo(UNITED_STATES_DOLLAR)));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR), is(equalTo(840)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR), is(equalTo(840)));

		assertThat(currencies.containsElement(EURO), is(true));
		assertThat(currencies.containsIndex(978), is(true));
		assertThat(currencies.get(978), is(equalTo(EURO)));
		assertThat(currencies.indexOf(EURO), is(equalTo(978)));
		assertThat(currencies.lastIndexOf(EURO), is(equalTo(978)));

		assertThat(currencies.containsElement(POUND_STERLING), is(true));
		assertThat(currencies.containsIndex(826), is(true));
		assertThat(currencies.get(826), is(equalTo(POUND_STERLING)));
		assertThat(currencies.indexOf(POUND_STERLING), is(equalTo(826)));
		assertThat(currencies.lastIndexOf(POUND_STERLING), is(equalTo(826)));

		assertThat(currencies.containsElement(JAPANESE_YEN), is(true));
		assertThat(currencies.containsIndex(392), is(true));
		assertThat(currencies.get(392), is(equalTo(JAPANESE_YEN)));
		assertThat(currencies.indexOf(JAPANESE_YEN), is(equalTo(392)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN), is(equalTo(392)));

		assertThat(currencies.containsElement(RENMINBI), is(true));
		assertThat(currencies.containsIndex(156), is(true));
		assertThat(currencies.get(156), is(equalTo(RENMINBI)));
		assertThat(currencies.indexOf(RENMINBI), is(equalTo(156)));
		assertThat(currencies.lastIndexOf(RENMINBI), is(equalTo(156)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR), is(true));
		assertThat(currencies.containsIndex(36), is(true));
		assertThat(currencies.get(36), is(equalTo(AUSTRALIAN_DOLLAR)));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR), is(equalTo(36)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR), is(equalTo(36)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR), is(true));
		assertThat(currencies.containsIndex(124), is(true));
		assertThat(currencies.get(124), is(equalTo(CANADIAN_DOLLAR)));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR), is(equalTo(124)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR), is(equalTo(124)));

		assertThat(currencies.containsElement(SWISS_FRANC), is(true));
		assertThat(currencies.containsIndex(756), is(true));
		assertThat(currencies.get(756), is(equalTo(SWISS_FRANC)));
		assertThat(currencies.indexOf(SWISS_FRANC), is(equalTo(756)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC), is(equalTo(756)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR), is(true));
		assertThat(currencies.containsIndex(344), is(true));
		assertThat(currencies.get(344), is(equalTo(HONG_KONG_DOLLAR)));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR), is(equalTo(344)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR), is(equalTo(344)));

		assertThat(currencies.containsElement(THAI_BAHT), is(true));
		assertThat(currencies.containsIndex(764), is(true));
		assertThat(currencies.get(764), is(equalTo(THAI_BAHT)));
		assertThat(currencies.indexOf(THAI_BAHT), is(equalTo(764)));
		assertThat(currencies.lastIndexOf(THAI_BAHT), is(equalTo(764)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR), is(false));
		assertThat(currencies.containsIndex(702), is(false));
		assertThat(currencies.get(702), is(nullValue()));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(SWEDISH_KRONA), is(false));
		assertThat(currencies.containsIndex(752), is(false));
		assertThat(currencies.get(752), is(nullValue()));
		assertThat(currencies.indexOf(SWEDISH_KRONA), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA), is(lessThan(0)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE), is(false));
		assertThat(currencies.containsIndex(578), is(false));
		assertThat(currencies.get(578), is(nullValue()));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE), is(lessThan(0)));

		assertThat(currencies.containsElement(POLISH_ZLOTY), is(false));
		assertThat(currencies.containsIndex(985), is(false));
		assertThat(currencies.get(985), is(nullValue()));
		assertThat(currencies.indexOf(POLISH_ZLOTY), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY), is(lessThan(0)));

		assertThat(currencies.containsElement(DANISH_KRONE), is(false));
		assertThat(currencies.containsIndex(208), is(false));
		assertThat(currencies.get(208), is(nullValue()));
		assertThat(currencies.indexOf(DANISH_KRONE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE), is(lessThan(0)));

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		currencies.set(702, SINGAPORE_DOLLAR);
		currencies.set(752, SWEDISH_KRONA);
		currencies.set(578, NORWEGIAN_KRONE);
		currencies.set(985, POLISH_ZLOTY);
		currencies.set(208, DANISH_KRONE);

		assertThat(currencies.size(), is(equalTo(15)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR), is(true));
		assertThat(currencies.containsIndex(840), is(true));
		assertThat(currencies.get(840), is(equalTo(UNITED_STATES_DOLLAR)));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR), is(equalTo(840)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR), is(equalTo(840)));

		assertThat(currencies.containsElement(EURO), is(true));
		assertThat(currencies.containsIndex(978), is(true));
		assertThat(currencies.get(978), is(equalTo(EURO)));
		assertThat(currencies.indexOf(EURO), is(equalTo(978)));
		assertThat(currencies.lastIndexOf(EURO), is(equalTo(978)));

		assertThat(currencies.containsElement(POUND_STERLING), is(true));
		assertThat(currencies.containsIndex(826), is(true));
		assertThat(currencies.get(826), is(equalTo(POUND_STERLING)));
		assertThat(currencies.indexOf(POUND_STERLING), is(equalTo(826)));
		assertThat(currencies.lastIndexOf(POUND_STERLING), is(equalTo(826)));

		assertThat(currencies.containsElement(JAPANESE_YEN), is(true));
		assertThat(currencies.containsIndex(392), is(true));
		assertThat(currencies.get(392), is(equalTo(JAPANESE_YEN)));
		assertThat(currencies.indexOf(JAPANESE_YEN), is(equalTo(392)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN), is(equalTo(392)));

		assertThat(currencies.containsElement(RENMINBI), is(true));
		assertThat(currencies.containsIndex(156), is(true));
		assertThat(currencies.get(156), is(equalTo(RENMINBI)));
		assertThat(currencies.indexOf(RENMINBI), is(equalTo(156)));
		assertThat(currencies.lastIndexOf(RENMINBI), is(equalTo(156)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR), is(true));
		assertThat(currencies.containsIndex(36), is(true));
		assertThat(currencies.get(36), is(equalTo(AUSTRALIAN_DOLLAR)));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR), is(equalTo(36)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR), is(equalTo(36)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR), is(true));
		assertThat(currencies.containsIndex(124), is(true));
		assertThat(currencies.get(124), is(equalTo(CANADIAN_DOLLAR)));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR), is(equalTo(124)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR), is(equalTo(124)));

		assertThat(currencies.containsElement(SWISS_FRANC), is(true));
		assertThat(currencies.containsIndex(756), is(true));
		assertThat(currencies.get(756), is(equalTo(SWISS_FRANC)));
		assertThat(currencies.indexOf(SWISS_FRANC), is(equalTo(756)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC), is(equalTo(756)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR), is(true));
		assertThat(currencies.containsIndex(344), is(true));
		assertThat(currencies.get(344), is(equalTo(HONG_KONG_DOLLAR)));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR), is(equalTo(344)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR), is(equalTo(344)));

		assertThat(currencies.containsElement(THAI_BAHT), is(true));
		assertThat(currencies.containsIndex(764), is(true));
		assertThat(currencies.get(764), is(equalTo(THAI_BAHT)));
		assertThat(currencies.indexOf(THAI_BAHT), is(equalTo(764)));
		assertThat(currencies.lastIndexOf(THAI_BAHT), is(equalTo(764)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR), is(true));
		assertThat(currencies.containsIndex(702), is(true));
		assertThat(currencies.get(702), is(equalTo(SINGAPORE_DOLLAR)));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR), is(equalTo(702)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR), is(equalTo(702)));

		assertThat(currencies.containsElement(SWEDISH_KRONA), is(true));
		assertThat(currencies.containsIndex(752), is(true));
		assertThat(currencies.get(752), is(equalTo(SWEDISH_KRONA)));
		assertThat(currencies.indexOf(SWEDISH_KRONA), is(equalTo(752)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA), is(equalTo(752)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE), is(true));
		assertThat(currencies.containsIndex(578), is(true));
		assertThat(currencies.get(578), is(equalTo(NORWEGIAN_KRONE)));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE), is(equalTo(578)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE), is(equalTo(578)));

		assertThat(currencies.containsElement(POLISH_ZLOTY), is(true));
		assertThat(currencies.containsIndex(985), is(true));
		assertThat(currencies.get(985), is(equalTo(POLISH_ZLOTY)));
		assertThat(currencies.indexOf(POLISH_ZLOTY), is(equalTo(985)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY), is(equalTo(985)));

		assertThat(currencies.containsElement(DANISH_KRONE), is(true));
		assertThat(currencies.containsIndex(208), is(true));
		assertThat(currencies.get(208), is(equalTo(DANISH_KRONE)));
		assertThat(currencies.indexOf(DANISH_KRONE), is(equalTo(208)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE), is(equalTo(208)));

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		assertThat(currencies.remove(156), is(equalTo(RENMINBI)));
		assertThat(currencies.remove(764), is(equalTo(THAI_BAHT)));
		assertThat(currencies.remove(702), is(equalTo(SINGAPORE_DOLLAR)));

		assertThat(currencies.size(), is(equalTo(12)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR), is(true));
		assertThat(currencies.containsIndex(840), is(true));
		assertThat(currencies.get(840), is(equalTo(UNITED_STATES_DOLLAR)));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR), is(equalTo(840)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR), is(equalTo(840)));

		assertThat(currencies.containsElement(EURO), is(true));
		assertThat(currencies.containsIndex(978), is(true));
		assertThat(currencies.get(978), is(equalTo(EURO)));
		assertThat(currencies.indexOf(EURO), is(equalTo(978)));
		assertThat(currencies.lastIndexOf(EURO), is(equalTo(978)));

		assertThat(currencies.containsElement(POUND_STERLING), is(true));
		assertThat(currencies.containsIndex(826), is(true));
		assertThat(currencies.get(826), is(equalTo(POUND_STERLING)));
		assertThat(currencies.indexOf(POUND_STERLING), is(equalTo(826)));
		assertThat(currencies.lastIndexOf(POUND_STERLING), is(equalTo(826)));

		assertThat(currencies.containsElement(JAPANESE_YEN), is(true));
		assertThat(currencies.containsIndex(392), is(true));
		assertThat(currencies.get(392), is(equalTo(JAPANESE_YEN)));
		assertThat(currencies.indexOf(JAPANESE_YEN), is(equalTo(392)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN), is(equalTo(392)));

		assertThat(currencies.containsElement(RENMINBI), is(false));
		assertThat(currencies.containsIndex(156), is(false));
		assertThat(currencies.get(156), is(nullValue()));
		assertThat(currencies.indexOf(RENMINBI), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(RENMINBI), is(lessThan(0)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR), is(true));
		assertThat(currencies.containsIndex(36), is(true));
		assertThat(currencies.get(36), is(equalTo(AUSTRALIAN_DOLLAR)));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR), is(equalTo(36)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR), is(equalTo(36)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR), is(true));
		assertThat(currencies.containsIndex(124), is(true));
		assertThat(currencies.get(124), is(equalTo(CANADIAN_DOLLAR)));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR), is(equalTo(124)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR), is(equalTo(124)));

		assertThat(currencies.containsElement(SWISS_FRANC), is(true));
		assertThat(currencies.containsIndex(756), is(true));
		assertThat(currencies.get(756), is(equalTo(SWISS_FRANC)));
		assertThat(currencies.indexOf(SWISS_FRANC), is(equalTo(756)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC), is(equalTo(756)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR), is(true));
		assertThat(currencies.containsIndex(344), is(true));
		assertThat(currencies.get(344), is(equalTo(HONG_KONG_DOLLAR)));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR), is(equalTo(344)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR), is(equalTo(344)));

		assertThat(currencies.containsElement(THAI_BAHT), is(false));
		assertThat(currencies.containsIndex(764), is(false));
		assertThat(currencies.get(764), is(nullValue()));
		assertThat(currencies.indexOf(THAI_BAHT), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(THAI_BAHT), is(lessThan(0)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR), is(false));
		assertThat(currencies.containsIndex(702), is(false));
		assertThat(currencies.get(702), is(nullValue()));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(SWEDISH_KRONA), is(true));
		assertThat(currencies.containsIndex(752), is(true));
		assertThat(currencies.get(752), is(equalTo(SWEDISH_KRONA)));
		assertThat(currencies.indexOf(SWEDISH_KRONA), is(equalTo(752)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA), is(equalTo(752)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE), is(true));
		assertThat(currencies.containsIndex(578), is(true));
		assertThat(currencies.get(578), is(equalTo(NORWEGIAN_KRONE)));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE), is(equalTo(578)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE), is(equalTo(578)));

		assertThat(currencies.containsElement(POLISH_ZLOTY), is(true));
		assertThat(currencies.containsIndex(985), is(true));
		assertThat(currencies.get(985), is(equalTo(POLISH_ZLOTY)));
		assertThat(currencies.indexOf(POLISH_ZLOTY), is(equalTo(985)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY), is(equalTo(985)));

		assertThat(currencies.containsElement(DANISH_KRONE), is(true));
		assertThat(currencies.containsIndex(208), is(true));
		assertThat(currencies.get(208), is(equalTo(DANISH_KRONE)));
		assertThat(currencies.indexOf(DANISH_KRONE), is(equalTo(208)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE), is(equalTo(208)));

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		currencies.clear();

		assertThat(currencies.size(), is(equalTo(0)));

		assertThat(currencies.containsElement(UNITED_STATES_DOLLAR), is(false));
		assertThat(currencies.containsIndex(840), is(false));
		assertThat(currencies.get(840), is(nullValue()));
		assertThat(currencies.indexOf(UNITED_STATES_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(UNITED_STATES_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(EURO), is(false));
		assertThat(currencies.containsIndex(978), is(false));
		assertThat(currencies.get(978), is(nullValue()));
		assertThat(currencies.indexOf(EURO), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(EURO), is(lessThan(0)));

		assertThat(currencies.containsElement(POUND_STERLING), is(false));
		assertThat(currencies.containsIndex(826), is(false));
		assertThat(currencies.get(826), is(nullValue()));
		assertThat(currencies.indexOf(POUND_STERLING), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(POUND_STERLING), is(lessThan(0)));

		assertThat(currencies.containsElement(JAPANESE_YEN), is(false));
		assertThat(currencies.containsIndex(392), is(false));
		assertThat(currencies.get(392), is(nullValue()));
		assertThat(currencies.indexOf(JAPANESE_YEN), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(JAPANESE_YEN), is(lessThan(0)));

		assertThat(currencies.containsElement(RENMINBI), is(false));
		assertThat(currencies.containsIndex(156), is(false));
		assertThat(currencies.get(156), is(nullValue()));
		assertThat(currencies.indexOf(RENMINBI), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(RENMINBI), is(lessThan(0)));

		assertThat(currencies.containsElement(AUSTRALIAN_DOLLAR), is(false));
		assertThat(currencies.containsIndex(36), is(false));
		assertThat(currencies.get(36), is(nullValue()));
		assertThat(currencies.indexOf(AUSTRALIAN_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(AUSTRALIAN_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(CANADIAN_DOLLAR), is(false));
		assertThat(currencies.containsIndex(124), is(false));
		assertThat(currencies.get(124), is(nullValue()));
		assertThat(currencies.indexOf(CANADIAN_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(CANADIAN_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(SWISS_FRANC), is(false));
		assertThat(currencies.containsIndex(756), is(false));
		assertThat(currencies.get(756), is(nullValue()));
		assertThat(currencies.indexOf(SWISS_FRANC), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWISS_FRANC), is(lessThan(0)));

		assertThat(currencies.containsElement(HONG_KONG_DOLLAR), is(false));
		assertThat(currencies.containsIndex(344), is(false));
		assertThat(currencies.get(344), is(nullValue()));
		assertThat(currencies.indexOf(HONG_KONG_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(HONG_KONG_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(THAI_BAHT), is(false));
		assertThat(currencies.containsIndex(764), is(false));
		assertThat(currencies.get(764), is(nullValue()));
		assertThat(currencies.indexOf(THAI_BAHT), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(THAI_BAHT), is(lessThan(0)));

		assertThat(currencies.containsElement(SINGAPORE_DOLLAR), is(false));
		assertThat(currencies.containsIndex(702), is(false));
		assertThat(currencies.get(702), is(nullValue()));
		assertThat(currencies.indexOf(SINGAPORE_DOLLAR), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SINGAPORE_DOLLAR), is(lessThan(0)));

		assertThat(currencies.containsElement(SWEDISH_KRONA), is(false));
		assertThat(currencies.containsIndex(752), is(false));
		assertThat(currencies.get(752), is(nullValue()));
		assertThat(currencies.indexOf(SWEDISH_KRONA), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(SWEDISH_KRONA), is(lessThan(0)));

		assertThat(currencies.containsElement(NORWEGIAN_KRONE), is(false));
		assertThat(currencies.containsIndex(578), is(false));
		assertThat(currencies.get(578), is(nullValue()));
		assertThat(currencies.indexOf(NORWEGIAN_KRONE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(NORWEGIAN_KRONE), is(lessThan(0)));

		assertThat(currencies.containsElement(POLISH_ZLOTY), is(false));
		assertThat(currencies.containsIndex(985), is(false));
		assertThat(currencies.get(985), is(nullValue()));
		assertThat(currencies.indexOf(POLISH_ZLOTY), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(POLISH_ZLOTY), is(lessThan(0)));

		assertThat(currencies.containsElement(DANISH_KRONE), is(false));
		assertThat(currencies.containsIndex(208), is(false));
		assertThat(currencies.get(208), is(nullValue()));
		assertThat(currencies.indexOf(DANISH_KRONE), is(lessThan(0)));
		assertThat(currencies.lastIndexOf(DANISH_KRONE), is(lessThan(0)));
	}

}
