import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import javadb.AddClient;
import javadb.CheckIfLoginExists;
import javadb.IdFromLogin;
import javadb.UpdateToAdmin;

public class AddClientPanel extends JFrame {


		final static boolean shouldFill = true;
	    final static boolean shouldWeightX = true;
		
		
	    JPanel loginPanel;
		
	    JButton registrationConfirmed;
	    JButton backButton;
	    
	    JTextArea loginPass;
	    JTextArea passwordPass;
	    
	    JLabel title;
	    JLabel loginAsk;
	    JLabel passwordAsk;
	    
	    JRadioButton adminStatus;
	    JRadioButton userStatus;
	    
	    public String status;
	    
	    public AddClientPanel() throws HeadlessException{
	    	
	    	super("Login Panel");
	    	setDefaultCloseOperation(EXIT_ON_CLOSE);
	    	setExtendedState(Frame.MAXIMIZED_BOTH); //rozmiar okna=rozmiar ekranu
	    	
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
	    	if (shouldWeightX) {
	            constraint.weightx = 0.5;
	        }
	    	
	    	
	    	title=new JLabel("Enter registration data ");
	    	constraint.fill = GridBagConstraints.HORIZONTAL;
	    	constraint.gridwidth = 2;
	    	constraint.ipady = 20;
	    	constraint.weightx = 0.0;
	    	constraint.gridx = 0;
	    	constraint.gridy = 0;
	    	loginPanel.add(title, constraint);
	    	
	    	loginAsk=new JLabel("Login: ");
	    	constraint.fill = GridBagConstraints.HORIZONTAL;
	    	constraint.gridwidth = 1;
	    	constraint.ipady = 0;
	    	constraint.gridx = 0;
	    	constraint.gridy = 1;
	    	loginPanel.add(loginAsk, constraint);
	    	
	    	passwordAsk=new JLabel("Password: ");
	    	constraint.fill = GridBagConstraints.HORIZONTAL;
	    	constraint.gridx = 0;
	    	constraint.gridy = 2;
	    	loginPanel.add(passwordAsk, constraint);
	    	
	    	loginPass=new JTextArea();
	    	constraint.fill = GridBagConstraints.HORIZONTAL;
	    	constraint.gridx = 1;
	    	constraint.gridy = 1;
	    	loginPanel.add(loginPass, constraint);
	    	
	    	passwordPass=new JTextArea();
	    	constraint.fill = GridBagConstraints.HORIZONTAL;
	    	constraint.insets = new Insets(10,0,0,0);
	    	constraint.gridx = 1;
	    	constraint.gridy = 2;
	    	loginPanel.add(passwordPass, constraint);
	    	
	    	userStatus=new JRadioButton("User");
	    	userStatus.setSelected(true);
	    	userStatus.addActionListener(userStatusButton);
	    	constraint.fill = GridBagConstraints.VERTICAL;
	    	constraint.anchor = GridBagConstraints.LINE_START; //bottom of space
	    	constraint.gridx = 0;
	    	constraint.gridy = 3;
	    	loginPanel.add(userStatus, constraint);
	    	
	    	adminStatus=new JRadioButton("Admin");
	    	adminStatus.setSelected(false);
	    	adminStatus.addActionListener(adminStatusButton);
	    	constraint.fill = GridBagConstraints.VERTICAL;
	    	constraint.anchor = GridBagConstraints.LINE_END; //bottom of space
	    	constraint.gridx = 1;
	    	constraint.gridy = 3;
	    	loginPanel.add(adminStatus, constraint);
	    	
	    	registrationConfirmed=new JButton("Confirm");
	    	registrationConfirmed.addActionListener(confirmButton);
	    	constraint.fill = GridBagConstraints.HORIZONTAL;
	    	constraint.insets = new Insets(10,0,0,0);
	    	constraint.gridwidth = 2;
	    	constraint.weightx = 0.0;
	    	constraint.gridx = 0;
	    	constraint.gridy = 4;
	    	loginPanel.add(registrationConfirmed, constraint);
	    	
	    	backButton=new JButton("Back to main menu");
	    	backButton.addActionListener(backToMainMenu);
	    	constraint.fill = GridBagConstraints.HORIZONTAL;
	    	constraint.insets = new Insets(10,0,0,0);
	    	constraint.gridwidth = 2;
	    	constraint.weightx = 0.0;
	    	constraint.gridx = 0;
	    	constraint.gridy = 5;
	    	loginPanel.add(backButton, constraint);
	    	
	    	Box centerScreen = new Box(BoxLayout.Y_AXIS);

	        centerScreen.add(Box.createVerticalGlue());
	        centerScreen.add(loginPanel);     
	        centerScreen.add(Box.createVerticalGlue());
	        add(centerScreen);
	    	
	        ButtonGroup group = new ButtonGroup();
			group.add(userStatus);
			group.add(adminStatus);
	        
	    }
	    
        ActionListener userStatusButton=new ActionListener(){
	    	
	    	public void actionPerformed(ActionEvent e){

	    	status="user";
	    	adminStatus.setSelected(false);

	    	}
	    	
	    };
	    
        ActionListener adminStatusButton=new ActionListener(){
	    	
	    	public void actionPerformed(ActionEvent e){

	    	status="admin";
	    	userStatus.setSelected(false);

	    	}
	    	
	    };
	    
        ActionListener backToMainMenu=new ActionListener(){
	    	
	    	public void actionPerformed(ActionEvent e){

	    		AdminMenu mainMenu=new AdminMenu();
	        	mainMenu.setVisible(true);
	        	dispose();

	    	}
	    	
	    };
	    
	    ActionListener confirmButton=new ActionListener(){
	    	
	    	public void actionPerformed(ActionEvent e){

	    	CheckIfLoginExists ifExists=new CheckIfLoginExists();
	    	try {
				boolean free=ifExists.loginFree(loginPass.getText());
				if(free==true) {
			    	AddClient newOne=new AddClient(loginPass.getText(),passwordPass.getText());
			    	try {
						newOne.adding(loginPass.getText(),passwordPass.getText());
					} catch (ClassNotFoundException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
			    	
			    	if(status.contentEquals("admin")) {
			    		IdFromLogin newOneId=new IdFromLogin();
			    		int id=0;
			    		try {
							id=newOneId.returnId(loginPass.getText());
							UpdateToAdmin updateNewOne=new UpdateToAdmin();
							updateNewOne.update(id);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    	}

			    	AdminMenu mainMenu=new AdminMenu();
		        	mainMenu.setVisible(true);
		        	dispose();
			    	
			    	
			    	}
		            else {
		            	JOptionPane.showMessageDialog(null, "This login already exists!");
			    	}
			} catch (ClassNotFoundException | SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
	    	
	    	
	    	}
	    	
	    };
}
