
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;


/**
 *
 * @author Evelyn Gonzalez
 */
public class ContenedorServer {

    public LinkedList<Socket> contenido;
    private boolean contenedovacio = Boolean.TRUE;

    public ContenedorServer() {
        this.contenido = new LinkedList<>();
    }

    public synchronized Socket get() throws IOException {
        Socket tem;
        while (contenedovacio) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("Contenedor: Error en get -> " + e.getMessage());
            }
        }
        if (!contenido.isEmpty()) {
           notify();
            System.out.println("Contenedor saco de la  cola ");
            return contenido.poll();
        }
        else {
            contenedovacio = Boolean.TRUE;
        return null;}
    }

    public synchronized void put(Socket value) throws IOException {

        contenido.add(value);
        contenedovacio = Boolean.FALSE;
        System.out.println("productor puso en cola ");
        notifyAll();
    }
}
