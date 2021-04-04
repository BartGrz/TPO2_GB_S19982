package zad1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
