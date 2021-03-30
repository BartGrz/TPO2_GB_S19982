package zad1.languageServers;

import zad1.holder.TranslatingRequest;

import java.io.*;
import java.util.Properties;

public class EngLanguageServer extends LanguageServer{


    @Override
    public String getWordFromDictionary(TranslatingRequest tr) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/dictionary_eng.properties"));
        String translatedWord =  properties.getProperty(tr.getWordToTranslate());

        return translatedWord;
    }
}
