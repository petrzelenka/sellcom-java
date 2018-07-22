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
package org.sellcom.core.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

import org.sellcom.core.Contract;
import org.sellcom.core.Strings;
import org.sellcom.core.util.MoreArrays;

public class ReflectionUtils {

	private ReflectionUtils() {
		// Utility class, not to be instantiated
	}


	@SuppressWarnings("unchecked")
	public static <T> T[] createArray(Class<T> type, int length) {
		Contract.checkArgument(type != null, "Type must not be null");
		Contract.checkArgument(length >= 0, "Length must not be negative: {0}", length);

		return (T[]) Array.newInstance(type, length);
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] createArray(T[] reference, int length) {
		Contract.checkArgument(reference != null, "Reference must not be null");

		return (T[]) createArray(reference.getClass().getComponentType(), length);
	}

	public static <T extends Exception> T createException(Class<T> type) {
		Contract.checkArgument(type != null, "Type must not be null");

		try {
			Constructor<T> constructor = type.getConstructor();

			return constructor.newInstance();
		} catch (ReflectiveOperationException e) {
			throw new IllegalArgumentException(String.format("Exception class %s is not instantiable", type.getSimpleName()), e);
		}
	}

	public static <T extends Exception> T createException(Class<T> type, String message) {
		Contract.checkArgument(type != null, "Type must not be null");

		try {
			Constructor<T> constructor = type.getConstructor(String.class);

			return constructor.newInstance(message);
		} catch (ReflectiveOperationException e) {
			throw new IllegalArgumentException(String.format("Exception class %s is not instantiable", type.getSimpleName()), e);
		}
	}

	public static <T extends Exception> T createException(Class<T> type, String message, Throwable cause) {
		Contract.checkArgument(type != null, "Type must not be null");

		try {
			Constructor<T> constructor = type.getConstructor(String.class, Throwable.class);

			return constructor.newInstance(message, cause);
		} catch (ReflectiveOperationException e) {
			throw new IllegalArgumentException(String.format("Exception class %s is not instantiable", type.getSimpleName()), e);
		}
	}

	public static <T extends Exception> T createException(Class<T> type, Throwable cause) {
		Contract.checkArgument(type != null, "Type must not be null");

		try {
			Constructor<T> constructor = type.getConstructor(Throwable.class);

			return constructor.newInstance(cause);
		} catch (ReflectiveOperationException e) {
			throw new IllegalArgumentException(String.format("Exception class %s is not instantiable", type.getSimpleName()), e);
		}
	}

	public static void decrementClassFieldValue(Class<?> target, String fieldName) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(fieldName), "Field name must not be null or empty");

		Field field = findField(target, fieldName);
		if (field == null) {
			throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" not found", fieldName));
		}

		decrementFieldValue(null, field);
	}

	public static void decrementFieldValue(Object target, Field field) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(field != null, "Field must not be null");

		Class<?> fieldType = field.getType();
		if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
			Integer value = (Integer) getFieldValue(target, field);
			value = Math.decrementExact(value);
			setFieldValue(target, field, value);

			return;
		}
		if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
			Long value = (Long) getFieldValue(target, field);
			value = Math.decrementExact(value);
			setFieldValue(target, field, value);

			return;
		}

		throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" is not of decrementable type", field.getName()));
	}

	public static void decrementInstanceFieldValue(Object target, String fieldName) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(fieldName), "Field name must not be null or empty");

		Field field = findField(target.getClass(), fieldName);
		if (field == null) {
			throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" not found", fieldName));
		}

		decrementFieldValue(target, field);
	}

	public static void ensureModifiable(Field field) throws IllegalAccessException {
		Contract.checkArgument(field != null, "Field must not be null");

		try {
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		} catch (NoSuchFieldException e) {
			; // Ignore
		}
	}

	public static Field findField(Class<?> type, String fieldName) {
		Contract.checkArgument(type != null, "Type must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(fieldName), "Field name must not be null or empty");

		try {
			return type.getField(fieldName);
		} catch (NoSuchFieldException e) {
			; // Ignore
		}

		do {
			Field[] declaredFields = type.getDeclaredFields();
			for (Field field : declaredFields) {
				if (Objects.equals(field.getName(), fieldName)) {
					return field;
				}
			}

			type = type.getSuperclass();
		} while (type != null);

		return null;
	}

	public static Method findMethod(Class<?> type, String methodName, Class<?>[] parameterTypes) {
		Contract.checkArgument(type != null, "Type must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(methodName), "Method name must not be null or empty");

		try {
			return type.getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			; // Ignore
		}

		parameterTypes = MoreArrays.nullToEmpty(parameterTypes);

		do {
			Method[] declaredMethods = type.getDeclaredMethods();
			for (Method method : declaredMethods) {
				if (Objects.equals(method.getName(), methodName) && Arrays.equals(method.getParameterTypes(), parameterTypes)) {
					return method;
				}
			}

			type = type.getSuperclass();
		} while (type != null);

		return null;
	}

	public static <T> T getClassFieldValue(Class<?> target, String fieldName) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(fieldName), "Field name must not be null or empty");

		Field field = findField(target, fieldName);
		if (field == null) {
			throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" not found", fieldName));
		}

		return getFieldValue(null, field);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue(Object target, Field field) throws IllegalAccessException {
		Contract.checkArgument(field != null, "Field must not be null");

		return (T) field.get(target);
	}

	public static <T> T getInstanceFieldValue(Object target, String fieldName) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(fieldName), "Field name must not be null or empty");

		Field field = findField(target.getClass(), fieldName);
		if (field == null) {
			throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" not found", fieldName));
		}

		return getFieldValue(target, field);
	}

	public static void incrementClassFieldValue(Class<?> target, String fieldName) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(fieldName), "Field name must not be null or empty");

		Field field = findField(target.getClass(), fieldName);
		if (field == null) {
			throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" not found", fieldName));
		}

		incrementFieldValue(null, field);
	}

	public static void incrementFieldValue(Object target, Field field) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(field != null, "Field must not be null");

		Class<?> fieldType = field.getType();
		if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
			Integer value = (Integer) getFieldValue(target, field);
			value = Math.incrementExact(value);
			setFieldValue(target, field, value);

			return;
		}
		if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
			Long value = (Long) getFieldValue(target, field);
			value = Math.incrementExact(value);
			setFieldValue(target, field, value);

			return;
		}

		throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" is not of incrementable type", field.getName()));
	}

	public static void incrementInstanceFieldValue(Object target, String fieldName) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(fieldName), "Field name must not be null or empty");

		Field field = findField(target.getClass(), fieldName);
		if (field == null) {
			throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" not found", fieldName));
		}

		incrementFieldValue(target, field);
	}

	public static <T> T invokeClassMethod(Class<?> target, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws IllegalAccessException, InvocationTargetException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(methodName), "Method name must not be null or empty");

		Method method = findMethod(target, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException(MessageFormat.format("Method \"{0}\" not found", methodName));
		}

		return invokeMethod(null, method, arguments);
	}

	public static <T> T invokeInstanceMethod(Object target, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws IllegalAccessException, InvocationTargetException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(methodName), "Method name must not be null or empty");

		Method method = findMethod(target.getClass(), methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException(MessageFormat.format("Method \"{0}\" not found", methodName));
		}

		return invokeMethod(target, method, arguments);
	}

	@SuppressWarnings("unchecked")
	public static <T> T invokeMethod(Object target, Method method) throws IllegalAccessException, InvocationTargetException {
		Contract.checkArgument(method != null, "Method must not be null");

		return (T) method.invoke(target);
	}

	@SuppressWarnings("unchecked")
	public static <T> T invokeMethod(Object target, Method method, Object[] arguments) throws IllegalAccessException, InvocationTargetException {
		Contract.checkArgument(method != null, "Method must not be null");

		return (T) method.invoke(target, arguments);
	}

	public static void setClassFieldValue(Class<?> target, String fieldName, Object value) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(fieldName), "Field name must not be null or empty");

		Field field = findField(target, fieldName);
		if (field == null) {
			throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" not found", fieldName));
		}

		setFieldValue(null, field, value);
	}

	public static void setFieldValue(Object target, Field field, Object value) throws IllegalAccessException {
		Contract.checkArgument(field != null, "Field must not be null");

		field.set(target, value);
	}

	public static void setInstanceFieldValue(Object target, String fieldName, Object value) throws IllegalAccessException {
		Contract.checkArgument(target != null, "Target object must not be null");
		Contract.checkArgument(!Strings.isNullOrEmpty(fieldName), "Field name must not be null or empty");

		Field field = findField(target.getClass(), fieldName);
		if (field == null) {
			throw new IllegalArgumentException(MessageFormat.format("Field \"{0}\" not found", fieldName));
		}

		setFieldValue(target, field, value);
	}

}
