/**
 * Homework 2B 
 * Abigail Castro , abc3gnm
 */

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class LibraryTest {

    
    Library a = new Library("Abby", 17);
    Library b = new Library("Allen", 20);
    Library c = new Library("Wen", 28);
    Library d = new Library("Q", 14);
    Library e = new Library("Cindy",18);
    Photo p1 = new Photo("Brussels", "C'est la vie","2019-08-17", 2);
    Photo p2 = new Photo("US", "Good Morning America!","2019-08-18",1);
    Photo p3 = new Photo("Paris", "Au revoir!");
    Photo p4 = new Photo("Barcelona", "Hasta luego!");
    Photo p5 = new Photo ("Palm Beach","Sunny days!");
    Photo p6 = new Photo ("Daytona Beach","Sunny days!","1901-01-01",3);
    PhotoCaptionComparator c1 = new PhotoCaptionComparator();
    PhotoRatingComparator r1 = new PhotoRatingComparator();


    @Before
    public void setUp() throws Exception {
        
        a.addPhoto(p1);
        a.addPhoto(p2);
        a.addPhoto(p3);
        a.addPhoto(p4);
        a.createAlbum("France");
        a.addPhotoToAlbum(p1, "France");
        a.addPhotoToAlbum(p2, "France");
        a.addPhotoToAlbum(p3, "France");
        a.createAlbum("Spain");
        a.addPhotoToAlbum(p4, "Spain");
        
        b.addPhoto(p1);
        b.addPhoto(p2);
        b.addPhoto(p3);
        b.addPhoto(p4);
        
        c.addPhoto(p1);
        c.addPhoto(p2);
        c.addPhoto(p3);
        c.addPhoto(p4);
        
        d.addPhoto(p1);
        d.addPhoto(p2);
        d.addPhoto(p5);
        d.addPhoto(p6);
        
    }

    @Test
    public void testGetPhotos() {
        ArrayList<Photo> expected1 = new ArrayList<Photo>();
        expected1.add(p1);
        expected1.add(p2);
        expected1.add(p3);
        expected1.add(p4);
        ArrayList<Photo> actual1 = a.getPhotos(1);
        assertEquals(expected1,actual1);
        ArrayList<Photo> expected2 = new ArrayList<Photo>();
        expected2.add(p1);
        ArrayList<Photo> actual2 = a.getPhotos(2);
        assertEquals(expected2,actual2);
        ArrayList<Photo> expected3 = new ArrayList<Photo>();
        assertEquals(expected3,a.getPhotos(3));
        assertEquals(null,a.getPhotos(0));
        assertNull(a.getPhotos(6));
    }
    
    @Test
    public void testGetPhotosInMonth() {
        
        ArrayList<Photo> expected1 = new ArrayList<Photo>();
        expected1.add(p3);
        expected1.add(p4);
        assertEquals(expected1,a.getPhotosInMonth(01, 1901));
        assertEquals(null,a.getPhotosInMonth(12, 10000));
        assertEquals(null,a.getPhotosInMonth(13, 10000));
        assertEquals(null,a.getPhotosInMonth(12, -1000));
        assertEquals(null,a.getPhotosInMonth(13, -1000));
        ArrayList<Photo> expected2 = new ArrayList<Photo>();
        assertEquals(expected2,a.getPhotosInMonth(01, 9999));
        assertEquals(expected2,a.getPhotosInMonth(01, 0000));
    }
    
    @Test
    public void testGetPhotosBetween() {
        
        ArrayList<Photo> expected1 = new ArrayList<Photo>();
        expected1.add(p1);
        expected1.add(p2);
        assertEquals(expected1,a.getPhotosBetween("2019-08-16", "2019-08-31"));
        assertNull(a.getPhotosBetween("20160817","2019-08-17"));
        assertNull(a.getPhotosBetween("2016-08-17","20190817"));
        assertNull(a.getPhotosBetween("2020-08-17","2019-08-17"));
        ArrayList<Photo> expected2 = new ArrayList<Photo>();
        assertEquals(expected2,a.getPhotosBetween("2019-08-01", "2019-08-16"));
    }
    
    @Test
    public void testRemovePhoto() {
        Photo not = new Photo ("Palm Beach","Sunny days!");
        assertTrue(a.removePhoto(p2));
        ArrayList<Photo> expected1 = new ArrayList<Photo>();
        expected1.add(p1);
        expected1.add(p3);
        expected1.add(p4);
        assertEquals(expected1,a.getPhotos());
        assertFalse(a.removePhoto(not));
        System.out.println(a.toString());
    }
    
    @Test
    public void testSimilarity() {
        assertEquals(0.0, Library.similarity(b, e),0.1);
        assertEquals(1.0, Library.similarity(b, c),0.1);
        assertEquals(0.5, Library.similarity(b, d),0.1);
    }
    
    @Test
    public void testCompareTo() {
        System.out.println("compareTo Test");
        //p1 was taken before p2 therefore returns negative number
        assertTrue(p1.compareTo(p2)<0);
        System.out.println(p1.compareTo(p2));
        //p2 was taken after p1 therefore returns positive number
        assertTrue(p2.compareTo(p1)>0);
        System.out.println(p2.compareTo(p1));
        //p3 and p4 were taken on the same date
        //but p3's caption is alphabetically before p4's caption
        //therefore should return a negative number
        assertTrue(p3.compareTo(p4)<0);
        System.out.println(p3.compareTo(p4));
        //p3 and p4 were taken on the same date
        //but p4's caption is alphabetically after p3's caption
        //therefore should return a positive number
        assertTrue(p4.compareTo(p3)>0);
        System.out.println(p4.compareTo(p3));
    }
    
    @Test
    public void testCompare() {
        System.out.println("PhotoCaptionComparator Test");
        //p3's caption is alphabetically before p4's caption
        //therefore should return a negative number
        assertTrue(c1.compare(p3,p4)<0);
        System.out.println(c1.compare(p3,p4));
        //p4's caption is alphabetically after p3's caption
        //therefore should return a positive number
        assertTrue(c1.compare(p4,p3)>0);
        System.out.println(c1.compare(p4,p3));
        //p5's caption is the same as p6's caption
        //but p6's rating is higher than p5's rating
        //therefore should return a negative number
        assertTrue(c1.compare(p6,p5)<0);
        System.out.println(c1.compare(p6,p5));
        //p5's caption is the same as p6's caption
        //but p5's rating is lower than p6's rating
        //therefore should return a positive number
        assertTrue(c1.compare(p5,p6)>0);
        System.out.println(c1.compare(p5,p6));
        
        System.out.println("PhotoRatingComparator Test");
        //p3's and p4's ratings are the same
        //but p3's caption is alphabetically before p4's caption
        //therefore should return a negative number
        assertTrue(r1.compare(p3,p4)<0);
        System.out.println(r1.compare(p3,p4));
        //p3's and p4's ratings are the same
        //p4's caption is alphabetically after p3's caption
        //therefore should return a positive number
        assertTrue(r1.compare(p4,p3)>0);
        System.out.println(r1.compare(p4,p3));
        //p6's rating is higher than p5's rating
        //therefore should return a negative number
        assertTrue(r1.compare(p6,p5)<0);
        System.out.println(r1.compare(p6,p5));
        //p5's rating is lower than p6's rating
        //therefore should return a positive number
        assertTrue(r1.compare(p5,p6)>0);
        System.out.println(r1.compare(p5,p6));
    }
}
