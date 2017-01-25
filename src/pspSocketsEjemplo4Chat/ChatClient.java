package pspSocketsEjemplo4Chat;

import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * ChatClient class. Establishes the connection and creates 
 * the window and the control class.
 */
public class ChatClient {

    /**
     * Socket with the server.
     */
    private Socket socket;

    /**
     * Panel with the client UI.
     */
    private ClientPanel panel;

    /**
     * Port.
     */
    private static final int PORT = 8080;

    /**
     * Starts the chat.
     *
     */
    public static void main(String[] args) {
        new ChatClient();
    }

    /**
     * Creates the UI, establishes the connection.
     */
    public ChatClient() {
        try {
            renderUI();
            socket = new Socket("127.0.0.1", PORT);
            ClientControl control = new ClientControl(socket, panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a windows and then places it inside the panel.
     */
    private void renderUI() {
        JFrame v = new JFrame();
        panel = new ClientPanel(v.getContentPane());
        v.pack();
        v.setVisible(true);
        v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
