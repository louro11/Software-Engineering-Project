package pt.tecnico.mydrive.service;


import java.util.*;
import org.joda.time.DateTime;


import pt.tecnico.mydrive.exceptions.FileAlreadyExistsException;
import pt.tecnico.mydrive.exceptions.InvalidPathSizeException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;

public class ChangeDirectoryService extends MyDriveService {
	
	private String _path;
	private long _token;
	
	public ChangeDirectoryService(long token, String path){

		_path=path;
		_token=token;

	}
	
	public String getPath(){
		
		return _path;
	}
		
	
	public void setPath(String path){
		
		_path = path;
	}
		
	
	public long getToken(){
		
		return _token;
	}
		
	
	public void setToken(long token){
		
		_token = token;
		
	}
	
	public final void dispatch(){
        try{
	       getMydrive().changeCurrentDirectory(_token,_path);
        }catch (LoginDoesNotExistException e){
        	System.out.println(e.getMessage());
        }catch (InvalidPathSizeException e){
        	System.out.println(e.getMessage());   
        }catch (FileNotFoundException e){
        	System.out.println(e.getMessage());
        }
	}
}
