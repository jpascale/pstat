package ar.edu.itba.protos.pstat.metrics;


import ar.edu.itba.protos.pstat.interfaces.Protocol;
import ar.edu.itba.protos.pstat.models.Metric;
import ar.edu.itba.protos.pstat.models.PStatParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PStatProtocol implements Protocol {


    private static final Logger LOG = LoggerFactory.getLogger(PStatProtocol.class.getSimpleName());
    private static final Integer ERR_TOLERANCE = 5;
    private final PStatParser parser;

    private Integer errCount = 0;

    public PStatProtocol(){
            parser = new PStatParser();
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
}
