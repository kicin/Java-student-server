package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoFromId {

	public InfoFromId() {
		
	}
	
	public Client returnInfo(int id) throws ClassNotFoundException, SQLException {
		
		System.out.println(id);
		Client client=new Client();
		
		Connection conn = null;
		String SQLcheck="SELECT clientLOGIN,clientPASS,dateOfCreation FROM clientsTable WHERE clientID=?";
		String userStatus;
		
		String DBUserName="sa";
		String DBPassword="aa";
		
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			
			
			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
			     "instance=SQLEXPRESS;databaseName=serverDB;";
			  
			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);
			
			PreparedStatement preparedStatement =conn.prepareStatement(SQLcheck);
			preparedStatement.setInt(1,id);
			ResultSet rs = preparedStatement.executeQuery();
					
			
			while(rs.next()){
                client.clientId=id;
                client.clientLogin= rs.getString("clientLOGIN");
                client.clientPassword= rs.getString("clientPASS");
                client.startDate= rs.getDate("dateOfCreation");

            }
		
			
		} finally {
			if (conn!= null){
				conn.close();
			}
		}
		
		
		
		return client;
	}
	
}
