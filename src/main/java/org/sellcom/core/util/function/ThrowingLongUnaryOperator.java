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
import java.util.function.LongUnaryOperator;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents an operation on a single {@code long}-valued operand that produces a {@code long}-valued result, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingLongUnaryOperator extends LongUnaryOperator {

	/**
	 * Returns a composed {@code ThrowingLongUnaryOperator} that first applies this operator to the argument, and then applies the after operator to the result.
	 *
	 * @throws IllegalArgumentException if {@code after} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongUnaryOperator andThen(ThrowingLongUnaryOperator after) {
		Contract.checkArgument(after != null, "After operator must not be null");

		return argument -> after.applyAsLongThrowing(applyAsLongThrowing(argument));
	}

	/**
	 * Applies this operator to the given argument.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default long applyAsLong(long argument) {
		try {
			return applyAsLongThrowing(argument);
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
	long applyAsLongThrowing(long argument) throws Exception;

	/**
	 * Returns a composed {@code ThrowingLongUnaryOperator} that first applies the before operator to the argument, and then applies this operator to the result.
	 *
	 * @throws IllegalArgumentException if {@code before} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongUnaryOperator compose(ThrowingLongUnaryOperator before) {
		Contract.checkArgument(before != null, "Before operator must not be null");

		return argument -> applyAsLongThrowing(before.applyAsLongThrowing(argument));
	}

	/**
	 * Returns a {@code LongUnaryOperator} applying the given operator to the argument if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default LongUnaryOperator fallbackTo(LongUnaryOperator fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code LongUnaryOperator} applying the given operator to the argument if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default LongUnaryOperator fallbackTo(LongUnaryOperator fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback operator must not be null");

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
	 * Returns a {@code LongUnaryOperator} returning the given value if this operator throws an exception.
	 *
	 * @since 1.0
	 */
	default LongUnaryOperator orReturn(long value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code LongUnaryOperator} returning the given value if this operator throws an exception.
	 *
	 * @since 1.0
	 */
	default LongUnaryOperator orReturn(long value, Consumer<Exception> exceptionConsumer) {
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
	 * Returns a {@code ThrowingLongUnaryOperator} throwing an exception of the given type if this operator throws an exception.
	 * The original exception thrown by this operator is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongUnaryOperator orThrow(Class<? extends RuntimeException> exceptionClass) {
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
	 * Returns a {@code ThrowingLongUnaryOperator} throwing an exception of the given type if this operator throws an exception.
	 * The original exception thrown by this operator is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongUnaryOperator orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
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
	 * Returns a {@code ThrowingLongUnaryOperator} applying the given operator to the argument if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongUnaryOperator orTryWith(ThrowingLongUnaryOperator other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingLongUnaryOperator} applying the given operator to the argument if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongUnaryOperator orTryWith(ThrowingLongUnaryOperator other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other operator must not be null");

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
