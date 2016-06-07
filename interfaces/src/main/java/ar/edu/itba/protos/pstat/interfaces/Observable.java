package ar.edu.itba.protos.pstat.interfaces;

public interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}
