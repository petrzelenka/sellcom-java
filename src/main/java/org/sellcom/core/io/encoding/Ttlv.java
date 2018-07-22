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

import static java.nio.charset.StandardCharsets.UTF_16BE;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;
import org.sellcom.core.io.Io;

/**
 * Operations with TTLV-encoded messages.
 *
 * @since 1.0
 */
public class Ttlv {

	private Ttlv() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns an empty builder of TTLV-encoded messages.
	 *
	 * @since 1.0
	 */
	public static TtlvBuilder create() {
		return new Builder();
	}

	/**
	 * Returns an extractor of data from the given TTLV-encoded messages.
	 *
	 * @throws IllegalArgumentException if {@code message} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TtlvExtractor parse(byte[] message) throws IOException {
		Contract.checkArgument(message != null, "Message must not be null");

		return parse(new ByteArrayInputStream(message));
	}

	/**
	 * Returns an extractor of data from the given TTLV-encoded messages.
	 *
	 * @throws IllegalArgumentException if {@code message} is {@code null}
	 *
	 * @since 1.0
	 */
	public static TtlvExtractor parse(byte[] message, int offset, int length) throws IOException {
		Contract.checkArgument(message != null, "Message must not be null");

		return parse(new ByteArrayInputStream(message, offset, length));
	}


	private static TtlvExtractor parse(ByteArrayInputStream source) throws IOException {
		Extractor extractor = new Extractor();

		while (source.available() > 0) {
			// Read tag
			short tag = Io.readShort(source);
			// Read type
			Type type = Type.fromValue(Io.readByte(source));
			// Read length
			int length = Io.readInt(source);

			// Read value
			if (length > 0) {
				extractor.entries.put(tag, type.read(source, length));
			} else {
				extractor.entries.put(tag, null);
			}
		}

		return extractor;
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class Builder implements TtlvBuilder {

		private final Map<Short, TypedValue> entries = new LinkedHashMap<>();


		@Override
		public byte[] build() {
			try {
				ByteArrayOutputStream target = new ByteArrayOutputStream();
				for (Map.Entry<Short, TypedValue> entry : entries.entrySet()) {
					// Write tag
					Io.writeShort(target, entry.getKey());
					// Write type, length and value
					entry.getValue().write(target);
				}

				return target.toByteArray();
			} catch (IOException e) {
				throw new AssertionError("ByteArrayOutputStream threw IOException");
			}
		}

		@Override
		public TtlvBuilder putBlob(short tag, byte[] value) {
			entries.put(tag, new TypedValue(Type.BLOB, value));

			return this;
		}

		@Override
		public TtlvBuilder putBoolean(short tag, Boolean value) {
			entries.put(tag, new TypedValue(Type.BOOLEAN, value));

			return this;
		}

		@Override
		public TtlvBuilder putByte(short tag, Byte value) {
			entries.put(tag, new TypedValue(Type.BYTE, value));

			return this;
		}

		@Override
		public TtlvBuilder putCharacter(short tag, Character value) {
			entries.put(tag, new TypedValue(Type.CHARACTER, value));

			return this;
		}

		@Override
		public TtlvBuilder putDouble(short tag, Double value) {
			entries.put(tag, new TypedValue(Type.DOUBLE, value));

			return this;
		}

		@Override
		public TtlvBuilder putFloat(short tag, Float value) {
			entries.put(tag, new TypedValue(Type.FLOAT, value));

			return this;
		}

		@Override
		public TtlvBuilder putInteger(short tag, Integer value) {
			entries.put(tag, new TypedValue(Type.INTEGER, value));

			return this;
		}

		@Override
		public TtlvBuilder putLong(short tag, Long value) {
			entries.put(tag, new TypedValue(Type.LONG, value));

			return this;
		}

		@Override
		public TtlvBuilder putShort(short tag, Short value) {
			entries.put(tag, new TypedValue(Type.SHORT, value));

			return this;
		}

		@Override
		public TtlvBuilder putUtf8String(short tag, String value) {
			entries.put(tag, new TypedValue(Type.STRING_UTF_8, value));

			return this;
		}

		@Override
		public TtlvBuilder putUtf16String(short tag, String value) {
			entries.put(tag, new TypedValue(Type.STRING_UTF_16BE, value));

			return this;
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class Extractor implements TtlvExtractor {

		private final Map<Short, Object> entries = new LinkedHashMap<>();


		@Override
		public byte[] getBlob(short tag) {
			return (byte[]) entries.get(tag);
		}

		@Override
		public Boolean getBoolean(short tag) {
			return (Boolean) entries.get(tag);
		}

		@Override
		public Byte getByte(short tag) {
			return (Byte) entries.get(tag);
		}

		@Override
		public Character getCharacter(short tag) {
			return (Character) entries.get(tag);
		}

		@Override
		public Double getDouble(short tag) {
			return (Double) entries.get(tag);
		}

		@Override
		public Float getFloat(short tag) {
			return (Float) entries.get(tag);
		}

		@Override
		public Integer getInteger(short tag) {
			return (Integer) entries.get(tag);
		}

		@Override
		public Long getLong(short tag) {
			return (Long) entries.get(tag);
		}

		@Override
		public Short getShort(short tag) {
			return (Short) entries.get(tag);
		}

		@Override
		public String getString(short tag) {
			return (String) entries.get(tag);
		}

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static enum Type {

		BLOB((byte) 0x01) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readBytes(source, length);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				byte[] value = (byte[]) genericValue;

				Io.writeByte(destination, this.value); // Write type

				if (value == null) {
					Io.writeInt(destination, 0); // Write length
				} else {
					Io.writeInt(destination, value.length); // Write length
					Io.writeBytes(destination, value); // Write value
				}
			}

		},

		BOOLEAN((byte) 0x08) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Boolean.valueOf(Io.readByte(source) != 0);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				Boolean value = (Boolean) genericValue;

				Io.writeByte(destination, this.value); // Write type

				if (value == null) {
					Io.writeInt(destination, 0); // Write length
				} else {
					Io.writeInt(destination, Byte.BYTES); // Write length
					Io.writeByte(destination, value ? (byte) 1 : (byte) 0); // Write value
				}
			}

		},

		BYTE((byte) 0x02) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readByte(source);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				Byte value = (Byte) genericValue;

				Io.writeByte(destination, this.value); // Write type

				if (value == null) {
					Io.writeInt(destination, 0); // Write length
				} else {
					Io.writeInt(destination, Byte.BYTES); // Write length
					Io.writeByte(destination, value); // Write value
				}
			}

		},

		CHARACTER((byte) 0x09) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readChar(source);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				Character value = (Character) genericValue;

				Io.writeByte(destination, this.value); // Write type

				if (value == null) {
					Io.writeInt(destination, 0); // Write length
				} else {
					Io.writeInt(destination, Character.BYTES); // Write length
					Io.writeChar(destination, value); // Write value
				}
			}

		},

		DOUBLE((byte) 0x07) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readDouble(source);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				Double value = (Double) genericValue;

				Io.writeByte(destination, this.value); // Write type

				if (value == null) {
					Io.writeInt(destination, 0); // Write length
				} else {
					Io.writeInt(destination, Double.BYTES); // Write length
					Io.writeDouble(destination, value); // Write value
				}
			}

		},

		FLOAT((byte) 0x06) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readFloat(source);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				Float value = (Float) genericValue;

				Io.writeByte(destination, this.value); // Write type

				if (value == null) {
					Io.writeInt(destination, 0); // Write length
				} else {
					Io.writeInt(destination, Float.BYTES); // Write length
					Io.writeFloat(destination, value); // Write value
				}
			}

		},

		INTEGER((byte) 0x04) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readInt(source);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				Integer value = (Integer) genericValue;

				Io.writeByte(destination, this.value); // Write type

				if (value == null) {
					Io.writeInt(destination, 0); // Write length
				} else {
					Io.writeInt(destination, Integer.BYTES); // Write length
					Io.writeInt(destination, value); // Write value
				}
			}

		},

		LONG((byte) 0x05) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readLong(source);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				Long value = (Long) genericValue;

				Io.writeByte(destination, this.value); // Write type

				if (value == null) {
					Io.writeInt(destination, 0); // Write length
				} else {
					Io.writeInt(destination, Long.BYTES); // Write length
					Io.writeLong(destination, value); // Write value
				}
			}

		},

		SHORT((byte) 0x03) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readShort(source);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				Short value = (Short) genericValue;

				Io.writeByte(destination, this.value); // Write type

				if (value == null) {
					Io.writeInt(destination, 0); // Write length
				} else {
					Io.writeInt(destination, Short.BYTES); // Write length
					Io.writeShort(destination, value); // Write value
				}
			}

		},

		STRING_UTF_8((byte) 0x0A) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readString(source, length, UTF_8);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				byte[] value = Strings.nullToEmpty((String) genericValue).getBytes(UTF_8);

				Io.writeByte(destination, this.value); // Write type
				Io.writeInt(destination, value.length); // Write length
				Io.writeBytes(destination, value); // Write value
			}

		},

		STRING_UTF_16BE((byte) 0x0B) {

			@Override
			Object read(InputStream source, int length) throws IOException {
				return (length == 0) ? null : Io.readString(source, length, UTF_16BE);
			}

			@Override
			void write(OutputStream destination, Object genericValue) throws IOException {
				byte[] value = Strings.nullToEmpty((String) genericValue).getBytes(UTF_8);

				Io.writeByte(destination, this.value); // Write type
				Io.writeInt(destination, value.length); // Write length
				Io.writeBytes(destination, value); // Write value
			}

		};

		// ------------------------------------------------------------
		// ------------------------------------------------------------
		// ------------------------------------------------------------

		private static final Map<Byte, Type> LOOKUP_TABLE = new HashMap<>();

		static {
			EnumSet.allOf(Type.class).stream()
				.forEach(type -> LOOKUP_TABLE.put(type.value, type));
		}

		protected byte value;


		private Type(byte value) {
			this.value = value;
		}


		static Type fromValue(byte value) {
			return LOOKUP_TABLE.get(value);
		}

		abstract Object read(InputStream source, int length) throws IOException;

		abstract void write(OutputStream destination, Object value) throws IOException;

	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class TypedValue {

		private final Type type;

		private final Object value;


		TypedValue(Type type, Object value) {
			this.type = type;
			this.value = value;
		}


		void write(OutputStream destination) throws IOException {
			type.write(destination, value);
		}

	}

}
