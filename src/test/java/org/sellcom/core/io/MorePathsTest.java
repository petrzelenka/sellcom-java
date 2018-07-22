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
package org.sellcom.core.io;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.sellcom.core.io.MorePaths.getFileExtension;
import static org.sellcom.core.io.MorePaths.getFileName;
import static org.sellcom.core.io.MorePaths.getFileNameWithoutExtension;

import java.nio.file.Paths;

import org.junit.Test;

public class MorePathsTest {

	@Test
	public void testGetFileExtension() {
		assertThat(getFileExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/.gitignore")), is(equalTo("")));
		assertThat(getFileExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/.settings/org.eclipse.jdt.core.prefs")), is(equalTo("prefs")));
		assertThat(getFileExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/src/main/java/cz/manx/oak/base/io/Paths2.java")), is(equalTo("java")));
		assertThat(getFileExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/build.gradle")), is(equalTo("gradle")));
		assertThat(getFileExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-javaoak/LICENSE")), is(equalTo("")));
	}

	@Test
	public void testGetFileName() {
		assertThat(getFileName(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/.gitignore")), is(equalTo(".gitignore")));
		assertThat(getFileName(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/.settings/org.eclipse.jdt.core.prefs")), is(equalTo("org.eclipse.jdt.core.prefs")));
		assertThat(getFileName(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/src/main/java/cz/manx/oak/base/io/Paths2.java")), is(equalTo("Paths2.java")));
		assertThat(getFileName(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/build.gradle")), is(equalTo("build.gradle")));
		assertThat(getFileName(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/LICENSE")), is(equalTo("LICENSE")));
	}

	@Test
	public void testGetFileNameWithoutExtension() {
		assertThat(getFileNameWithoutExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/.gitignore")), is(equalTo(".gitignore")));
		assertThat(getFileNameWithoutExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/.settings/org.eclipse.jdt.core.prefs")), is(equalTo("org.eclipse.jdt.core")));
		assertThat(getFileNameWithoutExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/src/main/java/cz/manx/oak/base/io/Paths2.java")), is(equalTo("Paths2")));
		assertThat(getFileNameWithoutExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/build.gradle")), is(equalTo("build")));
		assertThat(getFileNameWithoutExtension(Paths.get("/home/nobody/Programming/sellcom/sellcom-java/LICENSE")), is(equalTo("LICENSE")));
	}

}
