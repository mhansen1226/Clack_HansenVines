package data;

import java.util.Date;

/**
 * This is a superclass that represents the data sent between the client and the server.
 *
 * @author Matt Hansen
 */
public class ClackData {
    private String userName;
    private int type;
    private Date date;

    public static final int CONSTANT_LISTUSERS = 0; // gives a list of all users connected to the session
    public static int CONSTANT_LOGOUT = 1;          // log out -> close the client's connection
    public static int CONSTANT_SENDMESSAGE = 2;     // send a message
    public static int CONSTANT_SENDFILE = 3;        // send a file


    /**
     * Constructor creates an instance of ClackData based on user-provided username and type. The date is automatically
     * recorded
     *
     * @param userName The client's username
     * @param type     The current type
     */
    public ClackData(String userName, int type) {
        this.userName = userName;
        this.type = type;
        this.date = new Date(); // sets date to the time it was instantiated
    }

    /**
     * Constructor that creates an anonymous user ("Anon") with a user-provided type. The date is automatically
     * recorded.
     *
     * @param type The current type
     */
    public ClackData(int type) {
        this("Anon", type);
    }

    /**
     * Constructor that creates an anonymous user ("Anon") in list users type. The date is automatically
     * recorded.
     */
    public ClackData() {
        this(CONSTANT_LISTUSERS); // need to decide default value, this might be it?
    }

    /**
     * The type accessor
     *
     * @return The type
     */
    public int getType() {
        return this.type;
    }

    /**
     * The username accessor
     *
     * @return The Username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * The date accessor
     *
     * @return The date
     */
    public Date getDate() {
        return this.date;
    }

//    public getData () {
//        // not sure what this is
//    }
}
