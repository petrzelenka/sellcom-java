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
import java.util.function.ObjIntConsumer;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;

/**
 * Represents an operation that accepts an {@code object}-valued and an {@code int}-valued argument, and returns no result, possibly throwing an exception.
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingObjIntConsumer<T> extends ObjIntConsumer<T> {

	/**
	 * Performs this operation on the given arguments.
	 * Wraps any thrown checked exceptions with {@link UncheckedException}.
	 *
	 * @since 1.0
	 */
	@Override
	default void accept(T former, int latter) {
		try {
			acceptThrowing(former, latter);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw UncheckedException.wrap(e);
		}
	}

	/**
	 * Performs this operation on the given arguments.
	 *
	 * @since 1.0
	 */
	void acceptThrowing(T former, int latter) throws Exception;

	/**
	 * Returns an {@code ObjIntConsumer} performing the given operation on the arguments if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default ObjIntConsumer<T> fallbackTo(ObjIntConsumer<T> fallback) {
		return fallbackTo(fallback, null);
	}

	/**
	 * Returns an {@code ObjIntConsumer} performing the given operation on the arguments if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code fallback} is {@code null}
	 *
	 * @since 1.0
	 */
	default ObjIntConsumer<T> fallbackTo(ObjIntConsumer<T> fallback, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(fallback != null, "Fallback consumer must not be null");

		return (former, latter) -> {
			try {
				acceptThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				fallback.accept(former, latter);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingObjIntConsumer} throwing an exception of the given type if this operation throws an exception.
	 * The original exception thrown by this operation is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting a single {@code Throwable} as an argument.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingObjIntConsumer<T> orThrow(Class<? extends RuntimeException> exceptionClass) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				acceptThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingObjIntConsumer} throwing an exception of the given type if this operation throws an exception.
	 * The original exception thrown by this operation is the {@link Throwable#getCause() cause} of the thrown exception.
	 *
	 * <p>The exception class must have a constructor accepting {@code String} and {@code Throwable} as arguments.</p>
	 *
	 * @throws IllegalArgumentException if {@code exceptionClass} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingObjIntConsumer<T> orThrow(Class<? extends RuntimeException> exceptionClass, String message) {
		Contract.checkArgument(exceptionClass != null, "Exception class must not be null");

		return (former, latter) -> {
			try {
				acceptThrowing(former, latter);
			} catch (Exception e) {
				throw ReflectionUtils.createException(exceptionClass, message, e);
			}
		};
	}

	/**
	 * Returns a {@code ThrowingObjIntConsumer} performing the given operation on the arguments if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingObjIntConsumer<T> orTryWith(ThrowingObjIntConsumer<? super T> other) {
		return orTryWith(other, null);
	}

	/**
	 * Returns a {@code ThrowingObjIntConsumer} performing the given operation on the arguments if this operation throws an exception.
	 *
	 * @throws IllegalArgumentException if {@code other} is {@code null}
	 *
	 * @since 1.0
	 */
	default ThrowingObjIntConsumer<T> orTryWith(ThrowingObjIntConsumer<? super T> other, Consumer<Exception> exceptionConsumer) {
		Contract.checkArgument(other != null, "Other consumer must not be null");

		return (former, latter) -> {
			try {
				acceptThrowing(former, latter);
			} catch (Exception e) {
				if (exceptionConsumer != null) {
					exceptionConsumer.accept(e);
				}

				other.acceptThrowing(former, latter);
			}
		};
	}

}
