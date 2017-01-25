package pspSocketsEjemplo4Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ChatServer. Accepts clients connections, creates a thread 
 * to talk with them and waits for the next connection.
 *
 * @author Eugenia Pérez Martínez
 * @mailto eugenia_perez@cuatrovientos.org
 *
 */
public class ChatServer {

    /**
     * Port.
     */
    private static final int PORT = 8080;

    /**
     * Entry point.
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started");
            while (true) {
                Socket client = serverSocket.accept();
                ClientHandler newClient = new ClientHandler(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
