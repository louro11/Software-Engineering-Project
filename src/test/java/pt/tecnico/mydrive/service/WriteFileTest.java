 package pt.tecnico.mydrive.service;

 import static org.junit.Assert.assertEquals;
 import static org.junit.Assert.assertNotNull;
 import static org.junit.Assert.assertNull;

 import org.junit.Test;

 import pt.tecnico.mydrive.exceptions.InvalidPathException;
 import pt.tecnico.mydrive.exceptions.PermitionException;
 import pt.tecnico.mydrive.service.MyDriveService;
 import pt.tecnico.mydrive.exceptions.FileNotFoundException;


 import pt.tecnico.mydrive.domain.MyDrive;
 import pt.tecnico.mydrive.domain.TextFile;
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
     
      @Test
      public void success() {
     
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
 		
      }
     
      //ficheiro nao existe
      @Test(expected = FileNotFoundException.class)
      public void fileNotExists() {
     
		MyDrive md = MyDriveService.getMydrive();
		final String username = "henrique";
		long token2 = md.loginUser(username,username);
		
		Login login = md.getLoginbyToken(token2);
		Directory dir = login.getCurrentdirectory();
			
		final String filename = "carlos";
  
		WriteFileService service= new WriteFileService(token2, filename,"something");
		service.execute();
      }
     
      //no permission, usar tenta alterar um ficheiro que nao e dele, tentar fazer com o root
      
      @Test(expected = PermitionException.class)
      public void insufficientPermissions() {
     
		MyDrive md = MyDriveService.getMydrive();
		 
		final String username1 = "rafa";
		long token1 = md.loginUser(username1,username1);
		Login login1 = md.getLoginbyToken(token1);
		
		final String username2= "henrique";
		long token2 = md.loginUser(username2,username2);
		Login login2 = md.getLoginbyToken(token2);
		
		final String filename = "testtxt2";


        WriteFileService service = new WriteFileService(token1,filename, "something");
        service.execute();
      }



 }
