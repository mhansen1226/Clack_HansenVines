package data;

import java.io.*;
import java.util.Objects;

/**
 * Subclass of ClackData that is used to deal with sending files. Can be of type CONSTANT_SENDFILE.
 *
 * @author Matt Hansen
 */
public class FileClackData extends ClackData {
    private String fileName;
    private String fileContents;

    /**
     * Creates an instance of FileClackData according to user provided username, file name, and type.
     * File contents are set to null.
     *
     * @param username the client's username
     * @param fileName the file name
     * @param type     the type
     */
    public FileClackData(String username, String fileName, int type) {
        super(username, type);
        this.fileName = fileName;
        this.fileContents = null;
    }

    /**
     * writes non-decrypted file contents to the file
     *
     */
    public void writeFileContents() {
        FileWriter writer = null;
        try{
            writer = new FileWriter(fileName);
            writer.write(fileContents);
            writer.close();
        }catch (IOException ioe){
            System.err.println("My message is: "+ ioe.getMessage());
        }
    }

    /**
     * Writes the decrypted file contents to the file
     * @param key Takes key as input
     */
    public void writeFileContents(String key){
        FileWriter writer = null;
        try{
            writer = new FileWriter(fileName);
            writer.write(decrypt(fileContents,key));
            writer.close();
        }catch (IOException ioe){
            System.err.println("My message is: "+ ioe.getMessage());
        }
    }

    /**
     * Default constructor for FileClackData creates an anonymous user ("Anon") in send file type.
     * File name and contents are set to null.
     */
    public FileClackData() {
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
    public String getData() {
        return fileContents;
    }

    /**
     * Takes in data and decrypts
     * @param key take a key as input
     * @return returns decrypted data
     */
    public String getData(String key) {
        return decrypt(fileContents, key);
    }

    /**
     * Read the contents of the file
     */
    public void readFileContents() throws IOException {
        try {
            File myFile = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(myFile));
            String line;

            while ((line = br.readLine()) != null) {
                fileContents += line + '\n';
            }

        } catch (IOException e) {
            System.err.println( "Issue with reading.");
        }
    }



    /**
     * Overrides Object.equals()
     *
     * @param other any object
     *
     * @return boolean value that returns true iff the object is an instance of FileClackData with
     *         an equivalent file name, file contents, type, and username
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof FileClackData)) return false;
        FileClackData otherFCD = (FileClackData) other;
        return (fileName.equals(otherFCD.fileName) &&
                fileContents.equals(otherFCD.fileContents) &&
                getType() == otherFCD.getType() &&
                getUsername().equals(otherFCD.getUsername()));
    }

    /**
     * Overrides Object.hashCode()
     *
     * @return returns an integer specific to FileClackData objects according to the data contained within them
     */
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 37 * result + Objects.hash(fileName);
        result = 37 * result + Objects.hash(fileContents);
        return result;
    }

    /**
     * Overrides Object.toString()
     *
     * @return String displaying all data contained within the instance of FileClackData
     */
    @Override
    public String toString() {
        return super.toString() + "\n" +
                "File name: " + fileName + "\n" +
                "File Contents: " + fileContents;
    }
}
