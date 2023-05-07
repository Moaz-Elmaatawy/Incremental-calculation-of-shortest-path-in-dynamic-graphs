package com.example;

import java.rmi.RemoteException;


public class DynamicGraph {
    private String fileName;

    public DynamicGraph(String fileName){
        this.fileName =fileName;
    }

    public void add(int u, int v){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    public void delete(int u, int v) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    public int shortestPath(int u, int v) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shortestPath'");
    }
    
}
