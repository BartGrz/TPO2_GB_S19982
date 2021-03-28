package zad1.loaders;

import java.io.IOException;
import java.net.UnknownHostException;

public interface LanguageServerTemplate {

    void start(int port, String langCode) throws IOException, ClassNotFoundException;

}
