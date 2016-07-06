package edu;
import java.io.*;
import java.net.*;

public class KnockKnockClient {
  public static void main(String[] args) throws IOException {

    Socket kkSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
	String host=args[0];
    int port;
	port = Integer.parseInt(args[1]); 

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

    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    String fromServer;
    String fromUser;

    while ((fromServer = in.readLine()) != null) {
      System.out.println("Server: " + fromServer);
      if (fromServer.equals("Bye."))
        break;
        
      fromUser = stdIn.readLine();
      if (fromUser != null) {
        System.out.println("Client: " + fromUser);
        out.println(fromUser);
      }
    }

    out.close();
    in.close();
    stdIn.close();
    kkSocket.close();
 }
}
