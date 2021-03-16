import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OldPasswordError extends JFrame {

	JPanel errorLoginPanel;
	
	JLabel error;
	
	public OldPasswordError () throws HeadlessException{
		
	super("Error");
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setSize(300,100);
	setLocationRelativeTo(null);
	
	errorLoginPanel=new JPanel();
	Dimension expectedDimension = new Dimension(300,150);
	
	errorLoginPanel.setPreferredSize(expectedDimension);
	errorLoginPanel.setMaximumSize(expectedDimension);
	errorLoginPanel.setMinimumSize(expectedDimension);
	errorLoginPanel.setLayout(new FlowLayout());
	
	error=new JLabel("INCORRECT PASSWORD");
	errorLoginPanel.add(error);
	
	Box centerScreen = new Box(BoxLayout.Y_AXIS);

    centerScreen.add(Box.createVerticalGlue());
    centerScreen.add(errorLoginPanel);     
    centerScreen.add(Box.createVerticalGlue());
    add(centerScreen);
	}
	
	public static void main(String[] args){
		OldPasswordError errorFrame=new OldPasswordError();
		errorFrame.setVisible(true);
	} 
	
}
