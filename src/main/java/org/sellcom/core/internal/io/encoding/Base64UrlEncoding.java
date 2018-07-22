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

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Base64;

import org.sellcom.core.Contract;
import org.sellcom.core.io.encoding.BinaryDecoder;
import org.sellcom.core.io.encoding.BinaryEncoder;
import org.sellcom.core.io.encoding.BinaryEncoding;

public final class Base64UrlEncoding extends BinaryEncoding {

	@Override
	public BinaryDecoder newDecoder() {
		return new Decoder();
	}

	@Override
	public BinaryEncoder newEncoder() {
		return new Encoder();
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class Decoder extends BinaryDecoder {

		private final Base64.Decoder decoder = Base64.getUrlDecoder();


		@Override
		public byte[] decode(byte[] input) {
			Contract.checkArgument(input != null, "Input must not be null");

			return decoder.decode(input);
		}

		@Override
		public ByteBuffer decode(ByteBuffer input) {
			Contract.checkArgument(input != null, "Input must not be null");

			return decoder.decode(input);
		}

		@Override
		public byte[] decode(String input) {
			Contract.checkArgument(input != null, "Input must not be null");

			return decoder.decode(input);
		}

		@Override
		public boolean supportsStreaming() {
			return true;
		}

		@Override
		public InputStream wrap(InputStream source) {
			Contract.checkArgument(source != null, "Source stream must not be null");

			return decoder.wrap(source);
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class Encoder extends BinaryEncoder {

		private final Base64.Encoder encoder = Base64.getUrlEncoder();


		@Override
		public byte[] encode(byte[] input) {
			Contract.checkArgument(input != null, "Input must not be null");

			return encoder.encode(input);
		}

		@Override
		public ByteBuffer encode(ByteBuffer input) {
			Contract.checkArgument(input != null, "Input must not be null");

			return encoder.encode(input);
		}

		@Override
		public String encodeToString(byte[] input) {
			Contract.checkArgument(input != null, "Input must not be null");

			return encoder.encodeToString(input);
		}

		@Override
		public boolean supportsStreaming() {
			return true;
		}

		@Override
		public OutputStream wrap(OutputStream destination) {
			Contract.checkArgument(destination != null, "Destination stream must not be null");

			return encoder.wrap(destination);
		}

	}

}
