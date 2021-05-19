import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PhotoWindow extends JFrame {
    
    //fields
    private PhotoContainer imageLibrary;
    public JButton nextButton;
    public JButton previousButton;
    public JLabel fileLabel;
    public JButton ratingOne;
    public JLabel picLabel;
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PhotoWindow myViewer = new PhotoWindow();
        
        // the relative image directory
        String imageDirectory = "images\\"; // for Windows
            
        Photo p1 = new Photo (imageDirectory +
        "Mandala.JPG", "Caption", " 2015 -06 -30 ", 5) ;
        p1.loadImageData (imageDirectory + "Mandala.JPG");
        Photo p2 = new Photo (imageDirectory +
        "Sunrise.JPG", "Caption", " 2015 -06 -30 ", 5) ;
        p2.loadImageData (imageDirectory + "Sunrise.JPG"); 
        Photo p3 = new Photo (imageDirectory +
        "Family.JPG", "Caption", " 2015 -06 -30 ", 5) ;
        p3.loadImageData (imageDirectory + "Family.JPG");
        Photo p4 = new Photo (imageDirectory +
        "Pillar.JPG", "Caption", " 2015 -06 -30 ", 5);
        p4.loadImageData (imageDirectory + "Pillar.JPG");
        Photo p5 = new Photo (imageDirectory +
        "Chinafest.png", "Caption", " 2015 -06 -30 ", 5) ;
        p5.loadImageData (imageDirectory + "Chinafest.png");
        // add four more photographs like the lines above
            
        myViewer.setImageLibrary(new Library("Test Library", 1)) ;
           
        myViewer.getImageLibrary().addPhoto(p1);
        myViewer.getImageLibrary().addPhoto(p2);
        myViewer.getImageLibrary().addPhoto(p3);
        myViewer.getImageLibrary().addPhoto(p4);
        myViewer.getImageLibrary().addPhoto(p5);
        // four more photographs added like the line above
        
        //System.out.println(myViewer.getImageLibrary());
        
        // use Collections.sort() to sort the photos based on the
        // written compareTo() method
        //Collections.sort(myViewer.getImageLibrary().getPhotos());
        //System.out.println(myViewer.getImageLibrary().getPhotos().get(0));   
    }
    
    public PhotoWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        this.setSize(width, height);
        
        nextButton = new JButton("Next");
        previousButton= new JButton("Previous");
        fileLabel = new JLabel("Hi");
        ratingOne = new JButton("1");

        
        String imageDirectory = "images\\"; // for Windows
        
        Photo p1 = new Photo (imageDirectory +
        "Mandala.JPG", "Caption", " 2015 -06 -30 ", 5) ;
        p1.loadImageData (imageDirectory + "Mandala.JPG");
        p1.loadImageData(p1.getFilename());
        Image img = p1.getImageData();
        img = img.getScaledInstance((width/20)*8, -1, Image.SCALE_AREA_AVERAGING);
        picLabel = new JLabel(new ImageIcon(img));
        
        previousButton.setSize(width/10, 50);
        previousButton.setLocation(width, height/3);
        nextButton.setSize(width/10, 50);
        nextButton.setLocation(width-(width/10), height/3);
        fileLabel.setSize(23*width/60, height/6);
        fileLabel.setLocation(width/15, 1);
        picLabel.setLocation((width/20)*4,height/20);

        
        this.add(nextButton);
        this.add(previousButton);
        this.add(fileLabel);
        this.add(ratingOne);
        this.add(picLabel);
        
        pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    //getter, takes no arguments, returns the imageLibrary
    public PhotoContainer getImageLibrary() {
        return imageLibrary;
    }

    //setter, take in PhotoContainer object, sets imageLibrary to the enter PhotoContainer object
    public void setImageLibrary(PhotoContainer imageLibrary) {
        this.imageLibrary = imageLibrary;
    }

}
