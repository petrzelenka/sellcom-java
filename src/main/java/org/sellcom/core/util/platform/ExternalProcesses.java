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

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;

import org.sellcom.core.Contract;
import org.sellcom.core.io.Io;

/**
 * Operations with external processes.
 *
 * @since 1.0
 */
public class ExternalProcesses {

	private ExternalProcesses() {
		// Utility class, not to be instantiated
	}


	/**
	 * Executes the given command.
	 *
	 * @throws IllegalArgumentException if {@code command} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void execute(String... command) {
		Contract.checkArgument(command != null, "Command must not be null");

		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(command);

			int exitValue = process.waitFor();
			if (exitValue != 0) {
				throw new ExternalProcessException(String.format("Process terminated with exit value %d", exitValue));
			}
		} catch (InterruptedException e) {
			throw new ExternalProcessException("Process interrupted", e);
		} catch (IOException e) {
			throw new ExternalProcessException("Process failed", e);
		}
	}

	/**
	 * Executes the given command and returns its output.
	 *
	 * @throws IllegalArgumentException if {@code command} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String executeAndReadOutput(String... command) {
		Contract.checkArgument(command != null, "Command must not be null");

		Runtime runtime = Runtime.getRuntime();
		try {
			Process process = runtime.exec(command);

			String output = "";
			try (InputStream sourceStream = process.getInputStream()) {
				output = Io.readString(sourceStream, UTF_8);
			}

			int exitValue = process.waitFor();
			if (exitValue != 0) {
				throw new ExternalProcessException(String.format("Process terminated with exit value %d", exitValue));
			}

			return output;
		} catch (InterruptedException e) {
			throw new ExternalProcessException("Process interrupted", e);
		} catch (IOException e) {
			throw new ExternalProcessException("Process failed", e);
		}
	}

}
