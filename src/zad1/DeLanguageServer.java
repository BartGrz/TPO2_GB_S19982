package zad1;

import zad1.loaders.TranslatingRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DeLanguageServer extends LanguageServer {


    @Override
    public String getWordFromDictionary(TranslatingRequest tr) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src/dictionary_de.properties"));
        String translatedWord =  properties.getProperty(tr.getWordToTranslate());

       // if(properties.getProperty(tr.getWordToTranslate())==null) {
       //     return " word not found in dictionary";
      //  }

        return translatedWord;
    }
}
