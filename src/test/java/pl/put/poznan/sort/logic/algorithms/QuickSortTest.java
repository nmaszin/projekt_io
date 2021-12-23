package pl.put.poznan.sort.logic.algorithms;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortTest {
    @Test
    void testQuickSortForListOfNumbers() {
        List<Integer> data = new ArrayList<>(List.of(54, 51, 33, 47, 92, 25, 81, 64, 69, 56));
        List<Integer> correctSolution = new ArrayList<>(List.of(25, 33, 47, 51, 54, 56, 64, 69, 81, 92));

        QuickSort<Integer> sorter = new QuickSort<>();
        sorter.sort(data);

        assertEquals(data, correctSolution);
    }

    @Test
    void testQuickSortForListOfStrings() {
        List<String> data = new ArrayList<>(List.of("yiiripfb", "xdadplcj", "llzvnmgsdn",
            "qejlgkaz", "dotvjdtcd", "kxoumktxj", "vdcctq", "onseqtvtk", "xgwebtmao", "jxhshii"));
        List<String> correctSolution = new ArrayList<>(List.of("dotvjdtcd", "jxhshii", "kxoumktxj",
            "llzvnmgsdn", "onseqtvtk", "qejlgkaz", "vdcctq", "xdadplcj", "xgwebtmao", "yiiripfb"));

        QuickSort<String> sorter = new QuickSort<>();
        sorter.sort(data);

        assertEquals(data, correctSolution);
    }
}
