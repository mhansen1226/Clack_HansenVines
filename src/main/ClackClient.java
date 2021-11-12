package main;


import data.ClackData;
import data.FileClackData;
import data.MessageClackData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;


/**
 * Parent class that is used to represent the client user.
 *
 * @author Matt Vines
 */
public class ClackClient {

    private static final int CONSTANT_DEFAULT_PORT = 7000;

    private String key;
    private String userName;
    private String hostName;
    private int port;
    private boolean closeConnection;
    private ClackData dataToSendToServer;
    private ClackData dataToReceiveFromServer;
    private Scanner inFromStd;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    /**
     * constructor creates an instance based on username hostname and port number
     * closed connection set to false
     *
     * @param userName the client username
     * @param hostName the host name
     * @param port     port number
     */

    public ClackClient(String userName, String hostName, int port) throws IllegalArgumentException {
        if (userName == null)
            throw new IllegalArgumentException("userName cannot be null");
        if (hostName == null )
            throw new IllegalArgumentException("hostName cannot be null");
        if (port < 1024)
            throw new IllegalArgumentException("port cannot be less than 1024");

        this.userName = userName;
        this.hostName = hostName;
        this.port = port;
        this.closeConnection = false;
        this.dataToReceiveFromServer = null;
        this.dataToSendToServer = null;
        this.inFromServer = null;
        this.outToServer = null;
    }

    /**
     * Construction based on a username and host name given
     * Port is set to defualt = 7000
     * Closed connection set to false
     *
     * @param userName set to username
     * @param hostName set to host name
     */
    public ClackClient(String userName, String hostName) throws IllegalArgumentException {
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
        this(userName, "localhost");
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
     * Main method
     *
     * @param args optional argument to pass to client in the form "username@hostnam:portnumber"
     *             can be truncated
     */
    public static void main(String[] args) {

        ClackClient client;
        String username = null;
        String hostName = null;
        String sPort = null;
        int port = 0;
        if (args.length > 0) {
            String input = args[0];

            int idx1 = input.indexOf('@');
            int idx2 = input.indexOf(':');

            if(idx1 != -1) {
                username = input.substring(0, idx1);
                if (idx2 != -1) {
                    hostName = input.substring(idx1 + 1, idx2);
                    sPort = input.substring(idx2 + 1);
                    try {
                        port = Integer.parseInt(sPort);
                    } catch (NumberFormatException nfe) {
                        System.err.println(nfe.getMessage());
                    }
                } else
                    hostName = input.substring(idx1 + 1);
            } else
                username = input;
        }

        if (sPort != null)
            client = new ClackClient(username, hostName, port);
        else if (hostName != null)
            client = new ClackClient(username, hostName);
        else if (username != null)
            client = new ClackClient(username);
        else
            client= new ClackClient();

        client.start();
    }


    /**
     * Calls readClientData and printData in a loop until DONE is passes from System.in
     */
    public void start() {
        try {
            Socket skt = new Socket(hostName, port);

            outToServer = new ObjectOutputStream(skt.getOutputStream());
            inFromServer = new ObjectInputStream(skt.getInputStream());

            inFromStd = new Scanner(System.in);

            Thread listener = new Thread( new ClientSideServerListener(this) );
            listener.start();

            while (!closeConnection) {
                readClientData();
                sendData();
            }

            outToServer.close();
            inFromServer.close();
            skt.close();

        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }



    /**
     * Read data passed from Standard.in
     */
    public void readClientData() {
        dataToSendToServer = null; // clear data
        String input = inFromStd.next();
        switch (input) {
            case "DONE":
                closeConnection = true;
                break;
            case "SENDFILE":
                dataToSendToServer = new FileClackData(userName, inFromStd.next(), ClackData.CONSTANT_SENDFILE);
                try {
                    ((FileClackData) dataToSendToServer).readFileContents();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "LISTUSERS":

                break;
            default:
                dataToSendToServer = new MessageClackData(userName, input + inFromStd.nextLine(), ClackData.CONSTANT_SENDMESSAGE);
                break;
        }

    }
    /**
     * Method to send data.
     */
    public void sendData() {
        try {
            outToServer.writeObject(dataToSendToServer);
            outToServer.flush();
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     *Method to receive data. Will be implemented later
     */
    public void receiveData() {
        try {
            dataToReceiveFromServer = (ClackData) inFromServer.readObject();
        } catch (IOException | ClassNotFoundException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * Method to print data.
     */
    public void printData() {
        System.out.println(dataToReceiveFromServer.getUsername() + ":");
        System.out.println("\t" + dataToReceiveFromServer.getData());
    }

    /**
     * A get method for the username
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
     * A get method for the closeConnection condition
     * @return returns closeConnerction
     */
    public boolean getCloseConnection() {
        return closeConnection;
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
