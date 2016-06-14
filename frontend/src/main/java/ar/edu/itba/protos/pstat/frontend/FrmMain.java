package ar.edu.itba.protos.pstat.frontend;

import javax.swing.*;

public class FrmMain {
    private FrontendChart downloadedBytesChart;

    public FrmMain(){
        downloadedBytesChart = new FrontendChart();
    }

    public void start(){

        SwingUtilities.invokeLater(downloadedBytesChart);
    }

    public FrontendChart getFrontendChart() {
        return downloadedBytesChart;
    }
}
