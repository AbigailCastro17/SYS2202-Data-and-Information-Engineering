 /**
 * Homework 3
 * Abigail Castro , abc3gnm
 */


import java.util.Comparator;

public class PhotoCaptionComparator implements Comparator<Photo>{

    //compares two Photos by caption in alphabetical order
    //if captions are identical, compare by rating in descending order
    public int compare(Photo p1, Photo p2) {//takes in two photos
        if(p1.getCaption().compareTo(p2.getCaption())==0) {//if captions are equals
            return p2.getRating()-p1.getRating();//compare by rating in descending order
        }
        return p1.getCaption().compareTo(p2.getCaption());//otherwise compare by caption in alphabetical order
    }
    
}
