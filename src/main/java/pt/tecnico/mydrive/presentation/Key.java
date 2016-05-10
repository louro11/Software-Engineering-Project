package pt.tecnico.mydrive.presentation;


public class Key extends MdCommand{

  public Key(Shell sh){

    super(sh, "token", "Changes the login session. If the arguments are omitted it prints the current session token");

  }

  public void execute(String[] args){

    if (args.length > 1){

      throw new RuntimeException("USAGE: "+name()+" [<username>]");

    }

    //invocado com username
    if(args.length == 1) {

      String activeUser = this.shell().setActiveUsers(args[0]);
      long token = this.shell().getTokenByUser(activeUser);
      System.out.println(token);
      
    }

    //invocado sem argumentos
   	else {

      String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
      System.out.println(token);
      System.out.println(username);

    }

  }

}
