package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class AddClient {

	public String clientLogin;
	public String clientPassword;
	
public AddClient(String name,String pass) {
	clientLogin=name;
	clientPassword=pass;
}
	public void adding(String username, String password) throws ClassNotFoundException, SQLException {
		
        Connection conn = null;
		
		String DBUserName="sa";
		String DBPassword="aa";
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			
			
			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
			     "instance=SQLEXPRESS;databaseName=serverDB;";
			  
			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);
			
			
			String SQL = "SET NOCOUNT OFF;";
			String SQLnew="INSERT INTO clientsTable (clientLOGIN,clientPASS,ifAdmin,dateOfCreation) VALUES (?,?,?,?);";
			
			
			PreparedStatement preparedStatement =conn.prepareStatement(SQLnew);
			preparedStatement.setString(1,username);
			preparedStatement.setString(2,password);
			String user="user";
			preparedStatement.setString(3,user);
			preparedStatement.setDate(4,getCurrentDate());
			
			preparedStatement.addBatch();
			
			preparedStatement.executeUpdate();
			
			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
	}
	
	private static java.sql.Date getCurrentDate() {
	    java.util.Date today = new java.util.Date();
	    return new java.sql.Date(today.getTime());
	}


}
