package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.service.WriteFileService;

public class Write extends MdCommand{

	public Write(Shell sh){
		super(sh, "update", "write replaces the file contents of the file referred by the path with text");

	}

	public void execute(String[] args) { 

		if(args.length <2){

			throw new RuntimeException("USAGE: "+name()+ "<path> <text>");

		}
			String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);

			WriteFileService wfs = new WriteFileService(token, args[0] , args[1]);
			wfs.execute();

		}

	}