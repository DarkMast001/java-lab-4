package org.example.task_9;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static class A {
        public int a;
        public B b;
        public List<Integer> arr = new ArrayList<>();

        {
            arr.add(1);
            arr.add(2);
            arr.add(3);
        }
    }

    static class B {
        public String str;
        public C c;
    }

    static class C {
        public double doubleValue;
        public A a;
    }

    public static void main(String[] args) {

        A a = new A();
        B b = new B();
        C c = new C();
        a.a = 1;
        a.b = b;
        b.str = "zxc";
        b.c = c;
        c.doubleValue = 2.34;
        c.a = a;
        
        System.out.println(ReflectionToString.toString(a));

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        node1.next = node2;
        node2.next = node1; // Создаем циклическую ссылку

//        System.out.println(ReflectionToString.toString(node1));


    }
}
