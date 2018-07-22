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
package org.sellcom.core.collection.concurrent;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.time.Clock;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.collection.MapEntry;

/**
 * Concurrent {@code HashMap} of expiring elements.
 * <p>
 * Does not allow {@code null} keys or values.
 *
 * @since 1.0
 */
public class ExpiringHashMap<K, V> implements Map<K, V> {

	private Clock clock = Clock.systemUTC();

	private final Map<K, V> entries = new HashMap<>();

	private final Queue<SimpleExpiringObject<K>> expiryTimeline = new PriorityQueue<>();

	private final long timeToLive; // Milliseconds


	/**
	 * Creates a new map of expiring elements with the given default time to live.
	 *
	 * @throws IllegalArgumentException if {@code timeToLive} is negative
	 * @throws IllegalArgumentException if {@code unit} is {@code null}
	 *
	 * @since 1.0
	 */
	public ExpiringHashMap(long timeToLive, TimeUnit unit) {
		Contract.checkArgument(timeToLive >= 0L, "Time to live must not be negative: {0}", timeToLive);
		Contract.checkArgument(unit != null, "Unit must not be null");

		this.timeToLive = unit.toMillis(timeToLive);
	}


	@Override
	public void clear() {
		synchronized (expiryTimeline) {
			entries.clear();
			expiryTimeline.clear();
		}
	}

	@Override
	public boolean containsKey(Object key) {
		Contract.checkArgument(key != null, "Key must not be null");

		synchronized (expiryTimeline) {
			cleanUp();

			return entries.containsKey(key);
		}
	}

	@Override
	public boolean containsValue(Object value) {
		Contract.checkArgument(value != null, "Value must not be null");

		synchronized (expiryTimeline) {
			cleanUp();

			return entries.containsValue(value);
		}
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		synchronized (expiryTimeline) {
			cleanUp();

			Set<Map.Entry<K, V>> entrySet = new HashSet<>(entries.size());
			entries.forEach((key, value) -> {
				entrySet.add(new MapEntry<>(key, value));
			});

			return entrySet;
		}
	}

	@Override
	public V get(Object key) {
		Contract.checkArgument(key != null, "Key must not be null");

		synchronized (expiryTimeline) {
			cleanUp();

			return entries.get(key);
		}
	}

	@Override
	public boolean isEmpty() {
		synchronized (expiryTimeline) {
			cleanUp();

			return entries.isEmpty();
		}
	}

	@Override
	public Set<K> keySet() {
		synchronized (expiryTimeline) {
			cleanUp();

			return new HashSet<>(entries.keySet());
		}
	}

	@Override
	public V put(K key, V value) {
		Contract.checkArgument(key != null, "Key must not be null");
		Contract.checkArgument(value != null, "Value must not be null");

		synchronized (expiryTimeline) {
			cleanUp();

			return _put(key, value);
		}
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {
		Contract.checkArgument(map != null, "Map must not be null");

		synchronized (expiryTimeline) {
			cleanUp();

			map.forEach(this::_put);
		}
	}

	@Override
	public V remove(Object key) {
		Contract.checkArgument(key != null, "Key must not be null");

		synchronized (expiryTimeline) {
			cleanUp();

			return _remove(key);
		}
	}

	@Override
	public int size() {
		synchronized (expiryTimeline) {
			cleanUp();

			return entries.size();
		}
	}

	@Override
	public Collection<V> values() {
		synchronized (expiryTimeline) {
			cleanUp();

			return new HashSet<>(entries.values());
		}
	}


	private V _put(K key, V value) {
		V previousValue = entries.put(key, value);

		expiryTimeline.remove(key);
		expiryTimeline.add(new SimpleExpiringObject<>(key, timeToLive, MILLISECONDS, clock));

		return previousValue;
	}

	private V _remove(Object key) {
		V previousValue = entries.remove(key);

		expiryTimeline.remove(key);

		return previousValue;
	}

	private void cleanUp() {
		while (!expiryTimeline.isEmpty() && expiryTimeline.peek().isExpired()) {
			entries.remove(expiryTimeline.poll().getValue());
		}
	}

	// Intended for testing only
	void setClock(Clock clock) {
		Contract.checkArgument(clock != null, "Clock must not be null");

		this.clock = clock;
		expiryTimeline.forEach(expiringObject -> expiringObject.setClock(clock));
	}

}
