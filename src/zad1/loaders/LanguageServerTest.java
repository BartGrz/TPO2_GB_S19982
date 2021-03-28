package zad1.loaders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public abstract class LanguageServerTest implements LanguageServerTemplate {
    private  ServerSocket serverSocket;
    private  int port = 53343;
    private  int portSeend = 5355;
    private final String info = " SERVER LANG :";
    static TranslatingRequest tr = null;

    @Override
    public void start(int port,String langCode) throws IOException, ClassNotFoundException {
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
        properties.load(new FileInputStream("src/dictionary_"+langCode +".properties"));
        String translatedWord =  properties.getProperty(tr.getWordToTranslate());


        //  tr.setWordToTranslate("i changed the word + chekc diff " + String.valueOf(Math.random() + 1));
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
