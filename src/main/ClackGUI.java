package main;

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
    private String stylesheet;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stylesheet = getClass().getResource("ClackStyle.css").toExternalForm();
        client = ClackClient.buildClient(getParameters().getRaw().toArray(new String[0]));
        client.start();

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

        root.getChildren().addAll(userListDisplay, chat, inputBar);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(stylesheet);

        sendButton.setOnAction(event -> {
            chat.appendText(messageBar.getText() + '\n');
            messageBar.clear();
        });

        mediaButton.setOnAction(event -> {
            try {
                File file = new FileChooser().showOpenDialog(primaryStage);

                BufferedReader br = new BufferedReader(new FileReader(file));
                String line, out = "";

                while ((line = br.readLine()) != null) {
                    out += line + '\n';
                }
                chat.setText(chat.getText() + out);
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
