package pt.tecnico.mydrive.service;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;


import mockit.Mock;
import mockit.MockUp;
import mockit.Expectations;
import mockit.Verifications;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.jdom2.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.exceptions.PermitionException;
import pt.tecnico.mydrive.service.MyDriveService;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;
import pt.tecnico.mydrive.exceptions.WrongPasswordException;
import pt.tecnico.mydrive.exceptions.MyDriveException;
import pt.tecnico.mydrive.exceptions.InvalidPasswordLengthException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;




import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.TextFile;
import pt.tecnico.mydrive.domain.SuperUser;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;

@RunWith(JMockit.class)
public class LoginTest extends AbstractServiceTest{

	Random rand = new Random();
		long token = rand.nextLong();

@Mocked
private MyDrive md;




    protected void populate() {

    	md = MyDrive.getInstance();
    	md.createUser("HenriqueCarloss");
    	
}

	@Test
	public void success(){
		Random rand = new Random();
		final long token = rand.nextLong();
		final String username = "HenriqueCarloss";
		final String password = "nooooooooo";
		LoginService service = new LoginService(username, password);
		service.execute();
		Login login = md.getLoginbyToken(token);
		assertNotNull("Login Success",login); 

	}
   
 

 //    @Test(expected = UserDoesNotExistException.class)
 //    public void testLoginUserNotExist() throws UserDoesNotExistException{
	// 	final String username = "madLena";
	// 	final String password = "batata";

	//  new MockUp<LoginService>() {
	//   @Mock
	//   void dispatch() throws MyDriveException {
	//     throw new UserDoesNotExistException(username); }
	// };

 //        new LoginService(username, password).execute();
 //    }

	@Test(expected=LoginDoesNotExistException.class)
	public void testNotExistToken(){
		long token = 9999999999L;
		md.getLoginbyToken(token);
	}

    @Test(expected = WrongPasswordException.class)
    public void testWrongPassword() throws WrongPasswordException{

    	final String password = "tinhas";

        LoginService service = new LoginService("HenriqueCarloss", password);

        service.dispatch();

    }

    @Test(expected = InvalidPasswordLengthException.class)
    public void testLengthofPassword() throws InvalidPasswordLengthException{
    	final String password = "ola";
    	LoginService service = new LoginService("HenriqueCarloss", password);
   		service.dispatch();
   }
  }