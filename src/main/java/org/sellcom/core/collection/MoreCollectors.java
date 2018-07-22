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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * More implementations of a {@link Collector}.
 *
 * @since 1.0
 *
 * @see Collectors
 */
public class MoreCollectors {

	private MoreCollectors() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns a {@code Collector} that accumulates the input elements into a new unmodifiable {@code List}.
	 *
	 * @since 1.0
	 */
	public static <E> Collector<E, List<E>, List<E>> toUnmodifiableList() {
		return toUnmodifiableList(ArrayList::new);
	}

	/**
	 * Returns a {@code Collector} that accumulates the input elements into a new unmodifiable {@code List}.
	 * Uses the given factory to create the internal list.
	 *
	 * @since 1.0
	 */
	public static <E, C extends List<E>> Collector<E, C, List<E>> toUnmodifiableList(Supplier<C> listFactory) {
		return Collector.of(listFactory, List<E>::add, MoreCollectors::listCombiner, Collections::unmodifiableList);
	}

	/**
	 * Returns a {@code Collector} that accumulates the input elements into a new unmodifiable {@code Set}.
	 *
	 * @since 1.0
	 */
	public static <E> Collector<E, Set<E>, Set<E>> toUnmodifiableSet() {
		return toUnmodifiableSet(HashSet::new);
	}

	/**
	 * Returns a {@code Collector} that accumulates the input elements into a new unmodifiable {@code Set}.
	 * Uses the given factory to create the internal set.
	 *
	 * @since 1.0
	 */
	public static <E, C extends Set<E>> Collector<E, C, Set<E>> toUnmodifiableSet(Supplier<C> setFactory) {
		return Collector.of(setFactory, Set<E>::add, MoreCollectors::setCombiner, Collections::unmodifiableSet);
	}


	private static <E, C extends List<E>> C listCombiner(C left, C right) {
		left.addAll(right);

		return left;
	}

	private static <E, C extends Set<E>> C setCombiner(C left, C right) {
		left.addAll(right);

		return left;
	}

}
