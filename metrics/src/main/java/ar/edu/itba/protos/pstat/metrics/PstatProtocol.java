package ar.edu.itba.protos.pstat.metrics;


import ar.edu.itba.protos.pstat.configuration.Chart;
import ar.edu.itba.protos.pstat.interfaces.Meter;
import ar.edu.itba.protos.pstat.interfaces.Observer;
import ar.edu.itba.protos.pstat.interfaces.Protocol;
import ar.edu.itba.protos.pstat.models.meters.DownloadedBytesMeter;
import ar.edu.itba.protos.pstat.models.Metric;
import ar.edu.itba.protos.pstat.models.PStatParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PStatProtocol implements Protocol {

    //TODO: Create builder for observers;

    private static final Logger LOG = LoggerFactory.getLogger(PStatProtocol.class.getSimpleName());
    private static final Integer ERR_TOLERANCE = 5;
    private final PStatParser parser;

    private Integer errCount = 0;
    private Chart chart;
    private final Meter meter;

    public PStatProtocol(Chart chart){
        parser = new PStatParser();
        this.chart = chart;

        switch (chart) {
            case DOWNLOADED_BYTES:
                meter = new DownloadedBytesMeter();
                break;
            default:
                meter = null;
                LOG.error("Wrong meter. PStat will exit.");
                System.exit(1);
                break;
        }
    }

    @Override
    public void handle(String response) {
        LOG.info("Entering PStatProtocol handler");
        LOG.info("Response => {}", response);

        final String header = parser.getHeader(response);
        LOG.info("Got header from response => {}", header);

        Metric metric;

        switch (header){
            case PStatParser.METRIC_OK_HEADER:
                metric = parser.parseMetrics(response);
                LOG.info("Metric generated => {}", metric);
                handleMeterUpdate(metric);
                break;

            case PStatParser.METRIC_ERR_HEADER:
                errCount++;
                LOG.error("Received -ERR response from server. Error count => {}", errCount);
                if (errCount.equals(ERR_TOLERANCE)){
                    LOG.error("Max amount of -ERR reached. PStat will shut down.");
                    System.exit(1);
                }
                break;
        }

    }

    private void handleMeterUpdate(Metric metric) {
        meter.addMetric(metric.getDownloadedBytes());
    }

    public void setDownloadedBytesMeterObserver(Observer<Meter> obs){
        meter.addObserver(obs);
    }
}
