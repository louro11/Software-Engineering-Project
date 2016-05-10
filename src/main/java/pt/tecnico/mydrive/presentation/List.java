// package pt.tecnico.mydrive.presentation;
//
// import pt.tecnico.mydrive.service.ListDirectoryService;
//
//
// public class List extends MdCommand{
//
// 	public List(Shell sh){
// 		super(sh, "ls", "lists the directory specified by the path directory, if the path is omitted it lists the current directory");
//
// 	}
//
// 	public void execute(String[] args) {
//
// 		if(args.length > 1){
//
// 			throw new RuntimeException("USAGE: "+name()+ " <path>");
//
// 		}
//
// 		if(args.length == 1){
//
// 			String activeUser = this.shell().getActiveUser();
// 			long token = this.shell().getTokenByUser(activeUser);
//
// 			ListDirectoryService cwd = new ListDirectoryService(token);
// 			System.out.println(args[0]);
// 			cwd.execute();
//
// 		}
//
// 		else{
//
//
// 		}
//
// 	}
//
// }
