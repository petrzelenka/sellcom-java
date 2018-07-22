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
package org.sellcom.core.internal.io.type;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;

import org.sellcom.core.Strings;
import org.sellcom.core.io.MorePaths;
import org.sellcom.core.io.type.StandardContentTypes;

public class ContentTypeDetector extends FileTypeDetector {

	@Override
	public String probeContentType(Path path) throws IOException {
		String normalizedExtension = Strings.toLowerCase(MorePaths.getFileExtension(path));
		switch (normalizedExtension) {
			case "css" : {
				return StandardContentTypes.TEXT_CSS;
			}
			case "csv" : {
				return StandardContentTypes.TEXT_CSV;
			}
			case "flac" : {
				return StandardContentTypes.AUDIO_FLAC;
			}
			case "htm" : {
				return StandardContentTypes.TEXT_HTML;
			}
			case "html" : {
				return StandardContentTypes.TEXT_HTML;
			}
			case "jpeg" : {
				return StandardContentTypes.IMAGE_JPEG;
			}
			case "jpg" : {
				return StandardContentTypes.IMAGE_JPEG;
			}
			case "js" : {
				return StandardContentTypes.APPLICATION_JAVASCRIPT;
			}
			case "json" : {
				return StandardContentTypes.APPLICATION_JSON;
			}
			case "m4a" : {
				return StandardContentTypes.AUDIO_MP4;
			}
			case "mp4" : {
				return StandardContentTypes.VIDEO_MP4;
			}
			case "pdf" : {
				return StandardContentTypes.APPLICATION_PDF;
			}
			case "png" : {
				return StandardContentTypes.IMAGE_PNG;
			}
			case "svg" : {
				return StandardContentTypes.IMAGE_SVG_XML;
			}
			case "txt" : {
				return StandardContentTypes.TEXT_PLAIN;
			}
			case "vcard" : {
				return StandardContentTypes.TEXT_VCARD;
			}
			case "vcf" : {
				return StandardContentTypes.TEXT_VCARD;
			}
			case "webm" : {
				return StandardContentTypes.VIDEO_WEBM;
			}
			case "webp" : {
				return StandardContentTypes.IMAGE_WEBP;
			}
			case "xml" : {
				return StandardContentTypes.TEXT_XML;
			}
			case "zip" : {
				return StandardContentTypes.APPLICATION_ZIP;
			}
			default : {
				return StandardContentTypes.APPLICATION_OCTET_STREAM;
			}
		}
	}

}
