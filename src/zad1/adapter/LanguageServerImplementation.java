package zad1.adapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

public interface LanguageServerImplementation extends FileHandler, FileCreator {
    String destination = "src/";
    String prefix = "dictionary_";
    String extension = ".properties";

    enum LanguagesPossible {
        DE("de"), ES("es"), FR("fr"), FIN("fin"), EN("eng"), NO("no"), SE("se");
        public String lang;

        LanguagesPossible(String lang) {
            this.lang = lang;
        }

        public String getLang() {
            return this.lang;
        }
    }

    default String getWord(String wordToBeTranslated, LanguagesPossible language) throws IOException {
        String file_name = prefix + language.getLang()+extension;
        validateAndCreateFile(file_name);
        Properties properties = new Properties();
        properties.load(new FileInputStream(destination + prefix + language.getLang() + extension));
        String translatedWord = properties.getProperty(wordToBeTranslated);
        return translatedWord;
    }


}
