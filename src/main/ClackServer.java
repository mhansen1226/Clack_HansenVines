package main;

import data.ClackData;

import java.util.Objects;


public class ClackServer {
    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;

    public ClackServer(int port) {
        this.port = port;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
    }

    public ClackServer() {
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

        // to avoid errors when data variables are null, returns true if null until it gets fixed in the future
        boolean data = true;
        if (dataToReceiveFromClient != null)
            data = data && dataToReceiveFromClient.equals(otherCS.dataToReceiveFromClient);
        if (dataToSendToClient != null)
            data = data && dataToSendToClient.equals(otherCS.dataToSendToClient);


        return port == otherCS.port &&
                closeConnection == otherCS.closeConnection &&
                data;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + port;
        result = 37*result + Objects.hash(closeConnection);

        // to avoid errors when data variables are null, will get fixed in future installments
        if (dataToSendToClient != null)
            result = 37*result + dataToSendToClient.hashCode();
        if (dataToReceiveFromClient != null)
            result = 37*result + dataToReceiveFromClient.hashCode();
        return result;
    }

    @Override
    public String toString() {
        // to avoid errors when data variables are null, will get fixed in future installments
        String data = "";
        if (dataToReceiveFromClient != null)
            data = "Data to send to client: " + dataToSendToClient.toString() + "\n";
        if (dataToSendToClient != null)
            data = data + "Data to receive from client: " + dataToReceiveFromClient.toString();

        return "Port: " + port + "\n" +
                "Close connection? " + closeConnection + "\n" +
                data;

    }
}
