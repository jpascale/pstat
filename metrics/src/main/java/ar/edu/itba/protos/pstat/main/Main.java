package ar.edu.itba.protos.pstat.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class.getSimpleName());

    public static void main (String[] args){
        if (args.length != 1){
            System.out.println("Invalid amount of arguments. Path to the config file is needed.");
            LOG.error("Invalid amount of arguments");
            System.exit(1);
        }

        LOG.info("Right amount of arguments");

        



    }
}
