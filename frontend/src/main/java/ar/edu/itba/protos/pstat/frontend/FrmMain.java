package ar.edu.itba.protos.pstat.frontend;

import javax.swing.*;

public class FrmMain {
    final JFrame frame;

    private DownloadedBytesChart downloadedBytesChart;

    public FrmMain(){
        frame = new JFrame("Downloaded bytes chart");

        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        downloadedBytesChart = new DownloadedBytesChart(frame);
    }

    public void start(){

        SwingUtilities.invokeLater(downloadedBytesChart);
    }

    public DownloadedBytesChart getDownloadedBytesChart() {
        return downloadedBytesChart;
    }
}
