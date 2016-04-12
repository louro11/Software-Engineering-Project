package pt.tecnico.mydrive.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import pt.tecnico.mydrive.domain.MyDrive;
import pt.tecnico.mydrive.domain.User;
import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.domain.Directory;
import pt.tecnico.mydrive.domain.Login;

public class DeleteFileTest extends AbstractServiceTest{

    MyDrive md = MyDrive.getInstance();

    protected void populate(){


      md.createUser("Harry");
      md.createUser("Hermione");

      long token_hermione = md.loginUser("Hermione","Hermione");

      long token_harry = md.loginUser("Harry","Harry");

      md.createFile(token_harry,"GinnyLeaked", "directory","");

      md.changeCurrentDirectory(token_harry,"GinnyLeaked");

      md.createFile(token_harry, "RonLeaked", "directory", "");

      md.createFile(token_harry, "theFatningGinny", "link", "/Harry/GinnyLeaked");



    }


    //  O assert nao funciona bem com metodos void, temos de mudar o execute para devolver uma string?
    // @Test
    // public void success(){
    //     final String userName = "Harry";
    //
    //     long token_harry = md.loginUser(userName, userName);
    //
    //     DeleteFileService dfs = new DeleteFileService(token_harry,"GinnyLeaked");
    //     assertNull( dfs.execute()); //FALSE ou NULL ASSERT
    //
    // }
    //
    // @Test
    // public void successNameWithPath(){
    //     final string userName = "Harry";
    //
    //     long token_harry = md.loginUser(userName, userName);
    //
    //     DeleteFileService dfs = new DeleteFileService(token_harry,"/GinnyLeaked/RonLeaked");
    //     assertNull( dfs.execute()); //FALSE ou NULL ASSERT
    //
    // }
    //
    // @Test
    // public void successWithoutLoginUser(){
    //     final string userName = "Ron";
    //
    //     long token_harry = md.loginUser(userName, userName);
    //
    //     DeleteFileService dfs = new DeleteFileService(token_harry,"GinnyLeaked");
    //     assertEquals("Login does not exist", dfs.execute());
    //
    // }
    //
    // @Test
    // public void successWithNotOwnerUser(){
    //     final string userName = "Hermione";
    //
    //     long token_harry = md.loginUser(userName, userName);
    //
    //     DeleteFileService dfs = new DeleteFileService(token_harry,"GinnyLeaked");
    //     assertEquals("This user: " + userName + " has no permission to delete this file. ", dfs.execute());
    //
    // }
    //
    // @Test
    // public void successWithNotExistingFile(){
    //     final string userName = "Harry";
    //
    //     long token_harry = md.loginUser(userName, userName);
    //
    //     DeleteFileService dfs = new DeleteFileService(token_harry,"GinnyNotLeaked");
    //     assertEquals("GinnyNotLeaked", dfs.execute());
    //
    // }
    //
    // @Test
    // public void successWithRepeatDelete(){
    //     final string userName = "Harry";
    //
    //     long token_harry = md.loginUser(userName, userName);
    //       DeleteFileService dfs = new DeleteFileService(token_harry,"GinnyLeaked");
    //       dfs.execute();
    //
    //     assertEquals("GinnyLeaked", dfs.execute());
    //
    // }
    //
    // @Test
    // public void successWithRecursiveDelete(){
    //     final string userName = "Harry";
    //
    //     long token_harry = md.loginUser(userName, userName);
    //       DeleteFileService dfs = new DeleteFileService(token_harry,"GinnyLeaked");
    //       dfs.execute();
    //
    //
    //     md.changeCurrentDirectory(token_harry, "GinnyLeaked");
    //
    //     dfs = new DeleteFileService(token_harry,"RonLeaked");
    //
    //     assertFalse("RonLeaked", dfs.execute()); //FALSE ou NULL ASSERT
    //
    // }









}
