

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FilesConfirmation extends JFrame {
	
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
	
	JPanel listPanel;
    JPanel backPanel;
    
    JLabel fileName;
	JLabel fileAddDate;
	JLabel fileOwnerName;
	
    JButton confirmButton;
	JButton backButton;
	
	
public FilesConfirmation() throws HeadlessException{
	
	super("Files to confirm");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setExtendedState(Frame.MAXIMIZED_BOTH);
	setLayout(new BorderLayout());
	
	listPanel=new JPanel();
	
	Dimension expectedDimension = new Dimension(1200,300);
	
    listPanel.setPreferredSize(expectedDimension);
    listPanel.setMaximumSize(expectedDimension);
    listPanel.setMinimumSize(expectedDimension);
	
	listPanel.setLayout(new GridBagLayout());
	
	GridBagConstraints constraint = new GridBagConstraints();
	
	if (shouldFill) {
        constraint.fill = GridBagConstraints.VERTICAL;
    }
	if (shouldWeightX) {
        constraint.weightx = 1;
    }
	
	fileName=new JLabel("name");
	constraint.fill = GridBagConstraints.VERTICAL;
	constraint.insets = new Insets(0,10,0,0);
	constraint.ipady = 10;
	constraint.weightx = 0.0;
	constraint.gridx = 0;
	constraint.gridy = 0;
	listPanel.add(fileName, constraint);
	
	fileOwnerName=new JLabel("Owner's name");
	constraint.fill = GridBagConstraints.VERTICAL;
	constraint.insets = new Insets(0,10,0,0);
	constraint.ipady = 10;
	constraint.weightx = 0.0;
	constraint.gridx = 2;
	constraint.gridy = 0;
	listPanel.add(fileOwnerName, constraint);
	
	fileAddDate=new JLabel("DD/MM/RRRR");
	constraint.fill = GridBagConstraints.VERTICAL;
	constraint.insets = new Insets(0,10,0,0);
	constraint.ipady = 10;
	constraint.weightx = 0.0;
	constraint.gridx = 4;
	constraint.gridy = 0;
	listPanel.add(fileAddDate, constraint);
	
	confirmButton=new JButton("Confirm");
	constraint.fill = GridBagConstraints.VERTICAL;
	constraint.anchor = GridBagConstraints.FIRST_LINE_END; 
	constraint.ipady = 10;
	constraint.weightx = 0.0;
	constraint.gridwidth=2;
	constraint.gridx = 6;
	constraint.gridy = 0;
	listPanel.add(confirmButton, constraint);
	
	
	Box northScreen = new Box(BoxLayout.Y_AXIS);

    northScreen.add(Box.createVerticalGlue());
    northScreen.add(listPanel);     
    northScreen.add(Box.createVerticalGlue());
    add(northScreen,BorderLayout.NORTH);

    backPanel=new JPanel();
	
    Dimension expectedBackDimension = new Dimension(900,100);
	
    backPanel.setPreferredSize(expectedBackDimension);
    backPanel.setMaximumSize(expectedBackDimension);
    backPanel.setMinimumSize(expectedBackDimension);
	
	backPanel.setLayout(new GridBagLayout());
	
    GridBagConstraints backconstraint = new GridBagConstraints();
	
	if (shouldFill) {
        backconstraint.fill = GridBagConstraints.HORIZONTAL;
    }
	if (shouldWeightX) {
        backconstraint.weightx = 0;
    }
	
	backButton=new JButton("Back to main menu");
	backconstraint.fill = GridBagConstraints.VERTICAL;
	backconstraint.anchor = GridBagConstraints.FIRST_LINE_END; 
	backconstraint.ipady = 10;
	backconstraint.weightx = 0.0;
	backconstraint.gridwidth=2;
	backconstraint.gridx = 0;
	backconstraint.gridy = 0;
	backButton.addActionListener(goToAdminMainMenu);
	backPanel.add(backButton, constraint);
	
	Box southScreen = new Box(BoxLayout.X_AXIS);

	southScreen.add(Box.createVerticalGlue());
	southScreen.add(backPanel);     
	southScreen.add(Box.createVerticalGlue());
    add(southScreen,BorderLayout.SOUTH);
    
}

ActionListener goToAdminMainMenu=new ActionListener(){
	public void actionPerformed(ActionEvent e){
		AdminMenu mainMenu=new AdminMenu();
    	mainMenu.setVisible(true);
    	dispose();
	}
};

public static void main(String[] arg0){
FilesConfirmation filesMenu=new FilesConfirmation();
filesMenu.setVisible(true);
}
	
	
}