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
import static java.nio.charset.StandardCharsets.UTF_16;
import static java.nio.charset.StandardCharsets.UTF_16BE;
import static java.nio.charset.StandardCharsets.UTF_16LE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.sellcom.core.io.charset.MoreCharsets.BIG5;
import static org.sellcom.core.io.charset.MoreCharsets.BIG5_HKSCS;
import static org.sellcom.core.io.charset.MoreCharsets.EUC_JP;
import static org.sellcom.core.io.charset.MoreCharsets.EUC_KR;
import static org.sellcom.core.io.charset.MoreCharsets.GB18030;
import static org.sellcom.core.io.charset.MoreCharsets.GB2312;
import static org.sellcom.core.io.charset.MoreCharsets.GBK;
import static org.sellcom.core.io.charset.MoreCharsets.ISCII91;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_2022_CN;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_2022_JP;
import static org.sellcom.core.io.charset.MoreCharsets.ISO_2022_KR;
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
import static org.sellcom.core.io.charset.MoreCharsets.KOI8_R;
import static org.sellcom.core.io.charset.MoreCharsets.KOI8_U;
import static org.sellcom.core.io.charset.MoreCharsets.SHIFT_JIS;
import static org.sellcom.core.io.charset.MoreCharsets.TIS_620;
import static org.sellcom.core.io.charset.MoreCharsets.UTF_32;
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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LocaleToCharset {

	private LocaleToCharset() {
		// Utility class, not to be instantiated
	}


	static List<Charset> getSupportedCharsets(Locale locale) {
		switch (locale.getLanguage()) {
			case "af": { // Afrikaans
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_14, ISO_8859_15, WINDOWS_1252);
			}
			case "ar": { // Arabic
				ensureScript(locale, "Arab");

				return customCharsets(ISO_8859_6, WINDOWS_1256);
			}
			case "ast": { // Asturian
				ensureScript(locale, "Latn");

				return customCharsets();
			}
			case "az": { // Azerbaijani
				ensureScript(locale, "Cyrl", "Latn");

				if (Objects.equals(locale.getScript(), "Cyrl")) {
					return customCharsets();
				} else {
					return customCharsets();
				}
			}
			case "be": { // Belarusian
				ensureScript(locale, "Cyrl");

				return customCharsets(ISO_8859_5, WINDOWS_1251);
			}
			case "bg": { // Bulgarian
				ensureScript(locale, "Cyrl");

				// NOTE: Not considering "Ѝ" and "ѝ" characters
				return customCharsets(ISO_8859_5, KOI8_R, KOI8_U, WINDOWS_31J, WINDOWS_949, WINDOWS_1251);
			}
			case "br": { // Breton
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "bs": { // Bosnian
				ensureScript(locale, "Cyrl", "Latn");

				if (Objects.equals(locale.getScript(), "Cyrl")) {
					return customCharsets(ISO_8859_5, WINDOWS_1251);
				} else {
					return customCharsets(ISO_8859_2, ISO_8859_16, WINDOWS_1250);
				}
			}
			case "ca": { // Catalan
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254);
			}
			case "co": { // Corsican
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254);
			}
			case "crh": { // Crimean Tatar
				ensureScript(locale, "Cyrl", "Latn");

				if (Objects.equals(locale.getScript(), "Cyrl")) {
					return customCharsets(ISO_8859_5, KOI8_R, KOI8_U, WINDOWS_31J, WINDOWS_949, WINDOWS_1251);
				} else {
					return customCharsets(ISO_8859_3, ISO_8859_9, WINDOWS_1254);
				}
			}
			case "cs": { // Czech
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_2, WINDOWS_1250);
			}
			case "cy": { // Welsh
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_14);
			}
			case "da": { // Danish
				ensureScript(locale, "Latn");

				// NOTE: Not considering "Ǿ" and "ǿ" characters
				return customCharsets(ISO_8859_1, ISO_8859_4, ISO_8859_9, ISO_8859_10, ISO_8859_13, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254, WINDOWS_1257, WINDOWS_1258);
			}
			case "de": { // German
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_4, ISO_8859_9, ISO_8859_10, ISO_8859_13, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1250, WINDOWS_1252, WINDOWS_1254, WINDOWS_1257, WINDOWS_1258);
			}
			case "dsb": { // Lower Sorbian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_2, WINDOWS_1250);
			}
			case "el": { // Greek
				ensureScript(locale, "Grek");

				return customCharsets(ISO_8859_7, WINDOWS_1253);
			}
			case "en": { // English
				ensureScript(locale, "Latn");

				return asciiCompatibleCharsets();
			}
			case "eo": { // Esperanto
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_3);
			}
			case "es": { // Spanish
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_9, ISO_8859_15, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "et": { // Estonian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_4, ISO_8859_10, ISO_8859_13, ISO_8859_15, WINDOWS_1252, WINDOWS_1257);
			}
			case "eu": { // Basque
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "ext": { // Extremaduran
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "fa": { // Persian
				ensureScript(locale, "Arab");

				return customCharsets(WINDOWS_1256);
			}
			case "fi": { // Finnish
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_4, ISO_8859_10, ISO_8859_13, ISO_8859_15, WINDOWS_1252, WINDOWS_1257);
			}
			case "fo": { // Faroese
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_10, ISO_8859_15, WINDOWS_1252);
			}
			case "fr": { // French
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "fur": { // Friulian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254);
			}
			case "fy": { // Western Frisian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "ga": { // Irish
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_9, ISO_8859_10, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1250, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "gd": { // Scottish Gaelic
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254);
			}
			case "gl": { // Galician
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "gv": { // Manx
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1250, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "haw": { // Hawaiian
				ensureScript(locale, "Latn");

				return asciiCompatibleCharsets();
			}
			case "hi": { // Hindi
				ensureScript(locale, "Deva");

				return customCharsets(ISCII91);
			}
			case "hr": { // Croatian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_2, ISO_8859_16, WINDOWS_1250);
			}
			case "hsb": { // Upper Sorbian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_2, WINDOWS_1250);
			}
			case "hu": { // Hungarian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_2, ISO_8859_16, WINDOWS_1250);
			}
			case "hy": { // Armenian
				ensureScript(locale, "Armn");

				return customCharsets();
			}
			case "in": { // Indonesian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_4, ISO_8859_9, ISO_8859_10, ISO_8859_13, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1250, WINDOWS_1252, WINDOWS_1254, WINDOWS_1257, WINDOWS_1258);
			}
			case "is": { // Icelandic
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_10, ISO_8859_15, WINDOWS_1252);
			}
			case "it": { // Italian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254);
			}
			case "iu": { // Inuktitut
				ensureScript(locale, "Cans", "Latn");

				switch (locale.getScript()) {
					case "Cans": {
						return customCharsets();
					}
					default: {
						return customCharsets(ISO_8859_2, ISO_8859_13, ISO_8859_16, WINDOWS_949, WINDOWS_1250, WINDOWS_1257);
					}
				}
			}
			case "iw": { // Hebrew
				ensureScript(locale, "Hebr");

				return customCharsets(ISO_8859_8, WINDOWS_1255);
			}
			case "ja": { // Japanese
				ensureScript(locale, "Jpan");

				return customCharsets(EUC_JP, ISO_2022_JP, SHIFT_JIS, WINDOWS_31J);
			}
			case "ji": { // Yiddish
				ensureScript(locale, "Hebr");

				return customCharsets();
			}
			case "ka": { // Georgian
				ensureScript(locale, "Geok", "Geor");

				return customCharsets();
			}
			case "kk": { // Kazakh
				ensureScript(locale, "Cyrl", "Latn");

				if (Objects.equals(locale.getScript(), "Latn")) {
					return customCharsets(ISO_8859_3, ISO_8859_9, WINDOWS_1254);
				} else {
					return customCharsets();
				}
			}
			case "kl": { // Greenlandic
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_4, ISO_8859_9, ISO_8859_10, ISO_8859_13, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254, WINDOWS_1257, WINDOWS_1258);
			}
			case "ko": { // Korean
				ensureScript(locale, "Kore");

				return customCharsets(EUC_KR, ISO_2022_KR, WINDOWS_949);
			}
			case "krl": { // Karelian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_2, ISO_8859_4, ISO_8859_10, ISO_8859_13, ISO_8859_16, WINDOWS_1250, WINDOWS_1257);
			}
			case "ku": { // Kurdish
				ensureScript(locale, "Arab", "Latn");

				switch (locale.getScript()) {
					case "Arab": {
						return customCharsets();
					}
					case "Latn": {
						return customCharsets(ISO_8859_3, ISO_8859_9, WINDOWS_1254);
					}
				}

				switch (locale.getCountry()) {
					case "AM": {
						return customCharsets();
					}
					case "IQ": {
						return customCharsets(ISO_8859_3, ISO_8859_9, WINDOWS_1254);
					}
					case "IR": {
						return customCharsets(ISO_8859_3, ISO_8859_9, WINDOWS_1254);
					}
					case "SY": {
						return customCharsets();
					}
					case "TR": {
						return customCharsets();
					}
				}

				break;
			}
			case "kw": { // Cornish
				ensureScript(locale, "Latn");

				return asciiCompatibleCharsets();
			}
			case "ky": { // Kyrgyz
				ensureScript(locale, "Cyrl");

				return customCharsets();
			}
			case "la": { // Latin
				ensureScript(locale, "Latn");

				return asciiCompatibleCharsets();
			}
			case "lb": { // Luxembourgish
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_4, ISO_8859_9, ISO_8859_10, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1250, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "li": { // Limburghish
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "lij": { // Ligurian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_14, ISO_8859_15, ISO_8859_16, ISO_8859_9, WINDOWS_1252, WINDOWS_1254);
			}
			case "llt": { // Llanito
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "lt": { // Lithuanian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_4, ISO_8859_10, ISO_8859_13, WINDOWS_1257);
			}
			case "lv": { // Latvian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_4, ISO_8859_10, ISO_8859_13, WINDOWS_1257);
			}
			case "me": { // Montenegrin
				ensureScript(locale, "Cyrl", "Latn");

				if (Objects.equals(locale.getScript(), "Cyrl")) {
					return customCharsets();
				} else {
					return customCharsets(ISO_8859_2, ISO_8859_16, WINDOWS_1250);
				}
			}
			case "mk": { // Macedonian
				ensureScript(locale, "Cyrl");

				return customCharsets(ISO_8859_5, WINDOWS_1251);
			}
			case "mn": { // Mongolian
				ensureScript(locale, "Cyrl");

				return customCharsets();
			}
			case "ms": { // Malay
				ensureScript(locale, "Latn");

				return asciiCompatibleCharsets();
			}
			case "mt": { // Maltese
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_3);
			}
			case "nb": { // Norwegian Bokmål
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_9, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254);
			}
			case "nds": { // Low German
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_4, ISO_8859_9, ISO_8859_10, ISO_8859_13, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1250, WINDOWS_1252, WINDOWS_1254, WINDOWS_1257, WINDOWS_1258);
			}
			case "nl": { // Dutch
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "nn": { // Norwegian Nynorsk
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_9, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254);
			}
			case "oc": { // Occitan
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254);
			}
			case "pl": { // Polish
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_2, ISO_8859_13, ISO_8859_16, WINDOWS_1250, WINDOWS_1257);
			}
			case "pms": { // Piedmontese
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254);
			}
			case "ps": { // Pashto
				ensureScript(locale, "Arab");

				return customCharsets();
			}
			case "pt": { // Portuguese
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_9, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254);
			}
			case "rm": { // Romansh
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "ro": { // Romanian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_16);
			}
			case "ru": { // Russian
				ensureScript(locale, "Cyrl");

				return customCharsets(ISO_8859_5, KOI8_R, KOI8_U, WINDOWS_31J, WINDOWS_949, WINDOWS_1251);
			}
			case "rue": { // Rusyn
				ensureScript(locale, "Cyrl");

				return customCharsets(KOI8_U, WINDOWS_1251);
			}
			case "sc": { // Sardinian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1250, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "scn": { // Sicilian
				ensureScript(locale, "Latn");

				return asciiCompatibleCharsets();
			}
			case "sco": { // Scots
				ensureScript(locale, "Latn");

				return customCharsets();
			}
			case "se": { // Northern Sami
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_4, ISO_8859_10);
			}
			case "sk": { // Slovak
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_2, WINDOWS_1250);
			}
			case "sl": { // Slovenian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_2, ISO_8859_4, ISO_8859_10, ISO_8859_13, ISO_8859_16, WINDOWS_1250, WINDOWS_1257);
			}
			case "sq": { // Albanian
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_9, ISO_8859_14, ISO_8859_15, ISO_8859_16, WINDOWS_1250, WINDOWS_1252, WINDOWS_1254, WINDOWS_1258);
			}
			case "sr": { // Serbian
				ensureScript(locale, "Cyrl", "Latn");

				if (Objects.equals(locale.getScript(), "Latn")) {
					return customCharsets(ISO_8859_2, ISO_8859_16, WINDOWS_1250);
				} else {
					return customCharsets(ISO_8859_5, WINDOWS_1251);
				}
			}
			case "sv": { // Swedish
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_1, ISO_8859_4, ISO_8859_9, ISO_8859_10, ISO_8859_13, ISO_8859_14, ISO_8859_15, WINDOWS_1252, WINDOWS_1254, WINDOWS_1257, WINDOWS_1258);
			}
			case "sw": { // Swahili
				ensureScript(locale, "Latn");

				return asciiCompatibleCharsets();
			}
			case "ta": { // Tamil
				ensureScript(locale, "Taml");

				return customCharsets();
			}
			case "tg": { // Tajik
				ensureScript(locale, "Cyrl");

				return customCharsets();
			}
			case "th": { // Thai
				ensureScript(locale, "Thai");

				return customCharsets(ISO_8859_11, TIS_620, WINDOWS_874);
			}
			case "tk": { // Turkmen
				ensureScript(locale, "Cyrl", "Latn");

				if (Objects.equals(locale.getScript(), "Cyrl")) {
					return customCharsets();
				} else {
					return customCharsets(ISO_8859_2, WINDOWS_1250);
				}
			}
			case "tl": { // Tagalog
				ensureScript(locale, "Tglg");

				return customCharsets();
			}
			case "tn": { // Tswana
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_15, ISO_8859_16, WINDOWS_1252, WINDOWS_1254);
			}
			case "tr": { // Turkish
				ensureScript(locale, "Latn");

				return customCharsets(ISO_8859_3, ISO_8859_9, WINDOWS_1254);
			}
			case "tt": { // Tatar
				ensureScript(locale, "Cyrl");

				return customCharsets();
			}
			case "uk": { // Ukrainian
				ensureScript(locale, "Cyrl");

				return customCharsets(KOI8_U, WINDOWS_1251);
			}
			case "ur": { // Urdu
				ensureScript(locale, "Arab");

				return customCharsets(WINDOWS_1256);
			}
			case "uz": { // Uzbek
				ensureScript(locale, "Cyrl", "Latn");

				if (Objects.equals(locale.getScript(), "Cyrl")) {
					return customCharsets();
				} else {
					return customCharsets();
				}
			}
			case "vec": { // Venetian
				ensureScript(locale, "Latn");

				return customCharsets();
			}
			case "vi": { // Vietnamese
				ensureScript(locale, "Latn");

				return customCharsets(WINDOWS_1258);
			}
			case "wa": { // Walloon
				ensureScript(locale, "Latn");

				return customCharsets();
			}
			case "xh": { // Xhosa
				ensureScript(locale, "Latn");

				return asciiCompatibleCharsets();
			}
			case "zh": { // Chinese
				ensureScript(locale, "Hans", "Hant");

				switch (locale.getScript()) {
					case "Hans": {
						return simplifiedChineseCharsets();
					}
					case "Hant": {
						return traditionalChineseCharsets();
					}
				}

				switch (locale.getCountry()) {
					case "CN": {
						return simplifiedChineseCharsets();
					}
					case "HK": {
						return traditionalChineseCharsets();
					}
					case "MO": {
						return traditionalChineseCharsets();
					}
					case "SG": {
						return simplifiedChineseCharsets();
					}
					case "TW": {
						return traditionalChineseCharsets();
					}
				}

				break;
			}
			case "zu": { // Zulu
				ensureScript(locale, "Latn");

				return asciiCompatibleCharsets();
			}
		}

		return customCharsets();
	}


	private static List<Charset> asciiCompatibleCharsets() {
		return customCharsets(BIG5, BIG5_HKSCS, EUC_JP, EUC_KR, GB2312, GB18030, GBK, ISCII91, ISO_2022_CN, ISO_2022_JP, ISO_2022_KR, ISO_8859_1, ISO_8859_2, ISO_8859_3, ISO_8859_4, ISO_8859_5, ISO_8859_6, ISO_8859_7, ISO_8859_8, ISO_8859_9, ISO_8859_10, ISO_8859_11, ISO_8859_13, ISO_8859_14, ISO_8859_15, ISO_8859_16, KOI8_R, KOI8_U, SHIFT_JIS, TIS_620, WINDOWS_31J, WINDOWS_874, WINDOWS_949, WINDOWS_1250, WINDOWS_1251, WINDOWS_1252, WINDOWS_1253, WINDOWS_1254, WINDOWS_1255, WINDOWS_1256, WINDOWS_1257, WINDOWS_1258);
	}

	private static List<Charset> customCharsets(Charset... additionalCharsets) {
		Stream<Charset> charsetStream = (additionalCharsets == null)
				? univeralCharsets()
				: Stream.concat(univeralCharsets(), Arrays.stream(additionalCharsets));

		return charsetStream.collect(Collectors.toList());
	}

	private static void ensureScript(Locale locale, String... scripts) {
		String script = locale.getScript();
		if (Objects.equals(script, "")) {
			return;
		}
		if (Arrays.binarySearch(scripts, script) != -1) {
			return;
		}

		throw new IllegalArgumentException(String.format("Unsupported locale: %s", locale));
	}

	private static List<Charset> simplifiedChineseCharsets() {
		return customCharsets(GB2312, GB18030, GBK, ISO_2022_CN);
	}

	private static List<Charset> traditionalChineseCharsets() {
		return customCharsets(BIG5, BIG5_HKSCS, ISO_2022_CN);
	}

	private static Stream<Charset> univeralCharsets() {
		return Stream.of(UTF_8, UTF_8BOM, UTF_16, UTF_16BE, UTF_16LE, UTF_32, UTF_32BE, UTF_32LE);
	}

}
