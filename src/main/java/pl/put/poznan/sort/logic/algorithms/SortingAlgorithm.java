package pl.put.poznan.sort.logic.algorithms;

import java.util.List;

/**
 * Base class for sorting algorithms
 * @param <T> Type of data which user would like to sort
 */
public abstract class SortingAlgorithm<T extends Comparable<T>> {
    /**
     * Sorts given list of data in ascending order
     * @param list List of data which user would like to sort
     */
    public abstract void sort(List<T> list);

    /**
     * Returns the name of the algorithm
     * @return The name of the algorithm
     */
    public String getName() {
        return this.name;
    }

    /**
     * Initializes the name of the algorithm
     * Each derived class should call this
     * @param name The name of the algorithm
     */
    protected SortingAlgorithm(String name) {
        this.name = name;
    }

    /**
     * Name of the algorithm
     */
    private final String name;
}
