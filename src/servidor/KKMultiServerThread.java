package servidor;

import java.net.*;
import java.util.Set;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


 public class KKMultiServerThread extends Thread {
    private Socket socket = null;
    private static final int MAX_ARRAY_SIZE = 128;
    private static final int MAX_ARRAY_SIZE_VAL = 2048;
    public KKMultiServerThread(Socket socket) {
	super("KKMultiServerThread");
	this.socket = socket;
    }

    public void run() {
        PrintWriter out=null;
        BufferedReader in=null;
	try {
	    out = new PrintWriter(socket.getOutputStream(), true);
	    in = new BufferedReader(
				    new InputStreamReader(
				    socket.getInputStream()));

	    String inputLine, outputLine;
	    KVKeyValuehasp kvKeyValuehasp= new KVKeyValuehasp();
	    //out.println("Bienvenido KEY / VALUE");
	    while ((inputLine = in.readLine()) != null) {
		String [] comando = inputLine.split("\\s+");//Valida mas de un espacio
                if(comando[0].toUpperCase().equals("SET")){//Soporta mayusculas y minusculas
	            kvKeyValuehasp.putKeyValue(comando[1], comando[2]);
                    out.println("OK");
		} else if(comando[0].toUpperCase().equals("GET")){
		    if(kvKeyValuehasp.getKeyValue(comando[1].trim())!=null){
                        out.println(comando[1]+" = "+kvKeyValuehasp.getKeyValue(comando[1].trim()));
                    }else{
                        out.println(comando[1]+" = ");
                    }	
		} else if(comando[0].toUpperCase().equals("DEL")){//Soporta mayusculas y minusculas
                        kvKeyValuehasp.delKeyValue(comando[1].trim());
			out.println("OK");
		} else if(comando[0].toUpperCase().equals("LIST")){ //Soporta mayusculas y minusculas
			Set<String> keys=kvKeyValuehasp.getListKeyValue();
			String key="";
                        for (String s : keys) {
				key=key.concat(s+ " \t");
                        }
                        out.println(key);
		} else{
                    out.println("ERROR: Debe insertar un comando, help para ayuda");
                }
            }
	} catch (IOException e) {
	    //e.printStackTrace();
	} finally{
            try {
                out.close();
                in.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(KKMultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
