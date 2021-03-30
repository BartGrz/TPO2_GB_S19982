package zad1;

import zad1.loaders.TranslatingRequest;

import java.io.IOException;

public class EsLanguageServer extends LanguageServer{

    /**
     * utworzyc  Properties properties = new Properties();
     * properties.load(new FileInputStream("src/dictionary_kodjezyka(np fr).properties"));
     * String translatedWord =  properties.getProperty(tr.getWordToTranslate());
     */
    @Override
    public String getWordFromDictionary(TranslatingRequest tr) throws IOException {
        return null;
    }
}
