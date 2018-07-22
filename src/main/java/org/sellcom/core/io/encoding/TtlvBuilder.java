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
 * Builder of TTLV-encoded messages.
 *
 * @since 1.0
 */
public interface TtlvBuilder {

	/**
	 * Returns the binary representation of the message.
	 *
	 * @since 1.0
	 */
	byte[] build();

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putBlob(short tag, byte[] value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putBoolean(short tag, Boolean value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putByte(short tag, Byte value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putCharacter(short tag, Character value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putDouble(short tag, Double value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putFloat(short tag, Float value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putInteger(short tag, Integer value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putLong(short tag, Long value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putShort(short tag, Short value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putUtf8String(short tag, String value);

	/**
	 * Associates the given value with the given tag in the message.
	 *
	 * @since 1.0
	 */
	TtlvBuilder putUtf16String(short tag, String value);

}
