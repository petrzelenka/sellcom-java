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
package org.sellcom.core;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.MoreObjects.isDescendantOf;
import static org.sellcom.core.MoreObjects.isInstanceOf;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class MoreObjectsTest {

	@Test
	public void testIsDescendantOf() {
		TreeSet<Object> set = new TreeSet<>();

		assertThat(isDescendantOf(set, AbstractCollection.class), is(true));
		assertThat(isDescendantOf(set, AbstractSet.class), is(true));
		assertThat(isDescendantOf(set, Cloneable.class), is(true));
		assertThat(isDescendantOf(set, Collection.class), is(true));
		assertThat(isDescendantOf(set, Iterable.class), is(true));
		assertThat(isDescendantOf(set, NavigableSet.class), is(true));
		assertThat(isDescendantOf(set, Object.class), is(true));
		assertThat(isDescendantOf(set, Serializable.class), is(true));
		assertThat(isDescendantOf(set, Set.class), is(true));
		assertThat(isDescendantOf(set, SortedSet.class), is(true));
		assertThat(isDescendantOf(set, TreeSet.class), is(true));
	}

	@Test
	public void testIsInstanceOf() {
		TreeSet<Object> set = new TreeSet<>();

		assertThat(isInstanceOf(set, AbstractCollection.class), is(false));
		assertThat(isInstanceOf(set, AbstractSet.class), is(false));
		assertThat(isInstanceOf(set, Cloneable.class), is(false));
		assertThat(isInstanceOf(set, Collection.class), is(false));
		assertThat(isInstanceOf(set, Iterable.class), is(false));
		assertThat(isInstanceOf(set, NavigableSet.class), is(false));
		assertThat(isInstanceOf(set, Object.class), is(false));
		assertThat(isInstanceOf(set, Serializable.class), is(false));
		assertThat(isInstanceOf(set, Set.class), is(false));
		assertThat(isInstanceOf(set, SortedSet.class), is(false));
		assertThat(isInstanceOf(set, TreeSet.class), is(true));
	}

}
