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
package org.sellcom.core.security.password;

import java.util.Arrays;
import java.util.function.ToDoubleFunction;

import org.sellcom.core.Contract;
import org.sellcom.core.util.MoreArrays;

/**
 * Standard syntax rules.
 *
 * @since 1.0
 */
public class StandardSyntaxRules {

	private static final int[] LESS_COMMON_SPECIAL_CHARS = new int[] {
		'"', '\'', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '[', '\\', ']', '_', '`', '{', '|', '}', '~'
	};

	private static final int[] MORE_COMMON_SPECIAL_CHARS = new int[] {
		'!', '#', '$', '%', '&', '(', ')', '*', '@', '^'
	};

	private static final int[] SPECIAL_CHARS_SEQUENCE = new int[] {
		'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+'
	};


	private StandardSyntaxRules() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns a syntax rule scoring the number of character classes in the password.
	 *
	 * @throws IllegalArgumentException if {@code minClasses} is not positive
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> characterClasses(int minClasses, double weight) {
		Contract.checkArgument(minClasses > 0, "Minimum number of classes must be positive: {0}", minClasses);
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			boolean digits = false;
			boolean lessCommonSpecialChars = false;
			boolean lowerCaseLetters = false;
			boolean moreCommonSpecialChars = false;
			boolean upperCaseLetters = false;

			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);

				try {
					if (Character.isUpperCase(codePoint)) {
						if (!upperCaseLetters) {
							upperCaseLetters = true;
						}

						continue;
					}
					if (Character.isLowerCase(codePoint)) {
						if (!lowerCaseLetters) {
							lowerCaseLetters = true;
						}

						continue;
					}
					if (Character.isDigit(codePoint)) {
						if (!digits) {
							digits = true;
						}

						continue;
					}
					if (Arrays.binarySearch(MORE_COMMON_SPECIAL_CHARS, codePoint) >= 0) {
						if (!moreCommonSpecialChars) {
							moreCommonSpecialChars = true;
						}

						continue;
					}
					if (Arrays.binarySearch(LESS_COMMON_SPECIAL_CHARS, codePoint) >= 0) {
						if (!lessCommonSpecialChars) {
							lessCommonSpecialChars = true;
						}

						continue;
					}
				} finally {
					offset += Character.charCount(codePoint);
				}
			}

			int characterClasses = 0;
			characterClasses += (digits) ? 1 : 0;
			characterClasses += (lessCommonSpecialChars) ? 1 : 0;
			characterClasses += (lowerCaseLetters) ? 1 : 0;
			characterClasses += (moreCommonSpecialChars) ? 1 : 0;
			characterClasses += (upperCaseLetters) ? 1 : 0;

			return (characterClasses >= minClasses) ? (weight * characterClasses) : Double.MIN_VALUE;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of consecutive digits in the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> consecutiveDigits(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			if (MoreArrays.isNullOrEmpty(password)) {
				return 0.0;
			}

			int currentLength = 0;
			int totalLength = 0;
			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);

				if (Character.isDigit(codePoint)) {
					currentLength += 1;
				} else {
					totalLength += currentLength;
					currentLength = 0;
				}

				offset += Character.charCount(codePoint);
			}
			totalLength += currentLength;

			return weight * totalLength;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of consecutive lower-case letters in the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> consecutiveLowerCaseLetters(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			if (MoreArrays.isNullOrEmpty(password)) {
				return 0.0;
			}

			int currentLength = 0;
			int totalLength = 0;
			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);

				if (Character.isLowerCase(codePoint)) {
					currentLength += 1;
				} else {
					totalLength += currentLength;
					currentLength = 0;
				}

				offset += Character.charCount(codePoint);
			}
			totalLength += currentLength;

			return weight * totalLength;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of consecutive upper-case letters in the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> consecutiveUpperCaseLetters(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			if (MoreArrays.isNullOrEmpty(password)) {
				return 0.0;
			}

			int currentLength = 0;
			int totalLength = 0;
			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);

				if (Character.isUpperCase(codePoint)) {
					currentLength += 1;
				} else {
					totalLength += currentLength;
					currentLength = 0;
				}

				offset += Character.charCount(codePoint);
			}
			totalLength += currentLength;

			return weight * totalLength;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of digits in the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> digits(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			double score = 0.0;

			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);
				if (Character.isDigit(codePoint)) {
					score += weight;
				}

				offset += Character.charCount(codePoint);
			}

			return score;
		};
	}

	/**
	 * Returns a syntax rule scoring digit-only passwords.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> digitsOnly(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);

				try {
					if (Character.isDigit(codePoint)) {
						continue;
					}

					return 0.0;
				} finally {
					offset += Character.charCount(codePoint);
				}
			}

			return weight * password.length;
		};
	}

	/**
	 * Returns a syntax rule scoring the length of the password.
	 *
	 * @throws IllegalArgumentException if {@code minLength} is not positive
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> length(int minLength, double weight) {
		Contract.checkArgument(minLength > 0, "Minimum length must be positive: {0}", minLength);
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			return (password.length >= minLength) ? (weight * password.length) : Double.MIN_VALUE;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of less common special characters in the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> lessCommonSpecialChars(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			double score = 0.0;

			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);
				if (Arrays.binarySearch(LESS_COMMON_SPECIAL_CHARS, codePoint) >= 0) {
					score += weight;
				}

				offset += Character.charCount(codePoint);
			}

			return score;
		};
	}

	/**
	 * Returns a syntax rule scoring letter-only passwords.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> lettersOnly(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);

				try {
					if (Character.isLetter(codePoint)) {
						continue;
					}

					return 0.0;
				} finally {
					offset += Character.charCount(codePoint);
				}
			}

			return weight * password.length;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of lower-case letters in the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> lowerCaseLetters(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			double score = 0.0;

			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);
				if (Character.isLowerCase(codePoint)) {
					score += weight;
				}

				offset += Character.charCount(codePoint);
			}

			return score;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of digits in the middle of the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> midDigits(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			double score = 0.0;

			for (int offset = 1; offset < (password.length -1); ) {
				int codePoint = Character.codePointAt(password, offset);
				if (Character.isDigit(codePoint)) {
					score += weight;
				}

				offset += Character.charCount(codePoint);
			}

			return score;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of less common special characters in the middle of the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> midLessCommonSpecialChars(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			double score = 0.0;

			for (int offset = 1; offset < (password.length - 1); ) {
				int codePoint = Character.codePointAt(password, offset);
				if (Arrays.binarySearch(LESS_COMMON_SPECIAL_CHARS, codePoint) >= 0) {
					score += weight;
				}

				offset += Character.charCount(codePoint);
			}

			return score;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of more common special characters in the middle of the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> midMoreCommonSpecialChars(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			double score = 0.0;

			for (int offset = 1; offset < (password.length - 1); ) {
				int codePoint = Character.codePointAt(password, offset);
				if (Arrays.binarySearch(MORE_COMMON_SPECIAL_CHARS, codePoint) >= 0) {
					score += weight;
				}

				offset += Character.charCount(codePoint);
			}

			return score;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of more common special characters in the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> moreCommonSpecialChars(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			double score = 0.0;

			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);
				if (Arrays.binarySearch(MORE_COMMON_SPECIAL_CHARS, codePoint) >= 0) {
					score += weight;
				}

				offset += Character.charCount(codePoint);
			}

			return score;
		};
	}

	/**
	 * Returns a syntax rule scoring the sequences of digits in the password.
	 *
	 * @throws IllegalArgumentException if {@code minSequenceLength} is less than {@code 2}
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> sequentialDigits(int minSequenceLength, double weight) {
		Contract.checkArgument(minSequenceLength >= 2, "Minimum sequence length must not be less than 2: {0}", minSequenceLength);
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			if (password.length < 2) {
				return 0.0;
			}

			int totalSequenceLength = 0;

			// Increasing sequences
			int currentSequenceLength = 1;
			for (int offset1 = 0, offset2 = Character.charCount(0); offset2 < password.length; ) {
				int codePoint1 = Character.codePointAt(password, offset1);
				int codePoint2 = Character.codePointAt(password, offset2);

				if (Character.isDigit(codePoint1) && Character.isDigit(codePoint2) && ((Character.digit(codePoint1, 10) + 1) == Character.digit(codePoint2, 10))) {
					currentSequenceLength += 1;
				} else {
					if (currentSequenceLength >= minSequenceLength) {
						totalSequenceLength += currentSequenceLength;
					}
					currentSequenceLength = 1;
				}

				offset1 += Character.charCount(codePoint1);
				offset2 += Character.charCount(codePoint2);
			}
			if (currentSequenceLength >= minSequenceLength) {
				totalSequenceLength += currentSequenceLength;
			}

			// Decreasing sequences
			currentSequenceLength = 1;
			for (int offset1 = 0, offset2 = Character.charCount(0); offset2 < password.length; ) {
				int codePoint1 = Character.codePointAt(password, offset1);
				int codePoint2 = Character.codePointAt(password, offset2);

				if (Character.isDigit(codePoint1) && Character.isDigit(codePoint2) && ((Character.digit(codePoint1, 10) - 1) == Character.digit(codePoint2, 10))) {
					currentSequenceLength += 1;
				} else {
					if (currentSequenceLength >= minSequenceLength) {
						totalSequenceLength += currentSequenceLength;
					}
					currentSequenceLength = 1;
				}

				offset1 += Character.charCount(codePoint1);
				offset2 += Character.charCount(codePoint2);
			}
			if (currentSequenceLength >= minSequenceLength) {
				totalSequenceLength += currentSequenceLength;
			}

			return weight * totalSequenceLength;
		};
	}

	/**
	 * Returns a syntax rule scoring the sequences of letters in the password.
	 *
	 * @throws IllegalArgumentException if {@code minSequenceLength} is less than {@code 2}
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> sequentialLetters(int minSequenceLength, double weight) {
		Contract.checkArgument(minSequenceLength >= 2, "Minimum sequence length must not be less than 2: {0}", minSequenceLength);
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			if (password.length < 2) {
				return 0.0;
			}

			int totalSequenceLength = 0;

			// Increasing sequences
			int currentSequenceLength = 1;
			for (int offset1 = 0, offset2 = Character.charCount(0); offset2 < password.length; ) {
				int codePoint1 = Character.codePointAt(password, offset1);
				int codePoint2 = Character.codePointAt(password, offset2);

				if (Character.isLetter(codePoint1) && Character.isLetter(codePoint2) && ((Character.toLowerCase(codePoint1) + 1) == Character.toLowerCase(codePoint2))) {
					currentSequenceLength += 1;
				} else {
					if (currentSequenceLength >= minSequenceLength) {
						totalSequenceLength += currentSequenceLength;
					}
					currentSequenceLength = 1;
				}

				offset1 += Character.charCount(codePoint1);
				offset2 += Character.charCount(codePoint2);
			}
			if (currentSequenceLength >= minSequenceLength) {
				totalSequenceLength += currentSequenceLength;
			}

			// Decreasing sequences
			currentSequenceLength = 1;
			for (int offset1 = 0, offset2 = Character.charCount(0); offset2 < password.length; ) {
				int codePoint1 = Character.codePointAt(password, offset1);
				int codePoint2 = Character.codePointAt(password, offset2);

				if (Character.isLetter(codePoint1) && Character.isLetter(codePoint2) && ((Character.toLowerCase(codePoint1) - 1) == Character.toLowerCase(codePoint2))) {
					currentSequenceLength += 1;
				} else {
					if (currentSequenceLength >= minSequenceLength) {
						totalSequenceLength += currentSequenceLength;
					}
					currentSequenceLength = 1;
				}

				offset1 += Character.charCount(codePoint1);
				offset2 += Character.charCount(codePoint2);
			}
			if (currentSequenceLength >= minSequenceLength) {
				totalSequenceLength += currentSequenceLength;
			}

			return weight * totalSequenceLength;
		};
	}

	/**
	 * Returns a syntax rule scoring the sequences of special characters in the password.
	 *
	 * @throws IllegalArgumentException if {@code minSequenceLength} is less than {@code 2}
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> sequentialSpecialChars(int minSequenceLength, double weight) {
		Contract.checkArgument(minSequenceLength >= 2, "Minimum sequence length must not be less than 2: {0}", minSequenceLength);
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			if (password.length < 2) {
				return 0.0;
			}

			int totalSequenceLength = 0;

			// Increasing sequences
			int currentSequenceLength = 1;
			for (int offset1 = 0, offset2 = Character.charCount(0); offset2 < password.length; ) {
				int codePoint1 = Character.codePointAt(password, offset1);
				int codePoint2 = Character.codePointAt(password, offset2);

				int codePoint1Index = MoreArrays.indexOf(SPECIAL_CHARS_SEQUENCE, codePoint1);
				int codePoint2Index = MoreArrays.indexOf(SPECIAL_CHARS_SEQUENCE, codePoint2);
				if ((codePoint1Index >= 0) && (codePoint2Index >= 0) && ((codePoint1Index + 1) == codePoint2Index)) {
					currentSequenceLength += 1;
				} else {
					if (currentSequenceLength >= minSequenceLength) {
						totalSequenceLength += currentSequenceLength;
					}
					currentSequenceLength = 1;
				}

				offset1 += Character.charCount(codePoint1);
				offset2 += Character.charCount(codePoint2);
			}
			if (currentSequenceLength >= minSequenceLength) {
				totalSequenceLength += currentSequenceLength;
			}

			// Decreasing sequences
			currentSequenceLength = 1;
			for (int offset1 = 0, offset2 = Character.charCount(0); offset2 < password.length; ) {
				int codePoint1 = Character.codePointAt(password, offset1);
				int codePoint2 = Character.codePointAt(password, offset2);

				int codePoint1Index = MoreArrays.indexOf(SPECIAL_CHARS_SEQUENCE, codePoint1);
				int codePoint2Index = MoreArrays.indexOf(SPECIAL_CHARS_SEQUENCE, codePoint2);
				if ((codePoint1Index >= 0) && (codePoint2Index >= 0) && ((codePoint1Index - 1) == codePoint2Index)) {
					currentSequenceLength += 1;
				} else {
					if (currentSequenceLength >= minSequenceLength) {
						totalSequenceLength += currentSequenceLength;
					}
					currentSequenceLength = 1;
				}

				offset1 += Character.charCount(codePoint1);
				offset2 += Character.charCount(codePoint2);
			}
			if (currentSequenceLength >= minSequenceLength) {
				totalSequenceLength += currentSequenceLength;
			}

			return weight * totalSequenceLength;
		};
	}

	/**
	 * Returns a syntax rule scoring the number of upper-case letters in the password.
	 *
	 * @throws IllegalArgumentException if {@code weight} is not a finite value
	 *
	 * @since 1.0
	 */
	public static ToDoubleFunction<char[]> upperCaseLetters(double weight) {
		Contract.checkArgument(Double.isFinite(weight), "Weight must be a finite value: {0}", weight);

		return password -> {
			double score = 0.0;

			for (int offset = 0; offset < password.length; ) {
				int codePoint = Character.codePointAt(password, offset);
				if (Character.isUpperCase(codePoint)) {
					score += weight;
				}

				offset += Character.charCount(codePoint);
			}

			return score;
		};
	}

}
