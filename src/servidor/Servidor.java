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
 * @author Evelyn Gonzalez
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        int port = 0;
        if (args.length>0 && args[0] != null) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.out.println("ERROR: Puerto invalido");
                System.exit(0);
            }
        } else {
            System.out.println("ERROR: Debe enviar el puerto");
            System.exit(0);
        }

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("No esta escuchando en el Puerto: " + port);
            System.exit(-1);
        }

        while (listening) {
            System.out.println("Esperando conexion");
            new KKMultiServerThread(serverSocket.accept()).start();
        }
        serverSocket.close();
    }

}
