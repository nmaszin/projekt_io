package pl.put.poznan.sort.logic;

import java.util.List;

/**
 * Result of execution of all sorting algorithms
 */
public class SortResult {
    /**
     * Results of execution of all algorithms
     */
    private final List<AlgorithmResult> results;

    /**
     * Initializes SortResult with list of results for specific algorithms
     * @param results List of results for specific sorting algorithms
     */
    public SortResult(List<AlgorithmResult> results) {
        this.results = results;
    }

    /**
     * Returns list of results for specific sorting algorithms
     * @return List of results for specific sorting algorithms
     */
    public List<AlgorithmResult> getResults() {
        return this.results;
    }
}
