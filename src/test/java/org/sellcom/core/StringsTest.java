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
import static org.sellcom.core.Strings.interleavePadding;
import static org.sellcom.core.Strings.padEnd;
import static org.sellcom.core.Strings.padStart;
import static org.sellcom.core.Strings.removePrefix;
import static org.sellcom.core.Strings.removeSuffix;
import static org.sellcom.core.Strings.repeat;
import static org.sellcom.core.Strings.startsWith;

import org.junit.Test;

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
	public void testRemovePrefix() {
		assertThat(removePrefix("afternoon", "after"), is(equalTo("noon")));
		assertThat(removePrefix("aircraft", "air"), is(equalTo("craft")));
		assertThat(removePrefix("airfield", "air"), is(equalTo("field")));
		assertThat(removePrefix("airline", "air"), is(equalTo("line")));
		assertThat(removePrefix("background", "back"), is(equalTo("ground")));
		assertThat(removePrefix("backlog", "back"), is(equalTo("log")));
		assertThat(removePrefix("backstage", "back"), is(equalTo("stage")));
		assertThat(removePrefix("bagpipe", "bag"), is(equalTo("pipe")));
		assertThat(removePrefix("become", "be"), is(equalTo("come")));
		assertThat(removePrefix("bedclothes", "bed"), is(equalTo("clothes")));
		assertThat(removePrefix("blackboard", "black"), is(equalTo("board")));
		assertThat(removePrefix("blacksmith", "black"), is(equalTo("smith")));
		assertThat(removePrefix("bluefish", "blue"), is(equalTo("fish")));
		assertThat(removePrefix("blueprint", "blue"), is(equalTo("print")));
		assertThat(removePrefix("bookshelf", "book"), is(equalTo("shelf")));
		assertThat(removePrefix("buttermilk", "butter"), is(equalTo("milk")));
		assertThat(removePrefix("catwalk", "cat"), is(equalTo("walk")));
		assertThat(removePrefix("cheesecake", "cheese"), is(equalTo("cake")));
		assertThat(removePrefix("clockwise", "clock"), is(equalTo("wise")));
		assertThat(removePrefix("comeback", "come"), is(equalTo("back")));
		assertThat(removePrefix("commonplace", "common"), is(equalTo("place")));
		assertThat(removePrefix("counterpart", "counter"), is(equalTo("part")));
		assertThat(removePrefix("courthouse", "court"), is(equalTo("house")));
		assertThat(removePrefix("crossover", "cross"), is(equalTo("over")));
		assertThat(removePrefix("deadline", "dead"), is(equalTo("line")));
		assertThat(removePrefix("downsize", "down"), is(equalTo("size")));
		assertThat(removePrefix("downstairs", "down"), is(equalTo("stairs")));
		assertThat(removePrefix("driveway", "drive"), is(equalTo("way")));
		assertThat(removePrefix("earring", "ear"), is(equalTo("ring")));
		assertThat(removePrefix("eggshell", "egg"), is(equalTo("shell")));
		assertThat(removePrefix("everything", "every"), is(equalTo("thing")));
		assertThat(removePrefix("eyeball", "eye"), is(equalTo("ball")));
		assertThat(removePrefix("foothill", "foot"), is(equalTo("hill")));
		assertThat(removePrefix("forgive", "for"), is(equalTo("give")));
		assertThat(removePrefix("forklift", "fork"), is(equalTo("lift")));
		assertThat(removePrefix("friendship", "friend"), is(equalTo("ship")));
		assertThat(removePrefix("gearshift", "gear"), is(equalTo("shift")));
		assertThat(removePrefix("graveyard", "grave"), is(equalTo("yard")));
		assertThat(removePrefix("handbook", "hand"), is(equalTo("book")));
		assertThat(removePrefix("headlight", "head"), is(equalTo("light")));
		assertThat(removePrefix("highland", "high"), is(equalTo("land")));
		assertThat(removePrefix("hometown", "home"), is(equalTo("town")));
		assertThat(removePrefix("honeycomb", "honey"), is(equalTo("comb")));
		assertThat(removePrefix("honeymoon", "honey"), is(equalTo("moon")));
		assertThat(removePrefix("keynote", "key"), is(equalTo("note")));
		assertThat(removePrefix("keyword", "key"), is(equalTo("word")));
		assertThat(removePrefix("lifetime", "life"), is(equalTo("time")));
		assertThat(removePrefix("limestone", "lime"), is(equalTo("stone")));
		assertThat(removePrefix("longhouse", "long"), is(equalTo("house")));
		assertThat(removePrefix("mainland", "main"), is(equalTo("land")));
		assertThat(removePrefix("mainline", "main"), is(equalTo("line")));
		assertThat(removePrefix("meantime", "mean"), is(equalTo("time")));
		assertThat(removePrefix("newspaper", "news"), is(equalTo("paper")));
		assertThat(removePrefix("overflow", "over"), is(equalTo("flow")));
		assertThat(removePrefix("pancake", "pan"), is(equalTo("cake")));
		assertThat(removePrefix("passbook", "pass"), is(equalTo("book")));
		assertThat(removePrefix("passport", "pass"), is(equalTo("port")));
		assertThat(removePrefix("peppermint", "pepper"), is(equalTo("mint")));
		assertThat(removePrefix("pinhole", "pin"), is(equalTo("hole")));
		assertThat(removePrefix("railroad", "rail"), is(equalTo("road")));
		assertThat(removePrefix("railway", "rail"), is(equalTo("way")));
		assertThat(removePrefix("rainstorm", "rain"), is(equalTo("storm")));
		assertThat(removePrefix("sailboat", "sail"), is(equalTo("boat")));
		assertThat(removePrefix("steamboat", "steam"), is(equalTo("boat")));
		assertThat(removePrefix("sunglasses", "sun"), is(equalTo("glasses")));
		assertThat(removePrefix("supermarket", "super"), is(equalTo("market")));
		assertThat(removePrefix("teapot", "tea"), is(equalTo("pot")));
		assertThat(removePrefix("textbook", "text"), is(equalTo("book")));
		assertThat(removePrefix("thunderbird", "thunder"), is(equalTo("bird")));
		assertThat(removePrefix("thunderstorm", "thunder"), is(equalTo("storm")));
		assertThat(removePrefix("township", "town"), is(equalTo("ship")));
		assertThat(removePrefix("underbelly", "under"), is(equalTo("belly")));
		assertThat(removePrefix("underclothes", "under"), is(equalTo("clothes")));
		assertThat(removePrefix("undercover", "under"), is(equalTo("cover")));
		assertThat(removePrefix("underground", "under"), is(equalTo("ground")));
		assertThat(removePrefix("uphill", "up"), is(equalTo("hill")));
		assertThat(removePrefix("uplink", "up"), is(equalTo("link")));
		assertThat(removePrefix("upright", "up"), is(equalTo("right")));
		assertThat(removePrefix("uprising", "up"), is(equalTo("rising")));
		assertThat(removePrefix("upside", "up"), is(equalTo("side")));
		assertThat(removePrefix("upstairs", "up"), is(equalTo("stairs")));
		assertThat(removePrefix("uptime", "up"), is(equalTo("time")));
		assertThat(removePrefix("upwind", "up"), is(equalTo("wind")));
		assertThat(removePrefix("wallpaper", "wall"), is(equalTo("paper")));
		assertThat(removePrefix("warehouse", "ware"), is(equalTo("house")));
		assertThat(removePrefix("washroom", "wash"), is(equalTo("room")));
		assertThat(removePrefix("wastewater", "waste"), is(equalTo("water")));
		assertThat(removePrefix("watercolor", "water"), is(equalTo("color")));
		assertThat(removePrefix("weekday", "week"), is(equalTo("day")));
		assertThat(removePrefix("wheelchair", "wheel"), is(equalTo("chair")));
	}

	@Test
	public void testRemoveSuffix() {
		assertThat(removeSuffix("airplane", "plane"), is(equalTo("air")));
		assertThat(removeSuffix("airport", "port"), is(equalTo("air")));
		assertThat(removeSuffix("archangel", "angel"), is(equalTo("arch")));
		assertThat(removeSuffix("backbone", "bone"), is(equalTo("back")));
		assertThat(removeSuffix("backhoe", "hoe"), is(equalTo("back")));
		assertThat(removeSuffix("backpack", "pack"), is(equalTo("back")));
		assertThat(removeSuffix("baseball", "ball"), is(equalTo("base")));
		assertThat(removeSuffix("basketball", "ball"), is(equalTo("basket")));
		assertThat(removeSuffix("bedroom", "room"), is(equalTo("bed")));
		assertThat(removeSuffix("blackbird", "bird"), is(equalTo("black")));
		assertThat(removeSuffix("blacklist", "list"), is(equalTo("black")));
		assertThat(removeSuffix("blackout", "out"), is(equalTo("black")));
		assertThat(removeSuffix("blueberry", "berry"), is(equalTo("blue")));
		assertThat(removeSuffix("boardwalk", "walk"), is(equalTo("board")));
		assertThat(removeSuffix("bodyguard", "guard"), is(equalTo("body")));
		assertThat(removeSuffix("bodywork", "work"), is(equalTo("body")));
		assertThat(removeSuffix("bookcase", "case"), is(equalTo("book")));
		assertThat(removeSuffix("bookworm", "worm"), is(equalTo("book")));
		assertThat(removeSuffix("bootstrap", "strap"), is(equalTo("boot")));
		assertThat(removeSuffix("candlelight", "light"), is(equalTo("candle")));
		assertThat(removeSuffix("candlestick", "stick"), is(equalTo("candle")));
		assertThat(removeSuffix("cardboard", "board"), is(equalTo("card")));
		assertThat(removeSuffix("carpool", "pool"), is(equalTo("car")));
		assertThat(removeSuffix("coffeemaker", "maker"), is(equalTo("coffee")));
		assertThat(removeSuffix("courtyard", "yard"), is(equalTo("court")));
		assertThat(removeSuffix("crossbow", "bow"), is(equalTo("cross")));
		assertThat(removeSuffix("daylight", "light"), is(equalTo("day")));
		assertThat(removeSuffix("daytime", "time"), is(equalTo("day")));
		assertThat(removeSuffix("dishcloth", "cloth"), is(equalTo("dish")));
		assertThat(removeSuffix("earthworm", "worm"), is(equalTo("earth")));
		assertThat(removeSuffix("eyewitness", "witness"), is(equalTo("eye")));
		assertThat(removeSuffix("firefighter", "fighter"), is(equalTo("fire")));
		assertThat(removeSuffix("footnote", "note"), is(equalTo("foot")));
		assertThat(removeSuffix("footprint", "print"), is(equalTo("foot")));
		assertThat(removeSuffix("glassmaker", "maker"), is(equalTo("glass")));
		assertThat(removeSuffix("grasshopper", "hopper"), is(equalTo("grass")));
		assertThat(removeSuffix("haircut", "cut"), is(equalTo("hair")));
		assertThat(removeSuffix("handgun", "gun"), is(equalTo("hand")));
		assertThat(removeSuffix("handmade", "made"), is(equalTo("hand")));
		assertThat(removeSuffix("headache", "ache"), is(equalTo("head")));
		assertThat(removeSuffix("herein", "in"), is(equalTo("here")));
		assertThat(removeSuffix("highway", "way"), is(equalTo("high")));
		assertThat(removeSuffix("homemade", "made"), is(equalTo("home")));
		assertThat(removeSuffix("horseback", "back"), is(equalTo("horse")));
		assertThat(removeSuffix("horsepower", "power"), is(equalTo("horse")));
		assertThat(removeSuffix("housework", "work"), is(equalTo("house")));
		assertThat(removeSuffix("keyboard", "board"), is(equalTo("key")));
		assertThat(removeSuffix("keyhole", "hole"), is(equalTo("key")));
		assertThat(removeSuffix("lifeboat", "boat"), is(equalTo("life")));
		assertThat(removeSuffix("lifeguard", "guard"), is(equalTo("life")));
		assertThat(removeSuffix("matchbox", "box"), is(equalTo("match")));
		assertThat(removeSuffix("moonlight", "light"), is(equalTo("moon")));
		assertThat(removeSuffix("motherhood", "hood"), is(equalTo("mother")));
		assertThat(removeSuffix("nearby", "by"), is(equalTo("near")));
		assertThat(removeSuffix("oneself", "self"), is(equalTo("one")));
		assertThat(removeSuffix("rainbow", "bow"), is(equalTo("rain")));
		assertThat(removeSuffix("raincoat", "coat"), is(equalTo("rain")));
		assertThat(removeSuffix("raindrop", "drop"), is(equalTo("rain")));
		assertThat(removeSuffix("sandstone", "stone"), is(equalTo("sand")));
		assertThat(removeSuffix("schoolhouse", "house"), is(equalTo("school")));
		assertThat(removeSuffix("seashore", "shore"), is(equalTo("sea")));
		assertThat(removeSuffix("shoemaker", "maker"), is(equalTo("shoe")));
		assertThat(removeSuffix("sidewalk", "walk"), is(equalTo("side")));
		assertThat(removeSuffix("silversmith", "smith"), is(equalTo("silver")));
		assertThat(removeSuffix("skyscraper", "scraper"), is(equalTo("sky")));
		assertThat(removeSuffix("snowball", "ball"), is(equalTo("snow")));
		assertThat(removeSuffix("snowdrift", "drift"), is(equalTo("snow")));
		assertThat(removeSuffix("something", "thing"), is(equalTo("some")));
		assertThat(removeSuffix("soundproof", "proof"), is(equalTo("sound")));
		assertThat(removeSuffix("sundial", "dial"), is(equalTo("sun")));
		assertThat(removeSuffix("sunflower", "flower"), is(equalTo("sun")));
		assertThat(removeSuffix("tablecloth", "cloth"), is(equalTo("table")));
		assertThat(removeSuffix("timetable", "table"), is(equalTo("time")));
		assertThat(removeSuffix("toolbox", "box"), is(equalTo("tool")));
		assertThat(removeSuffix("underpass", "pass"), is(equalTo("under")));
		assertThat(removeSuffix("uplift", "lift"), is(equalTo("up")));
		assertThat(removeSuffix("washcloth", "cloth"), is(equalTo("wash")));
		assertThat(removeSuffix("wasteland", "land"), is(equalTo("waste")));
		assertThat(removeSuffix("watchmaker", "maker"), is(equalTo("watch")));
		assertThat(removeSuffix("watermark", "mark"), is(equalTo("water")));
		assertThat(removeSuffix("waterproof", "proof"), is(equalTo("water")));
		assertThat(removeSuffix("watershed", "shed"), is(equalTo("water")));
		assertThat(removeSuffix("waterway", "way"), is(equalTo("water")));
		assertThat(removeSuffix("weatherproof", "proof"), is(equalTo("weather")));
		assertThat(removeSuffix("weekend", "end"), is(equalTo("week")));
		assertThat(removeSuffix("wheelbarrow", "barrow"), is(equalTo("wheel")));
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

}
