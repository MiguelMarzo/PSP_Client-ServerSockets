package pspSocketsEjemplo4Chat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Waits for users requests. Displays in the panel textarea all the content that
 * comes from the socket, i.e., whatever the user writes in the panel is sent
 * through the socket.
 */
public class ClientControl
        extends Thread
        implements ActionListener {

    /**
     * To read data from the socket.
     */
    private BufferedReader input;

    /**
     * To write in the socket.
     */
    private PrintWriter output;

    /**
     * Panel with the user controls.
     */
    private ClientPanel panel;

    /**
     * Launches a thread to connect with the socket.
     *
     * @param socket The socket
     * @param panel The user panel
     */
    public ClientControl(Socket socket, ClientPanel panel) {
        this.panel = panel;
        try {
            input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            output = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);

            panel.addActionListener(this);
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the text from the panel and sends it through the socket. The panel
     * will call this method whenever the user writes something down in it and
     * presses the send button.
     */
    public void actionPerformed(ActionEvent event) {
        try {
            if (event.getActionCommand().equals("Send")) {
                String text = panel.getText();
                if (text != null) {
                    output.println(text);
                    System.out.println(text);
                }
            } else if(event.getActionCommand().equals("Export")){
                output.println("exportar");
            }

        } catch (Exception excepcion) {
            excepcion.printStackTrace();
        }
    }

    /**
     * Everything coming from the socket is written in the panel.
     */
    public void run() {
        try {
            while (true) {
                String text = input.readLine();
                panel.addText(text);
                panel.addText("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
