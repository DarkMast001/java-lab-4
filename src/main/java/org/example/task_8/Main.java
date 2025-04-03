package org.example.task_8;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("1 Полное имя класса");
        System.out.println(int.class.getName());
        System.out.println(String[].class.getName());
        System.out.println(List.class.getName());
        System.out.println(Queue.Item.class.getName());

        System.out.println("\n2 Имя класса без пакета");
        System.out.println(int.class.getSimpleName());
        System.out.println(String[].class.getSimpleName());
        System.out.println(List.class.getSimpleName());
        System.out.println(Queue.Item.class.getSimpleName());

        System.out.println("\n3 Полное имя с учётом пакета и внутренних классов");
        System.out.println(int.class.getCanonicalName());
        System.out.println(String[].class.getCanonicalName());
        System.out.println(List.class.getCanonicalName());
        System.out.println(Queue.Item.class.getCanonicalName());

        System.out.println("\n4 Строка, точно описывающая тип");
        System.out.println(int.class.getTypeName());
        System.out.println(String[].class.getTypeName());
        System.out.println(new ArrayList<Integer>() {}.getClass().getTypeName());
        System.out.println(Queue.Item.class.getTypeName());

        System.out.println("\n5 Строка, описывающая объявление типа");
        System.out.println(int.class.toGenericString());
        System.out.println(String[].class.toGenericString());
        System.out.println(new ArrayList<Integer>() {}.getClass().toGenericString());
        System.out.println(Queue.Item.class.toGenericString());

        System.out.println("\n6 Строка в формате \"class <имя_класса>\"");
        System.out.println(int.class.toString());
        System.out.println(String[].class.toString());
        System.out.println(List.class.toString());
        System.out.println(Queue.Item.class.toString());
    }
}
