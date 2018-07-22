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

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

public class Utf8BomCharset extends Charset {

	private static final String[] ALIASES = { };

	private static final String CANONICAL_NAME = "UTF-8BOM";


	protected Utf8BomCharset() {
		super(CANONICAL_NAME, ALIASES);
	}


	@Override
	public boolean contains(Charset charset) {
		return UTF_8.contains(charset);
	}

	@Override
	public CharsetDecoder newDecoder() {
		return new Decoder(this, UTF_8.newDecoder());
	}

	@Override
	public CharsetEncoder newEncoder() {
		return new Encoder(this, UTF_8.newEncoder());
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class Decoder extends CharsetDecoder {

		private final CharsetDecoder charsetDecoder;

		private boolean needsByteOrderMark = true;


		protected Decoder(Charset charset, CharsetDecoder charsetDecoder) {
			super(charset, charsetDecoder.averageCharsPerByte(), charsetDecoder.maxCharsPerByte());

			this.charsetDecoder = charsetDecoder;
		}


		@Override
		protected CoderResult decodeLoop(ByteBuffer source, CharBuffer destination) {
			if (needsByteOrderMark) {
				if (source.remaining() < 3) {
					return CoderResult.UNDERFLOW;
				}

				if (source.get() != (byte) 0xEF) {
					source.position(source.position() - 1);

					return CoderResult.malformedForLength(3);
				}
				if (source.get() != (byte) 0xBB) {
					source.position(source.position() - 1);

					return CoderResult.malformedForLength(2);
				}
				if (source.get() != (byte) 0xBF) {
					source.position(source.position() - 1);

					return CoderResult.malformedForLength(1);
				}

				needsByteOrderMark = false;
			}

			return charsetDecoder.decode(source, destination, false);
		}

		@Override
		protected void implReset() {
			charsetDecoder.reset();

			needsByteOrderMark = true;
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class Encoder extends CharsetEncoder {

		private final CharsetEncoder charsetEncoder;

		private boolean needsByteOrderMark = true;


		protected Encoder(Charset charset, CharsetEncoder charsetEncoder) {
			super(charset, charsetEncoder.averageBytesPerChar(), charsetEncoder.maxBytesPerChar());

			this.charsetEncoder = charsetEncoder;
		}


		@Override
		protected CoderResult encodeLoop(CharBuffer source, ByteBuffer destination) {
			if (needsByteOrderMark) {
				if (destination.remaining() < 3) {
					return CoderResult.OVERFLOW;
				}

				destination.put((byte) 0xEF);
				destination.put((byte) 0xBB);
				destination.put((byte) 0xBF);

				needsByteOrderMark = false;
			}

			return charsetEncoder.encode(source, destination, false);
		}

		@Override
		protected void implReset() {
			charsetEncoder.reset();

			needsByteOrderMark = true;
		}

	}

}
