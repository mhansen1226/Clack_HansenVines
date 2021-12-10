package data;

import java.util.Date;
import java.io.Serializable;
import static java.lang.Character.*;


/**
 * This is a superclass that represents the data sent between the client and the server.
 *
 * @author Matt Hansen
 */
public abstract class ClackData implements Serializable {
    private String userName;
    private int type;
    private Date date;

    public static final int CONSTANT_LISTUSERS = 0;     // gives a list of all users connected to the session
    public static final int CONSTANT_LOGOUT = 1;        // log out -> close the client's connection
    public static final int CONSTANT_SENDMESSAGE = 2;   // send a message
    public static final int CONSTANT_SENDFILE = 3;      // send a file
    public static final int CONSTANT_USERNAME = 4;
    public static final int DEFAULT_TYPE = -1;          // invalid type to show something went wrong


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
     * Default constructor creates an anonymous user ("Anon") with invalid type to show something went wrong. The
     * default constructor should not be used if everything is working. The date is automatically recorded.
     */
    public ClackData() {
        this(DEFAULT_TYPE); // invalid type to show something went wrong
    }

    /**
     * Implements the Vigenère cipher
     *
     * @param inputStringToEncrypt  string to encrypt
     * @param key                   key used to encrypt
     * @return                      encrypted string
     */
    protected String encrypt(String inputStringToEncrypt, String key) {
        char[] cString = inputStringToEncrypt.toCharArray();
        char[] cKey = key.toLowerCase().toCharArray();

        String out = "";
        int i = 0; int j = 0;
        while(i < inputStringToEncrypt.length()) {
            boolean skipForNonLetter = false;
            char currChar = cString[i];
            if (!isLetter(currChar))
                skipForNonLetter = true;
            char aA = isUpperCase(currChar) ? 'A' : 'a';

            int currKeyCharPos = (cKey[j % key.length()] - 'a');
            int currStringCharPos = (cString[i] - aA);

            if (!skipForNonLetter) {
                int newChar = aA + (currStringCharPos + currKeyCharPos) % 26;
                out += (char) newChar;
                j++;
            } else out += cString[i];
            i++;
        }
        return out;
    }

    /**
     * Reverses the Vigenère cipher to decrypt a message
     *
     * @param inputStringToDecrypt  string to decrypt
     * @param key                   key used to decrypt
     * @return                      Decrypted string
     */
    protected String decrypt(String inputStringToDecrypt, String key) {
        char[] cString = inputStringToDecrypt.toCharArray();
        char[] cKey = key.toLowerCase().toCharArray();
        String out = "";
        int i = 0; int j = 0;
        while(i < inputStringToDecrypt.length()) {
            boolean skipForNonLetter = false;
            char currChar = cString[i];
            if (!isLetter(currChar))
                skipForNonLetter = true;
            char aA = isUpperCase(currChar) ? 'A' : 'a';

            int currKeyCharPos = (cKey[j % key.length()] - 'a');
            int currStringCharPos = (cString[i] - aA);

            if (!skipForNonLetter) {
                int newChar = aA + (currStringCharPos - currKeyCharPos + 26) % 26;
                out += (char) newChar;
                j++;
            } else out += cString[i];
            i++;
        }
        return out;
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
    public String getUsername() {
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
    public abstract String getData(String key);

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
