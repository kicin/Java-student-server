package filesdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckIfNameExists {

public CheckIfNameExists() {
		
	}
	
public boolean nameFree(String name) throws SQLException, ClassNotFoundException{
    	
    	boolean status=true;
    	String fileName;
    	
		Connection conn = null;
		String SQLcheck="SELECT fileName FROM files WHERE fileName = ?;";
		
		String DBUserName="sa";
		String DBPassword="aa";
		
		
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			
			
			String connectionUrl = "jdbc:sqlserver://localhost:3306;" +
			     "instance=SQLEXPRESS;databaseName=serverDB;";
			  
			conn = DriverManager.getConnection(connectionUrl,DBUserName,DBPassword);
			
			PreparedStatement preparedStatement =conn.prepareStatement(SQLcheck);
			preparedStatement.setString(1, name);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while(rs.next()){
				fileName = rs.getString("fileName");

                if(fileName.equals(name)){
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
