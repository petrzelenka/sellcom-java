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
import java.util.function.LongSupplier;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents a supplier of {@code long}-valued results, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingLongSupplier extends LongSupplier {

	/**
	 * Returns a {@code LongSupplier} returning from the given supplier if this supplier throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default LongSupplier fallbackTo(LongSupplier fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code LongSupplier} returning from the given supplier if this supplier throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default LongSupplier fallbackTo(LongSupplier fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback supplier must not be null");

		return () -> {
			try {
				return getAsLongThrowing();
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.getAsLong();
			}
		};
	}

	/**
	 * Returns a result.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default long getAsLong() {
		try {
			return getAsLongThrowing();
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw UncheckedException.wrap(e);
		}
	}

	/**
	 * Returns a result.
	 *
	 * @since 1.0
	 */
	long getAsLongThrowing() throws Exception;

	/**
	 * Returns a {@code LongSupplier} returning the given value if this supplier throws an exception.
	 *
	 * @since 1.0
	 */
	default LongSupplier orReturn(long value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code LongSupplier} returning the given value if this supplier throws an exception.
	 *
	 * @since 1.0
	 */
	default LongSupplier orReturn(long value, Consumer<Exception> exceptionConsumer) {
		return () -> {
			try {
				return getAsLongThrowing();
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingLongSupplier} throwing an exception of the given type if this supplier throws an exception.
	 * The original exception thrown by this supplier is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongSupplier orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return () -> {
			try {
				return getAsLongThrowing();
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingLongSupplier} throwing an exception of the given type if this supplier throws an exception.
	 * The original exception thrown by this supplier is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongSupplier orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return () -> {
			try {
				return getAsLongThrowing();
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingLongSupplier} returning from the given supplier if this supplier throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongSupplier orTryWith(ThrowingLongSupplier other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingLongSupplier} returning from the given supplier if this supplier throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongSupplier orTryWith(ThrowingLongSupplier other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other supplier must not be null");

		return () -> {
			try {
				return getAsLongThrowing();
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.getAsLongThrowing();
			}
		};
	}

}
