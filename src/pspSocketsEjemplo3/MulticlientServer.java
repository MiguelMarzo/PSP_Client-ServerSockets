package pspSocketsEjemplo3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Miguel
 */
public class MulticlientServer {
    static final int PORT = 8080;
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server started");
        while (true) {
            //Blocks until connection happensa;
            Socket socket = s.accept();
            try {
                new ClientHandler(socket);
            } catch (IOException e){
                //If it fails, close the socket
                socket.close();
            }
        }
    }            
}
