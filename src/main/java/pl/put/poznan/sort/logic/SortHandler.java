package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sort.logic.SortTask;
import pl.put.poznan.sort.rest.SortController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortHandler {
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    private SortTask task;
    private List<SortableData> dataToSort;

    private List<SortableData> extractDataListFromJson(List<JsonNode> jsonNode) {
        return jsonNode.stream()
            .map(SortableData::new)
            .collect(Collectors.toList());
    }

    public SortHandler(SortTask task) {
        this.task = task;
        this.dataToSort = extractDataListFromJson(task.getData());
    }

    public SortResult run() {
        List<AlgorithmResult> algorithmsResults = new ArrayList<>();
        for (String algorithmName : this.task.getAlgorithms()) {
            AlgorithmResult result = new AlgorithmResult(
                algorithmName, this.task.getData(), 0.112);
            algorithmsResults.add(result);
        }

        return new SortResult(algorithmsResults);
    }
}
