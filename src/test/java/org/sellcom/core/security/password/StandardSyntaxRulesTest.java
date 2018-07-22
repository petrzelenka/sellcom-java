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

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.security.password.StandardSyntaxRules.characterClasses;
import static org.sellcom.core.security.password.StandardSyntaxRules.consecutiveDigits;
import static org.sellcom.core.security.password.StandardSyntaxRules.consecutiveLowerCaseLetters;
import static org.sellcom.core.security.password.StandardSyntaxRules.consecutiveUpperCaseLetters;
import static org.sellcom.core.security.password.StandardSyntaxRules.digits;
import static org.sellcom.core.security.password.StandardSyntaxRules.digitsOnly;
import static org.sellcom.core.security.password.StandardSyntaxRules.lessCommonSpecialChars;
import static org.sellcom.core.security.password.StandardSyntaxRules.lettersOnly;
import static org.sellcom.core.security.password.StandardSyntaxRules.lowerCaseLetters;
import static org.sellcom.core.security.password.StandardSyntaxRules.midDigits;
import static org.sellcom.core.security.password.StandardSyntaxRules.midLessCommonSpecialChars;
import static org.sellcom.core.security.password.StandardSyntaxRules.midMoreCommonSpecialChars;
import static org.sellcom.core.security.password.StandardSyntaxRules.moreCommonSpecialChars;
import static org.sellcom.core.security.password.StandardSyntaxRules.sequentialDigits;
import static org.sellcom.core.security.password.StandardSyntaxRules.sequentialLetters;
import static org.sellcom.core.security.password.StandardSyntaxRules.sequentialSpecialChars;
import static org.sellcom.core.security.password.StandardSyntaxRules.upperCaseLetters;

import java.util.function.ToDoubleFunction;

import org.junit.Test;

public class StandardSyntaxRulesTest {

	@Test
	public void testCharacterClasses() {
		ToDoubleFunction<char[]> rule = characterClasses(3, 1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("ABCD".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("ABCDabcd".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("ABCDabcd1234".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("ABCDabcd1234!#$%".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("ABCDabcd1234!#$%+,-.".toCharArray()), is(equalTo(5.0)));
	}

	@Test
	public void testConsecutiveDigits() {
		ToDoubleFunction<char[]> rule = consecutiveDigits(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("1".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("12".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("124".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("1234".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("12345".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("123456".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("1234567".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("12345678".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("123456789".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("12 89".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("123 789".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("1234 6789".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("9".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("98".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("987".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("9876".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("98765".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("987654".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("9876543".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("98765432".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("987654321".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("98 21".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("987 321".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("9876 4321".toCharArray()), is(equalTo(8.0)));
	}

	@Test
	public void testConsecutiveLowerCaseLetters() {
		ToDoubleFunction<char[]> rule = consecutiveLowerCaseLetters(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("a".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("ab".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("abc".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("abcd".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("abcde".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("abcdef".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("abcdefg".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("abcdefgh".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("abcdefghi".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("ab hi".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("abc ghi".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("abcd fghi".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("z".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("zy".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("zyx".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("zyxw".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("zyxwv".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("zyxwvu".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("zyxwvut".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("zyxwvuts".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("zyxwvutsr".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("zy sr".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("zyx tsr".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("zyxw utsr".toCharArray()), is(equalTo(8.0)));
	}

	@Test
	public void testConsecutiveUpperCaseLetters() {
		ToDoubleFunction<char[]> rule = consecutiveUpperCaseLetters(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("A".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("AB".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("ABC".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("ABCD".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("ABCDE".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("ABCDEF".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("ABCDEFG".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("ABCDEFGH".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("ABCDEFGHI".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("AB HI".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("ABC GHI".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("ABCD FGHI".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("Z".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("ZY".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("ZYX".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("ZYXW".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("ZYXWV".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("ZYXWVU".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("ZYXWVUT".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("ZYXWVUTS".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("ZYXWVUTSR".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("ZY SR".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("ZYX TSR".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("ZYXW UTSR".toCharArray()), is(equalTo(8.0)));
	}

	@Test
	public void testDigits() {
		ToDoubleFunction<char[]> rule = digits(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("1".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("12".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("123".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("1234".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("12345".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("123456".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("1234567".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("12345678".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("123456789".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("12 89".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("123 789".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("1234 6789".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("9".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("98".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("987".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("9876".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("98765".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("987654".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("9876543".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("98765432".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("987654321".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("98 21".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("987 321".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("9876 4321".toCharArray()), is(equalTo(8.0)));
	}

	@Test
	public void testDigitsOnly() {
		ToDoubleFunction<char[]> rule = digitsOnly(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("1".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("12".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("123".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("1234".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("12345".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("123456".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("1234567".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("12345678".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("123456789".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("12 89".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("123 789".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("1234 6789".toCharArray()), is(equalTo(0.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("9".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("98".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("987".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("9876".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("98765".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("987654".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("9876543".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("98765432".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("987654321".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("98 21".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("987 321".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("9876 4321".toCharArray()), is(equalTo(0.0)));
	}

	@Test
	public void testLength() {
		ToDoubleFunction<char[]> rule = StandardSyntaxRules.length(8, 1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("a".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("aB".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("aBc".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("aBcD".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("aBcDe".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("aBcDeF".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("aBcDeFg".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("aBcDeFgH".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("aBcDeFgHi".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("z".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("zY".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("zYx".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("zYxW".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("zYxWv".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("zYxWvU".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("zYxWvUt".toCharArray()), is(equalTo(Double.MIN_VALUE)));
		assertThat(rule.applyAsDouble("zYxWvUtS".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("zYxWvUtSr".toCharArray()), is(equalTo(9.0)));
	}

	@Test
	public void testLessCommonSpecialChars() {
		ToDoubleFunction<char[]> rule = lessCommonSpecialChars(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("+".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("+,".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("+,-".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("+,-.".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("+,-./".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("+,-./:".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("+,-./:;".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("+,-./:;=".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("+,-./:;=?".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("+, =?".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("+,- ;=?".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("+,-. :;=?".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("~".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("~|".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("~|`".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("~|`_".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("~|`_?".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("~|`_?=".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("~|`_?=;".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("~|`_?=;:".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("~|`_?=;:/".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("~| :/".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("~|` ;:/".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("~|`_ =;:/".toCharArray()), is(equalTo(8.0)));
	}

	@Test
	public void testLettersOnly() {
		ToDoubleFunction<char[]> rule = lettersOnly(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("a".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("aB".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("aBc".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("aBcD".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("aBcDe".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("aBcDeF".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("aBcDeFg".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("aBcDeFgH".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("aBcDeFgHi".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("aB Hi".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("aBc gHi".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("aBcD FgHi".toCharArray()), is(equalTo(0.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("z".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("zY".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("zYx".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("zYxW".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("zYxWv".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("zYxWvU".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("zYxWvUt".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("zYxWvUtS".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("zYxWvUtSr".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("zY Sr".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("zYx tSr".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("zYxW UtSr".toCharArray()), is(equalTo(0.0)));
	}

	@Test
	public void testLowerCaseLetters() {
		ToDoubleFunction<char[]> rule = lowerCaseLetters(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("a".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("ab".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("abc".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("abcd".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("abcde".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("abcdef".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("abcdefg".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("abcdefgh".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("abcdefghi".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("ab hi".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("abc ghi".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("abcd fghi".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("z".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("zy".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("zyx".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("zyxw".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("zyxwv".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("zyxwvu".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("zyxwvut".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("zyxwvuts".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("zyxwvutsr".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("zy sr".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("zyx tsr".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("zyxw utsr".toCharArray()), is(equalTo(8.0)));
	}

	@Test
	public void testMidDigits() {
		ToDoubleFunction<char[]> rule = midDigits(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("1".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("12".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("123".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("1234".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("12345".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("123456".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("1234567".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("12345678".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("123456789".toCharArray()), is(equalTo(7.0)));

		assertThat(rule.applyAsDouble("12 89".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("123 789".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("1234 6789".toCharArray()), is(equalTo(6.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("9".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("98".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("987".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("9876".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("98765".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("987654".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("9876543".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("98765432".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("987654321".toCharArray()), is(equalTo(7.0)));

		assertThat(rule.applyAsDouble("98 21".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("987 321".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("9876 4321".toCharArray()), is(equalTo(6.0)));
	}

	@Test
	public void testMidLessCommonSpecialChars() {
		ToDoubleFunction<char[]> rule = midLessCommonSpecialChars(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("+".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("+,".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("+,-".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("+,-.".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("+,-./".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("+,-./:".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("+,-./:;".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("+,-./:;=".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("+,-./:;=?".toCharArray()), is(equalTo(7.0)));

		assertThat(rule.applyAsDouble("+, =?".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("+,- ;=?".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("+,-. :;=?".toCharArray()), is(equalTo(6.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("~".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("~|".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("~|`".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("~|`_".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("~|`_?".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("~|`_?=".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("~|`_?=;".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("~|`_?=;:".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("~|`_?=;:/".toCharArray()), is(equalTo(7.0)));

		assertThat(rule.applyAsDouble("~| :/".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("~|` ;:/".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("~|`_ =;:/".toCharArray()), is(equalTo(6.0)));
	}

	@Test
	public void testMidMoreCommonSpecialChars() {
		ToDoubleFunction<char[]> rule = midMoreCommonSpecialChars(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("!".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("!#".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("!#$".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("!#$%".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("!#$%&".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("!#$%&(".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("!#$%&()".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("!#$%&()*".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("!#$%&()*@".toCharArray()), is(equalTo(7.0)));

		assertThat(rule.applyAsDouble("!# *@".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("!#$ )*@".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("!#$% ()*@".toCharArray()), is(equalTo(6.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("^".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("^@".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("^@*".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("^@*(".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("^@*()".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("^@*()&".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("^@*()&%".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("^@*()&%$".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("^@*()&%$#".toCharArray()), is(equalTo(7.0)));

		assertThat(rule.applyAsDouble("^@ $#".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("^@* %$#".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("^@*( &%$#".toCharArray()), is(equalTo(6.0)));
	}

	@Test
	public void testMoreCommonSpecialChars() {
		ToDoubleFunction<char[]> rule = moreCommonSpecialChars(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("!".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("!#".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("!#$".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("!#$%".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("!#$%&".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("!#$%&(".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("!#$%&()".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("!#$%&()*".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("!#$%&()*@".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("!# *@".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("!#$ )*@".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("!#$% ()*@".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("^".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("^@".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("^@*".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("^@*(".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("^@*()".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("^@*()&".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("^@*()&%".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("^@*()&%$".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("^@*()&%$#".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("^@ $#".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("^@* %$#".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("^@*( &%$#".toCharArray()), is(equalTo(8.0)));
	}

	@Test
	public void testSequentialDigits() {
		ToDoubleFunction<char[]> rule = sequentialDigits(3, 1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("1".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("12".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("123".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("1234".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("12345".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("123456".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("1234567".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("12345678".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("123456789".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("12 89".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("123 789".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("1234 6789".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("9".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("98".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("987".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("9876".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("98765".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("987654".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("9876543".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("98765432".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("987654321".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("98 21".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("987 321".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("9876 4321".toCharArray()), is(equalTo(8.0)));
	}

	@Test
	public void testSequentialLetters() {
		ToDoubleFunction<char[]> rule = sequentialLetters(3, 1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("a".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("aB".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("aBc".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("aBcD".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("aBcDe".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("aBcDeF".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("aBcDeFg".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("aBcDeFgH".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("aBcDeFgHi".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("aB Hi".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("aBc gHi".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("aBcD FgHi".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("z".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("zY".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("zYx".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("zYxW".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("zYxWv".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("zYxWvU".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("zYxWvUt".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("zYxWvUtS".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("zYxWvUtSr".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("zY Sr".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("zYx tSr".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("zYxW UtSr".toCharArray()), is(equalTo(8.0)));
	}

	@Test
	public void testSequentialSpecialChars() {
		ToDoubleFunction<char[]> rule = sequentialSpecialChars(3, 1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("~".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("~!".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("~!@".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("~!@#".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("~!@#$".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("~!@#$%".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("~!@#$%^".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("~!@#$%^&".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("~!@#$%^&*".toCharArray()), is(equalTo(9.0)));
		assertThat(rule.applyAsDouble("~!@#$%^&*(".toCharArray()), is(equalTo(10.0)));
		assertThat(rule.applyAsDouble("~!@#$%^&*()".toCharArray()), is(equalTo(11.0)));
		assertThat(rule.applyAsDouble("~!@#$%^&*()_".toCharArray()), is(equalTo(12.0)));
		assertThat(rule.applyAsDouble("~!@#$%^&*()_+".toCharArray()), is(equalTo(13.0)));

		assertThat(rule.applyAsDouble("~! _+".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("~!@ )_+".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("~!@# ()_+".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("~!@#$ *()_+".toCharArray()), is(equalTo(10.0)));
		assertThat(rule.applyAsDouble("~!@#$% &*()_+".toCharArray()), is(equalTo(12.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("+".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("+_".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("+_)".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("+_)(".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("+_)(*".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("+_)(*&".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("+_)(*&^".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("+_)(*&^%".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("+_)(*&^%$".toCharArray()), is(equalTo(9.0)));
		assertThat(rule.applyAsDouble("+_)(*&^%$#".toCharArray()), is(equalTo(10.0)));
		assertThat(rule.applyAsDouble("+_)(*&^%$#@".toCharArray()), is(equalTo(11.0)));
		assertThat(rule.applyAsDouble("+_)(*&^%$#@!".toCharArray()), is(equalTo(12.0)));
		assertThat(rule.applyAsDouble("+_)(*&^%$#@!~".toCharArray()), is(equalTo(13.0)));

		assertThat(rule.applyAsDouble("+_ !~".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("+_) @!~".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("+_)( #@!~".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("+_)(* $#@!~".toCharArray()), is(equalTo(10.0)));
		assertThat(rule.applyAsDouble("+_)(*& %$#@!~".toCharArray()), is(equalTo(12.0)));
	}

	@Test
	public void testUpperCaseLetters() {
		ToDoubleFunction<char[]> rule = upperCaseLetters(1.0);

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("A".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("AB".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("ABC".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("ABCD".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("ABCDE".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("ABCDEF".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("ABCDEFG".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("ABCDEFGH".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("ABCDEFGHI".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("AB HI".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("ABC GHI".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("ABCD FGHI".toCharArray()), is(equalTo(8.0)));

		assertThat(rule.applyAsDouble("".toCharArray()), is(equalTo(0.0)));
		assertThat(rule.applyAsDouble("Z".toCharArray()), is(equalTo(1.0)));
		assertThat(rule.applyAsDouble("ZY".toCharArray()), is(equalTo(2.0)));
		assertThat(rule.applyAsDouble("ZYX".toCharArray()), is(equalTo(3.0)));
		assertThat(rule.applyAsDouble("ZYXW".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("ZYXWV".toCharArray()), is(equalTo(5.0)));
		assertThat(rule.applyAsDouble("ZYXWVU".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("ZYXWVUT".toCharArray()), is(equalTo(7.0)));
		assertThat(rule.applyAsDouble("ZYXWVUTS".toCharArray()), is(equalTo(8.0)));
		assertThat(rule.applyAsDouble("ZYXWVUTSR".toCharArray()), is(equalTo(9.0)));

		assertThat(rule.applyAsDouble("ZY SR".toCharArray()), is(equalTo(4.0)));
		assertThat(rule.applyAsDouble("ZYX TSR".toCharArray()), is(equalTo(6.0)));
		assertThat(rule.applyAsDouble("ZYXW UTSR".toCharArray()), is(equalTo(8.0)));
	}

}
