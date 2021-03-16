package javadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckIfAdmin {

	public CheckIfAdmin() {
		
	}
	
	public boolean check(int id) throws ClassNotFoundException, SQLException {
		
        boolean status=false;
    	
		Connection conn = null;
		String SQLcheck="SELECT ifADMIN FROM clientsTable WHERE clientID ="+Integer.toString(id);
		String userStatus;
		
		String DBUserName="sa";
		String DBPassword="aa";
		
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			
			
			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
			     "instance=SQLEXPRESS;databaseName=serverDB;";
			  
			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);
			
			Statement stmt=conn.createStatement();

			ResultSet rs = stmt.executeQuery(SQLcheck);
					
			
			while(rs.next()){
                userStatus= rs.getString("ifADMIN");

                if(userStatus.equals("admin")){
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
