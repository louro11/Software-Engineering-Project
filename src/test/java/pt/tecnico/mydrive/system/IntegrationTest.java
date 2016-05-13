package pt.tecnico.mydrive.system;
import static org.junit.Assert.*;
import org.junit.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import mockit.Mock;
import mockit.MockUp;
import mockit.integration.junit4.JMockit;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.jdom2.output.Format;

import pt.tecnico.mydrive.domain; // Mockup
import pt.tecnico.mydrive.service.*;
import pt.tecnico.mydrive.service.dto.*;
import pt.tecnico.mydrive.exception.*;

@RunWith(JMockit.class)
public class IntegrationTest extends AbstractServiceTest {

/private static final List<String> usernames = new ArrayList<String>();

	private static final String username1 = "claudiaamorim";
	private static final String filename = "readme";
	private static final String type1 = "textfile";
	private static final String content = "nhanha";


	protected void populate(){

		usernames.add(username1);
		

	}

	@Test
	public void success() throws Exception {
		//recebo o token
		//o login precisa de um username, e este username tem que estar na base de dados
		//o mock assume que o utilizador esta na base de dados?
		long token = new Loin(username1).execute();

		//long token, String filename, String type, String content

		new CreateFileService(token,filename,type1,content).execute();

	}
}