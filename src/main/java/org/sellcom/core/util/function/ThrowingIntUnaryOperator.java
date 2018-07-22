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
import java.util.function.IntUnaryOperator;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents an operation on a single {@code int}-valued operand that produces an {@code int}-valued result, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingIntUnaryOperator extends IntUnaryOperator {

	/**
	 * Returns a composed {@code ThrowingIntUnaryOperator} that first applies this operator to the argument, and then applies the after operator to the result.
	 *
	 * @throws IllegalArgumentException if {@code after} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingIntUnaryOperator andThen(ThrowingIntUnaryOperator after) {
		Contract.checkArgument(after != null, "After operator must not be null");

		return argument -> after.applyAsIntThrowing(applyAsIntThrowing(argument));
	}

	/**
	 * Applies this operator to the given argument.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default int applyAsInt(int argument) {
		try {
			return applyAsIntThrowing(argument);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw UncheckedException.wrap(e);
		}
	}

	/**
	 * Applies this operator to the given argument.
	 *
	 * @since 1.0
	 */
	int applyAsIntThrowing(int argument) throws Exception;

	/**
	 * Returns a composed {@code ThrowingIntUnaryOperator} that first applies the before operator to the argument, and then applies this operator to the result.
	 *
	 * @throws IllegalArgumentException if {@code before} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingIntUnaryOperator compose(ThrowingIntUnaryOperator before) {
		Contract.checkArgument(before != null, "Before operator must not be null");

		return argument -> applyAsIntThrowing(before.applyAsIntThrowing(argument));
	}

	/**
	 * Returns an {@code IntUnaryOperator} applying the given operator to the argument if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default IntUnaryOperator fallbackTo(IntUnaryOperator fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns an {@code IntUnaryOperator} applying the given operator to the argument if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default IntUnaryOperator fallbackTo(IntUnaryOperator fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback operator must not be null");

		return argument -> {
			try {
				return applyAsIntThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.applyAsInt(argument);
			}
		};
	}

	/**
	 * Returns an {@code IntUnaryOperator} returning the given value if this operator throws an exception.
	 *
	 * @since 1.0
	 */
	default IntUnaryOperator orReturn(int value) {
		return orReturn(value, null);
	}

	/**
	 * Returns an {@code IntUnaryOperator} returning the given value if this operator throws an exception.
	 *
	 * @since 1.0
	 */
	default IntUnaryOperator orReturn(int value, Consumer<Exception> exceptionConsumer) {
		return argument -> {
			try {
				return applyAsIntThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingIntUnaryOperator} throwing an exception of the given type if this operator throws an exception.
	 * The original exception thrown by this operator is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingIntUnaryOperator orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return argument -> {
			try {
				return applyAsIntThrowing(argument);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingIntUnaryOperator} throwing an exception of the given type if this operator throws an exception.
	 * The original exception thrown by this operator is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingIntUnaryOperator orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return argument -> {
			try {
				return applyAsIntThrowing(argument);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingIntUnaryOperator} applying the given operator to the argument if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingIntUnaryOperator orTryWith(ThrowingIntUnaryOperator other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingIntUnaryOperator} applying the given operator to the argument if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingIntUnaryOperator orTryWith(ThrowingIntUnaryOperator other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other operator must not be null");

		return argument -> {
			try {
				return applyAsIntThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.applyAsIntThrowing(argument);
			}
		};
	}

}
