package zad1.languageServers;

import zad1.adapter.LanguageServerImplementation;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class FinLanguageServer extends LanguageServer implements LanguageServerImplementation {

    @Override
    public String getWordFromDictionary(String wordToTranslate) throws IOException {
        return getWord(wordToTranslate,LanguagesPossible.FIN);
    }

}
