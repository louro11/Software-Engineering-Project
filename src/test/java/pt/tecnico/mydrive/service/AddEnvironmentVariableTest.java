package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.MyDrive;

import org.junit.Test;

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
		
		//long logintoken = this.login("louro", "louro");
		
	}
	
	@Test
	public void Success(){

       long token = login("louro","louro");
       AddEnvironmentVariableService service = new AddEnvironmentVariableService(token,"var1", "something");
       service.execute();

       
	
	 }
	

}
