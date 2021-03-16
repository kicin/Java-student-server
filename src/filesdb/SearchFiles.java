package filesdb;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchFiles {

	public SearchFiles() {
		
	}
	
	public  ArrayList<FileDB> returnFiles(String searchFileName) throws IOException, SQLException, ClassNotFoundException {
		
		ArrayList<FileDB> listOfFiles=new ArrayList<FileDB>();
		FileDB searchingFile=new FileDB();
		
		Connection conn = null;

		String DBUserName="sa";
		String DBPassword="aa";
		
		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	


			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
					"instance=SQLEXPRESS;databaseName=serverDB;";

			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);


			String SQLnew="SELECT fileID,fileAuthor,fileGrade,fileAccepted,pdfFile,fileName,fileGradesCounter FROM files WHERE fileName LIKE ?;";

			
			PreparedStatement preparedStatement =conn.prepareStatement(SQLnew);
			preparedStatement.setString(1,"%" + searchFileName +"%");

            ResultSet rs = preparedStatement.executeQuery();
			
			
			while(rs.next()){
				
				String acceptionCheck=rs.getString("fileAccepted");
				
				if(acceptionCheck.equalsIgnoreCase("accepted")) {
				
				searchingFile.fileId=rs.getInt("fileID");
				searchingFile.fileAuthor=rs.getString("fileAuthor");
				searchingFile.fileGrade=rs.getDouble("fileGrade");
				searchingFile.fileGrade=rs.getDouble("fileGrade");
				searchingFile.fileName=rs.getString("fileName");
				searchingFile.fileGradesCounter=rs.getInt("fileGradesCounter");
				searchingFile.isAccepted=true;
				
				byte[] fileBytes = rs.getBytes("pdfFile");
				FileOutputStream fos=  new FileOutputStream(searchingFile.thisFile);
				
				fos.write(fileBytes);
				fos.close();
				
				listOfFiles.add(searchingFile);
				
				}
            }
			


		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		
		
		
		return listOfFiles;
	}
}
