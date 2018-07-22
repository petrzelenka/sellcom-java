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
package org.sellcom.core.internal.io.encoding;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.nio.ByteBuffer;

import org.junit.Test;
import org.sellcom.core.io.encoding.BinaryDecoder;
import org.sellcom.core.io.encoding.BinaryEncoder;
import org.sellcom.core.io.encoding.StandardBinaryEncodings;

public class Base16EncodingTest {

	private static byte[] LINE_0_BASE_16_BYTES = { (byte) '0', (byte) '0', (byte) '0', (byte) '1', (byte) '0', (byte) '2', (byte) '0', (byte) '3', (byte) '0', (byte) '4', (byte) '0', (byte) '5', (byte) '0', (byte) '6', (byte) '0', (byte) '7', (byte) '0', (byte) '8', (byte) '0', (byte) '9', (byte) '0', (byte) 'A', (byte) '0', (byte) 'B', (byte) '0', (byte) 'C', (byte) '0', (byte) 'D', (byte) '0', (byte) 'E', (byte) '0', (byte) 'F' };

	private static byte[] LINE_0_BYTES = { (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08, (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C, (byte) 0x0D, (byte) 0x0E, (byte) 0x0F };

	private static byte[] LINE_1_BASE_16_BYTES = { (byte) '1', (byte) '0', (byte) '1', (byte) '1', (byte) '1', (byte) '2', (byte) '1', (byte) '3', (byte) '1', (byte) '4', (byte) '1', (byte) '5', (byte) '1', (byte) '6', (byte) '1', (byte) '7', (byte) '1', (byte) '8', (byte) '1', (byte) '9', (byte) '1', (byte) 'A', (byte) '1', (byte) 'B', (byte) '1', (byte) 'C', (byte) '1', (byte) 'D', (byte) '1', (byte) 'E', (byte) '1', (byte) 'F' };

	private static byte[] LINE_1_BYTES = { (byte) 0x10, (byte) 0x11, (byte) 0x12, (byte) 0x13, (byte) 0x14, (byte) 0x15, (byte) 0x16, (byte) 0x17, (byte) 0x18, (byte) 0x19, (byte) 0x1A, (byte) 0x1B, (byte) 0x1C, (byte) 0x1D, (byte) 0x1E, (byte) 0x1F };

	private static byte[] LINE_2_BASE_16_BYTES = { (byte) '2', (byte) '0', (byte) '2', (byte) '1', (byte) '2', (byte) '2', (byte) '2', (byte) '3', (byte) '2', (byte) '4', (byte) '2', (byte) '5', (byte) '2', (byte) '6', (byte) '2', (byte) '7', (byte) '2', (byte) '8', (byte) '2', (byte) '9', (byte) '2', (byte) 'A', (byte) '2', (byte) 'B', (byte) '2', (byte) 'C', (byte) '2', (byte) 'D', (byte) '2', (byte) 'E', (byte) '2', (byte) 'F' };

	private static byte[] LINE_2_BYTES = { (byte) 0x20, (byte) 0x21, (byte) 0x22, (byte) 0x23, (byte) 0x24, (byte) 0x25, (byte) 0x26, (byte) 0x27, (byte) 0x28, (byte) 0x29, (byte) 0x2A, (byte) 0x2B, (byte) 0x2C, (byte) 0x2D, (byte) 0x2E, (byte) 0x2F };

	private static byte[] LINE_3_BASE_16_BYTES = { (byte) '3', (byte) '0', (byte) '3', (byte) '1', (byte) '3', (byte) '2', (byte) '3', (byte) '3', (byte) '3', (byte) '4', (byte) '3', (byte) '5', (byte) '3', (byte) '6', (byte) '3', (byte) '7', (byte) '3', (byte) '8', (byte) '3', (byte) '9', (byte) '3', (byte) 'A', (byte) '3', (byte) 'B', (byte) '3', (byte) 'C', (byte) '3', (byte) 'D', (byte) '3', (byte) 'E', (byte) '3', (byte) 'F' };

	private static byte[] LINE_3_BYTES = { (byte) 0x30, (byte) 0x31, (byte) 0x32, (byte) 0x33, (byte) 0x34, (byte) 0x35, (byte) 0x36, (byte) 0x37, (byte) 0x38, (byte) 0x39, (byte) 0x3A, (byte) 0x3B, (byte) 0x3C, (byte) 0x3D, (byte) 0x3E, (byte) 0x3F };

	private static byte[] LINE_4_BASE_16_BYTES = { (byte) '4', (byte) '0', (byte) '4', (byte) '1', (byte) '4', (byte) '2', (byte) '4', (byte) '3', (byte) '4', (byte) '4', (byte) '4', (byte) '5', (byte) '4', (byte) '6', (byte) '4', (byte) '7', (byte) '4', (byte) '8', (byte) '4', (byte) '9', (byte) '4', (byte) 'A', (byte) '4', (byte) 'B', (byte) '4', (byte) 'C', (byte) '4', (byte) 'D', (byte) '4', (byte) 'E', (byte) '4', (byte) 'F' };

	private static byte[] LINE_4_BYTES = { (byte) 0x40, (byte) 0x41, (byte) 0x42, (byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46, (byte) 0x47, (byte) 0x48, (byte) 0x49, (byte) 0x4A, (byte) 0x4B, (byte) 0x4C, (byte) 0x4D, (byte) 0x4E, (byte) 0x4F };

	private static byte[] LINE_5_BASE_16_BYTES = { (byte) '5', (byte) '0', (byte) '5', (byte) '1', (byte) '5', (byte) '2', (byte) '5', (byte) '3', (byte) '5', (byte) '4', (byte) '5', (byte) '5', (byte) '5', (byte) '6', (byte) '5', (byte) '7', (byte) '5', (byte) '8', (byte) '5', (byte) '9', (byte) '5', (byte) 'A', (byte) '5', (byte) 'B', (byte) '5', (byte) 'C', (byte) '5', (byte) 'D', (byte) '5', (byte) 'E', (byte) '5', (byte) 'F' };

	private static byte[] LINE_5_BYTES = { (byte) 0x50, (byte) 0x51, (byte) 0x52, (byte) 0x53, (byte) 0x54, (byte) 0x55, (byte) 0x56, (byte) 0x57, (byte) 0x58, (byte) 0x59, (byte) 0x5A, (byte) 0x5B, (byte) 0x5C, (byte) 0x5D, (byte) 0x5E, (byte) 0x5F };

	private static byte[] LINE_6_BASE_16_BYTES = { (byte) '6', (byte) '0', (byte) '6', (byte) '1', (byte) '6', (byte) '2', (byte) '6', (byte) '3', (byte) '6', (byte) '4', (byte) '6', (byte) '5', (byte) '6', (byte) '6', (byte) '6', (byte) '7', (byte) '6', (byte) '8', (byte) '6', (byte) '9', (byte) '6', (byte) 'A', (byte) '6', (byte) 'B', (byte) '6', (byte) 'C', (byte) '6', (byte) 'D', (byte) '6', (byte) 'E', (byte) '6', (byte) 'F' };

	private static byte[] LINE_6_BYTES = { (byte) 0x60, (byte) 0x61, (byte) 0x62, (byte) 0x63, (byte) 0x64, (byte) 0x65, (byte) 0x66, (byte) 0x67, (byte) 0x68, (byte) 0x69, (byte) 0x6A, (byte) 0x6B, (byte) 0x6C, (byte) 0x6D, (byte) 0x6E, (byte) 0x6F };

	private static byte[] LINE_7_BASE_16_BYTES = { (byte) '7', (byte) '0', (byte) '7', (byte) '1', (byte) '7', (byte) '2', (byte) '7', (byte) '3', (byte) '7', (byte) '4', (byte) '7', (byte) '5', (byte) '7', (byte) '6', (byte) '7', (byte) '7', (byte) '7', (byte) '8', (byte) '7', (byte) '9', (byte) '7', (byte) 'A', (byte) '7', (byte) 'B', (byte) '7', (byte) 'C', (byte) '7', (byte) 'D', (byte) '7', (byte) 'E', (byte) '7', (byte) 'F' };

	private static byte[] LINE_7_BYTES = { (byte) 0x70, (byte) 0x71, (byte) 0x72, (byte) 0x73, (byte) 0x74, (byte) 0x75, (byte) 0x76, (byte) 0x77, (byte) 0x78, (byte) 0x79, (byte) 0x7A, (byte) 0x7B, (byte) 0x7C, (byte) 0x7D, (byte) 0x7E, (byte) 0x7F };

	private static byte[] LINE_8_BASE_16_BYTES = { (byte) '8', (byte) '0', (byte) '8', (byte) '1', (byte) '8', (byte) '2', (byte) '8', (byte) '3', (byte) '8', (byte) '4', (byte) '8', (byte) '5', (byte) '8', (byte) '6', (byte) '8', (byte) '7', (byte) '8', (byte) '8', (byte) '8', (byte) '9', (byte) '8', (byte) 'A', (byte) '8', (byte) 'B', (byte) '8', (byte) 'C', (byte) '8', (byte) 'D', (byte) '8', (byte) 'E', (byte) '8', (byte) 'F' };

	private static byte[] LINE_8_BYTES = { (byte) 0x80, (byte) 0x81, (byte) 0x82, (byte) 0x83, (byte) 0x84, (byte) 0x85, (byte) 0x86, (byte) 0x87, (byte) 0x88, (byte) 0x89, (byte) 0x8A, (byte) 0x8B, (byte) 0x8C, (byte) 0x8D, (byte) 0x8E, (byte) 0x8F };

	private static byte[] LINE_9_BASE_16_BYTES = { (byte) '9', (byte) '0', (byte) '9', (byte) '1', (byte) '9', (byte) '2', (byte) '9', (byte) '3', (byte) '9', (byte) '4', (byte) '9', (byte) '5', (byte) '9', (byte) '6', (byte) '9', (byte) '7', (byte) '9', (byte) '8', (byte) '9', (byte) '9', (byte) '9', (byte) 'A', (byte) '9', (byte) 'B', (byte) '9', (byte) 'C', (byte) '9', (byte) 'D', (byte) '9', (byte) 'E', (byte) '9', (byte) 'F' };

	private static byte[] LINE_9_BYTES = { (byte) 0x90, (byte) 0x91, (byte) 0x92, (byte) 0x93, (byte) 0x94, (byte) 0x95, (byte) 0x96, (byte) 0x97, (byte) 0x98, (byte) 0x99, (byte) 0x9A, (byte) 0x9B, (byte) 0x9C, (byte) 0x9D, (byte) 0x9E, (byte) 0x9F };

	private static byte[] LINE_A_BASE_16_BYTES = { (byte) 'A', (byte) '0', (byte) 'A', (byte) '1', (byte) 'A', (byte) '2', (byte) 'A', (byte) '3', (byte) 'A', (byte) '4', (byte) 'A', (byte) '5', (byte) 'A', (byte) '6', (byte) 'A', (byte) '7', (byte) 'A', (byte) '8', (byte) 'A', (byte) '9', (byte) 'A', (byte) 'A', (byte) 'A', (byte) 'B', (byte) 'A', (byte) 'C', (byte) 'A', (byte) 'D', (byte) 'A', (byte) 'E', (byte) 'A', (byte) 'F' };

	private static byte[] LINE_A_BYTES = { (byte) 0xA0, (byte) 0xA1, (byte) 0xA2, (byte) 0xA3, (byte) 0xA4, (byte) 0xA5, (byte) 0xA6, (byte) 0xA7, (byte) 0xA8, (byte) 0xA9, (byte) 0xAA, (byte) 0xAB, (byte) 0xAC, (byte) 0xAD, (byte) 0xAE, (byte) 0xAF };

	private static byte[] LINE_B_BASE_16_BYTES = { (byte) 'B', (byte) '0', (byte) 'B', (byte) '1', (byte) 'B', (byte) '2', (byte) 'B', (byte) '3', (byte) 'B', (byte) '4', (byte) 'B', (byte) '5', (byte) 'B', (byte) '6', (byte) 'B', (byte) '7', (byte) 'B', (byte) '8', (byte) 'B', (byte) '9', (byte) 'B', (byte) 'A', (byte) 'B', (byte) 'B', (byte) 'B', (byte) 'C', (byte) 'B', (byte) 'D', (byte) 'B', (byte) 'E', (byte) 'B', (byte) 'F' };

	private static byte[] LINE_B_BYTES = { (byte) 0xB0, (byte) 0xB1, (byte) 0xB2, (byte) 0xB3, (byte) 0xB4, (byte) 0xB5, (byte) 0xB6, (byte) 0xB7, (byte) 0xB8, (byte) 0xB9, (byte) 0xBA, (byte) 0xBB, (byte) 0xBC, (byte) 0xBD, (byte) 0xBE, (byte) 0xBF };

	private static byte[] LINE_C_BASE_16_BYTES = { (byte) 'C', (byte) '0', (byte) 'C', (byte) '1', (byte) 'C', (byte) '2', (byte) 'C', (byte) '3', (byte) 'C', (byte) '4', (byte) 'C', (byte) '5', (byte) 'C', (byte) '6', (byte) 'C', (byte) '7', (byte) 'C', (byte) '8', (byte) 'C', (byte) '9', (byte) 'C', (byte) 'A', (byte) 'C', (byte) 'B', (byte) 'C', (byte) 'C', (byte) 'C', (byte) 'D', (byte) 'C', (byte) 'E', (byte) 'C', (byte) 'F' };

	private static byte[] LINE_C_BYTES = { (byte) 0xC0, (byte) 0xC1, (byte) 0xC2, (byte) 0xC3, (byte) 0xC4, (byte) 0xC5, (byte) 0xC6, (byte) 0xC7, (byte) 0xC8, (byte) 0xC9, (byte) 0xCA, (byte) 0xCB, (byte) 0xCC, (byte) 0xCD, (byte) 0xCE, (byte) 0xCF };

	private static byte[] LINE_D_BASE_16_BYTES = { (byte) 'D', (byte) '0', (byte) 'D', (byte) '1', (byte) 'D', (byte) '2', (byte) 'D', (byte) '3', (byte) 'D', (byte) '4', (byte) 'D', (byte) '5', (byte) 'D', (byte) '6', (byte) 'D', (byte) '7', (byte) 'D', (byte) '8', (byte) 'D', (byte) '9', (byte) 'D', (byte) 'A', (byte) 'D', (byte) 'B', (byte) 'D', (byte) 'C', (byte) 'D', (byte) 'D', (byte) 'D', (byte) 'E', (byte) 'D', (byte) 'F' };

	private static byte[] LINE_D_BYTES = { (byte) 0xD0, (byte) 0xD1, (byte) 0xD2, (byte) 0xD3, (byte) 0xD4, (byte) 0xD5, (byte) 0xD6, (byte) 0xD7, (byte) 0xD8, (byte) 0xD9, (byte) 0xDA, (byte) 0xDB, (byte) 0xDC, (byte) 0xDD, (byte) 0xDE, (byte) 0xDF };

	private static byte[] LINE_E_BASE_16_BYTES = { (byte) 'E', (byte) '0', (byte) 'E', (byte) '1', (byte) 'E', (byte) '2', (byte) 'E', (byte) '3', (byte) 'E', (byte) '4', (byte) 'E', (byte) '5', (byte) 'E', (byte) '6', (byte) 'E', (byte) '7', (byte) 'E', (byte) '8', (byte) 'E', (byte) '9', (byte) 'E', (byte) 'A', (byte) 'E', (byte) 'B', (byte) 'E', (byte) 'C', (byte) 'E', (byte) 'D', (byte) 'E', (byte) 'E', (byte) 'E', (byte) 'F' };

	private static byte[] LINE_E_BYTES = { (byte) 0xE0, (byte) 0xE1, (byte) 0xE2, (byte) 0xE3, (byte) 0xE4, (byte) 0xE5, (byte) 0xE6, (byte) 0xE7, (byte) 0xE8, (byte) 0xE9, (byte) 0xEA, (byte) 0xEB, (byte) 0xEC, (byte) 0xED, (byte) 0xEE, (byte) 0xEF };

	private static byte[] LINE_F_BASE_16_BYTES = { (byte) 'F', (byte) '0', (byte) 'F', (byte) '1', (byte) 'F', (byte) '2', (byte) 'F', (byte) '3', (byte) 'F', (byte) '4', (byte) 'F', (byte) '5', (byte) 'F', (byte) '6', (byte) 'F', (byte) '7', (byte) 'F', (byte) '8', (byte) 'F', (byte) '9', (byte) 'F', (byte) 'A', (byte) 'F', (byte) 'B', (byte) 'F', (byte) 'C', (byte) 'F', (byte) 'D', (byte) 'F', (byte) 'E', (byte) 'F', (byte) 'F' };

	private static byte[] LINE_F_BYTES = { (byte) 0xF0, (byte) 0xF1, (byte) 0xF2, (byte) 0xF3, (byte) 0xF4, (byte) 0xF5, (byte) 0xF6, (byte) 0xF7, (byte) 0xF8, (byte) 0xF9, (byte) 0xFA, (byte) 0xFB, (byte) 0xFC, (byte) 0xFD, (byte) 0xFE, (byte) 0xFF };


	@Test
	public void testDecoder() {
		BinaryDecoder decoder = StandardBinaryEncodings.createBase16Decoder();

		testByteDecoder(decoder, LINE_0_BASE_16_BYTES, LINE_0_BYTES);
		testByteDecoder(decoder, LINE_1_BASE_16_BYTES, LINE_1_BYTES);
		testByteDecoder(decoder, LINE_2_BASE_16_BYTES, LINE_2_BYTES);
		testByteDecoder(decoder, LINE_3_BASE_16_BYTES, LINE_3_BYTES);
		testByteDecoder(decoder, LINE_4_BASE_16_BYTES, LINE_4_BYTES);
		testByteDecoder(decoder, LINE_5_BASE_16_BYTES, LINE_5_BYTES);
		testByteDecoder(decoder, LINE_6_BASE_16_BYTES, LINE_6_BYTES);
		testByteDecoder(decoder, LINE_7_BASE_16_BYTES, LINE_7_BYTES);
		testByteDecoder(decoder, LINE_8_BASE_16_BYTES, LINE_8_BYTES);
		testByteDecoder(decoder, LINE_9_BASE_16_BYTES, LINE_9_BYTES);
		testByteDecoder(decoder, LINE_A_BASE_16_BYTES, LINE_A_BYTES);
		testByteDecoder(decoder, LINE_B_BASE_16_BYTES, LINE_B_BYTES);
		testByteDecoder(decoder, LINE_C_BASE_16_BYTES, LINE_C_BYTES);
		testByteDecoder(decoder, LINE_D_BASE_16_BYTES, LINE_D_BYTES);
		testByteDecoder(decoder, LINE_E_BASE_16_BYTES, LINE_E_BYTES);
		testByteDecoder(decoder, LINE_F_BASE_16_BYTES, LINE_F_BYTES);
	}

	@Test
	public void testEncoder() {
		BinaryEncoder encoder = StandardBinaryEncodings.createBase16Encoder();

		testByteEncoder(encoder, LINE_0_BYTES, LINE_0_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_1_BYTES, LINE_1_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_2_BYTES, LINE_2_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_3_BYTES, LINE_3_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_4_BYTES, LINE_4_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_5_BYTES, LINE_5_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_6_BYTES, LINE_6_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_7_BYTES, LINE_7_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_8_BYTES, LINE_8_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_9_BYTES, LINE_9_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_A_BYTES, LINE_A_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_B_BYTES, LINE_B_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_C_BYTES, LINE_C_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_D_BYTES, LINE_D_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_E_BYTES, LINE_E_BASE_16_BYTES);
		testByteEncoder(encoder, LINE_F_BYTES, LINE_F_BASE_16_BYTES);
	}


	private void testByteDecoder(BinaryDecoder decoder, byte[] input, byte[] expectedResult) {
		assertThat(decoder.decode(input), is(equalTo(expectedResult)));
		assertThat(decoder.decode(ByteBuffer.wrap(input)), is(equalTo(ByteBuffer.wrap(expectedResult))));
		assertThat(decoder.decode(new String(input, US_ASCII)), is(equalTo(expectedResult)));
	}

	private void testByteEncoder(BinaryEncoder encoder, byte[] input, byte[] expectedResult) {
		assertThat(encoder.encode(input), is(equalTo(expectedResult)));
		assertThat(encoder.encode(ByteBuffer.wrap(input)), is(equalTo(ByteBuffer.wrap(expectedResult))));
		assertThat(encoder.encodeToString(input), is(equalTo(new String(expectedResult, US_ASCII))));
	}

}
