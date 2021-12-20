package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.*;
import java.util.List;

public class SortTask {
    @NotNull(message = "You have to provide list of data to sort")
    @Size(min = 1, message = "List has to contain at least one element")
    private List<JsonNode> data;

    private String key;

    @Value("false")
    private boolean reverse;

    @Size(min = 1, message = "List has to contain at least one element")
    private List<String> algorithms;

    public List<JsonNode> getData() {
        return this.data;
    }

    public String getKey() {
        return key;
    }

    public Boolean getReverse() {
        return reverse;
    }

    public List<String> getAlgorithms() {
        return this.algorithms;
    }
}
