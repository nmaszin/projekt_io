package pl.put.poznan.sort.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sort.logic.algorithms.*;
import pl.put.poznan.sort.logic.exceptions.SortHandlerUnsupportedAlgorithmException;
import pl.put.poznan.sort.rest.SortController;
import pl.put.poznan.sort.logic.threads.SortThread;

/**
 * Integrates application logic into main class, which can be easily used from the controller
 */
public class SortHandler {
    /**
     * Logger instance
     */
    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    /**
     * List of supported algorithms
     */
    private static final List<SortingAlgorithm<SortableData>> supportedAlgorithms = List.of(
            new BubbleSort<>(),
            new GnomeSort<>(),
            new InsertionSort<>(),
            new MergeSort<>(),
            new QuickSort<>(),
            new SelectionSort<>()
    );

    /**
     * Identifier (name) to algorithm mapping
     */
    private static final Map<String, SortingAlgorithm<SortableData>> algorithmsMapping =
        supportedAlgorithms.stream()
            .collect(Collectors.toMap(
                SortingAlgorithm<SortableData>::getName,
                Function.identity()
            ));

    /**
     * Task which will be handled while executing run()
     */
    private final SortTask task;

    /**
     * List of data to sort
     */
    private final List<SortableData> dataToSort;

    /**
    private final HashMap<String, SortingAlgorithm<SortableData>> algorithmsMapping;

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
        List<SortThread> algorithmsThreads=new LinkedList<>();
        //Start all the algorithms
        for (String algorithmName : this.task.getAlgorithms()) {
            List<SortableData> dataCopy = new ArrayList<>(this.dataToSort);
            SortThread algorithmThread = new SortThread(this::sortDataWith, algorithmName, dataCopy);
            algorithmThread.start();
            algorithmsThreads.add(algorithmThread);
        }

        //Wait for algorithms' results
        for (SortThread algThread  : algorithmsThreads) {
            try {
                algThread.join();
            } catch (InterruptedException e) {
                logger.info("Algorithm execution interrupted");
            };

            double time = algThread.getTime();
            List<SortableData> sortedData = algThread.getData();
            if (this.task.getReverse()) {
                Collections.reverse(sortedData);
            }

            // Save results
            List<JsonNode> sortedDataAsJson = extractJsonListFromSortableData(sortedData);
            AlgorithmResult result = new AlgorithmResult(
                    algThread.getAlgorithmName(), sortedDataAsJson, time);
            algorithmsResults.add(result);
        }

        logger.info("Handling task has ended successfully");
        return new SortResult(algorithmsResults);
    }

    /**
     * Converts list of parsed JSON objects into list of SortableData
     * @param jsonList List of parsed JSON objects
     * @param key Key given by user in the task
     * @return List of SortableData
     */
    protected List<SortableData> extractDataListFromJson(List<JsonNode> jsonList, String key) {
        return jsonList.stream()
            .map(e -> new SortableData(e, key))
            .collect(Collectors.toList());
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

    /**
     * Sorts list of SortableData with the specific algorithm
     * @param algorithmName Name of the algorithm which will be sorted
     * @param data List of data which will be sorted in-place
     * @return Duration of sorting process in seconds
     */
    protected double sortDataWith(String algorithmName, List<SortableData> data) {
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
    protected SortingAlgorithm<SortableData> getAlgorithmByName(String name) {
        SortingAlgorithm<SortableData> sorter = algorithmsMapping.get(name);

        if (sorter == null) {
            throw new SortHandlerUnsupportedAlgorithmException(
                String.format("Unsupported algorithm: %s", name)
            );
        }

        return sorter;
    }
}
