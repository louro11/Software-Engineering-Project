package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.AccessDeniedException;
import pt.tecnico.mydrive.exceptions.CantWriteToDirectoryException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.PermitionException;

public class ReadFileService extends MyDriveService{
	
	private String _filename;
	private long _token;
	
	public ReadFileService(long token, String filename){
		_filename=filename;
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
	       System.out.println(getMydrive().readFile(_token,_filename));
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
