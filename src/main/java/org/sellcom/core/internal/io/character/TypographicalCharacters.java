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
package org.sellcom.core.internal.io.character;

/**
 * Common typography-related characters.
 *
 * @since 1.3
 */
public class TypographicalCharacters {

	/**
	 * Em-width dash.
	 *
	 * @since 1.3
	 */
	public static final char EM_DASH = '\u2014';

	/**
	 * Em-width quad (MQSP).
	 *
	 * @since 1.3
	 */
	public static final char EM_QUAD = '\u2001';

	/**
	 * Em-width space (EMSP).
	 *
	 * @since 1.3
	 */
	public static final char EM_SPACE = '\u2003';

	/**
	 * En-width dash.
	 *
	 * @since 1.3
	 */
	public static final char EN_DASH = '\u2013';

	/**
	 * En-width quad (NQSP).
	 *
	 * @since 1.3
	 */
	public static final char EN_QUAD = '\u2000';

	/**
	 * En-width space (ENSP).
	 *
	 * @since 1.3
	 */
	public static final char EN_SPACE = '\u2002';

	/**
	 * Figure dash.
	 *
	 * @since 1.3
	 */
	public static final char FIGURE_DASH = '\u2012';

	/**
	 * Figure space (FSP).
	 *
	 * @since 1.3
	 */
	public static final char FIGURE_SPACE = '\u2007';

	/**
	 * First strong isolate (FSI).
	 *
	 * @since 1.3
	 */
	public static final char FIRST_STRONG_ISOLATE = '\u2068';

	/**
	 * Hair space (HSP).
	 *
	 * @since 1.3
	 */
	public static final char HAIR_SPACE = '\u200A';

	/**
	 * Hyphen.
	 *
	 * @since 1.3
	 */
	public static final char HYPHEN = '\u2010';

	/**
	 * Hyphenation point.
	 *
	 * @since 1.3
	 */
	public static final char HYPHENATION_POINT = '\u2027';

	/**
	 * Ideographic space.
	 *
	 * @since 1.3
	 */
	public static final char IDEOGRAPHIC_SPACE = '\u3000';

	/**
	 * Left-to-right embedding (LRE).
	 *
	 * @since 1.3
	 */
	public static final char LEFT_TO_RIGHT_EMBEDDING = '\u202A';

	/**
	 * Left-to-right isolate (LRI).
	 *
	 * @since 1.3
	 */
	public static final char LEFT_TO_RIGHT_ISOLATE = '\u2066';

	/**
	 * Left-to-right mark (LRM).
	 *
	 * @since 1.3
	 */
	public static final char LEFT_TO_RIGHT_MARK = '\u200E';

	/**
	 * Left-to-right override (LRO).
	 *
	 * @since 1.3
	 */
	public static final char LEFT_TO_RIGHT_OVERRIDE = '\u202D';

	/**
	 * Narrow non-breaking space (NNBSP).
	 *
	 * @since 1.3
	 */
	public static final char NARROW_NON_BREAKING_SPACE = '\u202F';

	/**
	 * Non-breaking hyphen.
	 *
	 * @since 1.3
	 */
	public static final char NON_BREAKING_HYPHEN = '\u2011';

	/**
	 * Non-breaking space (NBSP).
	 *
	 * @since 1.3
	 */
	public static final char NON_BREAKING_SPACE = '\u00A0';

	/**
	 * Pop directional formatting (PDF).
	 *
	 * @since 1.3
	 */
	public static final char POP_DIRECTIONAL_FORMATTING = '\u202C';

	/**
	 * Pop directional isolate (PDI).
	 *
	 * @since 1.3
	 */
	public static final char POP_DIRECTIONAL_ISOLATE = '\u2069';

	/**
	 * Punctuation space (PSP).
	 *
	 * @since 1.3
	 */
	public static final char PUNCTUATION_SPACE = '\u2008';

	/**
	 * Right-to-left embedding (RLE).
	 *
	 * @since 1.3
	 */
	public static final char RIGHT_TO_LEFT_EMBEDDING = '\u202B';

	/**
	 * Right-to-left isolate (RLI).
	 *
	 * @since 1.3
	 */
	public static final char RIGHT_TO_LEFT_ISOLATE = '\u2067';

	/**
	 * Right-to-left mark (RLM).
	 *
	 * @since 1.3
	 */
	public static final char RIGHT_TO_LEFT_MARK = '\u200F';

	/**
	 * Right-to-left override (RLO).
	 *
	 * @since 1.3
	 */
	public static final char RIGHT_TO_LEFT_OVERRIDE = '\u202E';

	/**
	 * Soft hyphen (SHY).
	 *
	 * @since 1.3
	 */
	public static final char SOFT_HYPHEN = '\u00AD';

	/**
	 * Thin space (THSP).
	 *
	 * @since 1.3
	 */
	public static final char THIN_SPACE = '\u2009';

	/**
	 * Word joiner (WJ).
	 *
	 * @since 1.3
	 */
	public static final char WORD_JOINER = '\u2060';

	/**
	 * Zero-width joiner (ZWJ).
	 *
	 * @since 1.3
	 */
	public static final char ZERO_WIDTH_JOINER = '\u200D';

	/**
	 * Zero-width non-breaking space (ZWNBSP).
	 *
	 * @since 1.3
	 */
	public static final char ZERO_WIDTH_NON_BREAKING_SPACE = '\uFEFF';

	/**
	 * Zero-width non-joiner (ZWNJ).
	 *
	 * @since 1.3
	 */
	public static final char ZERO_WIDTH_NON_JOINER = '\u200C';

	/**
	 * Zero-width space (ZWSP).
	 *
	 * @since 1.3
	 */
	public static final char ZERO_WIDTH_SPACE = '\u200B';


	private TypographicalCharacters() {
		// Namespace for constants, not to be instantiated
	}

}
