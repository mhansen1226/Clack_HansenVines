package main;

import data.ClackData;
import data.FileClackData;
import data.MessageClackData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;


public class ClackGUI extends Application {
    private ClackClient client;

    @Override
    public void start(Stage primaryStage) throws Exception {

        String stylesheet = getClass().getResource("ClackStyle.css").toExternalForm();
        client = ClackClient.buildClient(getParameters().getRaw().toArray(new String[0]));

        VBox root = new VBox();

        Button mediaButton = new Button("+");
        mediaButton.setId("mediaButton");
        Button sendButton = new Button("SEND");
        sendButton.setId("sendButton");
        TextArea messageBar = new TextArea();
        messageBar.setPromptText("Enter Message...");
        messageBar.setId("messageBar");

        HBox inputBar = new HBox(mediaButton, messageBar, sendButton);

        Label userLable = new Label("Online: ");
        Label userList = new Label();

        HBox userListDisplay = new HBox(userLable, userList);

        TextArea chat = new TextArea();
        chat.setId("chat");
        chat.setEditable(false);
        chat.setWrapText(true);

        client.start(chat);

        root.getChildren().addAll(userListDisplay, chat, inputBar);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(stylesheet);

        sendButton.setOnAction(event -> {
            client.setDataToSendToServer(new MessageClackData(client.getUserName(), messageBar.getText(), ClackData.CONSTANT_SENDMESSAGE));
            client.sendData();
            messageBar.clear();
        });

        mediaButton.setOnAction(event -> {
            File file = new FileChooser().showOpenDialog(primaryStage);
            client.setDataToSendToServer(new FileClackData(client.getUserName(),file.getPath(),ClackData.CONSTANT_SENDFILE));
            client.sendData();
        });

        primaryStage.setOnCloseRequest(event -> {
            client.setDataToSendToServer(new MessageClackData(ClackData.CONSTANT_LOGOUT));
            client.sendData();
        });

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Clack");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
