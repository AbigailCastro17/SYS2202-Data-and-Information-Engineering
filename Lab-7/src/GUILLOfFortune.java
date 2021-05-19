import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class GUILLOfFortune extends JFrame {
	JLabel instructions;
	JButton submit;
	JTextArea enterArea;
	
	JLabel livesRemaining;
	JLabel lettersEntered;
	JLabel wordOutput;
	
	
	boolean[] lettersCorrect = {false, false, false, false, false};
	int lives;
	String password;
	ArrayList<String> letters;
	
	public static void main(String[] args) {
		new GUILLOfFortune();
	}
	
	public GUILLOfFortune()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		int width = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		this.setSize(width, height);
		
		password = "super";
		lives = 5;
		letters = new ArrayList<String>();
		
		instructions = new JLabel("<html>Enter a letter to see if it's in the word!</html>" );
		submit = new JButton("ENTER");
		enterArea = new JTextArea();
		livesRemaining = new JLabel("Lives: " + lives);
		lettersEntered = new JLabel("<html>Letters Entered: " + letters + "</html>");
		wordOutput = new JLabel("_ _ _ _ _");
		wordOutput.setFont(new Font("Times", Font.BOLD, 20));
		
		instructions.setSize(23*width/60, height/6);
		submit.setSize(width/3, 50);
		enterArea.setSize(width/3, 50);
		livesRemaining.setSize(width/4, height/12);
		lettersEntered.setSize(width/3, height/3);
		wordOutput.setSize(width/2, height/6);
		
		instructions.setLocation(width/15, 1);
		submit.setLocation(width/2, height/2);
		enterArea.setLocation(width/6, height/2);
		livesRemaining.setLocation(11*width/15, 1);
		lettersEntered.setLocation(17*width/30, height/20);
		wordOutput.setLocation(width/3, height/3);
		
		submit.addActionListener(new passwordButtonListener());
		submit.setActionCommand("click");
		
		this.add(instructions);
		this.add(submit);
		this.add(enterArea);
		this.add(livesRemaining);
		this.add(lettersEntered);
		this.add(wordOutput);
		
		this.add(new JLabel());
		this.setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private class passwordButtonListener implements ActionListener
	{
//        public void actionPerformed (ActionEvent e) {
//            if (e.getActionCommand().contentEquals("click")) {
//                lives--;
//                wordOutput.setText("hi");
//            }
//            wordOutput.setVisible(false);
//            wordOutput.setVisible(true);
//            //livesRemaining.setVisible(true);
//        }

		@Override
		public void actionPerformed(ActionEvent arg0) {
            // TODO: Implement the action taken by the button.
		    if (arg0.getActionCommand().contentEquals("click")) {
                if (enterArea.getText()!=null && enterArea.getText().length()==1){
                    if (!letters.contains(enterArea.getText())) {
                        letters.add(enterArea.getText());
                        String guess = enterArea.getText();
                        int i = 0;
                        while(i < password.length()-1) {
                            if (password.indexOf(enterArea.getText())!=-1) {
                                i=password.indexOf(guess, i+1);
                                if (i==0) {
                                    List<String> al = new ArrayList<String>();
                                    al = Arrays.asList(wordOutput.getText());
                                    al.set(0,password.substring(i,i+1));
                                    String output = String.join("",al);
                                    wordOutput.setText(output);
                                    letters.add(guess);
                                }
                                else {
                                    List<String> al = new ArrayList<String>();
                                    al = Arrays.asList(wordOutput.getText());
                                    al.set(i*2, password.substring(i,i+1));
                                    String output = String.join("",al);
                                    wordOutput.setText(output);
                                    letters.add(guess);
                                }
                            }
                            else {
                                if(lives!=0) {
                                    lives--; 
                                    letters.add(guess);
                                    instructions.setText("Sorry, wrong letter :(");
                                }
                                else {
                                    instructions.setText("You're out of lives!");
                                }
                            }
                        }
                    } 
                    else {
                        instructions.setText("I'm sorry, that letter has been entered already. Try again");
                    }
                }
                else {
                    instructions.setText("Please input one letter.");
                }
                //wordOutput.setVisible(true);
            }  
		}
	}

}
