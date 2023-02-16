package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class ServerController extends JFrame {
    ServerModel ServerModel;
    ServerView ServerView;

    public ServerController(ServerModel m, ServerView v) {
        this.ServerModel = m;
        this.ServerView = v;

        v.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerModel.setMsg(ServerView.getTextField());
                ServerModel.addMessage(ServerModel.getMsg());
                ServerView.setMessage(ServerModel.getChat());
            }
        });

        this.setContentPane(ServerView.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        v.getSendButton();
    }

    public static void main(String[] args) {

        ServerModel s = new ServerModel(5858);
        s.acceptClient();
        s.getStreams();
        ServerListenerThread l = new ServerListenerThread(s.in, System.out);
        Thread listener = new Thread(l);
        listener.start();
        s.runProtocol();
        listener.stop();
        s.shutdown();

        ServerModel m = new ServerModel(5858);
        ServerView v = new ServerView();
        ServerController thisIsTheProgram = new ServerController(m,v);
        thisIsTheProgram.setVisible(true);
    }
}