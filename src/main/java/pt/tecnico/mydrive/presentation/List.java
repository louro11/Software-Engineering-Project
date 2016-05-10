package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.service.ListDirectoryService;

import pt.tecnico.mydrive.service.dto.FileDto;

public class List extends MdCommand{

	public List(Shell sh){

		super(sh, "ls", "lists the directory specified by the path directory, if the path is omitted it lists the current directory");

	}

	public void execute(String[] args) {

		if(args.length > 1){

			throw new RuntimeException("USAGE: "+name()+ " <path>");

		}

		//caso em que nao recebe path
		if(args.length == 0){

			String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
			ListDirectoryService cwd = new ListDirectoryService(token);
			cwd.execute();
			for (String s : cwd.result())
				System.out.println(s);
			System.out.println("use 'list <path>' to list a complete entry information");

		}

		//caso em que recebe path
		else{

			String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
			ListDirectoryService cwd = new ListDirectoryService(token, args[0]);
			cwd.execute();
			System.out.println("Entries for "+args[0]);
			for (FileDto f : cwd.result())
				System.out.println(f.getName() + " " + f.getPermissions() + " " +
								f.getOwner() + " " + f.getTimeStamp());

		}

	}

}
