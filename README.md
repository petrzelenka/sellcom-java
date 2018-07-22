sellcom.org Core Functions for Java
===================================

[![Build Status](https://travis-ci.org/petrzelenka/sellcom-java.svg?branch=master)](https://travis-ci.org/petrzelenka/sellcom-java)
[![Maven Central](https://img.shields.io/maven-central/v/org.sellcom/sellcom-java.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A"org.sellcom"%20AND%20a%3A"sellcom-java")
[![Javadoc](https://javadoc.io/badge/org.sellcom/sellcom-java.svg?color=blue)](https://javadoc.io/doc/org.sellcom/sellcom-java)
[![License](https://img.shields.io/badge/license-Apache_2.0-blue.svg)](https://apache.org/licenses/LICENSE-2.0)

Compatibility
-------------
This library is compatible with Java 10 and newer.

Latest Release
--------------
To add a dependency on sellcom-java using Gradle, use the following:

```
dependencies {
	compile 'org.sellcom:sellcom-java:2.0.0'
}
```

To add a dependency using Maven:

```
<dependency>
	<groupId>org.sellcom</groupId>
	<artifactId>sellcom-java</artifactId>
	<version>2.0.0</version>
</dependency>
```

Module
------
To declare a dependency on sellcom-java in `module-info.java`, use the following `requires` statement:

```
module my.module {
	requires org.sellcom.core;
}
```

Acknowledgements
----------------
Development of this library used to be partially supported by [MANX Technologies, s.r.o.](http://manx.cz/)
