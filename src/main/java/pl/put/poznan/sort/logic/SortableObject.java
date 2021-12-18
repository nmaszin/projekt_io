package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;

public class SortableObject implements Comparable<SortableObject> {
    private final SortableData value;


    public SortableObject(JsonNode object, String key) {
        if (key == null) {
            throw new SortableObjectKeyNotExistsException("Key is null");
        }

        JsonNode node = object.get(key);
        if (node == null) {
            throw new SortableObjectKeyNotExistsException(
                String.format("Key %s not exists in object", key)
            );
        }

        this.value = new SortableData(node, null);
    }

    @Override
    public int compareTo(SortableObject other) {
        return this.value.compareTo(other.value);
    }
}

class SortableObjectKeyNotExistsException extends RuntimeException {
    public SortableObjectKeyNotExistsException(String message) {
        super(message);
    }
}
