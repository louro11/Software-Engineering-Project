package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.MyDrive;

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
		//long logintoken = this.login("louro", "louro");
		
	}
	
	
	@Test //success creating a env variable
	public void Success(){

	   String var_name="var1";
	   String var_value="something";
       long token = login("louro","louro");
       AddEnvironmentVariableService service = new AddEnvironmentVariableService(token,var_name, var_value);
       service.execute();

       Login login = md.getLoginbyToken(token);
       
       //O servico devolve a lista, tenho que a ir buscar? ou vou buscar ao retorno do serviço
       
       for(EnvironmentVar env: login.getVarsSet()){
    	   if(env.get_name()==var_name && env.get_value()==var_value){
    		   
    	   }
       }
	
	 }
	

}
