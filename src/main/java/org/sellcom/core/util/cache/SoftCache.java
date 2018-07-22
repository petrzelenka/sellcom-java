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

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.sellcom.core.Contract;

/**
 * Unbounded cache with soft-referenced values.
 *
 * @since 1.3
 */
public final class SoftCache<K, V> implements Cache<K, V> {

	private final Map<K, SoftReference<V>> entries = new HashMap<>();

	private final Function<K, V> mappingFunction;

	private final ReferenceQueue<V> staleReferences = new ReferenceQueue<>();



	/**
	 * Creates an unbounded cache with soft-referenced values.
	 *
	 * @since 1.3
	 */
	public SoftCache() {
		this.mappingFunction = null;
	}

	/**
	 * Creates an unbounded cache with soft-referenced values that uses the given mapping function to create values from keys.
	 *
	 * @throws IllegalArgumentException if {@code mappingFunction} is {@code null}
	 *
	 * @since 1.3
	 */
	public SoftCache(Function<K, V> mappingFunction) {
		Contract.checkArgument(mappingFunction != null, "Mapping function must not be null");

		this.mappingFunction = mappingFunction;
	}


	@Override
	public void evict(K key) {
		Contract.checkArgument(key != null, "Key must not be null");

		expungeStaleReferences();

		entries.remove(key);
	}

	@Override
	public void evict(K key, V value) {
		Contract.checkArgument(key != null, "Key must not be null");
		Contract.checkArgument(value != null, "Value must not be null");

		// NOTE: Do not delegate to entries.remove(K, SoftReference<V>)
		// to avoid comparing references using identity.

		Optional<V> currentValue = get(key);
		if (currentValue.isPresent() && Objects.equals(currentValue.get(), value)) {
			evict(key);
		}
	}

	@Override
	public void evictAll() {
		expungeStaleReferences();

		entries.clear();
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) {
		Contract.checkArgument(action != null, "Action must not be null");

		expungeStaleReferences();

		for (Map.Entry<K, SoftReference<V>> currentEntry : entries.entrySet()) {
			K currentKey = currentEntry.getKey();
			V currentValue = currentEntry.getValue().get();
			if (currentValue != null) {
				action.accept(currentKey, currentValue);
			}
		}
	}

	@Override
	public Optional<V> get(K key) {
		Contract.checkArgument(key != null, "Key must not be null");

		expungeStaleReferences();

		V value = null;

		SoftReference<V> softReference = entries.get(key);
		if (softReference == null) { // Mapping not present
			if (mappingFunction != null) {
				value = mappingFunction.apply(key);
				if (value != null) {
					entries.put(key, new KeyedSoftReference<>(staleReferences, key, value));
				}
			}

			return Optional.ofNullable(value);
		}

		value = softReference.get();
		if (value == null) { // Mapping expired
			if (mappingFunction != null) {
				value = mappingFunction.apply(key);
				if (value != null) {
					entries.put(key, new KeyedSoftReference<>(staleReferences, key, value));

					return Optional.ofNullable(value);
				}
			}
		}

		return Optional.ofNullable(value);
	}

	@Override
	public V getOrDefault(K key, V defaultValue) {
		Contract.checkArgument(key != null, "Key must not be null");

		expungeStaleReferences();

		V value = null;

		SoftReference<V> softReference = entries.get(key);
		if (softReference == null) { // Mapping not present
			return defaultValue;
		}

		value = softReference.get();
		if (value == null) { // Mapping expired
			return defaultValue;
		}

		return value;
	}

	@Override
	public boolean isEmpty() {
		expungeStaleReferences();

		return entries.isEmpty();
	}

	@Override
	public void put(K key, V value) {
		Contract.checkArgument(key != null, "Key must not be null");
		Contract.checkArgument(value != null, "Value must not be null");

		entries.put(key, new KeyedSoftReference<>(staleReferences, key, value));
	}

	@Override
	public void putIfAbsent(K key, V value) {
		Contract.checkArgument(key != null, "Key must not be null");
		Contract.checkArgument(value != null, "Value must not be null");

		// NOTE: Do not delegate to entries.putIfAbsent(K, SoftReference<V>)
		// to avoid creating an unnecessary KeyHoldingSoftReference.

		Optional<V> currentValue = get(key);
		if (!currentValue.isPresent()) {
			put(key, value);
		}
	}

	@Override
	public int size() {
		expungeStaleReferences();

		return entries.size();
	}


	@SuppressWarnings("unchecked")
	private void expungeStaleReferences() {
		KeyedSoftReference<K, V> reference;
		while ((reference = (KeyedSoftReference<K, V>) staleReferences.poll()) != null) {
			entries.remove(reference.getKey());
		}
	}

	@Override
	public String toString() {
		expungeStaleReferences();

		StringBuilder builder = new StringBuilder("{");
		entries.forEach((key, reference) -> {
			V value = reference.get();
			if (value != null) {
				builder.append(key);
				builder.append("=");
				builder.append(value);
				builder.append(", ");
			}
		});
		if (builder.length() > 2) {
			builder.setLength(builder.length() - 2);
		}
		builder.append("}");

		return builder.toString();
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class KeyedSoftReference<K, V> extends SoftReference<V> {

		private final K key;


		private KeyedSoftReference(ReferenceQueue<? super V> staleReferences, K key, V value) {
			super(value, staleReferences);

			this.key = key;
		}


		private K getKey() {
			return key;
		}

	}

}
