package zad1.languageServers;

import zad1.adapter.LanguageServerImplementation;
import zad1.holder.TranslatingRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DeLanguageServer extends LanguageServer implements LanguageServerImplementation {


    @Override
    public String getWordFromDictionary(String wordToTranslate) throws IOException {
        return getWord(wordToTranslate, LanguagesPossibele.DE);
    }

}
