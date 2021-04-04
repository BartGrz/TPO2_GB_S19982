package zad1.languageServers;


import zad1.holder.TranslatingRequest;

import java.io.*;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class LanguageServer implements LanguageServerTemplate {
    private ServerSocket serverSocket;
    private final String info = " SERVER LANG : ";
    static TranslatingRequest tr = null;
    String translatedWord;

    @Override
    public void start(int port ) throws IOException, ClassNotFoundException {
        InetAddress host = InetAddress.getLocalHost();

        System.out.println(info + " awaiting data on " + port + " port ");
        try {
            serverSocket = new ServerSocket(port);


            // oczekuje na info od serwera glownego
            Socket socket = serverSocket.accept();
            if (serverSocket.isBound()) {
                System.out.println(info + "data received on " + port + " port");
            }

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            tr = (TranslatingRequest) ois.readObject();


            if(notFoundInDictionary()) {
             translatedWord = "not found in dictionary";

            }else {

            translatedWord = getWordFromDictionary(tr.getWordToTranslate());
            }

            ois.close();
        } catch (BindException e) {

        }

        System.out.println(info + " sending data to " + tr.getPort() + " port ");

        //wysyla luamczenie do klienta
        while (true) {

            Socket returnInfo = new Socket(host.getHostName(), tr.getPort());
            ObjectOutputStream oos = new ObjectOutputStream(returnInfo.getOutputStream());
            oos.writeObject(translatedWord);
            oos.close();
            returnInfo.close();
            serverSocket.close();

            System.out.println(info + " : data send, shuting down");

            if (returnInfo.isBound()) {

                break;
            }
        }
    }


    /**
     * @Method template
     *         Properties properties = new Properties();
     *         properties.load(new FileInputStream("src/dictionary_languageCode( example fr).properties"));
     *         String translatedWord =  properties.getProperty(wordToTranslate);
     * @return translatedWord
     */
    abstract public String getWordFromDictionary(String wordToTranslate) throws IOException;


    private boolean notFoundInDictionary() throws IOException {

        if (getWordFromDictionary(tr.getWordToTranslate())==null) {

            return true;
        }

            return false;
    }
}