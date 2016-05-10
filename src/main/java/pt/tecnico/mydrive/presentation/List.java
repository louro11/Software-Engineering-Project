package pt.tecnico.mydrive.presentation;

import java.util.*;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import pt.tecnico.mydrive.domain.File;
import pt.tecnico.mydrive.service.ListDirectoryService;

import pt.tecnico.mydrive.service.dto.FileDto;

public class List extends MdCommand{

	public List(Shell sh){

		super(sh, "ls", "Lists the complete entry information of each directory referred to by the given path or the entries of the current working directory, if the path is omitted");

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
			
			
			java.util.List<FileDto> fileArray = cwd.result();

		    for(FileDto f : fileArray) {
		    	
		    	DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		    	String tmstp = fmt.print(f.getTimeStamp());
			
		    	System.out.println("Name: " + f.getName() + "\nPermissions: " + f.getPermissions() + "\nTimestamp: " + tmstp + "\nOwner: " + f.getOwner().get_name());
			
		    }	
		    	
			System.out.println("use 'list <path>' to list a complete entry information");

		}

		//TODO:XXX -> O List Directory Service ainda nao esta a receber o path!!! caso em que recebe path  
		else{

			String activeUser = this.shell().getActiveUser();
			long token = this.shell().getTokenByUser(activeUser);
			
			ListDirectoryService cwd = new ListDirectoryService(token);   //favor alterar aqui mininos
			cwd.execute();
			
			
			java.util.List<FileDto> fileArray = cwd.result();

		    for(FileDto f : fileArray) {
		    	
		    	DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		    	String tmstp = fmt.print(f.getTimeStamp());
			
		    	System.out.println("Name: " + f.getName() + "\nPermissions: " + f.getPermissions() + "\nTimestamp: " + tmstp + "\nOwner: " + f.getOwner().get_name());
			
		    }
		    
		}

	}

}
