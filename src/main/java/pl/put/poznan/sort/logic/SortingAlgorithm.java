package pl.put.poznan.sort.logic;

import java.util.List;

abstract public class SortingAlgorithm {
    protected String name;
    public String getName(){return name;}
    abstract public void sort(List<Comparable<?>> listToSort);
}
