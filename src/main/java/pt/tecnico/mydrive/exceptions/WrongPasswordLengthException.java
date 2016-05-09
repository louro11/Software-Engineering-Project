package pt.tecnico.mydrive.exceptions;

public class WrongPasswordLengthException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public WrongPasswordLengthException(){}

	@Override
    public String getMessage(){
		return "The password is not long enough. Min: 8 characters";
	}

}
