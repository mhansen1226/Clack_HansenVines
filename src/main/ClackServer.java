package main;

import data.ClackData;

import java.util.Objects;

/**
 * This class is a blueprint for a ClackServer object that contains information about the port number that clients
 * connect to
 *
 * @author Matt Hansen
 */
public class ClackServer {

    private int port;
    private boolean closeConnection;
    private ClackData dataToReceiveFromClient;
    private ClackData dataToSendToClient;

    /**
     * Constructor that creates an instance of ClackServer with a user-provided port number.
     * For now, dataToReceiveFromClient and dataToSendToClient are set to null
     *
     * @param port the port number
     */
    public ClackServer(int port) {
        this.port = port;
        dataToReceiveFromClient = null;
        dataToSendToClient = null;
    }
    /**
     * Default constructor that creates an instance of ClackServer with a port number of 7000.
     */
    public ClackServer() {
        this(7000);
    }

    /**
     * Start server
     */
    public void start() {}

    /**
     * Receive data from client
     */
    public void receiveData() {}

    /**
     * Send data to client
     */
    public void sendData() {}

    /**
     * Port number accessor
     *
     * @return port number
     */
    public int getPort() {
        return port;
    }

    /**
     * Overrides Object.equals()
     * Temporarily avoids the problem of using null data variables
     *
     * @param other any object
     *
     * @return boolean value that returns true iff the object is an instance of ClackServer with an equivalent port
     *         number, closeConnection, dataToReceiveFromClient and dataToSendToClient.
     */
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

    /**
     * Overrides Object.hashCode()
     * Temporarily avoids the problem of using null data variables
     *
     * @return returns an integer specific to ClackServer objects according to the data contained within them
     */
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

    /**
     * Overrides Object.toString()
     * Temporarily avoids the problem of using null data variables
     *
     * @return String displaying all data contained within the instance of FileClackData
     */

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
