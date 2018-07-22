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
package org.sellcom.core.io.charset;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_16BE;
import static java.nio.charset.StandardCharsets.UTF_16LE;
import static org.sellcom.core.io.charset.MoreCharsets.GB18030;
import static org.sellcom.core.io.charset.MoreCharsets.GB2312;
import static org.sellcom.core.io.charset.MoreCharsets.ISCII91;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_10;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_11;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_13;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_14;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_15;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_16;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_2;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_3;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_4;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_5;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_6;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_7;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_8;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_9;
import static org.sellcom.core.io.charset.MoreCharsets.SHIFT_JIS;
import static org.sellcom.core.io.charset.MoreCharsets.UTF_32BE;
import static org.sellcom.core.io.charset.MoreCharsets.UTF_32LE;
import static org.sellcom.core.io.charset.MoreCharsets.UTF_8BOM;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_1250;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_1251;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_1252;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_1253;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_1254;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_1255;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_1256;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_1257;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_1258;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_31J;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_874;
import static org.sellcom.core.io.charset.MoreCharsets.WINDOWS_949;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.sellcom.core.Contract;

/**
 * Operations with {@link Charset}s.
 *
 * @since 1.0
 */
public class Charsets {

	private static final Map<String, String> DISPLAY_NAMES;

	static {
		Map<String, String> displayNames = new HashMap<>();
		displayNames.put(GB2312.name(), "GB 2312");
		displayNames.put(GB18030.name(), "GB 18030");
		displayNames.put(ISCII91.name(), "ISCII");
		displayNames.put(ISO_8859_1.name(), "Latin 1 (ISO-8859-1/CP819)");
		displayNames.put(ISO_8859_2.name(), "Latin 2 (ISO-8859-2)");
		displayNames.put(ISO_8859_3.name(), "Latin 3 (ISO-8859-3)");
		displayNames.put(ISO_8859_4.name(), "Latin 4 (ISO-8859-4)");
		displayNames.put(ISO_8859_5.name(), "Latin/Cyrillic (ISO-8859-5)");
		displayNames.put(ISO_8859_6.name(), "Latin/Arabic (ISO-8859-6)");
		displayNames.put(ISO_8859_7.name(), "Latin/Greek (ISO-8859-7)");
		displayNames.put(ISO_8859_8.name(), "Latin/Hebrew (ISO-8859-8)");
		displayNames.put(ISO_8859_9.name(), "Latin 5 (ISO-8859-9)");
		displayNames.put(ISO_8859_10.name(), "Latin 6 (ISO-8859-10)");
		displayNames.put(ISO_8859_11.name(), "Latin/Thai (ISO-8859-11)");
		displayNames.put(ISO_8859_13.name(), "Latin 7 (ISO-8859-13)");
		displayNames.put(ISO_8859_14.name(), "Latin 8 (ISO-8859-13)");
		displayNames.put(ISO_8859_15.name(), "Latin 9 (ISO-8859-15)");
		displayNames.put(ISO_8859_16.name(), "Latin 10 (ISO-8859-16)");
		displayNames.put(SHIFT_JIS.name(), "Shift JIS");
		displayNames.put(US_ASCII.name(), "ASCII");
		displayNames.put(UTF_8BOM.name(), "UTF-8, BOM");
		displayNames.put(UTF_16BE.name(), "UTF-16, Big Engian");
		displayNames.put(UTF_16LE.name(), "UTF-16, Little Engian");
		displayNames.put(UTF_32BE.name(), "UTF-32, Big Engian");
		displayNames.put(UTF_32LE.name(), "UTF-32, Little Engian");
		displayNames.put(WINDOWS_31J.name(), "Windows-31J (CP932)");
		displayNames.put(WINDOWS_874.name(), "Windows-874");
		displayNames.put(WINDOWS_949.name(), "Windows-949");
		displayNames.put(WINDOWS_1250.name(), "Windows-1250");
		displayNames.put(WINDOWS_1251.name(), "Windows-1251");
		displayNames.put(WINDOWS_1252.name(), "Windows-1252");
		displayNames.put(WINDOWS_1253.name(), "Windows-1253");
		displayNames.put(WINDOWS_1254.name(), "Windows-1254");
		displayNames.put(WINDOWS_1255.name(), "Windows-1255");
		displayNames.put(WINDOWS_1256.name(), "Windows-1256");
		displayNames.put(WINDOWS_1257.name(), "Windows-1257");
		displayNames.put(WINDOWS_1258.name(), "Windows-1258");

		DISPLAY_NAMES = Collections.unmodifiableMap(displayNames);
	}


	private Charsets() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the human-readable name of the given charset.
	 * <p>
	 * NOTE: This method exists because {@code Charset#displayName()} is a synonym for {@code Charset#name()} in charsets provided by Oracle.
	 *
	 * @throws IllegalArgumentException if {@code charset} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String getDisplayName(Charset charset) {
		Contract.checkArgument(charset != null, "Charset must not be null");

		return DISPLAY_NAMES.getOrDefault(charset.name(), charset.displayName());
	}

	/**
	 * Returns the list of charsets that support encoding and decoding strings in the given language and script.
	 * <p>
	 * NOTE: If any of the returned charsets shall be used for encoding, make sure its {@link Charset#canEncode()} method returns {@code true}.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 *
	 * @since 1.0
	 */
	public static List<Charset> getSupportedCharsets(Locale locale) {
		Contract.checkArgument(locale != null, "Locale must not be null");

		return LocaleToCharset.getSupportedCharsets(locale);
	}

}
