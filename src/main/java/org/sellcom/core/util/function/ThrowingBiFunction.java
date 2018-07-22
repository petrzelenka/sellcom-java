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

import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents a function that accepts two arguments and produces a result, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingBiFunction<T, U, R> extends BiFunction<T, U, R> {

	/**
	 * Returns a composed {@code ThrowingBiFunction} that first applies this function to the arguments, and then applies the after function to the result.
	 *
	 * @throws IllegalArgumentException if {@code after} is {@code null}
	 *
	 * @since 1.0
	 */
	default <V> ThrowingBiFunction<T, U, V> andThen(ThrowingFunction<? super R, ? extends V> after) {
		Contract.checkArgument(after != null, "After function must not be null");

		return (former, latter) -> after.apply(apply(former, latter));
	}

	/**
	 * Applies this function to the given arguments.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default R apply(T former, U latter) {
		try {
			return applyThrowing(former, latter);
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
	R applyThrowing(T former, U latter) throws Exception;


	/**
	 * Returns a {@code BiFunction} applying the given function to the arguments if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default BiFunction<T, U, R> fallbackTo(BiFunction<T, U, R> fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code BiFunction} applying the given function to the arguments if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default BiFunction<T, U, R> fallbackTo(BiFunction<T, U, R> fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback function must not be null");

		return (former, latter) -> {
			try {
				return applyThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.apply(former, latter);
			}
		};
	}

	/**
	 * Returns a {@code BiFunction} returning the given value if this function throws an exception.
	 *
	 * @since 1.0
	 */
	default BiFunction<T, U, R> orReturn(R value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code BiFunction} returning the given value if this function throws an exception.
	 *
	 * @since 1.0
	 */
	default BiFunction<T, U, R> orReturn(R value, Consumer<Exception> exceptionConsumer) {
		return (former, latter) -> {
			try {
				return applyThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingBiFunction} throwing an exception of the given type if this function throws an exception.
	 * The original exception thrown by this function is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingBiFunction<T, U, R> orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				return applyThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingBiFunction} throwing an exception of the given type if this function throws an exception.
	 * The original exception thrown by this function is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingBiFunction<T, U, R> orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				return applyThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingBiFunction} applying the given function to the arguments if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingBiFunction<T, U, R> orTryWith(ThrowingBiFunction<? super T, ? super U, ? extends R> other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingBiFunction} applying the given function to the arguments if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingBiFunction<T, U, R> orTryWith(ThrowingBiFunction<? super T, ? super U, ? extends R> other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other function must not be null");

		return (former, latter) -> {
			try {
				return applyThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.applyThrowing(former, latter);
			}
		};
	}

}
