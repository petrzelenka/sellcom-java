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

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Operations with {@code NetworkInterface}s.
 *
 * @since 1.0
 *
 * @see NetworkInterface
 */
public class NetworkInterfaces {

	private NetworkInterfaces() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns the list of all network interfaces.
	 *
	 * @since 1.0
	 */
	public static List<NetworkInterface> getNetworkInterfaces() throws SocketException {
		List<NetworkInterface> networkInterfaces = new ArrayList<>();
		for (Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces(); enumeration.hasMoreElements(); ) {
			networkInterfaces.add(enumeration.nextElement());
		}

		return networkInterfaces;
	}

	/**
	 * Returns the list of broadcast-enabled network interfaces.
	 *
	 * @since 1.0
	 */
	public static List<NetworkInterface> getBroadcastEnabledNetworkInterfaces() throws SocketException {
		List<NetworkInterface> networkInterfaces = getNetworkInterfaces();
		for (Iterator<NetworkInterface> iterator = networkInterfaces.iterator(); iterator.hasNext(); ) {
			NetworkInterface networkInterface = iterator.next();
			Optional<InterfaceAddress> broadcastEnabledInterfaceAddress = networkInterface.getInterfaceAddresses().stream()
					.filter(interfaceAddress -> interfaceAddress.getBroadcast() != null)
					.findAny();

			if (!broadcastEnabledInterfaceAddress.isPresent()) {
				iterator.remove();
			}
		}

		return networkInterfaces;
	}

}
