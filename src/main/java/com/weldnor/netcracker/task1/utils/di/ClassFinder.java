package com.weldnor.netcracker.task1.utils.di;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.List;

public final class ClassFinder {

    private ClassFinder() {
    }

    /**
     * @param packages - пакеты проекта, в которых ищутся подклассы
     * @param clazz    - класс, для которого ищутся подклассы
     * @return список подклассов
     */
    public static List<Class<?>> getSubClassesFromPackages(List<String> packages, Class<?> clazz) {
        List<Class<?>> subClasses = new ArrayList<>();

        for (String pkg : packages) {
            Reflections reflections = new Reflections(pkg, new SubTypesScanner(false));
            subClasses.addAll(reflections.getSubTypesOf(clazz));
        }
        return subClasses;
    }
}
