package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class SortableData implements Comparable<SortableData> {
    private final JsonNode data;

    public SortableData(JsonNode data) {
        this.data = data;
    }

    public JsonNode getData() {
        return this.data;
    }

    @Override
    public int compareTo(SortableData sortableData) {
        // TODO: implement
        return 0;
    }
}
