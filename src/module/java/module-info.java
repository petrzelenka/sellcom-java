module org.sellcom.core {
	exports org.sellcom.core;
	exports org.sellcom.core.collection;
	exports org.sellcom.core.collection.concurrent;
	exports org.sellcom.core.i18n;
	exports org.sellcom.core.io;
	exports org.sellcom.core.io.charset;
	exports org.sellcom.core.io.encoding;
	exports org.sellcom.core.io.type;
	exports org.sellcom.core.math;
	exports org.sellcom.core.net;
	exports org.sellcom.core.security.password;
	exports org.sellcom.core.util;
	exports org.sellcom.core.util.cache;
	exports org.sellcom.core.util.function;
	exports org.sellcom.core.util.geography;
	exports org.sellcom.core.util.platform;

	provides java.nio.charset.spi.CharsetProvider
		with org.sellcom.core.internal.io.charset.MoreCharsetsProvider;
	provides java.nio.file.spi.FileTypeDetector
		with org.sellcom.core.internal.io.type.ContentTypeDetector;
}
