package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.phonebook.domain.Person;
import pt.tecnico.phonebook.service.PhoneBookService;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;


import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;


public class WriteFileTest extends AbstractServiceTest{
	
	
	
    protected void populate() {
    	
    	MyDrive md = MyDrive.getInstance();
    	md.createUser("carlos pincel");
    	
    	long token = md.loginUser("carlos", "carlos");
    	
    	md.createFile(token, "testdir", "directory","");
    	md.createFile(token, "testtxt","textfile", "ola eu sou o carlos pincel");
    	md.createFile(token, "testapp", "app", "I dunno wth goes here");
    	md.createFile(token, "testlink", "link", "/some/path");
    }
    
    @Test //login correcto
    public void success_1() {
        
    	MyDrive md = MyDriveService.getMydrive();
        
        final String userName = "henrique";
        long token_riri = md.loginUser(userName,userName);
        
        ////ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/Downloads/Unseen/xxx");
        ////service.execute();

       

        ////assertEquals("Invalid access: directory not reachable", "/Downloads/Unseen/xxx", MyDriveService.getCurrentDirectory().get_name());
    //}
    
}
