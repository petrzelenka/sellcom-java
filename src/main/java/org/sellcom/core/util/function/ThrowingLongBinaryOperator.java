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
import java.util.function.LongBinaryOperator;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents an operation on two {@code long}-valued operands, producing a {@code long}-valued result, and possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingLongBinaryOperator extends LongBinaryOperator {

	/**
	 * Applies this operator to the given argument.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default long applyAsLong(long former, long latter) {
		try {
			return applyAsLongThrowing(former, latter);
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
	long applyAsLongThrowing(long former, long latter) throws Exception;

	/**
	 * Returns a {@code LongBinaryOperator} applying the given operator to the arguments if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default LongBinaryOperator fallbackTo(LongBinaryOperator fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code LongBinaryOperator} applying the given operator to the arguments if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default LongBinaryOperator fallbackTo(LongBinaryOperator fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback operator must not be null");

		return (former, latter) -> {
			try {
				return applyAsLongThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.applyAsLong(former, latter);
			}
		};
	}

	/**
	 * Returns a {@code LongBinaryOperator} returning the given value if this operator throws an exception.
	 *
	 * @since 1.0
	 */
	default LongBinaryOperator orReturn(long value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code LongBinaryOperator} returning the given value if this operator throws an exception.
	 *
	 * @since 1.0
	 */
	default LongBinaryOperator orReturn(long value, Consumer<Exception> exceptionConsumer) {
		return (former, latter) -> {
			try {
				return applyAsLongThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingLongBinaryOperator} throwing an exception of the given type if this operator throws an exception.
	 * The original exception thrown by this operator is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongBinaryOperator orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				return applyAsLongThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingLongBinaryOperator} throwing an exception of the given type if this operator throws an exception.
	 * The original exception thrown by this operator is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongBinaryOperator orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				return applyAsLongThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingLongBinaryOperator} applying the given operator to the arguments if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongBinaryOperator orTryWith(ThrowingLongBinaryOperator other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingLongBinaryOperator} applying the given operator to the arguments if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongBinaryOperator orTryWith(ThrowingLongBinaryOperator other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other operator must not be null");

		return (former, latter) -> {
			try {
				return applyAsLongThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.applyAsLongThrowing(former, latter);
			}
		};
	}

}
