package ar.edu.itba.protos.pstat.interfaces;


public interface Observer<T extends Observable> {
    void handleUpdate(T data);
}
