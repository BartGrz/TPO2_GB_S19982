package zad1;

import zad1.loaders.ClientRequest;
import zad1.loaders.Translated;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static ServerSocket serverSocketReceived;


    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {

            socket = new Socket(host.getHostName(), 9876);

            oos = new ObjectOutputStream(socket.getOutputStream());

            String clientMes = scanner.next();
            String lCode = scanner.next();

            ClientRequest cr = new ClientRequest(clientMes, lCode, 9876, host);

            oos.writeObject(cr);


            oos.close();


            //  Thread.sleep(100);

    serverSocketReceived = new ServerSocket(54926);
    Socket socketReceived = serverSocketReceived.accept();
    //   if (socketReceived.isBound()) {
    System.out.println(" data received from main server");
    ObjectInputStream oiss = new ObjectInputStream(socketReceived.getInputStream());
    Translated translated = (Translated) oiss.readObject();
    System.out.println("I received" + translated);
    //  }
    if (socketReceived.isBound()) {
        break;
    }
}

        }

}


