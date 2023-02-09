package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerController extends JFrame {
    ServerModel ServerModel;
    ServerView ServerView;

    public ServerController(ServerModel m, ServerView v) {
        this.ServerModel = m;
        this.ServerView = v;
        this.setContentPane(ServerView.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ServerModel m = new ServerModel();
        ServerView v = new ServerView();
        ServerController thisIsTheProgram = new ServerController(m,v);
        thisIsTheProgram.setVisible(true);

    }
}