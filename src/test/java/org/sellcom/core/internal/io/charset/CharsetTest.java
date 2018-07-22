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
package org.sellcom.core.internal.io.charset;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

abstract class CharsetTest {

	protected void testCharsetDecoder(CharsetDecoder decoder, byte[] input, char[] expectedResult) throws CharacterCodingException {
		assertThat(decoder.decode(ByteBuffer.wrap(input)), is(equalTo(CharBuffer.wrap(expectedResult))));
	}

	protected void testCharsetEncoder(CharsetEncoder encoder, char[] input, byte[] expectedResult) throws CharacterCodingException {
		assertThat(encoder.encode(CharBuffer.wrap(input)), is(equalTo(ByteBuffer.wrap(expectedResult))));
	}

}
