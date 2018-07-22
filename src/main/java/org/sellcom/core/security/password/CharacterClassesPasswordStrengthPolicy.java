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

import org.sellcom.core.Contract;

/**
 * Password strength policy based on character classes.
 *
 * <h3>Example</h3>
 * <pre>
 * CharacterClassesPasswordStrengthPolicy policy = CharacterClassesPasswordStrengthPolicy.create()
 *     .withMinLength(8)
 *     .withMinUpperCaseLetters(2)
 *     .withMinLowerCaseLetters(2)
 *     .withMinDigits(1)
 *     .withMinSpecialChars(1);
 *
 * if (!policy.test(password)) {
 *     // Reject password
 * }
 * </pre>
 *
 * @since 1.0
 */
public class CharacterClassesPasswordStrengthPolicy implements PasswordStrengthPolicy {

	private static final int[] SPECIAL_CHARS = new int[] {
			'!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'
	};

	private int minDigits = 0;

	private int minLength = 0;

	private int minLowerCaseLetters = 0;

	private int minOtherChars = 0;

	private int minSpecialChars = 0;

	private int minUpperCaseLetters = 0;


	private CharacterClassesPasswordStrengthPolicy() {
		// Not to be instantiated directly
	}


	/**
	 * Returns an empty character classes password policy.
	 *
	 * @since 1.0
	 */
	public CharacterClassesPasswordStrengthPolicy create() {
		return new CharacterClassesPasswordStrengthPolicy();
	}

	@Override
	public boolean test(char[] password) {
		Contract.checkArgument(password != null, "Password must not be null");

		int length = password.length;
		if (length < minLength) {
			return false;
		}

		int digits = 0;
		int lowerCaseLetters = 0;
		int otherChars = 0;
		int specialChars = 0;
		int upperCaseLetters = 0;

		for (int offset = 0; offset < length; ) {
			int codePoint = Character.codePointAt(password, offset);

			try {
				if (Character.isUpperCase(codePoint)) {
					upperCaseLetters += 1;

					continue;
				}
				if (Character.isLowerCase(codePoint)) {
					lowerCaseLetters += 1;

					continue;
				}
				if (Character.isDigit(codePoint)) {
					digits += 1;

					continue;
				}
				if (Arrays.binarySearch(SPECIAL_CHARS, codePoint) != -1) {
					specialChars += 1;

					continue;
				}

				otherChars += 1;
			} finally {
				offset += Character.charCount(codePoint);
			}
		}

		return (upperCaseLetters >= minUpperCaseLetters)
				&& (lowerCaseLetters >= minLowerCaseLetters)
				&& (digits >= minDigits)
				&& (specialChars >= minSpecialChars)
				&& (otherChars >= minOtherChars);
	}

	/**
	 * Sets the minimum number of digits of this policy.
	 *
	 * @throws IllegalArgumentException if {@code minDigits} is negative
	 *
	 * @since 1.0
	 */
	public CharacterClassesPasswordStrengthPolicy withMinDigits(int minDigits) {
		Contract.checkArgument(minDigits >= 0, "Minimum number of digits must not be negative: {0}", minDigits);

		this.minDigits = minDigits;

		return this;
	}

	/**
	 * Sets the minimum length of this policy.
	 *
	 * @throws IllegalArgumentException if {@code minLength} is negative
	 *
	 * @since 1.0
	 */
	public CharacterClassesPasswordStrengthPolicy withMinLength(int minLength) {
		Contract.checkArgument(minLength >= 0, "Minimum length must not be negative: {0}", minLength);

		this.minLength = minLength;

		return this;
	}

	/**
	 * Sets the minimum number of lower-case letters of this policy.
	 *
	 * @throws IllegalArgumentException if {@code minLowerCaseLetters} is negative
	 *
	 * @since 1.0
	 */
	public CharacterClassesPasswordStrengthPolicy withMinLowerCaseLetters(int minLowerCaseLetters) {
		Contract.checkArgument(minLowerCaseLetters >= 0, "Minimum number of lower-case letters must not be negative: {0}", minLowerCaseLetters);

		this.minLowerCaseLetters = minLowerCaseLetters;

		return this;
	}

	/**
	 * Sets the minimum number of other characters of this policy.
	 *
	 * @throws IllegalArgumentException if {@code minOtherChars} is negative
	 *
	 * @since 1.0
	 */
	public CharacterClassesPasswordStrengthPolicy withMinOtherChars(int minOtherChars) {
		Contract.checkArgument(minOtherChars >= 0, "Minimum number of other characters must not be negative: {0}", minOtherChars);

		this.minOtherChars = minOtherChars;

		return this;
	}

	/**
	 * Sets the minimum number of special characters of this policy.
	 *
	 * @throws IllegalArgumentException if {@code minSpecialChars} is negative
	 *
	 * @since 1.0
	 */
	public CharacterClassesPasswordStrengthPolicy withMinSpecialChars(int minSpecialChars) {
		Contract.checkArgument(minSpecialChars >= 0, "Minimum number of special characters must not be negative: {0}", minSpecialChars);

		this.minSpecialChars = minSpecialChars;

		return this;
	}

	/**
	 * Sets the minimum number of upper-case letters of this policy.
	 *
	 * @throws IllegalArgumentException if {@code minUpperCaseLetters} is negative
	 *
	 * @since 1.0
	 */
	public CharacterClassesPasswordStrengthPolicy withMinUpperCaseLetters(int minUpperCaseLetters) {
		Contract.checkArgument(minUpperCaseLetters >= 0, "Minimum number of upper-case letters must not be negative: {0}", minUpperCaseLetters);

		this.minUpperCaseLetters = minUpperCaseLetters;

		return this;
	}

}
