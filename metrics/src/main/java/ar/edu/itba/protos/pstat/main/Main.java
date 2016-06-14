package ar.edu.itba.protos.pstat.main;

import ar.edu.itba.protos.pstat.configuration.Chart;
import ar.edu.itba.protos.pstat.configuration.Configuration;
import ar.edu.itba.protos.pstat.frontend.FrmMain;
import ar.edu.itba.protos.pstat.metrics.MetricsClient;
import ar.edu.itba.protos.pstat.metrics.PStatProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    //Main Logger info
    private static final Logger LOG = LoggerFactory.getLogger(Main.class.getSimpleName());

    //Requests timer in millis
    private static final Integer CLIENT_TIMER = 500;


    public static void main (String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid amount of arguments. Path to the config file is needed.");
            LOG.error("Invalid amount of arguments");
            System.exit(1);
        }

        LOG.info("Right amount of arguments.");
        LOG.info("Proceed to load configuration.");

        final Configuration config = new Configuration(args[0]);

        try {
            config.load();
            LOG.info("Configuration loaded");

        } catch (FileNotFoundException e) {
            LOG.warn("Configuration file not found: {} ", args[0]);
            System.exit(1);
        } catch (IOException e) {
            LOG.error("Error loading config file.");
            System.exit(1);
        } catch (Exception e) {
            LOG.error("Unknown exception.");
            System.exit(1);
        }

        final String metricsIp = config.getValue(Configuration.PSTAT_METRICS_IP).toString();
        final int metricsPort = config.getValue(Configuration.PSTAT_METRICS_PORT).asInt();
        final Chart display = Chart.getChart(config.getValue(Configuration.PSTAT_DISPLAY).toString());
        LOG.info("Loaded values => ip: {}, port: {}, display: {}", metricsIp, metricsPort, display.toString());


        try {
            final FrmMain frmMain = new FrmMain(display.getName(), display.getY());
            frmMain.start();

            final PStatProtocol pstatProtocol = new PStatProtocol(display);
            pstatProtocol.setDownloadedBytesMeterObserver(frmMain.getFrontendChart());

            final MetricsClient mc = new MetricsClient(metricsIp, metricsPort, pstatProtocol);



            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
            executor.scheduleAtFixedRate(mc, 0, CLIENT_TIMER, TimeUnit.MILLISECONDS);

        } catch (IOException e){
            LOG.error("Unable to start PStat.");
            System.exit(1);
        }
    }
}
