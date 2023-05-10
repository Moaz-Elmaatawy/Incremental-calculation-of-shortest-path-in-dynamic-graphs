package com.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Client extends Thread{
   private final int BATCH_SIZE = 10;
   private final int SLEEP_TIME = 100;
   private final String ALGORITHM_USED = "BFS";
   private Logger logger = LogManager.getLogger(Client.class);

   public void run() {
      for(int i=0;i<5;++i){
         try {
            System.err.println("ClientID: "+ Thread.currentThread().getId());

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // lookup the graph object
            GraphService graphService = (GraphService) registry.lookup("graphService");
            System.err.println("graphService found");
            // TO BE REMOVED for testing purposes
            String graphName = graphService.getName();
            System.out.println("Name:" + graphName);

            int graphInitialSize = graphService.getInitialSize();
            logger.info("Graph Initial Size = " + graphInitialSize);
            
            BatchGeneration batchGeneration = new BatchGeneration(graphInitialSize);
            
            Random rand = new Random();
            int updatePercentage = rand.nextInt(100);
            String requestBatch = batchGeneration.getBatch(updatePercentage,BATCH_SIZE);
            long startTime = System.currentTimeMillis();
            String batchResult = graphService.processBatch(requestBatch,ALGORITHM_USED );
            long endTime = System.currentTimeMillis();

            long responseTime = endTime - startTime;

            Batch batch = new Batch(responseTime , batchResult ,requestBatch, ALGORITHM_USED , updatePercentage, BATCH_SIZE, Thread.currentThread().getId());
            logger.info(batch.toString());

            int sleepTime = rand.nextInt(SLEEP_TIME);
            Thread.sleep(sleepTime);

         } catch (Exception e) {
            logger.error(e);
         }
      }
   }
}