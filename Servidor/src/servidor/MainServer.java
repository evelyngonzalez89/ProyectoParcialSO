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
import java.io.IOException;
import servidor.ServidorSO;



//Clase principal que hará uso del servidor
public class MainServer
{
    public static void main(String[] args) throws IOException
    {        
        ServidorSO servidor = new ServidorSO(args[0]); //Se crea el servidor
        
        System.out.println("Iniciando servidor\n");
        servidor.iniConex(); //Se inicia el servidor
    }
}
