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
    private String mediaFileName;
    private Media media;

    /**
     * Creates an instance of MediaClackData according to user provided username, file name, and type.
     * File contents are set to null.
     *
     * @param username the client's username
     * @param mediaFileName the file name
     * @param type     the type
     */
    public MediaClackData(String username, String mediaFileName, int type) {
        super(username, type);
        this.mediaFileName = mediaFileName;
        media = new Media(mediaFileName);
    }

    /**
     * Default constructor for MediaClackData creates an anonymous user ("Anon") in send file type.
     * File name and contents are set to null.
     */
    public MediaClackData() {
        super(CONSTANT_SENDMEDIA);
        this.mediaFileName = null;
    }

    /**
     * File name mutator
     *
     * @param fileName new file name
     */
    public void setFileName(String fileName) {
        this.mediaFileName =  mediaFileName;
    }

    /**
     * File name accessor
     *
     * @return file name
     */
    public String getFileName() {
        return mediaFileName;
    }

    /**
     * Data accessor
     *
     * @return the contents of the file
     */
    public String getData(String key) {
        return media.toString();
    }

    public String getData() {
        return media.toString();
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
        if (!(other instanceof MediaClackData)) return false;
        MediaClackData otherFCD = (MediaClackData) other;
        return (mediaFileName.equals(otherFCD.mediaFileName) &&
                media.equals(otherFCD.media) &&
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
        result = 37 * result + Objects.hash(mediaFileName);
        result = 37 * result + Objects.hash(media);
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
                "File name: " + mediaFileName + "\n" +
                "File Contents: " + media.toString();
    }
}
