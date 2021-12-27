package pl.put.poznan.sort.logic.algorithms;

import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {
    @Test
    void testInsertionSortForListOfNumbers() {
        List<Integer> data = new ArrayList<>(List.of(23, 9, 33, 74, 29, 25, 23, 264, 69, -1));
        List<Integer> correctSolution = new ArrayList<>(List.of(-1, 9, 23, 23, 25, 29, 33, 69, 74, 264));

        InsertionSort<Integer> sorter = new InsertionSort<>();
        sorter.sort(data);

        assertEquals(data, correctSolution);
    }
    @Test
    void testMergeSortForListOfStrings(){
        List<String> data = new ArrayList<>(List.of("abcd", "woda", "kasdhgkjasd",
                "asdgas", "vnasdjk", "bvasjkdf", "paihgpdsajk", "poaisdhg", "auidhguaisp", "z"));
        List<String> correctSolution = new ArrayList<>(List.of("abcd", "asdgas", "auidhguaisp", "bvasjkdf",
                "kasdhgkjasd", "paihgpdsajk", "poaisdhg", "vnasdjk", "woda", "z"));

        InsertionSort<String> sorter = new InsertionSort<>();
        sorter.sort(data);

        assertEquals(data, correctSolution);

    }
}
