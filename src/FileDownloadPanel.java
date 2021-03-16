import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import filesdb.FileDB;

public class FileDownloadPanel {

	public JLabel fileName;
	public JLabel fileAuthor;
	public JLabel fileGrade;
	public JButton downloadFile;
	public JButton rateFile;
	public File thisFile;
	public JTextField fileNameField = new JTextField(), dir = new JTextField();
	
	public FileDownloadPanel(FileDB file) {
		fileName=new JLabel(file.fileName);
		fileAuthor=new JLabel(file.fileAuthor);
		fileGrade=new JLabel(Double.toString(file.fileGrade));
		downloadFile=new JButton("download");
		rateFile=new JButton("rate");
		thisFile=file.thisFile;
	}
	
	ActionListener downloadFileListener=new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			JFileChooser jfc=new JFileChooser();
			int rVal = jfc.showSaveDialog(null);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				fileNameField.setText(thisFile.getName());
				dir.setText(jfc.getCurrentDirectory().toString());
			}

		}
		
	};
}
