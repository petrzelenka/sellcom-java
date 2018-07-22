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

import java.net.DatagramSocket;

/**
 * Traffic classes.
 *
 * @since 1.0
 *
 * @see DatagramSocket#setTrafficClass(int)
 */
public enum TrafficClass {

	/**
	 * Traffic class maximizing reliability.
	 *
	 * @since 1.0
	 */
	MAXIMIZE_RELIABILITY(0x04),

	/**
	 * Traffic class maximizing throughput.
	 *
	 * @since 1.0
	 */
	MAXIMIZE_THROUGHPUT(0x08),

	/**
	 * Traffic class minimizing delay.
	 *
	 * @since 1.0
	 */
	MINIMIZE_DELAY(0x10),

	/**
	 * Traffic class minimizing monetary cost.
	 *
	 * @since 1.0
	 */
	MINIMIZE_MONETARY_COST(0x02),

	/**
	 * Traffic class of normal service.
	 *
	 * @since 1.0
	 */
	NORMAL_SERVICE(0x00),

	;

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private final int value;


	private TrafficClass(int value) {
		this.value = value;
	}


	public int getValue() {
		return value;
	}

}
