package zad1;

import zad1.loaders.TranslatingRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class LanguageServer {

    private static ServerSocket serverSocket;
    private static int port =53343;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

            InetAddress host = InetAddress.getLocalHost();
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            TranslatingRequest tr = (TranslatingRequest) ois.readObject();
            String received = "port" + tr.getPort() + " adres " + tr.getClientAdress().toString() + " wordTo :" + tr.getWordToTranslate();
            ois.close();
            System.out.println(received);


    }

}
