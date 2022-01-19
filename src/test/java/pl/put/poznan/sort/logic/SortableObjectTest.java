package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sort.logic.exceptions.SortableObjectKeyNotExistsException;

public class SortableObjectTest {
    private JsonNode SimpleTestNode;
    private JsonNode CompositeTestNode;

    @BeforeEach
    void init() {
        String SimpleJsonString = "{\"a\":\"2\", \"b\":\"3\"}";
        String CompositeJsonString = "{\"a\":{\"b\":\"2\"}}";
        ObjectMapper mapper = new ObjectMapper();
        SimpleTestNode = null;
        CompositeTestNode = null;

        try {
            SimpleTestNode = mapper.readTree(SimpleJsonString);
            CompositeTestNode = mapper.readTree(CompositeJsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
     void compareToTest() {
        SortableObject Soa = new SortableObject(SimpleTestNode, "a");
        SortableObject Sob = new SortableObject(SimpleTestNode, "b");
        assertEquals(0, Soa.compareTo(Soa));
        assertEquals(-1, Soa.compareTo(Sob));
        assertEquals(1, Sob.compareTo(Soa));
    }

    @Test
    void NullKeyTest() {
        Assertions.assertThrows(
			SortableObjectKeyNotExistsException.class,
            ()-> {
				SortableObject So = new SortableObject(SimpleTestNode, null);
			},
			"good"
		);
    }

    @Test
    void KeyNotExistTest() {
        Assertions.assertThrows(
			SortableObjectKeyNotExistsException.class,
        	() -> {
				SortableObject So = new SortableObject(CompositeTestNode, "d");
			},
			"good"
		);
    }

    @Test
    void CompositeKeyTest() {
        Assertions.assertDoesNotThrow(
            () -> {
                SortableObject Soa = new SortableObject(CompositeTestNode, "a.b");
            }
        );
    }
}

