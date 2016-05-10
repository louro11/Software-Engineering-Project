package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.domain.MyDrive;

import pt.tecnico.mydrive.exceptions.AccessDeniedException;
import pt.tecnico.mydrive.exceptions.InvalidPathSizeException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;

public class ChangeDirectoryService extends MyDriveService {
	
	private String _path;
	private long _token;
	
	// //caso em que nao recebe o caminho, nao sei se e preciso
	// public ChangeDirectoryService(long token){

	// 	_path="";
	// 	_token = token;

	// }

	//caso em que recebe o caminho
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
	
	public final void dispatch() throws AccessDeniedException, InvalidPathSizeException, LoginDoesNotExistException, FileNotFoundException{

		_path = getMydrive().changeCurrentDirectory(_token, _path);

	}

	public final String result(){

		return _path;
		
	}
}

