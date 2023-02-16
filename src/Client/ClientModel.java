package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientModel {
    Socket socket;

    PrintWriter out;
    BufferedReader in;

    String msg = "";
    String chat = "";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getChat() {
        return chat;
    }

    public void addMessage(String msg) {
        chat += "Client: " + msg + "\n";
    }

    public ClientModel(String ip, int port) {
        try {
            socket = new Socket(ip,port);
        } catch (IOException e) {
            System.err.println("Failed to connect to server");
            e.printStackTrace();
        }
        System.out.println("Connection ready...");
    }

    public void getStreams() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Streams ready...");
    }

    public void runProtocol() {
        Scanner tgb = new Scanner(System.in);
        System.out.println("chatting...");
        String msg = "";
        while (!msg.equals("QUIT")) {
            msg = tgb.nextLine();
            out.println("CLIENT: " + msg);

            //msg = getTextField();
            //setTextArea1() = msg;
        }
    }

    public void shutDown() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
