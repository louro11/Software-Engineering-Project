package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.MyDrive;

import pt.tecnico.mydrive.exceptions.FileAlreadyExistsException;
import pt.tecnico.mydrive.exceptions.InvalidContentException;
import pt.tecnico.mydrive.exceptions.InvalidPathSizeException;
import pt.tecnico.mydrive.exceptions.InvalidTypeException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.PermitionException;

public class CreateFileService extends MyDriveService {
	
	private String _filename;
	private String _type;
	private String _content;
	private long _token;
	
	public CreateFileService(long token, String filename, String type, String content){
		_token=token;
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


	public String getcontent(){

		return _content;

	}

	public void setcontent(String content){

		_content = content;

	}


	public String gettype(){

		return _type;

	}

	public void settype(String type){

		_type = type;

	}
	
	public final void dispatch() throws InvalidPathSizeException, LoginDoesNotExistException, InvalidContentException,InvalidTypeException,FileAlreadyExistsException, PermitionException{


		getMydrive().createFile(this.getToken(), this.getfileName(), this.gettype(), this.getcontent());

	}
}
