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

import java.util.Locale;

class PluralizationRules {

	private static final String FEW = ".few";

	private static final String MANY = ".many";

	private static final String ONE = ".one";

	private static final String OTHER = ".other";

	private static final String TWO = ".two";

	private static final String ZERO = ".zero";


	private PluralizationRules() {
		// Utility class, not to be instantiated
	}


	public static String getSuffix(Locale locale, String key, int quantity) {
		switch (locale.getLanguage()) {
			case "af": { // Afrikaans
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "ar": { // Arabic
				if (quantity == 0) {
					return ZERO;
				}
				if (quantity == 1) {
					return ONE;
				}
				if (quantity == 2) {
					return TWO;
				}
				if (isInRange(quantity % 100, 3, 10)) {
					return FEW;
				}
				if ((quantity % 100) >= 11) {
					return MANY;
				}

				return OTHER;
			}
			case "az": { // Azerbaijani
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "be": { // Belarusian
				if (((quantity % 10) == 1) && ((quantity % 100) != 11)) {
					return ONE;
				}
				if (isInRange(quantity % 10, 2, 4) && !isInRange(quantity % 100, 12, 14)) {
					return FEW;
				}

				return OTHER;
			}
			case "bg": { // Bulgarian
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "bs": { // Bosnian
				if (((quantity % 10) == 1) && ((quantity % 100) != 11)) {
					return ONE;
				}
				if (isInRange(quantity % 10, 2, 4) && !isInRange(quantity % 100, 12, 14)) {
					return FEW;
				}

				return OTHER;
			}
			case "ca": { // Catalan
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "cs": { // Czech
				if (quantity == 1) {
					return ONE;
				}
				if (isInRange(quantity, 2, 4)) {
					return FEW;
				}

				return OTHER;
			}
			case "da": { // Danish
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "de": { // German
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "el": { // Greek
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "en": { // English
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "eo": { // Esperanto
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "es": { // Spanish
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "et": { // Estonian
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "eu": { // Basque
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "fi": { // Finnish
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "fo": { // Faroese
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "fr": { // French
				if ((quantity == 0) || (quantity == 1)) {
					return ONE;
				}

				return OTHER;
			}
			case "fy": { // Western Frisian
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "gl": { // Galician
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "hr": { // Croatian
				if (((quantity % 10) == 1) && ((quantity % 100) != 11)) {
					return ONE;
				}
				if (isInRange(quantity % 10, 2, 4) && !isInRange(quantity % 100, 12, 14)) {
					return FEW;
				}

				return OTHER;
			}
			case "hu": { // Hungarian
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "hy": { // Armenian
				if ((quantity == 0) || (quantity == 1)) {
					return ONE;
				}

				return OTHER;
			}
			case "is": { // Icelandic
				if (((quantity % 10) == 1) && ((quantity % 100) != 11)) {
					return ONE;
				}

				return OTHER;
			}
			case "it": { // Italian
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "iu": { // Inuktitut
				if (quantity == 1) {
					return ONE;
				}
				if (quantity == 2) {
					return TWO;
				}

				return OTHER;
			}
			case "iw": { // Hebrew
				if (quantity == 1) {
					return ONE;
				}
				if (quantity == 2) {
					return TWO;
				}
				if (((quantity % 10) == 0) && (quantity != 0)) {
					return MANY;
				}

				return OTHER;
			}
			case "ja": { // Japanese
				return "";
			}
			case "ka": { // Georgian
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "kk": { // Kazakh
				return "";
			}
			case "ko": { // Korean
				return "";
			}
			case "ky": { // Kyrgyz
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "la": { // Latin
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "lb": { // Luxembourgish
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "lt": { // Lithuanian
				if (((quantity % 10) == 1) && ((quantity % 100) != 11)) {
					return ONE;
				}
				if (((quantity % 10) >= 2) && !isInRange(quantity % 100, 11, 19)) {
					return ONE;
				}

				return OTHER;
			}
			case "lv": { // Latvian
				if (((quantity % 10) == 0) || isInRange(quantity % 100, 11, 19)) {
					return ZERO;
				}
				if (((quantity % 10) == 1) && ((quantity % 100) != 11)) {
					return ONE;
				}

				return OTHER;
			}
			case "mk": { // Macedonian
				if ((quantity % 10) == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "mn": { // Mongolian
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "nb": { // Norwegian Bokmål
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "nl": { // Dutch
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "nn": { // Norwegian Nynorsk
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "pl": { // Polish
				if (quantity == 1) {
					return ONE;
				}
				if (isInRange(quantity % 10, 2, 4) && !isInRange(quantity % 100, 12, 14)) {
					return FEW;
				}

				return OTHER;
			}
			case "pt": { // Portuguese
				switch (locale.getCountry()) {
					case "BR": {
						if ((quantity == 0) || (quantity == 1)) {
							return ONE;
						}

						return OTHER;
					}
					default: {
						if (quantity == 1) {
							return ONE;
						}

						return OTHER;
					}
				}
			}
			case "rm": { // Romansh
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "ro": { // Romanian
				if (quantity == 1) {
					return ONE;
				}
				if ((quantity == 0) || isInRange(quantity % 100, 1, 19)) {
					return FEW;
				}

				return OTHER;
			}
			case "ru": { // Russian
				if (((quantity % 10) == 1) && ((quantity % 100) != 11)) {
					return ONE;
				}
				if (isInRange(quantity % 10, 2, 4) && !isInRange(quantity % 100, 12, 14)) {
					return FEW;
				}

				return OTHER;
			}
			case "se": { // Northern Sami
				if (quantity == 1) {
					return ONE;
				}
				if (quantity == 2) {
					return TWO;
				}

				return OTHER;
			}
			case "sk": { // Slovak
				if (quantity == 1) {
					return ONE;
				}
				if (isInRange(quantity, 2, 4)) {
					return FEW;
				}

				return OTHER;
			}
			case "sl": { // Slovenian
				if ((quantity % 100) == 1) {
					return ONE;
				}
				if ((quantity % 100) == 2) {
					return TWO;
				}
				if (isInRange(quantity % 100, 3, 4)) {
					return FEW;
				}

				return OTHER;
			}
			case "sq": { // Albanian
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "sr": { // Serbian
				if (((quantity % 10) == 1) && ((quantity % 100) != 11)) {
					return ONE;
				}
				if (isInRange(quantity % 10, 2, 4) && !isInRange(quantity % 100, 12, 14)) {
					return FEW;
				}

				return OTHER;
			}
			case "sv": { // Swedish
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "tk": { // Turkmen
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "tr": { // Turkish
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "uk": { // Ukrainian
				if (((quantity % 10) == 1) && ((quantity % 100) != 11)) {
					return ONE;
				}
				if (isInRange(quantity % 10, 2, 4) && !isInRange(quantity % 100, 12, 14)) {
					return FEW;
				}

				return OTHER;
			}
			case "uz": { // Uzbek
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "vi": { // Vietnamese
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "wa": { // Walloon
				if ((quantity == 0) || (quantity == 1)) {
					return ONE;
				}

				return OTHER;
			}
			case "yi": { // Yiddish
				if (quantity == 1) {
					return ONE;
				}

				return OTHER;
			}
			case "zh": { // Chinese
				return "";
			}
		}

		throw new LocaleNotSupportedException(String.format("Locale not supported: %s", locale));
	}


	private static boolean isInRange(int n, int min, int max) {
		return (n >= min) && (n <= max);
	}

}
