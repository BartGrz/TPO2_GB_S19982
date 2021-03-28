package zad1;

import zad1.loaders.ClientRequest;
import zad1.loaders.Translated;
import zad1.loaders.TranslatingRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {

    private static ServerSocket serverSocket;
    private static ServerSocket serverSocketReceived;
    private static int port = 9876;
    public static int newPort = 53343;
    private static final String info = " SERVER MAIN :";
    private static Translated tran;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InetAddress host = InetAddress.getLocalHost();


        while (true) {

            System.out.println(info + "waiting for Client request on port " + port);
            try {
                serverSocket = new ServerSocket(port);
            } catch (BindException e) {

            }


            Socket socket = serverSocket.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ClientRequest cr = null;
            cr = (ClientRequest) ois.readObject();
            System.out.println(info + " data received on " + port + " port");
//w zaleznosci od kodu kraju (w przyszlosci) powinien odpalic sie serwer na oczekujacy danych na danym porcie

            if (cr.getLanguageCode().equals("eng")) {
                final String code = cr.getLanguageCode();
                Thread serverThread = new Thread(() -> {
                    try {
                        new EngLanguageServer().start(newPort,code);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                serverThread.start();

            }else if (cr.getLanguageCode().equals("de")) {


                final String code = cr.getLanguageCode();

                Thread serverThread = new Thread(() -> {
                    try {
                        new DeLanguageServer().start(newPort, code);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                serverThread.start();

            } else {

                Socket socket_returnData = new Socket(host.getHostName(), cr.getPort());
                System.out.println(info + "I do not recognize language code , sending data to client  " + socket_returnData.getPort());
                ObjectOutputStream objos = new ObjectOutputStream(socket_returnData.getOutputStream());
                objos.writeObject("no data");
                objos.close();

                if (socket_returnData.isBound()) {
                }
            }

            Socket socketLang = new Socket(host.getHostName(), newPort);

            System.out.println(info + "data sended on " + newPort + " port");
            TranslatingRequest tr = new TranslatingRequest(cr.getPort(), cr.getWordToTranslate(), cr.getHost().getAddress());
            ObjectOutputStream oos = new ObjectOutputStream(socketLang.getOutputStream());
            oos.writeObject(tr);
            oos.close();
            ois.close();


        }

    }
}
    /*
//czekanie na objekt od lang server

                while (true) {
                    try {

                        serverSocketReceived = new ServerSocket(5355);
                    } catch (BindException e) {
                        System.out.println(info + " ERROR, BIND EXC ");
                    }
                    System.out.println(info + " awaiting data on " + 5355 + " port ");

                    Socket socketReceived = serverSocketReceived.accept();
                    if (socketReceived.isBound()) {
                        ObjectInputStream oiss = new ObjectInputStream(socketReceived.getInputStream());
                        tran = (Translated) oiss.readObject();

                        System.out.println(info + " I received from lang server " + tran.getTranslatedWord());

                        oiss.close();

                    }
//wysylanie zwroconego z lang server objektu

                    Socket socketReturnData = new Socket(host.getHostName(), cr.getPort());
                    System.out.println(info + " sendiung data to client on port : " + socketReturnData.getPort());
                    oos = new ObjectOutputStream(socketReturnData.getOutputStream());

                    oos.writeObject(tran.getTranslatedWord());
                    oos.close();
                    socketReturnData.close();
                    System.out.println(info + " data send ");

                    if (socketReturnData.isBound()) {
                        serverSocket.close();
                        break;

                    }


                }


             */
