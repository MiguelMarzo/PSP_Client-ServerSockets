package pspServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classs that represents a Web Server.
 *
 * @author Eugenia Pérez Martínez
 * @mailto eugenia_perez@cuatrovientos.org
 *
 */
public class WebServer {

    /**
     * If no port is specified, the following one will be chosen.
     */
    private final static int DEFAULT_PORT = 8080;

    /**
     * Constructor.
     */
    WebServer() {
        bootstrap();
    }

    /**
     * Launches the server.
     */
    private void bootstrap() {
        int port = DEFAULT_PORT;
        ServerSocket serverSocket;
        Socket clientSocket = null;

        System.out.println("Server " + MyProperties.getProperties().readProperty("name") + " starting");

        if (!MyProperties.getProperties().readProperty("port").isEmpty()) {
            port = Integer.parseInt(MyProperties.getProperties().readProperty("port"));
        }

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("New request from: " + clientSocket.getInetAddress().getHostAddress());

                new RequestHandler(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())),
                        clientSocket.getOutputStream());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        new WebServer();
    }
}
