package org.example.task_9_from_list;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.function.Supplier;

public class DIServiceLoader<T> implements Iterable<T> {
    private Class<T> serviceClass;
    private Map<String, Supplier<T>> beansByName = new HashMap<>();
    private Map<Class<?>, Supplier<T>> beansByType = new HashMap<>();

    public DIServiceLoader(Class<T> serviceClass) {
        this.serviceClass = serviceClass;
        loadBeans();
    }

    private void loadBeans() {
        String serviceName = serviceClass.getName();
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            Enumeration<URL> resources = classLoader.getResources("META-INF/services/" + serviceName);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        line = line.trim();
                        if (line.isEmpty() || line.startsWith("#")) {
                            continue;
                        }

                        String[] parts = line.split("#", 2);

                        String className = parts[0].trim();
                        String beanName;

                        if (parts.length == 1) {
                            beanName = className.substring(className.lastIndexOf('.') + 1);
                        }
                        else {
                            beanName = parts[1].trim();
                        }

                        if (beansByName.containsKey(beanName)) {
                            throw new IllegalArgumentException("Дубликат: " + beanName);
                        }

                        Supplier<T> supplier = createSupplier(className);
                        beansByName.put(beanName, supplier);

                        try {
                            Class<?> clazz = Class.forName(className);
                            beansByType.put(clazz, supplier);
                        }
                        catch (ClassNotFoundException e) {
                            throw new RuntimeException("Не получилось загрузить класс: " + className, e);
                        }
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки сервиса: " + serviceName, e);
        }
    }

    private Supplier<T> createSupplier(String className) {
        return () -> {
            try {
                Class<?> clazz = Class.forName(className);
                return serviceClass.cast(clazz.getDeclaredConstructor().newInstance());
            }
            catch (Exception e) {
                throw new RuntimeException("Не получилось сделать Instance: " + className, e);
            }
        };
    }

    public T getBean(String beanName) {
        Supplier<T> supplier = beansByName.get(beanName);
        if (supplier == null) {
            throw new IllegalArgumentException("Нет бина с именем: " + beanName);
        }
        return supplier.get();
    }

    public T getBean(Class<?> beanClass) {
        Supplier<T> supplier = beansByType.get(beanClass);
        if (supplier == null) {
            throw new IllegalArgumentException("Нет бина для класса: " + beanClass.getName());
        }
        return supplier.get();
    }

    public Iterator<T> iterator() {
        return beansByName.values().stream()
                .map(Supplier::get)
                .iterator();
    }
}
