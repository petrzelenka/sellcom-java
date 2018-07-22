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
package org.sellcom.core.io.encoding;

/**
 * Encoding scheme for binary data.
 *
 * @since 1.0
 */
public abstract class BinaryEncoding {

	/**
	 * Checks whether this encoding scheme supports decoding.
	 *
	 * @since 1.0
	 */
	public boolean canDecode() {
		return true;
	}

	/**
	 * Checks whether this encoding scheme supports encoding.
	 *
	 * @since 1.0
	 */
	public boolean canEncode() {
		return true;
	}

	/**
	 * Returns a decoder for this encoding scheme.
	 *
	 * @throws UnsupportedOperationException if this encoding scheme does not support decoding
	 *
	 * @since 1.0
	 */
	public abstract BinaryDecoder newDecoder();

	/**
	 * Returns an encoder for this encoding scheme.
	 *
	 * @throws UnsupportedOperationException if this encoding scheme does not support encoding
	 *
	 * @since 1.0
	 */
	public abstract BinaryEncoder newEncoder();

}
