package ar.edu.itba.protos.pstat.frontend;


import ar.edu.itba.protos.pstat.interfaces.Meter;
import ar.edu.itba.protos.pstat.interfaces.Observer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class FrontendChart implements Observer<Meter>, Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(FrontendChart.class.getSimpleName());

    private JFrame frame;
    private ChartPanel cp;
    private final String name;
    private final String y;

    public FrontendChart(String name, String y){
        this.name = name;
        this.y = y;
    }

    @Override
    public void run() {
        LOG.info("Calling run method.");

        frame = new JFrame(name);

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        XYDataset ds = createDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(name,
                "", y, ds, PlotOrientation.VERTICAL, true, true,
                false);

        cp = new ChartPanel(chart);

        //frame.getContentPane().add("Chart", cp);
        frame.getContentPane().add(cp);
    }

    @Override
    public void handleUpdate(Meter data) {
        LOG.info("Handle update called in FrontendChart");

        DefaultXYDataset ds = new DefaultXYDataset();
        ds.addSeries(name, data.getMetricArray());

        JFreeChart chart = ChartFactory.createXYLineChart(name,
                "", y, ds, PlotOrientation.VERTICAL, true, true,
                false);

        LOG.info("New chart created");

        ChartPanel cp = new ChartPanel(chart);
        LOG.info("Added to chart panel");


        frame.getContentPane().removeAll();
        frame.getContentPane().revalidate();

        frame.getContentPane().add(cp);
;
        LOG.info("Add to content pane");
        frame.getContentPane().repaint();
        LOG.info("Content pane repaint");

    }

    private static XYDataset createDataset() {

        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data = { {0.1, 0.2, 0.3}, {1, 2, 3} };

        ds.addSeries("name", data);

        return ds;
    }
}
