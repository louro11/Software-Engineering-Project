package pt.tecnico.mydrive.service;
import pt.tecnico.mydrive.exceptions.MyDriveException;

public class ExecuteFileService extends MyDriveService{
	
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

	protected void dispatch() throws MyDriveException {
		try{
			getMydrive().executeFile(_token, _path, _args);
		}catch(MyDriveException e){
			throw e;
		}

	}

}
