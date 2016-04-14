package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.service.MyDriveService;
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
    	
    	long token1 = md.loginUser("rafa", "rafa");
    	long token2 = md.loginUser("henrique","henrique");
    	
    	md.createFile(token1, "testdir1", "directory","");
    	md.createFile(token1, "testtxt1","textfile", "ola eu sou o rafa");
    	md.createFile(token1, "testapp1", "app", "I dunno wth goes here");
    	md.createFile(token1, "testlink1", "link", "/some/path");
    	
    	md.createFile(token2, "testdir2", "directory","");
    	md.createFile(token2, "testtxt2","textfile", "ola eu sou o henrique");
    	md.createFile(token2, "testapp2", "app", "I dunno wth goes here");
    	md.createFile(token2, "testlink2", "link", "/some/path");
    }
    
    @Test //permission
    public void success_1() {
        
    	MyDrive md = MyDriveService.getMydrive();
        
        final String username = "henrique";
        long token1 = md.loginUser(username,username);
        
        Login login = md.getLoginbyToken(token1);
		Directory dir = login.getCurrentdirectory();
        User user = login.getUser();
		
		//WriteFileService service = new WriteFileService("testtxt1", token1, "ola eu sou o rafael santos");
		//service.execute();
		
		
		File file = dir.getFile("testtxt1");
		assertNotNull("file does not exist", file);
		//assertNotEquals("Cant write to directory", file, ) //nao pode escrever para uma directoria
		assertEquals("Insufficient permissions", file.get_permission(), user.get_mask());
		
       

        ////assertEquals("Invalid access: directory not reachable", "/Downloads/Unseen/xxx", MyDriveService.getCurrentDirectory().get_name());
    }
    
}
