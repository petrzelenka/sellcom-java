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
package org.sellcom.core;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * Operations with objects.
 *
 * @since 1.0
 */
public class MoreObjects {

	private MoreObjects() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the first object from the given objects that is not {@code null}.
	 * Returns {@code null} if there is no such object.
	 *
	 * @since 1.0
	 */
	@SafeVarargs
	public static <T> T coalesce(T first, T... other) {
		if (first != null) {
			return first;
		}
		if (other == null) {
			return null;
		}

		return Stream.of(other)
				.filter(Objects::nonNull)
				.findFirst()
				.orElse(null);
	}

	/**
	 * Checks whether the given object is an instance of the given class or any of its descendant classes.
	 *
	 * @throws IllegalArgumentException if {@code clazz} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isDescendantOf(Object object, Class<?> clazz) {
		Contract.checkArgument(clazz != null, "Class must not be null");

		return (object == null) ? false : clazz.isInstance(object);
	}

	/**
	 * Checks whether the given object is an instance of the given class.
	 *
	 * @throws IllegalArgumentException if {@code clazz} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean isInstanceOf(Object object, Class<?> clazz) {
		Contract.checkArgument(clazz != null, "Class must not be null");

		return (object == null) ? false : Objects.equals(object.getClass(), clazz);
	}

}
