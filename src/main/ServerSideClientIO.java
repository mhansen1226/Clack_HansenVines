package main;

import data.ClackData;
import data.MessageClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSideClientIO implements Runnable {

    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private ClackServer server;
    private Socket clientSocket;

    public ServerSideClientIO(ClackServer server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.closeConnection = false;
        this.dataToReceiveFromClient = null;
        this.dataToSendToClient = null;
        this.inFromClient = null;
        this.outToClient = null;
    }

    @Override
    public void run() {
        try {
            inFromClient = new ObjectInputStream(clientSocket.getInputStream());
            outToClient = new ObjectOutputStream(clientSocket.getOutputStream());

            while(!closeConnection)
            {
                receiveData();
                checkData();
                server.broadcast(dataToReceiveFromClient);
            }
            inFromClient.close();
            outToClient.close();
            clientSocket.close();

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }


    }

    public void sendData() {
        try {
            outToClient.writeObject(dataToSendToClient);
            outToClient.flush();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    public void receiveData() {
        try {
            dataToReceiveFromClient = (ClackData) inFromClient.readObject();
        } catch (IOException | ClassNotFoundException ioe) {
            System.err.println(ioe.getMessage());
        }
        if (dataToReceiveFromClient == null)
            closeConnection = true;
            server.remove(this);
    }

    public void checkData() {
        if (dataToReceiveFromClient != null && dataToReceiveFromClient.getData().equals("LISTUSERS")) {
            server.addUser(dataToReceiveFromClient.getUsername());
        }
    }

    public void setDataToSendToClient(ClackData data) {
        this.dataToSendToClient = data;
    }
}
