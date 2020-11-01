package com.weldnor.netcracker.task1.sorter;

import java.util.Comparator;

public interface Sorter<T> {
    /**
     * Метод для сортировки части массива по возрастанию.
     *
     * @param array      массив, часть которого будет отсортирована.
     * @param comparator критерий, по которому сравниваются элементы.
     * @param start      начальный индекс.
     * @param end        конечный индекс.
     */
    void sort(T[] array, Comparator<T> comparator, int start, int end);

    /**
     * Метод для сортировки массива по возрастанию.
     *
     * @param array      массив, который будет отсортирован.
     * @param comparator критерий, по которому сравниваются элементы.
     */
    default void sort(T[] array, Comparator<T> comparator) {
        if (array.length > 1) {
            sort(array, comparator, 0, array.length - 1);
        }
    }
}