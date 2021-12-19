package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class SortableData implements Comparable<SortableData> {
    protected final JsonNode data;
    private final String key;

    public SortableData(JsonNode data, String key) {
        this.data = data;
        this.key = key;
    }

    public JsonNode getData() {
        return this.data;
    }

    @Override
    public int compareTo(SortableData other) {
        if (this.data.isNumber() && other.data.isNumber()) {
            Double left = this.data.asDouble();
            Double right = other.data.asDouble();
            return left.compareTo(right);
        } else if (this.data.isTextual() && other.data.isTextual()) {
            String left = this.data.asText();
            String right = other.data.asText();
            return left.compareTo(right);
        } else if (this.data.isObject() && other.data.isObject()) {
            SortableObject left = new SortableObject(this.data, this.key);
            SortableObject right = new SortableObject(other.data, this.key);
            return left.compareTo(right);
        } else {
            throw new SortableDataInvalidDataTypeException("Trying to compare different types");
        }
    }
}

class SortableDataInvalidDataTypeException extends RuntimeException {
    public SortableDataInvalidDataTypeException(String message) {
        super(message);
    }
}

