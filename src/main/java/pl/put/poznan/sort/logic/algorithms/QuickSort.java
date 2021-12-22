package pl.put.poznan.sort.logic.algorithms;

import java.util.Collections;
import java.util.List;

/**
 * Class that implements quick sort algorithm
 * @param <T> Type of data which user would like to sort
 */
public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    public QuickSort() {
        super("quick");
    }

    @Override
    public void sort(List<T> list) {
        sortBackend(list, 0, list.size() - 1);
    }

    private void sortBackend(List<T> list, int beginIndex, int lastIndex) {
        if (beginIndex >= lastIndex) {
            return;
        }

        int pivotIndex = lastIndex - 1;
        pivotIndex = partition(list, beginIndex, lastIndex, pivotIndex);
        sortBackend(list, beginIndex, pivotIndex - 1);
        sortBackend(list, pivotIndex + 1, lastIndex);
    }

    private int partition(List<T> list, int beginIndex, int lastIndex, int pivotIndex) {
        int limitIndex = beginIndex;
        Collections.swap(list, pivotIndex, lastIndex);
        pivotIndex = lastIndex;

        for (int index = beginIndex; index < lastIndex; ++index) {
            if (list.get(index).compareTo(list.get(lastIndex)) < 0) {
                Collections.swap(list, limitIndex, index);
                limitIndex++;
            }
        }

        Collections.swap(list, limitIndex, pivotIndex);
        return limitIndex;
    }
}
