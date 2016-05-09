package pt.tecnico.mydrive.exceptions;

public class GuestDoesntHasWritePermitionException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public GuestDoesntHasWritePermitionException(){}

	@Override
    public String getMessage(){
		return "Guest does not has permition to wirte.";
	}

}
