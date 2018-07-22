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
package org.sellcom.core.internal.collection.concurrent;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.time.Clock;
import java.util.Objects;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.sellcom.core.Contract;

public abstract class SimpleDelayed<T> implements Delayed {

	private static Clock clock = Clock.systemUTC();

	private final long timestamp;

	private final T value;


	protected SimpleDelayed(T value, long delay, TimeUnit unit) {
		Contract.checkArgument(value != null, "Value must not be null");
		Contract.checkArgument(delay >= 0L, "Delay must not be negative");
		Contract.checkArgument(unit != null, "Unit must not be null");

		this.timestamp = Math.addExact(clock.millis(), unit.toMillis(delay));
		this.value = value;
	}


	@SuppressWarnings("rawtypes")
	@Override
	public int compareTo(Delayed other) {
		if (other instanceof SimpleDelayed) {
			return Long.compare(timestamp, ((SimpleDelayed) other).timestamp);
		} else {
			return Long.compare(getDelay(MILLISECONDS), other.getDelay(MILLISECONDS));
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (other instanceof SimpleDelayed) {
			return Objects.equals(value, ((SimpleDelayed<?>) other).value);
		}

		return false;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		Contract.checkArgument(unit != null, "Unit must not be null");

		return unit.convert(Math.subtractExact(timestamp, clock.millis()), MILLISECONDS);
	}

	public T getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

}
