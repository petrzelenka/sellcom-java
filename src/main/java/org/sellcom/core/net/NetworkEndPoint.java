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
package org.sellcom.core.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * Network end point.
 *
 * @since 1.0
 */
public interface NetworkEndPoint {

	/**
	 * Returns the local address of this end point.
	 *
	 * @since 1.0
	 */
	InetAddress getLocalAddress();

	/**
	 * Returns the local port of this end point.
	 *
	 * @since 1.0
	 */
	int getLocalPort();

	/**
	 * Returns the network interface associated with this end point.
	 *
	 * @since 1.0
	 */
	NetworkInterface getNetworkInterface();

	/**
	 * Returns the state of this end point.
	 *
	 * @since 1.0
	 */
	State getState();

	/**
	 * Starts this end point.
	 *
	 * @since 1.0
	 */
	void start() throws IOException;

	/**
	 * Stops this end point.
	 *
	 * @since 1.0
	 */
	void stop();

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	/**
	 * State of the end point.
	 *
	 * @since 1.0
	 */
	public enum State {

		STARTED,

		STARTING,

		STOPPED,

		STOPPING;

	}

}
