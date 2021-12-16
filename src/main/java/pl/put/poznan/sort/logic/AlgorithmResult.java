package pl.put.poznan.sort.logic;

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;

public class AlgorithmResult {
    private final String name;
    private final List<JsonNode> data;
    private final double time;

    public AlgorithmResult(String name, List<JsonNode> data, double time) {
        this.name = name;
        this.data = data;
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public List<JsonNode> getData() {
        return this.data;
    }

    public double getTime() {
        return this.time;
    }
}
