package pl.put.poznan.sort.rest;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
import java.util.Optional;

public class SortTask {
    List<JsonNode> data;
    String key;
    Boolean reverse;
    List<String> algorithmsNames;
    public SortTask(String jsonString){

    }
    public List<JsonNode> getData() {
        return data;
    }

    public void setData(List<JsonNode> data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getReverse() {
        return reverse;
    }

    public void setReverse(Boolean reverse) {
        this.reverse = reverse;
    }

    public List<String> getAlgorithmsNames() {
        return algorithmsNames;
    }

    public void setAlgorithmsNames(List<String> algorithmsNames) {
        this.algorithmsNames = algorithmsNames;
    }

}
