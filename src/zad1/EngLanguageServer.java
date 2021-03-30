package zad1;

import zad1.loaders.TranslatingRequest;

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
