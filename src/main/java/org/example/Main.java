package org.example;

import java.util.ArrayList;
import java.util.Arrays;

class Shape {
    String name;
    private String getName() { return this.name; }
}

class Circle extends Shape {
    public String getName() { return this.name; }
}

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(ArrayList.class.getFields()));
    }
}