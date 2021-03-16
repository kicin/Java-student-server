package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckIfLoginExists {

	public CheckIfLoginExists() {
		
	}
	
public boolean loginFree(String username) throws SQLException, ClassNotFoundException{
    	
    	boolean status=true;
    	
    	String dbUsername;
		Connection conn = null;
		String SQLcheck="SELECT clientLOGIN FROM clientsTable WHERE clientLOGIN = ?";
		
		String DBUserName="sa";
		String DBPassword="aa";
		
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			
			
			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
			     "instance=SQLEXPRESS;databaseName=serverDB;";
			  
			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);
			
			PreparedStatement preparedStatement =conn.prepareStatement(SQLcheck);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while(rs.next()){
                dbUsername = rs.getString("clientLOGIN");

                if(dbUsername.equals(username)){
                    status = false;
                }
            }
		
			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		

		return status;
		
	}
}
