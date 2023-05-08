package com.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager; 

public class Server implements GraphService{
	private Logger logger = LogManager.getLogger(Server.class);
	/*
	How to use logger
	logger.info("message");
	logger.error("message");
	 * */
	 
	private DynamicGraph graph;

	public Server(){
		graph = new DynamicGraph("graph.txt");
	}

	public static void main(String [] args) {
		try {
			
			System.out.println("Server is booting....");
			System.setProperty("java.rmi.server.hostname","localhost"); 

            GraphService graphService = new Server();


		    // Export object before registered in Registry. 
		    GraphService stub = (GraphService) UnicastRemoteObject.exportObject(graphService, 0);

			
            // create the RMI registry.
            Registry registry = LocateRegistry.createRegistry(1099);
			
            // Registered the exported object in rmi registry so that client can
            // lookup in this registry and call the object methods.
            registry.bind("graphService", stub);
			System.out.println("server ready .......");

		} catch (Exception e) {
			System.out.println("Server error" + e);

		}

	}

	@Override
	public String getName() throws RemoteException {
		return "RMI test";
	}

	@Override
	public String processBatch(String batch, String algoritm) throws RemoteException {

		StringBuilder result = new StringBuilder();
		String[] batchLines=batch.split("\n");

		for(String line : batchLines){

			String[] operation =line.split(" ");
			char queryType =operation[0].charAt(0);
			if (queryType == 'F') break;
			int u = Integer.parseInt(operation[1]);
			int v = Integer.parseInt(operation[2]);

			if(queryType == 'A'){
				graph.add(u, v);
			}
			else if(queryType == 'D'){
				graph.delete(u, v);
			}
			else{
				result.append(graph.shortestPath(u, v , algoritm));
				result.append("\n");
			}
		}

		return result.toString();
	}

	@Override
	public int getInitialSize() throws RemoteException {
		return graph.getGraphSize();
	}
}
