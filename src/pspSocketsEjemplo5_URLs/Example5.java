package pspSocketsEjemplo5_URLs;

import java.net.URL;
import java.net.MalformedURLException;
/**
 *
 * @author Miguel
 */
public class Example5 {
    
    public static void main(String[] args) {
        URL url;
        try {
            url = new URL("http://eugeniaperez.es/wordpress/index.php");
            System.out.println("URL visited: Displaying information");
            System.out.println("-----------------------------------");
            System.out.println("protocol = " + url.getProtocol());
            System.out.println("authority = " + url.getAuthority());
            System.out.println("host = " + url.getHost());
            System.out.println("port = " + url.getPort());
            System.out.println("path = " + url.getPath());
            System.out.println("query = " + url.getQuery());
            System.out.println("file name = " + url.getFile());
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
