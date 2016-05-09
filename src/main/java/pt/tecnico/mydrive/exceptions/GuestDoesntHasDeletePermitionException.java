package pt.tecnico.mydrive.exceptions;

public class GuestDoesntHasDeletePermitionException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public GuestDoesntHasDeletePermitionException(){}

	@Override
    public String getMessage(){
		return "Guest does not has permition to delete.";
	}

}
