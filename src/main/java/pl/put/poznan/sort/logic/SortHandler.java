package pl.put.poznan.sort.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sort.logic.algorithms.*;
import pl.put.poznan.sort.logic.exceptions.SortHandlerUnsupportedAlgorithmException;
import pl.put.poznan.sort.logic.threads.SortExecutor;
import pl.put.poznan.sort.rest.SortController;

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
        ExecutorService es = Executors.newScheduledThreadPool(4);
        List<Callable<AlgorithmResult>> todo = new ArrayList<>();

        // Start all the algorithms
        for (String algorithmName : this.task.getAlgorithms()) {
            logger.info("Running algorithm: {}", algorithmName);
            List<SortableData> dataCopy = new ArrayList<>(this.dataToSort);

            todo.add(new SortExecutor(
                algorithmName,
                getAlgorithmByName(algorithmName),
                dataCopy,
                this.task.getReverse()
            ));
        }

        List<AlgorithmResult> algorithmsResults = new ArrayList<>();
        try {
            for (Future<AlgorithmResult> f : es.invokeAll(todo)) {
                algorithmsResults.add(f.get());
            }
        } catch (ExecutionException | InterruptedException e) {
            throw (RuntimeException) e.getCause();
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
