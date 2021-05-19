import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyWindow extends JFrame {
    
    public JLabel instructionLabel;
    public JTextField inputTextField;
    public JLabel resultLabel;
    public JButton actionButton;
    
    private FlowLayout layout = new FlowLayout();
    
    public static void main(String[] args) {
        MyWindow gui1 = new MyWindow();
        gui1.createAndShowGUI();

    }

    private static void createAndShowGUI() {
        MyWindow frame = new MyWindow();
        frame.setTitle("My First GUI Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//what the 'x' button does
        frame.setSize(450,120);
        frame.setLocationRelativeTo(null);
        frame.addComponentsToPane(frame.getContentPane());
        
        //frame.pack(); //puts it in a line?
        frame.setVisible(true);
    }
    
    public void addComponentsToPane(Container pane) {
        JPanel panel = new JPanel();
        
        panel.setLayout(layout);
        layout.setAlignment(FlowLayout.CENTER);
        
        instructionLabel = new JLabel ("Enter distance in kilometers: ");
        panel.add(instructionLabel, BorderLayout.NORTH);
        
        inputTextField = new JTextField(20);
        panel.add(inputTextField, BorderLayout.NORTH);
        
        actionButton =new JButton("Click me to convert to miles!");
        actionButton.setActionCommand("click");
        actionButton.addActionListener(new ButtonListener());
        panel.add(actionButton, BorderLayout.WEST);
        
        resultLabel = new JLabel("");
        resultLabel.setVisible(false);
        panel.add(resultLabel, BorderLayout.SOUTH); // SOUTH = add to the "bottom" of the panel

        // add this panel to the content pane
        pane.add(panel);
    }
    
    class ButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent e) {
            if (e.getActionCommand().contentEquals("click")) {
                if (MyWindow.isNumeric(inputTextField.getText())){
                    double km = Double.parseDouble(inputTextField.getText());
                    double miles = km/1.609344;
                    resultLabel.setText(Double.toString(miles) + " miles"); 
                }
                else {
                    resultLabel.setText("Please input a number.");
                }
            }
            resultLabel.setVisible(true);
        }
    }

    
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Double.parseDouble(str);
        } 
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    

}
