/*import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[50];
            byte[] sendData = new byte[50];

            while(true)
               {
                //  receiveData = null;
                 // sendData = null;
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  sentence = sentence + " : " + java.net.InetAddress.getLocalHost().getCanonicalHostName();
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String capitalizedSentence = sentence.toUpperCase();
                  sendData = capitalizedSentence.getBytes();
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
                  receiveData = new byte[50];
                  sendData = new byte[50];
               }
      }
}*/
import java.io.*;
import java.net.*;

class UDPServer
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[50];
         byte[] sendData2 = new byte[1024];
            while(true)
               {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
              //String nuevaCad = new String("IP");
                  System.out.println("RECEIVED: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
              //nuevaCad = " IP: " + InetAddress.getLocalHost().getCanonicalHostName();
              //sentence = nuevaCad;
              //sentence = "ejemplito uno dos tres";
                  String capitalizedSentence = /*sentence.toUpperCase()+ */InetAddress.getLocalHost().getCanonicalHostName();
              System.out.println("envio: " + capitalizedSentence);
                  sendData = capitalizedSentence.getBytes();
              //sendData2 = nuevaCad.getBytes();
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
              //DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, IPAddress, port);
                  serverSocket.send(sendPacket);
              //serverSocket.send(sendPacket2);
               }
      }
}