package main;


import data.ClackData;

import java.util.Objects;

/**
 * Parent class that is used to represent the client user.
 *
 * @author Matt Vines
 */
public class ClackClient {

    private static final int CONSTANT_DEFAULT_PORT = 7000;

    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    ClackData dataToSendToServer;
    ClackData dataToReceiveFromServer;

    /**
     * constructor creates an instance based on username hostname and port number
     * closed connection set to false
     *
     * @param userName the client username
     * @param hostName the host name
     * @param port     port number
     */

    public ClackClient(String userName, String hostName, int port) {
        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.closeConnection = false;
        this.dataToReceiveFromServer = null;
        this.dataToSendToServer = null;
    }

    /**
     * Construction based on a username and host name given
     * Port is set to defualt = 7000
     * Closed connection set to false
     *
     * @param userName set to username
     * @param hostName set to host name
     */
    public ClackClient(String userName, String hostName) {
        this(userName, hostName, CONSTANT_DEFAULT_PORT);
    }

    /**
     * Will set host name to "localHost"
     * port is set to 7000
     * closed connection set to false
     *
     * @param userName set to username
     */
    public ClackClient(String userName) {
        this(userName, "localHost");
    }

    /**
     * Default constructor with no inputs
     * Will set name to "Anon"
     * Will set host name to "localHost"
     * closed connection set to false
     * port is set to 7000
     */
    public ClackClient() {
        this("Anon");
    }

    /**
     * Start method. Will be implemented later
     */
    public void start() {
    }



    /**
     * read client data method. Will be implemented later
     */
    public void readClientData() {
    }
    /**
     * Method to send data. Will be implemented later
     */
    public void sendData() {
    }

    /**
     *Method to receive data. Will be implemented later
     */
    public void receiveData() {
    }

    /**
     * Method to print data. Will be implemented later
     */
    public void printData() {
    }

    /**
     * A get method for the user name
     * @return returns username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * A get method foe the host name
     * @return returns host name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * A get method for the port number
     * @return returns port
     */
    public int getPort() {
        return port;
    }

    /**
     * Overrides the Object.equals() method
     * @param other Takes in an Clack Client object and compares if two objects are equal
     * @return Returns a boolean variable that determines weather the two objects are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof ClackClient)) return false;
        ClackClient otherCC = (ClackClient) other;
        // to avoid errors when data variables are null, returns true if null until it gets fixed in the future
        boolean data = true;
        if (dataToReceiveFromServer != null)
            data = data && dataToReceiveFromServer.equals(otherCC.dataToReceiveFromServer);
        if (dataToSendToServer != null)
            data = data && dataToSendToServer.equals(otherCC.dataToSendToServer);
        return port == otherCC.port && closeConnection == otherCC.closeConnection &&
                userName.equals(otherCC.userName) && hostName.equals(otherCC.hostName) &&
                data;
    }

    /**
     * Overrides the Object.hashcode()
     * @return Returns an integer specific to the ClackClient object and the
     * data contained within them
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + port;
        result = 37 * result + userName.hashCode();
        result = 37 * result + hostName.hashCode();
        result = 37 * result + Objects.hash(closeConnection);
        // to avoid errors when data variables are null, will get fixed in future installments
        if (dataToSendToServer != null)
            result = 37 * result + dataToSendToServer.hashCode();
        if (dataToReceiveFromServer != null)
            result = 37 * result + dataToReceiveFromServer.hashCode();
        return result;
    }

    /**
     * Overrides the Object.toString() method
     * @return Returns the data contained in the Clack Client object in a string
     */
    @Override
    public String toString() {
        // to avoid errors when data variables are null, will get fixed in future installments
        String data = "";
        if (dataToReceiveFromServer != null)
            data = "Data to send to server: " + dataToSendToServer.toString() + "\n";
        if (dataToSendToServer != null)
            data = data + "Data to receive from server: " + dataToReceiveFromServer.toString();

        return "port: " + port + "\n" +
                "UserName: " + userName + "\n" +
                "HostName: " + hostName + "\n" +
                "Close connection? " + closeConnection + "\n" + data;
    }
}
