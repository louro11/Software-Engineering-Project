package pt.tecnico.mydrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;
import pt.tecnico.mydrive.exceptions.WrongPasswordException;

public class LogoutServiceTest extends AbstractServiceTest{
    
	private MyDrive md;
	
	public LogoutServiceTest() {
		// TODO Auto-generated constructor stub
	}

	//closes #52
	
	@Override
	protected void populate() {
		md = MyDrive.getInstance();
    	md.createUser("louro");
		
	}

	@Test
	public void success(){
		final String username = "louro";
		final String password = "louro";
		LoginService service = new LoginService(username, password);
		service.execute();
		
		LogoutService newservice = new LogoutService(username, password);
		newservice.execute();
		
		assertTrue("logout failed", newservice.result());
	}
	
	@Test(expected = WrongPasswordException.class)
	public void logoutWrongpassword(){
		final String username = "louro";
		final String password = "louro";
		final String wrongpassword = "12345";
		
		LoginService service = new LoginService(username, password);
		service.execute();
		
		LogoutService newservice = new LogoutService(username, wrongpassword);
		newservice.execute();
	}
	
	@Test(expected = UserDoesNotExistException.class)
    public void testLogoutUserNotExist() throws Exception{
		final String username = "madLena";
		final String password = "batata";
		LogoutService service = new LogoutService(username, password);
		service.execute();
	}
	
}
