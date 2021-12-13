package main;

import data.ClackData;
import data.FileClackData;
import data.MediaClackData;
import data.MessageClackData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.*;


public class ClackGUI extends Application {
    private ClackClient client;
    MediaPlayer media = null;

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

        Label userLabel = new Label("Online: ");
        TextField userList = new TextField();
        userList.setEditable(false);

        HBox userListDisplay = new HBox(userLabel, userList);

        TextArea chat = new TextArea();
        chat.setId("chat");
        chat.setEditable(false);
        chat.setWrapText(true);

        HBox chatArea = new HBox(chat);

        MediaView mediaView = new MediaView(media);
        chatArea.getChildren().add(mediaView);
        mediaView.setFitWidth(300);

        client.start(chat, userList, mediaView);

        root.getChildren().addAll(userListDisplay, chatArea, inputBar);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(stylesheet);

        sendButton.setOnAction(event -> {
            client.setDataToSendToServer(new MessageClackData(client.getUserName(), messageBar.getText(), ClackData.CONSTANT_SENDMESSAGE));
            client.sendData();
            messageBar.clear();
        });

        mediaButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Text Files", "*.txt"),
                    new ExtensionFilter("Image Files", "*.png", "*.jpg"),
                    new ExtensionFilter("Media Files", "*.mp4"));
            File file = fileChooser.showOpenDialog(primaryStage);
            switch (file.getName().split("\\.")[1]) {
                case "mp4":
                    client.setDataToSendToServer(new MediaClackData(client.getUserName(), file.getName(), ClackData.CONSTANT_SENDMEDIA));
                    break;
                case "png":
                case "jpg":

                default:
                    client.setDataToSendToServer(new FileClackData(client.getUserName(), file.getPath(), ClackData.CONSTANT_SENDFILE));
            }
            client.sendData();
        });

        primaryStage.setOnCloseRequest(event -> {
            client.setDataToSendToServer(new MessageClackData(ClackData.CONSTANT_LOGOUT));
            client.sendData();
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Clack");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
