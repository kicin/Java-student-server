package javadb;

import java.sql.Date;
import java.util.ArrayList;

public class Client{

	public int clientId;
    public String clientLogin;
    public String clientPassword;
    public Date startDate;
    
    public Client() {
    	
    }

    public String getLogin() {
        return clientLogin;
    }

    public void setLogin(String login) {
        this.clientLogin = login;
    }
    
    public String getPassword(){
    	return clientPassword;
    }
    
    public void setPassword(String password){
    	this.clientPassword=password;
    }
    
    
    public int getId() {
        return clientId;
    }

    public void setId(int id) {
        this.clientId = id;
    }
    
}
