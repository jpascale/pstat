package ar.edu.itba.protos.pstat.frontend;

import javax.swing.*;

public class FrmMain {
    private FrontendChart frontendChart;

    public FrmMain(String name, String y){
        frontendChart = new FrontendChart(name, y);
    }

    public void start(){
        SwingUtilities.invokeLater(frontendChart);
    }

    public FrontendChart getFrontendChart() {
        return frontendChart;
    }
}
