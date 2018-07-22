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
import java.util.function.LongPredicate;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents a predicate ({@code boolean}-valued function) of one {@code long}-valued argument, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingLongPredicate extends LongPredicate {

	/**
	 * Evaluates this predicate on the given argument.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default boolean test(long argument) {
		try {
			return testThrowing(argument);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw UncheckedException.wrap(e);
		}
	}

	/**
	 * Evaluates this predicate on the given argument.
	 *
	 * @since 1.0
	 */
	boolean testThrowing(long argument) throws Exception;

	/**
	 * Returns a {@code LongPredicate} evaluating the given predicate on the argument if this predicate throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default LongPredicate fallbackTo(LongPredicate fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns a {@code LongPredicate} evaluating the given predicate on the argument if this predicate throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default LongPredicate fallbackTo(LongPredicate fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback predicate must not be null");

		return argument -> {
			try {
				return testThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return fallback.test(argument);
			}
		};
	}

	/**
	 * Returns a {@code LongPredicate} returning the given value if this predicate throws an exception.
	 *
	 * @since 1.0
	 */
	default LongPredicate orReturn(boolean value) {
		return orReturn(value, null);
	}

	/**
	 * Returns a {@code LongPredicate} returning the given value if this predicate throws an exception.
	 *
	 * @since 1.0
	 */
	default LongPredicate orReturn(boolean value, Consumer<Exception> exceptionConsumer) {
		return argument -> {
			try {
				return testThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return value;
			}
		};
	}

	/**
	 * Returns a {@code ThrowingLongPredicate} throwing an exception of the given type if this predicate throws an exception.
	 * The original exception thrown by this predicate is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongPredicate orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return argument -> {
			try {
				return testThrowing(argument);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingLongPredicate} throwing an exception of the given type if this predicate throws an exception.
	 * The original exception thrown by this predicate is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongPredicate orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return argument -> {
			try {
				return testThrowing(argument);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingLongPredicate} evaluating the given predicate on the argument if this predicate throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongPredicate orTryWith(ThrowingLongPredicate other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingLongPredicate} evaluating the given predicate on the argument if this predicate throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingLongPredicate orTryWith(ThrowingLongPredicate other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other predicate must not be null");

		return argument -> {
			try {
				return testThrowing(argument);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				return other.testThrowing(argument);
			}
		};
	}

}
