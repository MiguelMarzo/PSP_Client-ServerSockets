package pspGetIp;

import java.util.logging.Logger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;

/**
 *
 * @author Miguel
 */
public class GetIp {
    public static void main(String[] args) {
        System.err.println("How to use: WhoAmI PCName");
        InetAddress a = null;
        try {
            a = InetAddress.getByName("DESKTOP-BN36HO9");
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(GetIp.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(a);
    }
}
