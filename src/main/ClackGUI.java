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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.*;

/**
 * Class that handles the GUI for the project
 */
public class ClackGUI extends Application {
    private ClackClient client;
    MediaPlayer media = null;
    Image image = null;

    /**
     * Start method to start the GUI when the client joins the server
     * @param primaryStage
     * @throws Exception
     */
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

        MediaView mediaView = new MediaView(media);
        mediaView.setFitWidth(300);

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        VBox mediaBar = new VBox(mediaView, imageView);

        HBox chatArea = new HBox(chat, mediaBar);

        client.start(chat, userList, mediaView, imageView);

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
            switch (file.getName().substring(file.getName().length()-3)) {
                case "mp4":
                case "MP4":
                    client.setDataToSendToServer(new MediaClackData(client.getUserName(), file.getPath(), file.getName(), ClackData.CONSTANT_SENDVIDEO));
                    break;
                case "png":
                case "PNG":
                case "JPG":
                case "jpg":
                    client.setDataToSendToServer(new MediaClackData(client.getUserName(), file.getPath(), file.getName(), ClackData.CONSTANT_SENDPICTURE));
                    break;
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

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
