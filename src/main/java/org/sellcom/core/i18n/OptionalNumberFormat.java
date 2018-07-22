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

import java.math.RoundingMode;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;

/**
 * Replacement of {@link NumberFormat} capable of handling {@code null}s and empty strings.
 *
 * @since 1.0
 */
public class OptionalNumberFormat extends Format {

	private static final long serialVersionUID = 8713364721141508254L;

	private final NumberFormat format;


	private OptionalNumberFormat(NumberFormat format) {
		this.format = format;
	}


	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (other instanceof OptionalNumberFormat) {
			OptionalNumberFormat otherCast = (OptionalNumberFormat) other;

			return Objects.equals(format, otherCast.format);
		}

		return false;
	}

	/**
	 * Formats a {@code double} number.
	 *
	 * @since 1.0
	 */
	public String format(double number) {
		return format.format(number);
	}

	/**
	 * Formats a {@code long} number.
	 *
	 * @since 1.0
	 */
	public String format(long number) {
		return format.format(number);
	}

	@Override
	public StringBuffer format(Object source, StringBuffer buffer, FieldPosition position) {
		return (source == null) ? buffer : format.format(source, buffer, position);
	}

	/**
	 * Returns all locales supported by the {@code get*Instance()} methods.
	 *
	 * @since 1.0
	 */
	public static Locale[] getAvailableLocales() {
		return NumberFormat.getAvailableLocales();
	}

	/**
	 * Returns the currency used by this format when formatting currency values.
	 *
	 * @since 1.0
	 */
	public Currency getCurrency() {
		return format.getCurrency();
	}

	/**
	 * Returns a currency format for the current default locale.
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getCurrencyInstance() {
		return new OptionalNumberFormat(NumberFormat.getCurrencyInstance());
	}

	/**
	 * Returns a currency format for the given locale.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getCurrencyInstance(Locale locale) {
		Contract.checkArgument(locale != null, "Locale must not be null");

		return new OptionalNumberFormat(NumberFormat.getCurrencyInstance(locale));
	}

	/**
	 * Returns a general-purpose number format for the current default locale.
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getInstance() {
		return new OptionalNumberFormat(NumberFormat.getInstance());
	}

	/**
	 * Returns a general-purpose number format for the given locale.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getInstance(Locale locale) {
		Contract.checkArgument(locale != null, "Locale must not be null");

		return new OptionalNumberFormat(NumberFormat.getInstance(locale));
	}

	/**
	 * Returns an integer number format for the current default locale.
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getIntegerInstance() {
		return new OptionalNumberFormat(NumberFormat.getIntegerInstance());
	}

	/**
	 * Returns an integer number format for the given locale.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getIntegerInstance(Locale locale) {
		Contract.checkArgument(locale != null, "Locale must not be null");

		return new OptionalNumberFormat(NumberFormat.getIntegerInstance(locale));
	}

	/**
	 * Returns the maximum number of digits allowed in the fraction part of a number.
	 *
	 * @since 1.0
	 */
	public int getMaximumFractionDigits() {
		return format.getMaximumFractionDigits();
	}

	/**
	 * Returns the maximum number of digits allowed in the integer part of a number.
	 *
	 * @since 1.0
	 */
	public int getMaximumIntegerDigits() {
		return format.getMaximumFractionDigits();
	}

	/**
	 * Returns the minimum number of digits allowed in the fraction part of a number.
	 *
	 * @since 1.0
	 */
	public int getMinimumFractionDigits() {
		return format.getMinimumFractionDigits();
	}

	/**
	 * Returns the minimum number of digits allowed in the integer part of a number.
	 *
	 * @since 1.0
	 */
	public int getMinimumIntegerDigits() {
		return format.getMinimumIntegerDigits();
	}

	/**
	 * Returns a general-purpose number format for the current default locale.
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getNumberInstance() {
		return new OptionalNumberFormat(NumberFormat.getNumberInstance());
	}

	/**
	 * Returns a general-purpose number format for the given locale.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getNumberInstance(Locale locale) {
		Contract.checkArgument(locale != null, "Locale must not be null");

		return new OptionalNumberFormat(NumberFormat.getNumberInstance(locale));
	}

	/**
	 * Returns a percentage format for the current default locale.
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getPercentInstance() {
		return new OptionalNumberFormat(NumberFormat.getPercentInstance());
	}

	/**
	 * Returns a percentage format for the given locale.
	 *
	 * @throws IllegalArgumentException if {@code locale} is {@code null}
	 *
	 * @since 1.0
	 */
	public static OptionalNumberFormat getPercentInstance(Locale locale) {
		Contract.checkArgument(locale != null, "Locale must not be null");

		return new OptionalNumberFormat(NumberFormat.getPercentInstance(locale));
	}

	/**
	 * Returns the rounding mode used in this format.
	 *
	 * @since 1.0
	 */
	public RoundingMode getRoundingMode() {
		return format.getRoundingMode();
	}

	/**
	 * Checks whether grouping is used in this format.
	 *
	 * @since 1.0
	 */
	public boolean isGroupingUsed() {
		return format.isGroupingUsed();
	}

	/**
	 * Checks whether numbers shall be parsed as integers only.
	 *
	 * @since 1.0
	 */
	public boolean isParseIntegerOnly() {
		return format.isParseIntegerOnly();
	}

	@Override
	public Object parseObject(String source) throws ParseException {
		return Strings.isNullOrEmpty(source) ? null : format.parseObject(source);
	}

	@Override
	public Object parseObject(String source, ParsePosition position) {
		return Strings.isNullOrEmpty(source) ? null : format.parseObject(source, position);
	}

	@Override
	public int hashCode() {
		return format.hashCode();
	}

	/**
	 * Sets the currency used by this format when formatting currency values.
	 *
	 * @since 1.0
	 */
	public void setCurrency(Currency currency) {
		format.setCurrency(currency);
	}

	/**
	 * Sets whether grouping is used in this format.
	 *
	 * @since 1.0
	 */
	public void setGroupingUsed(boolean value) {
		format.setGroupingUsed(value);
	}

	/**
	 * Sets the maximum number of digits allowed in the fraction part of a number.
	 *
	 * @since 1.0
	 */
	public void setMaximumFractionDigits(int value) {
		format.setMaximumFractionDigits(value);
	}

	/**
	 * Sets the maximum number of digits allowed in the integer part of a number.
	 *
	 * @since 1.0
	 */
	public void setMaximumIntegerDigits(int value) {
		format.setMaximumIntegerDigits(value);
	}

	/**
	 * Sets the minimum number of digits allowed in the fraction part of a number.
	 *
	 * @since 1.0
	 */
	public void setMinimumFractionDigits(int value) {
		format.setMinimumFractionDigits(value);
	}

	/**
	 * Sets the minimum number of digits allowed in the integer part of a number.
	 *
	 * @since 1.0
	 */
	public void setMinimumIntegerDigits(int value) {
		format.setMinimumIntegerDigits(value);
	}

	/**
	 * Sets whether numbers shall be parsed as integers only.
	 *
	 * @since 1.0
	 */
	public void setParseIntegerOnly(boolean value) {
		format.setParseIntegerOnly(value);
	}

	/**
	 * Sets the rounding mode used in this format.
	 *
	 * @since 1.0
	 */
	public void setRoundingMode(RoundingMode roundingMode) {
		format.setRoundingMode(roundingMode);
	}

}
