package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class ClientController extends JFrame {
    ClientModel ClientModel;
    ClientView ClientView;
    private BufferedReader in;
    private PrintStream out;

    public ClientController(ClientModel m, ClientView v) {
        this.ClientModel = m;
        this.ClientView = v;

        v.getSendButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientModel.setMsg(ClientView.getTextField());
                String name = m.getName();
                ClientModel.addMessage(name + ": " + ClientModel.getMsg());
                ClientView.setMessage(ClientModel.getChat());
                ClientModel.sendMessage(ClientModel.getMsg());
                ClientView.setTextField("");
            }
        });

        this.setContentPane(ClientView.getPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setTitle("Client Chat");
        //ClientView.setSendButton(new sendButton());
        v.getSendButton();
    }

    public static void main(String[] args) {
        ClientModel me = new ClientModel("10.80.47.10", 5858);
        ClientView v = new ClientView();
        ClientController thisIsTheProgram = new ClientController(me,v);
        thisIsTheProgram.setVisible(true);
        String name = JOptionPane.showInputDialog("Name:");
        v.setList(name);
        me.setName(name);

        //Client me = new Client("10.80.47.10", 5858);
        //ClientModel me = new ClientModel("10.80.46.47", 1234);
        me.getStreams();
        ClientListenerThread l = new ClientListenerThread(me.in, thisIsTheProgram);
        Thread listener = new Thread(l);
        listener.start();
        me.runProtocol();
        listener.stop();
        me.shutDown();
    }

    public void newMessage(String msg) {
        ClientModel.addMessage(msg);
        ClientView.setMessage(ClientModel.getChat());
    }

    /*private class sendButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }*/
}