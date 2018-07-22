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

import static org.sellcom.core.io.charset.MoreCharsets.ISO_8859_16;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.junit.Test;

public class ISO_8859_16_Test extends CharsetTest {

	private static byte[] LINE_A_BYTES = { (byte) 0xA0, (byte) 0xA1, (byte) 0xA2, (byte) 0xA3, (byte) 0xA4, (byte) 0xA5, (byte) 0xA6, (byte) 0xA7, (byte) 0xA8, (byte) 0xA9, (byte) 0xAA, (byte) 0xAB, (byte) 0xAC, (byte) 0xAD, (byte) 0xAE, (byte) 0xAF };

	private static char[] LINE_A_CHARS = { '\u00A0', 'Ą', 'ą', 'Ł', '€', '„', 'Š', '§', 'š', '©', 'Ș', '«', 'Ź', '\u00AD', 'ź', 'Ż' };

	private static byte[] LINE_B_BYTES = { (byte) 0xB0, (byte) 0xB1, (byte) 0xB2, (byte) 0xB3, (byte) 0xB4, (byte) 0xB5, (byte) 0xB6, (byte) 0xB7, (byte) 0xB8, (byte) 0xB9, (byte) 0xBA, (byte) 0xBB, (byte) 0xBC, (byte) 0xBD, (byte) 0xBE, (byte) 0xBF };

	private static char[] LINE_B_CHARS = { '°', '±', 'Č', 'ł', 'Ž', '”', '¶', '·', 'ž', 'č', 'ș', '»', 'Œ', 'œ', 'Ÿ', 'ż' };

	private static byte[] LINE_C_BYTES = { (byte) 0xC0, (byte) 0xC1, (byte) 0xC2, (byte) 0xC3, (byte) 0xC4, (byte) 0xC5, (byte) 0xC6, (byte) 0xC7, (byte) 0xC8, (byte) 0xC9, (byte) 0xCA, (byte) 0xCB, (byte) 0xCC, (byte) 0xCD, (byte) 0xCE, (byte) 0xCF };

	private static char[] LINE_C_CHARS = { 'À', 'Á', 'Â', 'Ă', 'Ä', 'Ć', 'Æ', 'Ç', 'È', 'É', 'Ê', 'Ë', 'Ì', 'Í', 'Î', 'Ï' };

	private static byte[] LINE_D_BYTES = { (byte) 0xD0, (byte) 0xD1, (byte) 0xD2, (byte) 0xD3, (byte) 0xD4, (byte) 0xD5, (byte) 0xD6, (byte) 0xD7, (byte) 0xD8, (byte) 0xD9, (byte) 0xDA, (byte) 0xDB, (byte) 0xDC, (byte) 0xDD, (byte) 0xDE, (byte) 0xDF };

	private static char[] LINE_D_CHARS = { 'Đ', 'Ń', 'Ò', 'Ó', 'Ô', 'Ő', 'Ö', 'Ś', 'Ű', 'Ù', 'Ú', 'Û', 'Ü', 'Ę', 'Ț', 'ß' };

	private static byte[] LINE_E_BYTES = { (byte) 0xE0, (byte) 0xE1, (byte) 0xE2, (byte) 0xE3, (byte) 0xE4, (byte) 0xE5, (byte) 0xE6, (byte) 0xE7, (byte) 0xE8, (byte) 0xE9, (byte) 0xEA, (byte) 0xEB, (byte) 0xEC, (byte) 0xED, (byte) 0xEE, (byte) 0xEF };

	private static char[] LINE_E_CHARS = { 'à', 'á', 'â', 'ă', 'ä', 'ć', 'æ', 'ç', 'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï' };

	private static byte[] LINE_F_BYTES = { (byte) 0xF0, (byte) 0xF1, (byte) 0xF2, (byte) 0xF3, (byte) 0xF4, (byte) 0xF5, (byte) 0xF6, (byte) 0xF7, (byte) 0xF8, (byte) 0xF9, (byte) 0xFA, (byte) 0xFB, (byte) 0xFC, (byte) 0xFD, (byte) 0xFE, (byte) 0xFF };

	private static char[] LINE_F_CHARS = { 'đ', 'ń', 'ò', 'ó', 'ô', 'ő', 'ö', 'ś', 'ű', 'ù', 'ú', 'û', 'ü', 'ę', 'ț', 'ÿ' };


	@Test
	public void testDecode() throws CharacterCodingException {
		CharsetDecoder decoder = ISO_8859_16.newDecoder();

		testCharsetDecoder(decoder, LINE_A_BYTES, LINE_A_CHARS);
		testCharsetDecoder(decoder, LINE_B_BYTES, LINE_B_CHARS);
		testCharsetDecoder(decoder, LINE_C_BYTES, LINE_C_CHARS);
		testCharsetDecoder(decoder, LINE_D_BYTES, LINE_D_CHARS);
		testCharsetDecoder(decoder, LINE_E_BYTES, LINE_E_CHARS);
		testCharsetDecoder(decoder, LINE_F_BYTES, LINE_F_CHARS);
	}

	@Test
	public void testEncode() throws CharacterCodingException {
		CharsetEncoder encoder = ISO_8859_16.newEncoder();

		testCharsetEncoder(encoder, LINE_A_CHARS, LINE_A_BYTES);
		testCharsetEncoder(encoder, LINE_B_CHARS, LINE_B_BYTES);
		testCharsetEncoder(encoder, LINE_C_CHARS, LINE_C_BYTES);
		testCharsetEncoder(encoder, LINE_D_CHARS, LINE_D_BYTES);
		testCharsetEncoder(encoder, LINE_E_CHARS, LINE_E_BYTES);
		testCharsetEncoder(encoder, LINE_F_CHARS, LINE_F_BYTES);
	}

}
