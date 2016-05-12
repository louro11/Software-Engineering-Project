package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.MyDrive;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.mydrive.domain.EnvironmentVar;
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
		md = MyDrive.getInstance();
		md.createUser("louro");
		
	}
	
	
	@Test //success creating a env variable
	public void Success(){

	   String var_name="var1";
	   String var_value="something";
       long token = login("louro","louro");
       AddEnvironmentVariableService service = new AddEnvironmentVariableService(token,var_name, var_value);
       service.execute();

       Login login = md.getLoginbyToken(token);
       
       int size = service.result().size();
       
       
       for(EnvironmentVar env: service.result()){
    	   if(env.get_name()==var_name && env.get_value()==var_value){
    		   assertEquals(service.result().get(size-1).get_name(), var_name);
    	       assertEquals(service.result().get(size-1).get_value(), var_value);
    	   }
       }
		
	 }
	

}
