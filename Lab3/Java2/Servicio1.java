/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roci
 */
import java.io.*;
import java.net.*;

class Servicio1
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[7168];
   
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String capitalizedSentence = /*java.net.*/InetAddress.getLocalHost().getHostAddress();
		  System.out.println("envio: " + capitalizedSentence);
                  sendData = capitalizedSentence.getBytes();
                  
                  String str_proceso = null;
	          String admin = System.getenv("windir") + "\\system32\\" + "tasklist /svc";
                  //byte [] bufer = admin.getBytes();
	          Process proceso = Runtime.getRuntime().exec(admin);
	          BufferedReader input = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
	          while((str_proceso = input.readLine()) != null){
                  byte [] bufer = str_proceso.getBytes();
                  System.out.println("envio: " + str_proceso);
                  sendData  = str_proceso.getBytes();
                  }
		   //System.out.println(str_proceso);
	         //}
             
            
            
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
       
             }
      }
}
