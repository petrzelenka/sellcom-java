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
package org.sellcom.core.io;

import static java.nio.ByteOrder.BIG_ENDIAN;
import static java.nio.ByteOrder.LITTLE_ENDIAN;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.UUID;

import org.sellcom.core.Contract;

/**
 * I/O operations.
 *
 * @since 1.0
 */
public class Io {

	private static int bufferSize = 0x2000; // 8 KB


	private Io() {
		// Utility class, not to be instantiated
	}


	/**
	 * Closes the given {@link Closeable}, ignoring any possible I/O exception.
	 *
	 * @since 1.0
	 */
	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				; // Ignore
			}
		}
	}

	/**
	 * Copies all bytes from the given source to the given destination.
	 * Returns the number of bytes copied.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static int copyBytes(InputStream source, OutputStream destination) throws IOException {
		Contract.checkArgument(source != null, "Source stream must not be null");
		Contract.checkArgument(destination != null, "Destination stream must not be null");

		int bytesCopied = 0;
		int bytesRead;
		byte[] readBuffer = new byte[bufferSize];

		while ((bytesRead = source.read(readBuffer)) != -1) {
			destination.write(readBuffer, 0, bytesRead);
			bytesCopied += bytesRead;
		}

		return bytesCopied;
	}

	/**
	 * Copies the given number of bytes from the given source to the given destination.
	 * Copies all bytes if the number of bytes is negative.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void copyBytes(InputStream source, OutputStream destination, int byteCount) throws IOException {
		Contract.checkArgument(source != null, "Source stream must not be null");
		Contract.checkArgument(destination != null, "Destination stream must not be null");

		// NOTE: This special case enables supporting conditions like in
		// ServletRequest.getContentLength(), where -1 indicates that the length
		// of the request body is not known in advance.
		if (byteCount < 0) {
			copyBytes(source, destination);

			return;
		}

		int bytesCopied = 0;
		int bytesRead;
		byte[] readBuffer = new byte[bufferSize];

		while (bytesCopied < byteCount) {
			if ((bytesRead = source.read(readBuffer, 0, Math.min(byteCount - bytesCopied, bufferSize))) != -1) {
				destination.write(readBuffer, 0, bytesRead);
				bytesCopied += bytesRead;
			} else {
				throw new EOFException(String.format("Expected %d bytes but got only %d", byteCount, bytesCopied));
			}
		}
	}

	/**
	 * Discards all bytes from the given source.
	 * Returns the number of bytes discarded.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static int discardBytes(InputStream source) throws IOException {
		Contract.checkArgument(source != null, "Source stream must not be null");

		return copyBytes(source, new DiscardingOutputStream());
	}

	/**
	 * Discards the given number of bytes from the given source.
	 * Discards all bytes if the number of bytes is negative.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void discardBytes(InputStream source, int byteCount) throws IOException {
		Contract.checkArgument(source != null, "Source stream must not be null");

		copyBytes(source, new DiscardingOutputStream(), byteCount);
	}

	/**
	 * Returns the size of the buffer (in bytes) used in I/O operations.
	 *
	 * @since 1.0
	 */
	public static int getBufferSize() {
		return bufferSize;
	}

	/**
	 * Reads a {@code byte} from the given source.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static byte readByte(InputStream source) throws IOException {
		Contract.checkArgument(source != null, "Source stream must not be null");

		return (byte) readUnsignedByte(source);
	}

	/**
	 * Reads all bytes from the given source.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static byte[] readBytes(InputStream source) throws IOException {
		ByteArrayOutputStream destination = new ByteArrayOutputStream();
		copyBytes(source, destination);

		return destination.toByteArray();
	}

	/**
	 * Reads the given number of bytes from the given source.
	 * Reads all bytes if the number of bytes is negative.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static byte[] readBytes(InputStream source, int byteCount) throws IOException {
		ByteArrayOutputStream destination = new ByteArrayOutputStream();
		copyBytes(source, destination, byteCount);

		return destination.toByteArray();
	}

	/**
	 * Reads a {@code char} from the given source using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static char readChar(InputStream source) throws IOException {
		return (char) readShort(source, BIG_ENDIAN);
	}

	/**
	 * Reads a {@code char} from the given source using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static char readChar(InputStream source, ByteOrder byteOrder) throws IOException {
		return (char) readShort(source, byteOrder);
	}

	/**
	 * Reads a {@code double} from the given source using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static double readDouble(InputStream source) throws IOException {
		return Double.longBitsToDouble(readLong(source, BIG_ENDIAN));
	}

	/**
	 * Reads a {@code double} from the given source using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static double readDouble(InputStream source, ByteOrder byteOrder) throws IOException {
		return Double.longBitsToDouble(readLong(source, byteOrder));
	}

	/**
	 * Reads a {@code float} from the given source using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static float readFloat(InputStream source) throws IOException {
		return Float.intBitsToFloat(readInt(source, BIG_ENDIAN));
	}

	/**
	 * Reads a {@code float} from the given source using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static float readFloat(InputStream source, ByteOrder byteOrder) throws IOException {
		return Float.intBitsToFloat(readInt(source, byteOrder));
	}

	/**
	 * Reads an {@code int} from the given source using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static int readInt(InputStream source) throws IOException {
		return readInt(source, BIG_ENDIAN);
	}

	/**
	 * Reads an {@code int} from the given source using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static int readInt(InputStream source, ByteOrder byteOrder) throws IOException {
		Contract.checkArgument(source != null, "Source stream must not be null");
		Contract.checkArgument(byteOrder != null, "Byte order must not be null");

		int value = 0;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);

		return byteOrder.equals(LITTLE_ENDIAN) ? Integer.reverseBytes(value) : value;
	}

	/**
	 * Reads a {@code long} from the given source using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static long readLong(InputStream source) throws IOException {
		return readLong(source, BIG_ENDIAN);
	}

	/**
	 * Reads a {@code long} from the given source using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static long readLong(InputStream source, ByteOrder byteOrder) throws IOException {
		Contract.checkArgument(source != null, "Source stream must not be null");
		Contract.checkArgument(byteOrder != null, "Byte order must not be null");

		long value = 0L;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);

		return byteOrder.equals(LITTLE_ENDIAN) ? Long.reverseBytes(value) : value;
	}

	/**
	 * Reads a {@code short} from the given source using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static short readShort(InputStream source) throws IOException {
		return readShort(source, BIG_ENDIAN);
	}

	/**
	 * Reads a {@code short} from the given source using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static short readShort(InputStream source, ByteOrder byteOrder) throws IOException {
		Contract.checkArgument(source != null, "Source stream must not be null");
		Contract.checkArgument(byteOrder != null, "Byte order must not be null");

		short value = 0;
		value |= readUnsignedByte(source);
		value <<= 8;
		value |= readUnsignedByte(source);

		return byteOrder.equals(LITTLE_ENDIAN) ? Short.reverseBytes(value) : value;
	}

	/**
	 * Reads all bytes from the given source and converts them to a string using UTF-8.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static String readString(InputStream source) throws IOException {
		return readString(source, UTF_8);
	}

	/**
	 * Reads all bytes from the given source and converts them to a string using the given charset.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code charset} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static String readString(InputStream source, Charset charset) throws IOException {
		Contract.checkArgument(charset != null, "Charset must not be null");

		return new String(readBytes(source), charset);
	}

	/**
	 * Reads the given number of bytes from the given source and converts them to a string using UTF-8.
	 * Reads all bytes if the number of bytes is negative.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static String readString(InputStream source, int byteCount) throws IOException {
		return readString(source, byteCount, UTF_8);
	}

	/**
	 * Reads the given number of bytes from the given source and converts them to a string using the given charset.
	 * Reads all bytes if the number of bytes is negative.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code charset} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static String readString(InputStream source, int byteCount, Charset charset) throws IOException {
		Contract.checkArgument(charset != null, "Charset must not be null");

		return new String(readBytes(source, byteCount), charset);
	}

	/**
	 * Reads an unsigned {@code byte} from the given source.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static int readUnsignedByte(InputStream source) throws IOException {
		Contract.checkArgument(source != null, "Source stream must not be null");

		int value = source.read();
		if (value == -1) {
			throw new EOFException();
		}

		return value;
	}

	/**
	 * Reads a {@link UUID}} from the given source using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static UUID readUuid(InputStream source) throws IOException {
		return new UUID(readLong(source, BIG_ENDIAN), readLong(source, BIG_ENDIAN));
	}

	/**
	 * Reads a {@link UUID}} from the given source using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code source} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static UUID readUuid(InputStream source, ByteOrder byteOrder) throws IOException {
		return new UUID(readLong(source, byteOrder), readLong(source, byteOrder));
	}

	/**
	 * Sets the size of the buffer (in bytes) used in I/O operations.
	 *
	 * @throws IllegalArgumentException if {@code bufferSize} is not positive
	 *
	 * @since 1.0
	 */
	public static void setBufferSize(int bufferSize) {
		Contract.checkArgument(bufferSize > 0, "Buffer size must be positive: {0}", bufferSize);

		Io.bufferSize = bufferSize;
	}

	/**
	 * Writes a {@code byte} to the given destination.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeByte(OutputStream destination, byte value) throws IOException {
		Contract.checkArgument(destination != null, "Destination stream must not be null");

		destination.write(value);
	}

	/**
	 * Writes the given bytes to the given destination.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code bytes} are {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeBytes(OutputStream destination, byte[] bytes) throws IOException {
		Contract.checkArgument(destination != null, "Destination stream must not be null");
		Contract.checkArgument(bytes != null, "Bytes to write must not be null");

		destination.write(bytes);
	}

	/**
	 * Writes a {@code char} to the given destination using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeChar(OutputStream destination, char value) throws IOException {
		writeChar(destination, value, BIG_ENDIAN);
	}

	/**
	 * Writes a {@code char} to the given destination using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeChar(OutputStream destination, char value, ByteOrder byteOrder) throws IOException {
		writeShort(destination, (short) value, byteOrder);
	}

	/**
	 * Writes a {@code double} to the given destination using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeDouble(OutputStream destination, double value) throws IOException {
		writeDouble(destination, value, BIG_ENDIAN);
	}

	/**
	 * Writes a {@code double} to the given destination using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeDouble(OutputStream destination, double value, ByteOrder byteOrder) throws IOException {
		writeLong(destination, Double.doubleToRawLongBits(value), byteOrder);
	}

	/**
	 * Writes a {@code float} to the given destination using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeFloat(OutputStream destination, float value) throws IOException {
		writeFloat(destination, value, BIG_ENDIAN);
	}

	/**
	 * Writes a {@code float} to the given destination using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeFloat(OutputStream destination, float value, ByteOrder byteOrder) throws IOException {
		writeInt(destination, Float.floatToRawIntBits(value), byteOrder);
	}

	/**
	 * Writes an {@code int} to the given destination using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeInt(OutputStream destination, int value) throws IOException {
		writeInt(destination, value, BIG_ENDIAN);
	}

	/**
	 * Writes an {@code int} to the given destination using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeInt(OutputStream destination, int value, ByteOrder byteOrder) throws IOException {
		Contract.checkArgument(destination != null, "Destination stream must not be null");
		Contract.checkArgument(byteOrder != null, "Byte order must not be null");

		if (byteOrder.equals(BIG_ENDIAN)) {
			value = Integer.reverseBytes(value);
		}

		destination.write(value & 0xFF);
		value >>>= 8;
		destination.write(value & 0xFF);
		value >>>= 8;
		destination.write(value & 0xFF);
		value >>>= 8;
		destination.write(value & 0xFF);
	}

	/**
	 * Writes a {@code long} to the given destination using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeLong(OutputStream destination, long value) throws IOException {
		writeLong(destination, value, BIG_ENDIAN);
	}

	/**
	 * Writes a {@code long} to the given destination using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeLong(OutputStream destination, long value, ByteOrder byteOrder) throws IOException {
		Contract.checkArgument(destination != null, "Destination stream must not be null");
		Contract.checkArgument(byteOrder != null, "Byte order must not be null");

		if (byteOrder.equals(BIG_ENDIAN)) {
			value = Long.reverseBytes(value);
		}

		destination.write((int) value & 0xFF);
		value >>>= 8;
		destination.write((int) value & 0xFF);
		value >>>= 8;
		destination.write((int) value & 0xFF);
		value >>>= 8;
		destination.write((int) value & 0xFF);
		value >>>= 8;
		destination.write((int) value & 0xFF);
		value >>>= 8;
		destination.write((int) value & 0xFF);
		value >>>= 8;
		destination.write((int) value & 0xFF);
		value >>>= 8;
		destination.write((int) value & 0xFF);
	}

	/**
	 * Writes a {@code short} to the given destination using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeShort(OutputStream destination, short value) throws IOException {
		writeShort(destination, value, BIG_ENDIAN);
	}

	/**
	 * Writes a {@code short} to the given destination using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code byteOrder} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeShort(OutputStream destination, short value, ByteOrder byteOrder) throws IOException {
		Contract.checkArgument(destination != null, "Destination stream must not be null");
		Contract.checkArgument(byteOrder != null, "Byte order must not be null");

		if (byteOrder.equals(BIG_ENDIAN)) {
			value = Short.reverseBytes(value);
		}

		destination.write(value & 0xFF);
		value >>>= 8;
		destination.write(value & 0xFF);
	}

	/**
	 * Writes the given string to the given destination using UTF-8.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeString(OutputStream destination, String string) throws IOException {
		writeString(destination, string, UTF_8);
	}

	/**
	 * Writes the given string to the given destination using the given charset.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code string} is {@code null}
	 * @throws IllegalArgumentException if {@code charset} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeString(OutputStream destination, String string, Charset charset) throws IOException {
		Contract.checkArgument(destination != null, "Destination strema must not be null");
		Contract.checkArgument(string != null, "String to write must not be null");
		Contract.checkArgument(charset != null, "Charset must not be null");

		destination.write(string.getBytes(charset));
	}

	/**
	 * Writes an unsigned {@code byte} to the given destination.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeUnsignedByte(OutputStream destination, int value) throws IOException {
		Contract.checkArgument(destination != null, "Destination stream must not be null");

		destination.write(value);
	}

	/**
	 * Writes a {@link UUID} to the given destination using the default (big-endian) byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code uuid} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeUuid(OutputStream destination, UUID uuid) throws IOException {
		Contract.checkArgument(uuid != null, "UUID to write must not be null");

		writeLong(destination, uuid.getMostSignificantBits(), BIG_ENDIAN);
		writeLong(destination, uuid.getLeastSignificantBits(), BIG_ENDIAN);
	}

	/**
	 * Writes a {@link UUID} to the given destination using the given byte order.
	 *
	 * @throws IllegalArgumentException if {@code destination} is {@code null}
	 * @throws IllegalArgumentException if {@code uuid} is {@code null}
	 * @throws IOException if an I/O error occurs
	 *
	 * @since 1.0
	 */
	public static void writeUuid(OutputStream destination, UUID uuid, ByteOrder byteOrder) throws IOException {
		Contract.checkArgument(uuid != null, "UUID to write must not be null");

		writeLong(destination, uuid.getMostSignificantBits(), byteOrder);
		writeLong(destination, uuid.getLeastSignificantBits(), byteOrder);
	}

	// ------------------------------------------------------------
	// ------------------------------------------------------------
	// ------------------------------------------------------------

	private static class DiscardingOutputStream extends OutputStream {

		@Override
		public void write(int byteValue) throws IOException {
			; // Ignore
		}

	}

}
