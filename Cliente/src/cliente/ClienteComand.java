package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

public class ClienteComand  {
    public static int PUERTO ; //Puerto para la conexión
    public static String HOST = ""; //Host para la conexiónprotected String mensajeServidor; //Mensajes entrantes (recibidos) en el servidor
    protected ServerSocket ss; //Socket del servidor
    protected Socket cs; //Socket del cliente
    protected DataOutputStream salidaServidor, salidaCliente; //Flujo de datos de salida
    
    String[] comandos = {"GET", "SET", "DEL", "LIST", "EXIT"};
    String comandoscl = "";


    PrintWriter out = null;
    BufferedReader in = null;
    boolean verificaConexion = false;
    int cont = 0;
    boolean band = true;

    public void startClient(String comandos) //Inicia el cliente
    {
        this.comandoscl = comandos;
        this.startClient();
    }

    public void startClient() //Método para iniciar el cliente
    {
   
        String concat = "";
        long time_start, time_end;
        long heapsize = Runtime.getRuntime().totalMemory();
        // System.out.println("heapsize is::"+heapsize);
        try {
            out = new PrintWriter(cs.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(cs.getInputStream()));
            //Flujo de datos hacia el servidor
            //Se enviarán dos mensajes
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser = "";
            time_start = 0;
            while ((fromServer = in.readLine()) != null) {

                if (fromServer.startsWith("lista")) {
                    String[] lista;
                    String last, outputLine;
                    String arreglo;
                    arreglo = fromServer.substring(6);
                    arreglo = arreglo.replace("[", " ");
                    arreglo = arreglo.replace("]", " ");
                    lista = arreglo.split(",");
                    //last = in.readLine().split("[")[arreglo-1];
                    outputLine = "";
                    for (int i = 0; i < lista.length; i++) {
                        outputLine = outputLine + "\n" + lista[i];

                    }
                    //System.out.println(arreglo);
                    //System.out.println(outputLine);

                    fromServer = outputLine;

                }

                band = true;
                if (fromServer.startsWith("Linea")) {
                    fromServer = fromServer.substring(6);
                    if (cont == 0) {
                        concat = concat + "" + fromServer;
                        cont++;
                    } else {
                        concat = concat + "" + fromServer;
                        //System.out.println(fromServer);
                    }
                    band = false;

                } else {
                    time_end = System.currentTimeMillis();
                    float res = time_end - time_start;
                    System.out.println("Segundos: " + res / 1000);

                    time_start = System.currentTimeMillis();
                    System.out.println((char) 27 + "[31m" + "Server: " + fromServer);

                }
                if (fromServer.startsWith("Fin")) {
                    band = true;
                    System.out.println((char) 27 + "[31m" + concat);
                    concat = "";
                    cont = 0;
                }

                boolean validez = true;
                if (band == true) {
                    String[] comando = null;
                    do {

                        System.out.print((char) 27 + "[34m" + "Client: ");
                        if (!comandoscl.equals("")) {
                            out.println(comandoscl);
                            comandoscl = "";
                            break;
                        } else {

                            fromUser = stdIn.readLine();

                            time_start = System.currentTimeMillis();

                        }


                        if (fromUser != null) {
                            comando = validarComandos(fromUser);
                            if (comando != null) {
                                validez = true;
                                if (!comando[0].toLowerCase().equals("exit") && !comando[0].toLowerCase().equals("help")) {
               
                                    out.println(Arrays.toString(comando));

                                }
                                if (comando[0].toLowerCase().equals("help")) {
                                    validez = false;
                                }
                            } else {
                                validez = false;
                            }
                        }

                    } while (!validez);

                    if (comando[0].toLowerCase().equals("exit")) {
                        break;
                    }

                }
            }
            out.close();
            in.close();
            cs.close();//Fin de la conexión
        } catch (Exception e) {
            System.out.println("Fin de la ejecucion");
        }

    }

    private String[] validarComandos(String s) {
        String[] comando = s.split(" ");
        String cmd = "";
        try {
            //cmd = comando[0].toLowerCase();

            switch (cmd) {
                case "GET":
                    if (comando.length == 2) {
                        return comando;
                    } else {
                        System.out.println((char) 27 + "[34m" + "ERROR: El comando get recibe un parámetro de entrada");
                        return null;
                    }
                case "SET":
                    if (comando.length >= 3) {
                        comando = extraerDeComando(s);
                        if (comando != null) {
                            return comando;
                        } else {
                            System.out.println((char) 27 + "[34m" + "ERROR: El comando set no cumple con la estructura: set <key> <value>");
                            return null;
                        }
                    } else {
                        System.out.println((char) 27 + "[34m" + "ERROR: El comando set recibe dos parámetros de entrada");
                        return null;
                    }

                case "PUT":
                    if (comando.length >= 3) {
                        comando = extraerDeComando(s);
                        if (comando != null) {
                            return comando;
                        } else {
                            System.out.println((char) 27 + "[34m" + "ERROR: El comando put no cumple con la estructura: put <key> <value>");
                            return null;
                        }
                    } else {
                        System.out.println("(char)27 + \"[34m\"+ERROR: El comando put recibe dos parámetros de entrada");
                        return null;
                    }
                case "DEL":
                    if (comando.length == 2) {
                        return comando;
                    } else {
                        System.out.println("(char)27 + \"[34m\"+ERROR: El comando del recibe solo un parámetro de entrada");
                        return null;
                    }

                default:

                    System.out.println((char) 27 + "[34m" + "ERROR: El comando " + comando[0] + " no existe");

                    return null;
            }
        } catch (Exception e) {
            System.out.println((char) 27 + "[34m" + "ERROR: El comando ' ' no existe");
        }
        return null;
    }

    private static String[] extraerDeComando(String s) {
        ArrayList<String> cmdFinal = new ArrayList<>();
        String[] comando = s.split(" ");
        String clave = comando[1];
        String value = "";
        if (comando.length >= 3) {
            cmdFinal.add(comando[0]);
            if (clave.contains("\r") || clave.contains("\t")) {
                return null;
            } else {
                cmdFinal.add(clave);
            }
            for (int z = 2; z < comando.length; z++) {
                if (comando[z].contains("\r")) {
                    return null;
                } else {
                    if (z != 2) {
                        value = value + " " + comando[z];
                    } else {
                        value = value + comando[z];
                    }
                }
            }

        } else {
            return null;
        }
        String[] cmd = new String[]{cmdFinal.get(0), cmdFinal.get(1), value};
        return cmd;
    }
}
