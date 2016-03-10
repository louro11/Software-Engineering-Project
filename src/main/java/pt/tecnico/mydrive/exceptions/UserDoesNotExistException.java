package pt.tecnico.mydrive.exceptions;

public class UserDoesNotExistException extends FileSystemException{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	public UserDoesNotExistException(String usrname){
		username = usrname;
	}
	
	public String getUsername(){
		return username;
	}
	
	@Override
	public String getMessage(){
		return "User" + username + "does not exist";
	}
}
