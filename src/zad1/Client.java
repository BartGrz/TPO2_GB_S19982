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
import lombok.Builder;
import lombok.Data;
import zad1.holder.ClientRequest;


import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Application   {

    private static ServerSocket serverSocketReceived;
    public static final String info = " [CLIENT] :";
    public static ClientRequest cr;
    private static String data;

    private static String word = null;
    private static String langCode = null;
    private static String dataReceived;


    public static void main(String[] args) throws  IOException, ClassNotFoundException, InterruptedException {
        Logger logger = Logger.getAnonymousLogger();
        InetAddress host = InetAddress.getLocalHost();
        int port = Integer.parseInt(args[0]);
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
                Thread.sleep(150);
            }

            socket = new Socket(host.getHostName(), port);

            oos = new ObjectOutputStream(socket.getOutputStream());


            cr = new ClientRequest(getWord(), getLangCode(), socket.getLocalPort());
            cr.setHost(host);
            oos.writeObject(cr);
            oos.close();

            if (socket.isBound()) {
                socket.close();

            }
//oczekuje na informacje zwrotna od serwera
            System.out.println(info + "waiting for data on port : " + cr.getPort());

                serverSocketReceived = new ServerSocket(cr.getPort());

                Socket socketReceived = serverSocketReceived.accept();

                ObjectInputStream oiss = new ObjectInputStream(socketReceived.getInputStream());

                data = (String) oiss.readObject();

                oiss.close();

                setDataReceived(data);

                Thread.sleep(150);


                if (socketReceived.isBound()) {
                    socketReceived.close();
            }

            setWord(null);
            setLangCode(null);
            setDataReceived(null);
        }

    }
        @Override
        public void start (Stage primaryStage)   {

            Pane pane = new Pane();
            ComboBox comboBox_langCode = new ComboBox();
            ObservableList<String> lanCodes = FXCollections.observableArrayList("EN","FR","DE","ES","FIN","NO");
            TextField textField = new TextField();
            Label label_translated = new Label("Tlumaczenie : ");
            Label label_Translatedword = new Label();
            Label label_word = new Label("Wprowadz slowo do przetlumaczenia:");
            Button button_confirm = new Button("OK");

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

        //    comboBox_langCode.setValue("EN");

            pane.getChildren().addAll(label_word, button_confirm, textField, comboBox_langCode,label_translated,label_Translatedword);
            Scene scene = new Scene(pane, 550, 200);
            primaryStage.setScene(scene);
            primaryStage.show();

            label_Translatedword.setText(getDataReceived());

            button_confirm.setOnAction(event -> {

                if(comboBox_langCode.getValue() ==null) {
                    langCode = "";
                }else {

                    word = textField.getText();
                    langCode = comboBox_langCode.getValue().toString().toLowerCase();
                }
                    setLangCode(langCode);
                    setWord(word);

                while(getDataReceived()==null) {
                    try { Thread.sleep(150);
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



