package org.example.task_4_5;

public class Main {
    public static void main(String[] args) {
        Circle original = new Circle(0, 0, 5);
        Circle copy = original.clone();

        System.out.println("Оригинал: " + original.getCentre());
        System.out.println("Копия: " + copy.getCentre());

        original.moveBy(10, 10);

        System.out.println("После движения оригинала:");
        System.out.println("Оригинал: " + original.getCentre());
        System.out.println("Копия: " + copy.getCentre());
    }
}
