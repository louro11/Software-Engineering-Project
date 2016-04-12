package pt.tecnico.mydrive.service;

import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.DirectoryCantBeDeletedException;

public class DeleteFileService extends MyDriveService {

    private String _fileName;
    
    private long _token;




    public DeleteFileService(long token, String fileName) {
        
        _fileName = fileName;
        _token = token;
    }
	
	public String getfileName(){
		
		return _fileName;
	}
		
	
	public void setfileName(String fileName){
		
		_fileName = fileName;
	}
		
	
	public long getToken(){
		
		return _token;
	}
		
	
	public void setToken(long token){
		
		_token = token;
		
	}
		
	
    public final void dispatch() throws FileNotFoundException, DirectoryCantBeDeletedException  {
        
       getMydrive().removeFile(getToken(),getfileName()); 
        
    }
}
