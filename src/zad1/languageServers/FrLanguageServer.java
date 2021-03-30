package zad1.languageServers;

import zad1.holder.TranslatingRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FrLanguageServer extends LanguageServer  {


    /**
     * @param tr
     * @return translatedWord
     *
     * @Method template
     * Properties properties = new Properties();
     * properties.load(new FileInputStream("src/dictionary_languageCode( example fr).properties"));
     * String translatedWord =  properties.getProperty(tr.getWordToTranslate());
     */
    @Override
    public String getWordFromDictionary(TranslatingRequest tr) throws IOException {

        Properties properties = new Properties();
      properties.load(new FileInputStream("src/dictionary_fr.properties"));
      String translatedWord =  properties.getProperty(tr.getWordToTranslate());

        return translatedWord;
    }
}
