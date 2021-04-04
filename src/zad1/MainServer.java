package zad1;

import zad1.holder.AvailablePort;
import zad1.holder.ClientRequest;
import zad1.holder.TranslatingRequest;
import zad1.languageServers.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class MainServer {

    private static ServerSocket serverSocket;
    private static int port;
    public static int newPort = AvailablePort.findFreePort();
    private static final String info = " [SERVER MAIN] :";

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        InetAddress host = InetAddress.getLocalHost();
        port = Integer.parseInt(args[0]);
        serverSocket = new ServerSocket(port);

        while (true) {

            System.out.println(info + "waiting for Client request on port " + port);

            Socket socket = serverSocket.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ClientRequest cr = null;
            cr = (ClientRequest) ois.readObject();
            ois.close();
            System.out.println(info + " data received on " + port + " port");


            if (cr.getLanguageCode().equals("en")) {

                Thread serverThread = new Thread(() -> {
                    try {

                        new EngLanguageServer().start(newPort);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                serverThread.start();

                sendToLangServ(cr,host.getHostName());

            }else if (cr.getLanguageCode().equals("de")) {


                Thread serverThread = new Thread(() -> {
                    try {
                        new DeLanguageServer().start(newPort);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                serverThread.start();

                sendToLangServ(cr, host.getHostName());

            }  else if (cr.getLanguageCode().equals("es")) {
                Thread serverThread = new Thread(() -> {
                    try {
                        new EsLanguageServer().start(newPort);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                serverThread.start();

                sendToLangServ(cr, host.getHostName());

            }else if(cr.getLanguageCode().equals("fr")) {
                Thread serverThread = new Thread(() -> {
                    try {
                        new FrLanguageServer().start(newPort);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                serverThread.start();
                sendToLangServ(cr, host.getHostName());
            }else if (cr.getLanguageCode().equals("fin")) {


                Thread serverThread = new Thread(() -> {
                    try {
                        new FinLanguageServer().start(newPort);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                serverThread.start();

                sendToLangServ(cr, host.getHostName());

            } else {

                Socket socket_returnData = new Socket(host.getHostName(), cr.getPort());
                System.out.println(info + "I do not recognize language code , sending data to client  " + socket_returnData.getPort());
                ObjectOutputStream objos = new ObjectOutputStream(socket_returnData.getOutputStream());
                objos.writeObject("no data");
                objos.close();

                if (socket_returnData.isBound()) {
                    socket_returnData.close();
                }
            }


        }

    }

    public static void sendToLangServ(ClientRequest cr, String hostName ) throws IOException {

        Socket socketLang = new Socket(hostName, newPort);
        System.out.println(info + "data sended on " + newPort + " port");
        TranslatingRequest tr = new TranslatingRequest(cr.getWordToTranslate(),cr.getHost().getAddress(),cr.getPort());
        ObjectOutputStream oos = new ObjectOutputStream(socketLang.getOutputStream());
        oos.writeObject(tr);
        oos.close();

    }
}
