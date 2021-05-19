/**
 * Homework 1A 
 * Abigail Castro , abc3gnm
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Photo implements Comparable<Photo>{

    // fields
    private String caption;
    private String filename;
    private int rating;
    private final String dateTaken;
    protected BufferedImage imageData;

    // constructors
    public Photo(String filename, String caption) {
        this.filename = filename;
        this.caption = caption;
        this.dateTaken = "1901-01-01";
        this.rating = 1;
    }

    public Photo(String filename, String caption, String dateTaken, int rating) {
        this.filename = filename;
        this.caption = caption;
        if (!DateLibrary.isValidDate(dateTaken)) {
            this.dateTaken = "1901-01-01";
        } else {
            this.dateTaken = dateTaken;
        }
        this.rating = rating;
        if (this.rating < 1 || this.rating > 5) {
            this.rating = 1;
        }

    }

    public Photo() {
        this.filename = "";
        this.caption = "";
        this.rating = 1;
        this.dateTaken = "1901-01-01";
    }

    // getters
    public String getCaption() {
        return caption;
    }

    public String getFilename() {
        return filename;
    }

    public int getRating() {
        return rating;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    // set new rating and return true if rating is different than original and between 1 & 5
    // sets rating to 1 and returns false otherwise
    public boolean setRating(int newRating) {
        if (newRating != rating && newRating >= 1 && newRating <= 5) {
            rating = newRating;
            System.out.println(rating);
            return true;
        } else if (newRating == rating || newRating < 1 || newRating > 5) {
            return false;
        } 
        else {
            return false;
        }

    }

    // checks if objects are equal
    public boolean equals(Object o) {
        if (o == null) {
            return false; // null check
        }
        if (o instanceof Photo) { // are you a Photo
            Photo p = (Photo) o; // casting
            // now I can use p as a Photo
            return (this.filename == p.filename) && (this.caption == p.caption);
        } else {// o is not a Photo
            return false;
        }
    }

    public boolean equalstoo(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Photo) {
            Photo p = (Photo) o;
            return (this.filename.equals(p.filename) && this.caption.equals(p.caption));
        } else {
            return false;
        }

    }

    // returns Photo object formatted nicely
    public String toString() {
        return "Filename: " + filename + "\n" + "Caption: " + caption /*+ " Rating: " + rating */;
    }

    // overrides the default hashcode method
    // produces a unique integer for a Photo
    public int hashCode() {
        return filename.hashCode();
    }
    
    // define the natural ordering of photographs to be first dateTaken
    //and second by their caption
    @Override
    public int compareTo(Photo p) {//takes in one Photo
        // TODO Auto-generated method stub
        if (this.dateTaken.compareTo(p.dateTaken)==0) {//checks if dateTaken are the same
            return this.caption.compareTo(p.caption);//compares by caption in alphabetical order
        }
        return this.dateTaken.compareTo(p.dateTaken);//otherwise compares by dateTaken in ascending order
    }
    
    //HW 4 method addition
    
    /**
     Given a filename as a String, this method loads the Image data from the file and stores
     it into the imageData field. The filename is stored in the filename field. This method
     returns true on success and false on failure
    */
    public boolean loadImageData (String filename){
        try {//tries the following code, but goes to catch statements if an exception occurs
            BufferedImage img = ImageIO.read(new File(filename));//load image
            this.imageData = img;//stores it in imageData
            this.filename=filename;//sets the filename to entered filename
            return true;
        }//catch statements that will tell what kind of exception occured
        catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
        }
        catch (RuntimeException e) {
            System.out.println("A runtime exception occured.");
        }
        catch (Exception e) {
            System.out.println("An error occured.");
        }
        finally {
            System.out.println("End of program.");
        }
        return false;
    }

    //returns reference to imageData
    public BufferedImage getImageData() {
        return imageData;
    }

    //mutator for imageData, takes in a BufferedImage and sets imageData to the entered BufferedImage
    public void setImageData(BufferedImage imageData) {
        this.imageData = imageData;
    }

    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // testing
        String imageDirectory = "images\\";
        Photo p1 = new Photo(imageDirectory+"Family.JPG", "C'est la vie");
        p1.loadImageData(p1.getFilename());
//        System.out.println(p1.toString());//1
//        System.out.println(p1.setRating(3));//3 true
//        System.out.println(p1.setRating(3));//false
//        System.out.println(p1.getRating());//3
//        System.out.println(p1.getRating());//3
//        System.out.println(p1.setRating(1));//false
//        System.out.println(p1.getRating());//1
//        System.out.println(p1.setRating(5));//5 true
//        System.out.println(p1.setRating(5));//false
//        System.out.println(p1.getRating());//5

        /*Photo p2 = new Photo("France", "C'est la vie");
        System.out.println(p2.setRating(0));
        System.out.println(p2.toString());
        System.out.println(p2.setRating(1));

        Photo p3 = new Photo("France", "C'est la vie");
        System.out.println(p1.equals(p3));

        Photo p4 = new Photo("Spain", "Hasta luego!");
        System.out.println(p1.equals(p4));

        System.out.println(p3.toString());*/

    }



}
