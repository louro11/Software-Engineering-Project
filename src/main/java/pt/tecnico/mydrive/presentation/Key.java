package pt.tecnico.mydrive.presentation;


public class Key extends MdCommand{

  public Key(Shell sh){

    super(sh, "token", "The interpreter can keep several user sessions active simultaneously.\nThe token commands allows the interpreter to switch between active sessions.\nThe command prints the token of current active user.\nWhen invoked without arguments, it prints the token and user name of current active user.\nWhen a user is given, it changes the current active user tothe given user, updates the current token accordingly and prints its value.");

  }

  public void execute(String[] args){

    if (args.length > 1){

      throw new RuntimeException("USAGE: "+name()+" [<username>]");

    }

    //invocado com username
    if(args.length == 1) {

      String activeUser = this.shell().getActiveUser();
      long token = this.shell().getTokenByUser(activeUser);
      System.out.println(token);
      
    }

    //invocado sem argumentos
   	else {

      String activeUser = this.shell().getActiveUser();
	  long token = this.shell().getTokenByUser(activeUser);
      System.out.println(token);
      System.out.println(activeUser);

    }

  }

}
