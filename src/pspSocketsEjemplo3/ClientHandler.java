package pspSocketsEjemplo3;

import java.io.*;
import java.net.*;
/**
 *
 * @author Miguel
 */
public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    
    public ClientHandler(Socket s) throws IOException {
        socket = s;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        
        //TERMINAR....
    }
}
