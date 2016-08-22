package servidor;

import java.net.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ServidorSO extends ConexionServ //Se hereda de conexión para hacer uso de los sockets y demás
{
  public static boolean validaNumServer = false;
  private static ContenedorServer contenedor;
  private static BaseRedis Base=new BaseRedis();
  public static boolean verificaConexion = false;

 
    public ServidorSO(String args) throws IOException {
        super("servidor", args);
    } //Se usa el constructor para servidor de Conexion

    public void iniConex()//Método para iniciar el servidor
    {
        
        try {
            System.out.println("Esperando..."); //Esperando conexión
            Base=new BaseRedis();
            Base.iniciarBase();
            //Base.iniciarBase();
            contenedor = new ContenedorServer();
            ExecutorService producers = Executors.newFixedThreadPool(5);
          for (int i = 0; i < 5; i++) {
               producers.execute(new  ThreadProducer (ss,contenedor,i));
          }
      
             ExecutorService consumers = Executors.newFixedThreadPool(5);
          for (int i = 0; i < 5; i++) {
               consumers.execute(new  ThreadConsumidor(Base,contenedor,i));
          }
          consumers.shutdown();
          producers.shutdown();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

 
}
