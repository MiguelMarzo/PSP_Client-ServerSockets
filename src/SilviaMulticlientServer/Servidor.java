package SilviaMulticlientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

    static final int PUERTO = 8080;

    public static void main(String[] args) throws IOException {

        ServerSocket s = new ServerSocket(PUERTO);
        System.out.println("Servidor iniciado en el puerto " + PUERTO);
        
        
        try {
            while (true) {


                // Se bloquea hasta que se da una conexión:
                //creamos un socket por cada conexion
                Socket socket = s.accept();            
                try {
                    
                    //llamamos a una clase que gestione cada uno de los hilos
                    new ManejadorCliente(socket);
                    
                    
                } catch (IOException e) {
                    
                    // Si falla, cerrar el socket,
                    // si no, lo cerrará el hilo:
                    socket.close();
                }
            }
        } finally {
            
            // Tanto como si falla como si se termina el programa cerrar el socket,
            // en este caso nunca termina si no falla porque espera nuevos clientes
            s.close();
        }
    }
}


