package pspSocketsEjemplo4Chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * Deals with a client.
 */
public class ClientHandler extends Thread {

    /**
     * Socket to establish a connection with the client.
     */
    private Socket socket;

    /**
     * To read data from the socket.
     */
    private BufferedReader input;

    /**
     * To write data in the socket.
     */
    private PrintWriter output;

    /**
     * Creates an instance of this class.
     *
     * @param socket Socket with the client.
     */
    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes all the arriving data and prints it and sends it to the client
     */
    @Override
    public void run() {
        try {
            while (true) {
                String text = input.readLine();
                if (text != null) {
                    System.out.println("Message received: " + text);
                    output.println(text);
                }
            }
        } catch (SocketException e) {
            System.out.println("The client has terminated connection");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
