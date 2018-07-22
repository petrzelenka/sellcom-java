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

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;

/**
 * Static singleton for accessing application resources.
 *
 * <h3>References</h3>
 * Properties can refer to other properties using the <code>{&#64;&lt;property&gt;}</code> syntax.
 * The referenced property must either be located in the same file (the order of the properties in the file is not important) or must already be loaded.
 *
 * <h3>Example</h3>
 * <pre>
 * actions.openDocument.chooseFileDialog.title = {&#64;actions.openDocument.title};
 * actions.openDocument.menu.title = _{&#64;actions.openDocument.title};...
 * actions.openDocument.title = Open document
 * </pre>
 *
 * @since 1.0
 */
public class Resources {

	private static final Pattern REFERENCE_PATTERN = Pattern.compile("\\{@(?<referencedKey>[^}]*)\\}");

	private static final Map<Locale, Map<String, String>> REGISTERED_MAPPINGS = new HashMap<>();


	private Resources() {
		// Static singleton, not to be instantiated
	}


	/**
	 * Clears the resources for the default locale.
	 *
	 * @since 1.0
	 */
	public static void clearResources() {
		clearResources(Locale.getDefault());
	}

	/**
	 * Clears the resources for the given locale.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void clearResources(Locale locale) {
		Contract.checkArgument(locale != null, "Locale must not be null");

		getRegisteredMappings(locale).clear();
	}

	/**
	 * Returns the string resource with the given key for the default locale.
	 * Selects the resource with grammatically correct pluralization for the given quantity.
	 * Interpolates the given arguments using {@link MessageFormat#format(String, Object...)} into the resource.
	 *
	 * @throws IllegalArgumentException if {@code key} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static String getQuantityString(String key, int quantity, Object... args) {
		return getQuantityString(Locale.getDefault(), key, quantity, args);
	}

	/**
	 * Returns the string resource with the given key for the given locale.
	 * Selects the resource with grammatically correct pluralization for the given quantity.
	 * Interpolates the given arguments using {@link MessageFormat#format(String, Object...)} into the resource.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 * @throws IllegalArgumentException if {@code key} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static String getQuantityString(Locale locale, String key, int quantity, Object... args) {
		Contract.checkArgument(locale != null, "Locale must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(key), "Key must not be null or empty");

		return getString(locale, key + PluralizationRules.getSuffix(locale, key, quantity), args);
	}

	/**
	 * Returns the string resource with the given key for the given locale.
	 * Interpolates the given arguments using {@link MessageFormat#format(String, Object...)} into the resource.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 * @throws IllegalArgumentException if {@code key} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static String getString(Locale locale, String key, Object... args) {
		Contract.checkArgument(locale != null, "Locale must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(key), "Key must not be null or empty");

		Map<String, String> registeredMappings = getRegisteredMappings(locale);
		String value = registeredMappings.get(key);
		if (value == null) {
			throw new ResourceNotFoundException(String.format("Resource not found: %s", key));
		}

		if (args != null) {
			value = MessageFormat.format(value, args);
		}

		return value;
	}

	/**
	 * Returns the string resource with the given key for the default locale.
	 * Interpolates the given arguments using {@link MessageFormat#format(String, Object...)} into the resource.
	 *
	 * @throws IllegalArgumentException if {@code key} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static String getString(String key, Object... args) {
		return getString(Locale.getDefault(), key, args);
	}

	/**
	 * Loads resources from the given resource bundle.
	 *
	 * @throws IllegalArgumentException if {@code baseName} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static void loadResources(String baseName) {
		registerMappingsForLocale(Locale.getDefault(), toMap(ResourceBundle.getBundle(baseName, Locale.getDefault())));
	}

	/**
	 * Loads resources from the given resource bundle.
	 *
	 * @throws IllegalArgumentException if {@code baseName} is {@code null} or empty
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void loadResources(String baseName, Locale locale) {
		registerMappingsForLocale(locale, toMap(ResourceBundle.getBundle(baseName, locale)));
	}

	/**
	 * Loads resources from the given resource bundle.
	 *
	 * @throws IllegalArgumentException if {@code baseName} is {@code null} or empty
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 * @throws IllegalArgumentException if {@code classLoader} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void loadResources(String baseName, Locale locale, ClassLoader classLoader) {
		registerMappingsForLocale(locale, toMap(ResourceBundle.getBundle(baseName, locale, classLoader)));
	}


	private static Map<String, String> getRegisteredMappings(Locale locale) {
		Map<String, String> registeredMappings = REGISTERED_MAPPINGS.get(locale);
		if (registeredMappings == null) {
			REGISTERED_MAPPINGS.put(locale, registeredMappings = new HashMap<>());
		}

		return registeredMappings;
	}

	private static void registerMapping(Map<String, String> registeredMappings, String key, String value) {
		StringBuffer stringBuffer = new StringBuffer();

		Matcher matcher = REFERENCE_PATTERN.matcher(value);
		while (matcher.find()) {
			String referencedKey = matcher.group("referencedKey");
			String referencedValue = registeredMappings.get(referencedKey);
			if (referencedValue == null) {
				throw new ResourceNotFoundException(String.format("Resource not found: %s", key));
			}

			matcher.appendReplacement(stringBuffer, referencedValue);
		}
		matcher.appendTail(stringBuffer);

		registeredMappings.put(key, stringBuffer.toString());
	}

	private static void registerMappingsForLocale(Locale locale, Map<String, String> pendingMappings) {
		Map<String, String> registeredMappings = getRegisteredMappings(locale);

		// ----------------------------
		// STEP 1: PERFORM PRESELECTION
		// ----------------------------
		Set<String> keysReferencedFromPendingMappings = new LinkedHashSet<>();
		for (Iterator<Map.Entry<String, String>> iterator = pendingMappings.entrySet().iterator(); iterator.hasNext(); ) {
			Map.Entry<String, String> mapping = iterator.next();

			Matcher matcher = REFERENCE_PATTERN.matcher(mapping.getValue());
			if (matcher.find()) {
				// References present, save referenced pending keys and defer registration
				do {
					String referencedKey = matcher.group("referencedKey");
					if (!registeredMappings.containsKey(referencedKey)) {
						keysReferencedFromPendingMappings.add(referencedKey);
					}
				} while (matcher.find());
			} else {
				// References not present, register immediately
				registeredMappings.put(mapping.getKey(), mapping.getValue());

				iterator.remove();
			}
		}

		// ----------------------------------------------------------
		// STEP 2: REGISTER MAPPINGS REFERENCED FROM PENDING MAPPINGS
		// ----------------------------------------------------------
		for (Iterator<String> iterator = keysReferencedFromPendingMappings.iterator(); iterator.hasNext(); ) {
			String key = iterator.next();
			registerMapping(registeredMappings, key, registeredMappings.get(key));

			iterator.remove();
		}

		// -------------------------------------------
		// STEP 3: REGISTER REMAINING PENDING MAPPINGS
		// -------------------------------------------
		for (Iterator<Map.Entry<String, String>> iterator = pendingMappings.entrySet().iterator(); iterator.hasNext(); ) {
			Map.Entry<String, String> mapping = iterator.next();
			registerMapping(registeredMappings, mapping.getKey(), mapping.getValue());

			iterator.remove();
		}
	}

	private static HashMap<String, String> toMap(ResourceBundle bundle) {
		HashMap<String, String> map = new HashMap<>();
		for (Enumeration<String> keys = bundle.getKeys(); keys.hasMoreElements(); ) {
			String key = keys.nextElement();
			map.put(key, bundle.getString(key));
		}

		return map;
	}

}
