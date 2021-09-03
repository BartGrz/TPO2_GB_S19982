package zad1.languageServers;

import zad1.adapter.LanguageServerImplementation;

import java.io.IOException;

public class FinLanguageServer extends LanguageServer implements LanguageServerImplementation {

    @Override
    public String getWordFromDictionary(String wordToTranslate) throws IOException {
        return getWord(wordToTranslate, LanguagesPossible.FIN);
    }
}
