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
import static org.sellcom.core.Strings.commonPrefix;
import static org.sellcom.core.Strings.commonSuffix;
import static org.sellcom.core.Strings.endsWith;
import static org.sellcom.core.Strings.ensurePrefix;
import static org.sellcom.core.Strings.ensureSuffix;
import static org.sellcom.core.Strings.interleavePadding;
import static org.sellcom.core.Strings.normalizeWhitespace;
import static org.sellcom.core.Strings.padEnd;
import static org.sellcom.core.Strings.padStart;
import static org.sellcom.core.Strings.removePrefix;
import static org.sellcom.core.Strings.removeSuffix;
import static org.sellcom.core.Strings.repeat;
import static org.sellcom.core.Strings.startsWith;
import static org.sellcom.core.Strings.trimLeft;
import static org.sellcom.core.Strings.trimRight;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

public class StringsTest {

	@Test
	public void testCenter() {
		assertThat(Strings.center("JANUARY", 20, '='), is(equalTo("======JANUARY=======")));
		assertThat(Strings.center("FEBRUARY", 20, '='), is(equalTo("======FEBRUARY======")));
		assertThat(Strings.center("MARCH", 20, '='), is(equalTo("=======MARCH========")));
		assertThat(Strings.center("APRIL", 20, '='), is(equalTo("=======APRIL========")));
		assertThat(Strings.center("MAY", 20, '='), is(equalTo("========MAY=========")));
		assertThat(Strings.center("JUNE", 20, '='), is(equalTo("========JUNE========")));
		assertThat(Strings.center("JULY", 20, '='), is(equalTo("========JULY========")));
		assertThat(Strings.center("AUGUST", 20, '='), is(equalTo("=======AUGUST=======")));
		assertThat(Strings.center("SEPTEMBER", 20, '='), is(equalTo("=====SEPTEMBER======")));
		assertThat(Strings.center("OCTOBER", 20, '='), is(equalTo("======OCTOBER=======")));
		assertThat(Strings.center("NOVEMBER", 20, '='), is(equalTo("======NOVEMBER======")));
		assertThat(Strings.center("DECEMBER", 20, '='), is(equalTo("======DECEMBER======")));
	}

	@Test
	public void testCommonPrefix() {
		assertThat(commonPrefix("aircraft", "airfield", "airline"), is(equalTo("air")));
		assertThat(commonPrefix("background", "backlog", "backstage"), is(equalTo("back")));
		assertThat(commonPrefix("blackboard", "blacksmith"), is(equalTo("black")));
		assertThat(commonPrefix("bluefish", "blueprint"), is(equalTo("blue")));
		assertThat(commonPrefix("downsize", "downstairs"), is(equalTo("downs")));
		assertThat(commonPrefix("forgive", "forklift"), is(equalTo("for")));
		assertThat(commonPrefix("honeycomb", "honeymoon"), is(equalTo("honey")));
		assertThat(commonPrefix("keynote", "keyword"), is(equalTo("key")));
		assertThat(commonPrefix("railroad", "railway"), is(equalTo("rail")));
		assertThat(commonPrefix("thunderbird", "thunderstorm"), is(equalTo("thunder")));
		assertThat(commonPrefix("underbelly", "underclothes", "undercover", "underground"), is(equalTo("under")));
		assertThat(commonPrefix("uphill", "uplink", "upright", "uprising", "upside", "upstairs", "uptime", "upwind"), is(equalTo("up")));
	}

	@Test
	public void testCommonSuffix() {
		assertThat(commonSuffix("airport", "backport"), is(equalTo("port")));
		assertThat(commonSuffix("backbone", "fishbone"), is(equalTo("bone")));
		assertThat(commonSuffix("baseball", "basketball", "football", "handball", "snowball", "volleyball"), is(equalTo("ball")));
		assertThat(commonSuffix("bathroom", "bedroom"), is(equalTo("room")));
		assertThat(commonSuffix("blackboard", "cardboard", "keyboard", "whiteboard"), is(equalTo("board")));
		assertThat(commonSuffix("Blackpool", "carpool"), is(equalTo("pool")));
		assertThat(commonSuffix("bodyguard", "lifeguard"), is(equalTo("guard")));
		assertThat(commonSuffix("candlelight", "daylight", "moonlight"), is(equalTo("light")));
		assertThat(commonSuffix("herein", "pin", "tin"), is(equalTo("in")));
		assertThat(commonSuffix("matchbox", "toolbox"), is(equalTo("box")));
	}

	@Test
	public void testEndsWith() {
		assertThat(endsWith("DownHill", "hill", false), is(false));
		assertThat(endsWith("DownHill", "hill", true), is(true));
		assertThat(endsWith("UpHill", "hill", false), is(false));
		assertThat(endsWith("UpHill", "hill", true), is(true));

		assertThat(endsWith("DownLink", "link", false), is(false));
		assertThat(endsWith("DownLink", "link", true), is(true));
		assertThat(endsWith("UpLink", "link", false), is(false));
		assertThat(endsWith("UpLink", "link", true), is(true));

		assertThat(endsWith("DownSide", "side", false), is(false));
		assertThat(endsWith("DownSide", "side", true), is(true));
		assertThat(endsWith("UpSide", "side", false), is(false));
		assertThat(endsWith("UpSide", "side", true), is(true));

		assertThat(endsWith("DownTime", "time", false), is(false));
		assertThat(endsWith("DownTime", "time", true), is(true));
		assertThat(endsWith("UpTime", "time", false), is(false));
		assertThat(endsWith("UpTime", "time", true), is(true));

		assertThat(endsWith("DownWind", "wind", false), is(false));
		assertThat(endsWith("DownWind", "wind", true), is(true));
		assertThat(endsWith("UpWind", "wind", false), is(false));
		assertThat(endsWith("UpWind", "wind", true), is(true));
	}

	@Test
	public void testInterleavePadding() {
		assertThat(interleavePadding("George Orwell", " "), is(equalTo("G e o r g e   O r w e l l")));
		assertThat(interleavePadding("Nineteen Eighty-Four", " "), is(equalTo("N i n e t e e n   E i g h t y - F o u r")));
	}

	@Test
	public void testNormalizeWhitespace() {
		assertThat(normalizeWhitespace(" Republica Moldova "), is(equalTo("Republica Moldova")));
		assertThat(normalizeWhitespace("    Republica  Moldova    "), is(equalTo("Republica Moldova")));
		assertThat(normalizeWhitespace("\tRepublica\tMoldova\t"), is(equalTo("Republica Moldova")));
		assertThat(normalizeWhitespace("\t\t\t\tRepublica\t\tMoldova\t\t\t\t"), is(equalTo("Republica Moldova")));
		assertThat(normalizeWhitespace(" \t Republica \t Moldova \t "), is(equalTo("Republica Moldova")));
	}

	@Test
	public void testPadEnd() {
		assertThat(padEnd("42", 3, '='), is(equalTo("42=")));
		assertThat(padEnd("42", 5, '='), is(equalTo("42===")));
		assertThat(padEnd("42", 7, '='), is(equalTo("42=====")));
		assertThat(padEnd("42", 9, '='), is(equalTo("42=======")));
	}

	@Test
	public void testPadStart() {
		assertThat(padStart("42", 3, '='), is(equalTo("=42")));
		assertThat(padStart("42", 5, '='), is(equalTo("===42")));
		assertThat(padStart("42", 7, '='), is(equalTo("=====42")));
		assertThat(padStart("42", 9, '='), is(equalTo("=======42")));
	}

	@Test
	public void testRepeat() {
		assertThat(repeat("cous", 2), is(equalTo("couscous")));
		assertThat("Schi" + repeat("f", 3) + "ahrt", is(equalTo("Schifffahrt")));
		assertThat(repeat("taai", 2), is(equalTo("taaitaai")));
	}

	@Test
	public void testStartsWith() {
		assertThat(startsWith("DownHill", "down", false), is(false));
		assertThat(startsWith("DownHill", "down", true), is(true));
		assertThat(startsWith("UpHill", "up", false), is(false));
		assertThat(startsWith("UpHill", "up", true), is(true));

		assertThat(startsWith("DownLink", "down", false), is(false));
		assertThat(startsWith("DownLink", "down", true), is(true));
		assertThat(startsWith("UpLink", "up", false), is(false));
		assertThat(startsWith("UpLink", "up", true), is(true));

		assertThat(startsWith("DownSide", "down", false), is(false));
		assertThat(startsWith("DownSide", "down", true), is(true));
		assertThat(startsWith("UpSide", "up", false), is(false));
		assertThat(startsWith("UpSide", "up", true), is(true));

		assertThat(startsWith("DownTime", "down", false), is(false));
		assertThat(startsWith("DownTime", "down", true), is(true));
		assertThat(startsWith("UpTime", "up", false), is(false));
		assertThat(startsWith("UpTime", "up", true), is(true));

		assertThat(startsWith("DownWind", "down", false), is(false));
		assertThat(startsWith("DownWind", "down", true), is(true));
		assertThat(startsWith("UpWind", "up", false), is(false));
		assertThat(startsWith("UpWind", "up", true), is(true));
	}

	@Test
	public void testTrimLeft() {
		assertThat(trimLeft("\t \r \n Independence Day \t \r \n"), is(equalTo("Independence Day \t \r \n")));
	}

	@Test
	public void testTrimRight() {
		assertThat(trimRight("\t \r \n Independence Day \t \r \n"), is(equalTo("\t \r \n Independence Day")));
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
			assertThat(ensurePrefix(original, prefix), is(equalTo(expectedResult)));
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
			assertThat(ensureSuffix(original, suffix), is(equalTo(expectedResult)));
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
			assertThat(removePrefix(original, prefix), is(equalTo(expectedResult)));
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
			assertThat(removeSuffix(original, suffix), is(equalTo(expectedResult)));
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
