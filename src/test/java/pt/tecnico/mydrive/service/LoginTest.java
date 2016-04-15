package pt.tecnico.mydrive.service;

import static org.junit.Assert.*;

import org.junit.*;

import org.jdom2.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.exceptions.PermitionException;
import pt.tecnico.mydrive.service.MyDriveService;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;


import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.TextFile;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;


public class LoginTest extends AbstractServiceTest{

    MyDrive md = MyDrive.getInstance();

    protected void populate() {

        md.createUser("henrique");
        long tokenlog = md.loginUser("henrique", "password123");

}
    // tests
    @Test(expected = UserDoesNotExistException.class)
    public void testLoginUserNotExist() throws Exception{
        User user = set_username("Madalena");
        loginUser(user,"batata");
        
    }

    @Test(expected = LoginInvalidException.class)

	
	
	
    