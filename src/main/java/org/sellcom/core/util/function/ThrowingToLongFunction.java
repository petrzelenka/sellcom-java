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
import java.util.function.ToLongFunction;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents a function that accepts a single argument and produces an {@code long}-valued result, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingToLongFunction<T> extends ToLongFunction<T> {

	/**
	 * Applies this function to the given argument.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default long applyAsLong(T argument) {
		try {
			return applyAsLongThrowing(argument);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw UncheckedException.wrap(e);
		}
	}

	/**
	 * Applies this function to the given argument.
	 *
	 * @since 1.0
	 */
	long applyAsLongThrowing(T argument) throws Exception;

	/**
	 * Returns a {@code ToLongFunction} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default ToLongFunction<T> fallbackTo(ToLongFunction<T> fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code ToLongFunction} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default ToLongFunction<T> fallbackTo(ToLongFunction<T> fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback function must not be null");

		return argument -> {
			try {
				return applyAsLongThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.applyAsLong(argument);
			}
		};
	}

	/**
	 * Returns a {@code ToLongFunction} returning the given value if this function throws an exception.
	 *
	 * @since 1.0
	 */
	default ToLongFunction<T> orReturn(long value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code ToLongFunction} returning the given value if this function throws an exception.
	 *
	 * @since 1.0
	 */
	default ToLongFunction<T> orReturn(long value, Consumer<Exception> exceptionConsumer) {
		return argument -> {
			try {
				return applyAsLongThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingToLongFunction} throwing an exception of the given type if this function throws an exception.
	 * The original exception thrown by this function is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingToLongFunction<T> orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return argument -> {
			try {
				return applyAsLongThrowing(argument);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingToLongFunction} throwing an exception of the given type if this function throws an exception.
	 * The original exception thrown by this function is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingToLongFunction<T> orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return argument -> {
			try {
				return applyAsLongThrowing(argument);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingToLongFunction} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingToLongFunction<T> orTryWith(ThrowingToLongFunction<? super T> other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingToLongFunction} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingToLongFunction<T> orTryWith(ThrowingToLongFunction<? super T> other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other function must not be null");

		return argument -> {
			try {
				return applyAsLongThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.applyAsLongThrowing(argument);
			}
		};
	}

}
