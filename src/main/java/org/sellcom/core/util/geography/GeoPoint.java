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
import org.sellcom.core.math.MoreMath;

/**
 * Point in geographic coordinates.
 *
 * @since 1.5
 */
public class GeoPoint {

	private final double latitude;

	private final double longitude;


	/**
	 * Creates a new geographic point using the given latitude and longitude.
	 *
	 * @throws IllegalArgumentException if {@code latitude} is &lt; -90.0 or {@code latitude} is &gt; +90.0
	 * @throws IllegalArgumentException if {@code longitude} is &lt; -180.0 or {@code longitude} is &gt; +180.0
	 *
	 * @since 1.5
	 */
	public GeoPoint(double latitude, double longitude) {
		Contract.checkArgument(latitude >= -90.0, "Latitude must not be less than -90.0: {0}", latitude);
		Contract.checkArgument(latitude <= +90.0, "Latitude must not be greater than +90.0: {0}", latitude);
		Contract.checkArgument(longitude >= -180.0, "Latitude must not be less than -180.0: {0}", longitude);
		Contract.checkArgument(longitude <= +180.0, "Latitude must not be greater than +180.0: {0}", longitude);

		this.latitude = latitude;
		this.longitude = longitude;
	}


	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}

		if (other instanceof GeoPoint) {
			GeoPoint otherPoint = (GeoPoint) other;

			return MoreMath.equals(latitude, otherPoint.latitude, 0.0)
				&& MoreMath.equals(longitude, otherPoint.longitude, 0.0);
		}

		return false;
	}

	/**
	 * Return the latitude of this geographic point in degrees.
	 *
	 * @since 1.5
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * Return the longitude of this geographic point in degrees.
	 *
	 * @since 1.5
	 */
	public double getLongitude() {
		return longitude;
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude);
	}

	@Override
	public String toString() {
		return String.format("[%f,%f]", latitude, longitude);
	}

}
