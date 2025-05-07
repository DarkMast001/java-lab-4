package org.example.task_9_from_list;

import org.example.SimpleService;
import org.example.SimpleServiceImpl;

public class Main {
    public static void main(String[] args) {
        DIServiceLoader<SimpleService> loader = new DIServiceLoader<>(SimpleService.class);

        SimpleService defaultService = loader.getBean("simpser");
        System.out.println(defaultService.echo("Simple сервис!"));

        SimpleService anotherBean = loader.getBean(SimpleServiceImpl.class);
        System.out.println(anotherBean.echo("Опять Simple сервис"));

        for (SimpleService service : loader) {
            System.out.println("Сервис: " + service.getClass().getName());
        }
    }
}
