package org.example.task_9;

import java.lang.reflect.Field;
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

        StringBuilder result = new StringBuilder();
        Class<?> clazz = obj.getClass();

        result.append(clazz.getName()).append(" {");

        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            String fieldName = field.getName();
            Object fieldValue;
            try {
                fieldValue = field.get(obj);

                if (fieldValue != null && !isPrimitiveOrWrapper(fieldValue.getClass())) {
                    fieldValue = toStringHelper(fieldValue, visited);
                }

                result.append(fieldName).append("=").append(fieldValue);

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

    // Проверка, является ли класс примитивным или оберткой над примитивным типом
    private static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return clazz.isPrimitive() ||
                clazz == Integer.class ||
                clazz == Long.class ||
                clazz == Double.class ||
                clazz == Float.class ||
                clazz == Boolean.class ||
                clazz == Character.class ||
                clazz == Byte.class ||
                clazz == Short.class;
    }

}
