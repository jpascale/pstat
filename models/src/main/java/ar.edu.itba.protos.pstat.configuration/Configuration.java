package ar.edu.itba.protos.pstat.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {

    public static String PSTAT_METRICS_IP = "metrics_ip";
    public static String PSTAT_METRICS_PORT = "metrics_port";
    public static String PSTAT_DISPLAY = "display";

    private final String path;
    private final Map<String, Value> config = new HashMap<>();

    public Configuration(final String path) {
        this.path = path;
    }

    public void load() throws IOException {

        final FileInputStream inputStream = new FileInputStream(path);

        final Properties prop = new Properties();
        prop.load(inputStream);

        // get the property value and print it out
        String metrics_ip = prop.getProperty(PSTAT_METRICS_IP);
        String metrics_port = prop.getProperty(PSTAT_METRICS_PORT);
        String display = prop.getProperty(PSTAT_DISPLAY);

        config.put(PSTAT_METRICS_IP, new Value(metrics_ip));
        config.put(PSTAT_METRICS_PORT, new Value(metrics_port));
        config.put(PSTAT_DISPLAY, new Value(display));

        inputStream.close();
    }

    public Value getValue(String val){
        return config.get(val);
    }


}
