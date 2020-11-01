package com.weldnor.netcracker.task1.sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Быстрая сортировка.
 */
public class QuickSorter<T> implements Sorter<T> {

    /**
     * Сортировка массива методом быстрой сортировки.
     *
     * @param array      массив, часть которого будет отсортирована.
     * @param comparator критерий, по которому сравниваются элементы.
     * @param start      начальный индекс.
     * @param end        конечный индекс.
     */
    public void sort(T[] array, Comparator<T> comparator, int start, int end) {
        int size = end - start;

        if (size <= 1)
            return;

        int middleIndex = (end + start) / 2;
        T middle = array[middleIndex];

        List<T> greaterThenMiddle = new ArrayList<>();
        List<T> lessThenMiddle = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            if (comparator.compare(array[i], middle) > 0) {
                greaterThenMiddle.add(array[i]);
            } else {
                lessThenMiddle.add(array[i]);
            }
        }

        for (int i = 0; i < lessThenMiddle.size(); i++) {
            array[start + i] = lessThenMiddle.get(i);
        }
        for (int i = 0; i < greaterThenMiddle.size(); i++) {
            array[start + lessThenMiddle.size() + i] = greaterThenMiddle.get(i);
        }

        if (lessThenMiddle.size() > 0)
            sort(array, comparator, start, middleIndex);
        if (greaterThenMiddle.size() > 0)
            sort(array, comparator, middleIndex + 1, end);
    }

}
