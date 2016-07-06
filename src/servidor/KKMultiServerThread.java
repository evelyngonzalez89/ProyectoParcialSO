package servidor;

import java.net.*;
import java.util.Set;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KKMultiServerThread extends Thread {
    private Socket socket = null;

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
	    //KnockKnockProtocol kkp = new KnockKnockProtocol();
	    //outputLine = kkp.processInput(null);
	    //out.println(outputLine);
	    KVKeyValuehasp kvKeyValuehasp= new KVKeyValuehasp();
	    out.println("Bienvenido");
	    while ((inputLine = in.readLine()) != null) {
		String [] comando = inputLine.split(" ");
		
		if(comando[0]==null || comando[0].equals("")){
			out.println("Debe insertar un comando, help para ayuda");
		} else if(comando[0].equals("put")){
			if(comando[1]==null || comando[1].equals("") || comando[2] == null || comando[2].equals("")){
				out.println("Debe insertar el key y el value: put key value");
			} else{
				kvKeyValuehasp.putKeyValue(comando[1], comando[2]);
				out.println("Ingresado correctamente");
			}
		} else if(comando[0].equals("get")){
			if(comando[1]==null || comando[1].equals("")){
				out.println("Debe insertar el key: get key");
			} else{
				out.println(kvKeyValuehasp.getKeyValue(comando[1].trim()));
			}
		} else if(comando[0].equals("del")){
			if(comando[1]==null || comando[1].equals("")){
				out.println("Debe insertar el key: del key");
			} else{
				kvKeyValuehasp.delKeyValue(comando[1].trim());
				out.println("Eliminado correctamente");
			}
		} else if(comando[0].equals("list")){
			Set<String> keys=kvKeyValuehasp.getListKeyValue();
			String key="";
                        for (String s : keys) {
				key=key.concat(s+ "  ");
                        }
                        out.println(key);
		} else if (comando[0].equals("help")){
		    out.println("==========================================");
		    out.println("Debe insertar un comando, help para ayuda");
		    out.println("Debe insertar un comando, help para ayuda");
		    out.println("==========================================");
                } else if (comando[0].equals("exit")){
		    break;
                } else{
                    out.println("Debe insertar un comando, help para ayuda");
                }
	    }
	    

	} catch (IOException e) {
	    e.printStackTrace();
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
