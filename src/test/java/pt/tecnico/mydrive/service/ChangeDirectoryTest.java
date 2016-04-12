package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
		
		md.createDirectory(token_riri,"/Downloads/Unseen/xxx");
		
		md.createDirectory(token_riri,"/Documents/NaoAbrir/");
		





		md.createUser("duarte");
		
		long token_duarte = md.loginUser("duarte","duarte");
		
		md.createDirectory(token_duarte,"/Documents/Tecnico");
		
		md.createDirectory(token_duarte,"/Temporary/Finlandia-BoobTrip");
		
		md.createTextFile(token_duarte, "/Pictures/Finlandia", "/home/duarte/Temporary/Finlandia-BoobTrip" );
		
		

    //private Contact getContact(String personName, String contactName) {
        //Person p = PhoneBookService.getPhoneBook().getPersonByName(personName);
        //return p.getContactByName(contactName);
    //}

	
	}

	/** Todos os testes success tem de ter os Assert a funcionar **/
	
	
    //@Test
    //public void success_1() {
        
        
        //final String userName = "henrique";
        //long token_riri = md.loginUser(userName,userName);
        
        ////ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/Downloads/Unseen/xxx");
        ////service.execute();

        ////// check contact was removed
        ////Contact c = getContact(personName, "António");

        ////assertEquals("Invalid number of contacts", 0, PhoneBookService.getPerson(personName).getContactSet().size());
    //}
    
    //@Test
    //public void success_2() {
        
        
        //final String userName = "duarte";
        //long token_riri = md.loginUser(userName,userName);
        
        ////ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/home/duarte/Pictures/Finlandia");
        ////service.execute();

        ////// check contact was removed
        ////Contact c = getContact(personName, "António");
        
        ////assertEquals("Invalid number of contacts", 0, PhoneBookService.getPerson(personName).getContactSet().size());
    //}
    
    
    
    
    
    /** Todos os testes de insuccesso não precisam de Asserts **/

    //@Test(expected = InvalidPathException.class)
    
    //public void ChangetoInvalidDirectory_1() {
        
        //final String userName = "henrique";
        //long token_riri = md.loginUser(userName,userName);
        
        ////ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/home/duarte/Documents/Tecnico");
        ////service.execute();
    //}
    
    
    //@Test(expected = InvalidPathException.class)
    
    //public void ChangetoInvalidDirectory_2() {
        
        //final String userName = "duarte";
        //long token_riri = md.loginUser(userName,userName);
        
        ////ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/home/root");
        ////service.execute();
    //}
    

    //@Test(expected = FileNotFoundException.class)
    
    //public void DirectoryNotFound() {
        
        //final String userName = "henrique";
        //long token_riri = md.loginUser(userName,userName);
        
        ////ChangeDirectoryService service = new ChangeDirectoryService(token_riri, "/home/henrique/downloads/Unseen");
        ////service.execute();
    
    //}
}
