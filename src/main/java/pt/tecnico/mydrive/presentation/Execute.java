// package pt.tecnico.mydrive.presentation;

// import pt.tecnico.mydrive.service.ExecuteFileService;

// public class Execute extends MdCommand{

// 	public Execute(Shell sh){
// 		super(sh, "do", "Execute the file identified by the given path with the given arguments");

// 	}

// 	public void execute(String[] args) { 

// 		if(args.length < 3){

// 			throw new RuntimeException("USAGE: "+name()+ " <args>");

// 		}
// 			ExecuteFileService efs = new ExecuteFileService(args[1], args);
// 			efs.execute();

// 		}

// 	}
