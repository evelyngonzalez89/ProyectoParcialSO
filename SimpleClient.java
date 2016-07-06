import java.net.*;
import java.io.*;

public class SimpleClient
{
  public static void main(String[] args) throws IOException
  {
   int c,con;
   int puerto;
   String host;

  
	   String avisos []={"key1","key2","key3 ","key4"};	
	    try
	      {
	       host = args[0];
	       puerto = Integer.parseInt(args[1]);
	       System.out.println("port "+puerto);
	       System.out.println("Host "+host);
	       Socket s1 = new Socket(host,puerto);
	       InputStream is = s1.getInputStream();
	       
	       DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
		
		dos.writeInt(4);
		dos.writeInt(4);

		con=0;
		DataInputStream dis = new DataInputStream(is);
		  System.out.println(dis.readUTF());

	       while((c=dis.read())!=-1)
		{
		System.out.println(avisos[con]);
		System.out.println(Math.round((double)c));
		con++;
		}
	   
	     s1.close();
	     dis.close();
	     }
	      catch (ConnectException connExc){
		   System.err.println("OCURRIO UN ERROR");
	       } catch (IOException e){
		       
		       }
	    }
	
}   
