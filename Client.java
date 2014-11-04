package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * A simple client that sedns messages to the server.
 * If client sends message 'bye' it stops
 * @author ksolodovnik
 */
public class Client {
    
    /* port for connection */
    private static final int PORT = 8080;
    
    /* Message for closing connection */
    private static final String END_MESSAGE = "bye";
    
    /* Client socket for sending and receiving data */
    private static Socket socket = null;
    
    /* output stream for sending messages to the server */
    private static OutputStream out = null;
    
    /**
     * Main method
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        try {
            socket = new Socket(InetAddress.getLocalHost().getHostName(), PORT);
            System.out.println("Connected");
            
            out = socket.getOutputStream();
            
            writeMsg();
        }catch(ConnectException ce){
            System.out.println("Start server and then start client");
        }
    }
    
    /**
     * Method for writing messages to the server.
     * If client sends message 'bye' it stops
     * @throws IOException
     */
    private static void writeMsg() throws IOException{
        try{
            //reading data from console
            BufferedReader inputReader;
            while (true){
                inputReader = new BufferedReader(new InputStreamReader(System.in));
                //reading a line of text
                String message = inputReader.readLine();
                out.write(message.getBytes());
                if(message.equals(END_MESSAGE))
                    break;
            }
        }finally{
            //closing output stream and socket
            out.close();
            socket.close();
        }
    }
}
