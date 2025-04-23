package org.example.task_6_from_list;

import ru.mephi.Person;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        final ServiceLoader<SimpleService> services = ServiceLoader.load(SimpleService.class);
        for (SimpleService service : services) {
            System.out.println("Echo: " + service.echo("Привет"));
        }

        System.out.println("Рабочая директория: " + System.getProperty("user.dir"));

//        ClassLoader classLoader1 = Main.class.getClassLoader();
//        ClassLoader classLoader2 = Main.class.getClassLoader();
        ClassLoader classLoader1 = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader2 = ClassLoader.getSystemClassLoader();

        try {
            URL[] urls = {
                    new URL("file:///D:/Институт/3%20курс%202%20семестр/Java/lab4/src/main/java/"),
                    new URL("file:///D:/Институт/3%20курс%202%20семестр/Java/lab4/src/otherPath/")
            };

            String className = "ru.mephi.Person";

            URLClassLoader loader1 = new URLClassLoader(urls);
            Class<?> clazz1 = Class.forName(className, true, loader1);
            Object person1 = clazz1.getConstructor(String.class).newInstance("Dima");
            System.out.println("Класс 1 загружен: " + clazz1.getName());

            URLClassLoader loader2 = new URLClassLoader(urls);
            Class<?> clazz2 = Class.forName(className, true, loader2);
            Object person2 = clazz1.getConstructor(String.class).newInstance("Dima");
            System.out.println("Класс 1 загружен: " + clazz1.getName());

//            URLClassLoader customClassLoader = new URLClassLoader(new URL[]{new URL("file:///D:/Институт/3%20курс%202%20семестр/Java/lab4/src/main/java/")});
//            Class<?> clazz1 = customClassLoader.loadClass("ru.mephi.Person");
//            Object person1 = clazz1.getConstructor(String.class).newInstance("Dima");
//            System.out.println("Класс 1 загружен: " + clazz1.getName());
//
//            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
//            Class<?> clazz2 = systemClassLoader.loadClass("ru.mephi.Person");
//            Object person2 = clazz2.getConstructor(String.class).newInstance("Egor");
//            System.out.println("Класс 2 загружен: " + clazz2.getName());

            try {
                clazz1.cast(person2);
                System.out.println("Успешное приведение!");
            } catch (ClassCastException ex) {
                System.out.println("Ошибка: " + ex.getMessage());
            }
        }
        catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }
}
