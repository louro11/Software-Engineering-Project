package pt.tecnico.mydrive.presentation;


public class Key extends MdCommand{

  public Key(Shell sh){

    super(sh, "token", "Changes the login session. If the armuments are omitted it prints the current session token");

  }

  public void execute(String[] args){

    if (args.length > 1){

      throw new RuntimeException("USAGE: "+name()+" [<username>]");

    }

    if(args.length == 1) {

      this.shell().setActiveUsers(args[0]);

    }

   	else {

      String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
      System.out.println(token);

    }

  }

}
