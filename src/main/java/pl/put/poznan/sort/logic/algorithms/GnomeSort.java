package pl.put.poznan.sort.logic.algorithms;

import java.util.Collections;
import java.util.List;

/**
 * Class that implements gnome sort algorithm
 * @param <T> Type of data which user would like to sort
 */
public class GnomeSort<T extends  Comparable<T>> extends SortingAlgorithm<T> {
    public GnomeSort() {
        super("gnome");
    }

    @Override
    public void sort(List<T> list) {
        int id = 0;
        while (id < list.size()) {
            if (id == 0) {
                id++;
            }
            if (list.get(id).compareTo(list.get(id - 1)) >= 0) {
                id++;
            } else {
                Collections.swap(list, id - 1, id);
                id--;
            }
        }
    }
}
