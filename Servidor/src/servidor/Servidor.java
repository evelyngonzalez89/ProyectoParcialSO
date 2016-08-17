/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.*;
import java.util.HashMap;

/**
 *
 * @author Evelyn Gonzalez
 */
public class Servidor extends ConexionServ {
      public static boolean validNumServer = false;
      private static ContenedorServer contenedor;
      public static boolean verificaConexion = false;
      public Servidor(String args) throws IOException {
        super("servidor", args);
    } //Implementación del super - ConexionServ.java

    /**
     * @param args the command line arguments
     */
    public static void iniConex(String args) throws IOException{
        ServerSocket serverSocket = null;
        boolean listening = true;
        int port = 0;
        if (args.length()>0 && args!= null) {
            try {
                port = Integer.parseInt(args);
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
