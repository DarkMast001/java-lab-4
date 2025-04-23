package org.example.task_5_from_list;

public class Person {
    private String name;
    private int age;
    public String nickname;

    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void sayHello() {
        System.out.println("Hello, my name is " + name);
    }

    public void introduce() {
        System.out.println("I am " + name + ", and I am " + age + " years old.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}