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
package org.sellcom.core.io.type;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;

/**
 * Operations with content types.
 *
 * @since 1.0
 */
public class ContentTypes {

	private static final Pattern extractCharsetPattern = Pattern.compile("(?<=;charset=)(?<charset>[^;]+)");

	private static final Pattern replaceCharsetPattern = Pattern.compile("(;charset=[^;]+)");


	private ContentTypes() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the charset extracted from the given content type or {@code UTF-8} if the content type does not contain a charset.
	 *
	 * @throws IllegalArgumentException if {@code contentType} is {@code null} or empty
	 * @throws UnsupportedCharsetException if the charset is not supported
	 *
	 * @since 1.0
	 */
	public static Charset getCharset(String contentType) {
		Contract.checkArgument(!Strings.isNullOrEmpty(contentType), "Content type must not be null or empty");

		Matcher matcher = extractCharsetPattern.matcher(contentType);
		if (matcher.find()) {
			String charsetName = Strings.toUpperCase(matcher.group("charset").trim());

			return Charset.forName(charsetName);
		}

		return UTF_8; // RFC 6657 requires US-ASCII, UTF-8 is a superset
	}

	/**
	 * Sets the given charset to given content type.
	 *
	 * @throws IllegalArgumentException if {@code contentType} is {@code null} or empty
	 * @throws IllegalArgumentException if {@code charset} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String setCharset(String contentType, Charset charset) {
		Contract.checkArgument(!Strings.isNullOrEmpty(contentType), "Content type must not be null or empty");
		Contract.checkArgument(charset != null, "Charset must not be null");

		Matcher matcher = replaceCharsetPattern.matcher(contentType);

		StringBuilder builder = new StringBuilder();
		builder.append(matcher.replaceAll(""));
		builder.append(";charset=");
		builder.append(Strings.toLowerCase(charset.name()));

		return builder.toString();
	}

}
