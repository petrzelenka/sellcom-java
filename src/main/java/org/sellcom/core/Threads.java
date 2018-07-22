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

import java.util.concurrent.TimeUnit;

/**
 * Operations with {@code Thread}s.
 *
 * @since 1.0
 */
public class Threads {

	private Threads() {
		// Utility class, not to be instantiated
	}


	/**
	 * Checks the interrupted status of the current thread
	 *
	 * @since 1.0
	 */
	public static boolean checkInterrupted() {
		return Thread.currentThread().isInterrupted();
	}

	/**
	 * Interrupts the current thread.
	 *
	 * @since 1.0
	 */
	public static void interrupt() {
		Thread.currentThread().interrupt();
	}

	/**
	 * Preserves the interrupted status of the current thread following an {@code InterruptedException}.
	 *
	 * @since 1.0
	 */
	public static void preserveInterruptedStatus(InterruptedException e) {
		if (e != null) {
			interrupt();
		}
	}

	/**
	 * Makes the current thread sleep the given duration.
	 * Preserves the interrupted status of the current thread following an {@code InterruptedException}.
	 *
	 * @throws IllegalArgumentException if {@code duration} is negative
	 * @throws IllegalArgumentException if {@code unit} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void sleep(long duration, TimeUnit unit) {
		Contract.checkArgument(duration >= 0L, "Duration must not be negative");
		Contract.checkArgument(unit != null, "Unit must not be null");

		try {
			Thread.sleep(unit.toMillis(duration));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
