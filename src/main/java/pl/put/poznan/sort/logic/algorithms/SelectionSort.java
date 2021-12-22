package pl.put.poznan.sort.logic.algorithms;

import java.util.Collections;
import java.util.List;

/**
 * Class that implements selection sort algorithm
 * @param <T> Type of data which user would like to sort
 */
public class SelectionSort<T extends  Comparable<T>> extends SortingAlgorithm<T> {
    public SelectionSort() {
        super("selection");
    }

    @Override
    public void sort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int id = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(id)) < 0) {
                    id = j;
                }
            }
            Collections.swap(list, id, i);
        }
    }
}

