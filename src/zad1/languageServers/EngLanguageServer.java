package zad1.languageServers;



import java.io.*;
import java.util.Properties;

public class EngLanguageServer extends LanguageServer{


    @Override
    public String getWordFromDictionary(String wordToTranslate) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/dictionary_eng.properties"));
        String translatedWord =  properties.getProperty(wordToTranslate);

        return translatedWord;
    }
}
