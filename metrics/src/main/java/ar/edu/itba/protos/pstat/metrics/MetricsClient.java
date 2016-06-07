package ar.edu.itba.protos.pstat.metrics;

import ar.edu.itba.protos.pstat.interfaces.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetricsClient implements Runnable {

    //Main Logger info
    private static final Logger LOG = LoggerFactory.getLogger(MetricsClient.class.getSimpleName());

    private final String ip;
    private final Integer port;
    private final Protocol protocol;

    public MetricsClient(final String metricsIp, final Integer metricsPort, Protocol protocol){
        this.ip = metricsIp;
        this.port = metricsPort;
        this.protocol = protocol;
    }


    public void run() {
        LOG.info("Running Metrics client Task");
        LOG.info("Metrics: Ip => {}, Port => {}", ip, port);
    }
}
