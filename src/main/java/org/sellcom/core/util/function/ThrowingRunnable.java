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
package org.sellcom.core.util.function;

import java.util.function.Consumer;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents an operation that accepts no arguments and returns no result, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingRunnable extends Runnable {

	/**
	 * Returns a {@code Runnable} performing the given operation if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default Runnable fallbackTo(Runnable fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code Runnable} performing the given operation if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default Runnable fallbackTo(Runnable fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback runnable must not be null");

		return () -> {
			try {
				runThrowing();
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				fallback.run();
			}
		};
	}

	/**
	 * Returns a {@code Runnable} performing no other operation if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default Runnable orDoNothing() {
		return orDoNothing(null);
	}

	/**
	 * Returns a {@code Runnable} performing no other operation if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default Runnable orDoNothing(Consumer<Exception> exceptionConsumer) {
		return () -> {
			try {
				runThrowing();
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}
			}
		};
	}

	/**
	 * Returns a {@code ThrowingRunnable} throwing an exception of the given type if this operation throws an exception.
	 * The original exception thrown by this operation is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingRunnable orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return () -> {
			try {
				runThrowing();
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingRunnable} throwing an exception of the given type if this operation throws an exception.
	 * The original exception thrown by this operation is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingRunnable orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return () -> {
			try {
				runThrowing();
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingRunnable} performing the given operation if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingRunnable orTryWith(ThrowingRunnable other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingRunnable} performing the given operation if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingRunnable orTryWith(ThrowingRunnable other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other runnable must not be null");

		return () -> {
			try {
				runThrowing();
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				other.runThrowing();
			}
		};
	}

	/**
	 * Performs this operation.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default void run() {
		try {
			runThrowing();
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw UncheckedException.wrap(e);
		}
	}

	/**
	 * Performs this operation.
	 *
	 * @since 1.0
	 */
	void runThrowing() throws Exception;

}
