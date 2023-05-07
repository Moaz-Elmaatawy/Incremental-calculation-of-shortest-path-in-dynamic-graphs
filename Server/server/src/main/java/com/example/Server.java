package com.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject; 

public class Server {
	public static void main(String [] args) {
		try {
			
			System.out.println("Server is booting....");

			System.setProperty("java.rmi.server.hostname","localhost"); 

            Graph graph = new DynamicGraph("Graph 1");


		    // Export object before registered in Registry. 
		    Graph stub = (Graph) UnicastRemoteObject.exportObject(graph, 0);

			
            // create the RMI registry.
            Registry registry = LocateRegistry.createRegistry(1099);
			
            // Registered the exported object in rmi registry so that client can
            // lookup in this registry and call the object methods.
            registry.bind("graph", stub);
			System.out.println("server ready .......");

		} catch (Exception e) {
			System.out.println("Server error" + e);

		}

	}
}
