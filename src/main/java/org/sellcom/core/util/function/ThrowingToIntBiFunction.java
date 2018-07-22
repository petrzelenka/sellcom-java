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
import java.util.function.ToIntBiFunction;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents a function that accepts two arguments and produces a {@code double}-valued result, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingToIntBiFunction<T, U> extends ToIntBiFunction<T, U> {

	/**
	 * Applies this function to the given arguments.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default int applyAsInt(T former, U latter) {
		try {
			return applyAsIntThrowing(former, latter);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw UncheckedException.wrap(e);
		}
	}

	/**
	 * Applies this function to the given arguments.
	 *
	 * @since 1.0
	 */
	int applyAsIntThrowing(T former, U latter) throws Exception;

	/**
	 * Returns a {@code ToIntBiFunction} applying the given function to the arguments if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default ToIntBiFunction<T, U> fallbackTo(ToIntBiFunction<T, U> fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code ToIntBiFunction} applying the given function to the arguments if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default ToIntBiFunction<T, U> fallbackTo(ToIntBiFunction<T, U> fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback function must not be null");

		return (former, latter) -> {
			try {
				return applyAsIntThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.applyAsInt(former, latter);
			}
		};
	}

	/**
	 * Returns a {@code ToIntBiFunction} returning the given value if this function throws an exception.
	 *
	 * @since 1.0
	 */
	default ToIntBiFunction<T, U> orReturn(int value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code ToIntBiFunction} returning the given value if this function throws an exception.
	 *
	 * @since 1.0
	 */
	default ToIntBiFunction<T, U> orReturn(int value, Consumer<Exception> exceptionConsumer) {
		return (former, latter) -> {
			try {
				return applyAsIntThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingToIntBiFunction} throwing an exception of the given type if this function throws an exception.
	 * The original exception thrown by this function is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingToIntBiFunction<T, U> orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				return applyAsIntThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingToIntBiFunction} throwing an exception of the given type if this function throws an exception.
	 * The original exception thrown by this function is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingToIntBiFunction<T, U> orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				return applyAsIntThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingToIntBiFunction} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingToIntBiFunction<T, U> orTryWith(ThrowingToIntBiFunction<? super T, ? super U> other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingToIntBiFunction} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingToIntBiFunction<T, U> orTryWith(ThrowingToIntBiFunction<? super T, ? super U> other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other function must not be null");

		return (former, latter) -> {
			try {
				return applyAsIntThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.applyAsIntThrowing(former, latter);
			}
		};
	}

}
