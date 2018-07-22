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
 * Common control characters.
 *
 * @since 1.3
 */
public class ControlCharacters {

	/**
	 * Acknowledgement (ACK).
	 *
	 * @since 1.3
	 */
	public static final char ACKNOWLEDGEMENT = '\u0006';

	/**
	 * Application program command (APC).
	 *
	 * @since 1.3
	 */
	public static final char APPLICATION_PROGRAM_COMMAND = '\u009F';

	/**
	 * Backspace (BS).
	 *
	 * @since 1.3
	 */
	public static final char BACKSPACE = '\u0008';

	/**
	 * Bell (BEL).
	 *
	 * @since 1.3
	 */
	public static final char BELL = '\u0007';

	/**
	 * Break permitted here (BPH).
	 *
	 * @since 1.3
	 */
	public static final char BREAK_PERMITTED_HERE = '\u0082';

	/**
	 * Byte order mark (BOM).
	 *
	 * @since 1.3
	 */
	public static final char BYTE_ORDER_MARK = '\uFEFF';

	/**
	 * Cancel (CAN).
	 *
	 * @since 1.3
	 */
	public static final char CANCEL = '\u0018';

	/**
	 * Cancel character (CCH).
	 *
	 * @since 1.3
	 */
	public static final char CANCEL_CHARACTER = '\u0094';

	/**
	 * Carriage return (CR).
	 *
	 * @since 1.3
	 */
	public static final char CARRIAGE_RETURN = '\r';

	/**
	 * Control sequence introducer (CSI).
	 *
	 * @since 1.3
	 */
	public static final char CONTROL_SEQUENCE_INTRODUCER = '\u009B';

	/**
	 * Data link escape (DLE).
	 *
	 * @since 1.3
	 */
	public static final char DATA_LINK_ESCAPE = '\u0010';

	/**
	 * Delete (DEL).
	 *
	 * @since 1.3
	 */
	public static final char DELETE = '\u007F';

	/**
	 * Device control 1 (DC1).
	 *
	 * @since 1.3
	 */
	public static final char DEVICE_CONTROL_1 = '\u0011';

	/**
	 * Device control 2 (DC2).
	 *
	 * @since 1.3
	 */
	public static final char DEVICE_CONTROL_2 = '\u0012';

	/**
	 * Device control 3 (DC3).
	 *
	 * @since 1.3
	 */
	public static final char DEVICE_CONTROL_3 = '\u0013';

	/**
	 * Device control 4 (DC4).
	 *
	 * @since 1.3
	 */
	public static final char DEVICE_CONTROL_4 = '\u0014';

	/**
	 * Device control string (DCS).
	 *
	 * @since 1.3
	 */
	public static final char DEVICE_CONTROL_STRING = '\u0090';

	/**
	 * End of medium (EM).
	 *
	 * @since 1.3
	 */
	public static final char END_OF_MEDIUM = '\u0019';

	/**
	 * End of protected area (EPA).
	 *
	 * @since 1.3
	 */
	public static final char END_OF_PROTECTED_AREA = '\u0097';

	/**
	 * End of selected area (ESA).
	 *
	 * @since 1.3
	 */
	public static final char End_OF_SELECTED_AREA = '\u0087';

	/**
	 * End of text (ETX).
	 *
	 * @since 1.3
	 */
	public static final char END_OF_TEXT = '\u0003';

	/**
	 * End of transmission (EOT).
	 *
	 * @since 1.3
	 */
	public static final char END_OF_TRANSMISSION = '\u0004';

	/**
	 * End of transmission block (ETB).
	 *
	 * @since 1.3
	 */
	public static final char END_OF_TRANSMISSION_BLOCK = '\u0017';

	/**
	 * Enquiry (ENQ).
	 *
	 * @since 1.3
	 */
	public static final char ENQUIRY = '\u0005';

	/**
	 * Escape (ESC).
	 *
	 * @since 1.3
	 */
	public static final char ESCAPE = '\u001B';

	/**
	 * File separator (FS).
	 *
	 * @since 1.3
	 */
	public static final char FILE_SEPARATOR = '\u001C';

	/**
	 * Form feed (FF).
	 *
	 * @since 1.3
	 */
	public static final char FORM_FEED = '\u000C';

	/**
	 * Group separator (GS).
	 *
	 * @since 1.3
	 */
	public static final char GROUP_SEPARATOR = '\u001D';

	/**
	 * High octet preset (HOP).
	 *
	 * @since 1.3
	 */
	public static final char HIGH_OCTET_PRESET = '\u0081';

	/**
	 * Horizontal tab (HT).
	 *
	 * @since 1.3
	 */
	public static final char HORIZONTAL_TAB = '\u0009';

	/**
	 * Horizontal tabulation set (HTS).
	 *
	 * @since 1.3
	 */
	public static final char HORIZONTAL_TABULATION_SET = '\u0088';

	/**
	 * Horizontal tabulation with justification (HTJ).
	 *
	 * @since 1.3
	 */
	public static final char HORIZONTAL_TABULATION_WITH_JUSTIFICATION = '\u0089';

	/**
	 * Index (IND).
	 *
	 * @since 1.3
	 */
	public static final char INDEX = '\u0084';

	/**
	 * Line feed (LF).
	 *
	 * @since 1.3
	 */
	public static final char LINE_FEED = '\n';

	/**
	 * Line separator (LSEP).
	 *
	 * @since 1.3
	 */
	public static final char LINE_SEPARATOR = '\u2028';

	/**
	 * Line tabulation set (LTS).
	 *
	 * @since 1.3
	 */
	public static final char LINE_TABULATION_SET = '\u008A';

	/**
	 * Message waiting (MW).
	 *
	 * @since 1.3
	 */
	public static final char MESSAGE_WAITING = '\u0095';

	/**
	 * Negative acknowledgement (NAK).
	 *
	 * @since 1.3
	 */
	public static final char NEGATIVE_ACKNOWLEDGEMENT = '\u0015';

	/**
	 * Next line (NEL).
	 *
	 * @since 1.3
	 */
	public static final char NEXT_LINE = '\u0085';

	/**
	 * No break here (NBH).
	 *
	 * @since 1.3
	 */
	public static final char NO_BREAK_HERE = '\u0083';

	/**
	 * Null (NUL).
	 *
	 * @since 1.3
	 */
	public static final char NULL = '\u0000';

	/**
	 * Operating system command (OSC).
	 *
	 * @since 1.3
	 */
	public static final char OPERATING_SYSTEM_COMMAND = '\u009D';

	/**
	 * Padding (PAD).
	 *
	 * @since 1.3
	 */
	public static final char PADDING = '\u0080';

	/**
	 * Paragraph separator (PSEP).
	 *
	 * @since 1.3
	 */
	public static final char PARAGRAPH_SEPARATOR = '\u2029';

	/**
	 * Partial line down (PLD).
	 *
	 * @since 1.3
	 */
	public static final char PARTIAL_LINE_DOWN = '\u008B';

	/**
	 * Partial line up (PLU).
	 *
	 * @since 1.3
	 */
	public static final char PARTIAL_LINE_UP = '\u008C';

	/**
	 * Private message (PM).
	 *
	 * @since 1.3
	 */
	public static final char PRIVATE_MESSAGE = '\u009E';

	/**
	 * Record separator (RS).
	 *
	 * @since 1.3
	 */
	public static final char RECORD_SEPARATOR = '\u001E';

	/**
	 * Reverse index (RI).
	 *
	 * @since 1.3
	 */
	public static final char REVERSE_INDEX = '\u008D';

	/**
	 * Set transmit state (STS).
	 *
	 * @since 1.3
	 */
	public static final char SET_TRANSMIT_STATE = '\u0093';

	/**
	 * Shift in (SI).
	 *
	 * @since 1.3
	 */
	public static final char SHIFT_IN = '\u000F';

	/**
	 * Shift out (SO).
	 *
	 * @since 1.3
	 */
	public static final char SHIFT_OUT = '\u000E';

	/**
	 * Single character introducer (SCI).
	 *
	 * @since 1.3
	 */
	public static final char SINGLE_CHARACTER_INTRODUCER = '\u009A';

	/**
	 * Single graphic character introducer (SGCI).
	 *
	 * @since 1.3
	 */
	public static final char SINGLE_GRAPHIC_CHARACTER_INTRODUCER = '\u0099';

	/**
	 * Single-shift 2 (SS2).
	 *
	 * @since 1.3
	 */
	public static final char SINGLE_SHIFT_2 = '\u008E';

	/**
	 * Single-shift 3 (SS3).
	 *
	 * @since 1.3
	 */
	public static final char SINGLE_SHIFT_3 = '\u008F';

	/**
	 * Start of heading (SOH).
	 *
	 * @since 1.3
	 */
	public static final char START_OF_HEADING = '\u0001';

	/**
	 * Start of protected area (SPA).
	 *
	 * @since 1.3
	 */
	public static final char START_OF_PROTECTED_AREA = '\u0096';

	/**
	 * Start of selected area (SSA).
	 *
	 * @since 1.3
	 */
	public static final char START_OF_SELECTED_AREA = '\u0086';

	/**
	 * Start of string (SOS).
	 *
	 * @since 1.3
	 */
	public static final char START_OF_STRING = '\u0098';

	/**
	 * Start of text (STX).
	 *
	 * @since 1.3
	 */
	public static final char START_OF_TEXT = '\u0002';

	/**
	 * String terminator (ST).
	 *
	 * @since 1.3
	 */
	public static final char STRING_TERMINATOR = '\u009C';

	/**
	 * Substitute (SUB).
	 *
	 * @since 1.3
	 */
	public static final char SUBSTITUTE = '\u001A';

	/**
	 * Synchronous idle (SYN).
	 *
	 * @since 1.3
	 */
	public static final char SYNCHRONOUS_IDLE = '\u0016';

	/**
	 * Unit separator (US).
	 *
	 * @since 1.3
	 */
	public static final char UNIT_SEPARATOR = '\u001F';

	/**
	 * Vertical tab (VT).
	 *
	 * @since 1.3
	 */
	public static final char VERTICAL_TAB = '\u000B';


	private ControlCharacters() {
		// Namespace for constants, not to be instantiated
	}

}
