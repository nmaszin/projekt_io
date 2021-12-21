package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sort.logic.algorithms.*;
import pl.put.poznan.sort.rest.SortController;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
            double time=sortDataWith(algorithmName, dataCopy);
            List<JsonNode> sortedDataAsJson = extractJsonListFromSortableData(dataCopy);
            AlgorithmResult result = new AlgorithmResult(
                    algorithmName, sortedDataAsJson, time);
            algorithmsResults.add(result);
        }
        return new SortResult(algorithmsResults);
    }

    public double sortDataWith(String algorithmName, List<SortableData> data){
        SortingAlgorithm<SortableData> sorter = getAlgorithmByName(algorithmName);
        long startTime = System.currentTimeMillis();
        sorter.sort(data);
        return ((double)System.currentTimeMillis() - startTime)/1000;
    }

    private SortingAlgorithm<SortableData> getAlgorithmByName(String name){
        SortingAlgorithm<SortableData> sorter;
        switch(name){
            case "bubble":
                return new BubbleSort<>();

            case "gnome":
                return new GnomeSort<>();

            case "insertion":
                return new InsertionSort<>();

            case "merge":
                return new MergeSort<>();

            case "quick":
                return new QuickSort<>();

            case "selection":
                return new SelectionSort<>();
            default:
                return new QuickSort<>();
        }
    }
}


