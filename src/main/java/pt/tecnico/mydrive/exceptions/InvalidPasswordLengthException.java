package pt.tecnico.mydrive.exceptions;



public class InvalidPasswordLengthException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public InvalidPasswordLengthException(){}

	@Override
    public String getMessage(){
		return "The password is too short. It must contain at least 8 characters or numbers";
	}

}
