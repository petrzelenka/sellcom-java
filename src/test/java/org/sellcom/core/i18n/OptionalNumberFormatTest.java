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
package org.sellcom.core.i18n;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.i18n.OptionalNumberFormat.getInstance;

import java.text.ParseException;

import org.junit.Test;

public class OptionalNumberFormatTest {

	@Test
	public void testFormat() {
		OptionalNumberFormat format = getInstance();

		assertThat(format.format(null), is(equalTo("")));
	}

	@Test
	public void testParse() throws ParseException {
		OptionalNumberFormat format = getInstance();

		assertThat(format.parseObject(""), is(nullValue()));
	}

}
