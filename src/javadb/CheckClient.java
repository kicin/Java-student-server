package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Connection;

public class CheckClient {

	public CheckClient() {
		
	}
	

	
    public boolean loginCheck(String username,String password) throws SQLException, ClassNotFoundException{
    	
    	boolean status=false;
    	
    	String dbUsername, dbPassword;
		Connection conn = null;
		String SQLcheck="SELECT clientID,clientLOGIN,clientPASS FROM clientsTable WHERE clientLOGIN = ? and clientPASS=?";
		
		String DBUserName="sa";
		String DBPassword="aa";
		
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			
			
			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
			     "instance=SQLEXPRESS;databaseName=serverDB;";
			  
			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);
			
			PreparedStatement preparedStatement =conn.prepareStatement(SQLcheck);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while(rs.next()){
                dbUsername = rs.getString("clientLOGIN");
                dbPassword = rs.getString("clientPASS");

                if(dbUsername.equals(username) && dbPassword.equals(password)){
                    status = true;
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
