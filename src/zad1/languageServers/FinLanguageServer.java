package zad1.languageServers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FinLanguageServer extends LanguageServer{
    /**
     * @param wordToTranslate
     * @return translatedWord
     * @Method template
     * Properties properties = new Properties();
     * properties.load(new FileInputStream("src/dictionary_languageCode( example fr).properties"));
     * String translatedWord =  properties.getProperty(wordToTranslate);
     */
    @Override
    public String getWordFromDictionary(String wordToTranslate) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/dictionary_fin.properties"));
        String translatedWord =  properties.getProperty(wordToTranslate);

        return translatedWord;
    }
}
