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
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.StringBuilders.removeAll;
import static org.sellcom.core.StringBuilders.replaceAll;

import org.junit.Test;

public class StringBuildersTest {

	@Test
	public void testRemoveAll_char() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "Donau dampf schiff fahrt");
		removeAll(builder, ' ');
		assertThat(builder.toString(), is(equalTo("Donaudampfschifffahrt")));

		setContents(builder, "Kraft fahrzeug haft pflicht versicherung");
		removeAll(builder, ' ');
		assertThat(builder.toString(), is(equalTo("Kraftfahrzeughaftpflichtversicherung")));

		setContents(builder, "Glas flächen reinigung");
		removeAll(builder, ' ');
		assertThat(builder.toString(), is(equalTo("Glasflächenreinigung")));

		setContents(builder, "Sieben tausend zwei hundert vier und fünfzig");
		removeAll(builder, ' ');
		assertThat(builder.toString(), is(equalTo("Siebentausendzweihundertvierundfünfzig")));
	}

	@Test
	public void testRemoveAll_String() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "backpack");
		removeAll(builder, "ack");
		assertThat(builder.toString(), is(equalTo("bp")));

		setContents(builder, "braindrain");
		removeAll(builder, "rain");
		assertThat(builder.toString(), is(equalTo("bd")));

		setContents(builder, "output");
		removeAll(builder, "ut");
		assertThat(builder.toString(), is(equalTo("op")));
	}

	@Test
	public void testReplaceAll_char() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "Ächtung");
		replaceAll(builder, 'Ä', 'A');
		assertThat(builder.toString(), is(equalTo("Achtung")));

		setContents(builder, "drücken");
		replaceAll(builder, 'ü', 'u');
		assertThat(builder.toString(), is(equalTo("drucken")));

		setContents(builder, "précédence");
		replaceAll(builder, 'é', 'e');
		assertThat(builder.toString(), is(equalTo("precedence")));

		setContents(builder, "résumé");
		replaceAll(builder, 'é', 'e');
		assertThat(builder.toString(), is(equalTo("resume")));

		setContents(builder, "Société Générale");
		replaceAll(builder, 'é', 'e');
		assertThat(builder.toString(), is(equalTo("Societe Generale")));

		setContents(builder, "zählen");
		replaceAll(builder, 'ä', 'a');
		assertThat(builder.toString(), is(equalTo("zahlen")));
	}

	@Test
	public void testReplaceAll_String() {
		StringBuilder builder = new StringBuilder();

		setContents(builder, "façon");
		replaceAll(builder, "ç", "shi");
		assertThat(builder.toString(), is(equalTo("fashion")));

		setContents(builder, "Geschäftstätigkeit");
		replaceAll(builder, "ä", "ae");
		assertThat(builder.toString(), is(equalTo("Geschaeftstaetigkeit")));

		setContents(builder, "manœuvre");
		replaceAll(builder, "œ", "oe");
		assertThat(builder.toString(), is(equalTo("manoeuvre")));

		setContents(builder, "Weißrußland");
		replaceAll(builder, "ß", "ss");
		assertThat(builder.toString(), is(equalTo("Weissrussland")));
	}


	private static void setContents(StringBuilder builder, String contents) {
		builder.setLength(0);
		builder.append(contents);
	}

}
