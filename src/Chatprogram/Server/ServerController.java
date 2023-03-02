package Chatprogram.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                String name = m.getName();
                ServerModel.addMessage(name + ": " + ServerModel.getMsg());
                ServerView.setMessage(ServerModel.getChat());
                ServerModel.sendMessage(name + ": " + ServerModel.getMsg());
                ServerView.setTextField("");
            }
        });

        v.getEnter().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    ServerModel.setMsg(ServerView.getTextField());
                    String name = m.getName();
                    ServerModel.addMessage(name + ": " + ServerModel.getMsg());
                    ServerView.setMessage(ServerModel.getChat());
                    ServerModel.sendMessage(name + ": " + ServerModel.getMsg());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    ServerView.setTextField("");
                }
            }
        });

        this.setContentPane(ServerView.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setTitle("Chatprogram.Server Chat");
        v.getSendButton();
    }

    public static void main(String[] args) {
        ServerModel m = new ServerModel(5858);
        ServerView v = new ServerView();
        ServerController thisIsTheProgram = new ServerController(m,v);
        thisIsTheProgram.setVisible(true);
        String name = JOptionPane.showInputDialog("Name:");
        v.setList(name);
        m.setName(name);

        //ServerModel s = new ServerModel(5858);
        m.acceptClient();
        m.getStreams();
        ServerListenerThread l = new ServerListenerThread(m.in, thisIsTheProgram);
        Thread listener = new Thread(l);
        listener.start();
        m.runProtocol();
        listener.stop();
        m.shutdown();
    }

    public void newMessage(String msg) {
        ServerModel.addMessage(msg);
        ServerView.setMessage(ServerModel.getChat());
    }
}