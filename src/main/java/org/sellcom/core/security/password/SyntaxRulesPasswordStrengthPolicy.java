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

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.ToDoubleFunction;

import org.sellcom.core.Contract;

/**
 * Password strength policy based on syntax rules.
 *
 * <h3>Example</h3>
 * <pre>
 * SyntaxRulesPasswordStrengthPolicy policy = SyntaxRulesPasswordStrengthPolicy.create()
 *     .withRule(StandardSyntaxRules.length(8, 4.0))
 *     .withRule(StandardSyntaxRules.characterClasses(3, 2.0))
 *     .withRule(StandardSyntaxRules.upperCaseLetters(1.0))
 *     .withRule(StandardSyntaxRules.lowerCaseLetters(1.0))
 *     .withRule(StandardSyntaxRules.digits(4.0))
 *     .withRule(StandardSyntaxRules.moreCommonSpecialChars(4.0))
 *     .withRule(StandardSyntaxRules.lessCommonSpecialChars(8.0))
 *     .withRule(StandardSyntaxRules.midDigits(2.0))
 *     .withRule(StandardSyntaxRules.midMoreCommonSpecialChars(2.0))
 *     .withRule(StandardSyntaxRules.midLessCommonSpecialChars(2.0))
 *     .withRule(StandardSyntaxRules.lettersOnly(-1.0))
 *     .withRule(StandardSyntaxRules.digitsOnly(-1.0))
 *     .withRule(StandardSyntaxRules.consecutiveUpperCaseLetters(-2.0))
 *     .withRule(StandardSyntaxRules.consecutiveLowerCaseLetters(-2.0))
 *     .withRule(StandardSyntaxRules.consecutiveDigits(-2.0))
 *     .withRule(StandardSyntaxRules.sequentialLetters(3, -3.0))
 *     .withRule(StandardSyntaxRules.sequentialDigits(3, -3.0))
 *     .withRule(StandardSyntaxRules.sequentialSpecialChars(3, -3.0))
 *     .withMinScore(60);
 *
 * if (!policy.test(password)) {
 *     // Reject password
 * }
 * </pre>
 *
 * @since 1.0
 */
public class SyntaxRulesPasswordStrengthPolicy implements PasswordStrengthPolicy {

	private double minScore = 0.0;

	private final Set<ToDoubleFunction<char[]>> rules = new LinkedHashSet<>();


	/**
	 * Returns an empty character classes password policy.
	 *
	 * @since 1.0
	 */
	public SyntaxRulesPasswordStrengthPolicy create() {
		return new SyntaxRulesPasswordStrengthPolicy();
	}

	@Override
	public boolean test(char[] password) {
		Contract.checkArgument(password != null, "Password must not be null");

		double score = 0.0;
		for (ToDoubleFunction<char[]> rule : rules) {
			score += rule.applyAsDouble(password);
		}

		return score >= minScore;
	}

	/**
	 * Sets the minimum score of this policy.
	 *
	 * @throws IllegalArgumentException if {@code minScore} is negative
	 *
	 * @since 1.0
	 */
	public SyntaxRulesPasswordStrengthPolicy withMinScore(double minScore) {
		Contract.checkArgument(minScore >= 0, "Minimum score must not be negative: {0}", minScore);

		this.minScore = minScore;

		return this;
	}

	/**
	 * Adds the given rule to this policy.
	 *
	 * @throws IllegalArgumentException if {@code minLength} is negative
	 *
	 * @since 1.0
	 */
	public SyntaxRulesPasswordStrengthPolicy withRule(ToDoubleFunction<char[]> rule) {
		Contract.checkArgument(rule != null, "Rule must not be null");

		rules.add(rule);

		return this;
	}

}
