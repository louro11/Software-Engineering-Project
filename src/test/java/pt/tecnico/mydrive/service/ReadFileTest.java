// package pt.tecnico.mydrive.service;
//
// import java.util.*;
// import org.joda.time.DateTime;
//
//
// import static org.junit.Assert.*;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
//
// import mockit.Expectations;
// import mockit.Verifications;
// import mockit.Mocked;
// import mockit.integration.junit4.JMockit;
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
// @RunWith(JMockit.class)
// public class ReadFileTest extends AbstractServiceTest {
//
// 	private static final String userName = "claudia";
// 	private static final String fileName = "file.txt";
// 	Random rand = new Random();
//     long randomtoken = rand.nextLong();
//
// 	@Mocked
// 	private MyDrive md;
//
//
// 	public void populate(){
// 		md = MyDrive.getInstance();
// 	}
//
// 	@Test
// 	public void success() {
// 		new Expectations(){
// 			{
// 				md.getFileNameByUser(userName);
// 				result = fileName;
// 			}
// 		};
// 		ReadFileService service = new ReadFileService(randomtoken, fileName);
// 		service.execute();
// 		assertEquals(service.result(),fileName);
//
// 	}
//
//    //  public void invalidTokenTest() {
//
//
//
//    //      final String userName = "claudia";
//    //      md.createUser(userName);
//    //      long token = md.loginUser(userName,userName);
//
//    //      md.createFile(token, "file.txt", "textfile", "do not read");
//
//    //      Random rand = new Random();
//    //      long randomtoken = rand.nextLong();
//
//
//    //      ReadFileService service = new ReadFileService(randomtoken, "file.txt");
//
//    //      //assertEquals(log.warn(), service.execute());
//
//
//    // }
//
//    //  @Test(expected = FileNotFoundException.class)
//    //  public void fileDoesNotExist(){
//
//    //  	String userName = "claudia";
//    //      md.createUser(userName);
//    //      long token = md.loginUser(userName,userName);
//
//
//    //      ReadFileService service = new ReadFileService(token, "HowToDoAProject.txt");
//
//    //      service.execute();
//    //  }
//
//
// }
