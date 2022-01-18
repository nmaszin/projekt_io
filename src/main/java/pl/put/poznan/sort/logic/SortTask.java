package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import pl.put.poznan.sort.rest.SortController;

import javax.annotation.PostConstruct;
import javax.validation.constraints.*;
import java.util.List;

/**
 * Logical representation of user's sorting task
 * It responds to data forwarded in API request
 */
public class SortTask {
    /**
     * Data to sort (supported: integers, floats, text or JSON objects)
     */
    @NotNull(message = "You have to provide list of data to sort")
    @Size(min = 1, message = "List has to contain at least one element")
    private List<JsonNode> data;


    /**
     * Key for extracting sortable value while sorting JSON objects
     */
    private String key;

    /**
     * Value that represents order of sorting (false -> ascending, true -> descending)
     * It's false by default
     */
    @Value("false")
    private boolean reverse;

    /**
     * List of algorithms identifiers
     * When null, app try to match the best algorithm for task
     */
    @Size(min = 1, message = "List has to contain at least one element")
    private List<String> algorithms = List.of("quick");

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


    private static final Logger logger = LoggerFactory.getLogger(SortController.class);

    @PostConstruct
    private void setDefaultAlgorithmIfNecessary() {
        if (this.algorithms == null) {
            this.algorithms = List.of("quick");
        }
    }
}
