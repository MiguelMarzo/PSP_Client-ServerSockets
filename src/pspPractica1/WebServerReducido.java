/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pspPractica1;

/**
 *
 * @author Silvia
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServerReducido {

    /**
     * If no port is specified, the following one will be chosen.
     */
    private final static int DEFAULT_PORT = 8080;

    /**
     * Constructor.
     */
    WebServerReducido() {
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
        new WebServerReducido();
    }
}
