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
import java.util.function.DoubleBinaryOperator;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents an operation on two {@code double}-valued operands, producing a {@code double}-valued result, and possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingDoubleBinaryOperator extends DoubleBinaryOperator {

	/**
	 * Applies this operator to the given arguments.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default double applyAsDouble(double former, double latter) {
		try {
			return applyAsDoubleThrowing(former, latter);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw UncheckedException.wrap(e);
		}
	}

	/**
	 * Applies this operator to the given arguments.
	 *
	 * @since 1.0
	 */
	double applyAsDoubleThrowing(double former, double latter) throws Exception;

	/**
	 * Returns a {@code DoubleBinaryOperator} applying the given operator to the arguments if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default DoubleBinaryOperator fallbackTo(DoubleBinaryOperator fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code DoubleBinaryOperator} applying the given operator to the arguments if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default DoubleBinaryOperator fallbackTo(DoubleBinaryOperator fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback operator must not be null");

		return (former, latter) -> {
			try {
				return applyAsDoubleThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.applyAsDouble(former, latter);
			}
		};
	}

	/**
	 * Returns a {@code DoubleBinaryOperator} returning the given value if this operator throws an exception.
	 *
	 * @since 1.0
	 */
	default DoubleBinaryOperator orReturn(double value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code DoubleBinaryOperator} returning the given value if this operator throws an exception.
	 *
	 * @since 1.0
	 */
	default DoubleBinaryOperator orReturn(double value, Consumer<Exception> exceptionConsumer) {
		return (former, latter) -> {
			try {
				return applyAsDoubleThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingDoubleBinaryOperator} throwing an exception of the given type if this operator throws an exception.
	 * The original exception thrown by this operator is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingDoubleBinaryOperator orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				return applyAsDoubleThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingDoubleBinaryOperator} throwing an exception of the given type if this operator throws an exception.
	 * The original exception thrown by this operator is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingDoubleBinaryOperator orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				return applyAsDoubleThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingDoubleBinaryOperator} applying the given operator to the arguments if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingDoubleBinaryOperator orTryWith(ThrowingDoubleBinaryOperator other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingDoubleBinaryOperator} applying the given operator to the arguments if this operator throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingDoubleBinaryOperator orTryWith(ThrowingDoubleBinaryOperator other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other operator must not be null");

		return (former, latter) -> {
			try {
				return applyAsDoubleThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.applyAsDoubleThrowing(former, latter);
			}
		};
	}

}
