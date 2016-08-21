/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Evelyn Gonzalez and Karen Ponce

 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String host = "";
        String valida = null;
        int port = 0;
        
        if(args.length>0 && args[0] != null && !args[0].equals("")){
            host = args[0];
        } else {
            System.out.println("ERROR: Debe enviar el host");
            System.exit(0);
        }
        if(args.length>1 && args[1] !=null){
            try{
                port = Integer.parseInt(args[1]);
            }catch(Exception e){
                System.out.println("ERROR: Puerto invalido");
                System.exit(0);
            }
        } else {
            System.out.println("ERROR: Debe enviar el puerto");
            System.exit(0);
        }
        
        
        System.out.println("Bienvenidos KEY/VALUE Store");
        System.out.println("Ingrese el comando ó 'help' para ayuda");
        try {
            kkSocket = new Socket(host, port);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("No tiene acceso al host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Se ha perdido la conexion..");
            System.exit(1);
        }

        // new KVValidacionesCliente();
        KVValidacionesCliente KVValidacionesClient = new KVValidacionesCliente();
        Scanner scannerFile = new Scanner(System.in);
        while (scannerFile.hasNextLine() && (valida = KVValidacionesClient.validate(scannerFile.nextLine())) != null) {
            //if (valida != null) {
            if (!valida.equals("error")) {
                String fromServer;
                //String fromUser;
                out.println(valida);
                try {
                    while ((fromServer = in.readLine()) != null) {
                        System.out.println("Server: " + fromServer);
                        System.out.println("-----------------------------------------------");
                        break;
                    }
                } catch (SocketException se) {
                    System.out.println("ERROR: Problemas de conexion con el servidor");
                }
            }
            System.out.println("-----------------------------------------------");
        }

        out.close();
        in.close();
        kkSocket.close();
    }
}
