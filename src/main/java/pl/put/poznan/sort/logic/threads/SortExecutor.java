package pl.put.poznan.sort.logic.threads;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sort.logic.AlgorithmResult;
import pl.put.poznan.sort.logic.SortableData;
import pl.put.poznan.sort.logic.algorithms.SortingAlgorithm;
import pl.put.poznan.sort.rest.SortController;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class SortExecutor implements Callable<AlgorithmResult> {
    /**
     * Logger instance
     */
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    public SortExecutor(String name, SortingAlgorithm<SortableData> sorter, List<SortableData> data, boolean reverse) {
        this.name = name;
        this.sorter = sorter;
        this.data = data;
        this.reverse = reverse;
    }

    @Override
    public AlgorithmResult call() throws Exception {
        long startTime = System.currentTimeMillis();
        sorter.sort(data);
        long endTime = System.currentTimeMillis();
        double time = ((double)endTime - startTime) / 1000;

        if (this.reverse) {
            Collections.reverse(data);
        }

        List<JsonNode> sortedDataAsJson = extractJsonListFromSortableData(data);
        return new AlgorithmResult(
            name,
            sortedDataAsJson,
            time
        );
    }

    /**
     * Converts the sorted list of SortableData into list of JSON objects
     * @param sortableData List of sorted data
     * @return List of JSON objects
     */
    protected List<JsonNode> extractJsonListFromSortableData(List<SortableData> sortableData) {
        return sortableData.stream()
            .map(SortableData::getData)
            .collect(Collectors.toList());
    }

    protected String name;
    protected boolean reverse;
    protected List<SortableData> data;
    protected SortingAlgorithm<SortableData> sorter;
}
