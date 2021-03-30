package zad1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main   {

    public static void main(String[] args) {



        Thread mainServer = new Thread(() -> {
            try {
                new MainServer().main(new String[]{""});
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        mainServer.start();

        Thread client = new Thread(() -> {
            try {
                new Client().main(new String[]{""});
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
