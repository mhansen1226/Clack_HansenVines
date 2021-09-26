package main;


import data.ClackData;

import java.util.Objects;

/**
 * Parent class that is used to represent the client user.
 *
 * @author Matt Vines
 */
public class ClackClient {

private String userName;
private String hostName;
private int port;
private boolean closeConnection;
ClackData dataToSendToServer;
ClackData dataToReceiveFromServer;

    /**
     * constructor creates an instance based on username hostname and port number
     * closed connection set to false
     * @param userName the client username
     * @param hostName the host name
     * @param port port number
     *
     */
    ClackClient(String userName, String hostName, int port) {
this.userName = userName;
this.hostName = hostName;
this.port = port;
this.closeConnection = false;
this.dataToReceiveFromServer = null;
this.dataToSendToServer = null;
}

    /**
     *  Construction based on a username and host name given
     *  Port is set to defualt = 7000
     *  Closed connection set to false
     * @param userName set to username
     * @param hostName set to host name
     *
     */
    ClackClient(String userName, String hostName){
this(userName, hostName, 7000);
}

    /**
     * Will set host name to "localHost"
     * port is set to 7000
     * closed connection set to false
     * @param userName set to username
     */
    ClackClient(String userName){
        this(userName, "localHost");
}

    /**
     * Default constructor with no inputs
     * Will set name to "Anon"
     * Will set host name to "localHost"
     * closed connection set to false
     * port is set to 7000
     */
    ClackClient(){
        this("Anon");
}
//  public void start(){}
//  public void readClientData(){}
//  public void sendData(){}
//  public void receiveData(){}
//  public void printData(){}

    public String getUserName(){ return userName;}
    public String getHostName(){ return hostName;}
    public int getPort(){ return port;}

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof ClackClient)) return false;
        ClackClient otherCC = (ClackClient)other;
        return port == otherCC.port && closeConnection == otherCC.closeConnection &&
                userName.equals(otherCC.userName) && hostName.equals(otherCC.hostName) &&
                dataToSendToServer.equals(otherCC.dataToSendToServer) &&
                dataToReceiveFromServer.equals(otherCC.dataToReceiveFromServer);
    }

    @Override
    public int hashCode() {
       int result =17;
       result = 37*result + port;
       result = 37*result + userName.hashCode();
       result = 37*result + hostName.hashCode();
       result = 37*result + Objects.hash(closeConnection);
       result = 37*result + dataToSendToServer.hashCode();
       result = 37*result + dataToReceiveFromServer.hashCode();
       return result;
    }

    @Override
    public String toString(){
        return "port: " + port + "\n" +
                "UserName: " + userName + "\n" +
                "HostName: " + hostName + "\n" +
                "Close connection? " + closeConnection + "\n" +
                "Data to send to server: " + dataToSendToServer.toString() + "\n" +
                "Data to Receive From Server" + dataToReceiveFromServer.toString();
    }
}
