package zad1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import zad1.holder.ClientRequest;


import java.io.*;
import java.net.*;

public class Client extends Application   {

    private static ServerSocket serverSocketReceived;
    public static final String info = "CLIENT : ";
    public static ClientRequest cr;
    private static String data;

    private static String word = null;
    private static String langCode = null;
    private static String dataReceived;


    public static void main(String[] args) throws  IOException, ClassNotFoundException, InterruptedException {

        InetAddress host = InetAddress.getLocalHost();

        Thread guiThread = new Thread(() -> Application.launch(args));

        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        setLangCode(null);
        setWord(null);
        setDataReceived(null);
//wysyla objekt na serwer main
        while (true) {

            if (guiThread.isAlive()) {

            } else {
                  guiThread.start();
            }


            while (getWord()==null && getLangCode()==null) {
                Thread.sleep(500);
            }

            socket = new Socket(host.getHostName(), 9876);

            oos = new ObjectOutputStream(socket.getOutputStream());


            cr = new ClientRequest(getWord(), getLangCode(), socket.getLocalPort(), host);
            System.out.println(cr.toString());
            oos.writeObject(cr);
            oos.close();
            if (socket.isBound()) {
                socket.close();

            }
//oczekuje na informacje zwrotna od serwera

                try {
                    serverSocketReceived = new ServerSocket(cr.getPort());
                } catch (BindException e) {
                    System.out.println(info + " ERROR ,EXC BIND");
                }
                Socket socketReceived = serverSocketReceived.accept();

                ObjectInputStream oiss = new ObjectInputStream(socketReceived.getInputStream());

                data = (String) oiss.readObject();

                oiss.close();
                //  System.out.println(info +" Data received from main server: " + data);
                setDataReceived(data);


                Thread.sleep(250);


                if (socketReceived.isBound()) {
                    socketReceived.close();


            }

            setWord(null);
            setLangCode(null);

            setDataReceived(null);
        }

    }
   // }
        @Override
        public void start (Stage primaryStage) throws Exception {

            Pane pane = new Pane();
            ComboBox comboBox_langCode = new ComboBox();
            ObservableList<String> lanCodes = FXCollections.observableArrayList("ENG","FR","DE","ES");
            TextField textField = new TextField();
            TextField textField_langcode = new TextField();
            Label label_translated = new Label("Tlumaczenie : ");
            Label label_Translatedword = new Label();
            Label label_word = new Label("Wprowadz slowo do przetlumaczenia:");
            Button button_confirm = new Button("Confirm");

            comboBox_langCode.getItems().addAll(lanCodes);

            label_word.setLayoutX(20);
            label_word.setLayoutY(40);

            label_translated.setLayoutX(20);
            label_translated.setLayoutY(80);

            textField.setLayoutX(220);
            textField.setLayoutY(40);

            label_Translatedword.setLayoutX(220);
            label_Translatedword.setLayoutY(80);

            comboBox_langCode.setLayoutX(380);
            comboBox_langCode.setLayoutY(40);

            button_confirm.setLayoutX(460);
            button_confirm.setLayoutY(40);

            pane.getChildren().addAll(label_word, button_confirm, textField, comboBox_langCode,label_translated,label_Translatedword);
            Scene scene = new Scene(pane, 550, 200);
            primaryStage.setScene(scene);
            primaryStage.show();



            label_Translatedword.setText(getDataReceived());
            button_confirm.setOnAction(event -> {

                word = textField.getText();
                langCode = comboBox_langCode.getValue().toString().toLowerCase();

                setLangCode(langCode);
                setWord(word);

             //   System.out.println("word " + getWord() + " lang code " + getLangCode());
             //

                while(getDataReceived()==null) {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                label_Translatedword.setText(getDataReceived());

            });



        }


    public static String getWord() {
        return word;
    }

    public static String getLangCode() {
        return langCode;
    }

    public static void setWord(String word) {
        Client.word = word;
    }

    public static void setLangCode(String langCode) {
        Client.langCode = langCode;
    }

    public static String getDataReceived() {
        return dataReceived;
    }

    public static void setDataReceived(String dataReceived) {
        Client.dataReceived = dataReceived;
    }
}



