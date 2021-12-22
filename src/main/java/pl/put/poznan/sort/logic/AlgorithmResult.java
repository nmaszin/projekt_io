package pl.put.poznan.sort.logic;

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Representation of task's result for one specific algorithm
 */
public class AlgorithmResult {
    /**
     * Name of the algorithm
     */
    private final String name;

    /**
     * Sorted list of data
     */
    private final List<JsonNode> data;

    /**
     * Measured algorithm execution duration
     */
    private final double time;

    /**
     * Initializes result with necessary data
     * @param name The name of the algorithm
     * @param data Result list of sorted data
     * @param time Duration of executing the sorting algorithm
     */
    public AlgorithmResult(String name, List<JsonNode> data, double time) {
        this.name = name;
        this.data = data;
        this.time = time;
    }

    /**
     * Returns the name of the algorithm
     * @return The name of the algorithm
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the sorted list of data as list of JSON objects
     * @return The sorted list of data
     */
    public List<JsonNode> getData() {
        return this.data;
    }

    /**
     * Returns the algorithm's execution duration
     * @return Algorithm duration
     */
    public double getTime() {
        return this.time;
    }
}
