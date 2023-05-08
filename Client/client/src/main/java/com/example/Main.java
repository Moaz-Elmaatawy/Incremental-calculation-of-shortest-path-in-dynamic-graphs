package com.example;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting.....");

        try {
            Client clients = new Client();
            clients.start();
            
        } catch (Exception e) {
            System.err.println("GraphService exception");
            logger.error(e.getMessage());
        }
    }
}
