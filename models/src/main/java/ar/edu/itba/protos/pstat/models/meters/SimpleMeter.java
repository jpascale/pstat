package ar.edu.itba.protos.pstat.models.meters;

import ar.edu.itba.protos.pstat.configuration.Chart;
import ar.edu.itba.protos.pstat.interfaces.Meter;
import ar.edu.itba.protos.pstat.interfaces.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SimpleMeter implements Meter {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleMeter.class.getSimpleName());
    private static final Integer DEQUE_MAX_ELEMENTS = 40;
    private static final Double ZERO = 0.0;

    private final List<Observer<Meter>> observerList = new ArrayList<>();
    protected final Deque<Double> deque;
    private final Chart criteria;

    public SimpleMeter(Chart criteria){
        deque = new LinkedList<>();
        for (int i = 0; i < DEQUE_MAX_ELEMENTS; i++) {
            deque.add(ZERO);
        }
        this.criteria = criteria;
    }

    public void addMetric(Integer metric){
        deque.add(Double.valueOf(metric));
        deque.removeFirst();
        LOG.info("Meter deque status => size {} ", deque.size());
        notifyObservers();
    }

    public double[][] getMetricArray(){
        LOG.info("getMetric array called");
        long t1 = System.currentTimeMillis();
        Object[] d =  deque.toArray();

        LOG.info("getMetric array called");
        double[][] doubles = new double[2][DEQUE_MAX_ELEMENTS];

        for (int i = 0; i < DEQUE_MAX_ELEMENTS; i++) {
            doubles[0][i] = i;
            doubles[1][i] = (Double) d[i];
        }

        LOG.info("Spent time to get metric array => {}ms", System.currentTimeMillis() - t1);

        /*for (int i = 0; i < doubles[1].length; i++) {
            System.out.print(doubles[1][i]);
            System.out.print(" - ");
        }
        System.out.println("");*/

        return doubles;
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
