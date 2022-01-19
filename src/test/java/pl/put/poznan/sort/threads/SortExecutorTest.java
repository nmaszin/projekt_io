package pl.put.poznan.sort.threads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import pl.put.poznan.sort.logic.AlgorithmResult;
import pl.put.poznan.sort.logic.SortHandler;
import pl.put.poznan.sort.logic.SortResult;
import pl.put.poznan.sort.logic.SortableData;
import pl.put.poznan.sort.logic.algorithms.SortingAlgorithm;
import pl.put.poznan.sort.logic.threads.SortExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//import org.mockito.invocation.InvocationOnMock;

public class SortExecutorTest {
    @Test
    void basicTest() {
        ObjectMapper mapper = new ObjectMapper();
        SortingAlgorithm<SortableData> algorithmMock = (SortingAlgorithm<SortableData>) mock(SortingAlgorithm.class);
        List<SortableData> data = null;
        try {
            data = Arrays.asList(
                new SortableData(mapper.readTree("1"), ""),
                new SortableData(mapper.readTree("9"), ""),
                new SortableData(mapper.readTree("2"), ""),
                new SortableData(mapper.readTree("8"), ""),
                new SortableData(mapper.readTree("5"), ""),
                new SortableData(mapper.readTree("4"), "")
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Answer<List<SortableData>> answer = invocationOnMock -> {
            System.out.println("ala ma kota");
            return Arrays.asList(
                    new SortableData(mapper.readTree("1"), ""),
                    new SortableData(mapper.readTree("2"), ""),
                    new SortableData(mapper.readTree("4"), ""),
                    new SortableData(mapper.readTree("5"), ""),
                    new SortableData(mapper.readTree("8"), ""),
                    new SortableData(mapper.readTree("9"), "")
            );
        };

        doAnswer(answer).when(algorithmMock).sort(any());

        SortExecutor executor = new SortExecutor("test", algorithmMock, data, false);
        try {
            AlgorithmResult result = executor.call();
            assertEquals(result.getName(), "test");
            assertEquals(result.getData().size(), 6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
