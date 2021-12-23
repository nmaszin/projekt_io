package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sort.logic.exceptions.SortableDataInvalidDataTypeException;

/**
 * Wrapper for JSON data, which makes them comparable (and sortable thus)
 */
public class SortableData implements Comparable<SortableData> {
    /**
     * Parsed JSON which is representation of data user would like to sort
     */
    protected final JsonNode data;

    /**
     * Key which will be used to extract scalar value from data if data is an JSON object
     */
    protected final String key;

    /**
     * Creates an instance of SortableData class
     * @param data Data as parsed JSON tree
     * @param key Key which will be used to extract a scalar value if data is an JSON object
     */
    public SortableData(JsonNode data, String key) {
        this.data = data;
        this.key = key;
    }

    /**
     * Returns the name of the algorithm
     * @return The name of the algorithm
     */
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
            if (this.key == null) {
                throw new SortableDataInvalidDataTypeException(
                    "Trying to sort by property that isn't scalar value " +
                    "(probably you should add another key part after dot)"
                );
            }

            SortableObject left = new SortableObject(this.data, this.key);
            SortableObject right = new SortableObject(other.data, this.key);
            return left.compareTo(right);
        } else {
            throw new SortableDataInvalidDataTypeException("Trying to compare different types");
        }
    }
}
