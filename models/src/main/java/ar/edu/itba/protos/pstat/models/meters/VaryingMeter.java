package ar.edu.itba.protos.pstat.models.meters;

import ar.edu.itba.protos.pstat.configuration.Chart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VaryingMeter extends SimpleMeter {
    private static final Logger LOG = LoggerFactory.getLogger(VaryingMeter.class.getSimpleName());
    private Double last;

    public VaryingMeter(Chart criteria) {
        super(criteria);
        last = 0.0;
    }

    @Override
    public void addMetric(Integer m){
        LOG.info("Adding metric m => {}", m);

        LOG.info("Last metric was {}", last);
        LOG.info("Difference is {}", Double.valueOf(m) - last);
        super.addMetric((int)(Double.valueOf(m) - last));

        last = Double.valueOf(m);
    }

}
