package ar.edu.itba.protos.pstat.main;

import ar.edu.itba.protos.pstat.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class.getSimpleName());

    public static void main (String[] args){
        if (args.length != 1){
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

        LOG.info("Loaded values => ip: {}, port: {}", metricsIp, metricsPort);


    }
}
