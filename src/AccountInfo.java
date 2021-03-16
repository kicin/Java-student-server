import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javadb.Client;
import javadb.InfoFromId;

public class AccountInfo extends JFrame {

	
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;

    JPanel Panel;
    JPanel backPanel;
    
	JLabel fileLogin;
	JLabel fileDate;
	JLabel login;
	JLabel date;
	JButton changePassword;
	
	JButton backButton;
	
	public AccountInfo(Client client) throws HeadlessException, ClassNotFoundException, SQLException{
		
	super("Account informations");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setExtendedState(Frame.MAXIMIZED_BOTH);
    setLayout(new BorderLayout());
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
    
    Panel = new JPanel();
	
	Dimension expectedDimension = new Dimension(1200,300);
	
	Panel.setPreferredSize(expectedDimension);
    Panel.setMaximumSize(expectedDimension);
    Panel.setMinimumSize(expectedDimension);
 	
	Panel.setLayout(new GridBagLayout());
	
	GridBagConstraints constraint = new GridBagConstraints();
	
	if (shouldFill) {
        constraint.fill = GridBagConstraints.VERTICAL;
    }
	if (shouldWeightX) {
        constraint.weightx = 1;
    }
	
	fileLogin=new JLabel("Your login:");
	constraint.fill = GridBagConstraints.VERTICAL;
	constraint.insets = new Insets(0,10,0,0);
	constraint.ipady = 10;
	constraint.weightx = 0.0;
	constraint.gridx = 0;
	constraint.gridy = 0;
	Panel.add(fileLogin, constraint);
	
    login=new JLabel(client.clientLogin);
	constraint.fill = GridBagConstraints.VERTICAL;
	constraint.insets = new Insets(0,10,0,0);
	constraint.ipady = 10;
	constraint.weightx = 10.0;
	constraint.gridx = 0;
	constraint.gridy = 2;
	Panel.add(login, constraint);
	
	fileDate=new JLabel("Date of account creation:");
	constraint.fill = GridBagConstraints.VERTICAL;
	constraint.insets = new Insets(0,10,0,0);
	constraint.ipady = 10;
	constraint.weightx = 0.0;
	constraint.gridx = 0;
	constraint.gridy = 3;
	Panel.add(fileDate, constraint);
	
	date=new JLabel(dateFormat.format(client.startDate));
	constraint.fill = GridBagConstraints.VERTICAL;
	constraint.insets = new Insets(0,10,0,0);
	constraint.ipady = 10;
	constraint.weightx = 0.0;
	constraint.gridx = 0;
	constraint.gridy = 4;
	Panel.add(date, constraint);
	
		
	changePassword=new JButton("Change Password");
	constraint.fill = GridBagConstraints.VERTICAL; 
	constraint.ipady = 5;
	constraint.ipadx = 1;
	constraint.weightx = 0.0;
	constraint.gridwidth=2;
	constraint.gridx = 0;
	constraint.gridy = 5;
	              
	Panel.add(changePassword, constraint);
	changePassword.addActionListener(goToChangePassword);  
	
	Box northScreen = new Box(BoxLayout.Y_AXIS);

    northScreen.add(Box.createVerticalGlue());
    northScreen.add(Panel);     
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
	backButton.addActionListener(goToMainMenu);
	backPanel.add(backButton, constraint);
	
	Box southScreen = new Box(BoxLayout.X_AXIS);

	southScreen.add(Box.createVerticalGlue());
	southScreen.add(backPanel);     
	southScreen.add(Box.createVerticalGlue());
    add(southScreen,BorderLayout.SOUTH);
    
	}
	
	ActionListener goToChangePassword=new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		OldPasswordConfirmation changePassword=new OldPasswordConfirmation();
        	changePassword.setVisible(true);
        	dispose();
    	}
    };
	
	
	ActionListener goToMainMenu=new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		MainMenu mainMenu=new MainMenu();
        	mainMenu.setVisible(true);
        	dispose();
    	}
    };

    
}