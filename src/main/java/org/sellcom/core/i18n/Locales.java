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

import static java.util.Locale.ROOT;

import java.util.Locale;
import java.util.Optional;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;

/**
 * Operations with {@link Locale}s.
 *
 * @since 1.0
 */
public class Locales {

	private Locales() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the parent locale of the given locale.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 *
	 * @since 1.0
	 */
	public static Optional<Locale> getParentLocale(Locale locale) {
		Contract.checkArgument(locale != null, "Locale must not be null");

		if (locale.equals(ROOT)) {
			return Optional.empty();
		}

		if (!Strings.isNullOrEmpty(locale.getVariant())) {
			return Optional.of(createLocale(
					locale.getLanguage(),
					locale.getScript(),
					locale.getCountry(),
					""));
		}
		if (!Strings.isNullOrEmpty(locale.getCountry())) {
			return Optional.of(createLocale(
					locale.getLanguage(),
					locale.getScript(),
					"",
					""));
		}
		if (!Strings.isNullOrEmpty(locale.getScript())) {
			return Optional.of(createLocale(
					locale.getLanguage(),
					"",
					"",
					""));
		}

		return Optional.of(ROOT);
	}


	private static Locale createLocale(String language, String script, String region, String variant) {
		return new Locale.Builder()
				.setLanguage(language)
				.setScript(script)
				.setRegion(region)
				.setVariant(variant)
				.build();
	}

}
