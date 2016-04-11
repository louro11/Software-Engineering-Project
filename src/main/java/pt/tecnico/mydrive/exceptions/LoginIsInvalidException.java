package pt.tecnico.mydrive.exceptions;

public class LoginIsInvalidException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public LoginIsInvalidException(){}

	@Override
	public String getMessage(){
		return "The login is invalid";
	}
}
