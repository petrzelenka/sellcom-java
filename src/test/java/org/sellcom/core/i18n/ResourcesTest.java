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
package org.sellcom.core.i18n;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.i18n.Resources.clearResources;
import static org.sellcom.core.i18n.Resources.getQuantityString;
import static org.sellcom.core.i18n.Resources.getString;
import static org.sellcom.core.i18n.Resources.loadResources;
import static org.sellcom.core.i18n.StandardLocales.BRITISH_ENGLISH;
import static org.sellcom.core.i18n.StandardLocales.CZECH;
import static org.sellcom.core.i18n.StandardLocales.FRENCH;
import static org.sellcom.core.i18n.StandardLocales.GERMAN;
import static org.sellcom.core.i18n.StandardLocales.RUSSIAN;

import java.util.Locale;

import org.junit.Test;

public class ResourcesTest {

	@Test
	public void testGetQuantityString_czech() {
		Locale locale = CZECH;

		clearResources(locale);
		loadResources("i18n/QuantityTest", locale);

		assertThat(getQuantityString(locale, "days",  0, 0), is(equalTo("0 dnů")));
		assertThat(getQuantityString(locale, "days",  1, 1), is(equalTo("1 den")));
		assertThat(getQuantityString(locale, "days",  2, 2), is(equalTo("2 dny")));
		assertThat(getQuantityString(locale, "days",  3, 3), is(equalTo("3 dny")));
		assertThat(getQuantityString(locale, "days",  4, 4), is(equalTo("4 dny")));
		assertThat(getQuantityString(locale, "days",  5, 5), is(equalTo("5 dnů")));
		assertThat(getQuantityString(locale, "days",  11, 11), is(equalTo("11 dnů")));
		assertThat(getQuantityString(locale, "days",  12, 12), is(equalTo("12 dnů")));
		assertThat(getQuantityString(locale, "days",  21, 21), is(equalTo("21 dnů")));
		assertThat(getQuantityString(locale, "days",  22, 22), is(equalTo("22 dnů")));
		assertThat(getQuantityString(locale, "days",  31, 31), is(equalTo("31 dnů")));
		assertThat(getQuantityString(locale, "days",  32, 32), is(equalTo("32 dnů")));
		assertThat(getQuantityString(locale, "days",  41, 41), is(equalTo("41 dnů")));
		assertThat(getQuantityString(locale, "days",  42, 42), is(equalTo("42 dnů")));
		assertThat(getQuantityString(locale, "days",  51, 51), is(equalTo("51 dnů")));
		assertThat(getQuantityString(locale, "days",  52, 52), is(equalTo("52 dnů")));

		assertThat(getQuantityString(locale, "hours",  0, 0), is(equalTo("0 hodin")));
		assertThat(getQuantityString(locale, "hours",  1, 1), is(equalTo("1 hodina")));
		assertThat(getQuantityString(locale, "hours",  2, 2), is(equalTo("2 hodiny")));
		assertThat(getQuantityString(locale, "hours",  3, 3), is(equalTo("3 hodiny")));
		assertThat(getQuantityString(locale, "hours",  4, 4), is(equalTo("4 hodiny")));
		assertThat(getQuantityString(locale, "hours",  5, 5), is(equalTo("5 hodin")));
		assertThat(getQuantityString(locale, "hours",  11, 11), is(equalTo("11 hodin")));
		assertThat(getQuantityString(locale, "hours",  12, 12), is(equalTo("12 hodin")));
		assertThat(getQuantityString(locale, "hours",  21, 21), is(equalTo("21 hodin")));
		assertThat(getQuantityString(locale, "hours",  22, 22), is(equalTo("22 hodin")));
		assertThat(getQuantityString(locale, "hours",  31, 31), is(equalTo("31 hodin")));
		assertThat(getQuantityString(locale, "hours",  32, 32), is(equalTo("32 hodin")));
		assertThat(getQuantityString(locale, "hours",  41, 41), is(equalTo("41 hodin")));
		assertThat(getQuantityString(locale, "hours",  42, 42), is(equalTo("42 hodin")));
		assertThat(getQuantityString(locale, "hours",  51, 51), is(equalTo("51 hodin")));
		assertThat(getQuantityString(locale, "hours",  52, 52), is(equalTo("52 hodin")));
	}

	@Test
	public void testGetQuantityString_english() {
		Locale locale = BRITISH_ENGLISH;

		clearResources(locale);
		loadResources("i18n/QuantityTest", locale);

		assertThat(getQuantityString(locale, "days",  0, 0), is(equalTo("0 days")));
		assertThat(getQuantityString(locale, "days",  1, 1), is(equalTo("1 day")));
		assertThat(getQuantityString(locale, "days",  2, 2), is(equalTo("2 days")));
		assertThat(getQuantityString(locale, "days",  3, 3), is(equalTo("3 days")));
		assertThat(getQuantityString(locale, "days",  4, 4), is(equalTo("4 days")));
		assertThat(getQuantityString(locale, "days",  5, 5), is(equalTo("5 days")));
		assertThat(getQuantityString(locale, "days",  11, 11), is(equalTo("11 days")));
		assertThat(getQuantityString(locale, "days",  12, 12), is(equalTo("12 days")));
		assertThat(getQuantityString(locale, "days",  21, 21), is(equalTo("21 days")));
		assertThat(getQuantityString(locale, "days",  22, 22), is(equalTo("22 days")));
		assertThat(getQuantityString(locale, "days",  31, 31), is(equalTo("31 days")));
		assertThat(getQuantityString(locale, "days",  32, 32), is(equalTo("32 days")));
		assertThat(getQuantityString(locale, "days",  41, 41), is(equalTo("41 days")));
		assertThat(getQuantityString(locale, "days",  42, 42), is(equalTo("42 days")));
		assertThat(getQuantityString(locale, "days",  51, 51), is(equalTo("51 days")));
		assertThat(getQuantityString(locale, "days",  52, 52), is(equalTo("52 days")));

		assertThat(getQuantityString(locale, "hours",  0, 0), is(equalTo("0 hours")));
		assertThat(getQuantityString(locale, "hours",  1, 1), is(equalTo("1 hour")));
		assertThat(getQuantityString(locale, "hours",  2, 2), is(equalTo("2 hours")));
		assertThat(getQuantityString(locale, "hours",  3, 3), is(equalTo("3 hours")));
		assertThat(getQuantityString(locale, "hours",  4, 4), is(equalTo("4 hours")));
		assertThat(getQuantityString(locale, "hours",  5, 5), is(equalTo("5 hours")));
		assertThat(getQuantityString(locale, "hours",  11, 11), is(equalTo("11 hours")));
		assertThat(getQuantityString(locale, "hours",  12, 12), is(equalTo("12 hours")));
		assertThat(getQuantityString(locale, "hours",  21, 21), is(equalTo("21 hours")));
		assertThat(getQuantityString(locale, "hours",  22, 22), is(equalTo("22 hours")));
		assertThat(getQuantityString(locale, "hours",  31, 31), is(equalTo("31 hours")));
		assertThat(getQuantityString(locale, "hours",  32, 32), is(equalTo("32 hours")));
		assertThat(getQuantityString(locale, "hours",  41, 41), is(equalTo("41 hours")));
		assertThat(getQuantityString(locale, "hours",  42, 42), is(equalTo("42 hours")));
		assertThat(getQuantityString(locale, "hours",  51, 51), is(equalTo("51 hours")));
		assertThat(getQuantityString(locale, "hours",  52, 52), is(equalTo("52 hours")));
	}

	@Test
	public void testGetQuantityString_french() {
		Locale locale = FRENCH;

		clearResources(locale);
		loadResources("i18n/QuantityTest", locale);

		assertThat(getQuantityString(locale, "days",  0, 0), is(equalTo("0 jour")));
		assertThat(getQuantityString(locale, "days",  1, 1), is(equalTo("1 jour")));
		assertThat(getQuantityString(locale, "days",  2, 2), is(equalTo("2 jours")));
		assertThat(getQuantityString(locale, "days",  3, 3), is(equalTo("3 jours")));
		assertThat(getQuantityString(locale, "days",  4, 4), is(equalTo("4 jours")));
		assertThat(getQuantityString(locale, "days",  5, 5), is(equalTo("5 jours")));
		assertThat(getQuantityString(locale, "days",  11, 11), is(equalTo("11 jours")));
		assertThat(getQuantityString(locale, "days",  12, 12), is(equalTo("12 jours")));
		assertThat(getQuantityString(locale, "days",  21, 21), is(equalTo("21 jours")));
		assertThat(getQuantityString(locale, "days",  22, 22), is(equalTo("22 jours")));
		assertThat(getQuantityString(locale, "days",  31, 31), is(equalTo("31 jours")));
		assertThat(getQuantityString(locale, "days",  32, 32), is(equalTo("32 jours")));
		assertThat(getQuantityString(locale, "days",  41, 41), is(equalTo("41 jours")));
		assertThat(getQuantityString(locale, "days",  42, 42), is(equalTo("42 jours")));
		assertThat(getQuantityString(locale, "days",  51, 51), is(equalTo("51 jours")));
		assertThat(getQuantityString(locale, "days",  52, 52), is(equalTo("52 jours")));

		assertThat(getQuantityString(locale, "hours",  0, 0), is(equalTo("0 heure")));
		assertThat(getQuantityString(locale, "hours",  1, 1), is(equalTo("1 heure")));
		assertThat(getQuantityString(locale, "hours",  2, 2), is(equalTo("2 heures")));
		assertThat(getQuantityString(locale, "hours",  3, 3), is(equalTo("3 heures")));
		assertThat(getQuantityString(locale, "hours",  4, 4), is(equalTo("4 heures")));
		assertThat(getQuantityString(locale, "hours",  5, 5), is(equalTo("5 heures")));
		assertThat(getQuantityString(locale, "hours",  11, 11), is(equalTo("11 heures")));
		assertThat(getQuantityString(locale, "hours",  12, 12), is(equalTo("12 heures")));
		assertThat(getQuantityString(locale, "hours",  21, 21), is(equalTo("21 heures")));
		assertThat(getQuantityString(locale, "hours",  22, 22), is(equalTo("22 heures")));
		assertThat(getQuantityString(locale, "hours",  31, 31), is(equalTo("31 heures")));
		assertThat(getQuantityString(locale, "hours",  32, 32), is(equalTo("32 heures")));
		assertThat(getQuantityString(locale, "hours",  41, 41), is(equalTo("41 heures")));
		assertThat(getQuantityString(locale, "hours",  42, 42), is(equalTo("42 heures")));
		assertThat(getQuantityString(locale, "hours",  51, 51), is(equalTo("51 heures")));
		assertThat(getQuantityString(locale, "hours",  52, 52), is(equalTo("52 heures")));
	}

	@Test
	public void testGetQuantityString_german() {
		Locale locale = GERMAN;

		clearResources(locale);
		loadResources("i18n/QuantityTest", locale);

		assertThat(getQuantityString(locale, "days",  0, 0), is(equalTo("0 Tage")));
		assertThat(getQuantityString(locale, "days",  1, 1), is(equalTo("1 Tag")));
		assertThat(getQuantityString(locale, "days",  2, 2), is(equalTo("2 Tage")));
		assertThat(getQuantityString(locale, "days",  3, 3), is(equalTo("3 Tage")));
		assertThat(getQuantityString(locale, "days",  4, 4), is(equalTo("4 Tage")));
		assertThat(getQuantityString(locale, "days",  5, 5), is(equalTo("5 Tage")));
		assertThat(getQuantityString(locale, "days",  11, 11), is(equalTo("11 Tage")));
		assertThat(getQuantityString(locale, "days",  12, 12), is(equalTo("12 Tage")));
		assertThat(getQuantityString(locale, "days",  21, 21), is(equalTo("21 Tage")));
		assertThat(getQuantityString(locale, "days",  22, 22), is(equalTo("22 Tage")));
		assertThat(getQuantityString(locale, "days",  31, 31), is(equalTo("31 Tage")));
		assertThat(getQuantityString(locale, "days",  32, 32), is(equalTo("32 Tage")));
		assertThat(getQuantityString(locale, "days",  41, 41), is(equalTo("41 Tage")));
		assertThat(getQuantityString(locale, "days",  42, 42), is(equalTo("42 Tage")));
		assertThat(getQuantityString(locale, "days",  51, 51), is(equalTo("51 Tage")));
		assertThat(getQuantityString(locale, "days",  52, 52), is(equalTo("52 Tage")));

		assertThat(getQuantityString(locale, "hours",  0, 0), is(equalTo("0 Stunden")));
		assertThat(getQuantityString(locale, "hours",  1, 1), is(equalTo("1 Stunde")));
		assertThat(getQuantityString(locale, "hours",  2, 2), is(equalTo("2 Stunden")));
		assertThat(getQuantityString(locale, "hours",  3, 3), is(equalTo("3 Stunden")));
		assertThat(getQuantityString(locale, "hours",  4, 4), is(equalTo("4 Stunden")));
		assertThat(getQuantityString(locale, "hours",  5, 5), is(equalTo("5 Stunden")));
		assertThat(getQuantityString(locale, "hours",  11, 11), is(equalTo("11 Stunden")));
		assertThat(getQuantityString(locale, "hours",  12, 12), is(equalTo("12 Stunden")));
		assertThat(getQuantityString(locale, "hours",  21, 21), is(equalTo("21 Stunden")));
		assertThat(getQuantityString(locale, "hours",  22, 22), is(equalTo("22 Stunden")));
		assertThat(getQuantityString(locale, "hours",  31, 31), is(equalTo("31 Stunden")));
		assertThat(getQuantityString(locale, "hours",  32, 32), is(equalTo("32 Stunden")));
		assertThat(getQuantityString(locale, "hours",  41, 41), is(equalTo("41 Stunden")));
		assertThat(getQuantityString(locale, "hours",  42, 42), is(equalTo("42 Stunden")));
		assertThat(getQuantityString(locale, "hours",  51, 51), is(equalTo("51 Stunden")));
		assertThat(getQuantityString(locale, "hours",  52, 52), is(equalTo("52 Stunden")));
	}

	@Test
	public void testGetQuantityString_russian() {
		Locale locale = RUSSIAN;

		clearResources(locale);
		loadResources("i18n/QuantityTest", locale);

		assertThat(getQuantityString(locale, "days",  0, 0), is(equalTo("0 дней")));
		assertThat(getQuantityString(locale, "days",  1, 1), is(equalTo("1 день")));
		assertThat(getQuantityString(locale, "days",  2, 2), is(equalTo("2 дня")));
		assertThat(getQuantityString(locale, "days",  3, 3), is(equalTo("3 дня")));
		assertThat(getQuantityString(locale, "days",  4, 4), is(equalTo("4 дня")));
		assertThat(getQuantityString(locale, "days",  5, 5), is(equalTo("5 дней")));
		assertThat(getQuantityString(locale, "days",  11, 11), is(equalTo("11 дней")));
		assertThat(getQuantityString(locale, "days",  12, 12), is(equalTo("12 дней")));
		assertThat(getQuantityString(locale, "days",  21, 21), is(equalTo("21 день")));
		assertThat(getQuantityString(locale, "days",  22, 22), is(equalTo("22 дня")));
		assertThat(getQuantityString(locale, "days",  31, 31), is(equalTo("31 день")));
		assertThat(getQuantityString(locale, "days",  32, 32), is(equalTo("32 дня")));
		assertThat(getQuantityString(locale, "days",  41, 41), is(equalTo("41 день")));
		assertThat(getQuantityString(locale, "days",  42, 42), is(equalTo("42 дня")));
		assertThat(getQuantityString(locale, "days",  51, 51), is(equalTo("51 день")));
		assertThat(getQuantityString(locale, "days",  52, 52), is(equalTo("52 дня")));

		assertThat(getQuantityString(locale, "hours",  0, 0), is(equalTo("0 часов")));
		assertThat(getQuantityString(locale, "hours",  1, 1), is(equalTo("1 час")));
		assertThat(getQuantityString(locale, "hours",  2, 2), is(equalTo("2 часа")));
		assertThat(getQuantityString(locale, "hours",  3, 3), is(equalTo("3 часа")));
		assertThat(getQuantityString(locale, "hours",  4, 4), is(equalTo("4 часа")));
		assertThat(getQuantityString(locale, "hours",  5, 5), is(equalTo("5 часов")));
		assertThat(getQuantityString(locale, "hours",  11, 11), is(equalTo("11 часов")));
		assertThat(getQuantityString(locale, "hours",  12, 12), is(equalTo("12 часов")));
		assertThat(getQuantityString(locale, "hours",  21, 21), is(equalTo("21 час")));
		assertThat(getQuantityString(locale, "hours",  22, 22), is(equalTo("22 часа")));
		assertThat(getQuantityString(locale, "hours",  31, 31), is(equalTo("31 час")));
		assertThat(getQuantityString(locale, "hours",  32, 32), is(equalTo("32 часа")));
		assertThat(getQuantityString(locale, "hours",  41, 41), is(equalTo("41 час")));
		assertThat(getQuantityString(locale, "hours",  42, 42), is(equalTo("42 часа")));
		assertThat(getQuantityString(locale, "hours",  51, 51), is(equalTo("51 час")));
		assertThat(getQuantityString(locale, "hours",  52, 52), is(equalTo("52 часа")));
	}

	@Test
	public void testReferences() {
		clearResources();
		loadResources("i18n/ReferencesTest");

		assertThat(getString("org.fao.title"), is(equalTo("Food and Agriculture Organization")));
		assertThat(getString("org.iaea.title"), is(equalTo("International Atomic Energy Agency")));
		assertThat(getString("org.icao.title"), is(equalTo("International Civil Aviation Organization")));
		assertThat(getString("org.ifad.title"), is(equalTo("International Fund for Agricultural Development")));
		assertThat(getString("org.ilo.title"), is(equalTo("International Labour Organization")));
		assertThat(getString("org.imf.title"), is(equalTo("International Monetary Fund")));
		assertThat(getString("org.imo.title"), is(equalTo("International Maritime Organization")));
		assertThat(getString("org.itu.title"), is(equalTo("International Telecommunication Union")));
		assertThat(getString("org.un.title"), is(equalTo("United Nations")));
		assertThat(getString("org.unesco.title"), is(equalTo("United Nations Educational, Scientific and Cultural Organization")));
		assertThat(getString("org.unido.title"), is(equalTo("United Nations Industrial Development Organization")));
		assertThat(getString("org.upu.title"), is(equalTo("Universal Postal Union")));
		assertThat(getString("org.wbg.title"), is(equalTo("World Bank Group")));
		assertThat(getString("org.wfp.title"), is(equalTo("World Food Programme")));
		assertThat(getString("org.who.title"), is(equalTo("World Health Organization")));
		assertThat(getString("org.wipo.title"), is(equalTo("World Intellectual Property Organization")));
		assertThat(getString("org.wmo.title"), is(equalTo("World Meteorological Organization")));
		assertThat(getString("org.wto.title"), is(equalTo("World Tourism Organization")));
	}

	@Test
	public void testUtf8() {
		clearResources();
		loadResources("i18n/Utf8Test");

		assertThat(getString("state.at"), is(equalTo("Republik Österreich")));
		assertThat(getString("state.be"), is(equalTo("Koninkrijk België/Royaume de Belgique/Königreich Belgien")));
		assertThat(getString("state.bg"), is(equalTo("Република България")));
		assertThat(getString("state.cy"), is(equalTo("Κυπριακή Δημοκρατία/Kıbrıs Cumhuriyeti")));
		assertThat(getString("state.cz"), is(equalTo("Česká republika")));
		assertThat(getString("state.de"), is(equalTo("Bundesrepublik Deutschland")));
		assertThat(getString("state.dk"), is(equalTo("Kongeriget Danmark")));
		assertThat(getString("state.ee"), is(equalTo("Eesti Vabariik")));
		assertThat(getString("state.es"), is(equalTo("Reino de España")));
		assertThat(getString("state.fi"), is(equalTo("Suomen tasavalta/Republiken Finland")));
		assertThat(getString("state.fr"), is(equalTo("République française")));
		assertThat(getString("state.gb"), is(equalTo("United Kingdom of Great Britain and Northern Ireland")));
		assertThat(getString("state.gr"), is(equalTo("Ελληνική Δημοκρατία")));
		assertThat(getString("state.hr"), is(equalTo("Republika Hrvatska")));
		assertThat(getString("state.hu"), is(equalTo("Magyarország")));
		assertThat(getString("state.ie"), is(equalTo("Éire")));
		assertThat(getString("state.it"), is(equalTo("Repubblica Italiana")));
		assertThat(getString("state.lt"), is(equalTo("Lietuvos Respublika")));
		assertThat(getString("state.lu"), is(equalTo("Groussherzogtum Lëtzebuerg/Großherzogtum Luxemburg/Grand-Duché de Luxembourg")));
		assertThat(getString("state.lv"), is(equalTo("Latvijas Republika")));
		assertThat(getString("state.mt"), is(equalTo("Repubblika ta' Malta")));
		assertThat(getString("state.nl"), is(equalTo("Nederland")));
		assertThat(getString("state.pl"), is(equalTo("Rzeczpospolita Polska")));
		assertThat(getString("state.pt"), is(equalTo("República Portuguesa")));
		assertThat(getString("state.ro"), is(equalTo("România")));
		assertThat(getString("state.si"), is(equalTo("Republika Slovenija")));
		assertThat(getString("state.sk"), is(equalTo("Slovenská republika")));
		assertThat(getString("state.sv"), is(equalTo("Konungariket Sverige")));
	}

}
