package zad1;

import zad1.loaders.ClientRequest;
import zad1.loaders.Translated;
import zad1.loaders.TranslatingRequest;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {

    private static ServerSocket serverSocket;
    private static ServerSocket serverSocketLang;
    private static ServerSocket serverSocketReceived;
    private static int port = 9876;
    public static int newPort = 53343;
    private static final String info = " SERVER MAIN :";
    private static  Translated tran ;
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        InetAddress host = InetAddress.getLocalHost();

while (true) {
    System.out.println(info + "waiting for Client request");
    serverSocket = new ServerSocket(port);
    Socket socket = serverSocket.accept();

    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    ClientRequest cr = null;
    cr = (ClientRequest) ois.readObject();


    if (cr.getLanguageCode().equals("fr")) {
        System.out.println("data received on " + port + " port");

        Socket socketLang = new Socket(host.getHostName(), newPort);
        System.out.println(info + "data sended on " + newPort + " port");
        TranslatingRequest tr = new TranslatingRequest(newPort, cr.getWordToTranslate(), cr.getHost().getAddress());
        ObjectOutputStream oos = new ObjectOutputStream(socketLang.getOutputStream());
        oos.writeObject(tr);
        oos.close();
        ois.close();
    }
    break;
}

        while (true) {

            serverSocketReceived = new ServerSocket(5355);

            System.out.println(info + " awaiting data on " + 5355 + " port ");

            Socket socketReceived = serverSocketReceived.accept();
            if(socketReceived.isBound()) {
            ObjectInputStream oiss = new ObjectInputStream(socketReceived.getInputStream());
              tran = (Translated) oiss.readObject();

            System.out.println("I received from lang server " + tran.getTranslatedWord());

                oiss.close();
                break;
            }

        }

        while (true) {
            Socket socketReturnData = new Socket(host.getHostName(), 54926);
    System.out.println(info + " sendiung data to client on port : " + socketReturnData.getPort());
    ObjectOutputStream oos = new ObjectOutputStream(socketReturnData.getOutputStream());


    oos.writeObject(tran);
    oos.close();
    if(socketReturnData.isBound()) {
        break;
    }

}

    //    }
        //close the ServerSocket object;

    }


}
