package com.weldnor.netcracker.task1.utils.di;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.List;

public class ClassFinder {

    public static List<Class<?>> getSubClassesFromPackages(List<String> packages, Class<?> clazz) {
        List<Class<?>> subClasses = new ArrayList<>();

        for (String pkg : packages) {
            Reflections reflections = new Reflections(pkg, new SubTypesScanner(false));
            subClasses.addAll(reflections.getSubTypesOf(clazz));
        }
        return subClasses;
    }
}
