package pl.put.poznan.sort.logic.algorithms;

import java.util.List;

public abstract class SortingAlgorithm<T extends Comparable<T>> {
    public abstract void sort(List<T> list);

    public String getName() {
        return this.name;
    }

    protected SortingAlgorithm(String name) {
        this.name = name;
    }

    protected final String name;
}
