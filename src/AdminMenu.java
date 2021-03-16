
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javadb.Client;
import javadb.InfoFromId;

public class AdminMenu extends JFrame {
	
	public int adminMenuUserId;
	
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    
    JPanel mainPanel;
    
    JLabel mainTitle;
    
    JButton addCostumer;
    JButton FilesConfirmation;
    JButton searchFiles;
    JButton myFiles;
    JButton addFiles;
    JButton accountInfo;

    public AdminMenu() throws HeadlessException{
    	
    	super("Admin menu");
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
    	
    	
    	mainTitle=new JLabel("    Admin menu");
    	constraint.fill = GridBagConstraints.VERTICAL;
    	constraint.ipady = 20;
    	constraint.ipadx = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 0;
    	constraint.gridx = 0;
    	mainPanel.add(mainTitle, constraint);
    	
    	searchFiles=new JButton("Search Files");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 1;
    	constraint.gridx = 0;
    	searchFiles.addActionListener(goToSearch);
    	mainPanel.add(searchFiles, constraint);
    	
    	myFiles=new JButton("My Files");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.insets = new Insets(10,0,0,0);
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 2;
    	constraint.gridx = 0;
    	myFiles.addActionListener(goToMyFiles);    	
    	mainPanel.add(myFiles, constraint);
    	
    	addFiles=new JButton("Add Files");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.insets = new Insets(10,0,0,0);
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 3;
    	constraint.gridx = 0;
    	addFiles.addActionListener(goToAddFiles);           
    	mainPanel.add(addFiles, constraint);
    	
    	accountInfo=new JButton("Account Info");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.insets = new Insets(10,0,0,0);
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 4;
    	constraint.gridx = 0;
    	accountInfo.addActionListener(goToAccountInfo);
    	mainPanel.add(accountInfo, constraint);
    	
    	addCostumer=new JButton("Add Users");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 5;
    	constraint.gridx = 0;
    	addCostumer.addActionListener(goToAdd);
    	mainPanel.add(addCostumer, constraint);
    	
    	FilesConfirmation=new JButton("Confirm Files");
    	constraint.fill = GridBagConstraints.BOTH;
    	constraint.ipady = 20;
    	constraint.weightx = 0.0;
    	constraint.gridy = 6;
    	constraint.gridx = 0;
    	FilesConfirmation.addActionListener(goToConfirm);
    	mainPanel.add(FilesConfirmation, constraint);
    	
    	Box centerScreen = new Box(BoxLayout.Y_AXIS);

        centerScreen.add(Box.createVerticalGlue());
        centerScreen.add(mainPanel);     
        centerScreen.add(Box.createVerticalGlue());
        add(centerScreen);
    	
    }
    
    
    public static void main(String[] arg0){
    	AdminMenu mainFrame=new AdminMenu();
    	mainFrame.setVisible(true);
    }
    
    ActionListener goToAdd=new ActionListener(){ 
    	public void actionPerformed(ActionEvent e){
    		AddClientPanel searchMenu=new AddClientPanel();
        	searchMenu.setVisible(true);
        	dispose();
    	}
    };
    
    ActionListener goToConfirm=new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    	}
    };
    
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
    		AccountInfo accInfo;
			try {
				InfoFromId info=new InfoFromId();
			    Client client=info.returnInfo(adminMenuUserId);
			    AccountInfo adminAccInfo=new AccountInfo(client);
	        	adminAccInfo.setVisible(true);
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
				client = info.returnInfo(adminMenuUserId);
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
