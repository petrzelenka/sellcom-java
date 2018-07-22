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

import org.sellcom.core.internal.io.encoding.Base16Encoding;
import org.sellcom.core.internal.io.encoding.Base58BitcoinEncoding;
import org.sellcom.core.internal.io.encoding.Base58FlickrEncoding;
import org.sellcom.core.internal.io.encoding.Base64Encoding;
import org.sellcom.core.internal.io.encoding.Base64UrlEncoding;

/**
 * Standard encoding schemes for binary data.
 *
 * @since 1.0
 */
public class StandardBinaryEncodings {

	/**
	 * Base-16 encoding scheme.
	 *
	 * @since 1.0
	 */
	public static final BinaryEncoding BASE_16 = new Base16Encoding();

	/**
	 * Base-58 (Bitcoin alphabet) encoding scheme.
	 *
	 * @since 1.0
	 */
	public static final BinaryEncoding BASE_58_BITCOIN = new Base58BitcoinEncoding();

	/**
	 * Base-58 (Flickr alphabet) encoding scheme.
	 *
	 * @since 1.0
	 */
	public static final BinaryEncoding BASE_58_FLICKR = new Base58FlickrEncoding();

	/**
	 * Base-64 encoding scheme.
	 *
	 * @since 1.0
	 */
	public static final BinaryEncoding BASE_64 = new Base64Encoding();

	/**
	 * Base-64 URL and filename safe encoding scheme.
	 *
	 * @since 1.0
	 */
	public static final BinaryEncoding BASE_64_URL = new Base64UrlEncoding();


	private StandardBinaryEncodings() {
		// Namespace for constants, not to be instantiated
	}


	/**
	 * Returns a decoder for Base-16 encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryDecoder createBase16Decoder() {
		return BASE_16.newDecoder();
	}

	/**
	 * Returns an encoder for Base-16 encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryEncoder createBase16Encoder() {
		return BASE_16.newEncoder();
	}

	/**
	 * Returns a decoder for Base-58 (Bitcoin alphabet) encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryDecoder createBase58BitcoinDecoder() {
		return BASE_58_BITCOIN.newDecoder();
	}

	/**
	 * Returns an encoder for Base-58 (Bitcoin alphabet) encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryEncoder createBase58BitcoinEncoder() {
		return BASE_58_BITCOIN.newEncoder();
	}

	/**
	 * Returns a decoder for Base-58 (Flickr alphabet) encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryDecoder createBase58FlickrDecoder() {
		return BASE_58_FLICKR.newDecoder();
	}

	/**
	 * Returns an encoder for Base-58 (Flickr alphabet) encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryEncoder createBase58FlickrEncoder() {
		return BASE_58_FLICKR.newEncoder();
	}

	/**
	 * Returns a decoder for Base-64 encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryDecoder createBase64Decoder() {
		return BASE_64.newDecoder();
	}

	/**
	 * Returns an encoder for Base-64 encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryEncoder createBase64Encoder() {
		return BASE_64.newEncoder();
	}

	/**
	 * Returns a decoder for Base-64 URL and filename safe encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryDecoder createBase64UrlDecoder() {
		return BASE_64_URL.newDecoder();
	}

	/**
	 * Returns an encoder for Base-64 URL and filename safe encoding scheme.
	 *
	 * @since 1.0
	 */
	public static BinaryEncoder createBase64UrlEncoder() {
		return BASE_64_URL.newEncoder();
	}

}
