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
package org.sellcom.core.util.platform;

import java.util.HashMap;
import java.util.Map;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;

/**
 * Operations with shutdown hooks.
 *
 * @since 1.4
 */
public class ShutdownHooks {

	private static final Map<String, Thread> shutdownHooks = new HashMap<>();


	private ShutdownHooks() {
		// Utility class, not to be instantiated
	}


	/**
	 * Registers the given runnable as a shutdown hook under the given name.
	 *
	 * @throws IllegalArgumentException if {@code name} is {@code null} or empty
	 * @throws IllegalArgumentException if {@code runnable} is {@code null}
	 *
	 * @since 1.4
	 *
	 * @see Runtime#addShutdownHook(Thread)
	 */
	public static void register(String name, Runnable runnable) {
		Contract.checkArgument(!Strings.isNullOrEmpty(name), "Name must not be null or empty");
		Contract.checkArgument(runnable != null, "Runnable must not be null");

		Runtime runtime = Runtime.getRuntime();

		if (shutdownHooks.containsKey(name)) {
			runtime.removeShutdownHook(shutdownHooks.remove(name));
		}

		Thread thread = new Thread(runnable);
		thread.setName(name);

		shutdownHooks.put(name, thread);
		runtime.addShutdownHook(thread);
	}

	/**
	 * Unregisters the runnable previously registered as a shutdown hook under the given name.
	 *
	 * @throws IllegalArgumentException if {@code name} is {@code null} or empty
	 *
	 * @since 1.4
	 *
	 * @see Runtime#removeShutdownHook(Thread)
	 */
	public static void unregister(String name) {
		Contract.checkArgument(!Strings.isNullOrEmpty(name), "Name must not be null or empty");

		if (shutdownHooks.containsKey(name)) {
			Runtime runtime = Runtime.getRuntime();
			runtime.removeShutdownHook(shutdownHooks.remove(name));
		}
	}

}
