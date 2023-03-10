package Chatprogram.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerModel {
    ServerSocket server;
    Socket client;

    PrintWriter out;
    BufferedReader in;

    ServerController ServerController;

    String msg = "";
    String chat = "";
    String name = "";

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
        chat += msg + "\n";
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public ServerModel(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Failed to open serversocket.");
            e.printStackTrace();
        }
        System.out.println("Chatprogram.Server started...");
    }

    public void acceptClient() {
        try {
            client = server.accept();
        } catch (IOException e) {
            System.err.println("Failed to connect to client");
            e.printStackTrace();
        }
        System.out.println("client connected...");
    }

    public void getStreams() {
        try {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
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
            out.println(msg);
        }
    }

    public void shutdown() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
