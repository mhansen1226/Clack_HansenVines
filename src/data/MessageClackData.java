package data;

import java.util.Objects;

/**
 *
 *
 * @author Matt Hansen
 */
public class MessageClackData extends ClackData {

    private String message;

    /**
     * Constructor creates an instance of MessageClackData based on user-provided username, message, and type.
     *
     * @param username  the client's username
     * @param message   the instant message to be sent
     * @param type      the current type
     */
    public MessageClackData(String username, String message, int type) {
        super(username, type);
        this.message = message;
    }

    /**
     * Default constructor creates an instance with an empty message.
     */
    public MessageClackData() {
        super();
        this.message = "";
    }

    /**
     * Data accessor
     *
     * @return the message
     */
    public String getData() {
        return message;
    }

    /**
     * Overrides Object.equals()
     *
     * @param other any object
     *
     * @return boolean value that returns true iff the object is an instance of MessageClackData with
     *         an equivalent message
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof MessageClackData)) return false;
        MessageClackData otherMCD = (MessageClackData)other;
        return this.message.equals(otherMCD.message);
    }

    /**
     * Overrides Object.hashCode()
     *
     * @return returns the hash code of the message string
     */
    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    /**
     * Overrides Object.toString()
     *
     * @return String displaying all data contained within the instance of MessageClackData
     */
    @Override
    public String toString() {
        return "User name: " + getUserName() + "\n" +
                "Type: " + getType() + "\n" +
                "Message: " + message + "\n\n";
    }
}
