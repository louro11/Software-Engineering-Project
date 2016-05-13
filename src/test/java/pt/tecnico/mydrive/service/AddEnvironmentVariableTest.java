package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;

import static org.junit.Assert.*;

import java.util.List;

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
	
	//closes #49
	
	@Test //success creating a env variable
	public void Success(){

	   String var_name="var1";
	   String var_value="something";
       long token = login("louro","louro");
       AddEnvironmentVariableService service = new AddEnvironmentVariableService(token,var_name, var_value);
       service.execute();
       
       int size = service.result().size();
       
       
       for(EnvironmentVar env: service.result()){
    	   if(env.get_name()==var_name && env.get_value()==var_value){
    		   assertEquals(service.result().get(size-1).get_name(), var_name);
    	       assertEquals(service.result().get(size-1).get_value(), var_value);
    	   }
       }
		
	 }
	
	@Test //changing varible value
	public void changeVariableValue(){		
		
		String var_name="var1";
	    String var_value="something";
	    String var_newvalue = "somethingnew";
	    long token = login("louro", "louro");   
		AddEnvironmentVariableService service = new AddEnvironmentVariableService(token,var_name, var_value);
		service.execute();
		
		AddEnvironmentVariableService newservice = new AddEnvironmentVariableService(token,var_name, var_newvalue);
		newservice.execute();
	       
        for(EnvironmentVar env: service.result()){
		   if(env.get_name()==var_name){
			   assertEquals(env.get_value(), var_newvalue);
		   }
        }
	}
	
	@Test
	public void printingVariableValue(){
		String var_name="var1";
	    String var_value="something";
		
	    long token = login("louro", "louro");
		AddEnvironmentVariableService service = new AddEnvironmentVariableService(token,var_name, var_value);
		service.execute();
		
		List<EnvironmentVar> _vars = service.result();
		
		for(EnvironmentVar var : _vars){
			if(var.get_name().equals(var_name));
				assertEquals(var.get_value(), var_value);
		}
	}
	
	@Test(expected = LoginDoesNotExistException.class)
	public void loginFailTest(){
		
		String var_name="var1";
	    String var_value="something";
		
	    long token = login("carlos", "carlos");
		AddEnvironmentVariableService service = new AddEnvironmentVariableService(token,var_name, var_value);
		service.execute();
		
	}
	

}
