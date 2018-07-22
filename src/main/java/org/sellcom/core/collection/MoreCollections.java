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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.sellcom.core.Contract;

/**
 * Operations with collections.
 *
 * @since 1.0
 *
 * @see Collections
 */
public class MoreCollections {

	private MoreCollections() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns an immutable map containing the distinct values of the given collection as keys and their frequency as values.
	 *
	 * @throws IllegalArgumentException if {@code collection} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> Map<T, Integer> countFrequencies(Collection<T> collection) {
		Contract.checkArgument(collection != null, "Collection must not be null");

		return Collections.unmodifiableMap(collection.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.reducing(0, element -> 1, Integer::sum))));
	}

	/**
	 * Returns an empty case-ignoring {@link NavigableSet}.
	 *
	 * @since 1.0
	 */
	public static NavigableSet<String> caseIgnoringSet() {
		return new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	}

	/**
	 * Returns a case-ignoring {@link NavigableSet} containing the given strings.
	 *
	 * @throws IllegalArgumentException if {@code strings} are {@code null}
	 *
	 * @since 1.0
	 */
	public static NavigableSet<String> caseIgnoringSet(Collection<String> strings) {
		Contract.checkArgument(strings != null, "Strings must not be null");

		NavigableSet<String> caseIgnoringSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
		caseIgnoringSet.addAll(strings);

		return caseIgnoringSet;
	}

	/**
	 * Returns the given collection if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static <E> Collection<E> emptyToNull(Collection<E> collection) {
		return isNullOrEmpty(collection) ? null : collection;
	}

	/**
	 * Checks whether the given collection is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static <E> boolean isNullOrEmpty(Collection<E> collection) {
		return (collection == null) || collection.isEmpty();
	}

	/**
	 * Returns the given list if it is non-empty, empty list otherwise.
	 *
	 * @since 1.0
	 */
	public static <E> List<E> nullToEmpty(List<E> set) {
		return isNullOrEmpty(set) ? new ArrayList<>() : set;
	}

	/**
	 * Returns the given navigable set if it is non-empty, empty navigable set otherwise.
	 *
	 * @since 1.0
	 */
	public static <E> NavigableSet<E> nullToEmpty(NavigableSet<E> navigableSet) {
		return isNullOrEmpty(navigableSet) ? new TreeSet<>() : navigableSet;
	}

	/**
	 * Returns the given set if it is non-empty, empty set otherwise.
	 *
	 * @since 1.0
	 */
	public static <E> Set<E> nullToEmpty(Set<E> set) {
		return isNullOrEmpty(set) ? new HashSet<>() : set;
	}

	/**
	 * Returns an immutable map containing the mappings in the given map sorted by their values.
	 *
	 * @throws IllegalArgumentException if {@code map} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <K, V extends Comparable<V>> Map<K, V> sortByValues(Map<K, V> map) {
		Contract.checkArgument(map != null, "Map must not be null");

		return Collections.unmodifiableMap(map.entrySet().stream()
				.sorted(Entry.comparingByValue())
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (key, value) -> value, LinkedHashMap::new)));
	}

	/**
	 * Returns an immutable map containing the mappings in the given map sorted by their values.
	 *
	 * @throws IllegalArgumentException if {@code map} is {@code null}
	 * @throws IllegalArgumentException if {@code comparator} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <K, V> Map<K, V> sortByValues(Map<K, V> map, Comparator<V> comparator) {
		Contract.checkArgument(map != null, "Map must not be null");
		Contract.checkArgument(comparator != null, "Comparator must not be null");

		return Collections.unmodifiableMap(map.entrySet().stream()
				.sorted(Entry.comparingByValue(comparator))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (key, value) -> value, LinkedHashMap::new)));
	}

}
