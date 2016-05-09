package pt.tecnico.mydrive.exceptions;

public class GuestDoesntHasPasswordException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public GuestDoesntHasPasswordException(){}

	@Override
    public String getMessage(){
		return "Guest does not has password.";
	}

}
