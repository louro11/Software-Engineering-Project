package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.Login;

public class AddEnvironmentVariableTest extends AbstractServiceTest{

	public AddEnvironmentVariableTest() {
		// TODO Auto-generated constructor stub
	}

	private MyDrive md;
	
	
	private long login(String username, String password){	
	    return md.loginUser(username,password);
	}
	
	
	protected void populate() {
		// TODO Auto-generated method stub
		
		md = MyDrive.getInstance();
		
		md.createUser("louro");
		
		long logintoken = this.login("louro", "louro");
		
	}

	

}
