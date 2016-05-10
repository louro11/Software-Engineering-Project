package pt.tecnico.mydrive.presentation;

import java.util.ArrayList;
import java.util.List;

import pt.tecnico.mydrive.domain.EnvironmentVar;
import pt.tecnico.mydrive.service.AddEnvironmentVariableService;
import pt.tecnico.mydrive.service.LoginService;

public class Environment extends MdCommand{
	
	public Environment(Shell sh){

	    super(sh, "env", "for creating and editing environment variables on the current session");

	  }

	  public void execute(String[] args){

	    if (args.length < 0 || args.length > 2){

	      throw new RuntimeException("USAGE: " + name() + " [<name> [<value>]] ");

	    }

	    if(args.length == 1) {

	    	
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
