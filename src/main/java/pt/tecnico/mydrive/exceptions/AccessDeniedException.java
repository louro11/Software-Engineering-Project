package pt.tecnico.mydrive.exceptions;

public class AccessDeniedException extends FileSystemException{
	private static final long serialVersionUID = 1L;
	
	private String _username;
	
	public AccessDeniedException(String username){
		_username = username;
	}
	
	public String getUsername() {
        return _username;
    }

	@Override
    public String getMessage(){
		return "Access Denied. User" +_username + "can't access this file";
	}
}
