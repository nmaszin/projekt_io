package pl.put.poznan.sort.logic.threads;

import pl.put.poznan.sort.logic.SortableData;

import java.util.List;

public class SortThread extends Thread{
    private volatile double time;
    private final String algorithmName;
    private final List<SortableData> dataToSort;
    private final CallableAlgorithm algorithmFunction;
    public SortThread(CallableAlgorithm algorithmFunction, String algorithmName, List<SortableData> dataToSort){
        this.algorithmFunction = algorithmFunction;
        this.dataToSort = dataToSort;
        this.algorithmName = algorithmName;
    }
    @Override
    public void run(){
        time= algorithmFunction.call(algorithmName, dataToSort);
    }
    public double getTime(){
        return time;
    }
    public List<SortableData> getData(){
        return dataToSort;
    }
    public String getAlgorithmName(){
        return algorithmName;
    }
}
