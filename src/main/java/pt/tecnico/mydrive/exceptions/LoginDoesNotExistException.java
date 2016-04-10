package pt.tecnico.mydrive.exceptions;

public class LoginDoesNotExistException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public LoginDoesNotExistException(){}

	@Override
	public String getMessage(){
		return "Login does not exist";
	}
}
