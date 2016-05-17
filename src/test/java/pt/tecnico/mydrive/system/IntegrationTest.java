//// package pt.tecnico.mydrive.system;
//// import static org.junit.Assert.*;
//// import org.junit.Test;
//
//// import org.junit.Test;
//// import org.junit.runner.RunWith;
//// import mockit.Mock;
//// import mockit.MockUp;
//// import mockit.integration.junit4.JMockit;
//
//// import java.io.File;
//// import java.util.List;
//// import java.util.ArrayList;
//
//// import org.joda.time.DateTime;
//
//// import org.jdom2.Document;
//// import org.jdom2.input.SAXBuilder;
//// import org.jdom2.output.XMLOutputter;
//// import org.jdom2.output.Format; 
//
//// import pt.tecnico.mydrive.domain.*; // Mockup
//// import pt.tecnico.mydrive.service.*;
//// import pt.tecnico.mydrive.service.dto.*;
//// import pt.tecnico.mydrive.exceptions.*;
//
//// @RunWith(JMockit.class)
//// public class IntegrationTest extends AbstractServiceTest {
//
//// private static final List<String> usernames = new ArrayList<String>();
//
//// 	private static final String username1 = "claudiaamorim", username2 = "MargaridaCorreia", username3 = "HenriqueCarloss", username4 = "PedroNaoVas";
//// 	private static final String filename = "readme";
//// 	private static final String type1 = "textfile", type2 = "app";
//// 	private static final String content = "nhanha", content2 = "newContent";
//
//
//// 	protected void populate(){
//
//// 		usernames.add(username1);
//// 		usernames.add(username4);
//
//// 	}
//
//// 	@Test
//// 	public void success() throws Exception {
//// 		//recebo o token
//// 		//o login precisa de um username, e este username tem que estar na base de dados
//// 		//o mock assume que o utilizador esta na base de dados?
//		
//// 		SuperUser root = new SuperUser("root", "***", "Super user", "rwxdr-x-");
//// 		long token = new Login(root,"rootroot").dispatch();
//// 		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//// 		String tmstp = fmt.print(f.getTimeStamp());
//// 		Directory dir = new Directory("home", 123, new DateTime(), "rwxd----", (User)root);
//// 		List<FileDto> _files;
//// 		//long token, String filename, String type, String content
//
//// 		new CreateFileService(token,filename,type1,content).execute();
//
//// 		new ReadFileService(token, filename).execute();
//
//// 		new CreateFileService(token, filename, type2, content2);
//
//// 		ListDirectoryService lds = new ListDirectoryService(dir);
//// 		lds.execute();
//// 		System.out.println("Files: ");
//// 		for (FileDto fdto : lds.result())
//// 			System.out.println(("Name: " + fdto.getName() + "\nPermissions: " + fdto.getPermissions() + "\nTimestamp: " + tmstp + "\nOwner: " + fdto.getOwner().get_name()));
//// 		assertEquals(lds.result().size(), 4);
//
//// 		new DeleteFileService(filename, token).execute();
//
//// 	}
//// }