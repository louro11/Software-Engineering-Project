// package pt.tecnico.mydrive.service;
//
// import static org.junit.Assert.*;
//
// import org.junit.Test;
//
// import pt.tecnico.mydrive.exceptions.InvalidPathException;
// import pt.tecnico.mydrive.exceptions.FileNotFoundException;
//
//
// import pt.tecnico.mydrive.domain.MyDrive;
// import pt.tecnico.mydrive.domain.User;
// import pt.tecnico.mydrive.domain.File;
// import pt.tecnico.mydrive.domain.Directory;
// import pt.tecnico.mydrive.domain.Login;
//
//
// public class CreateLinkTest extends AbstractServiceTest {
//
//
// 	MyDrive md = MyDrive.getInstance();
//
//     protected void populate() {
//
//
// 		md.createUser("Henrip");
//
// 		long token_rip = md.loginUser("Henrip","Henrip");
//
//
//   }
//
//
//     @Test
//    	public void success_1() {
//
//
//         final String userName = "Henrip";
//         long token_rip = md.loginUser(userName,userName);
//
//         CreateFileService service = new CreateFileService(token_rip, "Dlink", "link", "");
//         service.execute();
//
// 				fail("This type: \"link\" is inavalid");
//
//     }
//
//     @Test
//     public void success_2() {
//
//
//         final String userName = "Henrip";
//         long token_rip = md.loginUser(userName,userName);
//
//         CreateFileService service = new CreateFileService(token_rip, "Fappylink", "link", "/Documents/Fappy");
//         service.execute();
// 				fail();
//
//     }
// }
