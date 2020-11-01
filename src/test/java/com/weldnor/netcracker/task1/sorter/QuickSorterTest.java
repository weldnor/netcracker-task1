package com.weldnor.netcracker.task1.sorter;

import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class QuickSorterTest {
    private final Sorter<Integer> sorter = new QuickSorter<>();

    private final Comparator<Integer> comp = (o1, o2) -> {
        if (o1.equals(o2)) return 0;
        return o1 > o2 ? 1 : -1;
    };


    @Test
    public void sort_EmptyArray() {
        Integer[] array = {};

        assertThatCode(() -> {
            sorter.sort(array, comp);
        }).doesNotThrowAnyException();
    }

    @Test
    public void sort_ArrayWithOneElement() {
        Integer[] array = {5};

        sorter.sort(array, comp);

        assertThat(array).containsExactly(5);
    }

    @Test
    public void sort_ArrayWithSomeElements() {
        Integer[] array1 = {9, -1, 10, 3, 5, 2, 3, -1};
        Integer[] array2 = {9, -1, 10, 3, 20, 5, 2, 3, -1};

        sorter.sort(array1, comp);
        sorter.sort(array2, comp);

        assertThat(array1).containsOnly(-1, -1, 2, 3, 3, 5, 9, 10);
        assertThat(array2).containsOnly(-1, -1, 2, 3, 3, 5, 9, 10, 20);
    }
}