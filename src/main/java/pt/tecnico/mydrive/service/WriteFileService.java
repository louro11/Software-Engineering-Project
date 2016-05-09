package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.MyDrive;

import pt.tecnico.mydrive.exceptions.AccessDeniedException;
import pt.tecnico.mydrive.exceptions.CantWriteToDirectoryException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.PermitionException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;


public class WriteFileService extends MyDriveService{
	private String _filename;
	private String _content;
	private long _token;
	
	public WriteFileService(long token, String filename, String content){
		_filename=filename;
		_content=content;
		_token=token;
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

	public String getcontent(){

		return _content;

	}

	public void setcontent(String content){

		_content = content;

	}
	
	public final void dispatch() throws AccessDeniedException, CantWriteToDirectoryException, LoginDoesNotExistException, PermitionException, FileNotFoundException{

		getMydrive().writeToFile(_token, _filename, _content);

	}

}
