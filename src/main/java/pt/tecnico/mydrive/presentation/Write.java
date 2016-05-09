package pt.tecnico.mydrive.presentation;

import pt.tecnico.mydrive.service.WriteFileService;

public class Write extends MdCommand{

	public Write(Shell sh){
		super(sh, "update", "write replaces the file contents of the file referred by the path with text");

	}

	public void execute(String[] args) { 

		if(args.length <2){

			throw new RuntimeException("USAGE: "+name()+ " <path> <text>");

		}
			String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);

			String[] path = args[0].split("/");
			WriteFileService wfs = new WriteFileService(token, path[path.length-1], args[1]);
			wfs.execute();

		}

	}