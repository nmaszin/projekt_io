package pl.put.poznan.sort.logic.algorithms;

import java.util.Collections;
import java.util.List;
public class GnomeSort<T extends  Comparable<T>> extends SortingAlgorithm<T> {
    public GnomeSort(){
        super("Gnome");
    }
    @Override
    public void sort(List<T> list){
        int id=0;
        while(id<list.size()){
            if(id==0){
                id++;
            }
            if(list.get(id).compareTo(list.get(id-1))>0) {
                id++;
            }
            else {
                Collections.swap(list, id-1, id);
                id--;
            }
        }
    }
}