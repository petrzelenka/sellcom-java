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
package org.sellcom.core.io.type;

/**
 * Standard content types.
 *
 * @since 1.0
 */
public class StandardContentTypes {

	/**
	 * Content type that includes all media ranges.
	 *
	 * @since 1.0
	 */
	public static final String ALL = "*/*";

	/**
	 * Content type for simple HTML form data.
	 *
	 * @since 1.0
	 */
	public static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

	/**
	 * Content type for JavaScript source code.
	 *
	 * @since 1.0
	 */
	public static final String APPLICATION_JAVASCRIPT = "application/javascript";

	/**
	 * Content type for JavaScript object.
	 *
	 * @since 1.0
	 */
	public static final String APPLICATION_JSON = "application/json";

	/**
	 * Content type for generic binary object.
	 *
	 * @since 1.0
	 */
	public static final String APPLICATION_OCTET_STREAM = "application/octet-stream";

	/**
	 * Content type for PDF document.
	 *
	 * @since 1.0
	 */
	public static final String APPLICATION_PDF = "application/pdf";

	/**
	 * Content type for XML document.
	 *
	 * @since 1.0
	 */
	public static final String APPLICATION_XML = "application/xml";

	/**
	 * Content type for ZIP archive.
	 *
	 * @since 1.0
	 */
	public static final String APPLICATION_ZIP = "application/zip";

	/**
	 * Content type for AAC audio.
	 *
	 * @since 1.0
	 */
	public static final String AUDIO_AAC = "audio/aac";

	/**
	 * Content type for FLAC audio.
	 *
	 * @since 1.0
	 */
	public static final String AUDIO_FLAC = "audio/flac";

	/**
	 * Content type for MPEG-4 audio.
	 *
	 * @since 1.0
	 */
	public static final String AUDIO_MP4 = "audio/mp4";

	/**
	 * Content type for WebM audio.
	 *
	 * @since 1.0
	 */
	public static final String AUDIO_WEBM = "audio/webm";

	/**
	 * Content type for JPEG image.
	 *
	 * @since 1.0
	 */
	public static final String IMAGE_JPEG = "image/jpeg";

	/**
	 * Content type for PNG image.
	 *
	 * @since 1.0
	 */
	public static final String IMAGE_PNG = "image/png";

	/**
	 * Content type for SVG image.
	 *
	 * @since 1.0
	 */
	public static final String IMAGE_SVG_XML = "image/svg+xml";

	/**
	 * Content type for WebP image.
	 *
	 * @since 1.0
	 */
	public static final String IMAGE_WEBP = "image/webp";

	/**
	 * Content type for multi-part HTML form data.
	 *
	 * @since 1.0
	 */
	public static final String MULTIPART_FORM_DATA = "multipart/form-data";

	/**
	 * Content type for CSS style sheet.
	 *
	 * @since 1.0
	 */
	public static final String TEXT_CSS = "text/css";

	/**
	 * Content type for CSV document.
	 *
	 * @since 1.0
	 */
	public static final String TEXT_CSV = "text/csv";

	/**
	 * Content type for HTML document.
	 *
	 * @since 1.0
	 */
	public static final String TEXT_HTML = "text/html";

	/**
	 * Content type for plain text document.
	 *
	 * @since 1.0
	 */
	public static final String TEXT_PLAIN = "text/plain";

	/**
	 * Content type for vCard contact.
	 *
	 * @since 1.0
	 */
	public static final String TEXT_VCARD = "text/vcard";

	/**
	 * Content type for XML document.
	 *
	 * @since 1.0
	 */
	public static final String TEXT_XML = "text/xml";

	/**
	 * Content type for MPEG-4 video.
	 *
	 * @since 1.0
	 */
	public static final String VIDEO_MP4 = "video/mp4";

	/**
	 * Content type for WebM video.
	 *
	 * @since 1.0
	 */
	public static final String VIDEO_WEBM = "video/webm";


	private StandardContentTypes() {
		// Namespace for constants, not to be instantiated
	}

}
