package ar.edu.itba.protos.pstat.frontend;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;

public class FrmMain {

    public void start(){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Charts");

            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            XYDataset ds = createDataset();
            JFreeChart chart = ChartFactory.createXYLineChart("Test Chart",
                    "x", "y", ds, PlotOrientation.VERTICAL, true, true,
                    false);

            ChartPanel cp = new ChartPanel(chart);

            frame.getContentPane().add(cp);
        });
    }

    private static XYDataset createDataset() {

        DefaultXYDataset ds = new DefaultXYDataset();

        double[][] data = { {0.1, 0.2, 0.3}, {1, 2, 3} };

        ds.addSeries("series1", data);

        return ds;
    }

}
