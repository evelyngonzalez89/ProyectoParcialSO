# ProyectoParcialSO
TÍTULO:

Key­value store de un solo nodo

INTEGRANTES:

    Evelyn Gonzalez
    Karen Ponce

DESCRIPCIÓN:

Realizaremos un Sistema de almacenamiento de clave-valor es un tipo de base NoSQL usa una matriz asociativa en nuestro caso para el almacenamiento en memoria usamos Hashmap como el modelo de datos fundamentales, donde una clave es asociada con un único valor de una colección.

METODOLOGÍA:

En el desrrollo de una aplicación cliente­servidor utilizando comunicaciones con sockets. El protocolo de conexión TCP fue implementado con las clases Socket y ServerSocket, las cuales son librerías de Java.

Key-value store en su diseño e implementación que permitirá a uno o más clientes conectarse con el key-value store en memoria. Se utilizó una base de datos NoSQL para el almacenamiento de las claves con sus respectivos valores.

Se implementó un modelo múltiples productores múltiples consumidores en donde se almacenan las peticiones de los clientes en una cola de peticiones porque manejan peticiones concurrentes que se van despachando en orden. Cuando un cliente se conecta por primera vez al servidor, el productor ubica la petición de este cliente en la cola de petición para que luego el consumidor la atienda creando un hilo hacia el socket de conexión.

La base NoSQL se implementó con el uso de hashmap para almacenar las claves con sus respectivos valores y contiene métodos. A su vez, se encuentra particionado los valores de las claves cuando se presente el caso que este valor sea muy grande con el fin de realizar consultas de manera eficiente.

Los hilos que sirven tanto para el productor y consumidor fueron implementados mediante el ExecutorService donde coloca los hilos en un ‘pool’ para limitar el número de hilos de conexión ya que esto ocasionaría que nuestro proceso ocupe demasiada memoria en un caso que requiera demasiadas conexiones.
Para poder ejecutar el Cliente:

EJECUCIÓN EN LINUX

    
1.-En la Carpeta JarCliente_Servidor se encuentrar los .jar tanto del cliente como del Servidor. Una vez descargados, para el 
CLIENTE
   Por medio del terminal nos situamos en el directorio del .jar descargado y para ejecutarlo se ingresa: 
      (Directorio destino....)/java -jar Cliente.jar IP PUERTO
      
   Para ejecutar el cliente y que lee los comandos desde un archivo.txt es el mismo procedimiento de abrir la terminal en el directorio donde se encuentra el.jar pero e comando que colocaremos será:
      cat nombrearchivo.txt| java  -jar Cliente.jar IP PUERTO
 
MAKE

Luego de descargar el proyecto completo, en una carpeta nueva (por comodidad) extraer el archivo "cliente" de la ruta: ProyectoParcialSO/tree/master/Cliente/src/cliente Hacer lo mismo para la carpeta "servidor" y pegarla en la misma carpeta nueva.
Abrir el terminal, y acceder a la misma ruta donde se ha creado la nueva carpeta. Primero entrar al directorio cliente y ejecutar el comando: make De igual manera acceder al directorio servidor, y ejecutar el mismo comando.
Para ejecutar el servidor: Acceder desde un terminal a la ruta de la carpeta nueva > servidor. 
        (Directorio destino....)/java -jar Cliente.jar #PUERTO
      
Para ejecutar el cliente y que lee los comandos desde un archivo.txt es el mismo procedimiento de abrir la terminal en el directorio donde se encuentra el.jar pero e comando que colocaremos será:
      cat nombrearchivo.txt| java  -jar Cliente.jar #IP #PUERTO Repetir el mismo paso desde otro servidor para ejecutar el cliente, con el comando: java -jar Cliente.jar #IP #PUERTO Nota: El host puede ser el localhost, y #PORT puede ser cualquier número de puerto.
    
 NOTA: EL archivo de comandos que envía el cliente debe estar en el mismo directorio del .jar Cliente
 Los print de pantalla se encuentran el documento que se presenta dentro de la carpeta Documentación
 
INSTALL YCSB

Para nuestro proyecto procedemos a la instalación de los paquetes a continuación:

a.  Maven: que es una herramienta de software para la gestión y construcción de proyectos.
    Ruta: https://www.mkyong.com/maven/how-to-install-maven-in-ubuntu/
    
b.  YCSB: Gestor de las pruebas automatizadas, un servicio para realizar pruebas de rendimiento a distintas base de datos. 
    Ruta: https://github.com/brianfrankcooper/YCSB/wiki/Getting-Started
    
c.  REDIS: Motor de base de datos en memoria, basado en el almacenamiento en tablas de hashes (clave/valor) pero que opcionalmente puede ser usada como una base de datos durable o persistente.
    Ruta: https://www.digitalocean.com/community/tutorials/how-to-configure-a-redis-cluster-on-ubuntu-14-04
    
d. Descargado el Repositorio YCSB como nuestro procedemos en consola ejecutar:

    cd /home/steven/Escritorio/ProyectoFinalSO/ProyectoParcialSO/YCSB-master
    mvn clean package
    mvn -pl com.yahoo.ycsb:mongodb-binding -am clean package
    
