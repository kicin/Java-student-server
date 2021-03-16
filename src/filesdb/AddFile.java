package filesdb;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.text.Document;

public class AddFile {

	
	public AddFile(FileDB file) throws ClassNotFoundException, SQLException, IOException {

		Connection conn = null;

		String DBUserName="sa";
		String DBPassword="aa";

		try {

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	


			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
					"instance=SQLEXPRESS;databaseName=serverDB;";

			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);


			String SQLnew="INSERT INTO files(fileAuthor,fileGrade,fileAccepted,pdfFile,fileName,fileGradesCounter) VALUES (?,?,?,?,?,?);";

			
			PreparedStatement preparedStatement =conn.prepareStatement(SQLnew);
			preparedStatement.setString(1, file.fileAuthor);
			preparedStatement.setString(2,  Double.toString(file.fileGrade));
			String notAcc="not accepted";
			preparedStatement.setString(3,notAcc);

			FileInputStream fis = new FileInputStream(file.thisFile);

			//method to insert a stream of bytes
			preparedStatement.setBlob(4, fis);
			preparedStatement.setString(5,file.fileName);
			preparedStatement.setInt(5,0);

			preparedStatement.executeUpdate();


		} finally {
			if (conn!= null){
				conn.close();
			}
		}
	}
	
	private byte[] getByteArrayFromFile(File file) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final InputStream in = new FileInputStream(file);
		final byte[] buffer = new byte[1024];
		int read = -1;
		while ((read = in.read(buffer)) > 0) {
			baos.write(buffer, 0, read);
		}
		in.close();
		return baos.toByteArray();
	}

}
