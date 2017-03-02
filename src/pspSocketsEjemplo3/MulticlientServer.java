package pspSocketsEjemplo3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server able to handle serveral concurrent clients.
 *
 * @author Eugenia Pérez Martínez
 * @mailto eugenia_perez@cuatrovientos.org
 */
public class MulticlientServer {

    static final int PORT = 8080;

    public static void main(String[] args)
            throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server started");
        try {
            while (true) {
                // Blocks until connection happens:
                Socket socket = s.accept();
                try {
                    new ClientHandler(socket);
                } catch (IOException e) {
                    // If it fails, close the socket
                    socket.close();
                }
            }
        } finally {
            s.close();
        }

    }
}
