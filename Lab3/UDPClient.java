import java.io.*;
import java.net.*;
import java.util.*;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {
   	  ArrayList<String> listaIPs = new ArrayList<String>();
      for (int i = 110; i > 0 ; i--){
      //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("255.255.255.255");
      byte[] sendData = new byte[50];
      byte[] receiveData = new byte[50];
      String sentence = "Prueba";//inFromUser.readLine() ; //+ java.net.InetAddress.getLocalHost().getCanonicalHostName();
      sendData = sentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
      clientSocket.send(sendPacket);
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      String modifiedSentence = new String(receivePacket.getData());
      //System.out.println("FROM SERVER:" + modifiedSentence);
      clientSocket.close();
      if (!listaIPs.contains(modifiedSentence)){
      	listaIPs.add(modifiedSentence);
      }

  	 }
 	 Iterator<String> nombreIterator = listaIPs.iterator();
	while(nombreIterator.hasNext()){
			String elemento = nombreIterator.next();
		System.out.print("El servicio esta activo en: " + elemento+"\n"  );
	}
     // clientSocket.close();
   }
} 