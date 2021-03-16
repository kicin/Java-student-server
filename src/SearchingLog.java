import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import filesdb.FileDB;
import filesdb.SearchFiles;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;



public class SearchingLog extends JFrame {

	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
	
	JPanel searchingPanel;
	JPanel backPanel;
	JPanel listPanel=new JPanel();
	
	JTextArea searchField;
	JLabel searchAsk;
	JButton searchConfirmed;
	
	JLabel listTitle;
	
	JLabel fileName;
	JLabel fileAuthor;
	JLabel fileGrade;
	JButton fileDownload;
	JButton fileRate;
	
	JButton backButton;
	
	public SearchingLog() throws HeadlessException{
		
		
		
		super("Login Panel");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setExtendedState(Frame.MAXIMIZED_BOTH);
    	setLayout(new BorderLayout());
    	
		searchingPanel=new JPanel();
		
        Dimension expectedDimension = new Dimension(1200,200);
    	
        searchingPanel.setPreferredSize(expectedDimension);
        searchingPanel.setMaximumSize(expectedDimension);
        searchingPanel.setMinimumSize(expectedDimension);
    	
		searchingPanel.setLayout(new GridBagLayout());
		
        GridBagConstraints constraint = new GridBagConstraints();
    	
    	if (shouldFill) {
            constraint.fill = GridBagConstraints.VERTICAL;
        }
    	if (shouldWeightX) {
            constraint.weightx = 1;
        }
		
		searchAsk=new JLabel("Search Files: ");
    	constraint.fill = GridBagConstraints.VERTICAL;
    	constraint.insets = new Insets(0,10,0,0);
    	constraint.ipady = 1;
    	constraint.weightx = 0.0;
    	constraint.gridx = 0;
    	constraint.gridy = 0;
    	searchingPanel.add(searchAsk, constraint);
		
    	searchField=new JTextArea();
    	searchField.setPreferredSize(new Dimension(200,10));
    	constraint.fill = GridBagConstraints.VERTICAL;
    	constraint.anchor = GridBagConstraints.CENTER; 
    	constraint.ipady = 2;
    	constraint.ipadx = 200;
    	constraint.weightx = 0.0;
    	constraint.gridx = 1;
    	constraint.gridy = 0;
    	searchingPanel.add(searchField, constraint);
		
    	searchConfirmed=new JButton("Search");
    	constraint.fill = GridBagConstraints.VERTICAL; 
    	constraint.ipady = 5;
    	constraint.ipadx = 1;
    	constraint.gridx = 2;
    	constraint.gridy = 0;
    	searchConfirmed.addActionListener(searchFileListener);
    	searchingPanel.add(searchConfirmed, constraint);
    	
    	listTitle=new JLabel("Search results: ");
    	constraint.fill = GridBagConstraints.VERTICAL;
    	constraint.insets = new Insets(20,10,0,0);
    	constraint.ipady = 1;
    	constraint.gridx = 0;
    	constraint.gridy = 1;
    	searchingPanel.add(listTitle, constraint);
    	
    	
    	Box northScreen = new Box(BoxLayout.Y_AXIS);

        northScreen.add(Box.createVerticalGlue());
        northScreen.add(searchingPanel);     
        add(northScreen,BorderLayout.NORTH);

        backPanel=new JPanel();
		
        Dimension expectedBackDimension = new Dimension(500,100);
    	
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
	
	ActionListener searchFileListener=new ActionListener() {
		public void actionPerformed(ActionEvent e){
        	
			Dimension expectedDimension = new Dimension(500,300);

			
			listPanel.setPreferredSize(expectedDimension);
	        searchingPanel.setMaximumSize(expectedDimension);
	        searchingPanel.setMinimumSize(expectedDimension);
	    	
			searchingPanel.setLayout(new GridBagLayout());
			
	        GridBagConstraints constraint = new GridBagConstraints();
	    	
	    	if (shouldFill) {
	            constraint.fill = GridBagConstraints.VERTICAL;
	        }
	    	if (shouldWeightX) {
	            constraint.weightx = 1;
	        }

			
			SearchFiles search=new SearchFiles();
			ArrayList<FileDB> filesList=new ArrayList<FileDB>();
			
			try {
				filesList=search.returnFiles(searchField.getText());
				if(filesList!=null) {
    				for(int i=0;i<filesList.size();i++) {
    					Component[] componentList =listPanel.getComponents();

    					for(Component c : componentList){

    					        listPanel.remove(c);
    					}
    					revalidate();
    					
    					
    					FileDB file=filesList.get(i);
    					FileDownloadPanel filePanel=new FileDownloadPanel(file);
    					
    					fileName=filePanel.fileName;
    			    	constraint.fill = GridBagConstraints.HORIZONTAL;
    			    	constraint.ipady = 10;
    			    	constraint.ipadx = 10;
    			    	constraint.weightx = 0.0;
    			    	constraint.gridwidth=2;
    			    	constraint.gridx = 0;
    			    	constraint.gridy =i;
    			    	listPanel.add(fileName, constraint);
    			    	
    			    	fileAuthor=filePanel.fileAuthor;
    			    	constraint.fill = GridBagConstraints.HORIZONTAL;
    			    	constraint.ipady = 10;
    			    	constraint.ipadx = 10;
    			    	constraint.weightx = 0.0;
    			    	constraint.gridwidth=2;
    			    	constraint.gridx = 3;
    			    	constraint.gridy =i;
    			    	listPanel.add(fileAuthor, constraint);
    			    	
    			    	fileGrade=filePanel.fileGrade;
    			    	constraint.fill = GridBagConstraints.HORIZONTAL;
    			    	constraint.ipady = 10;
    			    	constraint.ipadx = 10;
    			    	constraint.weightx = 0.0;
    			    	constraint.gridwidth=2;
    			    	constraint.gridx = 6;
    			    	constraint.gridy =i;
    			    	listPanel.add(fileGrade, constraint);
    			    	
    			    	fileDownload=filePanel.downloadFile;
    			    	constraint.fill = GridBagConstraints.HORIZONTAL;
    			    	constraint.ipady = 10;
    			    	constraint.ipadx = 10;
    			    	constraint.weightx = 0.0;
    			    	constraint.gridwidth=2;
    			    	constraint.gridx = 9;
    			    	constraint.gridy =i;
    			    	listPanel.add(fileDownload, constraint);
    			    	
    			    	fileRate=filePanel.rateFile;
    			    	constraint.fill = GridBagConstraints.HORIZONTAL;
    			    	constraint.ipady = 10;
    			    	constraint.ipadx = 10;
    			    	constraint.weightx = 0.0;
    			    	constraint.gridwidth=2;
    			    	constraint.gridx = 12;
    			    	constraint.gridy = i;
    			    	listPanel.add(fileRate, constraint);
    			    	
    			    	listTitle=new JLabel("Search results: ");
    			    	constraint.fill = GridBagConstraints.VERTICAL;
    			    	constraint.insets = new Insets(20,10,0,0);
    			    	constraint.ipady = 1;
    			    	constraint.gridx = 0;
    			    	constraint.gridy = 1;
    			    	
    			    	Box midScreen = new Box(BoxLayout.Y_AXIS);

        				midScreen.add(Box.createVerticalGlue());
        		        midScreen.add(listPanel);     
        		        add(midScreen,BorderLayout.CENTER);
    				}
    				
    				
    		        
    			}
				
				
				
			} catch (ClassNotFoundException | IOException | SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
    		
    	}
	};
	
	
	 ActionListener goToMainMenu=new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		MainMenu mainMenu=new MainMenu();
	        	mainMenu.setVisible(true);
	        	dispose();
	    	}
	    };
	
	public static void main(String[] arg0){
    	SearchingLog searchMenu=new SearchingLog();
    	searchMenu.setVisible(true);
    }
}
