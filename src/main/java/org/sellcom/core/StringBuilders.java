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

/**
 * Operations with {@code StringBuilder}s.
 *
 * @since 1.0
 */
public class StringBuilders {

	private StringBuilders() {
		// Utility class, not to be instantiated
	}


	/**
	 * Clears the contents of the given builder.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void clear(StringBuilder builder) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		builder.setLength(0);
	}

	/**
	 * Checks whether the given builder is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(StringBuilder builder) {
		return (builder == null) || (builder.length() == 0);
	}

	/**
	 * Removes all occurrences of the given character from the given builder.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void removeAll(StringBuilder builder, char character) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		int i = 0;
		int j = builder.length();
		while (i < j) {
			if (builder.charAt(i) == character) {
				builder.deleteCharAt(i);
				j -= 1;
			} else {
				i += 1;
			}
		}
	}

	/**
	 * Removes all occurrences of the given string from the given builder.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code string} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static void removeAll(StringBuilder builder, String string) {
		Contract.checkArgument(builder != null, "Builder must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(string), "String must not be null or empty");

		int index = builder.indexOf(string);
		while (index >= 0) {
			builder.delete(index, index + string.length());
			index = builder.indexOf(string, index);
		}
	}

	/**
	 * Replaces all occurrences of the given character in the given builder with the given replacement.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void replaceAll(StringBuilder builder, char character, char replacement) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		for (int i = 0, j = builder.length(); i < j; i++) {
			if (builder.charAt(i) == character) {
				builder.setCharAt(i, replacement);
			}
		}
	}

	/**
	 * Replaces all occurrences of the given string in the given builder with the given replacement.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code string} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static void replaceAll(StringBuilder builder, String string, String replacement) {
		Contract.checkArgument(builder != null, "Builder must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(string), "String must not be null or empty");

		int index = builder.indexOf(string);
		while (index >= 0) {
			builder.replace(index, index + string.length(), replacement);
			index += replacement.length();
			index = builder.indexOf(string, index);
		}
	}

	/**
	 * Converts the contents of the given builder to lower case in a locale-insensitive way.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void toLowerCase(StringBuilder builder) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		for (int i = 0, j = builder.length(); i < j; i++) {
			builder.setCharAt(i, Character.toLowerCase(builder.charAt(i)));
		}
	}

	/**
	 * Converts the contents of the given builder to upper case in a locale-insensitive way.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void toUpperCase(StringBuilder builder) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		for (int i = 0, j = builder.length(); i < j; i++) {
			builder.setCharAt(i, Character.toUpperCase(builder.charAt(i)));
		}
	}

}
