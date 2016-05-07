 package pt.tecnico.mydrive.service;

 import static org.junit.Assert.*;

 import org.junit.Test;


import pt.tecnico.mydrive.exceptions.InvalidTypeException;
import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;


import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.FileSystem;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.SuperUser;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class CreateFileTest extends AbstractServiceTest {


	

    protected void populate() {

        MyDrive md = MyDrive.getInstance();

        SuperUser root = new SuperUser("root", "***", "Super user", "rwxdr-x-");

        Directory claudiahome = new Directory("claudiahome",123,new DateTime(),"rwxd----", (User)root );
        
        User claudia = new User("claudiaamorim", "nhanha", "claudia", "rwxd----", claudiahome);
        claudiahome.setOwner(claudia);
        
        

        
		//long token_rip = md.loginUser("Henrip","Henrip");

  }
  private long login(String username, String password){

        MyDrive md = MyDriveService.getMydrive();
        return md.loginUser(username,password);
  }

  private File getFile(String name, long token){

        MyDrive md = MyDriveService.getMydrive();
        FileSystem fs= MyDriveService.getFilesystem();


        Login login = md.getLoginbyToken(token);

        Directory maindir = login.getCurrentdirectory();

        return maindir.getFile(name);


  }

    @Test
    public void sucess(){

        long token = login("claudiaamorim","nhanha");
        CreateFileService service = new CreateFileService(token,"README", "textfile", "4Dcinema----> check!");
        service.execute();

        File file = getFile("README",token);
        assertNotNull("File was not created",file);
        assertEquals("Invalid name", "README", file.get_name());
    }


    /*@Test(expected=InvalidTypeException.class)
    public void InvalidType() {


        //final String userName = "Henrip";

        
        token = login("claudiaamorim","nhanha");
        CreateFileService service = new CreateFileService(token,"README", "textfile", "4Dcinema----> check!");

        

        CreateFileService service = new CreateFileService(token, "HLgameplan", "file", "4Dcinema----> check!");
        service.execute();

				//fail("This type: \"textfile\" is inavalid");

    }*/
}
