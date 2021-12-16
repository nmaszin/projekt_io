package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;

public class SortTask {
    private List<JsonNode> data;
    private String key;
    private Boolean reverse;
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
