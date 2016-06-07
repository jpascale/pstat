package ar.edu.itba.protos.pstat.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PStatParser {

    private static final Logger LOG = LoggerFactory.getLogger(PStatParser.class.getSimpleName());

    private static final String CRLF = "\r\n";
    private static final String PREFIX_POSITIVE = "+OK";
    private static final String PREFIX_NEGATIVE = "-ERR";

    public static final String METRIC_OK_HEADER = "+OK Metric values follows";
    public static final String METRIC_ERR_HEADER = "-ERR";


    private static final String METRIC_EOF = ".\r\n";
    private static final String METRIC_VALUE_SEPARATOR = " = ";

    private static final String METRIC_NEW_CONNECTION = "NEW_CONNECTION";
    private static final String METRIC_DISCONNECTION = "DISCONNECTION";
    private static final String METRIC_UPLOADED_BYTES = "UPLOADED_BYTES";
    private static final String METRIC_DOWNLOADED_BYTES = "DOWNLOADED_BYTES";
    private static final String METRIC_PARSED_BYTES = "PARSED_BYTES";

    public static boolean gotResponse(String response) {
        return (response.startsWith(PREFIX_POSITIVE) && response.endsWith("." + CRLF)) ||
                (response.startsWith(PREFIX_NEGATIVE) && response.endsWith(CRLF));
    }

    public static boolean isPositive(String response) {
        return response.startsWith(PREFIX_POSITIVE);
    }

    public static boolean isNegative(String response) {
        return response.startsWith(PREFIX_NEGATIVE);
    }

    public void decideFromResponse(String response){
        //TODO: Implement
    }

    public Metric parseMetrics(String response){
        LOG.info("Parsing response => \n {}", response);

        Metric.Builder builder = new Metric.Builder();
        //String subtring method is efficient due to offset usage;
        //Remove OK_HEADER and EOF
        final String str = response.substring((METRIC_OK_HEADER + CRLF).length(), response.length() - METRIC_EOF.length());
        final String[] arr = str.split(CRLF);

        LOG.info("Parsing splited array length => {}", arr.length);

        for (int i = 0; i < arr.length; i++) {
            final String[] curr = arr[i].split(METRIC_VALUE_SEPARATOR);
            LOG.info("Entering parsing loop => {} - {}", curr[0], curr[1]);
            switch (curr[0]){
                case METRIC_NEW_CONNECTION:
                    builder = builder.newConnection(Integer.parseInt(curr[1]));
                    break;
                case METRIC_DISCONNECTION:
                    builder = builder.disconnection(Integer.parseInt(curr[1]));
                    break;
                case METRIC_UPLOADED_BYTES:
                    builder = builder.uploadedBytes(Integer.parseInt(curr[1]));
                    break;
                case METRIC_DOWNLOADED_BYTES:
                    builder = builder.downloadedBytes(Integer.parseInt(curr[1]));
                    break;
                case METRIC_PARSED_BYTES:
                    builder = builder.parsedBytes(Integer.parseInt(curr[1]));
                    break;
            }
        }

        final Metric metric = builder.build();
        LOG.info("Metric created => {}", metric.toString());

        return metric;
    }

    public String getHeader(String response){
        return response.split(CRLF)[0];
    };

}