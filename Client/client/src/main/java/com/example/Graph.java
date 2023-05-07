package com.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Graph extends Remote{

    public String getName() throws RemoteException;

    public void add(int u ,int v) throws RemoteException;
 
    public void delete(int u, int v) throws RemoteException;  

    public int shortestPath(int u ,int v) throws RemoteException;  
}
