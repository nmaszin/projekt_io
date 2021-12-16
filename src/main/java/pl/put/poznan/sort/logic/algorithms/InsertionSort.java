package pl.put.poznan.sort.logic.algorithms;

import java.util.List;

public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    public InsertionSort() {
        super("insertion");
    }

    @Override
    public void sort(List<T> list) {
        for (int i = 1; i < list.size(); ++i) {
            T key = list.get(i);

            int j = i - 1;
            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }

            list.set(j + 1, key);
        }
    }
}