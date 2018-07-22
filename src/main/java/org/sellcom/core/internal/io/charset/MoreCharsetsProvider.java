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
package org.sellcom.core.internal.io.charset;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MoreCharsetsProvider extends CharsetProvider {

	private static final Map<String, Charset> PROVIDED_CHARSETS;

	static {
		NavigableMap<String, Charset> providedCharsets = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		registerCharset(providedCharsets, new ISO_8859_10());
		registerCharset(providedCharsets, new ISO_8859_14());
		registerCharset(providedCharsets, new ISO_8859_16());
		registerCharset(providedCharsets, new Utf8BomCharset());

		PROVIDED_CHARSETS = Collections.unmodifiableNavigableMap(providedCharsets);
	}


	@Override
	public Iterator<Charset> charsets() {
		return PROVIDED_CHARSETS.values().iterator();
	}

	@Override
	public Charset charsetForName(String charsetName) {
		return PROVIDED_CHARSETS.getOrDefault(charsetName, null);
	}


	private static void registerCharset(NavigableMap<String, Charset> charsets, Charset charset) {
		charsets.put(charset.name(), charset);
		charset.aliases().forEach(alias -> charsets.put(alias, charset));
	}

}
