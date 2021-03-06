package pspSocketsEjercicio3;

import pspSocketsEjemplo.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Miguel
 */
public class Client {

    public static void main(String[] args) throws IOException {
        // When passing null to getByName, localhost is generated
        InetAddress addr = InetAddress.getByName("172.30.0.128");
        //OhterWise, these can be used for the same purpose;
        //InetAddress addr = InetAddress.getByName("127.0.0.1");
        System.out.println("addr = " + addr);
        Socket socket = new Socket("172.30.0.128", Server.PORT);

        //Put everything inside a try-finally to make sure gets closed
        try {
            System.out.println("socket = " + socket);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            // The output is emptied automatically by the PrintWriter:
            PrintWriter output;
            output = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())), true);
            for (int i = 0; i < 10; i++) {
                output.println("Message " + i);
                String str = input.readLine();
                System.out.println(str);
            }
            output.println("END");
        } finally {
            System.out.println("Closing...");
            socket.close();
        }
    }

}
