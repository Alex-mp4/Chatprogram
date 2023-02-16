package Server;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ServerView {
    private JTextArea textArea1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton sendButton;
    private JList list1;

    public JPanel getPanel() {
        return panel1;
    }

    public String getTextField() {
        return textField1.getText();
    }

    public JList getList() {
        return list1;
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public void setMessage(String c) {
        textArea1.setText(c);
    }

    public void setList1(JList list1) {
        this.list1 = list1;
    }

    public void setSendButton(ActionListener exportListener) {
        sendButton.addActionListener(exportListener);
    }

    public AbstractButton getSendButton() {
        return sendButton;
    }
}
