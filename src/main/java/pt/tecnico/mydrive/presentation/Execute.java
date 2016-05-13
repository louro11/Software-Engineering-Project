package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.service.ExecuteFileService;

public class Execute extends MdCommand{

	//closes #36
	
	public Execute(Shell sh){
		super(sh, "do", "Execute the file identified by the given path with the given arguments");

	}

	public void execute(String[] args) { 

		if(args.length < 1){

			throw new RuntimeException("USAGE: "+name()+ " <path> [<args>]");

		}

		//quando recebe args
		if(args.length >= 2){

			String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
			
			String[] serviceArgs = new String[args.length-1];

			for(int i=1; i < serviceArgs.length - 1; i++){

				serviceArgs[i] = args[i];

			}

			ExecuteFileService efs = new ExecuteFileService(token, args[0], serviceArgs );
			efs.execute();

		}
		//apenas recebe um path

		else{

			String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
			ExecuteFileService efs = new ExecuteFileService(token, args[0]);
			efs.execute();

		}

	}
}
