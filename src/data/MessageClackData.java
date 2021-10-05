package data;

import java.util.Objects;

/**
 * Subclass of ClackData that is used to deal with messaging and managing client connection. Can be of type
 * CONSTANT_LISTUSERS, CONSTANT_LOGOUT, or CONSTANT_SENDMESSAGE.
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
     * @param type      the type
     */
    public MessageClackData(String username, String message, int type) {
        super(username, type);
        this.message = message;
    }

    /**
     * Default constructor for MessageClackData creates an anonymous user ("Anon") in list users type.
     * Message is set to null.
     */
    public MessageClackData() {
        super(CONSTANT_LISTUSERS);
        this.message = null;
    }

    /**
     * Constructor creates an instance of MessageClackData based on user-provided username, message, and type.
     * Encrypts the message using the provided key.
     *
     * @param username  the client's username
     * @param message   the instant message to be sent
     * @param key       the encryption key
     * @param type      the type
     */
    public MessageClackData(String username, String message, String key, int type) {
       super(username, type);
       this.message = encrypt(message,key);
    }

    /**
     * Data accessor
     *
     * @return the message
     */
    public String getData() {
        return message;
    }

    public String getData(String key) {
        return decrypt(message, key);
    }
    /**
     * Overrides Object.equals()
     *
     * @param other any object
     *
     * @return boolean value that returns true iff the object is an instance of MessageClackData with
     *         an equivalent message, type, and username
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof MessageClackData)) return false;
        MessageClackData otherMCD = (MessageClackData)other;
        return (message.equals(otherMCD.message) &&
                getType() == otherMCD.getType() &&
                getUserName().equals(otherMCD.getUserName()));
    }

    /**
     * Overrides Object.hashCode()
     *
     * @return returns an integer specific to MessageClackData objects according to the data contained within them
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37*result + Objects.hash(message);
        return result;
    }

    /**
     * Overrides Object.toString()
     *
     * @return String displaying all data contained within the instance of MessageClackData
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + "Message: " + message;
    }
}
