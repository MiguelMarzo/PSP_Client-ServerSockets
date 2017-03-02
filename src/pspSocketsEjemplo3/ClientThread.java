/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pspSocketsEjemplo3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Simple client that sends text lines to the server and reads the response from
 * it.
 *
 * @author Eugenia Pérez Martínez
 * @mailto eugenia_perez@cuatrovientos.org
 */
class ClientThread extends Thread {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private static int counter = 0;
    private int id = counter++;
    private static int threadsCount = 0;
    private static int PORT = 8080;

    public static int getThreadsCount() {
        return threadsCount;
    }

    public ClientThread(InetAddress addr) {
        System.out.println("Creating the client " + id);
        threadsCount++;
        try {
            socket = new Socket(addr, ClientThread.PORT);
        } catch (IOException e) {
            System.err.println("The socket failed");
// If the creating of the socket fails, nothing needs 
// to be cleaned up.
        }
        try {
            input = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            output = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())), true);
            start();
        } catch (IOException e) {
// If any other error occurs, the socket must be closed:
            try {
                socket.close();
            } catch (IOException e2) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void run() {
        try {
            String option = "";
            String nota = "";
            Scanner in = new Scanner(System.in);
            while (!option.equals("BYE")) {
                System.out.println("1. Dejar mensaje");
                System.out.println("2. Leer mensaje");
                System.out.println("3. Salir");
                option = in.nextLine();
                switch (option) {
                    case "1":
                        output.println("DN");
                        if (input.readLine().equals("DN-OK")) {
                            System.out.println("Introduce texto para la nota:");
                            output.println(in.nextLine());
                        } else {
                            System.out.println("Error no se puede enviar nota");
                        }
                        break;
                    case "2":
                        output.println("LN");
                        System.out.println("Nota: " + input.readLine());
                        break;
                    case "3":
                        output.println("BN");
                        String str = input.readLine();
                        if (str.equals("BN-OK")) {
                            System.out.println("Nota borrada correctamente");
                        } else if (str.equals("BN-VOID")) {
                            System.out.println("No habia nota para borrar");
                        } else {
                            System.out.println("Error");
                        }
                    case "4":
                        output.print("BYE");
                        if (input.readLine().equals("BYE-OK")) {
                            socket.close();
                        } else {
                            System.out.println("Bye Error");
                        }
                        break;
                    default:
                        System.out.println("Opcion no válida");
                        break;
                }
            }
            socket.close();
            output.println("END");
        } catch (IOException e) {
            System.err.println("I/O Exception");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
                threadsCount--;
            }
        }
    }
}
