package zad1;

import zad1.loaders.ClientRequest;
import zad1.loaders.TranslatingRequest;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {

    private static ServerSocket serverSocket;
    private static ServerSocket serverSocketLang;
    private static int port = 9876;
    public static int newPort = 53343;


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();





            System.out.println("waiting for Client request");
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ClientRequest cr = null;
            cr = (ClientRequest) ois.readObject();


            if (cr.getLanguageCode().equals("fr")) {



                Socket socketLang = new Socket(host.getHostName(), newPort);
                 newPort = socketLang.getLocalPort();
                System.out.println(newPort);
                String portString = String.valueOf(newPort);


                TranslatingRequest tr = new TranslatingRequest(newPort, cr.getWordToTranslate(), cr.getHost().getAddress());
                ObjectOutputStream oos = new ObjectOutputStream(socketLang.getOutputStream());
                oos.writeObject(tr);
                System.out.println("I received values" + cr.toString());
                System.out.println("I am passing data : " + tr.toString());
               oos.close();
                ois.close();


                Thread.sleep(1000);

            }
            //close the ServerSocket object;



    }
}
