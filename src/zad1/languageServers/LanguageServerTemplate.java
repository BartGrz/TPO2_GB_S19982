package zad1.languageServers;

import java.io.IOException;
import java.net.UnknownHostException;

public interface LanguageServerTemplate {


    /**
     * Need to implement whole server logic
     * @param port = on this port client will be listening
     *             after sending messeage server should shut down
     * @throws IOException
     * @throws ClassNotFoundException
     */
    void start(int port ) throws IOException, ClassNotFoundException;

}
