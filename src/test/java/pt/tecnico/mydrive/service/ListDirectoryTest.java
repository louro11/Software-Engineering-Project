// package pt.tecnico.mydrive.service;

// import static org.junit.Assert.*;

// import org.junit.Test;
// import java.util.*;
// import org.joda.time.DateTime;
// import org.junit.runner.RunWith;

// import mockit.Expectations;
// import mockit.Verifications;
// import mockit.Mocked;
// import mockit.integration.junit4.JMockit;

// import pt.tecnico.mydrive.exceptions.InvalidPathException;
// import pt.tecnico.mydrive.exceptions.FileNotFoundException;
// import pt.tecnico.mydrive.exceptions.PermitionException;


// import pt.tecnico.mydrive.domain.MyDrive;
// import pt.tecnico.mydrive.domain.FileSystem;
// import pt.tecnico.mydrive.domain.User;
// import pt.tecnico.mydrive.domain.SuperUser;
// import pt.tecnico.mydrive.domain.File;
// import pt.tecnico.mydrive.domain.Directory;
// import pt.tecnico.mydrive.domain.Login;
// import pt.tecnico.mydrive.service.dto.FileDto;


// @RunWith(JMockit.class)
// public class ListDirectoryTest extends AbstractServiceTest {

//   @Mocked
//   private MyDrive md;

// 	protected void populate(){

//     md = MyDrive.getInstance();

//     SuperUser root = new SuperUser("root", "***", "Super user", "rwxdr-x-");

//     Directory claudiahome = new Directory("claudiahome",123,new DateTime(),"rwxd----", (User)root);

//     User claudia = new User("claudiaamorim", "nhanha", "claudia", "rwxd----", claudiahome);

//     claudiahome.setOwner(claudia);

//     Directory agah = new Directory("agah",123,new DateTime(),"rwxd----", (User)root);

//     Directory mais = new Directory("mais",123,new DateTime(),"rwxd----", (User)root);

//     Directory el = new Directory("el",123,new DateTime(),"rwxd----", (User)root);

//     claudiahome.addFiles(agah);

//     agah.setOwner(claudia);

//     claudiahome.addFiles(mais);

//     mais.setOwner(claudia);

//     claudiahome.addFiles(el);

//     el.setOwner(claudia);

// }

//     private long login(String username, String password){

//           return md.loginUser(username,password);
//     }



// 	@Test
//   public void success() {

//       long token = login("claudiaamorim","nhanha");

//       ListDirectoryService service = new ListDirectoryService(token);

//       service.execute();

//       List<FileDto> fs = service.result();

//       assertEquals("List with 3 Files", 3, fs.size());
//       assertEquals("First file is agah", "agah", fs.get(0).getName());
//       assertEquals("Second file is mais", "mais", fs.get(1).getName());
//       assertEquals("Last file is el", "el", fs.get(2).getName());
//     }
// }
