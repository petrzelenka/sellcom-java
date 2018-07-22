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
package org.sellcom.core.util;

import java.util.Arrays;
import java.util.Objects;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.ReflectionUtils;
import org.sellcom.core.math.MoreMath;

/**
 * Operations with arrays.
 *
 * @since 1.0
 */
public class MoreArrays {

	private MoreArrays() {
		// Utility class, not to be instantiated
	}


	/**
	 * Returns an array concatenated from the given arrays.
	 *
	 * @throws IllegalArgumentException if {@code arrays} are {@code null}
	 * @throws IllegalArgumentException if {@code arrays} contain {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean[] concat(boolean[]... arrays) {
		Contract.checkArgument(arrays != null, "Arrays must not be null");

		int totalLength = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			Contract.checkArgument(arrays[i] != null, "Arrays must not contain null");

			totalLength = Math.addExact(totalLength, arrays[i].length);
		}

		boolean[] result = new boolean[totalLength];
		int position = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			System.arraycopy(arrays[i], 0, result, position, arrays[i].length);
			position += arrays[i].length;
		}

		return result;
	}

	/**
	 * Returns an array concatenated from the given arrays.
	 *
	 * @throws IllegalArgumentException if {@code arrays} are {@code null}
	 * @throws IllegalArgumentException if {@code arrays} contain {@code null}
	 *
	 * @since 1.0
	 */
	public static byte[] concat(byte[]... arrays) {
		Contract.checkArgument(arrays != null, "Arrays must not be null");

		int totalLength = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			Contract.checkArgument(arrays[i] != null, "Arrays must not contain null");

			totalLength = Math.addExact(totalLength, arrays[i].length);
		}

		byte[] result = new byte[totalLength];
		int position = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			System.arraycopy(arrays[i], 0, result, position, arrays[i].length);
			position += arrays[i].length;
		}

		return result;
	}

	/**
	 * Returns an array concatenated from the given arrays.
	 *
	 * @throws IllegalArgumentException if {@code arrays} are {@code null}
	 * @throws IllegalArgumentException if {@code arrays} contain {@code null}
	 *
	 * @since 1.0
	 */
	public static char[] concat(char[]... arrays) {
		Contract.checkArgument(arrays != null, "Arrays must not be null");

		int totalLength = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			Contract.checkArgument(arrays[i] != null, "Arrays must not contain null");

			totalLength = Math.addExact(totalLength, arrays[i].length);
		}

		char[] result = new char[totalLength];
		int position = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			System.arraycopy(arrays[i], 0, result, position, arrays[i].length);
			position += arrays[i].length;
		}

		return result;
	}

	/**
	 * Returns an array concatenated from the given arrays.
	 *
	 * @throws IllegalArgumentException if {@code arrays} are {@code null}
	 * @throws IllegalArgumentException if {@code arrays} contain {@code null}
	 *
	 * @since 1.0
	 */
	public static double[] concat(double[]... arrays) {
		Contract.checkArgument(arrays != null, "Arrays must not be null");

		int totalLength = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			Contract.checkArgument(arrays[i] != null, "Arrays must not contain null");

			totalLength = Math.addExact(totalLength, arrays[i].length);
		}

		double[] result = new double[totalLength];
		int position = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			System.arraycopy(arrays[i], 0, result, position, arrays[i].length);
			position += arrays[i].length;
		}

		return result;
	}

	/**
	 * Returns an array concatenated from the given arrays.
	 *
	 * @throws IllegalArgumentException if {@code arrays} are {@code null}
	 * @throws IllegalArgumentException if {@code arrays} contain {@code null}
	 *
	 * @since 1.0
	 */
	public static float[] concat(float[]... arrays) {
		Contract.checkArgument(arrays != null, "Arrays must not be null");

		int totalLength = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			Contract.checkArgument(arrays[i] != null, "Arrays must not contain null");

			totalLength = Math.addExact(totalLength, arrays[i].length);
		}

		float[] result = new float[totalLength];
		int position = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			System.arraycopy(arrays[i], 0, result, position, arrays[i].length);
			position += arrays[i].length;
		}

		return result;
	}

	/**
	 * Returns an array concatenated from the given arrays.
	 *
	 * @throws IllegalArgumentException if {@code arrays} are {@code null}
	 * @throws IllegalArgumentException if {@code arrays} contain {@code null}
	 *
	 * @since 1.0
	 */
	public static int[] concat(int[]... arrays) {
		Contract.checkArgument(arrays != null, "Arrays must not be null");

		int totalLength = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			Contract.checkArgument(arrays[i] != null, "Arrays must not contain null");

			totalLength = Math.addExact(totalLength, arrays[i].length);
		}

		int[] result = new int[totalLength];
		int position = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			System.arraycopy(arrays[i], 0, result, position, arrays[i].length);
			position += arrays[i].length;
		}

		return result;
	}

	/**
	 * Returns an array concatenated from the given arrays.
	 *
	 * @throws IllegalArgumentException if {@code arrays} are {@code null}
	 * @throws IllegalArgumentException if {@code arrays} contain {@code null}
	 *
	 * @since 1.0
	 */
	public static long[] concat(long[]... arrays) {
		Contract.checkArgument(arrays != null, "Arrays must not be null");

		int totalLength = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			Contract.checkArgument(arrays[i] != null, "Arrays must not contain null");

			totalLength = Math.addExact(totalLength, arrays[i].length);
		}

		long[] result = new long[totalLength];
		int position = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			System.arraycopy(arrays[i], 0, result, position, arrays[i].length);
			position += arrays[i].length;
		}

		return result;
	}

	/**
	 * Returns an array concatenated from the given arrays.
	 *
	 * @throws IllegalArgumentException if {@code arrays} are {@code null}
	 * @throws IllegalArgumentException if {@code arrays} contain {@code null}
	 *
	 * @since 1.0
	 */
	public static short[] concat(short[]... arrays) {
		Contract.checkArgument(arrays != null, "Arrays must not be null");

		int totalLength = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			Contract.checkArgument(arrays[i] != null, "Arrays must not contain null");

			totalLength = Math.addExact(totalLength, arrays[i].length);
		}

		short[] result = new short[totalLength];
		int position = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			System.arraycopy(arrays[i], 0, result, position, arrays[i].length);
			position += arrays[i].length;
		}

		return result;
	}

	/**
	 * Returns an array concatenated from the given arrays.
	 *
	 * @throws IllegalArgumentException if {@code arrays} are {@code null}
	 * @throws IllegalArgumentException if {@code arrays} contain {@code null}
	 *
	 * @since 1.0
	 */
	@SafeVarargs
	public static <T> T[] concat(T[]... arrays) {
		Contract.checkArgument(arrays != null, "Arrays must not be null");

		int totalLength = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			Contract.checkArgument(arrays[i] != null, "Arrays must not contain null");

			totalLength = Math.addExact(totalLength, arrays[i].length);
		}

		T[] result = ReflectionUtils.createArray(arrays[0], totalLength);
		int position = 0;
		for (int i = 0, j = arrays.length; i < j; i++) {
			System.arraycopy(arrays[i], 0, result, position, arrays[i].length);
			position += arrays[i].length;
		}

		return result;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(boolean[] array, boolean element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(boolean[] array, int fromIndex, int toIndex, boolean element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, fromIndex, toIndex, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(byte[] array, byte element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(byte[] array, int fromIndex, int toIndex, byte element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, fromIndex, toIndex, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(char[] array, char element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(char[] array, int fromIndex, int toIndex, char element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, fromIndex, toIndex, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static boolean contains(double[] array, double element, double tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		return indexOf(array, element, tolerance) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static boolean contains(double[] array, int fromIndex, int toIndex, double element, double tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		return indexOf(array, fromIndex, toIndex, element, tolerance) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static boolean contains(float[] array, float element, float tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		return indexOf(array, element, tolerance) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static boolean contains(float[] array, int fromIndex, int toIndex, float element, float tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		return indexOf(array, fromIndex, toIndex, element, tolerance) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(int[] array, int element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(int[] array, int fromIndex, int toIndex, int element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, fromIndex, toIndex, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(long[] array, long element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(long[] array, int fromIndex, int toIndex, long element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, fromIndex, toIndex, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(short[] array, short element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean contains(short[] array, int fromIndex, int toIndex, short element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, fromIndex, toIndex, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> boolean contains(T[] array, T element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, element) >= 0;
	}

	/**
	 * Checks whether the given array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> boolean contains(T[] array, int fromIndex, int toIndex, T element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, fromIndex, toIndex, element) >= 0;
	}

	/**
	 * Returns a copy of the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean[] copyOf(boolean[] array) {
		Contract.checkArgument(array != null, "Array must not be null");

		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Returns a copy of the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static byte[] copyOf(byte[] array) {
		Contract.checkArgument(array != null, "Array must not be null");

		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Returns a copy of the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static char[] copyOf(char[] array) {
		Contract.checkArgument(array != null, "Array must not be null");

		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Returns a copy of the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static double[] copyOf(double[] array) {
		Contract.checkArgument(array != null, "Array must not be null");

		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Returns a copy of the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static float[] copyOf(float[] array) {
		Contract.checkArgument(array != null, "Array must not be null");

		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Returns a copy of the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int[] copyOf(int[] array) {
		Contract.checkArgument(array != null, "Array must not be null");

		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Returns a copy of the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static long[] copyOf(long[] array) {
		Contract.checkArgument(array != null, "Array must not be null");

		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Returns a copy of the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static short[] copyOf(short[] array) {
		Contract.checkArgument(array != null, "Array must not be null");

		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Returns a copy of the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> T[] copyOf(T[] array) {
		Contract.checkArgument(array != null, "Array must not be null");

		return Arrays.copyOf(array, array.length);
	}

	/**
	 * Returns the given array if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static boolean[] emptyToNull(boolean[] array) {
		return isNullOrEmpty(array) ? null : array;
	}

	/**
	 * Returns the given array if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static byte[] emptyToNull(byte[] array) {
		return isNullOrEmpty(array) ? null : array;
	}

	/**
	 * Returns the given array if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static char[] emptyToNull(char[] array) {
		return isNullOrEmpty(array) ? null : array;
	}

	/**
	 * Returns the given array if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static double[] emptyToNull(double[] array) {
		return isNullOrEmpty(array) ? null : array;
	}

	/**
	 * Returns the given array if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static float[] emptyToNull(float[] array) {
		return isNullOrEmpty(array) ? null : array;
	}

	/**
	 * Returns the given array if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static int[] emptyToNull(int[] array) {
		return isNullOrEmpty(array) ? null : array;
	}

	/**
	 * Returns the given array if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static long[] emptyToNull(long[] array) {
		return isNullOrEmpty(array) ? null : array;
	}

	/**
	 * Returns the given array if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static short[] emptyToNull(short[] array) {
		return isNullOrEmpty(array) ? null : array;
	}

	/**
	 * Returns the given array if it is non-empty, {@code null} otherwise.
	 *
	 * @since 1.0
	 */
	public static <T> T[] emptyToNull(T[] array) {
		return isNullOrEmpty(array) ? null : array;
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(boolean[] array, boolean element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(boolean[] array, int fromIndex, int toIndex, boolean element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = fromIndex; i < toIndex; i++) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(byte[] array, byte element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(byte[] array, int fromIndex, int toIndex, byte element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = fromIndex; i < toIndex; i++) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(char[] array, char element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(char[] array, int fromIndex, int toIndex, char element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = fromIndex; i < toIndex; i++) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static int indexOf(double[] array, double element, double tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, 0, array.length, element, tolerance);
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static int indexOf(double[] array, int fromIndex, int toIndex, double element, double tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		for (int i = fromIndex; i < toIndex; i++) {
			if (MoreMath.equals(array[i], element, tolerance)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static int indexOf(float[] array, float element, float tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, 0, array.length, element, tolerance);
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static int indexOf(float[] array, int fromIndex, int toIndex, float element, float tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		for (int i = fromIndex; i < toIndex; i++) {
			if (MoreMath.equals(array[i], element, tolerance)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(int[] array, int element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(int[] array, int fromIndex, int toIndex, int element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = fromIndex; i < toIndex; i++) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(long[] array, long element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(long[] array, int fromIndex, int toIndex, long element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = fromIndex; i < toIndex; i++) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(short[] array, short element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int indexOf(short[] array, int fromIndex, int toIndex, short element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = fromIndex; i < toIndex; i++) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> int indexOf(T[] array, T element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return indexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the first occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> int indexOf(T[] array, int fromIndex, int toIndex, T element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = fromIndex; i < toIndex; i++) {
			if (Objects.equals(array[i], element)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Checks whether the given array is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(boolean[] array) {
		return (array == null) || (array.length == 0);
	}

	/**
	 * Checks whether the given array is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(byte[] array) {
		return (array == null) || (array.length == 0);
	}

	/**
	 * Checks whether the given array is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(char[] array) {
		return (array == null) || (array.length == 0);
	}

	/**
	 * Checks whether the given array is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(double[] array) {
		return (array == null) || (array.length == 0);
	}

	/**
	 * Checks whether the given array is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(float[] array) {
		return (array == null) || (array.length == 0);
	}

	/**
	 * Checks whether the given array is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(int[] array) {
		return (array == null) || (array.length == 0);
	}

	/**
	 * Checks whether the given array is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(long[] array) {
		return (array == null) || (array.length == 0);
	}

	/**
	 * Checks whether the given array is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static boolean isNullOrEmpty(short[] array) {
		return (array == null) || (array.length == 0);
	}

	/**
	 * Checks whether the given array is {@code null} or empty.
	 *
	 * @since 1.0
	 */
	public static <T> boolean isNullOrEmpty(T[] array) {
		return (array == null) || (array.length == 0);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(boolean[] array, boolean element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return lastIndexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(boolean[] array, int fromIndex, int toIndex, boolean element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = toIndex - 1; i >= fromIndex; i--) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(byte[] array, byte element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return lastIndexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(byte[] array, int fromIndex, int toIndex, byte element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = toIndex - 1; i >= fromIndex; i--) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(char[] array, char element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return lastIndexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(char[] array, int fromIndex, int toIndex, char element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = toIndex - 1; i >= fromIndex; i--) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(double[] array, double element, double tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");

		return lastIndexOf(array, 0, array.length, element, tolerance);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(double[] array, int fromIndex, int toIndex, double element, double tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		for (int i = toIndex - 1; i >= fromIndex; i--) {
			if (MoreMath.equals(array[i], element, tolerance)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(float[] array, float element, float tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");

		return lastIndexOf(array, 0, array.length, element, tolerance);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(float[] array, int fromIndex, int toIndex, float element, float tolerance) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(tolerance >= 0, "Tolerance must not be negative: {0}", tolerance);

		for (int i = toIndex - 1; i >= fromIndex; i--) {
			if (MoreMath.equals(array[i], element, tolerance)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(int[] array, int element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return lastIndexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(int[] array, int fromIndex, int toIndex, int element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = toIndex - 1; i >= fromIndex; i--) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(long[] array, long element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return lastIndexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(long[] array, int fromIndex, int toIndex, long element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = toIndex - 1; i >= fromIndex; i--) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(short[] array, short element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return lastIndexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static int lastIndexOf(short[] array, int fromIndex, int toIndex, short element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = toIndex - 1; i >= fromIndex; i--) {
			if (array[i] == element) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> int lastIndexOf(T[] array, T element) {
		Contract.checkArgument(array != null, "Array must not be null");

		return lastIndexOf(array, 0, array.length, element);
	}

	/**
	 * Returns the index of the last occurrence of the given element in the given array or {@code -1} if the array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> int lastIndexOf(T[] array, int fromIndex, int toIndex, T element) {
		Contract.checkArgument(array != null, "Array must not be null");

		for (int i = toIndex - 1; i >= fromIndex; i--) {
			if (Objects.equals(array[i], element)) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Returns the given array if it is non-empty, empty array otherwise.
	 *
	 * @since 1.0
	 */
	public static boolean[] nullToEmpty(boolean[] array) {
		return isNullOrEmpty(array) ? new boolean[0] : array;
	}

	/**
	 * Returns the given array if it is non-empty, empty array otherwise.
	 *
	 * @since 1.0
	 */
	public static byte[] nullToEmpty(byte[] array) {
		return isNullOrEmpty(array) ? new byte[0] : array;
	}

	/**
	 * Returns the given array if it is non-empty, empty array otherwise.
	 *
	 * @since 1.0
	 */
	public static char[] nullToEmpty(char[] array) {
		return isNullOrEmpty(array) ? new char[0] : array;
	}

	/**
	 * Returns the given array if it is non-empty, empty array otherwise.
	 *
	 * @since 1.0
	 */
	public static double[] nullToEmpty(double[] array) {
		return isNullOrEmpty(array) ? new double[0] : array;
	}

	/**
	 * Returns the given array if it is non-empty, empty array otherwise.
	 *
	 * @since 1.0
	 */
	public static float[] nullToEmpty(float[] array) {
		return isNullOrEmpty(array) ? new float[0] : array;
	}

	/**
	 * Returns the given array if it is non-empty, empty array otherwise.
	 *
	 * @since 1.0
	 */
	public static int[] nullToEmpty(int[] array) {
		return isNullOrEmpty(array) ? new int[0] : array;
	}

	/**
	 * Returns the given array if it is non-empty, empty array otherwise.
	 *
	 * @since 1.0
	 */
	public static long[] nullToEmpty(long[] array) {
		return isNullOrEmpty(array) ? new long[0] : array;
	}

	/**
	 * Returns the given array if it is non-empty, empty array otherwise.
	 *
	 * @since 1.0
	 */
	public static short[] nullToEmpty(short[] array) {
		return isNullOrEmpty(array) ? new short[0] : array;
	}

	/**
	 * Returns the given array if it is non-empty, empty array otherwise.
	 *
	 * @since 1.0
	 */
	public static <T> T[] nullToEmpty(T[] array) {
		return isNullOrEmpty(array) ? ReflectionUtils.createArray(array, 0) : array;
	}

	/**
	 * Reverses the elements in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void reverse(boolean[] array) {
		Contract.checkArgument(array != null, "Input array must not be null");

		for(int i = 0, j = (array.length - 1); i < j; i++, j--) {
			swap(array, i, j);
		}
	}

	/**
	 * Reverses the elements in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void reverse(byte[] array) {
		Contract.checkArgument(array != null, "Input array must not be null");

		for(int i = 0, j = (array.length - 1); i < j; i++, j--) {
			swap(array, i, j);
		}
	}

	/**
	 * Reverses the elements in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void reverse(char[] array) {
		Contract.checkArgument(array != null, "Input array must not be null");

		for(int i = 0, j = (array.length - 1); i < j; i++, j--) {
			swap(array, i, j);
		}
	}

	/**
	 * Reverses the elements in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void reverse(double[] array) {
		Contract.checkArgument(array != null, "Input array must not be null");

		for(int i = 0, j = (array.length - 1); i < j; i++, j--) {
			swap(array, i, j);
		}
	}

	/**
	 * Reverses the elements in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void reverse(float[] array) {
		Contract.checkArgument(array != null, "Input array must not be null");

		for(int i = 0, j = (array.length - 1); i < j; i++, j--) {
			swap(array, i, j);
		}
	}

	/**
	 * Reverses the elements in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void reverse(int[] array) {
		Contract.checkArgument(array != null, "Input array must not be null");

		for(int i = 0, j = (array.length - 1); i < j; i++, j--) {
			swap(array, i, j);
		}
	}

	/**
	 * Reverses the elements in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void reverse(long[] array) {
		Contract.checkArgument(array != null, "Input array must not be null");

		for(int i = 0, j = (array.length - 1); i < j; i++, j--) {
			swap(array, i, j);
		}
	}

	/**
	 * Reverses the elements in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void reverse(short[] array) {
		Contract.checkArgument(array != null, "Input array must not be null");

		for(int i = 0, j = (array.length - 1); i < j; i++, j--) {
			swap(array, i, j);
		}
	}

	/**
	 * Reverses the elements in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> void reverse(T[] array) {
		Contract.checkArgument(array != null, "Input array must not be null");

		for(int i = 0, j = (array.length - 1); i < j; i++, j--) {
			swap(array, i, j);
		}
	}

	/**
	 * Checks whether the two given arrays are equal to each other.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean slowEquals(boolean[] former, boolean[] latter) {
		Contract.checkArgument(former != null, "Former array must not be null");
		Contract.checkArgument(latter != null, "Latter array must not be null");

		int diff = former.length ^ latter.length;
		for (int i = 0; (i < former.length) && (i < latter.length); i++) {
			diff |= Boolean.compare(former[i], latter[i]);
		}

		return diff == 0;
	}

	/**
	 * Checks whether the two given arrays are equal to each other.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean slowEquals(byte[] former, byte[] latter) {
		Contract.checkArgument(former != null, "Former array must not be null");
		Contract.checkArgument(latter != null, "Latter array must not be null");

		int diff = former.length ^ latter.length;
		for (int i = 0; (i < former.length) && (i < latter.length); i++) {
			diff |= (former[i] ^ latter[i]);
		}

		return diff == 0;
	}

	/**
	 * Checks whether the two given arrays are equal to each other.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean slowEquals(char[] former, char[] latter) {
		Contract.checkArgument(former != null, "Former array must not be null");
		Contract.checkArgument(latter != null, "Latter array must not be null");

		int diff = former.length ^ latter.length;
		for (int i = 0; (i < former.length) && (i < latter.length); i++) {
			diff |= (former[i] ^ latter[i]);
		}

		return diff == 0;
	}

	/**
	 * Checks whether the two given arrays are equal to each other.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean slowEquals(double[] former, double[] latter) {
		Contract.checkArgument(former != null, "Former array must not be null");
		Contract.checkArgument(latter != null, "Latter array must not be null");

		long diff = former.length ^ latter.length;
		for (int i = 0; (i < former.length) && (i < latter.length); i++) {
			diff |= (Double.doubleToLongBits(former[i]) ^ Double.doubleToLongBits(latter[i]));
		}

		return diff == 0;
	}

	/**
	 * Checks whether the two given arrays are equal to each other.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean slowEquals(float[] former, float[] latter) {
		Contract.checkArgument(former != null, "Former array must not be null");
		Contract.checkArgument(latter != null, "Latter array must not be null");

		int diff = former.length ^ latter.length;
		for (int i = 0; (i < former.length) && (i < latter.length); i++) {
			diff |= (Float.floatToIntBits(former[i]) ^ Float.floatToIntBits(latter[i]));
		}

		return diff == 0;
	}

	/**
	 * Checks whether the two given arrays are equal to each other.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean slowEquals(int[] former, int[] latter) {
		Contract.checkArgument(former != null, "Former array must not be null");
		Contract.checkArgument(latter != null, "Latter array must not be null");

		int diff = former.length ^ latter.length;
		for (int i = 0; (i < former.length) && (i < latter.length); i++) {
			diff |= (former[i] ^ latter[i]);
		}

		return diff == 0;
	}

	/**
	 * Checks whether the two given arrays are equal to each other.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean slowEquals(long[] former, long[] latter) {
		Contract.checkArgument(former != null, "Former array must not be null");
		Contract.checkArgument(latter != null, "Latter array must not be null");

		long diff = former.length ^ latter.length;
		for (int i = 0; (i < former.length) && (i < latter.length); i++) {
			diff |= (former[i] ^ latter[i]);
		}

		return diff == 0;
	}

	/**
	 * Checks whether the two given arrays are equal to each other.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static boolean slowEquals(short[] former, short[] latter) {
		Contract.checkArgument(former != null, "Former array must not be null");
		Contract.checkArgument(latter != null, "Latter array must not be null");

		int diff = former.length ^ latter.length;
		for (int i = 0; (i < former.length) && (i < latter.length); i++) {
			diff |= (former[i] ^ latter[i]);
		}

		return diff == 0;
	}

	/**
	 * Checks whether the two given arrays are equal to each other.
	 *
	 * @throws IllegalArgumentException if {@code former} is {@code null}
	 * @throws IllegalArgumentException if {@code latter} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> boolean slowEquals(T[] former, T[] latter) {
		Contract.checkArgument(former != null, "Former array must not be null");
		Contract.checkArgument(latter != null, "Latter array must not be null");

		int diff = former.length ^ latter.length;
		for (int i = 0; (i < former.length) && (i < latter.length); i++) {
			diff |= (Objects.equals(former[i], latter[i]) ? 0 : 1);
		}

		return diff == 0;
	}

	/**
	 * Swaps the elements at the given positions in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void swap(boolean[] array, int formerIndex, int latterIndex) {
		Contract.checkArgument(array != null, "Array must not be null");

		boolean element = array[formerIndex];
		array[formerIndex] = array[latterIndex];
		array[latterIndex] = element;
	}

	/**
	 * Swaps the elements at the given positions in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void swap(byte[] array, int formerIndex, int latterIndex) {
		Contract.checkArgument(array != null, "Array must not be null");

		byte element = array[formerIndex];
		array[formerIndex] = array[latterIndex];
		array[latterIndex] = element;
	}

	/**
	 * Swaps the elements at the given positions in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void swap(char[] array, int formerIndex, int latterIndex) {
		Contract.checkArgument(array != null, "Array must not be null");

		char element = array[formerIndex];
		array[formerIndex] = array[latterIndex];
		array[latterIndex] = element;
	}

	/**
	 * Swaps the elements at the given positions in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void swap(double[] array, int formerIndex, int latterIndex) {
		Contract.checkArgument(array != null, "Array must not be null");

		double element = array[formerIndex];
		array[formerIndex] = array[latterIndex];
		array[latterIndex] = element;
	}

	/**
	 * Swaps the elements at the given positions in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void swap(float[] array, int formerIndex, int latterIndex) {
		Contract.checkArgument(array != null, "Array must not be null");

		float element = array[formerIndex];
		array[formerIndex] = array[latterIndex];
		array[latterIndex] = element;
	}

	/**
	 * Swaps the elements at the given positions in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void swap(int[] array, int formerIndex, int latterIndex) {
		Contract.checkArgument(array != null, "Array must not be null");

		int element = array[formerIndex];
		array[formerIndex] = array[latterIndex];
		array[latterIndex] = element;
	}

	/**
	 * Swaps the elements at the given positions in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void swap(long[] array, int formerIndex, int latterIndex) {
		Contract.checkArgument(array != null, "Array must not be null");

		long element = array[formerIndex];
		array[formerIndex] = array[latterIndex];
		array[latterIndex] = element;
	}

	/**
	 * Swaps the elements at the given positions in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static void swap(short[] array, int formerIndex, int latterIndex) {
		Contract.checkArgument(array != null, "Array must not be null");

		short element = array[formerIndex];
		array[formerIndex] = array[latterIndex];
		array[latterIndex] = element;
	}

	/**
	 * Swaps the elements at the given positions in the given array.
	 *
	 * @throws IllegalArgumentException if {@code array} is {@code null}
	 *
	 * @since 1.0
	 */
	public static <T> void swap(T[] array, int formerIndex, int latterIndex) {
		Contract.checkArgument(array != null, "Array must not be null");

		T element = array[formerIndex];
		array[formerIndex] = array[latterIndex];
		array[latterIndex] = element;
	}

}
