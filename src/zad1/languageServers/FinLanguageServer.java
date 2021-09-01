package zad1.languageServers;

import zad1.adapter.LanguageServerImplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FinLanguageServer extends LanguageServer implements LanguageServerImplementation {

    @Override
    public String getWordFromDictionary(String wordToTranslate) throws IOException {
        return getWord(wordToTranslate, LanguagesPossibele.FIN);
    }
}
