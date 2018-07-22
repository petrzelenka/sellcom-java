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
package org.sellcom.core.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;

/**
 * Operations with IP addresses.
 *
 * @since 1.0
 */
public class InetAddresses {

	private static final Pattern INET_4_ADDRESS_PATTERN;

	private static final Pattern INET_6_ADDRESS_PATTERN;

	static {
		String ipV4Segment = "(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])";
		String ipV4Address = "(" + ipV4Segment + "\\.){3,3}" + ipV4Segment;
		String ipV6Segment = "[0-9a-fA-F]{1,4}";
		String ipV6Address = "("
				+ "(" + ipV6Segment + ":){7,7}" + ipV6Segment + "|"
				+ "(" + ipV6Segment + ":){1,7}:|"
				+ "(" + ipV6Segment + ":){1,6}:" + ipV6Segment + "|"
				+ "(" + ipV6Segment + ":){1,5}(:" + ipV6Segment + "){1,2}|"
				+ "(" + ipV6Segment + ":){1,4}(:" + ipV6Segment + "){1,3}|"
				+ "(" + ipV6Segment + ":){1,3}(:" + ipV6Segment + "){1,4}|"
				+ "(" + ipV6Segment + ":){1,2}(:" + ipV6Segment + "){1,5}|"
				+ ipV6Segment + ":((:" + ipV6Segment + "){1,6})|"
				+ ":((:" + ipV6Segment + "){1,7}|:)|"
				+ "[fF][eE]80:(:" + ipV6Segment + "){0,4}%[0-9a-zA-Z]{1,}|"
				+ "::([fF]{4,4}(:0{1,4}){0,1}:){0,1}" + ipV4Address + "|"
				+ "(" + ipV6Segment + ":){1,4}:" + ipV4Address
				+ ")";

		INET_4_ADDRESS_PATTERN = Pattern.compile(ipV4Address);
		INET_6_ADDRESS_PATTERN = Pattern.compile(ipV6Address);
	}


	private InetAddresses() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks whether the given string represents an IPv4 address.
	 *
	 * @throws IllegalArgumentException if {@code input} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static boolean isInet4Address(String input) {
		Contract.checkArgument(!Strings.isNullOrEmpty(input), "Input must not be null or empty");

		Matcher matcher = INET_4_ADDRESS_PATTERN.matcher(input);

		return matcher.matches();
	}

	/**
	 * Checks whether the given string represents an IPv6 address.
	 *
	 * @throws IllegalArgumentException if {@code input} is {@code null} or empty
	 *
	 * @since 1.0
	 */
	public static boolean isInet6Address(String input) {
		Contract.checkArgument(!Strings.isNullOrEmpty(input), "Input must not be null or empty");

		Matcher matcher = INET_6_ADDRESS_PATTERN.matcher(input);

		return matcher.matches();
	}

}
