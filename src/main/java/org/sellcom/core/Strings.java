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

import static java.util.Locale.ROOT;

import java.util.Arrays;

import org.sellcom.core.util.MoreArrays;

/**
 * Operations with strings.
 *
 * @since 1.0
 */
public class Strings {

	private Strings() {
		// Utility class, not to be instantiated
	}


	/**
	 * Centers the given string within the given target length using the given padding.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code targetLength} is negative
	 *
	 * @since 1.4
	 */
	public static String center(String string, int targetLength, char padding) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(targetLength >= 0, "Target length must not be negative: {0}", targetLength);

		if (string.length() >= targetLength) {
			return string;
		}

		StringBuilder builder = new StringBuilder(targetLength);

		int padStart = (targetLength - string.length()) / 2;
		for (int i = 0; i < padStart; i++) {
			builder.append(padding);
		}

		builder.append(string);

		int padEnd = targetLength - builder.length();
		for (int i = 0; i < padEnd; i++) {
			builder.append(padding);
		}

		return builder.toString();
	}

	/**
	 * Returns the common prefix of the given strings.
	 *
	 * @throws IllegalArgumentException if {@code first} is {@code null}
	 * @throws IllegalArgumentException if {@code other} contain {@code null}
	 *
	 * @since 1.4
	 */
	public static String commonPrefix(String first, String... other) {
		Contract.checkArgument(first != null, "First strings must not be null");

		if (other == null) {
			return first;
		}

		Contract.checkArgument(!MoreArrays.contains(other, null), "Other strings must not contain null");

		int maxPrefixLength = Math.min(first.length(), Arrays.stream(other)
				.mapToInt(String::length)
				.min()
				.getAsInt());

		int offset = 0;
		outer: for (; offset < maxPrefixLength; offset++) {
			for (String currentString : other) {
				if (currentString.charAt(offset) != first.charAt(offset)) {
					break outer;
				}
			}
		}

		return first.substring(0, offset);
	}

	/**
	 * Returns the common suffix of the given strings.
	 *
	 * @throws IllegalArgumentException if {@code first} is {@code null}
	 * @throws IllegalArgumentException if {@code other} contain {@code null}
	 *
	 * @since 1.4
	 */
	public static String commonSuffix(String first, String... other) {
		Contract.checkArgument(first != null, "First strings must not be null");

		if (other == null) {
			return first;
		}

		Contract.checkArgument(!MoreArrays.contains(other, null), "Other strings must not contain null");

		int maxPrefixLength = Math.min(first.length(), Arrays.stream(other)
				.mapToInt(String::length)
				.min()
				.getAsInt());

		int offset = 0;
		outer: for (; offset < maxPrefixLength; offset++) {
			for (String currentString : other) {
				if (currentString.charAt(currentString.length() - offset - 1) != first.charAt(first.length() - offset - 1)) {
					break outer;
				}
			}
		}

		return first.substring(first.length() - offset);
	}

	/**
	 * Returns the given string if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static String emptyToNull(String string) {
		return isNullOrEmpty(string) ? null : string;
	}

	/**
	 * Checks whether the given string ends with the given suffix, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean endsWith(String string, String suffix, boolean ignoreCase) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(suffix != null, "Suffix must not be null");

		return string.regionMatches(ignoreCase, string.length() - suffix.length(), suffix, 0, suffix.length());
	}

	/**
	 * Ensures that the given string starts with the given prefix.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String ensurePrefix(String string, String prefix) {
		return ensurePrefix(string, prefix, false);
	}

	/**
	 * Ensures that the given string starts with the given prefix, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String ensurePrefix(String string, String prefix, boolean ignoreCase) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(prefix != null, "Prefix must not be null");

		if (!startsWith(string, prefix, ignoreCase)) {
			return prefix + string;
		} else {
			return string;
		}
	}

	/**
	 * Ensures that the given string ends with the given suffix.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String ensureSuffix(String string, String suffix) {
		return ensureSuffix(string, suffix, false);
	}

	/**
	 * Ensures that the given string ends with the given suffix, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String ensureSuffix(String string, String suffix, boolean ignoreCase) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(suffix != null, "Suffix must not be null");

		if (!endsWith(string, suffix, ignoreCase)) {
			return string + suffix;
		} else {
			return string;
		}
	}

	/**
	 * Inserts the given padding between the characters of the given string.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code padding} is {@code null}
	 *
	 * @since 1.4
	 */
	public static String interleavePadding(String string, String padding) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(padding != null, "Padding must not be null");

		StringBuilder builder = new StringBuilder();
		string.codePoints().forEach(codePoint -> {
			builder.appendCodePoint(codePoint);
			builder.append(padding);
		});

		// Remove redundant padding at the end
		builder.setLength(builder.length() - 1);

		return builder.toString();
	}

	/**
	 * Checks whether the given string is composed of whitespace characters only.
	 *
	 * @since 1.6
	 *
	 * @see Character#isWhitespace(int)
	 */
	public static boolean isBlank(String string) {
		Contract.checkArgument(string != null, "String must not be null");

		for (int offset = 0, length = string.length(); offset < length; ) {
			int currentCodePoint = string.codePointAt(offset);
			if (!Character.isWhitespace(currentCodePoint)) {
				return false;
			}

			offset += Character.charCount(currentCodePoint);
		}

		return true;
	}

	/**
	 * Checks whether the given string is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(String string) {
		return (string == null) || string.isEmpty();
	}

	/**
	 * Removes any leading and trailing whitespace characters from the given string and replaces any contained sequences of whitespace characters by a single space.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String normalizeWhitespace(String string) {
		Contract.checkArgument(string != null, "String must not be null");

		String result = string.trim();
		result = result.replaceAll("(?U)\\p{Space}+", " ");

		return result;
	}

	/**
	 * Returns the given string if it is non-empty, an empty string otherwise.
	 *
	 * @since 1.0
	 */
	public static String nullToEmpty(String string) {
		return (string == null) ? "" : string;
	}

	/**
	 * Appends the given padding to the given string to ensure its given minimum length.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code minLength} is negative
	 *
	 * @since 1.4
	 */
	public static String padEnd(String string, int minLength, char padding) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(minLength >= 0, "Minimum length must not be negative: {0}", minLength);

		if (string.length() >= minLength) {
			return string;
		}

		StringBuilder builder = new StringBuilder(minLength);
		builder.append(string);
		for (int i = string.length(); i < minLength; i++) {
			builder.append(padding);
		}

		return builder.toString();
	}

	/**
	 * Prepends the given padding to the given string to ensure its given minimum length.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code minLength} is negative
	 *
	 * @since 1.4
	 */
	public static String padStart(String string, int minLength, char padding) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(minLength >= 0, "Minimum length must not be negative: {0}", minLength);

		if (string.length() >= minLength) {
			return string;
		}

		StringBuilder builder = new StringBuilder(minLength);
		for (int i = string.length(); i < minLength; i++) {
			builder.append(padding);
		}
		builder.append(string);

		return builder.toString();
	}

	/**
	 * Removes the given prefix from the given string if present.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String removePrefix(String string, String prefix) {
		return removePrefix(string, prefix, false);
	}

	/**
	 * Removes the given prefix from the given string if present, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String removePrefix(String string, String prefix, boolean ignoreCase) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(prefix != null, "Prefix must not be null");

		int prefixPosition = 0;
		int prefixLength = prefix.length();
		if (string.regionMatches(ignoreCase, prefixPosition, prefix, 0, prefixLength)) {
			return string.substring(prefixLength);
		} else {
			return string;
		}
	}

	/**
	 * Removes the given suffix from the given string if present.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String removeSuffix(String string, String suffix) {
		return removeSuffix(string, suffix, false);
	}

	/**
	 * Removes the given suffix from the given string if present, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String removeSuffix(String string, String suffix, boolean ignoreCase) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(suffix != null, "Suffix must not be null");

		int suffixLength = suffix.length();
		int suffixPosition = string.length() - suffixLength;
		if (string.regionMatches(ignoreCase, suffixPosition, suffix, 0, suffixLength)) {
			return string.substring(0, suffixPosition);
		} else {
			return string;
		}
	}

	/**
	 * Removes all whitespace characters from the given string.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String removeWhitespace(String string) {
		Contract.checkArgument(string != null, "String must not be null");

		return string.replaceAll("(?U)\\p{Space}+", "");
	}

	/**
	 * Returns a string consisting of the given number of copies of the given string.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code count} is negative
	 *
	 * @since 1.4
	 */
	public static String repeat(String string, int count) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(count >= 0, "Count must not be negative: {0}", count);

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < count; i++) {
			builder.append(string);
		}

		return builder.toString();
	}

	/**
	 * Checks whether the given string starts with the given prefix, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean startsWith(String string, String prefix, boolean ignoreCase) {
		Contract.checkArgument(string != null, "String must not be null");
		Contract.checkArgument(prefix != null, "Prefix must not be null");

		return string.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
	}

	/**
	 * Returns the given string converted to lower case in a locale-insensitive way.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String toLowerCase(String string) {
		Contract.checkArgument(string != null, "String must not be null");

		return string.toLowerCase(ROOT);
	}

	/**
	 * Returns the given string converted to upper case in a locale-insensitive way.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String toUpperCase(String string) {
		Contract.checkArgument(string != null, "String must not be null");

		return string.toUpperCase(ROOT);
	}

	/**
	 * Removes all leading whitespace characters from the given string.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 *
	 * @since 2.1
	 */
	public static String trimLeft(String string) {
		Contract.checkArgument(string != null, "String must not be null");

		StringBuilder builder = new StringBuilder(string);
		StringBuilders.trimLeft(builder);

		return builder.toString();
	}

	/**
	 * Removes all trailing whitespace characters from the given string.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 *
	 * @since 2.1
	 */
	public static String trimRight(String string) {
		Contract.checkArgument(string != null, "String must not be null");

		StringBuilder builder = new StringBuilder(string);
		StringBuilders.trimRight(builder);

		return builder.toString();
	}

}
