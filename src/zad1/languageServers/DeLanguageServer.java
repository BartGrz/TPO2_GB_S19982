package zad1.languageServers;

import zad1.holder.TranslatingRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DeLanguageServer extends LanguageServer {


    @Override
    public String getWordFromDictionary(String wordToTranslate) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/dictionary_de.properties"));
        String translatedWord =  properties.getProperty(wordToTranslate);


        return translatedWord;
    }
}
