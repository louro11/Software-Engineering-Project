package pt.tecnico.mydrive.exceptions;

public class WrongPasswordException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public WrongPasswordException(){}

	@Override
    public String getMessage(){
		return "The password is incorrect! Please try again";
	}

}
