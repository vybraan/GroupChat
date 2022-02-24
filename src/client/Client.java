package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private String username;

    public Client(Socket socket, String username){
        try {
            this.socket = socket;
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
            System.out.println("============== Welcome ==============");
        } catch (IOException e) {
            closeEverything(socket, in, out);
        }
    }

    public void sendMessage(){
        try {
            out.write(username);
            out.newLine();
            out.flush();

            Scanner k = new Scanner(System.in);

            while (socket.isConnected()) {
                String message = k.nextLine();
                out.write(username +": "+ message);
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, in, out);
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (socket.isConnected()){
                    try {
                        message = in.readLine();
                        System.out.println(message);
                    } catch (IOException e) {
                        closeEverything(socket, in, out);
                        break;
                    }
                }
            }
        }).start();

    }

    public void closeEverything(Socket socket, BufferedReader in, BufferedWriter out){
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

    public static void main(String[] args) throws IOException {
        Scanner k = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = k.nextLine();

        Socket socket = new Socket(InetAddress.getLocalHost(), 8000);
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendMessage();
    }

}
