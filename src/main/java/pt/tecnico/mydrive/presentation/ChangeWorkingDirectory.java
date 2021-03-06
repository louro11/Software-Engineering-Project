package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.service.ChangeDirectoryService;


public class ChangeWorkingDirectory extends MdCommand{

	public ChangeWorkingDirectory(Shell sh){

		super(sh, "cwd", "Changes the current working directory and prints the path to the new current working directory.");

	}

	public void execute(String[] args) {

		if(args.length > 1){

			throw new RuntimeException("USAGE: "+name()+ " [<path>]");

		}

		if(args.length == 1){

			String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
			ChangeDirectoryService cwd = new ChangeDirectoryService(token, args[0]);
			cwd.execute();
			System.out.println(args[0]);
			
		}

	}

}
