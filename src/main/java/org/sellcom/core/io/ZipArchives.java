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

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;

/**
 * Operations with ZIP archives.
 *
 * @since 1.0
 */
public class ZipArchives {

	private static final Path ROOT_PATH = Paths.get("/");


	private ZipArchives() {
		// Utility class, not to be instantiated
	}


	/**
	 * Creates a ZIP archive from the contents of the given directory.
	 * Encodes file names using UTF-8.
	 *
	 * @throws IllegalArgumentException if {@code archive} is {@code null}
	 * @throws IllegalArgumentException if {@code sourceDirectory} is {@code null}
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static void createArchive(Path archive, Path sourceDirectory) throws IOException {
		createArchive(archive, sourceDirectory, UTF_8);
	}

	/**
	 * Creates a ZIP archive from the contents of the given directory.
	 * Encodes file names using the given charset.
	 *
	 * @throws IllegalArgumentException if {@code archive} is {@code null}
	 * @throws IllegalArgumentException if {@code sourceDirectory} is {@code null}
	 * @throws IllegalArgumentException if {@code charset} is {@code null}
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static void createArchive(Path archive, Path sourceDirectory, Charset charset) throws IOException {
		Contract.checkArgument(archive != null, "Archive must not be null");
		Contract.checkArgument(sourceDirectory != null, "Source directory must not be null");
		Contract.checkArgument(charset != null, "Charset must not be null");

		Map<String, String> parameters = new HashMap<>();
		parameters.put("create", "true");
		parameters.put("encoding", charset.name());

		try (FileSystem archiveFileSystem = FileSystems.newFileSystem(getFileSystemUri(archive), parameters, null)) {
			Files.walkFileTree(sourceDirectory, new ContentsPackingVisitor(archiveFileSystem, sourceDirectory));
		}
	}

	/**
	 * Extracts the contents of the given ZIP archive into the given directory.
	 * Decodes file names using UTF-8.
	 *
	 * @throws IllegalArgumentException if {@code archive} is {@code null}
	 * @throws IllegalArgumentException if {@code destinationDirectory} is {@code null}
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static void extractArchive(Path archive, Path destinationDirectory) throws IOException {
		extractArchive(archive, destinationDirectory, UTF_8);
	}

	/**
	 * Extracts the contents of the given ZIP archive into the given directory.
	 * Decodes file names using the given charset.
	 *
	 * @throws IllegalArgumentException if {@code archive} is {@code null}
	 * @throws IllegalArgumentException if {@code destinationDirectory} is {@code null}
	 * @throws IllegalArgumentException if {@code charset} is {@code null}
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static void extractArchive(Path archive, Path destinationDirectory, Charset charset) throws IOException {
		Contract.checkArgument(archive != null, "Archive must not be null");
		Contract.checkArgument(destinationDirectory != null, "Destination directory must not be null");
		Contract.checkArgument(charset != null, "Charset must not be null");

		Map<String, String> parameters = new HashMap<>();
		parameters.put("create", "false");
		parameters.put("encoding", charset.name());

		try (FileSystem archiveFileSystem = FileSystems.newFileSystem(getFileSystemUri(archive), parameters, null)) {
			Files.walkFileTree(archiveFileSystem.getPath("/"), new ContentsUnpackingVisitor(destinationDirectory));
		}
	}

	/**
	 * Walks the file tree of the given ZIP archive.
	 * Decodes files name using UTF-8.
	 *
	 * @throws IllegalArgumentException if {@code archive} is {@code null}
	 * @throws IllegalArgumentException if {@code visitor} is {@code null}
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static void walkArchiveTree(Path archive, FileVisitor<? super Path> visitor) throws IOException {
		walkArchiveTree(archive, visitor, UTF_8);
	}

	/**
	 * Walks the file tree of the given ZIP archive.
	 * Decodes files name using the given charset.
	 *
	 * @throws IllegalArgumentException if {@code archive} is {@code null}
	 * @throws IllegalArgumentException if {@code visitor} is {@code null}
	 * @throws IllegalArgumentException if {@code charset} is {@code null}
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static void walkArchiveTree(Path archive, FileVisitor<? super Path> visitor, Charset charset) throws IOException {
		Contract.checkArgument(archive != null, "Archive must not be null");
		Contract.checkArgument(visitor != null, "Visitor must not be null");
		Contract.checkArgument(charset != null, "Charset must not be null");

		Map<String, String> parameters = new HashMap<>();
		parameters.put("create", "false");
		parameters.put("encoding", charset.name());

		try (FileSystem archiveFileSystem = FileSystems.newFileSystem(getFileSystemUri(archive), parameters, null)) {
			Files.walkFileTree(archiveFileSystem.getPath("/"), visitor);
		}
	}

	/**
	 * Walks the file tree of the given ZIP archive up to the given maximum depth.
	 * Decodes files names using UTF-8.
	 *
	 * @throws IllegalArgumentException if {@code archive} is {@code null}
	 * @throws IllegalArgumentException if {@code visitor} is {@code null}
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static void walkArchiveTree(Path archive, int maxDepth, FileVisitor<? super Path> visitor) throws IOException {
		walkArchiveTree(archive, maxDepth, visitor, UTF_8);
	}

	/**
	 * Walks the file tree of the given ZIP archive up to the given maximum depth.
	 * Decodes files names using the given charset.
	 *
	 * @throws IllegalArgumentException if {@code archive} is {@code null}
	 * @throws IllegalArgumentException if {@code maxDepth} is negative
	 * @throws IllegalArgumentException if {@code visitor} is {@code null}
	 * @throws IllegalArgumentException if {@code charset} is {@code null}
	 * @throws IOException if an I/O occurs
	 *
	 * @since 1.0
	 */
	public static void walkArchiveTree(Path archive, int maxDepth, FileVisitor<? super Path> visitor, Charset charset) throws IOException {
		Contract.checkArgument(archive != null, "Archive must not be null");
		Contract.checkArgument(maxDepth >= 0, "Maximum depth must not be negative");
		Contract.checkArgument(visitor != null, "Visitor must not be null");
		Contract.checkArgument(charset != null, "Charset must not be null");

		Map<String, String> parameters = new HashMap<>();
		parameters.put("create", "false");
		parameters.put("encoding", charset.name());

		try (FileSystem archiveFileSystem = FileSystems.newFileSystem(getFileSystemUri(archive), parameters, null)) {
			Files.walkFileTree(archiveFileSystem.getPath("/"), EnumSet.noneOf(FileVisitOption.class), maxDepth, visitor);
		}
	}


	private static URI getFileSystemUri(Path archive) throws IOException {
		try {
			URI archiveUri = archive.toUri();

			return new URI("jar:" + archiveUri.getScheme(), archiveUri.getPath(), null);
		} catch (URISyntaxException e) {
			throw new IOException("Invalid file system", e);
		}
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class ContentsPackingVisitor extends SimpleFileVisitor<Path> {

		private final FileSystem archiveFileSystem;

		private final Path sourceDirectory;


		private ContentsPackingVisitor(FileSystem archiveFileSystem, Path sourceDirectory) {
			this.archiveFileSystem = archiveFileSystem;
			this.sourceDirectory = sourceDirectory;
		}


		@Override
		public FileVisitResult postVisitDirectory(Path unpackedDirectory, IOException exception) throws IOException {
			if (exception != null) {
				throw exception;
			}

			return CONTINUE;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path unpackedDirectory, BasicFileAttributes attributes) throws IOException {
			if (!unpackedDirectory.equals(sourceDirectory)) {
				String relativePath = sourceDirectory.relativize(unpackedDirectory).toString();

				Path packedDirectory = archiveFileSystem.getPath(relativePath);
				MoreFiles.ensureDirectory(packedDirectory);
			}

			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path unpackedFile, BasicFileAttributes attributes) throws IOException {
			String relativePath = sourceDirectory.relativize(unpackedFile).toString();

			Path packedFile = archiveFileSystem.getPath(relativePath);
			Files.copy(unpackedFile, packedFile, StandardCopyOption.COPY_ATTRIBUTES);

			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path unpackedFile, IOException exception) throws IOException {
			if (exception != null) {
				throw exception;
			}

			return CONTINUE;
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class ContentsUnpackingVisitor extends SimpleFileVisitor<Path> {

		private final Path destinationDirectory;


		private ContentsUnpackingVisitor(Path destinationDirectory) {
			this.destinationDirectory = destinationDirectory;
		}


		@Override
		public FileVisitResult postVisitDirectory(Path packedDirectory, IOException exception) throws IOException {
			if (exception != null) {
				throw exception;
			}

			return CONTINUE;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path packedDirectory, BasicFileAttributes attributes) throws IOException {
			if (!packedDirectory.equals(ROOT_PATH)) {
				String relativePath = packedDirectory.toString();
				relativePath = Strings.removePrefix(relativePath, "/");
				relativePath = Strings.removeSuffix(relativePath, "/");

				Path unpackedDirectory = destinationDirectory.resolve(relativePath);
				MoreFiles.ensureDirectory(unpackedDirectory);
			}

			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path packedFile, BasicFileAttributes attributes) throws IOException {
			String relativePath = packedFile.toString();
			relativePath = Strings.removePrefix(relativePath, "/");
			relativePath = Strings.removeSuffix(relativePath, "/");

			Path unpackedFile = destinationDirectory.resolve(relativePath);
			Files.copy(packedFile, unpackedFile, StandardCopyOption.COPY_ATTRIBUTES);

			return CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path packedFile, IOException exception) throws IOException {
			if (exception != null) {
				throw exception;
			}

			return CONTINUE;
		}

	}

}
