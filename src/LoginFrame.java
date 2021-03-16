
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javadb.CheckClient;
import javadb.CheckIfAdmin;
import javadb.IdFromLogin;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class LoginFrame extends JFrame {

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
	
	
    JPanel loginPanel;
	
    JButton loginConfirmed;
    
    JTextArea loginPass;
    JTextArea passwordPass;
    
    JLabel titleAsk;
    JLabel loginAsk;
    JLabel passwordAsk;
    
    
    public LoginFrame() throws HeadlessException{
    	
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
    	
    	
    	titleAsk=new JLabel("Please enter your login data");
    	constraint.fill = GridBagConstraints.HORIZONTAL;
    	constraint.gridwidth = 2;
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridx = 0;
    	constraint.gridy = 0;
    	loginPanel.add(titleAsk, constraint);
    	
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
    	
    	loginConfirmed=new JButton("Confirm");
    	loginConfirmed.addActionListener(confirmButton);
    	constraint.fill = GridBagConstraints.HORIZONTAL;
    	constraint.gridwidth = 2;
    	constraint.ipady = 2;
    	constraint.weightx = 0.0;
    	constraint.gridx = 0;
    	constraint.gridy = 3;
    	loginPanel.add(loginConfirmed, constraint);
    	
    	
    	Box centerScreen = new Box(BoxLayout.Y_AXIS);

        centerScreen.add(Box.createVerticalGlue());
        centerScreen.add(loginPanel);     
        centerScreen.add(Box.createVerticalGlue());
        add(centerScreen);
    	
        
        
    }
    
    ActionListener confirmButton=new ActionListener(){
    	
    	public void actionPerformed(ActionEvent e){
    		
    		boolean pass = false;
			try {
				pass = authorizationFunction();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		if(pass==false){
    		LoginError error=new LoginError();
			error.setVisible(true);
    		}
    		if(pass==true){
    			CheckIfAdmin checkStatus=new CheckIfAdmin();
    			IdFromLogin getId=new IdFromLogin();
    			int id;
				try {
					id = getId.returnId(loginPass.getText());
					boolean ifAdmin=checkStatus.check(id);
					if(ifAdmin==true) {
						AdminMenu menu=new AdminMenu();
		    			menu.setVisible(true);
		    			menu.adminMenuUserId=id;
		    			dispose();
					}
					else {
						MainMenu menu=new MainMenu();
		    			menu.setVisible(true);
		    			menu.menuUserId=id;
		    			dispose();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    		}

    	}
    	
    };
    

	
	public boolean authorizationFunction() throws ClassNotFoundException, SQLException{
		
		CheckClient client=new CheckClient();
		
		return client.loginCheck(loginPass.getText(),passwordPass.getText());
		
		
		
		
	}
	
	public static void main(String[] args){
		LoginFrame loginFrame=new LoginFrame();
		loginFrame.setVisible(true);
	} 
	
}
