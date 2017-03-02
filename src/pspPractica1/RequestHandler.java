package pspPractica1;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/**
 * Class that waits for clients connections. Reads the resource requested, and
 * serves it to clients..
 *
 * @author Eugenia Pérez Martínez
 * @mailto eugenia_perez@cuatrovientos.org
 *
 */
public class RequestHandler implements Runnable {

    /**
     * This class makes use of threads to become a multiclient server.
     */
    private Thread handler;
    /**
     * Buffer to read clients requests.
     */
    private BufferedReader input;
    /**
     * Output channel to send responses to clients.
     */
    private OutputStream output;
    /**
     * Path where the resources served will be searched.
     */
    private String serverDir;
    /**
     * To write responses in the output channel.
     */
    private PrintWriter writer;

    /**
     * Constructor.
     */
    public RequestHandler(BufferedReader input, OutputStream output) {
        this.input = input;
        this.output = output;
        this.writer = new PrintWriter(output);
        serverDir = MyProperties.getProperties().readProperty("public");
        handler = new Thread(this);
        handler.start();
    }

    /**
     * To start the handler.
     */
    public void run() {
        try {
            sendResponse(processRequest());
            closeStreams();
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Process the request from the clients. The clients will be web browsers
     * (Chrome, Firefox, etc.) and the requests will be under HTTP. This
     * function reads the first line, where the information we are after is, and
     * then return the requested resource.
     *
     * @return The resource requested by the client.
     */
    private String processRequest() {
        String fileRequested = "/";
        String line = "";
        String requestLine[];
        System.out.println("request: " +line);
        try {
            /* A browser request contains several lines. For now we are only
             * interested in the first one, which format is:
             *
             * GET /oviedo.html HTTP/1.1
             * 
             * Here we can see 3 different parts:
             * 1) HTTP method used. For now we will only consider GET and POST.
             * 2) Requested resource. In this case, it is the file oviedo.html.
             * 3) HTTP version used (can be ignored)
             */
            
            while ((line = input.readLine()) != null) {
                if (line.startsWith("GET") || line.startsWith("POST")) {
                    requestLine = line.split(" ");
                    return requestLine[1];
                }
            }
        } catch (Exception e) {
            System.out.println("Error while trying to read the request: " + e.getMessage());
        }
        return fileRequested;
    }

    /**
     * Serves the resource requested by the client, sending it over through a
     * Socket.
     *
     * @param fileName File requested by the client.
     */
    private void sendResponse(String fileName) throws IOException {
        if (fileName.equals("/")) {
            fileName = "/" + MyProperties.getProperties().readProperty("default");
        }

       try {            
            sendFile(fileName);           
        } catch (FileNotFoundException e) {
            String notFoundFileName = MyProperties.getProperties().readProperty("not_found");
            System.err.println("File " + fileName + " not found.");
            //sendHttpHeaders(ResponseCode.NOT_FOUND, fileStream.getChannel().size());
            //transferFileContent(new FileInputStream(serverDir + "/" + notFoundFileName));
            sendFile ("/" + notFoundFileName);
        } catch (Exception e) {
            System.err.println("Error while trying to send the file " + fileName + ": "
                    + e.getMessage());
        }
    }

    /**
     * Sends a file as plain text.
     *
     * @param file File to send to the client.
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void sendFile(String file) throws FileNotFoundException, IOException {
        FileInputStream fileStream = new FileInputStream(serverDir + file);
        System.out.println("Trying to open file: " + file);
        /*if (file.equals("/not_found.html")){
            sendHttpHeaders(ResponseCode.NOT_FOUND, fileStream.getChannel().size());
        }
        else{
            sendHttpHeaders(ResponseCode.OK, fileStream.getChannel().size());
        }*/
        transferFileContent(fileStream);
    }

    /**
     * Sends a stream of bytes of the file received as a parameter.
     *
     * @param fileStream
     * @throws IOException
     */
    private void transferFileContent(FileInputStream fileStream) throws IOException {
        while (true) {
            int b = fileStream.read();
            if (b == -1) {
                break; //end of file
            }
            writer.write(b);
        }
        writer.flush();
    }

    private void sendHttpHeaders(String stateLine, long numBytes) {
        StringBuilder response = new StringBuilder();

        /*TODO: concat each line or header to be sent to the client. We do it 
         * using a  StringBuilder because it is much more efficient that appending
         * strings.
         */
        response.append(stateLine);
        response.append("\n");
        response.append("Content-Length: ");
        response.append(numBytes);
        response.append("\n\n");
        writer.write(response.toString());

        System.out.print(response.toString());
    }
    /**
     * Closes the input and output streams.
     */
    private void closeStreams() {
        try {
            writer.close();
            output.close();
            input.close();
        } catch (Exception e) {
            System.out.println("Error while trying to close the communication streams: " + e.getMessage());
        }
    }
}
