package com.example;

import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    private static final int RAMP_UP_TIME=10;
    private static final int NUM_CLIENTS = 5;

    public static void main(String[] args) {
        logger.info("Starting.....");
        try {
            ArrayList<Client> clientsThreads = new ArrayList<>();
            
            //spawning threads
            for(int i=0;i<NUM_CLIENTS;++i){
                clientsThreads.add(new Client());
                clientsThreads.get(i).start();
                Thread.sleep(RAMP_UP_TIME);
            }

            //joining threads
            for(int i=0;i<NUM_CLIENTS;++i){
                clientsThreads.get(i).join();
            }
            
        } catch (Exception e) {
            System.err.println("GraphService exception");
            logger.error(e.getMessage());
        }
    }
}
