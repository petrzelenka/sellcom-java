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

import java.time.Clock;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.sellcom.core.Contract;

/**
 * Simple expiring object.
 *
 * @since 1.0
 */
public class SimpleExpiringObject<T> implements Comparable<SimpleExpiringObject<T>> {

	private Clock clock;

	private final long expiry;

	private final T value;


	/**
	 * Creates a new object with the given value and expiring after the given time.
	 *
	 * @throws IllegalArgumentException if {@code value} is {@code null}
	 * @throws IllegalArgumentException if {@code expiry} is negative
	 * @throws IllegalArgumentException if {@code unit} is {@code null}
	 *
	 * @since 1.0
	 */
	public SimpleExpiringObject(T value, long expiry, TimeUnit unit) {
		this(value, expiry, unit, Clock.systemUTC());
	}

	SimpleExpiringObject(T value, long expiry, TimeUnit unit, Clock clock) {
		Contract.checkArgument(value != null, "Value must not be null");
		Contract.checkArgument(expiry >= 0L, "Expiry must not be negative");
		Contract.checkArgument(unit != null, "Unit must not be null");

		this.value = value;
		this.expiry = Math.addExact(clock.millis(), unit.toMillis(expiry));
		this.clock = clock;
	}


	@Override
	public int compareTo(SimpleExpiringObject<T> other) {
		return Long.compare(expiry, other.expiry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (other instanceof SimpleExpiringObject) {
			return Objects.equals(value, ((SimpleExpiringObject<T>) other).value);
		}

		return false;
	}

	/**
	 * Returns the value of this object.
	 *
	 * @since 1.0
	 */
	public T getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	/**
	 * Checks whether this object is expired.
	 *
	 * @since 1.0
	 */
	public boolean isExpired() {
		return clock.millis() > expiry;
	}

	// Intended for testing only
	void setClock(Clock clock) {
		Contract.checkArgument(clock != null, "Clock must not be null");

		this.clock = clock;
	}

}
