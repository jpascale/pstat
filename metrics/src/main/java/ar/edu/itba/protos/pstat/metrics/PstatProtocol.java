package ar.edu.itba.protos.pstat.metrics;


import ar.edu.itba.protos.pstat.interfaces.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PStatProtocol implements Protocol {

    private static final Logger LOG = LoggerFactory.getLogger(PStatProtocol.class.getSimpleName());

    @Override
    public void handle(String response) {
        LOG.info("Entering PStatProtocol handler");
        LOG.info("Response => {}", response);


    }
}
