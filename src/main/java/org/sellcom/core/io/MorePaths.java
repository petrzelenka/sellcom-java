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
package org.sellcom.core.io;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;
import org.sellcom.core.util.platform.Os;

/**
 * Operations with file system paths.
 *
 * @since 1.0
 */
public class MorePaths {

	private MorePaths() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the given path in the canonical (normalized absolute) form.
	 *
	 * @throws IllegalArgumentException if {@code path} is {@code null}
	 *
	 * @since 1.0
	 */
	public static Path canonicalize(Path path) {
		Contract.checkArgument(path != null, "Path must not be null");

		// NOTE: The order of the operations is important to correctly handle
		// situations like in this method call:
		//
		//     MorePaths.canonicalize(Paths.get("../some_file"));

		return path.toAbsolutePath().normalize();
	}

	/**
	 * Returns the extension of the file represented by the given path.
	 *
	 * @throws IllegalArgumentException if {@code path} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String getFileExtension(Path path) {
		String fileName = getFileName(path);

		int lastDot = fileName.lastIndexOf('.');

		return (lastDot > 0) ? fileName.substring(lastDot + 1) : "";
	}

	/**
	 * Returns the name of the file represented by the given path.
	 *
	 * @throws IllegalArgumentException if {@code path} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String getFileName(Path path) {
		Contract.checkArgument(path != null, "Path must not be null");

		Path fileName = path.getFileName();

		return (fileName != null) ? fileName.toString() : "";
	}

	/**
	 * Returns the name of the file represented by the given path without extension.
	 *
	 * @throws IllegalArgumentException if {@code path} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String getFileNameWithoutExtension(Path path) {
		String fileName = getFileName(path);

		int lastDot = fileName.lastIndexOf('.');

		return (lastDot > 0) ? fileName.substring(0, lastDot) : fileName;
	}

	/**
	 * Returns the home directory of the current JRE.
	 *
	 * @since 1.0
	 */
	public static Path getJavaHomeDirectory() {
		return Paths.get(System.getProperty("java.home"));
	}

	/**
	 * Returns the application cache directory of the current user.
	 *
	 * @throws IllegalArgumentException if {@code appName} is {@code null} or empty
	 *
	 * @since 1.2
	 */
	public static Path getUserCacheDirectory(String appName) {
		Contract.checkArgument(!Strings.isNullOrEmpty(appName), "Application name must not be null or empty");

		if (Os.isWindows()) {
			return getUserRoamingProfileDirectory().resolve(appName).resolve("Cache");
		}

		Path userHomeDirectory = getUserHomeDirectory();

		if (Os.isLinux()) {
			return userHomeDirectory.resolve(".cache").resolve(appName);
		}
		if (Os.isMacOs()) {
			return userHomeDirectory.resolve("Library").resolve("Caches").resolve(appName);
		}
		return userHomeDirectory;
	}

	/**
	 * Returns the application config directory of the current user.
	 *
	 * @throws IllegalArgumentException if {@code appName} is {@code null} or empty
	 *
	 * @since 1.2
	 */
	public static Path getUserConfigDirectory(String appName) {
		Contract.checkArgument(!Strings.isNullOrEmpty(appName), "Application name must not be null or empty");

		if (Os.isWindows()) {
			return getUserRoamingProfileDirectory().resolve(appName);
		}

		Path userHomeDirectory = getUserHomeDirectory();

		if (Os.isLinux()) {
			return userHomeDirectory.resolve(".config").resolve(appName);
		}
		if (Os.isMacOs()) {
			return userHomeDirectory.resolve("Library").resolve("Application Support").resolve(appName);
		}

		return userHomeDirectory;
	}

	/**
	 * Returns the application data directory of the current user.
	 *
	 * @throws IllegalArgumentException if {@code appName} is {@code null} or empty
	 *
	 * @since 1.2
	 */
	public static Path getUserDataDirectory(String appName) {
		Contract.checkArgument(!Strings.isNullOrEmpty(appName), "Application name must not be null or empty");

		if (Os.isWindows()) {
			return getUserRoamingProfileDirectory().resolve(appName);
		}

		Path userHomeDirectory = getUserHomeDirectory();

		if (Os.isLinux()) {
			return userHomeDirectory.resolve(".local").resolve("share").resolve(appName);
		}
		if (Os.isMacOs()) {
			return userHomeDirectory.resolve("Library").resolve("Application Support").resolve(appName);
		}

		return userHomeDirectory;
	}

	/**
	 * Returns the home directory of the current user.
	 *
	 * @since 1.0
	 */
	public static Path getUserHomeDirectory() {
		return Paths.get(System.getProperty("user.home"));
	}

	/**
	 * Returns the application log directory of the current user.
	 *
	 * @throws IllegalArgumentException if {@code appName} is {@code null} or empty
	 *
	 * @since 1.2
	 */
	public static Path getUserLogDirectory(String appName) {
		Contract.checkArgument(!Strings.isNullOrEmpty(appName), "Application name must not be null or empty");

		if (Os.isWindows()) {
			return getUserRoamingProfileDirectory().resolve(appName).resolve("Logs");
		}

		Path userHomeDirectory = getUserHomeDirectory();

		if (Os.isLinux()) {
			return userHomeDirectory.resolve(".cache").resolve(appName).resolve("logs");
		}
		if (Os.isMacOs()) {
			return userHomeDirectory.resolve("Library").resolve("Logs");
		}

		return userHomeDirectory;
	}

	/**
	 * Returns the working directory.
	 *
	 * @since 1.0
	 */
	public static Path getWorkingDirectory() {
		return Paths.get("");
	}

	/**
	 * Resolves the given path components against the given base path.
	 *
	 * @throws IllegalArgumentException if {@code base} is {@code null}
	 *
	 * @since 2.1
	 *
	 * @see Path#resolve(Path)
	 */
	public static Path resolveAll(Path base, Path... components) {
		Contract.checkArgument(base != null, "Base path must not be null");

		Path result = base;
		for (Path currentComponent : components) {
			if (currentComponent != null) {
				result = result.resolve(currentComponent);
			}
		}

		return result;
	}

	/**
	 * Resolves the given path components against the given base path.
	 *
	 * @throws IllegalArgumentException if {@code base} is {@code null}
	 *
	 * @since 2.1
	 *
	 * @see Path#resolve(String)
	 */
	public static Path resolveAll(Path base, String... components) {
		Path result = base;
		for (String currentComponent : components) {
			if (currentComponent != null) {
				result = result.resolve(currentComponent);
			}
		}

		return result;
	}


	private static Path getUserRoamingProfileDirectory() {
		String appData = System.getenv("APPDATA");

		return Strings.isNullOrEmpty(appData)
				? getUserHomeDirectory().resolve("AppData").resolve("Roaming")
				: Paths.get(appData);
	}

}
