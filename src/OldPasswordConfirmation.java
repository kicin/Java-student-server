import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class OldPasswordConfirmation extends JFrame {

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
	
    JPanel loginPanel;
    JPanel backPanel;
	
    JLabel OldPasswordTitle;
    JLabel OldPasswordAsk;
    
    JTextArea OldPasswordPass;
	
    JButton OldPasswordConfirmed;
    JButton backButton;
   
    public OldPasswordConfirmation() throws HeadlessException{
    
    	super("Old Password Confirmation");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setExtendedState(Frame.MAXIMIZED_BOTH);
    
    	loginPanel=new JPanel();
    	Dimension expectedDimension = new Dimension(400,200);
    	
    	loginPanel.setPreferredSize(expectedDimension);
    	loginPanel.setMaximumSize(expectedDimension);
    	loginPanel.setMinimumSize(expectedDimension);
    	
    	loginPanel.setLayout(new GridBagLayout());
    	
    	GridBagConstraints constraint = new GridBagConstraints();
    	
    	if (shouldFill) {
            constraint.fill = GridBagConstraints.HORIZONTAL;
        }
    }
}