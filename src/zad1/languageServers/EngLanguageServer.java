package zad1.languageServers;


import zad1.adapter.LanguageServerImplementation;

import java.io.*;
import java.util.Properties;

public class EngLanguageServer extends LanguageServer implements LanguageServerImplementation {


    @Override
    public String getWordFromDictionary(String wordToTranslate) throws IOException {
        return getWord(wordToTranslate, LanguagesPossibele.EN);
    }
}
