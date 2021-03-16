

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javadb.Client;
import javadb.InfoFromId;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class MainMenu extends JFrame {

	public int menuUserId;
	
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    
    JPanel mainPanel;
    
    JLabel mainTitle;
    
    JButton searchFiles;
    JButton myFiles;
    JButton addFiles;
    JButton accountInfo;
    
    public MainMenu() throws HeadlessException{
    	
    	super("Login Panel");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setExtendedState(Frame.MAXIMIZED_BOTH);
    	
    	mainPanel=new JPanel();
    	Dimension expectedDimension = new Dimension(600,800);
    	
    	mainPanel.setPreferredSize(expectedDimension);
    	mainPanel.setMaximumSize(expectedDimension);
    	mainPanel.setMinimumSize(expectedDimension);
    	
    	mainPanel.setLayout(new GridBagLayout());
    	
        GridBagConstraints constraint = new GridBagConstraints();
    	
    	if (shouldFill) {
            constraint.fill = GridBagConstraints.VERTICAL;
        }
    	if (shouldWeightX) {
            constraint.weightx = 1;
        }
    	
    	mainTitle=new JLabel("      Main menu");
    	constraint.fill = GridBagConstraints.VERTICAL;
    	constraint.ipady = 20;
    	constraint.ipadx = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 0;
    	mainPanel.add(mainTitle, constraint);
    	
    	searchFiles=new JButton("Search Files");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 1;
    	searchFiles.addActionListener(goToSearch);
    	mainPanel.add(searchFiles, constraint);
    	
    	myFiles=new JButton("My Files");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.insets = new Insets(10,0,0,0);
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 2;
    	myFiles.addActionListener(goToMyFiles);
    	mainPanel.add(myFiles, constraint);
    	
    	addFiles=new JButton("Add Files");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.insets = new Insets(10,0,0,0);
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 3;
    	addFiles.addActionListener(goToAddFiles);
    	mainPanel.add(addFiles, constraint);
    	
    	accountInfo=new JButton("Account Info");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.insets = new Insets(10,0,0,0);
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 4;
    	accountInfo.addActionListener(goToAccountInfo);
    	mainPanel.add(accountInfo, constraint);
    	
    	Box centerScreen = new Box(BoxLayout.Y_AXIS);

        centerScreen.add(Box.createVerticalGlue());
        centerScreen.add(mainPanel);     
        centerScreen.add(Box.createVerticalGlue());
        add(centerScreen);
    	
    	
    }
    
    
    public static void main(String[] arg0){
    	MainMenu mainFrame=new MainMenu();
    	mainFrame.setVisible(true);
    }
    
    ActionListener goToSearch=new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		SearchingLog searchMenu=new SearchingLog();
        	searchMenu.setVisible(true);
        	dispose();
    	}
    };
    
    ActionListener goToMyFiles=new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		MyFiles myFiles=new MyFiles();
        	myFiles.setVisible(true);
        	dispose();
    	}
    };
    
    ActionListener goToAccountInfo=new ActionListener(){

		public void actionPerformed(ActionEvent e){
    		try {
    			
				
				InfoFromId info=new InfoFromId();
			    Client client=info.returnInfo(menuUserId);
			    AccountInfo accInfo=new AccountInfo(client);
	        	accInfo.setVisible(true);
	        	dispose();
			} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	}
    };
    
    ActionListener goToAddFiles=new ActionListener(){
    	public void actionPerformed(ActionEvent e){
			try {
				InfoFromId info=new InfoFromId();
	    		Client client;
				client = info.returnInfo(menuUserId);
				AddFiles addFiles=new AddFiles(client);
	        	addFiles.setVisible(true);
	        	dispose();
	        	
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	}
    };
}
