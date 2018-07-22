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
package org.sellcom.core.util.geography;

import java.util.Objects;

import org.sellcom.core.Contract;

/**
 * Bounds in geographic coordinates.
 *
 * @since 1.5
 */
public class GeoBounds {

	private final double east;

	private final double north;

	private final double south;

	private final double west;


	/**
	 * Creates a new geographic point using the given coordinates.
	 *
	 * @throws IllegalArgumentException if {@code south} is &lt; -90.0 or {@code south} is &gt; +90.0
	 * @throws IllegalArgumentException if {@code north} is &lt; -90.0 or {@code north} is &gt; +90.0
	 * @throws IllegalArgumentException if {@code west} is &lt; -180.0 or {@code west} is &gt; +180.0
	 * @throws IllegalArgumentException if {@code east} is &lt; -180.0 or {@code east} is &gt; +180.0
	 *
	 * @since 1.5
	 */
	public GeoBounds(double south, double north, double west, double east) {
		Contract.checkArgument(south >= -90.0, "South must not be less than -90.0: {0}", south);
		Contract.checkArgument(south <= +90.0, "South must not be greater than +90.0: {0}", south);
		Contract.checkArgument(north >= -90.0, "North must not be less than -90.0: {0}", north);
		Contract.checkArgument(north <= +90.0, "North must not be greater than +90.0: {0}", north);
		Contract.checkArgument(west >= -180.0, "West must not be less than -180.0: {0}", west);
		Contract.checkArgument(west <= +180.0, "West must not be greater than +180.0: {0}", west);
		Contract.checkArgument(east >= -180.0, "East must not be less than -180.0: {0}", east);
		Contract.checkArgument(east <= +180.0, "East must not be greater than +180.0: {0}", east);

		this.north = north;
		this.south = south;
		this.east = east;
		this.west = west;
	}


	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (other instanceof GeoBounds) {
			GeoBounds otherBounds = (GeoBounds) other;

			return Objects.equals(south, otherBounds.south)
					&& Objects.equals(north, otherBounds.north)
					&& Objects.equals(west, otherBounds.west)
					&& Objects.equals(east, otherBounds.east);
		}

		return false;
	}

	/**
	 * Returns the east-most longitude.
	 *
	 * @since 1.5
	 */
	public double getEast() {
		return east;
	}

	/**
	 * Returns the north-most latitude.
	 *
	 * @since 1.5
	 */
	public double getNorth() {
		return north;
	}

	/**
	 * Returns the south-most latitude.
	 *
	 * @since 1.5
	 */
	public double getSouth() {
		return south;
	}

	/**
	 * Returns the west-most longitude.
	 *
	 * @since 1.5
	 */
	public double getWest() {
		return west;
	}

	@Override
	public int hashCode() {
		return Objects.hash(south, north, west, east);
	}

	@Override
	public String toString() {
		return String.format("[%f,%f,%f,%f]", south, north, west, east);
	}

}
