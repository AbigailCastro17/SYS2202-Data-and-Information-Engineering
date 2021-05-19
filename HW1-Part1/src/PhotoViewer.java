/**
 * Homework 4 
 * Abigail Castro , abc3gnm
 * Sources: StackOverflow, Oracle Java Documentation
 */

//imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class PhotoViewer extends JFrame {
    
    //fields
    private PhotoContainer imageLibrary;
    //instance variables for panel
    public JButton nextButton;
    public JButton previousButton;
    public JLabel fileLabel;
    public JLabel captionLabel;
    public JLabel picLabel;
    public static JRadioButton rating1;
    public static JRadioButton rating2;
    public static JRadioButton rating3;
    public static JRadioButton rating4;
    public static JRadioButton rating5;
    
    //variable for keeping track of index of Photo
    public int indexOfPic;
    
    
    private FlowLayout layout = new FlowLayout();

    //instantiates a PhotoViewer to use throughout code
    private static PhotoViewer myViewer = new PhotoViewer();
    

    
    public static void main(String[] args) {
    // TODO Auto-generated method stub
           
        
    // the relative image directory
    String imageDirectory = "images\\"; // for Windows
    
    //creates Photos and loads imageData
    Photo p1 = new Photo (imageDirectory +
    "Mandala.JPG", "Coloring?", " 2015 -06 -30 ", 5) ;
    p1.loadImageData (imageDirectory + "Mandala.JPG");
    Photo p2 = new Photo (imageDirectory +
    "Sunrise.JPG", "Nothing like waking up at the crack of dawn for school :)", " 2015 -06 -30 ", 1) ;
    p2.loadImageData (imageDirectory + "Sunrise.JPG"); 
    Photo p3 = new Photo (imageDirectory +
    "Family.JPG", "My college fam :))", " 2015 -06 -30 ", 4) ;
    p3.loadImageData (imageDirectory + "Family.JPG");
    Photo p4 = new Photo (imageDirectory +
    "Pillar.JPG", "Crackhead hours :P", " 2015 -06 -30 ", 3);
    p4.loadImageData (imageDirectory + "Pillar.JPG");
    Photo p5 = new Photo (imageDirectory +
    "Chinafest.png", "Pls come :)", " 2015 -06 -30 ", 2) ;
    p5.loadImageData (imageDirectory + "Chinafest.png");
    // add four more photographs like the lines above
     
    //creates ImageLibrary
    myViewer.setImageLibrary(new Library("Test Library", 1));
    
    //adds Photos to imageLibrary
    myViewer.getImageLibrary().addPhoto(p1);
    myViewer.getImageLibrary().addPhoto(p2);
    myViewer.getImageLibrary().addPhoto(p3);
    myViewer.getImageLibrary().addPhoto(p4);
    myViewer.getImageLibrary().addPhoto(p5);
    
    // Invoke and start the GUI
    myViewer.createAndShowGUI();
    }
    
    //getter, takes no arguments, returns the imageLibrary
    public PhotoContainer getImageLibrary() {
        return imageLibrary;
    }

    //setter, take in PhotoContainer object, sets imageLibrary to the enter PhotoContainer object
    public void setImageLibrary(PhotoContainer imageLibrary) {
        this.imageLibrary = imageLibrary;
    }
    
    //helper method to easily get BufferedImage
    public Image getBufferedImage(int indexOfPic) {
        Photo p = myViewer.imageLibrary.getPhotos().get(indexOfPic);
        Image img = p.getImageData();
        return img;
    }
    
    //helper method to easily get Photo
    public Photo getPhoto(int indexOfPic) {
        Photo p = myViewer.imageLibrary.getPhotos().get(indexOfPic);
        return p;
    }

    //method for selecting proper button based on rating
    public void setRatingButton(int rating) {
        if(rating==1) {
            rating1.setSelected(true);
        }
        else if(rating==2) {
            rating2.setSelected(true);
        }
        else if(rating==3) {
            rating3.setSelected(true);
        }
        else if(rating==4) {
            rating4.setSelected(true);
        }
        else {
            rating5.setSelected(true);
        }
    }

    //sets up initial gui
    private static void createAndShowGUI() {
        PhotoViewer frame = new PhotoViewer();
        frame.setTitle("My Photo Library");//sets title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//what the 'x' button does
        frame.setSize(500,800);//initial size of window
        frame.setLocationRelativeTo(null);//centers it
        frame.addComponentsToPane(frame.getContentPane());//calls method that adds components to gui
        
        frame.pack(); //minimizes extra space
        frame.setVisible(true);//makes it seen
    }
    
    //adds all the components to gui
    public void addComponentsToPane(Container pane) {
        indexOfPic = 0;//set indexOfPic to index of first Photo
        
        JPanel panel = new JPanel();//creates new panel
        
        panel.setLayout(new BorderLayout());//defines panel layout
        
        JPanel southPanel = new JPanel();//creates new subpanel to be added to main panel
        southPanel.setLayout(new FlowLayout());//defines southPanel layout
        
        JPanel centerPanel = new JPanel();//creates new subpanel to be added to main panel
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));//defines centerPanel layout
        
        fileLabel = new JLabel(myViewer.getPhoto(indexOfPic).getFilename().substring(7));//creates new label for the filename minus "images\"
        fileLabel.setFont(new Font("Arial",Font.PLAIN, 20));//sets font and size
        centerPanel.add(fileLabel);//adds fileLabel to the subpanel centerPanel
        
        Image i = myViewer.getBufferedImage(indexOfPic);//retrieves BufferedImage of first Photo in imageLibrary
        i = i.getScaledInstance(500, -1, Image.SCALE_AREA_AVERAGING);//scales it down
        
        picLabel = new JLabel(new ImageIcon(i));//creates new JLabel with an ImageIcon
        centerPanel.add(picLabel);//adds picLabel to the subpanel centerPanel
        
        nextButton = new JButton("Next");//creates new JButton with label Next
        nextButton.addActionListener(new ButtonListener());//adds an Action Listener
        nextButton.setActionCommand("click Next");//sets a command for nextButton
        panel.add(nextButton, BorderLayout.EAST);//sets location in the east area

        nextButton = new JButton("Previous");//creates new JButton with label Previous
        nextButton.addActionListener(new ButtonListener());//adds an Action Listener
        nextButton.setActionCommand("click Previous");//sets a command for previousButton
        panel.add(nextButton, BorderLayout.WEST);//sets location in west area
        
        captionLabel = new JLabel(myViewer.getPhoto(indexOfPic).getCaption());//creates new JLabel and sets it to the caption
        captionLabel.setFont(new Font("Arial",Font.PLAIN, 16));//sets font and size
        centerPanel.add(captionLabel); //adds captionLabel to the subpanel centerPanel

        rating1 = new JRadioButton("1");//creates new JRadioButton with label 1
        rating1.setFont(new Font("Arial",Font.PLAIN, 16));//sets font and size
        rating1.addActionListener(new ButtonListener());//adds an Action Listener
        rating1.setActionCommand("1");//sets a command for rating1
        rating2 = new JRadioButton("2");//creates new JRadioButton with label 2
        rating2.setFont(new Font("Arial",Font.PLAIN, 16));//sets font and size
        rating2.addActionListener(new ButtonListener());//adds an Action Listener
        rating2.setActionCommand("2");//sets a command for rating2
        rating3 = new JRadioButton("3");//creates new JRadioButton with label 3
        rating3.setFont(new Font("Arial",Font.PLAIN, 16));//sets font and size
        rating3.addActionListener(new ButtonListener());//adds an Action Listener
        rating3.setActionCommand("3");//sets a command for rating3
        rating4 = new JRadioButton("4");//creates new JRadioButton with label 4
        rating4.setFont(new Font("Arial",Font.PLAIN, 16));//sets font and size
        rating4.addActionListener(new ButtonListener());//adds an Action Listener
        rating4.setActionCommand("4");//sets a command for rating4
        rating5 = new JRadioButton("5");//creates new JRadioButton with label 5
        rating5.setFont(new Font("Arial",Font.PLAIN, 16));//sets font and size
        rating5.addActionListener(new ButtonListener());//adds an Action Listener
        rating5.setActionCommand("5");//sets a command for rating5
        
        ButtonGroup group = new ButtonGroup();//groups the radio buttons together so that only one can be selected at a time
        //adds button to group
        group.add(rating1);
        group.add(rating2);
        group.add(rating3);
        group.add(rating4);
        group.add(rating5);
        
        //adds buttons to subpanel southPanel
        southPanel.add(rating1);
        southPanel.add(rating2);
        southPanel.add(rating3);
        southPanel.add(rating4);
        southPanel.add(rating5);
        
        //selects rating button based on rating of first Photo
        myViewer.setRatingButton(myViewer.getPhoto(indexOfPic).getRating());
        
        //adds subpanels to main panel
        panel.add(southPanel,BorderLayout.SOUTH);
        panel.add(centerPanel, BorderLayout.CENTER);

        
        // add this panel to the content pane
        pane.add(panel);
    }    

    //new class for action listeners
    class ButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if (e.getActionCommand().contentEquals("click Next")) {//if nextButton is clicked
                if (indexOfPic==(myViewer.imageLibrary.getPhotos().size()-1)) {//if Photo is the last one in the imageLibrary
                    indexOfPic=0;//reset indexOfPic to 0
                }
                else {//otherwise
                    indexOfPic++;//add to indexOfPic
                }
                Image i = myViewer.getBufferedImage(indexOfPic);//gets buffered image of Photo
                i = i.getScaledInstance(500, -1, Image.SCALE_AREA_AVERAGING);//scales down Photo
                picLabel.setIcon(new ImageIcon(i));//sets picLabel to new Image
                fileLabel.setText(myViewer.getPhoto(indexOfPic).getFilename().substring(7));//sets fileLabel to the Photo's filename minus "images\"
                captionLabel.setText(myViewer.getPhoto(indexOfPic).getCaption());//sets captionLabel to the Photo's caption
                myViewer.setRatingButton(myViewer.getPhoto(indexOfPic).getRating());//selects button based on Photo's rating 
            }
            if (e.getActionCommand().contentEquals("click Previous")) {//if previousButton is clicked
                if (indexOfPic==0) {//if Photo is first Photo in imageLibrary
                    indexOfPic=4;//set index to last Photo of imageLibrary
                }
                else {//otherwise
                    indexOfPic--;//subtract from indexOfPic
                }
                Image i = myViewer.getBufferedImage(indexOfPic);//gets buffered image of Photo
                i = i.getScaledInstance(500, -1, Image.SCALE_AREA_AVERAGING);//scales down Photo
                picLabel.setIcon(new ImageIcon(i));//sets picLabel to new Image
                fileLabel.setText(myViewer.getPhoto(indexOfPic).getFilename().substring(7));//sets fileLabel to the Photo's filename minus "images\"
                captionLabel.setText(myViewer.getPhoto(indexOfPic).getCaption());//sets captionLabel to the Photo's caption
                myViewer.setRatingButton(myViewer.getPhoto(indexOfPic).getRating()); //selects button based on Photo's rating 
            }
            if(e.getActionCommand().contentEquals("1")) {//if rating1 is clicked
                myViewer.getPhoto(indexOfPic).setRating(1);//set Photo's rating to corresponding rating
                myViewer.setRatingButton(myViewer.getPhoto(indexOfPic).getRating()); //select proper rating button
            }
            if(e.getActionCommand().contentEquals("2")) {//if rating2 is clicked
                myViewer.getPhoto(indexOfPic).setRating(2);//set Photo's rating to corresponding rating
                myViewer.setRatingButton(myViewer.getPhoto(indexOfPic).getRating()); //select proper rating button
                
            }
            if(e.getActionCommand().contentEquals("3")) {//if rating3 is clicked
                myViewer.getPhoto(indexOfPic).setRating(3);//set Photo's rating to corresponding rating
                myViewer.setRatingButton(myViewer.getPhoto(indexOfPic).getRating());  //select proper rating button
            }
            if(e.getActionCommand().contentEquals("4")) {//if rating4 is clicked
                myViewer.getPhoto(indexOfPic).setRating(4);//set Photo's rating to corresponding rating
                myViewer.setRatingButton(myViewer.getPhoto(indexOfPic).getRating());  //select proper rating button
            }
            if(e.getActionCommand().contentEquals("5")) {//if rating5 is clicked
                myViewer.getPhoto(indexOfPic).setRating(5);//set Photo's rating to corresponding rating
                myViewer.setRatingButton(myViewer.getPhoto(indexOfPic).getRating());  //select proper rating button 
            }
            
        }
    }


}
