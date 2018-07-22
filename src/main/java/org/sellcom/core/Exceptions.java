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
package org.sellcom.core;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Operations with exceptions.
 *
 * @since 1.0
 */
public class Exceptions {

	private Exceptions() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the root cause of the given exception.
	 *
	 * @throws IllegalArgumentException if {@code exception} is {@code null}
	 *
	 * @since 1.0
	 */
	public static Throwable getRootCause(Throwable exception) {
		Contract.checkArgument(exception != null, "Exception must not be null");

		Throwable rootCause = exception;

		Throwable nextCause;
		while ((nextCause = rootCause.getCause()) != null) {
			rootCause = nextCause;
		}

		return rootCause;
	}

	/**
	 * Returns the stack trace of the given exception.
	 *
	 * @throws IllegalArgumentException if {@code exception} is {@code null}
	 *
	 * @since 1.0
	 */
	public static String getStackTrace(Throwable exception) {
		Contract.checkArgument(exception != null, "Exception must not be null");

		StringWriter writer = new StringWriter();
		exception.printStackTrace(new PrintWriter(writer, true));

		return writer.toString();
	}

}
