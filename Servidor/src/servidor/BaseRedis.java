
package servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Evelyn Gonzalez
 */
public class BaseRedis {

    HashMap Base = new HashMap();
    boolean lectura = false;
    boolean escritura = false;

    public BaseRedis() {
    }

    public HashMap getBase() {
        return Base;
    }

    public void setBase(HashMap Base) {
        this.Base = Base;
    }

    public void iniciarBase() {
        Base.put("1", "1 elemento");
 }

    public void iniciarBase2() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("prueba.txt"));

            String line;
            String[] values;

            while (((line = in.readLine()) != null)) {
                values = line.split("\\t");
                String clave = values[0];
                String valor = values[1];
                //System.out.println(clave + " "+ valor);
                Base.put(clave, valor);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void guardarArchivo(String nombreArchivo, String ruta) {
        try
        {
          BufferedReader in = new BufferedReader( new FileReader(ruta));
              
            String line;
            ArrayList<String> values = new ArrayList<String>();
            //String[] values;
            int i=0;
            while(((line = in.readLine()) != null))
            {
                i++;
                String valor = line;
                Base.put(nombreArchivo+"."+i, line);
                System.out.println(line);
                /*System.out.println(valor);
                values.add(valor);*/
                //ThreadServer.salida.println(line);
            }
            //Base.put(nombreArchivo, values);
 
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
      }
    }
    
    public void guardarDatos(String nombreArchivo, String value) {
          String [] lineas = value.split("\\n");  
          
             
            String valor = "";
            int k =0;
            int longitud = value.length();
            int i=0;
            int inicio=0;
            int fin=0;
            if(longitud < 50000) {
                Base.put(nombreArchivo, value);
            }
            else{
                while(fin<(longitud-1)){
                    fin=fin+50000;
                    if(fin<= (longitud-1)){
                            valor=value.substring(inicio, fin);
                            inicio=fin+1;
                             Base.put(nombreArchivo+"."+k, valor);
                    //System.out.println(nombreArchivo+"."+k +"  "+ valor);
                    k++;
                            
                        }
                    else break;
//                    if(i==100){
//                        System.out.println(i);
//                    }
                   
                }
            }
        }
        
    
    
    public void leerArchivo(String nombreArchivo) {
        
            Iterator it = Base.entrySet().iterator();
            while(it.hasNext()) {
             Map.Entry mentry = (Map.Entry)it.next();
             if(mentry.getKey().toString().startsWith(nombreArchivo)== true){
                 System.out.print("key: "+ mentry.getKey() + " & Value: ");
                 System.out.println(mentry.getValue());
                 ThreadServer.salida.println(mentry.getValue());
             }
             
             
            }   
            
    }
    

    public synchronized void getvalor(String key, PrintWriter out) throws InterruptedException {
        while (escritura) {
            wait();
        }
        lectura = true;
        Object value = this.Base.get(key);
        boolean control=false;
        lectura = false;
        notify();
        if (value == null) {
             value = this.Base.get(key+".0");
             if(value!=null){
                 int iterador =0;
                 while(value!=null){
                    value = this.Base.get(key+"."+iterador);
                    if(value!=null){
                      control = true;
                      out.println("Linea " +value);
                    }
                    iterador++;
                 }
                 if(control == true){
                    control=false;
                    out.println("Fin resultado - key:");
                 }
                 //return "";
             }else
             out.println("Key:");
        } else {
            out.println("Key:" + (String) value);
            
        }
    }

    public synchronized ArrayList listvalor() throws InterruptedException {
        ArrayList lista;
        while (escritura) {
            wait();
        }
        lectura = true;
        lista = new ArrayList<String>(this.Base.keySet());
        lectura = false;
        return lista;
    }
    
    public synchronized ArrayList listvalor2() throws InterruptedException {
        ArrayList lista;
        int tam;
        int j=0;
        String valor;
        String [] arreglo;
        while (escritura) {
            wait();
        }
        lectura = true;
        lista = new ArrayList<String>(this.Base.keySet());
        tam = lista.size();
        for(int i=0; i<tam; i++){
            //if(lista.get(i).toString().s)
            valor=lista.get(i).toString();
            arreglo= valor.split("\\.");
            
            if(arreglo.length !=0){
                valor=arreglo[0];
                lista.set(i, arreglo[0]);
            }
        }
        Set se =new HashSet(lista);
        lista.clear();
        lista = new ArrayList<String>(se);

        
        
        lectura = false;
        return lista;
    }

    public synchronized String delvalor(String key) throws InterruptedException {
        while (escritura || lectura) {
            wait();
        }
        lectura = true;
        Object value = this.Base.get(key);
        lectura = false;
        if (value == null) {
            return "ERROR: Este key no existe ,consultelo con el comando list";
        } else {
            escritura = true;
            this.Base.remove(key);
            escritura = false;
            return "Registro eliminado con clave: " + key;
        }
    }
    
    public synchronized String delvalor2(String key) throws InterruptedException {
        while (escritura || lectura) {
            wait();
        }
        lectura = true;
        Object value = this.Base.get(key);
        lectura = false;
        if (value == null) {
            value = this.Base.get(key+".0");
             if(value!=null){
                 int iterador =0;
                 while(value!=null){
                    value = this.Base.get(key+"."+iterador);
                    if(value!=null){
                      this.Base.remove(key+"."+iterador);
                    }
                    iterador++;
                 }
                 return "Se borró el registro con clave: "+key;
             }
             else{
                return "Error: Registro con clave: "+key+" no existe"; 
                
             }
            //return "ERROR: Este key no existe ,consultelo con el comando list";
        } else {
            escritura = true;
            this.Base.remove(key);
            escritura = false;
            return "Registro eliminado con clave: " + key;
        }
    }

    public synchronized String setvalor(String key, String newValue) throws InterruptedException {

        while (lectura) {
            wait();
        }
        escritura = true;
        Object value = this.Base.get(key);
        if (value == null) {
             escritura = false;
              notify();
            return "ERROR:" + key + " no existe";
        } else {
            this.Base.put(key, newValue);
            escritura = false;
            notify();
            return "Se cambio el valor de " + key;
        }
    }

    public synchronized String putvalor(String key, String value) throws InterruptedException {
        while (lectura) {
            wait();
        }
        escritura = true;
        this.Base.put(key, value);
        escritura = false;
        notify();
        return "Se agrego el nuevo objeto: " + key + ", a la lista";
    }
    
    public synchronized String putvalor2(String key, String value) {
        this.Base.put(key, value);
        guardarArchivo(key, value);
        return "Se agregó el nuevo objeto: " + key + ", a la lista";
    }
    
    public synchronized String putvalorInput (String key, String value) {
        guardarDatos(key, value);
        return "OK";
    }
}
