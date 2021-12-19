package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sort.logic.algorithms.BubbleSort;
import pl.put.poznan.sort.rest.SortController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortHandler {
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    private final SortTask task;
    private final List<SortableData> dataToSort;

    private List<SortableData> extractDataListFromJson(List<JsonNode> jsonNode, String key) {
        return jsonNode.stream()
            .map(e -> new SortableData(e, key))
            .collect(Collectors.toList());
    }

    private List<JsonNode> extractJsonListFromSortableData(List<SortableData> sortableData) {
        return sortableData.stream()
            .map(SortableData::getData)
            .collect(Collectors.toList());
    }

    public SortHandler(SortTask task) {
        this.task = task;
        this.dataToSort = extractDataListFromJson(task.getData(), task.getKey());
    }

    public SortResult run() {
        List<AlgorithmResult> algorithmsResults = new ArrayList<>();
        for (String algorithmName : this.task.getAlgorithms()) {
            List<SortableData> dataCopy = new ArrayList<>(this.dataToSort);
            BubbleSort<SortableData> sorter = new BubbleSort<>();
            sorter.sort(dataCopy);

            List<JsonNode> sortedDataAsJson = extractJsonListFromSortableData(dataCopy);
            AlgorithmResult result = new AlgorithmResult(
                    algorithmName, sortedDataAsJson, 0.112);
            algorithmsResults.add(result);
        }

        return new SortResult(algorithmsResults);
    }
}


