package com.example;

import java.util.Random;

public class BatchGeneration {
    private int graphSize;
    private final double  EXPANTION_FACTOR= 1.5;

    public BatchGeneration(int graphInitialSize){
        this.graphSize= (int) (graphInitialSize*EXPANTION_FACTOR);
    }

    String getBatch(int updataPercentage ,int batchSize){
        StringBuilder batch = new StringBuilder();
        Random rand = new Random();

        for(int i =0 ;i<batchSize;++i){ 
            int operation = rand.nextInt(100);
            int u = rand.nextInt(graphSize) , v = rand.nextInt(graphSize);

            
            if(operation < updataPercentage){
                //Addition
                if(rand.nextInt(1) == 0)
                    batch.append('A');
                //Deletion
                else 
                    batch.append('D');
            }
            //Shorest path Query
            else{
                batch.append('Q');
            }
            batch.append(" "+u+" "+v+"\n");
        }
        batch.append('F');


        return batch.toString();
    }

}
