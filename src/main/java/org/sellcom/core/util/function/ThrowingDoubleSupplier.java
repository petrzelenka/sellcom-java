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
import java.util.function.DoubleSupplier;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents a supplier of {@code double}-valued results, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingDoubleSupplier extends DoubleSupplier {

	/**
	 * Returns a {@code DoubleSupplier} returning from the given supplier if this supplier throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default DoubleSupplier fallbackTo(DoubleSupplier fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code DoubleSupplier} returning from the given supplier if this supplier throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default DoubleSupplier fallbackTo(DoubleSupplier fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback supplier must not be null");

		return () -> {
			try {
				return getAsDoubleThrowing();
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.getAsDouble();
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
	default double getAsDouble() {
		try {
			return getAsDoubleThrowing();
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
	double getAsDoubleThrowing() throws Exception;

	/**
	 * Returns a {@code DoubleSupplier} returning the given value if this supplier throws an exception.
	 *
	 * @since 1.0
	 */
	default DoubleSupplier orReturn(double value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code DoubleSupplier} returning the given value if this supplier throws an exception.
	 *
	 * @since 1.0
	 */
	default DoubleSupplier orReturn(double value, Consumer<Exception> exceptionConsumer) {
		return () -> {
			try {
				return getAsDoubleThrowing();
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingDoubleSupplier} throwing an exception of the given type if this supplier throws an exception.
	 * The original exception thrown by this supplier is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingDoubleSupplier orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return () -> {
			try {
				return getAsDoubleThrowing();
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingDoubleSupplier} throwing an exception of the given type if this supplier throws an exception.
	 * The original exception thrown by this supplier is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingDoubleSupplier orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return () -> {
			try {
				return getAsDoubleThrowing();
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingDoubleSupplier} returning from the given supplier if this supplier throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingDoubleSupplier orTryWith(ThrowingDoubleSupplier other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingDoubleSupplier} returning from the given supplier if this supplier throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingDoubleSupplier orTryWith(ThrowingDoubleSupplier other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other supplier must not be null");

		return () -> {
			try {
				return getAsDoubleThrowing();
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.getAsDoubleThrowing();
			}
		};
	}

}
