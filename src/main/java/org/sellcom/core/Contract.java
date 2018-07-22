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

import java.text.MessageFormat;

import org.sellcom.core.internal.ReflectionUtils;

/**
 * Operations for checking method contracts.
 *
 * @since 1.0
 */
public class Contract {

	private Contract() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks the given contract condition.
	 * Throws an exception of the given type with no message if the check fails.
	 *
	 * @throws IllegalArgumentException if {@code exceptionType} is {@code null}
	 * @throws IllegalArgumentException if {@code exceptionType} could not be instantiated
	 *
	 * @since 1.0
	 */
	public static <T extends RuntimeException> void check(boolean condition, Class<T> exceptionType) {
		if (exceptionType == null) {
			throw new IllegalArgumentException("Exception type must not be null");
		}

		if (!condition) {
			throw ReflectionUtils.createException(exceptionType);
		}
	}

	/**
	 * Checks the given contract condition.
	 * Throws an exception of the given type with the given message if the check fails.
	 * Interpolates the given arguments into the message using {@link MessageFormat} if any.
	 *
	 * @throws IllegalArgumentException if {@code exceptionType} is {@code null}
	 * @throws IllegalArgumentException if {@code exceptionType} could not be instantiated
	 *
	 * @since 1.0
	 */
	public static <T extends RuntimeException> void check(boolean condition, Class<T> exceptionType, String message, Object... arguments) {
		if (exceptionType == null) {
			throw new IllegalArgumentException("Exception type must not be null");
		}

		if (!condition) {
			if (arguments == null) {
				throw ReflectionUtils.createException(exceptionType, message);
			} else {
				throw ReflectionUtils.createException(exceptionType, MessageFormat.format(message, arguments));
			}
		}
	}

	/**
	 * Checks the given contract condition of one or more input parameters of the calling method.
	 * Throws an {@link IllegalArgumentException} with the given message if the check fails.
	 * Interpolates the given arguments into the message using {@link MessageFormat} if any.
	 *
	 * @since 1.0
	 */
	public static void checkArgument(boolean condition, String message, Object... arguments) {
		check(condition, IllegalArgumentException.class, message, arguments);
	}

	/**
	 * Checks the given contract condition of the calling object's state.
	 * Throws an {@link IllegalStateException} with the given message if the check fails.
	 * Interpolates the given arguments into the message using {@link MessageFormat} if any.
	 *
	 * @since 1.0
	 */
	public static void checkState(boolean condition, String message, Object... arguments) {
		check(condition, IllegalStateException.class, message, arguments);
	}

}
