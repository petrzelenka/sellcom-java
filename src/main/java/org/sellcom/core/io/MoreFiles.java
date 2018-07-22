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

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.sellcom.core.Contract;
import org.sellcom.core.collection.Iterables;

/**
 * Operations with files.
 *
 * @since 1.0
 */
public class MoreFiles {

	private MoreFiles() {
		// Utility class, not to be instantiated
	}


	/**
	 * Deletes the contents of the given directory.
	 *
	 * @throws IllegalArgumentException if {@code path} is {@code null}
	 * @throws IllegalArgumentException if {@code path} is not an existing directory
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static void deleteDirectoryContents(Path path) throws IOException {
		Contract.checkArgument(path != null, "Path must not be null");
		Contract.checkArgument(Files.isDirectory(path), "Path must be an existing directory");

		Files.walkFileTree(path, new ContentsDeletingVisitor(path));
	}

	/**
	 * Deletes the given file or directory.
	 * If the directory is non-empty, also deletes its contents.
	 *
	 * @throws IllegalArgumentException if {@code path} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void deleteRecursively(Path path) throws IOException {
		Contract.checkArgument(path != null, "Path must not be null");

		if (Files.isDirectory(path)) {
			deleteDirectoryContents(path);
		}

		Files.delete(path);
	}

	/**
	 * Ensures the existence of the given directory.
	 *
	 * @throws FileAlreadyExistsException if {@code path} already exists but is not a directory
	 * @throws IllegalArgumentException if {@code path} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static Path ensureDirectory(Path path) throws IOException {
		Contract.checkArgument(path != null, "Path must not be null");

		if (Files.isDirectory(path)) {
			return path;
		}
		if (Files.notExists(path)) {
			return Files.createDirectories(path);
		}

		throw new FileAlreadyExistsException(path.toString());
	}

	/**
	 * Ensures the existence of the parent directory of the given file or directory.
	 *
	 * @throws FileAlreadyExistsException if the parent directory of {@code path} already exists but is not a directory
	 * @throws IllegalArgumentException if {@code path} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static Path ensureParentDirectory(Path path) throws IOException {
		Contract.checkArgument(path != null, "Path must not be null");

		Path parentDirectory = path.getParent();
		if (parentDirectory != null) {
			ensureDirectory(parentDirectory);
		}

		return parentDirectory;
	}

	/**
	 * Checks whether the given path is an empty directory.
	 *
	 * @throws IllegalArgumentException if {@code path} is {@code null}
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static boolean isEmptyDirectory(Path path) throws IOException {
		Contract.checkArgument(path != null, "Path must not be null");

		if (Files.isDirectory(path)) {
			try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
				return Iterables.isNullOrEmpty(directoryStream);
			}
		}

		return false;
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class ContentsDeletingVisitor extends SimpleFileVisitor<Path> {

		private final Path rootDirectory;


		private ContentsDeletingVisitor(Path rootDirectory) {
			this.rootDirectory = rootDirectory;
		}


		@Override
		public FileVisitResult postVisitDirectory(Path directory, IOException exception) throws IOException {
			if (exception != null) {
				throw exception;
			}

			if (!directory.equals(rootDirectory)) { // Do not delete the root directory itself
				Files.delete(directory);
			}

			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
			Files.delete(file);

			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exception) throws IOException {
			if (exception != null) {
				throw exception;
			}

			return CONTINUE;
		}

	}

}
