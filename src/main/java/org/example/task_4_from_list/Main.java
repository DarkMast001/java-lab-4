package org.example.task_4_from_list;

public class Main {
    public enum Sequence {
        FIRST("First"),
        SECOND("Second");

        private String value;

        Sequence(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    // Безопасность типов
    public static void method(Sequence seq) {
        System.out.println(seq.getValue());
        // ...
    }

    public static void method(String seq) {
        // ...
    }

    // Если есть необходимость в наличии какого-то фиксированного числа состояний
    public enum Status {
        RUNNING, STOPPED, INTERRUPTED;
    }

    public static class Task {
        public Status status;

        // ...
    }

    public static void main(String[] args) {
        // Методы Enum

        // 1. values()
        Status[] statuses = Status.values();
        for (Status s : statuses) {
            System.out.println(s);
        }

        // 2. valueOf()
        Status statusValueOf = Status.valueOf("RUNNING");
        System.out.println(statusValueOf);

        // 3. ordinal()
        for (Status s : Status.values()) {
            System.out.println(s + " " + s.ordinal());
        }

        // 4. compareTo()
        Status statRun = Status.RUNNING;
        Status statStop = Status.STOPPED;
        Status statInter = Status.INTERRUPTED;

        System.out.println("statRun.compareTo(statStop): " + Status.RUNNING.compareTo(statStop));
        System.out.println("statRun.compareTo(statRun): " + statRun.compareTo(statRun));
        System.out.println("statStop.compareTo(statRun): " + statStop.compareTo(statRun));
        System.out.println("statStop.compareTo(statRun): " + statInter.compareTo(statRun));

        // 5. name()
        System.out.println("name(): " + Status.INTERRUPTED.name());

        // 6. getDeclaringClass()
        System.out.println(Status.RUNNING.getDeclaringClass());
    }
}
