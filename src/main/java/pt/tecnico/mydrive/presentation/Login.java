package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.service.LoginService;

public class Login extends MdCommand{

  public Login(Shell sh){

    super(sh, "login", "login a user");

  }

  public void execute(String[] args){

    if (args.length < 1 || args.length > 2){

      throw new RuntimeException("USAGE: "+name()+" <username> [<password>]");

    }

    if(args.length == 2) {

   		LoginService ls = new LoginService(args[0], args[1]);
      ls.execute();
      this.shell().addUsers(args[0], ls.result());
      this.shell().setActiveUsers(args[0]);

    }

   	else {

   		LoginService ls = new LoginService(args[0]);
      ls.execute();
      this.shell().addUsers(args[0], ls.result());
      this.shell().setActiveUsers(args[0]);

    }

  }

}
