package Client;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientView {
    private JTextArea textArea1;
    private JPanel panel1;
    private JTextField textField1;
    private JButton sendButton;
    private JTextArea list1;

    public JPanel getPanel() {
        return panel1;
    }

    public String getTextField() {
        return textField1.getText();
    }

    public void setTextField(String c) {
        textField1.setText(c);
    }

    public void setList(String c) {
        list1.setText(c);
    }

    public void setTextArea1(JTextArea textArea1) {
        this.textArea1 = textArea1;
    }

    public void setMessage(String c) {
        textArea1.setText(c);
    }

    public void setSendButton(ActionListener exportListener) {
        sendButton.addActionListener(exportListener);
    }

    public AbstractButton getSendButton() {
        return sendButton;
    }
}
