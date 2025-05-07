package org.example.task_6_from_list;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {
    public static void main(String[] args) {
//        final ServiceLoader<SimpleService> services = ServiceLoader.load(SimpleService.class);
//        for (SimpleService service : services) {
//            System.out.println("Echo: " + service.echo("Привет"));
//        }

        // Запускать из консоли java Main.java

        System.out.println("Рабочая директория: " + System.getProperty("user.dir"));

        try {
            URL person1Url = new File("out/person1").toURI().toURL();
            System.out.println(new File("out/person1").getAbsolutePath());
            URL person2Url = new File("out/person2").toURI().toURL();
            System.out.println(new File("out/person2").getAbsolutePath());

            URLClassLoader loader1 = new URLClassLoader(new URL[]{person1Url});
            URLClassLoader loader2 = new URLClassLoader(new URL[]{person2Url});

            Class<?> personClass1 = loader1.loadClass("ru.mephi.Person");
            Class<?> personClass2 = loader2.loadClass("ru.mephi.Person");

            Object person1 = personClass1.getDeclaredConstructor(String.class).newInstance("Dima");

            System.out.println("person1 classloader: " + personClass1.getClassLoader());
            System.out.println("person2 classloader: " + personClass2.getClassLoader());

            try {
                personClass2.cast(person1);
                System.out.println("Успешное приведение");
            } catch (ClassCastException ex) {
                System.out.println(ex.getMessage());
            }

            loader1.close();
            loader2.close();
        }
        catch (Exception e) {
            System.out.println("Ошибка: " + e);
            e.printStackTrace();
        }
    }
}
