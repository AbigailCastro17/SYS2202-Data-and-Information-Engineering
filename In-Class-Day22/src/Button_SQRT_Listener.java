// import the swing library and awt resources
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

//extend JFrame
public class Button_SQRT_Listener extends JFrame {
    // set form controls as instance variables
    private JButton actionButton;
    private JLabel infoLabel;
    private JLabel resultLabel;
    private JTextField inputTextField;

    // a layout object determines how controls are drawn on the panel
    private FlowLayout layout = new FlowLayout();

    // the main method invokes the createAndShowGUI method
    // using a runnable object
    public static void main(String[] args) {
        createAndShowGUI(); // call this method to get started
    } // --- END main ---

    // createAndShowGUI creates an instance of the current
    // class and displays it
    private static void createAndShowGUI() {
        //Create and set up the window. 
        Button_SQRT_Listener frame = new Button_SQRT_Listener();
        frame.setTitle("Calculating the Square Root");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 120);
        //This will center the JFrame in the middle of the screen
        frame.setLocationRelativeTo(null);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        //frame.pack();
        frame.setVisible(true); // so that all things show up, set visibility to "true"!
    } // --- END createAndShowGUI ---

    // Where most of the work takes place. Creating components and adding them to a panel and then onto the content pane
    // Inner Class "ButtonListener" is here, so that the button can perform some tasks after it has been clicked
    public void addComponentsToPane(Container pane) { //takes an item of type Container 

        // create an INNER CLASS for the button action ~ implements ActionListener
        // tasks to perform after button click are put in actionPerformed() method
        class ButtonListener implements ActionListener { // ** INNER CLASS **
            public void actionPerformed(ActionEvent e) {
                double userInput = 0.0;
                double sqrtOfInput = 0.0;

                DecimalFormat theOutput = new DecimalFormat("#,##0.00"); // format output

                if (e.getActionCommand().equals("click")) {
                    // Get what the user entered, convert it to a double
                    userInput = Double.parseDouble(inputTextField.getText()); // using getText() method
                    // Calculate the square root of the number
                    sqrtOfInput = Math.sqrt(userInput);
                    // Display the result
                    resultLabel.setText("Result: "+theOutput.format(sqrtOfInput)); // Set the restultLabel text (square root of the number) 
                    // Set visibility of this label to be true (visible) ~ remember, it was invisible at first!
                    resultLabel.setVisible(true);
                }
            } // END actionPerformed
        } // END class ButtonListener

        // add components to a panel
        JPanel panel = new JPanel();

        // set options
        panel.setLayout(layout);
        layout.setAlignment(FlowLayout.CENTER);

        // initialize and add elements to the frame
        // ----------------------------------------

        // label - prompt
        infoLabel = new JLabel("Enter a number to get it's square-root:");
        panel.add(infoLabel, BorderLayout.NORTH);   

        // text field
        inputTextField = new JTextField(10);
        inputTextField.setText("25"); // puts a default value into the text field
        panel.add(inputTextField, BorderLayout.NORTH);

        // button
        actionButton = new JButton("SQRT");
        actionButton.setActionCommand("click");
        actionButton.addActionListener(new ButtonListener()); // add ActionListener
        // Task to perform once button is clicked is found in "actionPerformed" method
        panel.add(actionButton, BorderLayout.NORTH);

        // label - result (notice, it only shows up AFTER the button is pressed!)
        resultLabel = new JLabel("Result: ");
        resultLabel.setVisible(false); // initially invisible
        panel.add(resultLabel, BorderLayout.SOUTH);

        // add this panel to the content pane
        pane.add(panel);
    } // --- END addComponentsToPane ---
}