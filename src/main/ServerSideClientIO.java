package main;

import data.ClackData;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import data.MediaClackData;
import data.MessageClackData;
import javafx.scene.control.Label;

/**
 * Class that handles IO with an individual client on the side of the server. Used to multithread multiple clients in
 * the same server.
 */
public class ServerSideClientIO implements Runnable {

    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;
    private String username;

    /**
     * Constructer that creates an instance of ServerSideClientIO based on user input for server and clientSocket.
     * closeConnection is set to false and all other instance variables are null.
     *
     * @param server       the server that created the instance
     * @param clientSocket the socket connected to the client
     */
    public ServerSideClientIO(ClackServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
        this.username = null;
    }

    /**
     * Implements run() from the Runnable class. Opens input and output streams from th client socket and reads and
     * sends data from and to the client.
     */
    @Override
    public void run() {
        try {
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());

            receiveData(); // get username
            server.listUsers();

            while (!closeConnection) {
                receiveData();
                if (dataToReceiveFromClient != null) {
                    server.broadcast(dataToReceiveFromClient);
                }
            }
            inFromClient.close();
            outToClient.close();
            clientSocket.close();

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }


    }

    /**
     * Sends dataToSendToClient to the client through the outToClient stream. Clears dataToSendToClient after.
     */
    public void sendData() {
        try {
            outToClient.writeObject(dataToSendToClient);
            outToClient.flush();
            dataToSendToClient = null;
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * Receives data from the client through the inFromClient stream and stores it in dataToRecieveFromClient. If
     * nothing is received the connection is closed and the instance of this class is removed from the
     * server.serverSideClientIOList.
     */
    public void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();

            switch (dataToReceiveFromClient.getType()) {
                case ClackData.CONSTANT_USERNAME:
                    username = dataToReceiveFromClient.getUsername();
                    dataToReceiveFromClient = null;
                    break;
                case ClackData.CONSTANT_LOGOUT:
                    closeConnection = true;
                    dataToReceiveFromClient = null;
                    server.remove(this);
                    break;
                case ClackData.CONSTANT_SENDVIDEO:
                    System.out.println(((MediaClackData) dataToReceiveFromClient).fileContents.length);

            }
        } catch (IOException | ClassNotFoundException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * dataToSendToClient mutator
     *
     * @param data data to send to client
     */
    public void setDataToSendToClient(ClackData data) {
        this.dataToSendToClient = data;
    }

    /**
     * Username accessor
     *
     * @return client username
     */
    public String getUsername() {
        return username;
    }
}
