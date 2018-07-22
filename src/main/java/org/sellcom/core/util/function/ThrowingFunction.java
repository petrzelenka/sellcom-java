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
import java.util.function.Function;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents a function that accepts a single argument and produces a result, possibly throwing an exception.
 *
 * <h3>Example</h3>
 * <pre>
 * ThrowingFunction&lt;String, Integer&gt; parse = Integer::parseInt;
 * Function&lt;String, Optional&lt;Integer&gt;&gt; safeParse = parse.fallbackTo(string -&gt; null)
 *         .andThen(Optional::ofNullable);
 * </pre>
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingFunction<T, R> extends Function<T, R> {

	/**
	 * Returns a composed {@code ThrowingFunction} that first applies this function to the argument, and then applies the after function to the result.
	 *
	 * @throws IllegalArgumentException if {@code after} is {@code null}
	 *
	 * @since 1.0
	 */
	default <V> ThrowingFunction<T, V> andThen(ThrowingFunction<? super R, ? extends V> after) {
		Contract.checkArgument(after != null, "After function must not be null");

		return argument -> after.applyThrowing(applyThrowing(argument));
	}

	/**
	 * Applies this function to the given argument.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default R apply(T argument) {
		try {
			return applyThrowing(argument);
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
	R applyThrowing(T argument) throws Exception;

	/**
	 * Returns a composed {@code ThrowingFunction} that first applies the before function to the argument, and then applies this function to the result.
	 *
	 * @throws IllegalArgumentException if {@code before} is {@code null}
	 *
	 * @since 1.0
	 */
	default <V> ThrowingFunction<V, R> compose(ThrowingFunction<? super V,? extends T> before) {
		Contract.checkArgument(before != null, "Before function must not be null");

		return argument -> applyThrowing(before.applyThrowing(argument));
	}

	/**
	 * Returns a {@code Function} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default Function<T, R> fallbackTo(Function<T, R> fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code Function} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default Function<T, R> fallbackTo(Function<T, R> fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback function must not be null");

		return argument -> {
			try {
				return applyThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.apply(argument);
			}
		};
	}

	/**
	 * Returns a {@code Function} returning the given value if this function throws an exception.
	 *
	 * @since 1.0
	 */
	default Function<T, R> orReturn(R value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code Function} returning the given value if this function throws an exception.
	 *
	 * @since 1.0
	 */
	default Function<T, R> orReturn(R value, Consumer<Exception> exceptionConsumer) {
		return argument -> {
			try {
				return applyThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingFunction} throwing an exception of the given type if this function throws an exception.
	 * The original exception thrown by this function is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingFunction<T, R> orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return argument -> {
			try {
				return applyThrowing(argument);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingFunction} throwing an exception of the given type if this function throws an exception.
	 * The original exception thrown by this function is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingFunction<T, R> orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return argument -> {
			try {
				return applyThrowing(argument);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingFunction} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingFunction<T, R> orTryWith(ThrowingFunction<? super T, ? extends R> other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingFunction} applying the given function to the argument if this function throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingFunction<T, R> orTryWith(ThrowingFunction<? super T, ? extends R> other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other function must not be null");

		return argument -> {
			try {
				return applyThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.applyThrowing(argument);
			}
		};
	}

}
