package pspSocketsEjemplo3;

// Un servidor que usa el multihilo
// para manejar cualquier número de clientes.
import java.util.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Server which uses multithreading to deal with a number of clients.
 *
 * @author Eugenia Pérez Martínez
 * @mailto eugenia_perez@cuatrovientos.org
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private boolean running;
    private static String nota = "";
    public ClientHandler(Socket s) throws IOException {
        socket = s;
        input = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
        output = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        socket.getOutputStream())),
                        true);
        // If any of the code above throws an exception, 
        // the caller is responsible
        // for closing the socket.
        start(); // Calls the run() method
    }

    @Override
    public void run() {
        try {
            running = true;
            while (running) {
                String str = input.readLine();
                switch (str) { 
                    case "DN":
                        output.println("DN-OK");
                        nota = input.readLine();
                        break;
                    case "LN":
                        System.out.println("Enviando la ultima nota");
                        output.println(nota);
                        break;
                    case "BN":
                        if (nota != ""){
                            nota = "";
                            output.println("BN-OK");
                        } else {
                            output.println("BN-VOID");
                        }
                        break;
                    case "BYE":
                        System.out.println("Adiosito");
                        output.println("BYE-OK");
                        socket.close();
                        running = false;
                        break;                        
                    default:
                        break;
                }
                System.out.println("Doing echo: " + str);
                output.println(str);
            }
            System.out.println("Closing...");
        } catch (IOException e) {
            System.err.println("I/O Exception");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }
}
