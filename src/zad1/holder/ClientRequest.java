package zad1.holder;

import java.io.Serializable;
import java.net.InetAddress;

public class ClientRequest implements Serializable {

    private String wordToTranslate;
    private String languageCode;
    private int port;
   InetAddress host ;


    public ClientRequest(String wordToTranslate, String languageCode, int port, InetAddress host) {
        this.wordToTranslate = wordToTranslate;
        this.languageCode = languageCode;
        this.port = port;
        this.host =host;
    }

    public String getWordToTranslate() {
        return wordToTranslate;
    }

    public void setWordToTranslate(String wordToTranslate) {
        this.wordToTranslate = wordToTranslate;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getHost() {
        return host;
    }

    public void setHost(InetAddress host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "ClientRequest{" +
                "wordToTranslate='" + wordToTranslate + '\'' +
                ", languageCode='" + languageCode + '\'' +
                ", port=" + port +
                ", host=" + host +
                '}';
    }
}
