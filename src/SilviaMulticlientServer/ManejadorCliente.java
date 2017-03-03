package SilviaMulticlientServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejadorCliente extends Thread {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;    
    public static int numeroBase = 0;
    

    public static String mensaje = "";      //Esta variable es común para todos los ManejadoresClientes

    public ManejadorCliente(Socket s)
            throws IOException {

        socket = s;
        entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

        start();
    }

    @Override
    public void run() {

        boolean seguir = true;
        int numero;
        while (seguir) {

            String str;
            try {
                //leemos mensaje del cliente por el socket
                str = entrada.readLine();
                System.out.println("Leído de cliente " + str);

                switch (str) {
                    case "SUM":
                        salida.println("OP-OK");
                        numero = Integer.parseInt(entrada.readLine());
                        int suma = numeroBase + numero;
                        System.out.println("Leido del cliente el dato " + numero);
                        salida.println(numeroBase + "+" + numero + "=" + suma);
                        numeroBase = suma;
                        System.out.println("Valor actualizado del dato común: " + numeroBase);
                        break;
                    case "RES":
                        salida.println("OP-OK");
                        numero = Integer.parseInt(entrada.readLine());
                        int resta = numeroBase - numero;
                        System.out.println("Leido del cliente el dato " + numero);
                        salida.println(numeroBase + "-" + numero + "=" + resta);
                        numeroBase = resta;
                        System.out.println("Valor actualizado del dato común: " + numeroBase);
                        break;
                    case "MUL":
                        salida.println("OP-OK");
                        numero = Integer.parseInt(entrada.readLine());
                        System.out.println("Leido del cliente el dato " + numero);
                        int mult = numeroBase * numero;
                        salida.println(numeroBase + "*" + numero + "=" + mult);
                        numeroBase = mult;
                        System.out.println("Valor actualizado del dato común: " + numeroBase);
                        break;
                    case "DIV":
                        salida.println("OP-OK");
                        numero = Integer.parseInt(entrada.readLine());
                        System.out.println("Leido del cliente el dato " + numero);
                        int div;
                        if (numero != 0) {
                            div = numeroBase / numero;
                            if (numeroBase % numero == 0) {
                                salida.println(numeroBase + "/" + numero + "=" + div);
                                numeroBase = (int) div;
                                System.out.println("Valor actualizado del dato común: " + numeroBase);
                            } else {
                                System.out.println("No se ha actualizado el valor del daton comun");
                                salida.println("Error: Division NO entera");
                                break;
                            }
                        } else {
                            salida.println("El numero no puede ser 0");
                            System.out.println("No se ha actualizado el valor del daton comun");
                            break;
                        }
                        break;
                    case "EXIT":
                        System.out.println("Leido del cliente el dato " + str);
                        salida.println("EXIT-OK");
                        System.out.println("Cerrando el socket y el hilo");
                        socket.close();
                        seguir = false;
                        break;
                    default:
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ManejadorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Termina el hilo");
    }
}
