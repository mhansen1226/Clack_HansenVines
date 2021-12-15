package main;

import data.ClackData;
import data.MessageClackData;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Objects;


/**
 * This class is a blueprint for a ClackServer object that contains information about the port number that clients
 * connect to
 *
 * @author Matt Hansen
 */
public class ClackServer {

    private static final int CONSTANT_DEFAULT_PORT = 7000;

    private int port;
    private boolean closeConnection;
    private ArrayList<ServerSideClientIO> serverSideClientIOList;

    /**
     * Constructor that creates an instance of ClackServer with a user-provided port number.
     * For now, dataToReceiveFromClient and dataToSendToClient are set to null
     *
     * @param port the port number
     */
    public ClackServer(int port) throws IllegalArgumentException {
        if (port < 1024)
            throw new IllegalArgumentException("Port cannot be less than 1024");

        this.serverSideClientIOList = new ArrayList<>();
        this.closeConnection = false;
        this.port = port;
    }
    /**
     * Default constructor that creates an instance of ClackServer with a port number of 7000.
     */
    public ClackServer() {
        this(CONSTANT_DEFAULT_PORT);
    }

    /**
     * Main method
     * @param args Takes in port #
     */
    public static void main(String[] args)
    {
        ClackServer CS;
        if (args.length == 0)
            CS = new ClackServer();
        else
            CS = new ClackServer(Integer.parseInt(args[0]));

        CS.start();
    }

    /**
     * Start server
     */
    public void start() {
        try {

            ServerSocket skt = new ServerSocket(port);
            while (!closeConnection) {
                ServerSideClientIO sscio = new ServerSideClientIO(this, skt.accept());
                serverSideClientIOList.add(sscio);
                new Thread(sscio).start();
            }
            skt.close();
        } catch (IOException ioe) {
           System.err.println(ioe.getMessage());
        }
    }

    /**
     * Sets serverSideClientIO.dataToSendToClient equal to dataToBroadcastToClients and sends it for each serverSideClientIO
     * in serverSideClientIOList.
     */

    public synchronized void broadcast(ClackData dataToBroadcastToClients) {
        for (ServerSideClientIO j : serverSideClientIOList) {
            j.setDataToSendToClient(dataToBroadcastToClients);
            j.sendData();
        }
    }

    /**
     * Removes the serverSideClientIO from the serverSideClientIOList
     * @param sscio the serverSideClientIO
     */
    public synchronized void remove(ServerSideClientIO sscio) {
        serverSideClientIOList.remove(sscio);
        if (serverSideClientIOList.size() == 0)
            closeConnection = true;
        else
            listUsers();
    }

    /**
     * Builds a list of all client usernames that are connected to the server and sends them to the client that requested
     * it.
     */
    public void listUsers() {
        String users = "";
        for (ServerSideClientIO j : serverSideClientIOList) {
            if (j.getUsername() != null)
                users += j.getUsername() + ", ";
        }
        users = users.substring(0, users.length()-2);

        ClackData data = new MessageClackData("User List", users, ClackData.CONSTANT_LISTUSERS);
            broadcast(data);
    }
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
        return port == otherCS.port && closeConnection == otherCS.closeConnection;
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
        return "Port: " + port + "\n" +
                "Close connection? " + closeConnection;
    }
}
