package zad1.languageServers;

import zad1.holder.TranslatingRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EsLanguageServer extends LanguageServer{

    /**
     * utworzyc  Properties properties = new Properties();
     * properties.load(new FileInputStream("src/dictionary_kodjezyka(np fr).properties"));
     * String translatedWord =  properties.getProperty(tr.getWordToTranslate());
     */
    @Override
    public String getWordFromDictionary(TranslatingRequest tr) throws IOException {
        Properties properties = new Properties();
      properties.load(new FileInputStream("src/dictionary_es.properties"));
      String translatedWord =  properties.getProperty(tr.getWordToTranslate());

     return translatedWord;
    }
}
