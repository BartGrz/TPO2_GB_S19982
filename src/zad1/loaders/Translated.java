package zad1.loaders;

import java.io.Serializable;

public class Translated implements Serializable {

    private String translatedWord;
    private int port;

    public Translated(String translatedWord, int port) {
        this.translatedWord = translatedWord;
        this.port = port;
    }

    public String getTranslatedWord() {
        return translatedWord;
    }

    public void setTranslatedWord(String translatedWord) {
        this.translatedWord = translatedWord;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
