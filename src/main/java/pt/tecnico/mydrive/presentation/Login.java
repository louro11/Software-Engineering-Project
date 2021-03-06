package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.service.LoginService;

public class Login extends MdCommand{

  public Login(Shell sh){

    super(sh, "login", "Performs the user login");

  }

  public void execute(String[] args){

    if (args.length < 1 || args.length > 2){

      throw new RuntimeException("USAGE: "+name()+" <username> [<password>]");

    }

    //caso em que recebe password
    if(args.length == 2) {

   		LoginService ls = new LoginService(args[0], args[1]);
      ls.execute();
      this.shell().addUsers(args[0], ls.result());
      this.shell().setActiveUsers(args[0]);

    }

    //caso em que nao recebe password
   	else {

   		LoginService ls = new LoginService(args[0]);
      ls.execute();
      this.shell().addUsers(args[0], ls.result());
      this.shell().setActiveUsers(args[0]);

    }

  }

}
