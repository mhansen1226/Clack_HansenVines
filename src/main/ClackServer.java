package main;

import data.ClackData;

import java.util.Objects;


public class ClackServer {
    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;

    ClackServer(int port) {
        this.port = port;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
    }

    ClackServer() {
        this(7000);
    }

//    public void start() {}
//    public void receiveData();
//    public void sendData();

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof ClackServer)) return false;
        ClackServer otherCS = (ClackServer)other;
        return port == otherCS.port &&
                closeConnection == otherCS.closeConnection &&
                dataToReceiveFromClient.equals(otherCS.dataToReceiveFromClient) &&
                dataToSendToClient.equals(otherCS.dataToSendToClient);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + port;
        result = 37*result + Objects.hash(closeConnection);
        result = 37*result + dataToSendToClient.hashCode();
        result = 37*result + dataToReceiveFromClient.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Port: " + port + "\n" +
                "Close connection? " + closeConnection + "\n" +
                "Data to send to client: " + dataToSendToClient.toString() + "\n" +
                "Data to receive from client: " + dataToReceiveFromClient.toString();
    }
}
