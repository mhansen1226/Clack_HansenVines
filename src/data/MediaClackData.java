package data;

import javafx.scene.media.Media;

import java.io.*;
import java.util.Objects;

/**
 * Subclass of ClackData that is used to deal with sending media files. Can be of type CONSTANT_MEDIAFILE.
 *
 * @author Matt Vines
 */
public class MediaClackData extends ClackData  {
    private String fileName;
    private byte[] fileContents;

    /**
     * Creates an instance of MediaClackData according to user provided username, file name, and type.
     * File contents are set to null.
     *
     * @param username the client's username
     * @param fileName the file name
     * @param type     the type
     */
    public MediaClackData(String username, String fileName, int type) {
        super(username, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    /**
     * Default constructor for MediaClackData creates an anonymous user ("Anon") in send file type.
     * File name and contents are set to null.
     */
    public MediaClackData() {
        super(CONSTANT_SENDFILE);
        this.fileName = null;
        this.fileContents = null;
    }

    /**
     * File name mutator
     *
     * @param fileName new file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * File name accessor
     *
     * @return file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Data accessor
     *
     * @return the contents of the file
     */
    public byte[] getData() {
        return fileContents;
    }

    /**
     * Takes in data and decrypts
     * @param key take a key as input
     * @return returns decrypted data
     */
    public String getData(String key) {
        return fileContents.toString();
    }

    /**
     * Read the contents of the file
     */
    public void readFileContents() throws IOException {
        try {
            File file = new File(fileName);
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            fileContents = new byte[(int) file.length()];
            is.read(fileContents,0, (int) file.length());

        } catch (NullPointerException npe) {
            System.err.println("No file name was provided");
        } catch (FileNotFoundException fnfe)  {
            System.err.println("File not found");
        } catch (IOException ioe) {
            throw new IOException("Issue with reading");
        }
    }

    public void readFileContents(String key) throws IOException {
        readFileContents();
    }

    /**
     * writes non-decrypted file contents to the file
     *
     */
    public void writeFileContents() {
        FileOutputStream os;
        try{
            os = new FileOutputStream(fileName);
            os.write(fileContents);
            os.close();
        }catch (IOException ioe){
            System.err.println("My message is: "+ ioe.getMessage());
        }
    }

    /**
     * Writes the decrypted file contents to the file
     * @param key Takes key as input
     */
    public void writeFileContents(String key){
        writeFileContents();
    }
}
