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
	 * Compacts runs of same consecutive characters from the given builder, retaining just a single occurrence from each run.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void compactCharacterRuns(StringBuilder builder) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		int lastCodePoint = builder.codePointAt(0);
		for (int offset = Character.charCount(lastCodePoint), length = builder.length(); offset < length; ) {
			int currentCodePoint = builder.codePointAt(offset);
			int currentCodePointLength = Character.charCount(currentCodePoint);

			if (currentCodePoint == lastCodePoint) {
				builder.delete(offset, offset + currentCodePointLength);
				length -= currentCodePointLength;
			} else {
				offset += currentCodePointLength;
				lastCodePoint = currentCodePoint;
			}
		}
	}

	/**
	 * Checks whether the given builder ends with the given suffix, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static boolean endsWith(StringBuilder builder, String suffix, boolean ignoreCase) {
		Contract.checkArgument(builder != null, "Builder must not be null");
		Contract.checkArgument(suffix != null, "Suffix must not be null");

		return regionMatches(ignoreCase, builder, builder.length() - suffix.length(), suffix, 0, suffix.length());
	}

	/**
	 * Ensures that the given builder starts with the given prefix.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void ensurePrefix(StringBuilder builder, String prefix) {
		ensurePrefix(builder, prefix, false);
	}

	/**
	 * Ensures that the given builder starts with the given prefix, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void ensurePrefix(StringBuilder builder, String prefix, boolean ignoreCase) {
		Contract.checkArgument(builder != null, "Builder must not be null");
		Contract.checkArgument(prefix != null, "Prefix must not be null");

		if (!startsWith(builder, prefix, ignoreCase)) {
			builder.insert(0, prefix);
		}
	}

	/**
	 * Ensures that the given builder ends with the given suffix.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void ensureSuffix(StringBuilder builder, String suffix) {
		ensureSuffix(builder, suffix, false);
	}

	/**
	 * Ensures that the given builder ends with the given suffix, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void ensureSuffix(StringBuilder builder, String suffix, boolean ignoreCase) {
		Contract.checkArgument(builder != null, "Builder must not be null");
		Contract.checkArgument(suffix != null, "Suffix must not be null");

		if (!endsWith(builder, suffix, ignoreCase)) {
			builder.append(suffix);
		}
	}

	/**
	 * Checks whether the given builder is composed of whitespace characters only.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 *
	 * @since 2.1
	 *
	 * @see Character#isWhitespace(int)
	 */
	public static boolean isBlank(StringBuilder builder) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		for (int offset = 0, length = builder.length(); offset < length; ) {
			int currentCodePoint = builder.codePointAt(offset);
			if (!Character.isWhitespace(currentCodePoint)) {
				return false;
			}

			offset += Character.charCount(currentCodePoint);
		}

		return true;
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
	 * Removes any leading and trailing whitespace characters from the given builder and replaces any contained sequences of whitespace characters by a single space.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void normalizeWhitespace(StringBuilder builder) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		trimLeft(builder);
		trimRight(builder);

		boolean inWhitespaceRun = false;
		for (int offset = 0, length = builder.length(); offset < length; ) {
			int currentCodePoint = builder.codePointAt(offset);
			int currentCodePointLength = Character.charCount(currentCodePoint);

			if (Character.isWhitespace(currentCodePoint)) {
				if (inWhitespaceRun) { // Consecutive whitespace character
					// Remove character
					builder.delete(offset, offset + currentCodePointLength);
					length -= currentCodePointLength;
				} else { // Start of whitespace characters
					if (currentCodePoint != ' ') {
						// Remove character
						builder.delete(offset, offset + currentCodePointLength);
						length -= currentCodePointLength;

						// Insert a single space
						builder.insert(offset, ' ');
						length += 1;
					}

					offset += 1;
				}

				inWhitespaceRun = true;
			} else {
				offset += currentCodePointLength;
				inWhitespaceRun = false;
			}
		}
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
	 * Removes the given prefix from the given builder if present.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void removePrefix(StringBuilder builder, String prefix) {
		removePrefix(builder, prefix, false);
	}

	/**
	 * Removes the given prefix from the given builder if present, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void removePrefix(StringBuilder builder, String prefix, boolean ignoreCase) {
		Contract.checkArgument(builder != null, "Builder must not be null");
		Contract.checkArgument(prefix != null, "Prefix must not be null");

		int prefixPosition = 0;
		int prefixLength = prefix.length();
		if (regionMatches(ignoreCase, builder, prefixPosition, prefix, 0, prefixLength)) {
			builder.delete(0, prefixLength);
		}
	}

	/**
	 * Removes the given suffix from the given builder if present.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void removeSuffix(StringBuilder builder, String suffix) {
		removeSuffix(builder, suffix, false);
	}

	/**
	 * Removes the given suffix from the given builder if present, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 * @throws IllegalArgumentException if {@code suffix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void removeSuffix(StringBuilder builder, String suffix, boolean ignoreCase) {
		Contract.checkArgument(builder != null, "Builder must not be null");
		Contract.checkArgument(suffix != null, "Suffix must not be null");

		int suffixLength = suffix.length();
		int suffixPosition = builder.length() - suffixLength;
		if (regionMatches(ignoreCase, builder, suffixPosition, suffix, 0, suffixLength)) {
			builder.setLength(suffixPosition);
		}
	}

	/**
	 * Removes all whitespace characters from the given builder.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 *
	 * @since 2.1
	 *
	 * @see Character#isWhitespace(int)
	 */
	public static void removeWhitespace(StringBuilder builder) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		for (int offset = 0, length = builder.length(); offset < length; ) {
			int currentCodePoint = builder.codePointAt(offset);
			int currentCodePointLength = Character.charCount(currentCodePoint);

			if (Character.isWhitespace(currentCodePoint)) {
				builder.delete(offset, offset + currentCodePointLength);
				length -= currentCodePointLength;
			} else {
				offset += currentCodePointLength;
			}
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
	 * Checks whether the given builder starts with the given prefix, possibly ignoring case.
	 *
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code prefix} is {@code null}
	 *
	 * @since 2.1
	 */
	public static boolean startsWith(StringBuilder builder, String prefix, boolean ignoreCase) {
		Contract.checkArgument(builder != null, "Builder must not be null");
		Contract.checkArgument(prefix != null, "Prefix must not be null");

		return regionMatches(ignoreCase, builder, 0, prefix, 0, prefix.length());
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

	/**
	 * Removes all leading whitespace characters from the given builder.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void trimLeft(StringBuilder builder) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		int currentCodePoint = 0;
		while (Character.isWhitespace(currentCodePoint = builder.codePointAt(0))) {
			builder.delete(0, Character.charCount(currentCodePoint));
		}
	}

	/**
	 * Removes all trailing whitespace characters from the given builder.
	 *
	 * @throws IllegalArgumentException if {@code builder} is {@code null}
	 *
	 * @since 2.1
	 */
	public static void trimRight(StringBuilder builder) {
		Contract.checkArgument(builder != null, "Builder must not be null");

		builder.reverse();
		trimLeft(builder);
		builder.reverse();
	}


	private static boolean regionMatches(boolean ignoreCase, StringBuilder former, int formerOffset, String latter, int latterOffset, int length) {
		if (!ignoreCase) {
			return regionMatches(former, formerOffset, latter, latterOffset, length);
		}

		Contract.checkArgument(length >= 0, "Length must not be negative: {0}", length);

		if (formerOffset < 0) {
			return false;
		}
		if (latterOffset < 0) {
			return false;
		}
		if ((formerOffset + length) > former.length()) {
			return false;
		}
		if ((latterOffset + length) > latter.length()) {
			return false;
		}

		for (int i = 0; i < length; ) {
			int formerCodePoint = Character.toLowerCase(former.codePointAt(formerOffset + i));
			int latterCodePoint = Character.toLowerCase(latter.codePointAt(latterOffset + i));

			if (formerCodePoint != latterCodePoint) {
				return false;
			} else {
				i += Character.charCount(formerCodePoint);
			}
		}

		return true;
	}

	private static boolean regionMatches(StringBuilder former, int formerOffset, String latter, int latterOffset, int length) {
		Contract.checkArgument(length >= 0, "Length must not be negative: {0}", length);

		if (formerOffset < 0) {
			return false;
		}
		if (latterOffset < 0) {
			return false;
		}
		if ((formerOffset + length) > former.length()) {
			return false;
		}
		if ((latterOffset + length) > latter.length()) {
			return false;
		}

		for (int i = 0; i < length; ) {
			int formerCodePoint = former.codePointAt(formerOffset + i);
			int latterCodePoint = latter.codePointAt(latterOffset + i);

			if (formerCodePoint != latterCodePoint) {
				return false;
			} else {
				i += Character.charCount(formerCodePoint);
			}
		}

		return true;
	}

}
