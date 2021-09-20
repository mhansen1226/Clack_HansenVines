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

    public MessageClackData() {
        super();
        this.message = "";
    }

    public String getData() {
        return this.message;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MessageClackData)) return false;
        MessageClackData otherMCD = (MessageClackData)other;
        return this.message.equals(otherMCD.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "User name: " + getUserName() + "\n" +
                "Type: " + getType() + "\n" +
                "Message: " + getData() + "\n\n";
    }
}
