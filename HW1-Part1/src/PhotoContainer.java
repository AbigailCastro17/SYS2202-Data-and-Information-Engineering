/**
 * Homework 3
 * Abigail Castro , abc3gnm
 */

import java.util.*;

public abstract class PhotoContainer {

    //fields
    protected String name;
    protected ArrayList<Photo> photos;
    
    //constructor
    public PhotoContainer(String name) {
        this.name = name;
        this.photos = new ArrayList<Photo>();
    }
    
    //accessor for name
    public String getName() {
        return name;
    }
    
    //mutator for name
    public void setName(String name) {
        this.name = name;
    }
    
    //accessor for photos
    public ArrayList<Photo> getPhotos() {
        return photos;
    }
    
    //adds Photo p to photos if not a duplicate
    //return true if added, return false if not
    public boolean addPhoto(Photo p) {
        if(p!=null) {//checks if photo is null
            if (!photos.contains(p)) { //if photos does not contain p
                photos.add(p);//add p to photos
                return true;
            }
        }
        return false;
    }
    
    //return true if photos has Photo p
    //return false if not
    public boolean hasPhoto(Photo p) {
        if (photos.contains(p)) {//checks if photos has Photo p
            return true;
        }
        return false;
    }
    
    //remove p from photos if it exists in photos and returns true
    //returns false if nothing is removed
    public boolean removePhoto(Photo p) {
        if(photos.contains(p)) {//checks if photos has Photo p
            photos.remove(p);
            return true;
        }
        return false;
    }
    
    //returns the number of Photos in photos
    public int numPhotos() {
        return photos.size();
    }
    
    //Albums are equal if names are equal
    //return true if equal, false if not
    public boolean equals(Object o) {
        if (o==null) {//null check
            return false;
        }
        if (o instanceof Album) {//are you a PhotoContainer
            Album a = (Album) o;//casting 
            return this.name.equals(a.name);
        }
        else
            return false;
    }
    
    //prints out an Album object
    public String toString() {
        return "Album name: " + this.name + photos;
    }
    
    //returns hashcode of the Album name
    public int hashCode() {
        return name.hashCode();
    }
    
    // takes in a rating
    // returns an ArrayList of photos that  have equal rating or higher
    // if not between 1 and 5 returns null
    // if no photos of that rating or higher, return empty ArrayList
    public ArrayList<Photo> getPhotos(int rating) {
        if (rating<1 || rating>5) {//if rating is not between 1 and 5 returns null
            return null;
        }
        ArrayList<Photo> photosWithRating = new ArrayList<Photo>();//makes new ArrayList to hold photos with equal or higher rating
        for (Photo p : photos) {//loops through ArrayList of photos
            if(p.getRating()>=rating) {//checks if the Photo p has an equal or higher rating
                photosWithRating.add(p);//adds Photo to the ArrayList created above 
            }
        }
        return photosWithRating;
    }

    // return an ArrayList of photos taken in the year provided
    // if year not valid or incorrectly formatted return null
    // if no photos from that year, return empty ArrayList
    public ArrayList<Photo> getPhotosInYear(int year) {
        if (year<0000 || year>9999) {//checks if valid year, return null if not
            return null;
        }
        ArrayList<Photo> photosInYear = new ArrayList<Photo>();//makes new ArrayList to hold photos taken in that year
        for (Photo p : photos) {//loops through ArrayList of photos
            String date = p.getDateTaken();//gets the date String from the Photo p
            if (DateLibrary.getYear(date)==year) {//checks if year of Photo p is the same as the inputted year
                photosInYear.add(p);//adds Photo p to ArrayList above
            }
        }
        return photosInYear;
    }

    // return an ArrayList of photos taken in the month and year
    // if month OR year formatted wrong, return null
    // if no photos taken during that month, return empty list
    public ArrayList<Photo> getPhotosInMonth(int month, int year) {
        if ((year<0000 || year>9999) || (month<1 || month>12)) {//checks if valid month and year, returns null if either them aren't valid
            return null;
        }
        ArrayList<Photo> photosInMonthYear = new ArrayList<Photo>();//makes new ArrayList to hold photos taken in that year and month
        for (Photo p : photos) {//loops through ArrayList of photos
            String date = p.getDateTaken();//gets the date String from the Photo p
            if (DateLibrary.getYear(date)==year && DateLibrary.getMonth(date)==month) {//checks if year and month of Photo p is the same as the inputted ones
                photosInMonthYear.add(p);//adds Photo p to ArrayList above
            }
        }
        return photosInMonthYear;
    }

    // return an ArrayList of photos in that time period
    // if begin and end are incorrectly formatted or begin is after end return null
    // no photos during time period, return empty ArrayList
    public ArrayList<Photo> getPhotosBetween(String beginDate, String endDate) {
        if ((!DateLibrary.isValidDate(beginDate) || !DateLibrary.isValidDate(endDate)) || DateLibrary.compare(beginDate, endDate)>0) {
            return null; // if begin and end are incorrectly formatted or begin is after end return null
        }
        ArrayList<Photo> photosInTimePeriod = new ArrayList<Photo>();//makes new ArrayList to hold photos in the time period specified
        for (Photo p : photos) {//loops through ArrayList of Photos
            if (DateLibrary.compare(beginDate, p.getDateTaken())<=0 && DateLibrary.compare(p.getDateTaken(), endDate)<=0) {//if dateTaken of p is between begin date and end date
               photosInTimePeriod.add(p); //adds the Photo p
            }
        }
        return photosInTimePeriod;
    }
}
