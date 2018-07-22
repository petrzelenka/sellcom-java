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

import org.sellcom.core.Contract;

/**
 * Unchecked wrapper of a checked exception.
 *
 * @since 1.0
 */
public final class UncheckedException extends RuntimeException {

	private static final long serialVersionUID = 8259155533222578328L;


	private UncheckedException(Exception exception) {
		super(exception.getMessage(), exception);
	}


	/**
	 * Returns an {@code UncheckedException} wrapping the given checked exception.
	 *
	 * @throws IllegalArgumentException if {@code exception} is {@code null}
	 *
	 * @since 1.0
	 */
	public static UncheckedException wrap(Exception exception) {
		Contract.checkArgument(exception != null, "Wrapped exception must not be null");

		return new UncheckedException(exception);
	}

	/**
	 * Returns the checked exception wrapped by this {@code UncheckedException}.
	 *
	 * @since 1.0
	 */
	public Exception unwrap() {
		return (Exception) getCause();
	}

}
