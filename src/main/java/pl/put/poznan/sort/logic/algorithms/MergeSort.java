package pl.put.poznan.sort.logic.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that implements merge sort algorithm
 * @param <T> Type of data which user would like to sort
 */
public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    public MergeSort() {
        super("merge");
    }

    @Override
    public void sort(List<T> list) {
        if (list.size() == 1) {
            return;
        }

        List<T> left = new ArrayList<>(list.subList(0, list.size() / 2));
        List<T> right = new ArrayList<>(list.subList(list.size() / 2, list.size()));

        sort(left);
        sort(right);
        merge(left, right, list);
    }

    private void merge(List<T> left, List<T> right, List<T> result) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) <= 0) {
                result.set(resultIndex, left.get(leftIndex));
                leftIndex++;
            } else {
                result.set(resultIndex, right.get(rightIndex));
                rightIndex++;
            }

            resultIndex++;
        }

        while (leftIndex < left.size()) {
            result.set(resultIndex, left.get(leftIndex));
            leftIndex++;
            resultIndex++;
        }

        while (rightIndex < right.size()) {
            result.set(resultIndex, right.get(rightIndex));
            rightIndex++;
            resultIndex++;
        }
    }
}
