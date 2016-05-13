package pt.tecnico.mydrive.service;

import java.lang.reflect.InvocationTargetException;

import pt.tecnico.mydrive.exceptions.AccessDeniedException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.LoopFoundException;

public class ExecuteFileService extends MyDriveService {
	
	private long _token;
	private String _path;
	private String[] _args;
	
	public ExecuteFileService(long token, String path, String[] args) {
		_token = token;
		_path = path;
		_args = args;

	}

	public ExecuteFileService(long token, String path) {
		_token = token;
		_path = path;
		
	}

	public void setToken(long tken){

   		 _token = tken;

  	}

	public void setPath(String path){

   		 _path = path;

  	}

	public void setArgs(String[] args){

   		 _args = args;

  	}

  	public long getToken(){

    	return _token;

  	}

   	public String getPath(){

    	return _path;

  	}

     public String[] getArgs(){

    	return _args;

  	}
    
	public final void dispatch()throws FileNotFoundException, LoopFoundException, InvalidPathException, AccessDeniedException, LoginDoesNotExistException {
		
		//WARNING: alterado por rafa: nao sei se e suposto estar assim, quem estiver com o issue tem que olhar melhorzinho
		
	    getMydrive().executeFile(_token, _path, _args);
		
	}

}
