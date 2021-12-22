package pl.put.poznan.sort.logic.algorithms;

import java.util.Collections;
import java.util.List;

/**
 * Class that implements bubble sort algorithm
 * @param <T> Type of data which user would like to sort
 */
public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    public BubbleSort() {
        super("bubble");
    }

    @Override
    public void sort(List<T> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0)
                {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }
}
