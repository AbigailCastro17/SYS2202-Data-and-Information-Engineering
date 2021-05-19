 /* 
 * Homework 3
 * Abigail Castro , abc3gnm
 */


import java.util.Comparator;

public class PhotoRatingComparator implements Comparator<Photo>{


    //compares two Photos by rating in descending order
    //if captions are identical, compares by caption in alphabetical order
    @Override
    public int compare(Photo p1, Photo p2) {
        // TODO Auto-generated method stub
        if (p2.getRating()-p1.getRating()==0) {//if ratings are the same
            return p1.getCaption().compareTo(p2.getCaption());//compare by caption in alphabetical order
        }
        return p2.getRating()-p1.getRating();//otherwise compare by rating in descending order
    }

}
