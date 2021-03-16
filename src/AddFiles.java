import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import filesdb.AddFile;
import filesdb.CheckIfNameExists;
import filesdb.FileDB;
import javadb.AddClient;
import javadb.Client;
import javadb.IdFromLogin;
import javadb.UpdateToAdmin;

import com.orsonpdf.PDFDocument;

public class AddFiles extends JFrame{
	
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
	
    String adressOfFile;
    JPanel addingPanel;
	JPanel backPanel;
	
	FileDB file=new FileDB();
	
	JTextArea NameField;
	
	JLabel FileTitle;
	
	JButton addButton;
	JButton backButton;
	
	public AddFiles(Client client) throws HeadlessException{
	
		super("Add File");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setExtendedState(Frame.MAXIMIZED_BOTH);
    	setLayout(new BorderLayout());
    	
		addingPanel=new JPanel();
		
        Dimension expectedDimension = new Dimension(1200,300);
    	
        addingPanel.setPreferredSize(expectedDimension);
        addingPanel.setMaximumSize(expectedDimension);
        addingPanel.setMinimumSize(expectedDimension);
    	
		addingPanel.setLayout(new GridBagLayout());
		
        GridBagConstraints constraint = new GridBagConstraints();
    	
    	if (shouldFill) {
            constraint.fill = GridBagConstraints.VERTICAL;
        }
    	if (shouldWeightX) {
            constraint.weightx = 1;
        }
		


		
    	addButton=new JButton("Click here to add file");
    	constraint.fill = GridBagConstraints.VERTICAL; 
    	constraint.ipady = 5;
    	constraint.ipadx = 1;
    	constraint.weightx = 0.0;
    	constraint.gridwidth=2;
    	constraint.gridx = 1000;
    	constraint.gridy = 0;
    	addButton.addActionListener(addingFile);
    	addingPanel.add(addButton, constraint);
    	
    	Box northScreen = new Box(BoxLayout.Y_AXIS);

        northScreen.add(Box.createVerticalGlue());
        northScreen.add(addingPanel);     
        northScreen.add(Box.createVerticalGlue());
        add(northScreen,BorderLayout.CENTER);

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
        
    	file.fileGrade=0.0;
		file.fileAuthor=client.getLogin();
	}
	
	
	ActionListener addingFile=new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			JFileChooser jfc;
			File choosenFile;
			
			
			jfc = new JFileChooser("open file");
			jfc.setFileFilter(new FileNameExtensionFilter("PDF (*.pdf)", "pdf"));
			jfc.showOpenDialog(null);
			choosenFile = jfc.getSelectedFile();

		    
		      
			if(choosenFile!=null){
				file.thisFile=choosenFile;
				file.fileName= choosenFile.getName();
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Something is wrong. Try Again.");
			}
			
			CheckIfNameExists newName=new CheckIfNameExists();
			
			try {
				
				boolean check=newName.nameFree(file.fileName);
				if(check==true) {
					try {
						AddFile addOne=new AddFile(file);
					}
					catch (ClassNotFoundException | SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "File with this name already exists");
				}
			} catch (ClassNotFoundException | SQLException e2) {
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
}