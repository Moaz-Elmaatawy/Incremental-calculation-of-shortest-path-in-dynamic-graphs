package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class DynamicGraph {
    private HashMap<Integer, HashSet<Integer>> graph;
    private HashMap<Integer, HashSet<Integer>> reversedGraph;

    public DynamicGraph(String filePath){
        createGraph(filePath);
    }

    private void createGraph(String filePath) {
        graph = new HashMap<>();
        reversedGraph = new HashMap<>();
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.charAt(0) == 'S') break;
                String[] edge = line.split(" ");
                int u = Integer.parseInt(edge[0]), v = Integer.parseInt(edge[1]);
                if (!graph.containsKey(u)){
                    graph.put(u, new HashSet<Integer>());
                }
                graph.get(u).add(v);

                if (!reversedGraph.containsKey(v)){
                    reversedGraph.put(v, new HashSet<Integer>());
                }
                reversedGraph.get(v).add(u);

            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void add(int u, int v){
        if (!graph.containsKey(u)){
            graph.put(u, new HashSet<Integer>());
        }
        graph.get(u).add(v);

        if (!reversedGraph.containsKey(v)){
            reversedGraph.put(v, new HashSet<Integer>());
        }
        reversedGraph.get(v).add(u);
    }

    public void delete(int u, int v) {
        if (graph.containsKey(u)){
            graph.get(u).remove(v);
        }

        if (reversedGraph.containsKey(v)){
            reversedGraph.get(v).remove(u);
        }
    }
    
    public int shortestPath(int u, int v) {
        if (u == v) return 0;

        HashMap<Integer, Integer> visitedForward = new HashMap<>();
        HashMap<Integer, Integer> visitedBackward = new HashMap<>();

        Queue<Integer> queueForward = new LinkedList<>();
        Queue<Integer> queueBackward = new LinkedList<>();

        visitedForward.put(u, 0);
        visitedBackward.put(v, 0);
        queueForward.add(u);
        queueBackward.add(v);

        while (!queueForward.isEmpty() && !queueBackward.isEmpty()) {
            // Forward BFS
            int currentForward = queueForward.remove();
            if (graph.containsKey(currentForward)) {
                for (int neighbor : graph.get(currentForward)) {
                    if (!visitedForward.containsKey(neighbor)) {
                        visitedForward.put(neighbor, visitedForward.get(currentForward) + 1);
                        queueForward.add(neighbor);
                    }
                    if (visitedBackward.containsKey(neighbor)) {
                        return visitedForward.get(neighbor) + visitedBackward.get(neighbor);
                    }
                }
            }

            // Backward BFS
            int currentBackward = queueBackward.remove();
            if (reversedGraph.containsKey(currentBackward)) {
                for (int neighbor : reversedGraph.get(currentBackward)) {
                    if (!visitedBackward.containsKey(neighbor)) {
                        visitedBackward.put(neighbor, visitedBackward.get(currentBackward) + 1);
                        queueBackward.add(neighbor);
                    }
                    if (visitedForward.containsKey(neighbor)) {
                        return visitedForward.get(neighbor) + visitedBackward.get(neighbor);
                    }
                }
            }
        }

        return -1;
    }
    
}
