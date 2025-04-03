package org.example.task_12;

import java.io.PrintStream;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        // Количество итераций для тестирования
        int iterations = 1_000_000;

        try {
            long startTimeNormal = System.currentTimeMillis();
            PrintStream out = System.out;
            for (int i = 0; i < iterations; i++) {
                out.println("Hello, World");
            }
            long endTimeNormal = System.currentTimeMillis();

            // 2. Вызов метода через рефлексию
            long startTimeReflection = System.currentTimeMillis();
            Class<?> printStreamClass = Class.forName("java.io.PrintStream");
            Method printlnMethod = printStreamClass.getDeclaredMethod("println", String.class);
            Object printStream = System.out;
            for (int i = 0; i < iterations; i++) {
                printlnMethod.invoke(printStream, "Hello, World");
            }
            long endTimeReflection = System.currentTimeMillis();

            // 3. Вывод результатов
            long timeNormal = endTimeNormal - startTimeNormal;
            long timeReflection = endTimeReflection - startTimeReflection;

            System.out.println("Время обычного вызова: " + timeNormal + " миллисекунд");
            System.out.println("Время вызова через рефлексию: " + timeReflection + " миллисекунд");
            System.out.println("Разница: " + (timeReflection - timeNormal) + " миллисекунд");

        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.err.println("Метод не найден: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
