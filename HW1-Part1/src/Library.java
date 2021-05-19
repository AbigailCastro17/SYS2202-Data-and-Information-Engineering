
/**
 * Homework 1B 
 * Abigail Castro , abc3gnm
 */

import java.util.*;// allows for use of ArrayLists

public class Library extends PhotoContainer{

    // fields
    //private String name;
    private final int id;
    //private ArrayList<Photo> photos;
    private HashSet<Album> albums;

    // Constructor
    public Library(String name, int id) {
        super(name);
        //this.name = name;
        this.id = id;
        //this.photos = new ArrayList<Photo>();
        this.albums = new HashSet<Album>();
    }
//
//    // getters and setters
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public int getId() {
        return id;
    }

//    public ArrayList<Photo> getPhotos() {//returns ArrayList of Photos
//        return photos;
//    }

    public HashSet<Album> getAlbums() {//returns HashSet of Albums
        return albums;
    }

    // adds photo to library if library doesn't already contain it
    // returns true if p is added
//    public boolean addPhoto(Photo p) {
//        if (!photos.contains(p)) {//checks if photos does not contain p
//            photos.add(p);
//            return true;
//        } else
//            return false;
//    }

    // checks if p is in the library
//    public boolean hasPhoto(Photo p) {
//        if (photos.contains(p))//checks if photos contains p
//            return true;
//        else
//            return false;
//    }

    // deletes p if library contains it
    // returns true if p was removed
    /*
     * method from previous hw
     * modified below
      public boolean deletePhoto(Photo p) {
        if (photos.contains(p)) {
            photos.remove(p);
            return true;
        } else
            return false;
    }*/

    // returns number of photos in library
//    public int numPhotos() {
//        return photos.size();
//    }

    // checks if two libraries are equal
    public boolean equals(Object o) {
        if (o == null) {
            return false; // null check
        }
        if (o instanceof Library) { // are you a Library
            Library p = (Library) o; // casting
            // now I can use p as a Library
            return (this.id == p.id);
        } else {// o is not a Library
            return false;
        }
    }

    // returns object Library
    //modified below
    /*public String toString() {
        return "Name: " + name + "\nId: " + id + "\nPhotos: " + photos;
    }*/

    // returns an arraylist with the photos that are in both libraries
    public static ArrayList<Photo> commonPhotos(Library a, Library b) {
        ArrayList<Photo> common = new ArrayList<Photo>();//makes new ArrayList to keep all the Photos the libraries have in common
        for (int i = 0; i < a.photos.size(); i++) {//loops through Photo ArrayList in first library
            if (b.photos.contains(a.photos.get(i))) {//checks if Photo ArrayList in library b contains thePphoto with index i in library a
                common.add(a.photos.get(i));//adds Photo to the common ArrayList
            }
        }
        return common;
    }

    // returns a measure of how similar the libraries are
    public static double similarity(Library a, Library b) {
        double counter = 0.0; // counts how many similar pictures
        for (int i = 0; i < a.numPhotos(); i++) {//loops through Photo ArrayList in first library
            if (b.photos.contains(a.photos.get(i))) {//checks if Photo ArrayList in library b contains thePphoto with index i in library a
                counter++;//adds to the count of similar photos
            }
        }
        // divides counter by smaller library unless one library has no photos
        if (a.numPhotos() == 0 || b.numPhotos() == 0)
            return 0.0;
        else if (a.numPhotos() < b.numPhotos()) {//if a is the smaller library divide counter by a.numPhotos()
            return counter / a.numPhotos();
        } else
            return counter / b.numPhotos();//if b is the smaller library divide counter by b.numPhotos()
    }

//    // takes in a rating
//    // returns an ArrayList of photos that  have equal rating or higher
//    // if not between 1 and 5 returns null
//    // if no photos of that rating or higher, return empty ArrayList
//    public ArrayList<Photo> getPhotos(int rating) {
//        if (rating<1 || rating>5) {//if rating is not between 1 and 5 returns null
//            return null;
//        }
//        ArrayList<Photo> photosWithRating = new ArrayList<Photo>();//makes new ArrayList to hold photos with equal or higher rating
//        for (Photo p : photos) {//loops through ArrayList of photos
//            if(p.getRating()>=rating) {//checks if the Photo p has an equal or higher rating
//                photosWithRating.add(p);//adds Photo to the ArrayList created above 
//            }
//        }
//        return photosWithRating;
//    }
//
//    // return an ArrayList of photos taken in the year provided
//    // if year not valid or incorrectly formatted return null
//    // if no photos from that year, return empty ArrayList
//    public ArrayList<Photo> getPhotosInYear(int year) {
//        if (year<0000 || year>9999) {//checks if valid year, return null if not
//            return null;
//        }
//        ArrayList<Photo> photosInYear = new ArrayList<Photo>();//makes new ArrayList to hold photos taken in that year
//        for (Photo p : photos) {//loops through ArrayList of photos
//            String date = p.getDateTaken();//gets the date String from the Photo p
//            if (DateLibrary.getYear(date)==year) {//checks if year of Photo p is the same as the inputted year
//                photosInYear.add(p);//adds Photo p to ArrayList above
//            }
//        }
//        return photosInYear;
//    }
//
//    // return an ArrayList of photos taken in the month and year
//    // if month OR year formatted wrong, return null
//    // if no photos taken during that month, return empty list
//    public ArrayList<Photo> getPhotosInMonth(int month, int year) {
//        if ((year<0000 || year>9999) || (month<1 || month>12)) {//checks if valid month and year, returns null if either them aren't valid
//            return null;
//        }
//        ArrayList<Photo> photosInMonthYear = new ArrayList<Photo>();//makes new ArrayList to hold photos taken in that year and month
//        for (Photo p : photos) {//loops through ArrayList of photos
//            String date = p.getDateTaken();//gets the date String from the Photo p
//            if (DateLibrary.getYear(date)==year && DateLibrary.getMonth(date)==month) {//checks if year and month of Photo p is the same as the inputted ones
//                photosInMonthYear.add(p);//adds Photo p to ArrayList above
//            }
//        }
//        return photosInMonthYear;
//    }
//
//    // return an ArrayList of photos in that time period
//    // if begin and end are incorrectly formatted or begin is after end return null
//    // no photos during time period, return empty ArrayList
//    public ArrayList<Photo> getPhotosBetween(String beginDate, String endDate) {
//        if ((!DateLibrary.isValidDate(beginDate) || !DateLibrary.isValidDate(endDate)) || DateLibrary.compare(beginDate, endDate)>0) {
//            return null; // if begin and end are incorrectly formatted or begin is after end return null
//        }
//        ArrayList<Photo> photosInTimePeriod = new ArrayList<Photo>();//makes new ArrayList to hold photos in the time period specified
//        for (Photo p : photos) {//loops through ArrayList of Photos
//            if (DateLibrary.compare(beginDate, p.getDateTaken())<=0 && DateLibrary.compare(p.getDateTaken(), endDate)<=0) {//if dateTaken of p is between begin date and end date
//               photosInTimePeriod.add(p); //adds the Photo p
//            }
//        }
//        return photosInTimePeriod;
//    }

    // creates a new Album with name albumName and adds it to list of albums, no duplicates
    // returns true if successful, false otherwise
    public boolean createAlbum(String albumName) {
        Album album = new Album (albumName);//creates new Album
        if (getAlbumByName(albumName)==null) {//checks if Album with albumName exists, if not execute next line
            albums.add(album);
            return true;
        }
        return false;
        /*for (Album a : albums) {//loops through HashSet of Albums
            if (a.getName().equals(album.getName())) {//checks if Album with albumName already exists
               return false;
            }
        }  
        albums.add(album);//adds album to library
        return true;*/
    }

    // removes the Album with albumName if it exists
    // return true if successful, false otherwise
    public boolean removeAlbum(String albumName) {
        if (getAlbumByName(albumName)!=null) {//checks if Album with albumName exists, if it exists execute code in if statement
            albums.remove(getAlbumByName(albumName));
            return true;
        }
        return false;
        /*for (Album a : albums) {//loops through HashSet of Albums
            if (a.getName().equals(albumName)) {
               albums.remove(a);//checks if Album with albumName already exists
               return true;
            }
        }  
        return false;*/
    }

    // add Photo to the Album with albumName if not already in the Album
    // return true if added, false if not
    public boolean addPhotoToAlbum(Photo p, String albumName) {
        if (photos.contains(p)) {
            for (Album a : albums) {
                if (a.getName().equals(albumName)) {
                    a.addPhoto(p);
                    return true;
                }
            }  
        }
        return false;
    }

    // remove Photo from Album with albumName if in album
    //return true if removed, false otherwise
    public boolean removePhotoFromAlbum(Photo p, String albumName) {
        if (getAlbumByName(albumName)!=null) {//checks if Album with albumName exists, if it exists executes code in if statement
            if (getAlbumByName(albumName).hasPhoto(p)) {//checks if Album has Photo p
                getAlbumByName(albumName).removePhoto(p);
                return true;
            }
        }
        return false;
        /*for (Album a : albums) {
            if (a.getName().equals(albumName)) {
                a.removePhoto(p);
                return true;
            }
        }  
        return false;*/
    }
    
    //given album name, return the Album with that name
    //return null if not found
    private Album getAlbumByName(String albumName) {
        for (Album a : albums) {//loops through HashSet of Albums
            if (a.getName().equals(albumName)){//checks if Album with albumName already exists
                return a;
            }
        }
        return null;
    }
    
    //removes p from the Library list of photos
    //also removes p from any Album in list of albums
    //return true if successful, false otherwise
    public boolean removePhoto(Photo p) {
        if (photos.contains(p)) {//checks if photos contains Photo p
            photos.remove(p);
            for (Album a : albums) {//loops through HashSet of of Albums
                if (a.hasPhoto(p)) {//checks if Album has photo
                    a.removePhoto(p);
                }
            }
            return true;
        } 
        else
            return false;
    }
    
    //returns Library object, but nicely formatted
    public String toString() {
        return "Name: " + name + " Id: " + id + "\nPhotos: " + photos + "\nAlbums: " + albums;
    }

    // main method testing
    public static void main(String[] args) {
        //establishing some Objects to use for testing
        Library a = new Library("Abby", 17);
        Library b = new Library("Allen", 1);
        Library c = new Library("Alex", 2);

        Photo p1 = new Photo("Brussels", "C'est la vie");
        Photo p2 = new Photo("US", "Good Morning America!");
        Photo p3 = new Photo("Paris", "Au revoir!");
        Photo p4 = new Photo("Barcelona", "Hasta luego!");
        Photo p5 = new Photo("Philippines", "Mabuhay!");
        Photo p6 = new Photo("France", "C'est la vie");

        //addPhoto test
        System.out.println("addPhotoTest");
        System.out.println(a.addPhoto(p1));// true
        System.out.println(a.addPhoto(p2));// true
        System.out.println(a.addPhoto(p3));// true
        System.out.println(a.addPhoto(p3));// false
        System.out.println(a.addPhoto(p6));// true
        System.out.println(b.addPhoto(p1));// true
        System.out.println(b.addPhoto(p4));// true
        System.out.println(b.addPhoto(p5));// true

        //hasPhoto test
        System.out.println("\nhasPhoto test");
        System.out.println(b.hasPhoto(p2));// false
        System.out.println(b.hasPhoto(p1));// true


        //numPhotos test
        System.out.println("\nnumPhotos test");
        System.out.println(a.numPhotos());// 4
        System.out.println(b.numPhotos());// 3

        //commonPhotos test
        System.out.println("\ncommonPhotos test");
        System.out.println(commonPhotos(a, b));// p1

        //similarity test
        System.out.println("\nsimilarity test");
        System.out.println(similarity(a, b));// 0.3333
        System.out.println(similarity(a, c));// 0.0
        
        //getPhotos test
        System.out.println("\ngetPhotos test");
        System.out.println(a.getPhotos(5));//empty ArrayList
        
        //getPhotosInYear test
        System.out.println("\ngetPhotosInYear test");
        System.out.println(a.getPhotosInYear(1901));//p1,p2,p3,p6
        
        //getPhotosBetween test (more in JUnit Test file)
        System.out.println("\ngetPhotosBetween test");
        System.out.println(a.getPhotosBetween("1901-01-01", "1901-01-01"));//p1,p2,p3,p6
        
        //createAlbum test
        System.out.println("\ncreateAlbum test");
        System.out.println(a.createAlbum("Spain"));//true
        System.out.println(a.createAlbum("Spain"));//false
        System.out.println(a.createAlbum("France"));//true
        
        System.out.println(a.toString());
        
        //addPhotoToAlbum test
        System.out.println("\naddPhotoToAlbum test");
        System.out.println(a.addPhotoToAlbum(p1, "France"));//true
        System.out.println(a.addPhotoToAlbum(p3, "France"));//true
        System.out.println(a.addPhotoToAlbum(p1, "Spain"));//true
        System.out.println(a.addPhotoToAlbum(p1, "Spain"));//false
        
        System.out.println(a.toString());
        
        //removePhoto test
        System.out.println("\nremovePhoto test");
        System.out.println(a.removePhotoFromAlbum(p1, "Spain"));//true
        System.out.println(a.toString());
        
        //deletePhoto test
        System.out.println("\ndeletePhoto test");
        //System.out.println(a.deletePhoto(p1));//true     

        System.out.println(a.toString());
    }
}
