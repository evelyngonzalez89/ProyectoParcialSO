/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emele_000
 */
public class ThreadProducer extends Thread {

    private final int numero;
    private ServerSocket serversocket = null;
    private final ContenedorServer contenedor;
    

    public ThreadProducer(ServerSocket serversocket, ContenedorServer contenedor, Integer numero) {
        super("ThreadServer");
        this.serversocket = serversocket;
        this.numero = numero;
        this.contenedor = contenedor;
    }

    @Override
    public void run() {
        while (true) {
            if(ServidorSO.validaNumServer==true){
                System.exit(0); 
                break;
            }
                
            
            Socket incoming;
            try {
                incoming = serversocket.accept();
                contenedor.put(incoming);
            } catch (Exception ex) {
                System.out.println("Mensaje Servidor => Se encuentra en ejecución");
                ServidorSO.validaNumServer=true;
                break;
                
            }
             
             
           

        }
    }
}
