package pt.tecnico.mydrive.presentation;
import java.io.*;
import java.util.*;

public class Login extends MdCommand{

  public Login(Shell sh){

    super.(sh, "login", "login a user");

  }

  public void execute(String[] args){

    if (args.length < 2){

    throw new RuntimeException("USAGE: "+name()+" <username> <password>");

    }

    new LoginService(args[0], args[1]).execute();

  }
  
}
