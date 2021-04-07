package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler extends Thread
{
    protected Socket socket       = null;
    protected PrintWriter networkOut     = null;
    protected BufferedReader in   = null;

    protected Vector messages     = null;

    public ClientHandler(Socket socket, Vector messages) {
        super();
        this.socket = socket;
        this.messages = messages;
        try {
            networkOut = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        boolean endOfSession = false;
        while(!endOfSession) {
            endOfSession = processCommand();
        }
        close();
    }
    public void close()
    {
        try {
            socket.close();
            networkOut.close();
            in.close();
            System.exit(0);

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean processCommand()
    {
        try {
            String message;

            message = in.readLine();
            if(message.equals("Exit")) {
                return true;
            }
            MainServer.board.appendText(message + "\n");

        } catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
