package zad1;

import zad1.loaders.Translated;
import zad1.loaders.TranslatingRequest;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class LanguageServer {

    private static ServerSocket serverSocket;
    private static int port = 53343;
    private static int portSeend = 5355;
    private static final String info = " SERVER LANG :";
    static TranslatingRequest tr = null;

    public void startServer() throws IOException, ClassNotFoundException {

        InetAddress host = InetAddress.getLocalHost();

        System.out.println(info + " awaiting data on " + port + " port ");
        serverSocket = new ServerSocket(port);
        // oczekuje na info od serwera glownego
        Socket socket = serverSocket.accept();
        if (serverSocket.isBound()) {
            System.out.println(info + "data received on " + port + " port");
        }

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        tr = (TranslatingRequest) ois.readObject();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/dictionary.properties"));
       String translatedWord =  properties.getProperty(tr.getWordToTranslate());

        tr.setWordToTranslate(translatedWord);
        ois.close();


        Translated translated = new Translated(tr.getWordToTranslate(), port);
        System.out.println(info + " sendinfg data to " + 5355 + " port ");


        while (true) {
            Socket returnInfo = new Socket(host.getHostName(), 5355);

            ObjectOutputStream oos = new ObjectOutputStream(returnInfo.getOutputStream());

            oos.writeObject(translated);
            oos.close();
            returnInfo.close();
            serverSocket.close();


            System.out.println(info + " : data send");

            if (returnInfo.isBound()) {

                break;
            }
        }
    }
}
