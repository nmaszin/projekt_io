package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.*;
import java.util.List;

/**
 * Logical representation of user's sorting task
 * It responds to data forwarded in API request
 */
public class SortTask {
    @NotNull(message = "You have to provide list of data to sort")
    @Size(min = 1, message = "List has to contain at least one element")
    private List<JsonNode> data;

    private String key;

    @Value("false")
    private boolean reverse;

    @Size(min = 1, message = "List has to contain at least one element")
    private List<String> algorithms;

    /**
     * Returns list of data as list of parsed JSON tree
     * @return List of data
     */
    public List<JsonNode> getData() {
        return this.data;
    }

    /**
     * Returns key which will be used when sorting JSON objects
     * @return The key
     */
    public String getKey() {
        return key;
    }


    /**
     * Returns a logical value which responds to sorting order
     * When false the direction is ascending, otherwise - descending
     * @return The sorting order
     */
    public Boolean getReverse() {
        return reverse;
    }

    /**
     * Returns list of names of algorithms which should be executed for this task
     * @return List of algorithm's names
     */
    public List<String> getAlgorithms() {
        return this.algorithms;
    }
}
