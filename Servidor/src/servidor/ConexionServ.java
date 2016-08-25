/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author Evelyn Gonzalez
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionServ
{
    protected int PUERTO = 1234; //Estableciendo conexión
    protected String HOST = "localhost"; //Host para la conexión
    protected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente; //Flujo de datos de salida
    
    public ConexionServ(String tipo,String args) throws IOException //Constructor
    {
        PUERTO=Integer.parseInt(args);
        if(tipo.equalsIgnoreCase("servidor"))
        {
              try {
                ss = new ServerSocket(PUERTO);
                cs = new Socket(); 
            } catch (Exception e) {
                System.out.println("Mensaje Servidor => NO esta disponible por el momento");
            }
        
        }
        else
        {
            try {
                  cs = new Socket(HOST, PUERTO); 
            } catch (Exception e) {
                System.out.println("Mensaje Servidor => NO esta disponible por el momento, porfavor intenta mas tarde");
            }
        }
    }
} 
