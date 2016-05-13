package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;
import pt.tecnico.mydrive.exceptions.WrongPasswordException;


public class LogoutService extends MyDriveService {

	  private String username;
	
	  private String password;
	  
	  private long newtoken;

	
	  //caso em que faz login sem password 
	
	  
	  public LogoutService(String username){
	
		    this.setUsername(username);
		
		    this.setPassword("");
	
	  }
	
	  //caso em que faz login com password
	
	  public LogoutService(String username, String password){
	
		    this.setUsername(username);
		
		    this.setPassword(password);
		
	  }
	
	
	  public void setUsername(String username){
	
		  this.username = username;
	
	  }
	
	  public void setPassword(String password){
	
		  this.password = password;
	
	  }
	
	
	  public String getUsername(){
	
		  return this.username;
	
	  }
	
	  public String getPassword(){
	
		  return this.password;
	
	  }
	

	
	  public final void dispatch() throws UserDoesNotExistException, WrongPasswordException {
	
		  newtoken = getMydrive().logoutUser(username, password);
	    
	  }
	
	  public final boolean result(){
	
		  return (newtoken != 0) ? true : false;
	
	  }
  
}
