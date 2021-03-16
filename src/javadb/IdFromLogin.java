package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IdFromLogin {

	public IdFromLogin() {
		
	}
	
	public int returnId(String username) throws ClassNotFoundException, SQLException {
		
		int id = 0;
		
		Connection conn = null;
		String SQLcheck="SELECT clientID FROM clientsTable WHERE clientLOGIN=?";
		String userStatus;
		
		String DBUserName="sa";
		String DBPassword="aa";
		
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			
			
			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
			     "instance=SQLEXPRESS;databaseName=serverDB;";
			  
			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);
			
			PreparedStatement preparedStatement =conn.prepareStatement(SQLcheck);
			preparedStatement.setString(1,username);
			ResultSet rs = preparedStatement.executeQuery();
					
			
			while(rs.next()){
                id= rs.getInt("clientID");

            }
		
			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		
		
		
		return id;
	}
	
}
