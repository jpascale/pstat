package ar.edu.itba.protos.pstat.frontend;

import javax.swing.*;

public class FrmMain {

    private DownloadedBytesChart downloadedBytesChart;

    public FrmMain(){
        downloadedBytesChart = new DownloadedBytesChart();
    }

    public void start(){
        SwingUtilities.invokeLater(downloadedBytesChart);
    }

    public DownloadedBytesChart getDownloadedBytesChart() {
        return downloadedBytesChart;
    }
}
