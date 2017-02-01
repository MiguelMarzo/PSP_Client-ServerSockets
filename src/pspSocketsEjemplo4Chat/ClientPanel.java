package pspSocketsEjemplo4Chat;

import java.awt.Container;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Panel to render the conversation and ask the user for the text they want to
 * send.
 */
public class ClientPanel {

    /**
     * Scroll
     */
    private JScrollPane scroll;

    /**
     * Area to render the conversation
     */
    private JTextArea textArea;

    /**
     * To insert a message
     */
    private JTextField textField;

    /**
     * Button to send the message
     */
    private JButton button;

    /*
    * Button to export the chat
     */
    private JButton buttonExport;

    /**
     * Creates the panel with all its components. A text area to render the
     * conversation, a textField to allow the user to write a message and a send
     * button.
     *
     */
    public ClientPanel(Container container) {
        container.setLayout(new BorderLayout());
        textArea = new JTextArea(10, 10);
        scroll = new JScrollPane(textArea);

        JPanel panel = new JPanel(new FlowLayout());
        textField = new JTextField(50);
        button = new JButton("Send");
        buttonExport = new JButton("Export");
        panel.add(textField);
        panel.add(button);
        panel.add(buttonExport);

        container.add(scroll, BorderLayout.CENTER);
        container.add(panel, BorderLayout.SOUTH);
    }

    /**
     * Adds the actionListener passed whether the users presses <enter> when the
     * focus is on the textField or when clicks the send button.
     *
     * @param action ActionListener to be added.
     */
    public void addActionListener(ActionListener action) {
        textField.addActionListener(action);
        button.addActionListener(action);
        buttonExport.addActionListener(action);
    }

    /**
     * Adds the text to the textArea.
     *
     * @param text Text to be added
     */
    public void addText(String text) {
        textArea.append(text);
    }

    /**
     * Returns the text in the textfield and empties it.
     *
     * @return The text in the textfield.
     */
    public String getText() {
        String text = textField.getText();
        textField.setText("");
        return text;
    }
    
    public String getTextAreaText() {
        return textArea.getText();
    }
}
