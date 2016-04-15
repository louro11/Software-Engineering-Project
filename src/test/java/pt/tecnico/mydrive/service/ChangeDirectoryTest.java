package pt.tecnico.mydrive.service;

import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;


import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;


public class ChangeDirectoryTest extends AbstractServiceTest {
	
	
	MyDrive md = MyDrive.getInstance();

    protected void populate() {
      
      
		md.createUser("henrique");
		
		long token_riri = md.loginUser("henrique","henrique");
		
		
		md.createFile(token_riri,"Downloads", "directory","");
		
		md.changeCurrentDirectory(token_riri,"/home/henrique/Downloads");
		
		md.createFile(token_riri,"Unseen", "directory","");
		
		md.changeCurrentDirectory(token_riri,"/home/henrique/Downloads/Unseen");
		
		md.createFile(token_riri,"xxx", "directory","");
		
		
		
		
		md.changeCurrentDirectory(token_riri,"/home/henrique");
		
		md.createFile(token_riri,"Documents", "directory","");
		
		md.changeCurrentDirectory(token_riri,"/home/henrique/Documents");
		
		md.createFile(token_riri,"NaoAbrir", "directory","");
		


	/****************************************************************************/


		md.createUser("duarte");
		
		long token_duarte = md.loginUser("duarte","duarte");
		
		md.createFile(token_duarte,"Documents", "directory","");
		
		md.changeCurrentDirectory(token_riri,"/home/duarte/Documents");
		
		md.createFile(token_duarte,"Tecnico", "directory","");
		
		
		md.changeCurrentDirectory(token_riri,"/home/duarte/");
		
		md.createFile(token_duarte,"Temporary", "directory","");
		
		md.changeCurrentDirectory(token_riri,"/home/duarte/Temporary");
		
		md.createFile(token_duarte,"Finlandia-BoobTrip", "directory","");
		
		
		md.changeCurrentDirectory(token_riri,"/home/duarte/");
		
		md.createFile(token_duarte, "Pictures", "directory", "" );
		
		md.changeCurrentDirectory(token_riri,"/home/duarte/Pictures");
		
		md.createFile(token_duarte, "Finlandia", "link", "/home/duarte/Temporary/Finlandia-BoobTrip" );
		
		

	
	}

	/** Todos os testes success tem de ter os Assert a funcionar **/
	
	
    @Test
    public void successChangeToOwnedDir() {
        
        
        final String userName = "henrique";
        long token_riri = md.loginUser(userName,userName);
        
        ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/Downloads/Unseen/xxx");
        service.execute();

       

        assertEquals("Invalid access: directory not reachable", "/Downloads/Unseen/xxx", MyDriveService.getCurrentDirectory().get_name());
    }
    
    @Test
    public void successChangeThroughLink() {
        
        
        final String userName = "duarte";
        long token_riri = md.loginUser(userName,userName);
        
        ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/home/duarte/Pictures/Finlandia");
        service.execute();

      
        
        assertEquals("Invalid access: directory not reachable", "/home/duarte/Pictures/Finlandia", MyDriveService.getCurrentDirectory().get_name());
    }
    
    
    
    
    
    /** Todos os testes de insuccesso não precisam de Asserts **/

    @Test(expected = InvalidPathException.class)
    
    public void ChangetoInvalidDirectory() {
        
        final String userName = "henrique";
        long token_riri = md.loginUser(userName,userName);
        
        //ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/home/duarte/Documents/Tecnico");
        //service.execute();
    }
    
    
    @Test(expected = InvalidPathException.class)
    
    public void ChangetoRootDirectory() {
        
        final String userName = "duarte";
        long token_riri = md.loginUser(userName,userName);
        
        ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/home/root");
        service.execute();
    }
    

    @Test(expected = FileNotFoundException.class)
    
    public void DirectoryNotFound() {
        
        final String userName = "henrique";
        long token_riri = md.loginUser(userName,userName);
        
        ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/home/henrique/downloads/Unseen");
        service.execute();
    
    }
    
}
