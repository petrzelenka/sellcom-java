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

import java.util.Optional;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.sellcom.core.Contract;

/**
 * Thread-safe unbounded cache with soft-referenced values.
 *
 * @since 1.3
 */
public class ConcurrentSoftCache<K, V> implements Cache<K, V> {

	private final Cache<K, V> entries;

	private final ReadWriteLock lock = new ReentrantReadWriteLock(true);


	/**
	 * Creates an unbounded cache with soft-referenced values.
	 *
	 * @since 1.3
	 */
	public ConcurrentSoftCache() {
		entries = new SoftCache<>();
	}

	/**
	 * Creates an unbounded cache with soft-referenced values that uses the given mapping function to create values from keys.
	 *
	 * @throws IllegalArgumentException if {@code mappingFunction} is {@code null}
	 *
	 * @since 1.3
	 */
	public ConcurrentSoftCache(Function<K, V> mappingFunction) {
		Contract.checkArgument(mappingFunction != null, "Mapping function must not be null");

		entries = new SoftCache<>(mappingFunction);
	}


	@Override
	public boolean equals(Object other) {
		try {
			lock.readLock().lock();

			return super.equals(other);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public void evict(K key) {
		try {
			lock.writeLock().lock();

			entries.evict(key);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void evict(K key, V value) {
		try {
			lock.writeLock().lock();

			entries.evict(key, value);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void evictAll() {
		try {
			lock.writeLock().lock();

			entries.evictAll();
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) {
		try {
			lock.readLock().lock();

			entries.forEach(action);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public Optional<V> get(K key) {
		try {
			lock.readLock().lock();

			return entries.get(key);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public V getOrDefault(K key, V defaultValue) {
		try {
			lock.readLock().lock();

			return entries.getOrDefault(key, defaultValue);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public int hashCode() {
		try {
			lock.readLock().lock();

			return entries.hashCode();
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		try {
			lock.readLock().lock();

			return entries.isEmpty();
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public void put(K key, V value) {
		try {
			lock.writeLock().lock();

			entries.put(key, value);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public void putIfAbsent(K key, V value) {
		try {
			lock.writeLock().lock();

			entries.putIfAbsent(key, value);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public int size() {
		try {
			lock.readLock().lock();

			return entries.size();
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public String toString() {
		try {
			lock.readLock().lock();

			return entries.toString();
		} finally {
			lock.readLock().unlock();
		}
	}

}
