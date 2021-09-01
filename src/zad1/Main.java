package zad1;

import zad1.holder.AvailablePort;

import java.io.IOException;

public class Main   {

    public static void main(String[] args) {

        int port = new AvailablePort().findFreePort();
        String portAval = String.valueOf(port);

        Thread mainServer = new Thread(() -> {
            try {
                new MainServer().main(new String[]{portAval});
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        mainServer.start();

        Thread client = new Thread(() -> {
            try {
                new Client().main(new String[]{portAval});
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        if(mainServer.isAlive()) {
            client.start();

        }
    }

}
