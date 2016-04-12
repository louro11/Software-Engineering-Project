package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;


import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;


public class CreateDirectoryTest extends AbstractServiceTest {


	MyDrive md = MyDrive.getInstance();

    protected void populate() {


		md.createUser("Henrip");

		long token_rip = md.loginUser("Henrip","Henrip");

		//não deve de estar bem -> md.createFile(token_rip, "D", "directory", "");

    //não deve de estar bem -> md.createFile(token_rip, "HappyD", "directory", "Upsi daysi! ( ͡° ͜ʖ ͡°) ");

  }
	/** Todos os testes success tem de ter os Assert a funcionar **/


    //@Test
    //public void success_1() {


        //final String userName = "Henrip";
        //long token_rip = md.loginUser(userName,userName);

        ////CreateFileService service = new ChangeFileService(token_rip, "D", "dir", "");
        ////service.execute();

    //}

    //@Test
    //public void success_2() {


        //final String userName = "Henrip";
        //long token_rip = md.loginUser(userName,userName);

        ////CreateFileService service = new ChangeFileService(token_rip, "HappyD", "dir", "Upsi daysi! ( ͡° ͜ʖ ͡°) ");
        ////service.execute();

    //}
}
