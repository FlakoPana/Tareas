import java.net.*;
import java.util.Date;
import java.io.*;
import java.lang.management.*;
public class Server{

	public static void main(String args[]){
		try {

			int puerto = 5000;

			ServerSocket s = new ServerSocket(puerto);
			String comandoSalir = "Exit";
			String entrada = "";
			String aux="";
			String opcion = "0";
			System.out.println("Servidor iniciado en el puerto " + puerto + "...");
			while(true){
				Socket s1 = s.accept();        
				System.out.println("Aceptando conexion...");
				PrintWriter out = new PrintWriter(s1.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));

				while ((entrada = in.readLine()) != null) {					
				   // System.out.println(entrada);
				    opcion = entrada;
				    if (opcion.equals("1")){
				    	System.out.println("mandaste un " + opcion);
				    	aux= java.net.InetAddress.getLocalHost().getCanonicalHostName();
				    	out.println(aux);
				    }
				    else if(opcion.equals("2")){
				    	InetAddress ip;
				    System.out.println("mandaste un " + opcion);
			    	try {
			    		ip = InetAddress.getLocalHost();			        
			    		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			        	byte[] mac = network.getHardwareAddress();
			        	//System.out.print("Current MAC address : ");
			        	StringBuilder sb = new StringBuilder();
			        	for (int i = 0; i < mac.length; i++) {
			            sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
			        }
			        out.println("MAC del Equipo: "+sb.toString());
			    } catch (UnknownHostException e) {
			        e.printStackTrace();
			    } catch (SocketException e){
			        e.printStackTrace();
			    }
			    	}
				    else if(opcion.equals("3")){
				    	File[] roots = File.listRoots();
				    	 System.out.println("mandaste un " + opcion);
				    	 float aux2 = 0;
					    for (File root : roots) {
					    	/*aux = aux + ("\n" + " Letra de Unidad: " + root.getAbsolutePath())+ "\n" + "Tamaño total del disco: " +
					    			 (((root.getTotalSpace())/1024)/1024)/1024 + "GB " + "\n" +
					    			"Espacio Utilizado " + (((root.getUsableSpace())/1024)/1024)/1024 + " GB" +
					    			"\n" + "Espacio Disponible " + (((root.getFreeSpace())/1024)/1024)/1024 + "GB" ;
					     */
					  
					      aux2 = (((root.getTotalSpace())/1024)/1024)/1024;
					      out.println("Tamaño total del disco: "+ root.getAbsolutePath()  + " es de: " + aux2 + " GB" + "\n");
					
				    } //System.out.println(aux);
					  //  out.print(aux);
					    }else if(opcion.equals("4")){
					    	System.out.println("mandaste un " + opcion);
					    	Date fecha1 = new Date ();
					    	out.println(fecha1.toString());
					    }else if(opcion.equals("5")){
					    	System.out.println("mandaste un " + opcion);
					    	   String usuario=System.getProperty("user.name");
					    	   System.out.println(usuario);
					    	   out.println(usuario);
					    	 } else{
					    	System.out.println(entrada);
					    	return;}
			
				    if (entrada.trim().equals(comandoSalir))
				        return;
				} 
				s1.close();   
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}


}