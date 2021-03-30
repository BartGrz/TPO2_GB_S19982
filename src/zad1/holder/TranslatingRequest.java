package zad1.holder;

import java.io.Serializable;
import java.util.Arrays;

public class TranslatingRequest implements Serializable {

    private int port;
    private String wordToTranslate;
    private byte[] clientAdress;

    public TranslatingRequest(int port, String wordToTranslate, byte[] clientAdress) {
        this.port = port;
        this.wordToTranslate = wordToTranslate;
        this.clientAdress = clientAdress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWordToTranslate() {
        return wordToTranslate;
    }

    public void setWordToTranslate(String wordToTranslate) {
        this.wordToTranslate = wordToTranslate;
    }

    public byte[] getClientAdress() {
        return clientAdress;
    }

    public void setClientAdress(byte[] clientAdress) {
        this.clientAdress = clientAdress;
    }

    @Override
    public String toString() {

        return "TranslatingRequest{" +
                "port=" + port +
                ", wordToTranslate='" + wordToTranslate + '\'' +
                ", clientAdress=" + Arrays.toString(clientAdress) +
                '}';
    }
}
