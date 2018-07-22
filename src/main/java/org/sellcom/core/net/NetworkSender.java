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

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Network sender.
 *
 * @since 1.0
 */
public interface NetworkSender extends NetworkEndPoint {

	/**
	 * Returns the local end point of this sender.
	 *
	 * @since 1.0
	 */
	InetSocketAddress getLocalEndPoint();

	/**
	 * Returns the send buffer size of this sender.
	 *
	 * @since 1.0
	 */
	int getSendBufferSize();

	/**
	 * Sends the given message after the given initial delay.
	 *
	 * @throws IllegalArgumentException if {@code message} is {@code null}
	 * @throws IllegalArgumentException if {@code message.getRemoteEndPoint()} is {@code null}
	 * @throws IllegalArgumentException if {@code message.getUuid()} is {@code null}
	 * @throws IllegalArgumentException if {@code initialDelay} is negative
	 * @throws IllegalArgumentException if {@code unit} is {@code null}
	 * @throws IllegalStateException if this sender has not yet been started
	 *
	 * @since 1.0
	 */
	void sendDelayed(NetworkMessage message, long initialDelay, TimeUnit unit);

	/**
	 * Sends the given message immediately.
	 *
	 * @throws IllegalArgumentException if {@code message} is {@code null}
	 * @throws IllegalArgumentException if {@code message.getRemoteEndPoint()} is {@code null}
	 * @throws IllegalArgumentException if {@code message.getUuid()} is {@code null}
	 * @throws IllegalStateException if this sender has not yet been started
	 *
	 * @since 1.0
	 */
	void sendImmediately(NetworkMessage message);

}
