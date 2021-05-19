/**
* Homework 2A
* Abigail Castro , abc3gnm
*
*/

//import java.util.*;

//Album class with fields, constructors, and methods
public class Album extends PhotoContainer {
	
    Album(String name) {
        super(name);
    }    
    
//	//fields
//	private String name;
//	private HashSet<Photo> photos;
//	
//	//constructor that take in a String creates a new Album
//	//object with name as the String and a new HashSet
//	public Album(String name) 
//		this.name = name;
//		this.photos = new HashSet<Photo>();
//	}
	
	//main method
	public static void main(String[] args) {
//
//		//establishing some Objects to use for testing
//		Album a = new Album ("France");//creates new Album a
//		Photo p1 = new Photo("Paris", "C'est la vie");//creates new Photo
//		Photo p2 = new Photo("Eiffel Tower", "Le monde");//create new Photo
//		Photo p3 = new Photo("Brussels", "Bridges");//create new Photo
//		
//		//addPhoto test
//		System.out.println("addPhoto test");
//		System.out.println(a.addPhoto(p1));//adds p1 to Album a, true
//		System.out.println(a.addPhoto(p2));//adds p2 to Album a, true
//		//addPhoto, edge case
//		System.out.println(a.addPhoto(p1));//does not add p1 to a because a already has p1, false
//		
//		//hasPhoto test
//	    System.out.println("\nhasPhoto test");
//		System.out.println(a.hasPhoto(p1));//checks if Album a has Photo p1, true
//		System.out.println(a.hasPhoto(p3));//checks if Album a has Photo p3, false
//		
//		//removePhoto test
//        System.out.println("\nremovePhoto test");
//		System.out.println(a.removePhoto(p2));//removes Photo p1 if in album, true
//		System.out.println(a.removePhoto(p3));//removes Photo p1 if in album, false
//
//		
//		//numPhotos Test
//		System.out.println("\nnumPhotos test");
//		Album d = new Album("Spain");
//		System.out.println(a.numPhotos());//a has 1 since p2 was removed
//		System.out.println(d.numPhotos());//d has 0 since no Photos were added
//		
//		//equals Test
//		System.out.println("\nequals test");
//		Album b = new Album ("France Part 2");//creates new Album b that is not equivalent to a
//		Album c = new Album ("France");//creates new Album c that is equivalent to a
//	    System.out.println(a.equals(b));// checks if albums are the same, false
//	    System.out.println(a.equals(c));// checks if albums are the same, true    
//
//        //toString Test
//        System.out.println("\ntoString test");
//        System.out.println(a.toString());//prints out Album a
	}
//
//	//accessor for name
//	public String getName() {
//		return name;
//	}
//	
//	//mutator for name
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	//accessor for photos
//	public HashSet<Photo> getPhotos() {
//		return photos;
//	}
//	
//	//adds Photo p to photos if not a duplicate
//	//return true if added, return false if not
//	public boolean addPhoto(Photo p) {
//	    if(p!=null) {//checks if photo is null
//	        if (!photos.contains(p)) { //if photos does not contain p
//	            photos.add(p);//add p to photos
//	            return true;
//	        }
//	    }
//		return false;
//	}
//	
//	//return true if photos has Photo p
//	//return false if not
//	public boolean hasPhoto(Photo p) {
//		if (photos.contains(p)) {//checks if photos has Photo p
//			return true;
//		}
//		return false;
//	}
//	
//	//remove p from photos if it exists in photos and returns true
//	//returns false if nothing is removed
//	public boolean removePhoto(Photo p) {
//		if(photos.contains(p)) {//checks if photos has Photo p
//			photos.remove(p);
//			return true;
//		}
//		return false;
//	}
//	
//	//returns the number of Photos in photos
//	public int numPhotos() {
//		return photos.size();
//	}
//	
//	//Albums are equal if names are equal
//	//return true if equal, false if not
//	public boolean equals(Object o) {
//		if (o==null) {//null check
//			return false;
//		}
//		if (o instanceof Album) {//are you an Album
//			Album p = (Album) o;//casting 
//			return this.name.equals(p.name);
//		}
//		else
//			return false;
//	}
//	
//	//prints out an Album object
//	public String toString() {
//		return "Album name: " + this.name + photos;
//	}
//	
//	//returns hashcode of the Album name
//	public int hashCode() {
//		return name.hashCode();
//	}
}
