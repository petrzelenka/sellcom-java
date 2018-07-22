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
package org.sellcom.core.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

import org.sellcom.core.Contract;
import org.sellcom.core.internal.util.ArrayUtils;
import org.sellcom.core.util.MoreArrays;

/**
 * Sparse array mapping integers to doubles.
 *
 * @since 1.0
 */
public class SparseDoubleArray implements Cloneable {

	private static final int DEFAULT_CAPACITY = 10;

	private double[] elements;

	private int[] indices;

	private int size;


	/**
	 * Creates an empty sparse array.
	 *
	 * @since 1.0
	 */
	public SparseDoubleArray() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates an empty sparse array with the given capacity.
	 *
	 * @since 1.0
	 */
	public SparseDoubleArray(int initialCapacity) {
		elements = new double[initialCapacity];
		indices = new int[initialCapacity];
		size = 0;
	}


	/**
	 * Removes all elements from this array.
	 *
	 * @since 1.0
	 */
	public void clear() {
		size = 0;
	}

	@Override
	public SparseDoubleArray clone() {
		SparseDoubleArray clone = null;

		try {
			clone = (SparseDoubleArray) super.clone();
			clone.elements = elements.clone();
			clone.indices = indices.clone();
		} catch (CloneNotSupportedException e) {
			; // Ignore
		}

		return clone;
	}

	/**
	 * Checks whether this array contains the given element.
	 *
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public boolean containsElement(double element, double tolerance) {
		return MoreArrays.indexOf(elements, 0, size, element, tolerance) >= 0;
	}

	/**
	 * Checks whether this array contains the given index.
	 *
	 * @throws IllegalArgumentException if {@code index} is negative
	 *
	 * @since 1.0
	 */
	public boolean containsIndex(int index) {
		Contract.checkArgument(index >= 0, "Index must not be negative: {0}", index);

		return Arrays.binarySearch(indices, 0, size, index) >= 0;
	}

	/**
	 * Increases the capacity of this array, if necessary, to ensure that it can hold at least the given number of elements.
	 *
	 * @throws IllegalArgumentException if {@code minCapacity} is negative
	 *
	 * @since 1.0
	 */
	public void ensureCapacity(int minCapacity) {
		Contract.checkArgument(minCapacity >= 0, "Minimum capacity must not be negative: {0}", minCapacity);

		if (minCapacity > elements.length) {
			int targetCapacity = Math.max(minCapacity, 2 * elements.length);

			elements = Arrays.copyOf(elements, targetCapacity);
			indices = Arrays.copyOf(indices, targetCapacity);
		}
	}

	/**
	 * Performs the given action for each element in this array until all elements have been processed or the action throws an exception.
	 * Actions are performed in the order of the element indices.
	 * Exceptions thrown by the action are relayed to the caller.
	 *
	 * @throws IllegalArgumentException if {@code action} is {@code null}
	 *
	 * @since 1.0
	 */
	public void forEach(BiConsumer<Integer, Double> action) {
		Contract.checkArgument(action != null, "Action must not be null");

		for (int i = 0; i < size; i++) {
			action.accept(indices[i], elements[i]);
		}
	}

	/**
	 * Returns the element at the given index in this array.
	 * Returns the given default value if there is no element in this array at the given index.
	 *
	 * @throws IllegalArgumentException if {@code index} is negative
	 *
	 * @since 1.0
	 */
	public double getOrDefault(int index, double defaultValue) {
		Contract.checkArgument(index >= 0, "Index must not be negative: {0}", index);

		int internalIndex = Arrays.binarySearch(indices, 0, size, index);
		if (internalIndex < 0) {
			return defaultValue;
		} else {
			return elements[internalIndex];
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this array.
	 * Returns {@code -1} if this array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public int indexOf(double element, double tolerance) {
		int internalIndex = MoreArrays.indexOf(elements, 0, size, element, tolerance);

		return (internalIndex == -1) ? -1 : indices[internalIndex];
	}

	/**
	 * Checks whether this array is empty.
	 *
	 * @since 1.0
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this array.
	 * Returns {@code -1} if this array does not contain the element.
	 *
	 * @throws IllegalArgumentException if {@code tolerance} is negative
	 *
	 * @since 1.0
	 */
	public int lastIndexOf(double element, double tolerance) {
		int internalIndex = MoreArrays.lastIndexOf(elements, 0, size, element, tolerance);

		return (internalIndex == -1) ? -1 : indices[internalIndex];
	}

	/**
	 * Removes and returns the element at the given index in this array.
	 * Returns the given default value if there is no element in this array at the given index.
	 *
	 * @throws IllegalArgumentException if {@code index} is negative
	 *
	 * @since 1.0
	 */
	public double remove(int index, double defaultValue) {
		Contract.checkArgument(index >= 0, "Index must not be negative: {0}", index);

		int internalIndex = Arrays.binarySearch(indices, 0, size, index);
		if (internalIndex < 0) {
			return defaultValue;
		} else {
			double element = elements[internalIndex];

			ArrayUtils.shiftLeft(elements, internalIndex + 1, size);
			ArrayUtils.shiftLeft(indices, internalIndex + 1, size);
			size -= 1;

			return element;
		}
	}

	/**
	 * Sets the element at the given index in this array to the given value.
	 *
	 * @throws IllegalArgumentException if {@code index} is negative
	 *
	 * @since 1.0
	 */
	public void set(int index, double value) {
		Contract.checkArgument(index >= 0, "Index must not be negative: {0}", index);

		int internalIndex = Arrays.binarySearch(indices, 0, size, index);
		if (internalIndex < 0) {
			ensureCapacity(size + 1);

			internalIndex = -internalIndex - 1;

			ArrayUtils.shiftRight(elements, internalIndex, size);
			elements[internalIndex] = value;
			ArrayUtils.shiftRight(indices, internalIndex, size);
			indices[internalIndex] = index;

			size += 1;
		} else {
			int elementIndex = indices[internalIndex];

			elements[elementIndex] = value;
		}
	}

	/**
	 * If there is no element in this array at the given index, sets it to the given value.
	 *
	 * @throws IllegalArgumentException if {@code index} is negative
	 *
	 * @since 1.0
	 */
	public void setIfAbsent(int index, double value) {
		Contract.checkArgument(index >= 0, "Index must not be negative: {0}", index);

		int internalIndex = Arrays.binarySearch(indices, 0, size, index);
		if (internalIndex < 0) {
			ensureCapacity(size + 1);

			internalIndex = -internalIndex - 1;

			ArrayUtils.shiftRight(elements, internalIndex, size);
			elements[internalIndex] = value;
			ArrayUtils.shiftRight(indices, internalIndex, size);
			indices[internalIndex] = index;

			size += 1;
		}
	}

	/**
	 * Returns the number of elements in this array.
	 *
	 * @since 1.0
	 */
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		if (size == 0) {
			return "{}";
		}

		List<String> elementStrings = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			elementStrings.add(String.format("[%d]=%s", indices[i], elements[i]));
		}

		return String.format("{%s}", String.join(", ", elementStrings));
	}

	/**
	 * Trims the capacity of this array to its current size.
	 *
	 * @since 1.0
	 */
	public void trimToSize() {
		if (elements.length < size) {
			elements = Arrays.copyOf(elements, size);
			indices = Arrays.copyOf(indices, size);
		}
	}

}
