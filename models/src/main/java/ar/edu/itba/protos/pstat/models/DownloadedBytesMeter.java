package ar.edu.itba.protos.pstat.models;

import ar.edu.itba.protos.pstat.interfaces.Observable;
import ar.edu.itba.protos.pstat.interfaces.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadedBytesMeter implements Observable {

    private final List<Observer<DownloadedBytesMeter>> observerList = new ArrayList<>();
    private final Map<Integer, Integer> map = new HashMap<>();
    int order;

    public DownloadedBytesMeter(){
        order = 0;
    }

    public void addMetric(Integer metric){
        map.put(order, metric);
        order++;
        notifyObservers();
    }

    //TODO: Add interface
    public Map<Integer, Integer> getMetricsMap(){
        return map;
    }


    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.handleUpdate(this);
        }
    }
}
