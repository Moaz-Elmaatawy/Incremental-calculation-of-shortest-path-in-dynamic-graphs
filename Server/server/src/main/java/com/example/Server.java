package com.example;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager; 

public class Server implements GraphService{
	private static Logger logger = LogManager.getLogger(Server.class);
	 
	private DynamicGraph graph;

	public Server(){
		//DynamicGraph.generateGraph("graphs/graphStand.txt", 5000, 0.0002);
		graph = new DynamicGraph("graphs/graphStand.txt");
	}

	public static void main(String [] args) {
		try {
			
			System.out.println("Server is booting....");
			logger.info("Server is booting....");
			System.setProperty("java.rmi.server.hostname","localhost"); 
			System.setProperty("log4j.configurationFile", "src/main/java/resources/log4j2.xml");
            GraphService graphService = new Server();


		    // Export object before registered in Registry. 
		    GraphService stub = (GraphService) UnicastRemoteObject.exportObject(graphService, 0);

			
            // create the RMI registry.
            Registry registry = LocateRegistry.createRegistry(1099);
			
            // Registered the exported object in rmi registry so that client can
            // lookup in this registry and call the object methods.
            registry.bind("graphService", stub);
			System.out.println("server ready .......");
			logger.info("server ready .......");

		} catch (Exception e) {
			logger.error("Server error" + e);

		}

	}

	@Override
	public synchronized String getName() throws RemoteException {
		return "RMI test";
	}

	@Override
	public synchronized String processBatch(String batch, String algoritm) throws RemoteException {
		logger.info("Server started a batch");
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
				logger.info("Edge added from " + u + " to " + v);
			}
			else if(queryType == 'D'){
				graph.delete(u, v);
				logger.info("Edge removed from " + u + " to " + v);
			}
			else{
				int out = graph.shortestPath(u, v , algoritm);
				logger.info("Shortest path between " + u + " and " + v + " usign " + algoritm + " is: " + out);
				result.append(out);
				result.append("\n");
			}
		}
		logger.info("Server finnished a batch");
		return result.toString();
	}

	@Override
	public synchronized int getInitialSize() throws RemoteException {
		logger.info("Client requested graph initial size: " + graph.getGraphInitialSize());
		return graph.getGraphInitialSize();
	}
}
