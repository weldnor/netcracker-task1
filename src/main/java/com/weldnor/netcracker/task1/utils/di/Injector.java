package com.weldnor.netcracker.task1.utils.di;

import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class Injector {
    @SuppressWarnings("checkstyle:ConstantName")
    private static final Logger log = Logger.getLogger(Injector.class);

    private Injector() {
    }

    /**
     * @param object обьект, куда иньектируются зависимости
     * @param <T>    тип обьекта
     * @return обьект с примененными зависимостями
     */
    public static <T> T inject(T object) {
        log.debug("inject " + object);

        Class<?> clazz = object.getClass();
        log.debug("injectable object class: " + clazz);

        List<String> packages = getPackages(clazz);
        log.debug("packages:" + packages);

        List<Field> injectableFields = getInjectableFields(clazz);
        log.debug("injectable fields:" + injectableFields);

        for (Field injectableField : injectableFields) {
            log.debug(String.format("try inject %s field", injectableField));

            Class<?> dependencyClass = getDependencyClassFromInjectableField(injectableField);
            log.debug("dependency class: " + dependencyClass);

            List<Class<?>> dependencySubClasses = ClassFinder.getSubClassesFromPackages(packages, dependencyClass);
            log.debug("dependency subclasses: " + dependencySubClasses);

            injectField(injectableField, dependencySubClasses, object);
        }

        return object;
    }

    private static List<Field> getInjectableFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            // пропускаем неаннотированные поля
            if (field.getAnnotation(Injectable.class) == null) {
                continue;
            }
            // пропускаем примитивы
            if (field.getType().isPrimitive()) {
                throw new RuntimeException("field type is primitive");
            }
            fields.add(field);
        }
        return fields;
    }

    private static List<String> getPackages(Class<?> clazz) { //TODO rename
        Configuration configurationAnnotation = clazz.getAnnotation(Configuration.class);
        return Arrays.asList(configurationAnnotation.packages());
    }

    private static Class<?> getDependencyClassFromInjectableField(Field injectableField) {
        if (Collection.class.isAssignableFrom(injectableField.getType())) {
            ParameterizedType listType = (ParameterizedType) injectableField.getGenericType();
            return (Class<?>) listType.getActualTypeArguments()[0];
        } else {
            return injectableField.getType();
        }
    }

    private static void injectField(Field injectableField, List<Class<?>> dependencySubClasses, Object obj) {
        Object value;

        if (Collection.class.isAssignableFrom(injectableField.getType())) {
            value = createInstances(dependencySubClasses);
        } else {
            if (dependencySubClasses.size() > 1) {
                throw new RuntimeException("dependency subclasses count more then 1");
            }
            value = createInstance(dependencySubClasses.get(0));
        }

        try {
            injectableField.setAccessible(true);
            injectableField.set(obj, value);
        } catch (Exception e) {
            throw new RuntimeException("cant inject field " + injectableField, e);
        }
    }

    private static List<Object> createInstances(List<Class<?>> classes) {
        List<Object> instances = new ArrayList<>();

        for (Class<?> clazz : classes) {
            instances.add(createInstance(clazz));
        }
        return instances;
    }

    private static Object createInstance(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("cant create instance of " + clazz, e);
        }
    }
}
