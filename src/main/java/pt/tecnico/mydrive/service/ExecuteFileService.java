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

	@Override
	protected void dispatch() throws MyDriveException {
		try{
			getMydrive().executeFile(_token, _path, _args);
		}catch(MyDriveException e){
			throw e;
		}

	}

}
