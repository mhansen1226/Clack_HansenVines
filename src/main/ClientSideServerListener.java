package main;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Class that waits for data from the server and prints it to the client. Implements the runnable class.
 */
public class ClientSideServerListener implements Runnable {

    private ClackClient client;
    private TextArea chat;
    private Label userList;

    /**
     * Constructor that instantiates the client based on user input
     * @param client the client
     */
    public ClientSideServerListener(ClackClient client, TextArea chat, Label userList) {
        this.client = client;
        this.chat = chat;
        this.userList = userList;
    }

    /**
     * Implements the run method from the Runnable interface. Receives data from the server and prints it to the client.
     */
    @Override
    public void run() {
        while (!client.getCloseConnection()) {
            client.receiveData();
            client.printData(chat, userList);
        }
    }
}



