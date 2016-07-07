/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Evelyn Gonzalez
 */
public class KVValidacionesCliente {

    private Socket socket = null;
    private static final int MAX_ARRAY_SIZE = 128;
    private static final int MAX_ARRAY_SIZE_VAL = 2048;

    public String validate(InputStreamReader input) throws IOException {
        BufferedReader stdIn = new BufferedReader(input);
        String inputLine, inputLineRet = null;
        out.println("Ingrese el comando ó 'help' para ayuda");
        inputLine = stdIn.readLine();
        if (inputLine == null) {
            out.println("Ingrese el comando ó 'help' para ayuda");
        } else if (inputLine != null) {
            String[] comando = inputLine.split("\\s+");//Valida mas de un espacio
            //out.println("VALIDA INGRESO COMANDOS");

            if (comando[0] == null || comando[0].equals("")) {
                out.println("ERROR: Debe insertar un comando, 'help' para ayuda");
            } else if (comando[0].toUpperCase().equals("SET")) {//Soporta mayusculas y minusculas
                if (comando[1] == null || comando[1].equals("") || comando[2] == null || comando[2].equals("")) {
                    out.println("ERROR: Debe insertar el key y el value: [set key value]");
                } else if (comando[1].getBytes("UTF-8").length > MAX_ARRAY_SIZE || comando[2].getBytes("UTF-8").length > MAX_ARRAY_SIZE_VAL) {
                    out.println("ERROR: El tamaño máximo de una clave debe ser de 128MB; el tamaño máximo\n"
                            + "de un valor es de 2GB");
                } else if (comando[1].contains("/n") || comando[1].contains("/t")) {
                    out.println("El [key] no debe contener [/n] saltos de linea, [/t] tabulaciones");
                } else if (comando[2].contains("/n") || comando[2].contains("/t")) {
                    out.println("El [Value] no debe contener [/n] saltos de linea, [/t] tabulaciones");
                } else {
                    //kvKeyValuehasp.putKeyValue(comando[1], comando[2]);
                    inputLineRet = inputLine;
                }
            } else if (comando[0].toUpperCase().equals("GET")) {
                if (comando[1] == null || comando[1].equals("")) {
                    out.println("ERROR: Debe insertar el key: [get key]");
                } else if (comando[1].getBytes("UTF-8").length > MAX_ARRAY_SIZE) {
                    out.println("ERROR: El tamaño máximo de una clave debe ser de 128MB.");
                } else if (comando[1].contains("/n") || comando[1].contains("/t")) {
                    out.println("El [key] no debe contener [/n] saltos de linea , [/t] tabulaciones");
                } else {
                    //kvKeyValuehasp.putKeyValue(comando[1], comando[2]);
                    inputLineRet = inputLine;
                }
            } else if (comando[0].toUpperCase().equals("DEL")) {//Soporta mayusculas y minusculas
                if (comando[1] == null || comando[1].equals("")) {
                    out.println("ERROR: Debe insertar el key: del key");
                } else if (comando[1].contains("/n") || comando[1].contains("/t")) {
                    out.println("El [key] no debe contener [/n] saltos de linea , [/t] tabulaciones");
                } else {
                    //kvKeyValuehasp.putKeyValue(comando[1], comando[2]);
                    inputLineRet = inputLine;
                }
            } else if (comando[0].toUpperCase().equals("LIST")) { //Soporta mayusculas y minusculas
                return inputLine;
            } else if (comando[0].toUpperCase().equals("HELP")) {//Soporta mayusculas y minusculas
                String nl = System.getProperty("line.separator");

                //Lanzamos el mensaje:
                JOptionPane.showMessageDialog(null, "AYUDA KEY VALUE STORE"
                        + nl + "get key ..."
                        + nl + "Almacena (en memoria) la clave, con el valor\n"
                        + "asociado. El valor puede contener cualquier\n"
                        + "caracter, incluso caracteres especiales, tabs\n"
                        + "y espaciones en blanco. /"
                        + nl + ""
                        + nl + "set key value ..."
                        + nl + "Almacena (en memoria) la clave, con el valor\n"
                        + "asociado. El valor puede contener cualquier\n"
                        + "caracter, incluso caracteres especiales, tabs\n"
                        + "y espaciones en blanco. "
                        + nl + ""
                        + nl + "del key  ..."
                        + nl + "Elimina la clave, con su valor asociado.."
                        + nl + ""
                        + nl + "list ..."
                        + nl + "Retorna la lista de todas las claves\n"
                        + "almacenadas. NO retorna los valores\n"
                        + "asociados a dichas claves."
                        + nl + ""
                        + nl + "exit  ..."
                        + nl + "Termina la conexión con el servidor y\n"
                        + "posteriormente, termina ejecución del\n"
                        + "programa cliente. OJO: Este NO es un\n"
                        + "comando soportado por el servidor; es un\n"
                        + "comando soportado por el programa cliente..");

                // out.println("Inserte un nuevo comando");
                inputLineRet = "error";
            } else if (comando[0].toUpperCase().equals("EXIT")) {
                out.println("Ha salido del programa [KEY/VALUE STORE]!!!!!");
                System.exit(0);
            } else {
                out.println("ERROR: Debe insertar un comando, help para ayuda");
                inputLineRet = "error";
            }

        }
        //stdIn.close();
        return inputLineRet;
    }
}
