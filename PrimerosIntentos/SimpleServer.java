import java.io.*;
import java.net.*;
public class SimpleServer
{
    public static void main(String args[]) 
    {
        ServerSocket s;
	int puerto;
        // Register service to port 5432 or anyother number
        try
        {
	    puerto = Integer.parseInt(args[0]);
            s = new ServerSocket(puerto);
        } 
        catch (IOException e) 
        {
            s = null; 
            e.printStackTrace();
        }
 
        while (true) 
        {
            try
            {
                 // Wait here and listen for a connection
                 Socket s1 = s.accept();
                 // Get output stream associated with the socket
                 OutputStream s1out = s1.getOutputStream();
                 DataOutputStream dos = new DataOutputStream(s1out);
                 // Send your string!
                 dos.writeUTF("Hello Net World!");
                 // Close the connection, but not the server socket
                 dos.close();
                 s1.close();
            } 
            catch (IOException e) 
            { 
                e.printStackTrace();
            }
        } //end of while
    }//end of main
}//end of class
