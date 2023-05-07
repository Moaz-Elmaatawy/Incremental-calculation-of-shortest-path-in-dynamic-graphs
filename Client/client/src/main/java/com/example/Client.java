package com.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
   public static void main(String[] args) {
      try {

         Registry registry = LocateRegistry.getRegistry("localhost", 1099);
   
         // lookup the graph object
         Graph graph = (Graph) registry.lookup("graph");
         String graphName = graph.getName();
         
         System.out.println("Name:" + graphName);
      } catch (Exception e) {
         System.out.println("Exception in client side" + e);
      }
   }
}