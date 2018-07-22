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
package org.sellcom.core.util.platform;

/**
 * Operations with the underlying operating system.
 *
 * @since 1.0
 */
public class Os {

	private Os() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks whether the current underlying operating system is a Linux.
	 *
	 * @since 1.0
	 */
	public static boolean isLinux() {
		String osName = System.getProperty("os.name");

		return osName.startsWith("Linux");
	}

	/**
	 * Checks whether the current underlying operating system is a 32-bit Linux.
	 *
	 * @since 1.0
	 */
	public static boolean isLinux32() {
		String osArch = System.getProperty("os.arch");
		String osName = System.getProperty("os.name");

		return osName.startsWith("Linux") && osArch.equals("i386");
	}

	/**
	 * Checks whether the current underlying operating system is a 64-bit Linux.
	 *
	 * @since 1.0
	 */
	public static boolean isLinux64() {
		String osArch = System.getProperty("os.arch");
		String osName = System.getProperty("os.name");

		return osName.startsWith("Linux") && osArch.equals("amd64");
	}

	/**
	 * Checks whether the current underlying operating system is a macOS.
	 *
	 * @since 1.1
	 */
	public static boolean isMacOs() {
		String osName = System.getProperty("os.name");

		return osName.startsWith("Mac OS");
	}

	/**
	 * Checks whether the current underlying operating system is a Solaris.
	 *
	 * @since 1.1
	 */
	public static boolean isSolaris() {
		String osName = System.getProperty("os.name");

		return osName.startsWith("Solaris");
	}

	/**
	 * Checks whether the current underlying operating system is a Microsoft Windows.
	 *
	 * @since 1.0
	 */
	public static boolean isWindows() {
		String osName = System.getProperty("os.name");

		return osName.startsWith("Windows");
	}

	/**
	 * Checks whether the current underlying operating system is a 32-bit Microsoft Windows.
	 *
	 * @since 1.0
	 */
	public static boolean isWindows32() {
		String osArch = System.getProperty("os.arch");
		String osName = System.getProperty("os.name");

		return osName.startsWith("Windows") && osArch.equals("x86");
	}

	/**
	 * Checks whether the current underlying operating system is a 64-bit Microsoft Windows.
	 *
	 * @since 1.0
	 */
	public static boolean isWindows64() {
		String osArch = System.getProperty("os.arch");
		String osName = System.getProperty("os.name");

		return osName.startsWith("Windows") && osArch.equals("x86_64");
	}

}
