package com.example;

import java.rmi.RemoteException;


public class DynamicGraph implements Graph{
    private String name;

    public DynamicGraph(String name) throws RemoteException{
        this.name =name;
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

    @Override
    public void add(int u, int v) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void delete(int u, int v) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int shortestPath(int u, int v) throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shortestPath'");
    }
    
}
