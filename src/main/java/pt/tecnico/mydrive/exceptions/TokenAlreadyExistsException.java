package pt.tecnico.mydrive.exceptions;



public class TokenAlreadyExistsException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public TokenAlreadyExistsException(){}

	@Override
    public String getMessage(){
		return "The token already exists";
	}

}
