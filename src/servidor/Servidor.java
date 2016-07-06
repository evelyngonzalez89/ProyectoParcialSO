/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Jessica
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        int port;
    	port = Integer.parseInt(args[0]); 

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("No esta escuchando en el Puerto: "+port);
            System.exit(-1);
        }
        
        while (listening) 
        {
            /*try
            {
            	 /*System.out.println("Esperando conexion");
                 // Wait here and listen for a connection
                 Socket s1 = serverSocket.accept();
                 // Get output stream associated with the socket
                 OutputStream s1out = s1.getOutputStream();
                 DataOutputStream dos = new DataOutputStream(s1out);
                 // Send your string!
                 dos.writeUTF("Hello Net World!");
                 // Close the connection, but not the server socket
                 dos.close();
                 s1.close();
            } 
            catch (IOException e) 
            { 
                e.printStackTrace();
            }*/
        	System.out.println("Esperando conexion");
    	    new KKMultiServerThread(serverSocket.accept()).start();
        } //end of while


        serverSocket.close();
    }
    
}
