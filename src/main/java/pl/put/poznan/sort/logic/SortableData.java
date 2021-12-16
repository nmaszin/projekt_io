package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class SortableData {
    private final JsonNode data;
    private Comparable<?> value;

    SortableData(JsonNode data) {
        this.data = data;
    }
}
