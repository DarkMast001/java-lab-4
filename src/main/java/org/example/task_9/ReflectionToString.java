package org.example.task_9;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ReflectionToString {
    public static String toString(Object obj) {
        Set<Object> visited = new HashSet<>();
        return toStringHelper(obj, visited);
    }

    private static String toStringHelper(Object obj, Set<Object> visited) {
        if (obj == null) {
            return "null";
        }

        if (visited.contains(obj)) {
            return "[cyclic reference]";
        }

        visited.add(obj);

        Class<?> clazz = obj.getClass();

        try {
            Method toStringMethod = clazz.getMethod("toString");
            if (!toStringMethod.getDeclaringClass().equals(Object.class)) {
                return (String) toStringMethod.invoke(obj);
            }
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        }

        StringBuilder result = new StringBuilder();

        result.append(clazz.getName()).append(" {");

        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            result.append(field.getName());

            field.setAccessible(true);

            String fieldName = field.getName();
            Object fieldValue;
            try {

                fieldValue = field.get(obj);

                if (fieldValue != null && !isPrimitiveOrWrapper(fieldValue.getClass())) {
                    fieldValue = toStringHelper(fieldValue, visited);
                }

                result.append("=").append(fieldValue);

                if (i < fields.length - 1) {
                    result.append(", ");
                }
            } catch (IllegalAccessException e) {
                result.append(fieldName).append("=[inaccessible]");
            }
        }

        result.append("}");
        return result.toString();
    }

    private static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() ||
                clazz == Integer.class ||
                clazz == Long.class ||
                clazz == Double.class ||
                clazz == Float.class ||
                clazz == Boolean.class ||
                clazz == Character.class ||
                clazz == Byte.class ||
                clazz == Short.class ||
                clazz == String.class;
    }

}
