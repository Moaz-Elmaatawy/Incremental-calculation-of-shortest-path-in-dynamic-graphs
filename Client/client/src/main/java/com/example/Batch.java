package com.example;

public class Batch {
    private Long responseTime;
    private String response;
    private String request;
    private String algorithmType;
    private int updataPercentage;
    private int batchSize;
    private long ThreadID;

    public Batch(Long responseTime, String response , String request, String algorithmType , int updataPercentage , int batchSize , long ThreadID){
        this.responseTime=responseTime;
        this.response=response;
        this.updataPercentage=updataPercentage;
        this.request=request;
        this.algorithmType=algorithmType;
        this.ThreadID= ThreadID;
        this.batchSize=batchSize;
    }
    
    public String toString(){
        return "threat ID = " +ThreadID +"\n"+
                "response time = "+responseTime+"\n"+
                "algorithm Type = "+ algorithmType+"\n"+
                "updata Percentage = "+ updataPercentage+"\n"+
                "batchSize = "+ batchSize+"\n"+
                "request = \n"+ request+"\n"+
                "response = \n"+ response+"\n"+
                "----------------------------\n";
    }
}
