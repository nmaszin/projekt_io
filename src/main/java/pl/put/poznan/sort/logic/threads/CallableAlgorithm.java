package pl.put.poznan.sort.logic.threads;

import pl.put.poznan.sort.logic.SortableData;

import java.util.List;
@FunctionalInterface
public interface CallableAlgorithm {
    public double call(String AlgorithmName, List<SortableData> dataToSort);
}
