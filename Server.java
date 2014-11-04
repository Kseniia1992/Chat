package pack;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ksolodovnik
 * A Simple server that receives the message from the client.
 * If server receives message 'bye' it stops
 */
public class Server {
    /* socket listens for the client requests  */
    private static ServerSocket server_socket = null;
    
    /* socket for connection to server_socket */
    private static Socket client_socket = null;
    
    /* server port */
    private static final int PORT = 8080;
    
    /* Message for closing connection */
    private static final String END_MESSAGE = "bye";
    
    /* input stream for receiving messages from client */
    private static InputStream in = null;
    
    /**
     * Main method
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        server_socket = new ServerSocket(PORT);
        System.out.println("Waiting...");
        
        client_socket = server_socket.accept();
        System.out.println("Client accepted: " + client_socket);
        
        in = client_socket.getInputStream();
        
        readMsg();
    }
    
    /**
     * Method for reading messages from client.
     * If server receives message 'bye' it stops
     * @throws IOException
     */
    private static void readMsg() throws IOException {
        try{
            while(true) {
                byte[] readBuffer = new byte[20];
                int num = in.read(readBuffer);
                if (num > 0) {
                    String readMessage = new String(readBuffer, 0, num);
                    System.out.println("Received message " + readMessage);
                    if (readMessage.equals(END_MESSAGE))
                        break;
                }
            }
        }finally{
            //closing input stream and sockets
            in.close();
            client_socket.close();
            server_socket.close();
        }
    }
}





