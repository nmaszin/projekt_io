package pl.put.poznan.sort.logic;

import com.fasterxml.jackson.databind.JsonNode;
import pl.put.poznan.sort.rest.SortTask;

import java.util.List;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class SortHandler {
    private List<SortableData> dataTSort;
    private List<AlgorithmResult> algorithmsResults;

    public SortHandler(SortTask sortTask){}
    private List<SortableData> extractDataListFromJson(List<JsonNode> jsonNode){}
    private void getAlgorithmsList(SortTask task){}
    public SortResult run(){
        return new SortResult();
    }
}
