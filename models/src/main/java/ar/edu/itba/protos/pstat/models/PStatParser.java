package ar.edu.itba.protos.pstat.models;

public class PStatParser {

    protected static final String CRLF = "\r\n";
    protected static final String PREFIX_POSITIVE = "+OK";
    protected static final String PREFIX_NEGATIVE = "-ERR";

    public static boolean isPositive(String response) {
        return response.startsWith(PREFIX_POSITIVE);
    }

    public static boolean isNegative(String response) {
        return response.startsWith(PREFIX_NEGATIVE);
    }

    public Metric parse(){
        return null;
    }

}