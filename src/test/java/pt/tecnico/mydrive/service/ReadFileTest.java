package pt.tecnico.mydrive.service;

import java.util.*;
import org.joda.time.DateTime;


import static org.junit.Assert.*;

import org.junit.Test;

import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;


import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;


public class ReadFileTest extends AbstractServiceTest {

	private MyDrive md = MyDrive.getInstance();


	public void populate(){}

	@Test
    public void invalidTokenTest() {



        final String userName = "claudia";
        md.createUser(userName);
        long token = md.loginUser(userName,userName);

        md.createFile(token, "file.txt", "textfile", "do not read");

        Random rand = new Random();
        long randomtoken = rand.nextLong();


        ReadFileService service = new ReadFileService(randomtoken, "file.txt");

        //assertEquals(log.warn(), service.execute());


   }

    @Test(expected = FileNotFoundException.class)
    public void fileDoesNotExist(){

    	String userName = "claudia";
        md.createUser(userName);
        long token = md.loginUser(userName,userName);


        ReadFileService service = new ReadFileService(token, "HowToDoAProject.txt");

        service.execute();
    }


}
