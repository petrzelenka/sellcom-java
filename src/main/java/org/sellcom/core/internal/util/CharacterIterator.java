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

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.sellcom.core.Contract;

public class CharacterIterator implements Iterator<Character> {

	private int index = 0;

	private final String string;


	public CharacterIterator(String string) {
		Contract.checkArgument(string != null, "String must not be null");

		this.string = string;
	}


	@Override
	public boolean hasNext() {
		return index < string.length();
	}

	@Override
	public Character next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		return string.charAt(index++);
	}

}
