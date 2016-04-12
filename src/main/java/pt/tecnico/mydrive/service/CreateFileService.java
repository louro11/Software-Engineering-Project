package pt.tecnico.mydrive.service;


import pt.tecnico.mydrive.exceptions.InvalidPathException;

public class CreateFileService extends MyDriveService {
	
	private String _filename;
	private String _type;
	private String _content;
	private long _token;
	
	public CreateFileService(long token, String filename, String type, String content){
		_filename=filename;
		_type=type;
		_content=content;
	}
	
	public String getfileName(){
		
		return _filename;
	}
		
	
	public void setfileName(String fileName){
		
		_filename = fileName;
	}
		
	
	public long getToken(){
		
		return _token;
	}
		
	
	public void setToken(long token){
		
		_token = token;
		
	}
	
	public final void dispatch() throws InvalidPathException  {
        
	       getMydrive().createFile(_token,_filename, _type, _content);
	        
	}
}
