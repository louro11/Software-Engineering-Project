package pt.tecnico.mydrive.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Verifications;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;



import pt.tecnico.mydrive.exceptions.InvalidTypeException;
import pt.tecnico.mydrive.exceptions.InvalidPathException;



import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.FileSystem;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.SuperUser;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Application;
import pt.tecnico.mydrive.domain.TextFile;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;



import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class ExecuteFileTest extends AbstractServiceTest {



	private MyDrive md;



    protected void populate() {

           md = MyDrive.getInstance();

           md.createUser("DonaldTrump");
          
           
           long tokenDonald = this.login("DonaldTrump", "DonaldTrump");
           
           Login loginDonald = md.getLoginbyToken(tokenDonald);
           
           md.createFile(loginDonald, "FileGenerator", "application", "mydrivedomain.filesystem.createFile");
           
           md.createFile(loginDonald, "FileGeneratorLink", "link", "/home/DonaldTrump/FileGenerator");


  }

	  private long login(String username, String password){
	
	
	       return md.loginUser(username,password);
	  }
	
	
	
	   private File getFile(String name, long token){
	
	
	        Login login = md.getLoginbyToken(token);
	
	        Directory dir = login.getCurrentdirectory();
	
	        File file = dir.getFile(name);
	
	        return file;
	  }
	
	
	 /* @Test
	  public void SuccessApp(){
	
	
	
	       long token = login("DonaldTrump","DonaldTrump");
	       ExecuteFileService service = new ExecuteFileService(token,"/home/DonaldTrump/FileGenerator", "HillaryClinton");
	       service.execute();
	
	       Application file = (Application)this.getFile("/home/DonaldTrump/FileGenerator",token);
	       assertNotNull("File was not created",file);
	
	  }
	
	  
	  @Test
	  public void SuccessLink(){
	
	
	
	       long token = login("DonaldTrump","DonaldTrump");
	       ExecuteFileService service = new ExecuteFileService(token,"/home/DonaldTrump/FileGeneratorLink");
	       service.execute();
	
	       File file = (File)this.getFile("/home/DonaldTrump/HillaryClinton",token);
	       assertNotNull("File was not created",file);
	
	  }*/
	
	
	
	  /* @Test(expected=FileNotFoundException.class)
	   public void FileNotFound() {
	
	       long token = login("DonaldTrump","DonaldTrump");
	
	       ExecuteFileService service = new ExecuteFileService(token,"/home/DonaldTrump/HillaryClinton");
	       service.execute();
	
	
	   } */

}
