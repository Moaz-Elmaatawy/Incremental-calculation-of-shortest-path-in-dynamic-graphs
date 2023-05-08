# Incremental-calculation-of-shortest-path-in-dynamic-graphs
Shortest Path Query System using RMI/RPC
This is a Java-based project that implements a shortest path query system using RMI/RPC. The project aims to solve the shortest path problem in graph theory, which involves finding the path with the lowest weight between two vertices in a graph. The system is designed to handle queries on a dynamic graph by processing and indexing an initial graph and then issuing sequential operation batches.

Getting Started
To get started with the project, you can either clone the repository or download the ZIP file and extract it. Once you have the project files on your computer, you can import the project into your preferred Java IDE (such as Eclipse or IntelliJ IDEA) and run the Server.java file to start the server.

Usage
Once the server is up and running, you can start sending queries to the system using client programs. The system supports two types of clients: reader clients and writer clients. Reader clients can send queries to the server and receive responses, while writer clients can modify the graph by adding or removing edges.

To start a reader or writer client, you can run the ReaderClient.java or WriterClient.java file, respectively. Both types of clients take command-line arguments to specify the host and port number of the server. For example, to start a reader client that connects to a server running on localhost at port number 1099.
