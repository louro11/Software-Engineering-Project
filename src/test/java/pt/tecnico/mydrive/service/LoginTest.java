package pt.tecnico.mydrive.service;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import org.jdom2.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;
import pt.tecnico.mydrive.exceptions.WrongPasswordException;
import pt.tecnico.mydrive.exceptions.InvalidPasswordLengthException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.TokenAlreadyExistsException;

import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.Login;

public class LoginTest extends AbstractServiceTest{

	Random rand = new Random();
	long token = rand.nextLong();
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
		final String password = "HenriqueCarloss";
		LoginService service = new LoginService(username, password);
		service.execute();
		Login login = md.getLoginbyToken(token);
		assertNotNull("Login Success",login); 

	}
   
 

    @Test(expected = UserDoesNotExistException.class)
    public void testLoginUserNotExist() throws Exception{
		final String username = "madLena";
		final String password = "batata";
		LoginService service = new LoginService(username, password);
		service.execute();
	}

	@Test(expected = UserDoesNotExistException.class)
    public void testNullUser() throws Exception{
		final String username = "";
		LoginService service = new LoginService(username, "username");
		service.execute();
	}

	@Test(expected=LoginDoesNotExistException.class)
	public void testNotExistToken(){
		long token = 9999999999L;
		md.getLoginbyToken(token);
	}

    @Test(expected = WrongPasswordException.class)
    public void testWrongPassword() throws Exception{

    	final String password = "tinhas";

        LoginService service = new LoginService("HenriqueCarloss", password);

        service.execute();

    }

   //@Test(expected = TokenAlreadyExistsException.class)


   /*FALTA TESTAR O TEMPO E O TOKENALREADYEXISTS*/
  }