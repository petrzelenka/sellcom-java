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
package org.sellcom.core.io.type;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_16;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.io.type.ContentTypes.getCharset;
import static org.sellcom.core.io.type.ContentTypes.setCharset;

import org.junit.Test;

public class ContentTypesTest {

	@Test
	public void testGetCharset() {
		assertThat(getCharset("text-plain"), is(equalTo(UTF_8)));
		assertThat(getCharset("text-plain;charset=iso-8859-1"), is(equalTo(ISO_8859_1)));
		assertThat(getCharset("text-plain;charset=us-ascii"), is(equalTo(US_ASCII)));
		assertThat(getCharset("text-plain;charset=utf-8"), is(equalTo(UTF_8)));
		assertThat(getCharset("text-plain;charset=utf-16"), is(equalTo(UTF_16)));
	}

	@Test
	public void testSetCharset() {
		assertThat(setCharset("text-plain", UTF_8), is(equalTo("text-plain;charset=utf-8")));
		assertThat(setCharset("text-plain;charset=us-ascii", ISO_8859_1), is(equalTo("text-plain;charset=iso-8859-1")));
		assertThat(setCharset("text-plain;charset=us-ascii", US_ASCII), is(equalTo("text-plain;charset=us-ascii")));
		assertThat(setCharset("text-plain;charset=us-ascii", UTF_8), is(equalTo("text-plain;charset=utf-8")));
		assertThat(setCharset("text-plain;charset=us-ascii", UTF_16), is(equalTo("text-plain;charset=utf-16")));
	}

}
