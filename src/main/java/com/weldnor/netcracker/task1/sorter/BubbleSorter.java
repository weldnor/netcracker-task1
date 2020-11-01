package com.weldnor.netcracker.task1.sorter;

import java.util.Comparator;

/**
 * Сортировка пузырьком.
 */
public class BubbleSorter<T> implements Sorter<T> {

    /**
     * @param array      массив, часть которого будет отсортирована.
     * @param comparator критерий, по которому сравниваются элементы.
     * @param start      начальный индекс.
     * @param end        конечный индекс.
     */
    @Override
    public void sort(T[] array, Comparator<T> comparator, int start, int end) {

        for (int i = end; i >= start; i--) {
            for (int j = start; j < i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    swapArrayElements(array, j, j + 1);
                }
            }
        }
    }

    private void swapArrayElements(T[] array, int firstIndex, int secondIndex) {
        T buf = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = buf;
    }

}
