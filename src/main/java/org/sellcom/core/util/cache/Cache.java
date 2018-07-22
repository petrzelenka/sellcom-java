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
package org.sellcom.core.util.cache;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * Generic cache.
 *
 * @since 1.3
 */
public interface Cache<K, V> {

	/**
	 * Evicts the entry with the given key from this cache.
	 *
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 *
	 * @since 1.3
	 *
	 * @see Map#remove(Object)
	 */
	void evict(K key);

	/**
	 * Evicts the entry with the given key and value from this cache.
	 *
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 *
	 * @since 1.3
	 *
	 * @see Map#remove(Object, Object)
	 */
	void evict(K key, V value);

	/**
	 * Evicts all entries from this cache.
	 *
	 * @since 1.3
	 *
	 * @see Map#clear()
	 */
	void evictAll();

	/**
	 * Performs the given action for each entry in this cache until all entries have been processed or the action throws an exception.
	 * Exceptions thrown by the action are relayed to the caller.
	 *
	 * @throws IllegalArgumentException if {@code action} is {@code null}
	 *
	 * @since 1.3
	 *
	 * @see Map#forEach(BiConsumer)
	 */
	void forEach(BiConsumer<? super K,? super V> action);

	/**
	 * Returns the value associated with the given key in this cache.
	 *
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 *
	 * @since 1.3
	 *
	 * @see Map#get(Object)
	 */
	Optional<V> get(K key);

	/**
	 * Returns the value associated with the given key in this cache.
	 * Returns the given default value if this cache contains no entry with the key.
	 *
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 *
	 * @since 1.3
	 *
	 * @see Map#getOrDefault(Object, Object)
	 */
	V getOrDefault(K key, V defaultValue);

	/**
	 * Checks whether this cache is empty.
	 *
	 * @since 1.3
	 *
	 * @see Map#isEmpty()
	 */
	boolean isEmpty();

	/**
	 * Creates an entry with the given key and value in this cache.
	 * Overwrites the existing entry with the same key, if any.
	 *
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 *
	 * @since 1.3
	 *
	 * @see Map#put(Object, Object)
	 */
	void put(K key, V value);

	/**
	 * Creates an entry with the given key and value in this cache only if no entry with the same key already exists.
	 *
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 *
	 * @since 1.3
	 *
	 * @see Map#putIfAbsent(Object, Object)
	 */
	void putIfAbsent(K key, V value);

	/**
	 * Returns the number of entries in this cache.
	 *
	 * @since 1.3
	 *
	 * @see Map#size()
	 */
	int size();

}
