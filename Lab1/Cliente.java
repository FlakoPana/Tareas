import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente{
    public static void main(String[] args) {
      String servidor = "127.0.0.1";
      int puerto = 5000;
      System.out.println("¿Selecciona la opción de deseas saber?: ");
      System.out.println("1: Nombre del equipo ");
      System.out.println("2: Direccion MAC ");
      System.out.println("3: Unidades de Disco ");
      System.out.println("4: Fecha y hora ");
      System.out.println("5: Usuario Actual ");
      Scanner entrada = new Scanner(System.in);
	  String lectura = entrada.next();
      
      try{
        Socket socket= new Socket (servidor,puerto);
        BufferedReader in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
        out.println(lectura);
	String line = "";
	
	while  (((line = in.readLine()) != null) ){
		System.out.println(line);
		break;
	
	}
	socket.close();
      } catch (IOException e)
      {
       		System.out.println("Error en conexión!!!");
      }
      
    }
}