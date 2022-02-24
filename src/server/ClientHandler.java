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
            broadcastMessage("SERVER: "+ username +" has joined the chat");
        } catch (IOException e) {
            closeEverything(socket, in, out);
        }
    }



    @Override
    public void run() {
        String clientMessage;
        while (socket.isConnected()){
            try {
                clientMessage = in.readLine();
                broadcastMessage(clientMessage);
            } catch (IOException e) {
                closeEverything(socket, in, out);
                break;
            }
        }
    }

    public void  broadcastMessage(String message){
        Node aux = MySimpleList.getBegin();
        while (aux != null){
            try {
                if (!(((ClientHandler) aux.getData()).username.equals(username))){

                    ((ClientHandler) aux.getData()).out.write(message);
                    ((ClientHandler) aux.getData()).out.newLine();
                    ((ClientHandler) aux.getData()).out.flush();
                }
            } catch (IOException e) {
                closeEverything(socket, in, out);
            }
            aux = aux.getNext();
        }
    }

    public void removeClientHandler(){
        broadcastMessage("SERVER: "+ username + "has left the chat");

        MySimpleList.remove(this);
        broadcastMessage("SERVER: "+ username + "has left the chat");
    }

    public void closeEverything(Socket socket, BufferedReader in, BufferedWriter out){
        removeClientHandler();
        try {
            if(in != null)
                in.close();
            if (out != null)
                out.close();
            if (socket != null)
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
