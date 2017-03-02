
package pspSocketsEjemplo3;

import java.io.IOException;
import java.net.InetAddress;

/**
 * Creates several clients which connect to the server.
 * @author Eugenia Pérez Martínez
 * @mailto eugenia_perez@cuatrovientos.org
 */
public class Multiclient {

    static final int MAX_THREADS = 1;

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress addr = InetAddress.getByName(null);
        while (true) {
            if (ClientThread.getThreadsCount() < MAX_THREADS) {
                new ClientThread(addr);
            }
            Thread.currentThread().sleep(100);
        }
    }
}
