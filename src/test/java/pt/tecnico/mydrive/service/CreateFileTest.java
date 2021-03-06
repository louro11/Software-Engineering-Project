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


 public class CreateFileTest extends AbstractServiceTest {

 private MyDrive md;



     protected void populate() {

            md = MyDrive.getInstance();

            //FileSystem fs = MyDriveService.getFilesystem();

           // SuperUser root = fs.getRoot();//new SuperUser("root", "***", "Super user", "rwxdr-x-");

            //Directory claudiahome = new Directory("claudiahome",123,new DateTime(),"rwxd----", (User)root);


            //User claudia = new User("claudiaamorim", "nhanha", "claudia", "rwxd----", claudiahome);

            md.createUser("claudiaamorim");
            //User claudia = new User("claudiaamorim");
            //claudiahome.setOwner(claudia);


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
      public void success(){



        long token = login("claudiaamorim","claudiaamorim");
        CreateFileService service = new CreateFileService(token,"readme", "textfile", "4Dcinema----> check!");
        service.execute();

        TextFile file = (TextFile)this.getFile("readme",token);
        assertNotNull("File was not created",file);

     }




   /* @Test(expected=InvalidTypeException.class)
    public void InvalidType() {

        long token = login("claudiaamorim","nhanha");

        CreateFileService service = new CreateFileService(token, "HLgameplan", "file", "4Dcinema----> check!");
        service.execute();


    } */

}
