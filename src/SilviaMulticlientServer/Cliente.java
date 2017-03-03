package SilviaMulticlientServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {

    private static BufferedReader std = new BufferedReader(new InputStreamReader(System.in));

    private static String printMenu() {
        String option = "";
        System.out.println("Escribe un comando para el servidor");
        try {
            option = std.readLine();
        } catch (Exception e) {
            System.out.println("Error en getOption");
        }
        return option;
    }

    public static void main(String[] args) throws IOException {

        InetAddress addr = InetAddress.getByName(null);

        System.out.println("Nos queremos conectar a = " + addr);
        Socket socket = new Socket(addr, 8080);

        // Guardar todo en un try-finally para asegurarse
        // de que se cierra el socket:
        System.out.println("Conexión correcta. Socket= " + socket);

        //////////////////////////////////////////////
        //2 Crear las variables "entrada" y "salida"//
        //////////////////////////////////////////////
        //creamos las variables de entrada y salida para leer y escribir del socket
        //ambas variables se crean usando el socket (variable "socket" )
        //la "entrada" será de tipo BufferedReader y se crea unsando un InputStreamReader
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //la "salida" será de tipo PrintWriter, y similar a "entrada" se contruye por medio de
        // un BufferedWriter que se crea usando un OutputStreamWriter
        PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

        ///////////////////////////////////////////
        //3	Realizar la transferencia de mensajes//
        ///////////////////////////////////////////
        boolean seguir = true;
        while (seguir) {  //ciclamos indefinidamente (salvo que haya un error o queramos desconectar)
            String option = printMenu();
            switch (option) {
                case "SUM":
                    try {
                        salida.println("SUM");
                        //recibir mensaje
                        String str = entrada.readLine();
                        //comprobamos que leemos OP-ok
                        if (!str.equals("OP-OK")) {
                            System.out.println("ERROR: no hemos leído OP-OK");
                        } else {
                            System.out.println("El servidor nos ha enviado un OP-OK");
                            //enviar siguiente numero
                            System.out.println("Vamos a hacer una SUMA");
                            System.out.println("Introduce el dato:");
                            int numero = Integer.parseInt(std.readLine());
                            //ecribir en el socket el numero
                            salida.println(numero);
                            System.out.println(entrada.readLine());
                        }
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }
                    break;
                case "RES":
                    try {
                        salida.println("RES");
                        //recibir mensaje
                        String str = entrada.readLine();
                        //comprobamos que leemos OP-ok
                        if (!str.equals("OP-OK")) {
                            System.out.println("ERROR: no hemos leído OP-OK");
                        } else {
                            System.out.println("El servidor nos ha enviado un OP-OK");
                            //enviar siguiente numero
                            System.out.println("Vamos a hacer una RESTA");
                            System.out.println("Introduce el dato:");
                            int numero = Integer.parseInt(std.readLine());
                            //ecribir en el socket el numero
                            salida.println(numero);
                            System.out.println(entrada.readLine());
                        }
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }
                    break;
                case "MUL":
                    try {
                        salida.println("MUL");
                        //recibir mensaje
                        String str = entrada.readLine();
                        //comprobamos que leemos OP-ok
                        if (!str.equals("OP-OK")) {
                            System.out.println("ERROR: no hemos leído OP-OK");
                        } else {
                            System.out.println("El servidor nos ha enviado un OP-OK");
                            //enviar siguiente numero
                            System.out.println("Vamos a hacer una MULTIPLICACION");
                            System.out.println("Introduce el dato:");
                            int numero = Integer.parseInt(std.readLine());
                            //ecribir en el socket el numero
                            salida.println(numero);
                            System.out.println(entrada.readLine());
                        }
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }
                    break;
                case "DIV":
                    try {
                        salida.println("DIV");
                        //recibir mensaje
                        String str = entrada.readLine();
                        //comprobamos que leemos OP-ok
                        if (!str.equals("OP-OK")) {
                            System.out.println("ERROR: no hemos leído OP-OK");
                        } else {
                            System.out.println("El servidor nos ha enviado un OP-OK");
                            //enviar siguiente numero
                            System.out.println("Vamos a hacer una DIVISION");
                            System.out.println("Introduce el dato:");
                            int numero = Integer.parseInt(std.readLine());
                            //ecribir en el socket el numero
                            salida.println(numero);
                            System.out.println(entrada.readLine());
                        }
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }
                    break;
                case "EXIT":
                    System.out.println("Vamos a cerrar la conexion con el servidor");
                    salida.println("EXIT");
                    String str = entrada.readLine();
                    if (!str.equals("EXIT-OK")){
                        System.out.println("Error: no hemos leido EXIT-OK");
                    } else {
                        System.out.println("El servidor nos ha enviado un EXIT-OK");
                        socket.close();
                        System.out.println("Conexion con el servidor CERRADA");
                        seguir = false;
                    }
                    break;
                default:
                    System.out.println("Comando no válido");
                    break;
            }
        }
        socket.close();
    }

}
