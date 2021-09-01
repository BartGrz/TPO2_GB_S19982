package zad1.holder;

import java.io.Serializable;
import java.net.InetAddress;



public class ClientRequest implements Serializable {

    private final String wordToTranslate;
    private final String languageCode;
    private final int port;
    private  InetAddress host ;



    public ClientRequest(String wordToTranslate, String languageCode, int port) {
        this.wordToTranslate = wordToTranslate;
        this.languageCode = languageCode;
        this.port = port;

    }

    public String getWordToTranslate() {
        return wordToTranslate;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getHost() {
        return host;
    }

    public void setHost(InetAddress host) {
        this.host = host;
    }
}
