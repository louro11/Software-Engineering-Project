package pt.tecnico.mydrive.exceptions;

public class UserNameAlreadyExistsException extends FileSystemException{

	private static final long serialVersionUID = 1L;
	
	private String takenUsername;
	
	public UserNameAlreadyExistsException(String username){
		takenUsername = username;
	}
	
	public String getTakenUsername() {
        return takenUsername;
    }
    
    @Override
    public String getMessage(){
		return "The username" + takenUsername + "is already taken";
	}
}
