package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    public static List MySimpleList = new List();
    private String username;
    private BufferedReader in;
    private BufferedWriter out;
    private Socket socket;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = in.readLine();

            MySimpleList.addEnd(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
