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
package org.sellcom.core.collection;

import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.sellcom.core.Contract;

/**
 * Operations with {@link Iterable}s.
 *
 * @since 1.0
 */
public class Iterables {

	private Iterables() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the given iterable if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static <E> Iterable<E> emptyToNull(Iterable<E> iterable) {
		return isNullOrEmpty(iterable) ? null : iterable;
	}

	/**
	 * Checks whether the given iterable is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(Iterable<?> iterable) {
		return (iterable == null) || !iterable.iterator().hasNext();
	}

	/**
	 * Returns the given iterable if it is non-empty, empty iterable otherwise.
	 *
	 * @since 1.0
	 */
	public static <E> Iterable<E> nullToEmpty(Iterable<E> iterable) {
		return isNullOrEmpty(iterable) ? Collections.emptyList() : iterable;
	}

	/**
	 * Returns a parallel {@code Stream} over the elements of the given iterable.
	 *
	 * @throws IllegalArgumentException if {@code iterable} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <E> Stream<E> parallelStream(Iterable<E> iterable) {
		Contract.checkArgument(iterable != null, "Iterable must not be null");

		return StreamSupport.stream(iterable.spliterator(), true);
	}

	/**
	 * Returns a sequential {@code Stream} over the elements of the given iterable.
	 *
	 * @throws IllegalArgumentException if {@code iterable} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <E> Stream<E> stream(Iterable<E> iterable) {
		Contract.checkArgument(iterable != null, "Iterable must not be null");

		return StreamSupport.stream(iterable.spliterator(), false);
	}

	/**
	 * Returns the given iterable converted to a {@link List}.
	 *
	 * @throws IllegalArgumentException if {@code elements} are {@code null}
	 *
	 * @since 1.0
	 */
	public static <E> List<E> toList(Iterable<E> elements) {
		Contract.checkArgument(elements != null, "Elements must not be null");

		if (elements instanceof List) {
			return (List<E>) elements;
		}

		return stream(elements)
				.collect(Collectors.toList());
	}

	/**
	 * Returns the given iterable converted to a {@link NavigableSet}.
	 *
	 * @throws IllegalArgumentException if {@code elements} are {@code null}
	 *
	 * @since 1.0
	 */
	public static <E> NavigableSet<E> toNavigableSet(Iterable<E> elements) {
		Contract.checkArgument(elements != null, "Elements must not be null");

		if (elements instanceof NavigableSet) {
			return (NavigableSet<E>) elements;
		}

		return stream(elements)
				.collect(Collectors.toCollection(TreeSet::new));
	}

	/**
	 * Returns the given iterable converted to a {@link Set}.
	 *
	 * @throws IllegalArgumentException if {@code elements} are {@code null}
	 *
	 * @since 1.0
	 */
	public static <E> Set<E> toSet(Iterable<E> elements) {
		Contract.checkArgument(elements != null, "Elements must not be null");

		if (elements instanceof Set) {
			return (Set<E>) elements;
		}

		return stream(elements)
				.collect(Collectors.toSet());
	}

}
