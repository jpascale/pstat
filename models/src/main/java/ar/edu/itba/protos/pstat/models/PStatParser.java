package ar.edu.itba.protos.pstat.models;

public class PStatParser {

    private static final String CRLF = "\r\n";
    private static final String PREFIX_POSITIVE = "+OK";
    private static final String PREFIX_NEGATIVE = "-ERR";

    public static boolean gotResponse(String response) {
        return response.endsWith("." + CRLF);
    }

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