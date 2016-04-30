// package pt.tecnico.mydrive.service;
//
// import static org.junit.Assert.*;
//
// import org.junit.Test;
// import java.util.*;
// import org.joda.time.DateTime;
//
// import pt.tecnico.mydrive.exceptions.InvalidPathException;
// import pt.tecnico.mydrive.exceptions.FileNotFoundException;
// import pt.tecnico.mydrive.exceptions.PermitionException;
//
//
// import pt.tecnico.mydrive.domain.MyDrive;
// import pt.tecnico.mydrive.domain.User;
// import pt.tecnico.mydrive.domain.File;
// import pt.tecnico.mydrive.domain.Directory;
// import pt.tecnico.mydrive.domain.FileSystem;
// import pt.tecnico.mydrive.domain.Login;
//
//
// public class ListDirectoryTest extends AbstractServiceTest {
//
// 	private MyDrive md = MyDrive.getInstance();
//
//
// 	public void populate(){
//
//
// 		md.createUser("henrique");
// 		md.createUser("claudia");
//
//
//
//
// 	}
//
// 	@Test
//     public void invalidTokenTest() {
//
//
//         long token = md.loginUser("claudia","claudia");
//
// 		Random rand = new Random();
//         long randomtoken = rand.nextLong();
//
//         ListDirectoryService service = new ListDirectoryService(randomtoken);
//
//    		//assertEquals(log.warn(), service.execute());
//
//
//    }
//
//     @Test(expected = PermitionException.class)
//     public void userhasnopermission(){
//
//     	FileSystem fs = md.getFilesystem();
//
//         User claudia = fs.getUserByUsername("claudia");
// 		User henrique = fs.getUserByUsername("henrique");
//
//         long token = md.loginUser("henrique","henrique");
//
//         long token2 = md.loginUser("claudia","claudia");
//
//         Login login = md.getLoginbyToken(token2);
//
//         Directory currentdir = login.getCurrentdirectory();
//
//
//
//         currentdir.setOwner(henrique);
//
//         ListDirectoryService service = new ListDirectoryService(token2);
//
//         service.execute();
//
//
//     }
//
//
// }
