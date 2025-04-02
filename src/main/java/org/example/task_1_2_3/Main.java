package org.example.task_1_2_3;

public class Main {
    public static void main(String[] args) {
        Point p = new Point(1, 2);
        LabeledPoint lp = new LabeledPoint("point", 3, 4);
        System.out.println(p);
        System.out.println(lp);
        System.out.println(lp.equals(p));
    }
}
