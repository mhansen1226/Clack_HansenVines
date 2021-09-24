package data;

import java.util.Date;

import static java.util.Objects.hash;

/**
 * This is a superclass that represents the data sent between the client and the server.
 *
 * @author Matt Hansen
 */
public abstract class ClackData {
    private String userName;
    private int type;
    private Date date;

    public static final int CONSTANT_LISTUSERS = 0;     // gives a list of all users connected to the session
    public static final int CONSTANT_LOGOUT = 1;        // log out -> close the client's connection
    public static final int CONSTANT_SENDMESSAGE = 2;   // send a message
    public static final int CONSTANT_SENDFILE = 3;      // send a file


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
     * @param type The type
     */
    public ClackData(int type) {
        this("Anon", type);
    }

    /**
     * Default constructor creates an anonymous user ("Anon") with invalid type. The date is automatically
     * recorded.
     */
    public ClackData() {
        this(-1); // need to decide default value, maybe this to show it wasn't instantiated?
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

    /**
     * Overrides Object.toString()
     *
     * @return String displaying all data contained within the instance of ClackData
     */
    @Override
    public String toString() {
        return "Username: " + userName + "\n" +
                "Type: " + type + "\n" +
                "Date: " + date;
    }

    /**
     * Overrides Object.hashCode()
     *
     * @return returns an integer specific to ClackData objects according to the data contained within them
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + type;
        result = 37*result + userName.hashCode();
        return result;
    }
}
