package pt.tecnico.mydrive.presentation;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.mydrive.domain.EnvironmentVar;
import pt.tecnico.mydrive.service.AddEnvironmentVariableService;
import pt.tecnico.mydrive.service.LoginService;

public class Environment extends MdCommand{
	
	public Environment(Shell sh){

	    super(sh, "env", "Creates or changes the value of the environment variable with the given name.\nIf the value is omited, the value associated with the given name is printed.\nIf no arguments are given, all environment variables are printed with their respective value, separated by ’=’");

	  }

	  public void execute(String[] args){

	    if (args.length < 0 || args.length > 2){

	      throw new RuntimeException("USAGE: " + name() + " [<name> [<value>]] ");

	    }

	    //Se nao forem indicados argumentos, imprime todas as variaveis de ambiente e os respetivos valores, separados por '='

	   /*if(args.length == 0){

	
	   }*/

	    //Se o valor for omitido imprime o valor que já lhe tinha sido associado
	    else if(args.length == 1) {

	    	
		    String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
			
		    AddEnvironmentVariableService ls = new AddEnvironmentVariableService(token,args[0],"");
		    ls.execute();
		    
		    List<EnvironmentVar> varArray = new ArrayList<EnvironmentVar>();
		    
		    varArray = ls.result();
		    
		    for(EnvironmentVar var : varArray) {
		          
		          if( var.get_name().equals(args[0]) )
		        	  System.out.print(var.get_value());
		         
		    }
		    
	    }

	    //Recebe uma variavel de ambiente e retorna o valor associado
	   	else {

	   		String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
			
		    AddEnvironmentVariableService ls = new AddEnvironmentVariableService(token,args[0],"");
		    ls.execute();
		    
		    List<EnvironmentVar> varArray = new ArrayList<EnvironmentVar>();
		    
		    varArray = ls.result();
		    
		    
		    for(EnvironmentVar var : varArray) {
		          
		          System.out.print(var.getDescription());
		         
		     }

	    }

	  }
	
	

}
