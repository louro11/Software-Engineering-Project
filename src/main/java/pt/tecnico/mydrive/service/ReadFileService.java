package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.MyDrive;

import pt.tecnico.mydrive.exceptions.AccessDeniedException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.CantReadDirectoryException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.PermitionException;

public class ReadFileService extends MyDriveService{
	
	private String _content;
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
	
	public final void dispatch() throws AccessDeniedException, LoginDoesNotExistException, CantReadDirectoryException, FileNotFoundException, PermitionException{

		_content = getMydrive().readFile(_token, _filename);
	}

	public final String result(){
		return _content;
	}

}
