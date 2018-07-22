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
 * Extractor of data from TTLV-encoded messages.
 *
 * @since 1.0
 */
public interface TtlvExtractor {

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	byte[] getBlob(short tag);

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	Boolean getBoolean(short tag);

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	Byte getByte(short tag);

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	Character getCharacter(short tag);

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	Double getDouble(short tag);

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	Float getFloat(short tag);

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	Integer getInteger(short tag);

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	Long getLong(short tag);

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	Short getShort(short tag);

	/**
	 * Returns the value associated with the given tag in the message.
	 *
	 * @since 1.0
	 */
	String getString(short tag);

}
