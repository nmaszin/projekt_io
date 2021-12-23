package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sort.logic.exceptions.SortableObjectKeyNotExistsException;


/**
 * Wrapper for JSON objects, which make them comparable (and sortable thus)
 */
public class SortableObject implements Comparable<SortableObject> {
    /**
     * Scalar value extracted from object (at key property)
     */
    private final SortableData value;

    /**
     * Creates an instance of SortableData class
     * @param object JSON object as parsed JSON tree
     * @param key Key which will be used to extract a scalar value from object
     */
    public SortableObject(JsonNode object, String key) {
        if (key == null) {
            throw new SortableObjectKeyNotExistsException("Key is null");
        }

        String[] keyParts = key.split("\\.");
        for (String keyPart : keyParts) {
            object = object.get(keyPart);
            if (object == null) {
                throw new SortableObjectKeyNotExistsException(
                    String.format("Key path %s not exists in each object", key)
                );
            }
        }

        this.value = new SortableData(object, null);
    }

    @Override
    public int compareTo(SortableObject other) {
        return this.value.compareTo(other.value);
    }
}
