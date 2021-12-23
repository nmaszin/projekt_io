package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.util.ArrayUtils;
import pl.put.poznan.sort.logic.algorithms.*;
import pl.put.poznan.sort.rest.SortController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Integrates application logic into main class, which can be easily used from the controller
 */
public class SortHandler {
    /**
     * Logger instance
     */
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    /**
     * Task which will be handled while executing run()
     */
    private final SortTask task;

    /**
     * List of data to sort
     */
    private final List<SortableData> dataToSort;

    /**
     * Converts list of parsed JSON objects into list of SortableData
     * @param jsonList List of parsed JSON objects
     * @param key Key given by user in the task
     * @return List of SortableData
     */
    private List<SortableData> extractDataListFromJson(List<JsonNode> jsonList, String key) {
        return jsonList.stream()
            .map(e -> new SortableData(e, key))
            .collect(Collectors.toList());
    }

    /**
     * Converts the sorted list of SortableData into list of JSON objects
     * @param sortableData List of sorted data
     * @return List of JSON objects
     */
    private List<JsonNode> extractJsonListFromSortableData(List<SortableData> sortableData) {
        return sortableData.stream()
            .map(SortableData::getData)
            .collect(Collectors.toList());
    }

    /**
     * Initializes sort handler with user's task
     * @param task Task which will be handled
     */
    public SortHandler(SortTask task) {
        this.task = task;
        this.dataToSort = extractDataListFromJson(task.getData(), task.getKey());
    }

    /**
     * Execute the given task
     * @return Result of the whole task execution
     */
    public SortResult run() {
        logger.info("Start handling task");
        List<AlgorithmResult> algorithmsResults = new ArrayList<>();
        for (String algorithmName : this.task.getAlgorithms()) {
            List<SortableData> dataCopy = new ArrayList<>(this.dataToSort);

            double time = sortDataWith(algorithmName, dataCopy);
            if (this.task.getReverse()) {
                Collections.reverse(dataCopy);
            }

            List<JsonNode> sortedDataAsJson = extractJsonListFromSortableData(dataCopy);
            AlgorithmResult result = new AlgorithmResult(
                    algorithmName, sortedDataAsJson, time);

            algorithmsResults.add(result);
        }

        logger.info("Handling task has ended successfully");
        return new SortResult(algorithmsResults);
    }

    /**
     * Sorts list of SortableData with the specific algorithm
     * @param algorithmName Name of the algorithm which will be sorted
     * @param data List of data which will be sorted in-place
     * @return Duration of sorting process in seconds
     */
    public double sortDataWith(String algorithmName, List<SortableData> data) {
        logger.info("Running algorithm: {}", algorithmName);
        SortingAlgorithm<SortableData> sorter = getAlgorithmByName(algorithmName);

        long startTime = System.currentTimeMillis();
        sorter.sort(data);
        long endTime = System.currentTimeMillis();
        return ((double)endTime - startTime) / 1000;
    }

    /**
     * Returns sorter for given sorting algorithm
     * @param name Name of algorithm
     * @return The sorter
     */
    private SortingAlgorithm<SortableData> getAlgorithmByName(String name) {
        switch (name) {
            case "bubble": return new BubbleSort<>();
            case "gnome": return new GnomeSort<>();
            case "insertion": return new InsertionSort<>();
            case "merge": return new MergeSort<>();
            case "quick": return new QuickSort<>();
            case "selection": return new SelectionSort<>();
            default: return new QuickSort<>();
        }
    }
}


