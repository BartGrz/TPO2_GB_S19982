package zad1.adapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public interface LanguageServerImplementation {
    static enum LanguagesPossibele {
        DE("de"), ES("es"), FR("fr"), FIN("fin"),EN("eng");
        public String lang;

        LanguagesPossibele(String lang) {
            this.lang = lang;
        }
        public String getLang() {
            return this.lang;
        }
    }
    default String getWord(String wordToBeTranslated, LanguagesPossibele language) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/dictionary_" + language.getLang() + ".properties"));
        String translatedWord = properties.getProperty(wordToBeTranslated);
        return translatedWord;
    }

}
