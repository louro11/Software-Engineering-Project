package pt.tecnico.mydrive.service;


import pt.tecnico.mydrive.exceptions.FileAlreadyExistsException;
import pt.tecnico.mydrive.exceptions.InvalidContentException;
import pt.tecnico.mydrive.exceptions.InvalidPathSizeException;
import pt.tecnico.mydrive.exceptions.InvalidTypeException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;

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
	
	public final void dispatch(){
        try{
	       getMydrive().createFile(_token,_filename, _type, _content);
        }catch (LoginDoesNotExistException e){
        	System.out.println(e.getMessage());
        }catch (InvalidPathSizeException e){
        	System.out.println(e.getMessage());
        }catch (InvalidContentException e){
        	System.out.println(e.getMessage());
        }catch (InvalidTypeException e){
        	System.out.println(e.getMessage());
        }catch (FileAlreadyExistsException e){
        	System.out.println(e.getMessage());
        }
	}
}
