package pspSocketsEjemplo4Chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.FileOutputStream;
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

    private String convo = "";

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
                    if (text.equals("exportar")) {
                        Writer writer = new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream("src/pspSocketsEjemplo4Chat/exportedChat.txt"), "utf-8"));
                        writer.write("--------Exporting--------\n");
                        writer.write(convo);
                        writer.close();
                        //PrintWriter writer = new PrintWriter("exportedChat.txt", "UTF-8");
                        //writer.println("--------Exporting--------");
                        //writer.println(convo);
                        //writer.close();
                        System.out.println("--------Exporting--------");
                        System.out.println(convo);
                    } else {
                        System.out.println("Message received: " + text);
                        output.println(text);
                        convo += text + ("\n");
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("The client has terminated connection");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
