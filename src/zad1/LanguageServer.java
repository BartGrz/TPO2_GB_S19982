package zad1;

import zad1.loaders.Translated;
import zad1.loaders.TranslatingRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class LanguageServer {

    private static ServerSocket serverSocket;
    private static int port =53343;
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
            tr.setWordToTranslate("i changed the word + chekc diff " + String.valueOf(Math.random()+1));
            ois.close();


            Translated translated = new Translated(tr.getWordToTranslate(), port);
            System.out.println(info + " sendinfg data to " + 5355 + " port ");



            Socket returnInfo = new Socket(host.getHostName(), 5355);

            ObjectOutputStream oos = new ObjectOutputStream(returnInfo.getOutputStream());

            oos.writeObject(translated);
            oos.close( );
            returnInfo.close();
            serverSocket.close();


            System.out.println(info + " : data sended");

            if(returnInfo.isBound()) {

                returnInfo.close();
            }




        }
}
