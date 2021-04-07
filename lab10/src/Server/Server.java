package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    protected Socket clientSocket           = null;
    protected ServerSocket serverSocket     = null;
    protected ClientHandler thread    = null;
    protected int numClients                = 0;
    protected Vector messages               = new Vector();

    public static int SERVER_PORT = 16789;


    public ObjectOutputStream output;

    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("---------------------------");
            System.out.println("Bulletin Board Application is running");
            System.out.println("---------------------------");
            System.out.println("Listening to port: "+SERVER_PORT);
            clientSocket = serverSocket.accept();
            System.out.println("Client connected #" + numClients+1);
            thread = new ClientHandler(clientSocket,messages);
            thread.start();
            numClients++;

        } catch (IOException e) {
            System.err.println("IOException while creating server connection");
        }
    }

    public void close()
    {
        thread.close();
    }

    public static void main(String[] args) {
        Server app = new Server();
    }
}
