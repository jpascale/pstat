package ar.edu.itba.protos.pstat.interfaces;

public interface Meter {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

    double[][] getMetricArray();

    void addMetric(Integer metric);
}
