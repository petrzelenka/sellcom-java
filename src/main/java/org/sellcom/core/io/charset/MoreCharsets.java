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
package org.sellcom.core.io.charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * More standard {@link Charset}s.
 * <p>
 * These charsets are not guaranteed to be available on every implementation of the Java platform, but are either available in the Oracle Java platform or provided by this library.
 *
 * @see StandardCharsets
 *
 * @since 1.0
 */
public class MoreCharsets {

	/**
	 * Big5.
	 * <p>
	 * Contains characters for Traditional Chinese.
	 *
	 * @since 1.0
	 */
	public static final Charset BIG5 = Charset.forName("Big5");

	/**
	 * Big5 with Hong Kong extensions.
	 * <p>
	 * Contains characters for Traditional Chinese.
	 *
	 * @since 1.0
	 */
	public static final Charset BIG5_HKSCS = Charset.forName("Big5-HKSCS");

	/**
	 * EUC-JP.
	 * <p>
	 * Contains characters for Japanese.
	 *
	 * @since 1.0
	 */
	public static final Charset EUC_JP = Charset.forName("EUC-JP");

	/**
	 * EUC-JP.
	 * <p>
	 * Contains characters for Korean.
	 *
	 * @since 1.0
	 */
	public static final Charset EUC_KR = Charset.forName("EUC-KR");

	/**
	 * GB 2312, also known as EUC-CN.
	 * <p>
	 * Contains characters for Simplified Chinese.
	 *
	 * @since 1.0
	 */
	public static final Charset GB2312 = Charset.forName("GB2312");

	/**
	 * GB 18030.
	 * <p>
	 * Contains characters for Simplified Chinese.
	 *
	 * @since 1.0
	 */
	public static final Charset GB18030 = Charset.forName("GB18030");

	/**
	 * GBK.
	 * <p>
	 * Contains characters for Simplified Chinese.
	 *
	 * @since 1.0
	 */
	public static final Charset GBK = Charset.forName("GBK");

	/**
	 * ISCII91.
	 * <p>
	 * Contains characters for Hindi.
	 *
	 * @since 1.0
	 */
	public static final Charset ISCII91 = Charset.forName("X-ISCII91");

	/**
	 * ISO-2022-CN.
	 * <p>
	 * Contains characters for Simplified and Traditional Chinese.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_2022_CN = Charset.forName("ISO-2022-CN");

	/**
	 * ISO-2022-JP.
	 * <p>
	 * Contains characters for Japanese.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_2022_JP = Charset.forName("ISO-2022-JP");

	/**
	 * ISO-2022-KR.
	 * <p>
	 * Contains characters for Korean.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_2022_KR = Charset.forName("ISO-2022-KR");

	/**
	 * ISO/IEC 8859-2, also known as ISO Latin-2 or Windows-28592.
	 * <p>
	 * Contains characters for Central European languages.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_2 = Charset.forName("ISO-8859-2");

	/**
	 * ISO/IEC 8859-3, also known as ISO Latin-3 or Windows-28593.
	 * <p>
	 * Contains characters for South European languages.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_3 = Charset.forName("ISO-8859-3");

	/**
	 * ISO/IEC 8859-4, also known as ISO Latin-4 or Windows-28594.
	 * <p>
	 * Contains characters for North European languages.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_4 = Charset.forName("ISO-8859-4");

	/**
	 * ISO/IEC 8859-5, also known as ISO Latin/Cyrillic or Windows-28595.
	 * <p>
	 * Contains characters for East European languages using Cyrillic script.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_5 = Charset.forName("ISO-8859-5");

	/**
	 * ISO/IEC 8859-6, also known as ISO Latin/Arabic or Windows-28596.
	 * <p>
	 * Contains characters for modern Arabic.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_6 = Charset.forName("ISO-8859-6");

	/**
	 * ISO/IEC 8859-7, also known as ISO Latin/Greek or Windows-28597.
	 * <p>
	 * Contains characters for modern Greek.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_7 = Charset.forName("ISO-8859-7");

	/**
	 * ISO/IEC 8859-8, also known as ISO Latin/Hebrew or Windows-28598.
	 * <p>
	 * Contains characters for modern Hebrew.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_8 = Charset.forName("ISO-8859-8");

	/**
	 * ISO/IEC 8859-9, also known as ISO Latin-5 or Windows-28599.
	 * <p>
	 * Contains characters for modern Turkish.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_9 = Charset.forName("ISO-8859-9");

	/**
	 * ISO/IEC 8859-10, also known as ISO Latin-6.
	 * <p>
	 * Contains characters for Nordic languages.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_10 = Charset.forName("ISO-8859-10");

	/**
	 * ISO/IEC 8859-11, also known as ISO Latin/Thai.
	 * <p>
	 * Contains characters for Thai.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_11 = Charset.forName("X-ISO-8859-11");

	/**
	 * ISO/IEC 8859-13, also known as ISO Latin-7 or Windows-28603.
	 * <p>
	 * Contains characters for Baltic languages.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_13 = Charset.forName("ISO-8859-13");

	/**
	 * ISO/IEC 8859-14, also known as ISO Latin-8.
	 * <p>
	 * Contains characters for Celtic languages.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_14 = Charset.forName("ISO-8859-14");

	/**
	 * ISO/IEC 8859-15, also known as ISO Latin-9 or Windows-28605.
	 * <p>
	 * Contains characters for West European languages.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_15 = Charset.forName("ISO-8859-15");

	/**
	 * ISO/IEC 8859-16, also known as ISO Latin-10.
	 * <p>
	 * Contains characters for South-East European languages.
	 *
	 * @since 1.0
	 */
	public static final Charset ISO_8859_16 = Charset.forName("ISO-8859-16");

	/**
	 * KOI8-R, also known as Windows-20866.
	 * <p>
	 * Contains characters for Russian.
	 *
	 * @since 1.0
	 */
	public static final Charset KOI8_R = Charset.forName("KOI8-R");

	/**
	 * KOI8-U, also known as Windows-21866.
	 * <p>
	 * Contains characters for Ukrainian.
	 *
	 * @since 1.0
	 */
	public static final Charset KOI8_U = Charset.forName("KOI8-U");

	/**
	 * Shift-JIS, also known as Windows-31J.
	 * <p>
	 * Contains characters for Japanese.
	 *
	 * @since 1.0
	 */
	public static final Charset SHIFT_JIS = Charset.forName("Shift_JIS");

	/**
	 * TIS-620.
	 * <p>
	 * Contains characters for Thai.
	 *
	 * @since 1.0
	 */
	public static final Charset TIS_620 = Charset.forName("TIS-620");

	/**
	 * UTF-8, also known as UCS-1, with a mandatory byte-order mark.
	 *
	 * @since 1.0
	 */
	public static final Charset UTF_8BOM = Charset.forName("UTF-8BOM");

	/**
	 * UTF-32, also known as UCS-4, with byte order identified by an optional byte-order mark.
	 *
	 * @since 1.0
	 */
	public static final Charset UTF_32 = Charset.forName("UTF-32");

	/**
	 * UTF-32, also known as UCS-4, with big-endian byte order.
	 *
	 * @since 1.0
	 */
	public static final Charset UTF_32BE = Charset.forName("UTF-32BE");

	/**
	 * UTF-32, also known as UCS-4, with little-endian byte order.
	 *
	 * @since 1.0
	 */
	public static final Charset UTF_32LE = Charset.forName("UTF-32LE");

	/**
	 * Windows-31J.
	 * <p>
	 * Contains characters for Japanese.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_31J = Charset.forName("Windows-31j");

	/**
	 * Windows-874.
	 * <p>
	 * Contains characters for Thai.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_874 = Charset.forName("x-windows-874");

	/**
	 * Windows-949.
	 * <p>
	 * Contains characters for Korean.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_949 = Charset.forName("x-windows-949");

	/**
	 * Windows-1250.
	 * <p>
	 * Contains characters for Central European and East European languages.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_1250 = Charset.forName("windows-1250");

	/**
	 * Windows-1251.
	 * <p>
	 * Contains characters for East European languages using Cyrillic script.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_1251 = Charset.forName("windows-1251");

	/**
	 * Windows-1252.
	 * <p>
	 * Contains characters for West European languages.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_1252 = Charset.forName("windows-1252");

	/**
	 * Windows-1253.
	 * <p>
	 * Contains characters for modern Greek.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_1253 = Charset.forName("windows-1253");

	/**
	 * Windows-1254.
	 * <p>
	 * Contains characters for modern Turkish.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_1254 = Charset.forName("windows-1254");

	/**
	 * Windows-1255.
	 * <p>
	 * Contains characters for modern Hebrew.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_1255 = Charset.forName("windows-1255");

	/**
	 * Windows-1256.
	 * <p>
	 * Contains characters for modern Arabic.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_1256 = Charset.forName("windows-1256");

	/**
	 * Windows-1257.
	 * <p>
	 * Contains characters for Baltic languages.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_1257 = Charset.forName("windows-1257");

	/**
	 * Windows-1258.
	 * <p>
	 * Contains characters for Vietnamese.
	 *
	 * @since 1.0
	 */
	public static final Charset WINDOWS_1258 = Charset.forName("windows-1258");


	private MoreCharsets() {
		// Namespace for constants, not to be instantiated
	}

}
