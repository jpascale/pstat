package ar.edu.itba.protos.pstat.frontend;

import org.jfree.ui.RefineryUtilities;

public class FrmMain {
    //private FrontendChart frontendChart;
    private final LineChartDemo1 demo;

    public FrmMain(String name, String y){
        //frontendChart = new FrontendChart(name, y);
        demo = new LineChartDemo1(name, y);

    }

    public void start(){
        //SwingUtilities.invokeLater(frontendChart);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }

    public LineChartDemo1 getFrontendChart() {
        return demo;
    }
}
