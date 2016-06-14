package ar.edu.itba.protos.pstat.interfaces;


public interface Observer<T> {
    void handleUpdate(T data);
}
