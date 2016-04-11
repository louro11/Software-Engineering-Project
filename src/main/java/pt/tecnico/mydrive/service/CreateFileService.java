package pt.tecnico.mydrive.service;


import pt.tecnico.mydrive.exceptions.InvalidPathException;

public class CreateFileService extends MyDriveService {
	
	private String _filename;
	private String _type;
	private String _content;
	
	public CreateFileService(String filename, String type, String content){
		_filename=filename;
		_type=type;
		_content=content;
	}
	
	public final void dispatch() throws InvalidPathException  {
        
	       //getMydrive().createFile(_filename, _type, _content);
	        
	}
}
