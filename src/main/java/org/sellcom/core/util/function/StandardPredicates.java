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

import java.util.Objects;
import java.util.function.Predicate;

import org.sellcom.core.MoreObjects;

/**
 * Standard predicates.
 *
 * @since 1.0
 */
public class StandardPredicates {

	private StandardPredicates() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns a predicate checking whether its argument is an instance of the given class or any of its descendant classes.
	 *
	 * @throws IllegalArgumentException if {@code clazz} is {@code null}
	 *
	 * @since 1.0
	 *
	 * @see MoreObjects#isDescendantOf(Object, Class)
	 */
	public static <T> Predicate<T> isAssignableFrom(Class<?> clazz) {
		return argument -> MoreObjects.isDescendantOf(argument, clazz);
	}

	/**
	 * Returns a predicate checking whether its argument is an instance of the given class.
	 *
	 * @throws IllegalArgumentException if {@code clazz} is {@code null}
	 *
	 * @since 1.0
	 *
	 * @see MoreObjects#isInstanceOf(Object, Class)
	 */
	public static <T> Predicate<T> isInstanceOf(Class<?> clazz) {
		return argument -> MoreObjects.isInstanceOf(argument, clazz);
	}

	/**
	 * Returns a predicate checking whether its argument is non-{@code null}.
	 *
	 * @since 1.0
	 */
	public static <T> Predicate<T> isNonNull() {
		return Objects::nonNull;
	}

	/**
	 * Returns a predicate checking whether its argument is {@code null}.
	 *
	 * @since 1.0
	 */
	public static <T> Predicate<T> isNull() {
		return Objects::isNull;
	}

}
