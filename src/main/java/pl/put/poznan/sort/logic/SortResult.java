package pl.put.poznan.sort.logic;

import java.util.List;

public class SortResult {
    private final List<AlgorithmResult> results;

    public SortResult(List<AlgorithmResult> results) {
        this.results = results;
    }

    public List<AlgorithmResult> getResults() {
        return this.results;
    }
}
