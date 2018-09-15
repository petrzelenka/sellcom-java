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
package org.sellcom.core;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.StringBuilders.endsWith;
import static org.sellcom.core.StringBuilders.ensurePrefix;
import static org.sellcom.core.StringBuilders.ensureSuffix;
import static org.sellcom.core.StringBuilders.normalizeWhitespace;
import static org.sellcom.core.StringBuilders.removeAll;
import static org.sellcom.core.StringBuilders.removePrefix;
import static org.sellcom.core.StringBuilders.removeSuffix;
import static org.sellcom.core.StringBuilders.replaceAll;
import static org.sellcom.core.StringBuilders.startsWith;
import static org.sellcom.core.StringBuilders.trimLeft;
import static org.sellcom.core.StringBuilders.trimRight;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

public class StringBuildersTest {

	@Test
	public void testCompactCharacterRuns() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "bookkeeper");
		StringBuilders.compactCharacterRuns(builder);
		assertThat(builder.toString(), is(equalTo("bokeper")));

		setContents(builder, "cioccolata");
		StringBuilders.compactCharacterRuns(builder);
		assertThat(builder.toString(), is(equalTo("ciocolata")));

		setContents(builder, "Mississippi");
		StringBuilders.compactCharacterRuns(builder);
		assertThat(builder.toString(), is(equalTo("Misisipi")));

		setContents(builder, "Flussschifffahrt");
		StringBuilders.compactCharacterRuns(builder);
		assertThat(builder.toString(), is(equalTo("Fluschifahrt")));

		setContents(builder, "skillless");
		StringBuilders.compactCharacterRuns(builder);
		assertThat(builder.toString(), is(equalTo("skiles")));
	}

	@Test
	public void testEndsWith() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "DownHill");
		assertThat(endsWith(builder, "hill", false), is(false));

		setContents(builder, "DownHill");
		assertThat(endsWith(builder, "hill", true), is(true));

		setContents(builder, "UpHill");
		assertThat(endsWith(builder, "hill", false), is(false));

		setContents(builder, "UpHill");
		assertThat(endsWith(builder, "hill", true), is(true));

		setContents(builder, "DownLink");
		assertThat(endsWith(builder, "link", false), is(false));

		setContents(builder, "DownLink");
		assertThat(endsWith(builder, "link", true), is(true));

		setContents(builder, "UpLink");
		assertThat(endsWith(builder, "link", false), is(false));

		setContents(builder, "UpLink");
		assertThat(endsWith(builder, "link", true), is(true));

		setContents(builder, "DownSide");
		assertThat(endsWith(builder, "side", false), is(false));

		setContents(builder, "DownSide");
		assertThat(endsWith(builder, "side", true), is(true));

		setContents(builder, "UpSide");
		assertThat(endsWith(builder, "side", false), is(false));

		setContents(builder, "UpSide");
		assertThat(endsWith(builder, "side", true), is(true));

		setContents(builder, "DownTime");
		assertThat(endsWith(builder, "time", false), is(false));

		setContents(builder, "DownTime");
		assertThat(endsWith(builder, "time", true), is(true));

		setContents(builder, "UpTime");
		assertThat(endsWith(builder, "time", false), is(false));

		setContents(builder, "UpTime");
		assertThat(endsWith(builder, "time", true), is(true));

		setContents(builder, "DownWind");
		assertThat(endsWith(builder, "wind", false), is(false));

		setContents(builder, "DownWind");
		assertThat(endsWith(builder, "wind", true), is(true));

		setContents(builder, "UpWind");
		assertThat(endsWith(builder, "wind", false), is(false));

		setContents(builder, "UpWind");
		assertThat(endsWith(builder, "wind", true), is(true));
	}

	@Test
	public void testNormalizeWhitespace() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, " Republica Moldova ");
		normalizeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("Republica Moldova")));

		setContents(builder, "    Republica  Moldova    ");
		normalizeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("Republica Moldova")));

		setContents(builder, "\tRepublica\tMoldova\t");
		normalizeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("Republica Moldova")));

		setContents(builder, "\t\t\t\tRepublica\t\tMoldova\t\t\t\t");
		normalizeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("Republica Moldova")));

		setContents(builder, " \t Republica \t Moldova \t ");
		normalizeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("Republica Moldova")));
	}

	@Test
	public void testRemoveAll_char() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "Donau dampf schiff fahrt");
		removeAll(builder, ' ');
		assertThat(builder.toString(), is(equalTo("Donaudampfschifffahrt")));

		setContents(builder, "Kraft fahrzeug haft pflicht versicherung");
		removeAll(builder, ' ');
		assertThat(builder.toString(), is(equalTo("Kraftfahrzeughaftpflichtversicherung")));

		setContents(builder, "Glas flächen reinigung");
		removeAll(builder, ' ');
		assertThat(builder.toString(), is(equalTo("Glasflächenreinigung")));

		setContents(builder, "Sieben tausend zwei hundert vier und fünfzig");
		removeAll(builder, ' ');
		assertThat(builder.toString(), is(equalTo("Siebentausendzweihundertvierundfünfzig")));
	}

	@Test
	public void testRemoveAll_String() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "backpack");
		removeAll(builder, "ack");
		assertThat(builder.toString(), is(equalTo("bp")));

		setContents(builder, "braindrain");
		removeAll(builder, "rain");
		assertThat(builder.toString(), is(equalTo("bd")));

		setContents(builder, "output");
		removeAll(builder, "ut");
		assertThat(builder.toString(), is(equalTo("op")));
	}

	@Test
	public void testRemoveWhitespace() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "after noon");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("afternoon")));

		setContents(builder, "al fræði orða bók");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("alfræðiorðabók")));

		setContents(builder, "door groei mogelijkheden");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("doorgroeimogelijkheden")));

		setContents(builder, "Eisen bahn");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("Eisenbahn")));

		setContents(builder, "every day");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("everyday")));

		setContents(builder, "ferro carril");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("ferrocarril")));

		setContents(builder, "ferro via");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("ferrovia")));

		setContents(builder, "grass hopper");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("grasshopper")));

		setContents(builder, "Kraft fahr zeug");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("Kraftfahrzeug")));

		setContents(builder, "limpia para brisas");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("limpiaparabrisas")));

		setContents(builder, "moon light");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("moonlight")));

		setContents(builder, "Ox ford");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("Oxford")));

		setContents(builder, "pass port");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("passport")));

		setContents(builder, "pepper mint");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("peppermint")));

		setContents(builder, "psycho the rapist");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("psychotherapist")));

		setContents(builder, "rail way");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("railway")));

		setContents(builder, "rauta tie asema");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("rautatieasema")));

		setContents(builder, "røyk fritt");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("røykfritt")));

		setContents(builder, "town ship");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("township")));

		setContents(builder, "week end");
		StringBuilders.removeWhitespace(builder);
		assertThat(builder.toString(), is(equalTo("weekend")));
	}

	@Test
	public void testReplaceAll_char() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "Ächtung");
		replaceAll(builder, 'Ä', 'A');
		assertThat(builder.toString(), is(equalTo("Achtung")));

		setContents(builder, "drücken");
		replaceAll(builder, 'ü', 'u');
		assertThat(builder.toString(), is(equalTo("drucken")));

		setContents(builder, "précédence");
		replaceAll(builder, 'é', 'e');
		assertThat(builder.toString(), is(equalTo("precedence")));

		setContents(builder, "résumé");
		replaceAll(builder, 'é', 'e');
		assertThat(builder.toString(), is(equalTo("resume")));

		setContents(builder, "Société Générale");
		replaceAll(builder, 'é', 'e');
		assertThat(builder.toString(), is(equalTo("Societe Generale")));

		setContents(builder, "zählen");
		replaceAll(builder, 'ä', 'a');
		assertThat(builder.toString(), is(equalTo("zahlen")));
	}

	@Test
	public void testReplaceAll_String() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "façon");
		replaceAll(builder, "ç", "shi");
		assertThat(builder.toString(), is(equalTo("fashion")));

		setContents(builder, "Geschäftstätigkeit");
		replaceAll(builder, "ä", "ae");
		assertThat(builder.toString(), is(equalTo("Geschaeftstaetigkeit")));

		setContents(builder, "manœuvre");
		replaceAll(builder, "œ", "oe");
		assertThat(builder.toString(), is(equalTo("manoeuvre")));

		setContents(builder, "Weißrußland");
		replaceAll(builder, "ß", "ss");
		assertThat(builder.toString(), is(equalTo("Weissrussland")));
	}

	@Test
	public void testStartsWith() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "DownHill");
		assertThat(startsWith(builder, "down", false), is(false));

		setContents(builder, "DownHill");
		assertThat(startsWith(builder, "down", true), is(true));

		setContents(builder, "UpHill");
		assertThat(startsWith(builder, "up", false), is(false));

		setContents(builder, "UpHill");
		assertThat(startsWith(builder, "up", true), is(true));

		setContents(builder, "DownLink");
		assertThat(startsWith(builder, "down", false), is(false));

		setContents(builder, "DownLink");
		assertThat(startsWith(builder, "down", true), is(true));

		setContents(builder, "UpLink");
		assertThat(startsWith(builder, "up", false), is(false));

		setContents(builder, "UpLink");
		assertThat(startsWith(builder, "up", true), is(true));

		setContents(builder, "DownSide");
		assertThat(startsWith(builder, "down", false), is(false));

		setContents(builder, "DownSide");
		assertThat(startsWith(builder, "down", true), is(true));

		setContents(builder, "UpSide");
		assertThat(startsWith(builder, "up", false), is(false));

		setContents(builder, "UpSide");
		assertThat(startsWith(builder, "up", true), is(true));

		setContents(builder, "DownTime");
		assertThat(startsWith(builder, "down", false), is(false));

		setContents(builder, "DownTime");
		assertThat(startsWith(builder, "down", true), is(true));

		setContents(builder, "UpTime");
		assertThat(startsWith(builder, "up", false), is(false));

		setContents(builder, "UpTime");
		assertThat(startsWith(builder, "up", true), is(true));

		setContents(builder, "DownWind");
		assertThat(startsWith(builder, "down", false), is(false));

		setContents(builder, "DownWind");
		assertThat(startsWith(builder, "down", true), is(true));

		setContents(builder, "UpWind");
		assertThat(startsWith(builder, "up", false), is(false));

		setContents(builder, "UpWind");
		assertThat(startsWith(builder, "up", true), is(true));
	}

	@Test
	public void testTrimLeft() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "\t \r \n Independence Day \t \r \n");
		trimLeft(builder);
		assertThat(builder.toString(), is(equalTo("Independence Day \t \r \n")));
	}

	@Test
	public void testTrimRight() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "\t \r \n Independence Day \t \r \n");
		trimRight(builder);
		assertThat(builder.toString(), is(equalTo("\t \r \n Independence Day")));
	}


	private static void setContents(StringBuilder builder, String contents) {
		builder.setLength(0);
		builder.append(contents);
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class EnsurePrefixTest {

		private final String expectedResult;

		private final String original;

		private final String prefix;


		public EnsurePrefixTest(ParameterValues values) {
			original = values.getStem();
			prefix = values.getPrefix();
			expectedResult = prefix + original;
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					new ParameterValues("after", "noon"),
					new ParameterValues("air", "craft"),
					new ParameterValues("air", "field"),
					new ParameterValues("air", "line"),
					new ParameterValues("air", "plane"),
					new ParameterValues("air", "port"),
					new ParameterValues("arch", "angel"),
					new ParameterValues("back", "bone"),
					new ParameterValues("back", "ground"),
					new ParameterValues("back", "hoe"),
					new ParameterValues("back", "log"),
					new ParameterValues("back", "pack"),
					new ParameterValues("back", "stage"),
					new ParameterValues("bag", "pipe"),
					new ParameterValues("base", "ball"),
					new ParameterValues("basket", "ball"),
					new ParameterValues("be", "come"),
					new ParameterValues("bed", "clothes"),
					new ParameterValues("bed", "room"),
					new ParameterValues("black", "bird"),
					new ParameterValues("black", "board"),
					new ParameterValues("black", "list"),
					new ParameterValues("black", "out"),
					new ParameterValues("black", "smith"),
					new ParameterValues("blue", "berry"),
					new ParameterValues("blue", "fish"),
					new ParameterValues("blue", "print"),
					new ParameterValues("board", "walk"),
					new ParameterValues("body", "guard"),
					new ParameterValues("body", "work"),
					new ParameterValues("book", "case"),
					new ParameterValues("book", "shelf"),
					new ParameterValues("book", "worm"),
					new ParameterValues("boot", "strap"),
					new ParameterValues("butter", "milk"),
					new ParameterValues("candle", "light"),
					new ParameterValues("candle", "stick"),
					new ParameterValues("car", "pool"),
					new ParameterValues("card", "board"),
					new ParameterValues("cat", "walk"),
					new ParameterValues("cheese", "cake"),
					new ParameterValues("clock", "wise"),
					new ParameterValues("coffee", "maker"),
					new ParameterValues("come", "back"),
					new ParameterValues("common", "place"),
					new ParameterValues("counter", "part"),
					new ParameterValues("court", "house"),
					new ParameterValues("court", "yard"),
					new ParameterValues("cross", "bow"),
					new ParameterValues("cross", "over"),
					new ParameterValues("day", "light"),
					new ParameterValues("day", "time"),
					new ParameterValues("dead", "line"),
					new ParameterValues("dish", "cloth"),
					new ParameterValues("down", "size"),
					new ParameterValues("down", "stairs"),
					new ParameterValues("drive", "way"),
					new ParameterValues("ear", "ring"),
					new ParameterValues("earth", "worm"),
					new ParameterValues("egg", "shell"),
					new ParameterValues("every", "thing"),
					new ParameterValues("eye", "ball"),
					new ParameterValues("eye", "witness"),
					new ParameterValues("fire", "fighter"),
					new ParameterValues("foot", "hill"),
					new ParameterValues("foot", "note"),
					new ParameterValues("foot", "print"),
					new ParameterValues("for", "give"),
					new ParameterValues("fork", "lift"),
					new ParameterValues("friend", "ship"),
					new ParameterValues("gear", "shift"),
					new ParameterValues("glass", "maker"),
					new ParameterValues("grass", "hopper"),
					new ParameterValues("grave", "yard"),
					new ParameterValues("hair", "cut"),
					new ParameterValues("hand", "book"),
					new ParameterValues("hand", "gun"),
					new ParameterValues("hand", "made"),
					new ParameterValues("head", "ache"),
					new ParameterValues("head", "light"),
					new ParameterValues("here", "in"),
					new ParameterValues("high", "land"),
					new ParameterValues("high", "way"),
					new ParameterValues("home", "made"),
					new ParameterValues("home", "town"),
					new ParameterValues("honey", "comb"),
					new ParameterValues("honey", "moon"),
					new ParameterValues("horse", "back"),
					new ParameterValues("horse", "power"),
					new ParameterValues("house", "work"),
					new ParameterValues("key", "board"),
					new ParameterValues("key", "hole"),
					new ParameterValues("key", "note"),
					new ParameterValues("key", "word"),
					new ParameterValues("life", "boat"),
					new ParameterValues("life", "guard"),
					new ParameterValues("life", "time"),
					new ParameterValues("lime", "stone"),
					new ParameterValues("long", "house"),
					new ParameterValues("main", "land"),
					new ParameterValues("main", "line"),
					new ParameterValues("match", "box"),
					new ParameterValues("mean", "time"),
					new ParameterValues("moon", "light"),
					new ParameterValues("mother", "hood"),
					new ParameterValues("near", "by"),
					new ParameterValues("news", "paper"),
					new ParameterValues("one", "self"),
					new ParameterValues("over", "flow"),
					new ParameterValues("pan", "cake"),
					new ParameterValues("pass", "book"),
					new ParameterValues("pass", "port"),
					new ParameterValues("pepper", "mint"),
					new ParameterValues("pin", "hole"),
					new ParameterValues("rail", "road"),
					new ParameterValues("rail", "way"),
					new ParameterValues("rain", "bow"),
					new ParameterValues("rain", "coat"),
					new ParameterValues("rain", "drop"),
					new ParameterValues("rain", "storm"),
					new ParameterValues("sail", "boat"),
					new ParameterValues("sand", "stone"),
					new ParameterValues("school", "house"),
					new ParameterValues("sea", "shore"),
					new ParameterValues("shoe", "maker"),
					new ParameterValues("side", "walk"),
					new ParameterValues("silver", "smith"),
					new ParameterValues("sky", "scraper"),
					new ParameterValues("snow", "ball"),
					new ParameterValues("snow", "drift"),
					new ParameterValues("some", "thing"),
					new ParameterValues("sound", "proof"),
					new ParameterValues("steam", "boat"),
					new ParameterValues("sun", "dial"),
					new ParameterValues("sun", "flower"),
					new ParameterValues("sun", "glasses"),
					new ParameterValues("super", "market"),
					new ParameterValues("table", "cloth"),
					new ParameterValues("tea", "pot"),
					new ParameterValues("text", "book"),
					new ParameterValues("thunder", "bird"),
					new ParameterValues("thunder", "storm"),
					new ParameterValues("time", "table"),
					new ParameterValues("tool", "box"),
					new ParameterValues("town", "ship"),
					new ParameterValues("under", "belly"),
					new ParameterValues("under", "clothes"),
					new ParameterValues("under", "cover"),
					new ParameterValues("under", "ground"),
					new ParameterValues("under", "pass"),
					new ParameterValues("up", "hill"),
					new ParameterValues("up", "lift"),
					new ParameterValues("up", "link"),
					new ParameterValues("up", "right"),
					new ParameterValues("up", "rising"),
					new ParameterValues("up", "side"),
					new ParameterValues("up", "stairs"),
					new ParameterValues("up", "time"),
					new ParameterValues("up", "wind"),
					new ParameterValues("wall", "paper"),
					new ParameterValues("ware", "house"),
					new ParameterValues("wash", "cloth"),
					new ParameterValues("wash", "room"),
					new ParameterValues("waste", "land"),
					new ParameterValues("waste", "water"),
					new ParameterValues("watch", "maker"),
					new ParameterValues("water", "color"),
					new ParameterValues("water", "mark"),
					new ParameterValues("water", "proof"),
					new ParameterValues("water", "shed"),
					new ParameterValues("water", "way"),
					new ParameterValues("weather", "proof"),
					new ParameterValues("week", "day"),
					new ParameterValues("week", "end"),
					new ParameterValues("wheel", "barrow"),
					new ParameterValues("wheel", "chair"),
			});
		}

		@Test
		public void test() {
			StringBuilder builder = new StringBuilder(original);
			ensurePrefix(builder, prefix);
			assertThat(builder.toString(), is(equalTo(expectedResult)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final String prefix;

			private final String stem;


			public ParameterValues(String prefix, String stem) {
				this.prefix = prefix;
				this.stem = stem;
			}


			public String getStem() {
				return stem;
			}

			public String getPrefix() {
				return prefix;
			}

		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class EnsureSuffixTest {

		private final String expectedResult;

		private final String original;

		private final String suffix;


		public EnsureSuffixTest(ParameterValues values) {
			original = values.getStem();
			suffix = values.getSuffix();
			expectedResult = original + suffix;
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
				new ParameterValues("after", "noon"),
				new ParameterValues("air", "craft"),
				new ParameterValues("air", "field"),
				new ParameterValues("air", "line"),
				new ParameterValues("air", "plane"),
				new ParameterValues("air", "port"),
				new ParameterValues("arch", "angel"),
				new ParameterValues("back", "bone"),
				new ParameterValues("back", "ground"),
				new ParameterValues("back", "hoe"),
				new ParameterValues("back", "log"),
				new ParameterValues("back", "pack"),
				new ParameterValues("back", "stage"),
				new ParameterValues("bag", "pipe"),
				new ParameterValues("base", "ball"),
				new ParameterValues("basket", "ball"),
				new ParameterValues("be", "come"),
				new ParameterValues("bed", "clothes"),
				new ParameterValues("bed", "room"),
				new ParameterValues("black", "bird"),
				new ParameterValues("black", "board"),
				new ParameterValues("black", "list"),
				new ParameterValues("black", "out"),
				new ParameterValues("black", "smith"),
				new ParameterValues("blue", "berry"),
				new ParameterValues("blue", "fish"),
				new ParameterValues("blue", "print"),
				new ParameterValues("board", "walk"),
				new ParameterValues("body", "guard"),
				new ParameterValues("body", "work"),
				new ParameterValues("book", "case"),
				new ParameterValues("book", "shelf"),
				new ParameterValues("book", "worm"),
				new ParameterValues("boot", "strap"),
				new ParameterValues("butter", "milk"),
				new ParameterValues("candle", "light"),
				new ParameterValues("candle", "stick"),
				new ParameterValues("car", "pool"),
				new ParameterValues("card", "board"),
				new ParameterValues("cat", "walk"),
				new ParameterValues("cheese", "cake"),
				new ParameterValues("clock", "wise"),
				new ParameterValues("coffee", "maker"),
				new ParameterValues("come", "back"),
				new ParameterValues("common", "place"),
				new ParameterValues("counter", "part"),
				new ParameterValues("court", "house"),
				new ParameterValues("court", "yard"),
				new ParameterValues("cross", "bow"),
				new ParameterValues("cross", "over"),
				new ParameterValues("day", "light"),
				new ParameterValues("day", "time"),
				new ParameterValues("dead", "line"),
				new ParameterValues("dish", "cloth"),
				new ParameterValues("down", "size"),
				new ParameterValues("down", "stairs"),
				new ParameterValues("drive", "way"),
				new ParameterValues("ear", "ring"),
				new ParameterValues("earth", "worm"),
				new ParameterValues("egg", "shell"),
				new ParameterValues("every", "thing"),
				new ParameterValues("eye", "ball"),
				new ParameterValues("eye", "witness"),
				new ParameterValues("fire", "fighter"),
				new ParameterValues("foot", "hill"),
				new ParameterValues("foot", "note"),
				new ParameterValues("foot", "print"),
				new ParameterValues("for", "give"),
				new ParameterValues("fork", "lift"),
				new ParameterValues("friend", "ship"),
				new ParameterValues("gear", "shift"),
				new ParameterValues("glass", "maker"),
				new ParameterValues("grass", "hopper"),
				new ParameterValues("grave", "yard"),
				new ParameterValues("hair", "cut"),
				new ParameterValues("hand", "book"),
				new ParameterValues("hand", "gun"),
				new ParameterValues("hand", "made"),
				new ParameterValues("head", "ache"),
				new ParameterValues("head", "light"),
				new ParameterValues("here", "in"),
				new ParameterValues("high", "land"),
				new ParameterValues("high", "way"),
				new ParameterValues("home", "made"),
				new ParameterValues("home", "town"),
				new ParameterValues("honey", "comb"),
				new ParameterValues("honey", "moon"),
				new ParameterValues("horse", "back"),
				new ParameterValues("horse", "power"),
				new ParameterValues("house", "work"),
				new ParameterValues("key", "board"),
				new ParameterValues("key", "hole"),
				new ParameterValues("key", "note"),
				new ParameterValues("key", "word"),
				new ParameterValues("life", "boat"),
				new ParameterValues("life", "guard"),
				new ParameterValues("life", "time"),
				new ParameterValues("lime", "stone"),
				new ParameterValues("long", "house"),
				new ParameterValues("main", "land"),
				new ParameterValues("main", "line"),
				new ParameterValues("match", "box"),
				new ParameterValues("mean", "time"),
				new ParameterValues("moon", "light"),
				new ParameterValues("mother", "hood"),
				new ParameterValues("near", "by"),
				new ParameterValues("news", "paper"),
				new ParameterValues("one", "self"),
				new ParameterValues("over", "flow"),
				new ParameterValues("pan", "cake"),
				new ParameterValues("pass", "book"),
				new ParameterValues("pass", "port"),
				new ParameterValues("pepper", "mint"),
				new ParameterValues("pin", "hole"),
				new ParameterValues("rail", "road"),
				new ParameterValues("rail", "way"),
				new ParameterValues("rain", "bow"),
				new ParameterValues("rain", "coat"),
				new ParameterValues("rain", "drop"),
				new ParameterValues("rain", "storm"),
				new ParameterValues("sail", "boat"),
				new ParameterValues("sand", "stone"),
				new ParameterValues("school", "house"),
				new ParameterValues("sea", "shore"),
				new ParameterValues("shoe", "maker"),
				new ParameterValues("side", "walk"),
				new ParameterValues("silver", "smith"),
				new ParameterValues("sky", "scraper"),
				new ParameterValues("snow", "ball"),
				new ParameterValues("snow", "drift"),
				new ParameterValues("some", "thing"),
				new ParameterValues("sound", "proof"),
				new ParameterValues("steam", "boat"),
				new ParameterValues("sun", "dial"),
				new ParameterValues("sun", "flower"),
				new ParameterValues("sun", "glasses"),
				new ParameterValues("super", "market"),
				new ParameterValues("table", "cloth"),
				new ParameterValues("tea", "pot"),
				new ParameterValues("text", "book"),
				new ParameterValues("thunder", "bird"),
				new ParameterValues("thunder", "storm"),
				new ParameterValues("time", "table"),
				new ParameterValues("tool", "box"),
				new ParameterValues("town", "ship"),
				new ParameterValues("under", "belly"),
				new ParameterValues("under", "clothes"),
				new ParameterValues("under", "cover"),
				new ParameterValues("under", "ground"),
				new ParameterValues("under", "pass"),
				new ParameterValues("up", "hill"),
				new ParameterValues("up", "lift"),
				new ParameterValues("up", "link"),
				new ParameterValues("up", "right"),
				new ParameterValues("up", "rising"),
				new ParameterValues("up", "side"),
				new ParameterValues("up", "stairs"),
				new ParameterValues("up", "time"),
				new ParameterValues("up", "wind"),
				new ParameterValues("wall", "paper"),
				new ParameterValues("ware", "house"),
				new ParameterValues("wash", "cloth"),
				new ParameterValues("wash", "room"),
				new ParameterValues("waste", "land"),
				new ParameterValues("waste", "water"),
				new ParameterValues("watch", "maker"),
				new ParameterValues("water", "color"),
				new ParameterValues("water", "mark"),
				new ParameterValues("water", "proof"),
				new ParameterValues("water", "shed"),
				new ParameterValues("water", "way"),
				new ParameterValues("weather", "proof"),
				new ParameterValues("week", "day"),
				new ParameterValues("week", "end"),
				new ParameterValues("wheel", "barrow"),
				new ParameterValues("wheel", "chair"),
			});
		}

		@Test
		public void test() {
			StringBuilder builder = new StringBuilder(original);
			ensureSuffix(builder, suffix);
			assertThat(builder.toString(), is(equalTo(expectedResult)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final String stem;

			private final String suffix;


			public ParameterValues(String stem, String suffix) {
				this.stem = stem;
				this.suffix = suffix;
			}


			public String getStem() {
				return stem;
			}

			public String getSuffix() {
				return suffix;
			}

		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class RemovePrefixTest {

		private final String expectedResult;

		private final String original;

		private final String prefix;


		public RemovePrefixTest(ParameterValues values) {
			prefix = values.getPrefix();
			expectedResult = values.getStem();
			original = prefix + expectedResult;
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
					new ParameterValues("after", "noon"),
					new ParameterValues("air", "craft"),
					new ParameterValues("air", "field"),
					new ParameterValues("air", "line"),
					new ParameterValues("air", "plane"),
					new ParameterValues("air", "port"),
					new ParameterValues("arch", "angel"),
					new ParameterValues("back", "bone"),
					new ParameterValues("back", "ground"),
					new ParameterValues("back", "hoe"),
					new ParameterValues("back", "log"),
					new ParameterValues("back", "pack"),
					new ParameterValues("back", "stage"),
					new ParameterValues("bag", "pipe"),
					new ParameterValues("base", "ball"),
					new ParameterValues("basket", "ball"),
					new ParameterValues("be", "come"),
					new ParameterValues("bed", "clothes"),
					new ParameterValues("bed", "room"),
					new ParameterValues("black", "bird"),
					new ParameterValues("black", "board"),
					new ParameterValues("black", "list"),
					new ParameterValues("black", "out"),
					new ParameterValues("black", "smith"),
					new ParameterValues("blue", "berry"),
					new ParameterValues("blue", "fish"),
					new ParameterValues("blue", "print"),
					new ParameterValues("board", "walk"),
					new ParameterValues("body", "guard"),
					new ParameterValues("body", "work"),
					new ParameterValues("book", "case"),
					new ParameterValues("book", "shelf"),
					new ParameterValues("book", "worm"),
					new ParameterValues("boot", "strap"),
					new ParameterValues("butter", "milk"),
					new ParameterValues("candle", "light"),
					new ParameterValues("candle", "stick"),
					new ParameterValues("car", "pool"),
					new ParameterValues("card", "board"),
					new ParameterValues("cat", "walk"),
					new ParameterValues("cheese", "cake"),
					new ParameterValues("clock", "wise"),
					new ParameterValues("coffee", "maker"),
					new ParameterValues("come", "back"),
					new ParameterValues("common", "place"),
					new ParameterValues("counter", "part"),
					new ParameterValues("court", "house"),
					new ParameterValues("court", "yard"),
					new ParameterValues("cross", "bow"),
					new ParameterValues("cross", "over"),
					new ParameterValues("day", "light"),
					new ParameterValues("day", "time"),
					new ParameterValues("dead", "line"),
					new ParameterValues("dish", "cloth"),
					new ParameterValues("down", "size"),
					new ParameterValues("down", "stairs"),
					new ParameterValues("drive", "way"),
					new ParameterValues("ear", "ring"),
					new ParameterValues("earth", "worm"),
					new ParameterValues("egg", "shell"),
					new ParameterValues("every", "thing"),
					new ParameterValues("eye", "ball"),
					new ParameterValues("eye", "witness"),
					new ParameterValues("fire", "fighter"),
					new ParameterValues("foot", "hill"),
					new ParameterValues("foot", "note"),
					new ParameterValues("foot", "print"),
					new ParameterValues("for", "give"),
					new ParameterValues("fork", "lift"),
					new ParameterValues("friend", "ship"),
					new ParameterValues("gear", "shift"),
					new ParameterValues("glass", "maker"),
					new ParameterValues("grass", "hopper"),
					new ParameterValues("grave", "yard"),
					new ParameterValues("hair", "cut"),
					new ParameterValues("hand", "book"),
					new ParameterValues("hand", "gun"),
					new ParameterValues("hand", "made"),
					new ParameterValues("head", "ache"),
					new ParameterValues("head", "light"),
					new ParameterValues("here", "in"),
					new ParameterValues("high", "land"),
					new ParameterValues("high", "way"),
					new ParameterValues("home", "made"),
					new ParameterValues("home", "town"),
					new ParameterValues("honey", "comb"),
					new ParameterValues("honey", "moon"),
					new ParameterValues("horse", "back"),
					new ParameterValues("horse", "power"),
					new ParameterValues("house", "work"),
					new ParameterValues("key", "board"),
					new ParameterValues("key", "hole"),
					new ParameterValues("key", "note"),
					new ParameterValues("key", "word"),
					new ParameterValues("life", "boat"),
					new ParameterValues("life", "guard"),
					new ParameterValues("life", "time"),
					new ParameterValues("lime", "stone"),
					new ParameterValues("long", "house"),
					new ParameterValues("main", "land"),
					new ParameterValues("main", "line"),
					new ParameterValues("match", "box"),
					new ParameterValues("mean", "time"),
					new ParameterValues("moon", "light"),
					new ParameterValues("mother", "hood"),
					new ParameterValues("near", "by"),
					new ParameterValues("news", "paper"),
					new ParameterValues("one", "self"),
					new ParameterValues("over", "flow"),
					new ParameterValues("pan", "cake"),
					new ParameterValues("pass", "book"),
					new ParameterValues("pass", "port"),
					new ParameterValues("pepper", "mint"),
					new ParameterValues("pin", "hole"),
					new ParameterValues("rail", "road"),
					new ParameterValues("rail", "way"),
					new ParameterValues("rain", "bow"),
					new ParameterValues("rain", "coat"),
					new ParameterValues("rain", "drop"),
					new ParameterValues("rain", "storm"),
					new ParameterValues("sail", "boat"),
					new ParameterValues("sand", "stone"),
					new ParameterValues("school", "house"),
					new ParameterValues("sea", "shore"),
					new ParameterValues("shoe", "maker"),
					new ParameterValues("side", "walk"),
					new ParameterValues("silver", "smith"),
					new ParameterValues("sky", "scraper"),
					new ParameterValues("snow", "ball"),
					new ParameterValues("snow", "drift"),
					new ParameterValues("some", "thing"),
					new ParameterValues("sound", "proof"),
					new ParameterValues("steam", "boat"),
					new ParameterValues("sun", "dial"),
					new ParameterValues("sun", "flower"),
					new ParameterValues("sun", "glasses"),
					new ParameterValues("super", "market"),
					new ParameterValues("table", "cloth"),
					new ParameterValues("tea", "pot"),
					new ParameterValues("text", "book"),
					new ParameterValues("thunder", "bird"),
					new ParameterValues("thunder", "storm"),
					new ParameterValues("time", "table"),
					new ParameterValues("tool", "box"),
					new ParameterValues("town", "ship"),
					new ParameterValues("under", "belly"),
					new ParameterValues("under", "clothes"),
					new ParameterValues("under", "cover"),
					new ParameterValues("under", "ground"),
					new ParameterValues("under", "pass"),
					new ParameterValues("up", "hill"),
					new ParameterValues("up", "lift"),
					new ParameterValues("up", "link"),
					new ParameterValues("up", "right"),
					new ParameterValues("up", "rising"),
					new ParameterValues("up", "side"),
					new ParameterValues("up", "stairs"),
					new ParameterValues("up", "time"),
					new ParameterValues("up", "wind"),
					new ParameterValues("wall", "paper"),
					new ParameterValues("ware", "house"),
					new ParameterValues("wash", "cloth"),
					new ParameterValues("wash", "room"),
					new ParameterValues("waste", "land"),
					new ParameterValues("waste", "water"),
					new ParameterValues("watch", "maker"),
					new ParameterValues("water", "color"),
					new ParameterValues("water", "mark"),
					new ParameterValues("water", "proof"),
					new ParameterValues("water", "shed"),
					new ParameterValues("water", "way"),
					new ParameterValues("weather", "proof"),
					new ParameterValues("week", "day"),
					new ParameterValues("week", "end"),
					new ParameterValues("wheel", "barrow"),
					new ParameterValues("wheel", "chair"),
			});
		}

		@Test
		public void test() {
			StringBuilder builder = new StringBuilder(original);
			removePrefix(builder, prefix);
			assertThat(builder.toString(), is(equalTo(expectedResult)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final String prefix;

			private final String stem;


			public ParameterValues(String prefix, String stem) {
				this.prefix = prefix;
				this.stem = stem;
			}


			public String getStem() {
				return stem;
			}

			public String getPrefix() {
				return prefix;
			}

		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	@RunWith(Parameterized.class)
	public static class RemoveSuffixTest {

		private final String expectedResult;

		private final String original;

		private final String suffix;


		public RemoveSuffixTest(ParameterValues values) {
			suffix = values.getSuffix();
			expectedResult = values.getStem();
			original = expectedResult + suffix;
		}


		@Parameters
		public static Collection<ParameterValues> parameters() {
			return Arrays.asList(new ParameterValues[] {
				new ParameterValues("after", "noon"),
				new ParameterValues("air", "craft"),
				new ParameterValues("air", "field"),
				new ParameterValues("air", "line"),
				new ParameterValues("air", "plane"),
				new ParameterValues("air", "port"),
				new ParameterValues("arch", "angel"),
				new ParameterValues("back", "bone"),
				new ParameterValues("back", "ground"),
				new ParameterValues("back", "hoe"),
				new ParameterValues("back", "log"),
				new ParameterValues("back", "pack"),
				new ParameterValues("back", "stage"),
				new ParameterValues("bag", "pipe"),
				new ParameterValues("base", "ball"),
				new ParameterValues("basket", "ball"),
				new ParameterValues("be", "come"),
				new ParameterValues("bed", "clothes"),
				new ParameterValues("bed", "room"),
				new ParameterValues("black", "bird"),
				new ParameterValues("black", "board"),
				new ParameterValues("black", "list"),
				new ParameterValues("black", "out"),
				new ParameterValues("black", "smith"),
				new ParameterValues("blue", "berry"),
				new ParameterValues("blue", "fish"),
				new ParameterValues("blue", "print"),
				new ParameterValues("board", "walk"),
				new ParameterValues("body", "guard"),
				new ParameterValues("body", "work"),
				new ParameterValues("book", "case"),
				new ParameterValues("book", "shelf"),
				new ParameterValues("book", "worm"),
				new ParameterValues("boot", "strap"),
				new ParameterValues("butter", "milk"),
				new ParameterValues("candle", "light"),
				new ParameterValues("candle", "stick"),
				new ParameterValues("car", "pool"),
				new ParameterValues("card", "board"),
				new ParameterValues("cat", "walk"),
				new ParameterValues("cheese", "cake"),
				new ParameterValues("clock", "wise"),
				new ParameterValues("coffee", "maker"),
				new ParameterValues("come", "back"),
				new ParameterValues("common", "place"),
				new ParameterValues("counter", "part"),
				new ParameterValues("court", "house"),
				new ParameterValues("court", "yard"),
				new ParameterValues("cross", "bow"),
				new ParameterValues("cross", "over"),
				new ParameterValues("day", "light"),
				new ParameterValues("day", "time"),
				new ParameterValues("dead", "line"),
				new ParameterValues("dish", "cloth"),
				new ParameterValues("down", "size"),
				new ParameterValues("down", "stairs"),
				new ParameterValues("drive", "way"),
				new ParameterValues("ear", "ring"),
				new ParameterValues("earth", "worm"),
				new ParameterValues("egg", "shell"),
				new ParameterValues("every", "thing"),
				new ParameterValues("eye", "ball"),
				new ParameterValues("eye", "witness"),
				new ParameterValues("fire", "fighter"),
				new ParameterValues("foot", "hill"),
				new ParameterValues("foot", "note"),
				new ParameterValues("foot", "print"),
				new ParameterValues("for", "give"),
				new ParameterValues("fork", "lift"),
				new ParameterValues("friend", "ship"),
				new ParameterValues("gear", "shift"),
				new ParameterValues("glass", "maker"),
				new ParameterValues("grass", "hopper"),
				new ParameterValues("grave", "yard"),
				new ParameterValues("hair", "cut"),
				new ParameterValues("hand", "book"),
				new ParameterValues("hand", "gun"),
				new ParameterValues("hand", "made"),
				new ParameterValues("head", "ache"),
				new ParameterValues("head", "light"),
				new ParameterValues("here", "in"),
				new ParameterValues("high", "land"),
				new ParameterValues("high", "way"),
				new ParameterValues("home", "made"),
				new ParameterValues("home", "town"),
				new ParameterValues("honey", "comb"),
				new ParameterValues("honey", "moon"),
				new ParameterValues("horse", "back"),
				new ParameterValues("horse", "power"),
				new ParameterValues("house", "work"),
				new ParameterValues("key", "board"),
				new ParameterValues("key", "hole"),
				new ParameterValues("key", "note"),
				new ParameterValues("key", "word"),
				new ParameterValues("life", "boat"),
				new ParameterValues("life", "guard"),
				new ParameterValues("life", "time"),
				new ParameterValues("lime", "stone"),
				new ParameterValues("long", "house"),
				new ParameterValues("main", "land"),
				new ParameterValues("main", "line"),
				new ParameterValues("match", "box"),
				new ParameterValues("mean", "time"),
				new ParameterValues("moon", "light"),
				new ParameterValues("mother", "hood"),
				new ParameterValues("near", "by"),
				new ParameterValues("news", "paper"),
				new ParameterValues("one", "self"),
				new ParameterValues("over", "flow"),
				new ParameterValues("pan", "cake"),
				new ParameterValues("pass", "book"),
				new ParameterValues("pass", "port"),
				new ParameterValues("pepper", "mint"),
				new ParameterValues("pin", "hole"),
				new ParameterValues("rail", "road"),
				new ParameterValues("rail", "way"),
				new ParameterValues("rain", "bow"),
				new ParameterValues("rain", "coat"),
				new ParameterValues("rain", "drop"),
				new ParameterValues("rain", "storm"),
				new ParameterValues("sail", "boat"),
				new ParameterValues("sand", "stone"),
				new ParameterValues("school", "house"),
				new ParameterValues("sea", "shore"),
				new ParameterValues("shoe", "maker"),
				new ParameterValues("side", "walk"),
				new ParameterValues("silver", "smith"),
				new ParameterValues("sky", "scraper"),
				new ParameterValues("snow", "ball"),
				new ParameterValues("snow", "drift"),
				new ParameterValues("some", "thing"),
				new ParameterValues("sound", "proof"),
				new ParameterValues("steam", "boat"),
				new ParameterValues("sun", "dial"),
				new ParameterValues("sun", "flower"),
				new ParameterValues("sun", "glasses"),
				new ParameterValues("super", "market"),
				new ParameterValues("table", "cloth"),
				new ParameterValues("tea", "pot"),
				new ParameterValues("text", "book"),
				new ParameterValues("thunder", "bird"),
				new ParameterValues("thunder", "storm"),
				new ParameterValues("time", "table"),
				new ParameterValues("tool", "box"),
				new ParameterValues("town", "ship"),
				new ParameterValues("under", "belly"),
				new ParameterValues("under", "clothes"),
				new ParameterValues("under", "cover"),
				new ParameterValues("under", "ground"),
				new ParameterValues("under", "pass"),
				new ParameterValues("up", "hill"),
				new ParameterValues("up", "lift"),
				new ParameterValues("up", "link"),
				new ParameterValues("up", "right"),
				new ParameterValues("up", "rising"),
				new ParameterValues("up", "side"),
				new ParameterValues("up", "stairs"),
				new ParameterValues("up", "time"),
				new ParameterValues("up", "wind"),
				new ParameterValues("wall", "paper"),
				new ParameterValues("ware", "house"),
				new ParameterValues("wash", "cloth"),
				new ParameterValues("wash", "room"),
				new ParameterValues("waste", "land"),
				new ParameterValues("waste", "water"),
				new ParameterValues("watch", "maker"),
				new ParameterValues("water", "color"),
				new ParameterValues("water", "mark"),
				new ParameterValues("water", "proof"),
				new ParameterValues("water", "shed"),
				new ParameterValues("water", "way"),
				new ParameterValues("weather", "proof"),
				new ParameterValues("week", "day"),
				new ParameterValues("week", "end"),
				new ParameterValues("wheel", "barrow"),
				new ParameterValues("wheel", "chair"),
			});
		}

		@Test
		public void test() {
			StringBuilder builder = new StringBuilder(original);
			removeSuffix(builder, suffix);
			assertThat(builder.toString(), is(equalTo(expectedResult)));
		}

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static class ParameterValues {

			private final String stem;

			private final String suffix;


			public ParameterValues(String stem, String suffix) {
				this.stem = stem;
				this.suffix = suffix;
			}


			public String getStem() {
				return stem;
			}

			public String getSuffix() {
				return suffix;
			}

		}

	}

}
