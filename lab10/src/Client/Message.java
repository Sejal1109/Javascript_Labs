package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Message {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;

    private Message(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(this.socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    public static Message connect(String host, int port) {
        try {
            return new Message(new Socket(host, port));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void send(String username, String content)
    {
        out.println(username + ": " + content);
    }
    public void exit() {
        out.println("Exit");
        close();
    }

    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.close();

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}
