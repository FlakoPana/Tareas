import java.net.*;
import java.io.*;
public class ServidorWeb {
	public static String getContenidoHTML(String host) throws IOException {
	    URL url = new URL(host);
	    URLConnection uc = url.openConnection();
	    uc.connect();
	    //Creamos el objeto con el que vamos a leer
	    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
	    String inputLine = ".";
	    String contenido = "";
	      
	    while ((inputLine = in.readLine()) != null) {
	        contenido += inputLine + "\n";
	    }
	    in.close();
	    return contenido;
	}
        public static void CnxServer2 (String recurso, PrintWriter salida_navegador) {
            //Ahora se comportara como un cliente
            
            	System.out.println(" Antes del nuevo socket");
               // Socket ser_privado= new Socket ("148.226.81.117", 5100);// Se hace la conexion al servidor
                //Socket ser_privado= new Socket ("127.0.0.1", 5100);// Se hace la conexion al servidor
            //	Socket ser_privado= new Socket ("192.168.11.50", 5100);// Se hace la conexion al servidor
       		  	System.out.println(" Entro aqui");
                String cadena;
                //para enviar el server privado
               // PrintWriter sal_servidor1 = new PrintWriter(ser_privado.getOutputStream(),true);
                //Para recuperar la respuesta del server
              //  DataInputStream en_servidor1 = new DataInputStream(ser_privado.getInputStream());
              //  sal_servidor1.println(recurso);//Escribo al server
                /*while (!(cadena = en_servidor1.readUTF()).equals(null)){ //imprimir respuesta del servidor
          		  System.out.println(" El servidor privado me responde = "+cadena);
          		  salida_navegador.println(cadena);
          		  salida_navegador.flush();
          	  	}*/
               //ser_privado.close();//Cierro objetos
              //  sal_servidor1.close();
               // en_servidor1.close();
             //catch (IOException e) {//Error
               // System.out.println("Error en conexion " + e);
            }
            
        
                
    public static void main(String args[])
    {
        ServerSocket s,s2;
        System.out.println("Servidor web 1 iniciado en el puerto 5000");
        try {
             // Crea el socket del servidor inicial
             s = new ServerSocket(5000);
	     s2 = new ServerSocket(5100);
        } catch ( Exception e ) {
            System.out.println("Error: " + e );
            return;
        }
        System.out.println("Esperando Conexiones");
        while ( true ) {
            try {
                // espera por una conexion
                Socket cli_navegador = s.accept();//acepta la conexion del navegador
                // se acepta la conexion
                Socket cli_serUV = s2.accept();//acepta la conexion del navegador
                System.out.println("Conexion, enviando datos.");
                //Lee la peticion del navegador
                BufferedReader en_navegador = new BufferedReader( new InputStreamReader(cli_navegador.getInputStream()) );
                //Para mandarle respuesta al navegador
                PrintWriter sal_navegador = new PrintWriter(cli_navegador.getOutputStream());
                //BufferedReader en_Ser_UV_navegador = new BufferedReader( new InputStreamReader(cli_serUV.getInputStream()) );
                //Para mandarle respuesta al navegador
                DataInputStream en_Ser_UV_navegador = new DataInputStream(cli_serUV.getInputStream());
              //  PrintWriter sal_Ser_UV_navegador = new PrintWriter(cli_serUV.getOutputStream());
                DataOutputStream sal_Ser_UV_navegador = new DataOutputStream(cli_serUV.getOutputStream());
                // leer los datos enviados,
                // para de leer hasta que lee el fin de linea, es decir la linea en blanco
                // la linea en blanco es la senal de fin de las cabeceras HTTP
                String linea=".";
                String host = "";
                String cadena = "";
                do {
                    linea = en_navegador.readLine();//Recupera lo que envio el cliente
                    System.out.println("El navegador envia: " + linea);
                    if  (linea.length()>7) {
                    	if ((linea.substring(0, 3)).equals("GET") ) {
                   
                             host = linea.substring(4, linea.length()-10);
                         //    CnxServer2(host, sal_navegador);
                             System.out.println("Entro al if del GET ::: ::: ::: " + host );
                             sal_Ser_UV_navegador.writeUTF(host);
                           //  sal_servidor1.writeUTF("Hola mundo :)
                             cadena = en_Ser_UV_navegador.readUTF();
                      		  sal_navegador.println(cadena);
                      		  sal_navegador.flush();
                    	}
                    }
                }while ( !linea.equals("") );
                String linea2 = en_Ser_UV_navegador.readUTF();
                System.out.println("Escribio esto: " + linea2);
                cli_navegador.close();// Se cierra la conexion remota
                en_navegador.close();// Se cierra la conexion remota
                sal_navegador.close();// Se cierra la conexion remota
            } catch ( Exception e ) {
                System.out.println("Error: " + e );
            }
        }
    }
}