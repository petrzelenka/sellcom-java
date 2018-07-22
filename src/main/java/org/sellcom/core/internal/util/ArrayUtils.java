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
package org.sellcom.core.internal.util;

import org.sellcom.core.Contract;

public class ArrayUtils {

	private ArrayUtils() {
		// Utility class, not to be instantiated
	}


	public static void shiftLeft(boolean[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex > 0, "From-index must be positive: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = fromIndex; i < toIndex; i++) {
			array[i - 1] = array[i];
		}
	}

	public static void shiftLeft(byte[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex > 0, "From-index must be positive: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = fromIndex; i < toIndex; i++) {
			array[i - 1] = array[i];
		}
	}

	public static void shiftLeft(char[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex > 0, "From-index must be positive: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = fromIndex; i < toIndex; i++) {
			array[i - 1] = array[i];
		}
	}

	public static void shiftLeft(double[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex > 0, "From-index must be positive: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = fromIndex; i < toIndex; i++) {
			array[i - 1] = array[i];
		}
	}

	public static void shiftLeft(float[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex > 0, "From-index must be positive: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = fromIndex; i < toIndex; i++) {
			array[i - 1] = array[i];
		}
	}

	public static void shiftLeft(int[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex > 0, "From-index must be positive: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = fromIndex; i < toIndex; i++) {
			array[i - 1] = array[i];
		}
	}

	public static void shiftLeft(long[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex > 0, "From-index must be positive: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = fromIndex; i < toIndex; i++) {
			array[i - 1] = array[i];
		}
	}

	public static void shiftLeft(short[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex > 0, "From-index must be positive: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = fromIndex; i < toIndex; i++) {
			array[i - 1] = array[i];
		}
	}

	public static <T> void shiftLeft(T[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex > 0, "From-index must be positive: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = fromIndex; i < toIndex; i++) {
			array[i - 1] = array[i];
		}
	}

	public static void shiftRight(boolean[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex >= 0, "From-index must not be negative: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = toIndex; i > fromIndex; i--) {
			array[i] = array[i - 1];
		}
	}

	public static void shiftRight(byte[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex >= 0, "From-index must not be negative: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = toIndex; i > fromIndex; i--) {
			array[i] = array[i - 1];
		}
	}

	public static void shiftRight(char[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex >= 0, "From-index must not be negative: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = toIndex; i > fromIndex; i--) {
			array[i] = array[i - 1];
		}
	}

	public static void shiftRight(double[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex >= 0, "From-index must not be negative: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = toIndex; i > fromIndex; i--) {
			array[i] = array[i - 1];
		}
	}

	public static void shiftRight(float[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex >= 0, "From-index must not be negative: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = toIndex; i > fromIndex; i--) {
			array[i] = array[i - 1];
		}
	}

	public static void shiftRight(int[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex >= 0, "From-index must not be negative: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = toIndex; i > fromIndex; i--) {
			array[i] = array[i - 1];
		}
	}

	public static void shiftRight(long[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex >= 0, "From-index must not be negative: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = toIndex; i > fromIndex; i--) {
			array[i] = array[i - 1];
		}
	}

	public static void shiftRight(short[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex >= 0, "From-index must not be negative: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = toIndex; i > fromIndex; i--) {
			array[i] = array[i - 1];
		}
	}

	public static <T> void shiftRight(T[] array, int fromIndex, int toIndex) {
		Contract.checkArgument(array != null, "Array must not be null");
		Contract.checkArgument(fromIndex >= 0, "From-index must not be negative: {0}", fromIndex);
		Contract.checkArgument(toIndex >= fromIndex, "To-index must not be less than from-index: {1} < {0}", fromIndex, toIndex);

		for (int i = toIndex; i > fromIndex; i--) {
			array[i] = array[i - 1];
		}
	}

}
