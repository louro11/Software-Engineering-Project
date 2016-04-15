package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.AccessDeniedException;
import pt.tecnico.mydrive.exceptions.CantWriteToDirectoryException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.PermitionException;

public class WriteFileService extends MyDriveService{
	private String _filename;
	private String _content;
	private long _token;
	
	public WriteFileService(long token, String filename, String content){
		_filename=filename;
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
	       getMydrive().writeToFile(_token,_filename, _content);
        }catch (LoginDoesNotExistException e){
        	System.out.println(e.getMessage());
        }catch (CantWriteToDirectoryException e){
        	System.out.println(e.getMessage());
        }catch (PermitionException  e){
        	System.out.println(e.getMessage());
        }catch (AccessDeniedException e){
        	System.out.println(e.getMessage());
        }
	}
}
