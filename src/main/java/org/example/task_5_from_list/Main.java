package org.example.task_5_from_list;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {

        try{
            Class<?> clazz = Class.forName("org.example.task_5_from_list.Person");

            System.out.println("=====Конструкторы=====");
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                System.out.println("Конструктор: " + constructor);
            }

            Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
            Object personInstance = constructor.newInstance("Dima", 20);

            System.out.println("\n=====Поля=====");
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                System.out.println("Поле: " + field.getName());
                field.setAccessible(true);

                if (field.getName().equals("name")) {
                    System.out.println("Значение 'name': " + field.get(personInstance));
                } else if (field.getName().equals("age")) {
                    System.out.println("Значение 'age': " + field.get(personInstance));
                } else if (field.getName().equals("nickname")) {
                    field.set(personInstance, "darkmast001");
                    System.out.println("Новое значение 'nickname': " + field.get(personInstance));
                }
            }

            System.out.println("\n=====Методы=====");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("Метод: " + method.getName());
                method.setAccessible(true);

                if (method.getName().equals("sayHello")) {
                    method.invoke(personInstance);
                } else if (method.getName().equals("introduce")) {
                    method.invoke(personInstance);
                }
            }
        }
        catch (Exception ex) {
            System.err.println("Произошла ошибка: " + ex.getMessage());
        }

    }
}
