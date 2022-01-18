package pl.put.poznan.sort.logic.algorithms;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortTest {
    @Test
    void testSelectionSortForListOfNumbers() {
        List<Integer> data = new ArrayList<>(List.of(45, 235, 562, 6, 235, 2, 634, 23, 52, 643, -214));
        List<Integer> correctSolution = new ArrayList<>(List.of(-214, 2, 6, 23, 45, 52, 235, 235, 562, 634, 643));

        SelectionSort<Integer> s = new SelectionSort<>();
        s.sort(data);

        assertEquals(data, correctSolution);
    }

    @Test
    void testSelectionSortForListOfStrings() {
        List<String> data = new ArrayList<>(List.of("abcd", "woda", "kasdhgkjasd",
                "asdgas", "vnasdjk", "bvasjkdf", "paihgpdsajk", "poaisdhg", "auidhguaisp", "z"));
        List<String> correctSolution = new ArrayList<>(List.of("abcd", "asdgas", "auidhguaisp", "bvasjkdf",
                "kasdhgkjasd", "paihgpdsajk", "poaisdhg", "vnasdjk", "woda", "z"));

        SelectionSort<String> s = new SelectionSort<>();
        s.sort(data);

        assertEquals(data, correctSolution);
    }
}
