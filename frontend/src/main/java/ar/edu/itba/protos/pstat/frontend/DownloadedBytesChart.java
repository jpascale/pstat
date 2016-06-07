package ar.edu.itba.protos.pstat.frontend;


import ar.edu.itba.protos.pstat.interfaces.Observer;
import ar.edu.itba.protos.pstat.models.DownloadedBytesMeter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class DownloadedBytesChart implements Observer<DownloadedBytesMeter>, Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(DownloadedBytesChart.class.getSimpleName());

    @Override
    public void run() {
        JFrame frame = new JFrame("Downloaded bytes chart");

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        XYDataset ds = createDataset();
        JFreeChart chart = ChartFactory.createXYLineChart("Test Chart",
                "x", "y", ds, PlotOrientation.VERTICAL, true, true,
                false);

        ChartPanel cp = new ChartPanel(chart);

        frame.getContentPane().add(cp);
    }

    @Override
    public void handleUpdate(DownloadedBytesMeter data) {
        LOG.info("Handle update called in DownloadedBytesChart");
    }

    private static XYDataset createDataset() {

        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data = { {0.1, 0.2, 0.3}, {1, 2, 3} };

        ds.addSeries("series1", data);

        return ds;
    }
}
