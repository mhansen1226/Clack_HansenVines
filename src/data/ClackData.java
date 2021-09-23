package data;

import java.util.Date;

/**
 * This is a superclass that represents the data sent between the client and the server.
 *
 * @author Matt Hansen
 */
public abstract class ClackData {
    private String userName;
    private int type;
    private Date date;

    public static final int CONSTANT_LISTUSERS = 0; // gives a list of all users connected to the session
    public final int CONSTANT_LOGOUT = 1;          // log out -> close the client's connection
    public final int CONSTANT_SENDMESSAGE = 2;     // send a message
    public final int CONSTANT_SENDFILE = 3;        // send a file


    /**
     * Constructor creates an instance of ClackData based on user-provided username and type. The date is automatically
     * recorded. Based on the type will create an object of MessageClackData or FileClackData
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
     * Constructor that creates an anonymous user ("Anon") in listUsers type. The date is automatically
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
        return type;
    }

    /**
     * The username accessor
     *
     * @return The Username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * The date accessor
     *
     * @return The date
     */
    public Date getDate() {
        return date;
    }

    public abstract String getData();
}
