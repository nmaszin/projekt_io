package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class SortableData {
    private JsonNode data;
    private Comparable<?> value;
    SortableData(JsonNode data){}
}
