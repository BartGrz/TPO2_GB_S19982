package zad1;

import zad1.loaders.ClientRequest;
import zad1.loaders.Translated;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.Scanner;

public class Client {

    private static ServerSocket serverSocketReceived;
    public static ServerSocket serverSocket_receivedNoData;
    ;
    public static final String info = "CLIENT : ";
    public static ClientRequest cr;
    private static String data;

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Scanner scanner = new Scanner(System.in);

//wysyla objekt na serwer main
        while (true) {

            socket = new Socket(host.getHostName(), 9876);

            oos = new ObjectOutputStream(socket.getOutputStream());

            String clientMes = scanner.next();
            String lCode = scanner.next();

            cr = new ClientRequest(clientMes, lCode, socket.getLocalPort(), host);
            oos.writeObject(cr);
            oos.close();
            if (socket.isBound()) {
                socket.close();
                // break;

            }

//oczekuje na informacje zwrotna od serwera glownego

            try {
                serverSocketReceived = new ServerSocket(cr.getPort());
            } catch (BindException e) {
                System.out.println(info + " ERROR ,EXC BIND");
            }
            Socket socketReceived = serverSocketReceived.accept();
            //   if (socketReceived.isBound()) {
            ObjectInputStream oiss = new ObjectInputStream(socketReceived.getInputStream());
            // Translated translated = (Translated) oiss.readObject();
            data = (String) oiss.readObject();
            oiss.close();
            System.out.println(" Data received from main server: " + data);

            if (socketReceived.isBound()) {
                socketReceived.close();

            }
        }
    }
}



