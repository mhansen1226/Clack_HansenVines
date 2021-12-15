package data;

import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Subclass of ClackData that is used to deal with sending media files. Can be of type CONSTANT_MEDIAFILE.
 *
 * @author Matt Vines
 */
public class PictureClackData extends ClackData  {
    private Image picture;

    /**
     * Creates an instance of PictureClackData according to user provided username, picture , and type.
     * Sets picture
     *
     * @param username the client's username
     * @param picture the picture
     * @param type     the type
     */
    public PictureClackData(String username, Image picture, int type) {
        super(username, type);
        this.picture = picture;
    }

    /**
     * Default constructor for PictureClackData creates a picture.
     * picture is set to null
     */
    public PictureClackData() {
        super(CONSTANT_SENDVIDEO);
        this.picture = null;
    }

    /**
     * Methos to set the image
     *
     * @param picture image
     */
    public void setPicture(Image picture) {
        this.picture =  picture;
    }

    /**
     * Image accessor class
     * @return the picture
     */
    public Image getPicture() {
        return picture;
    }

    /**
     * Data accessor
     * @param key takes in the object key
     * @return the picture contents
     */
    public String getData(String key) {
        return picture.toString();
    }

    /**
     * Data accessor
     * @return the pictue to a string
     */
    public String getData() {
        return picture.toString();
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
        if (!(other instanceof PictureClackData)) return false;
        PictureClackData otherFCD = (PictureClackData) other;
        return (picture.equals(otherFCD.picture) &&
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
        result = 37 * result + Objects.hash(picture);
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
                "picture: " + picture.toString() ;
    }
}
