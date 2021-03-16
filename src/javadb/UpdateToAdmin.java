package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class UpdateToAdmin {

public UpdateToAdmin() {
	
}
	
public void update(int id) throws ClassNotFoundException, SQLException {
		
		Connection conn = null;
		String SQL = "SET NOCOUNT OFF;";
		String SQLnew="UPDATE clientsTable SET ifADMIN=? WHERE clientID = ?";
		
		
		String DBUserName="sa";
		String DBPassword="aa";
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			
			
			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
			     "instance=SQLEXPRESS;databaseName=serverDB;";
			  
			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);
			
			PreparedStatement statement = conn.prepareStatement(SQLnew);
			statement.setString(1,"admin");
			statement.setInt(2,id);
			
			statement.executeUpdate();
			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		

	}

}
