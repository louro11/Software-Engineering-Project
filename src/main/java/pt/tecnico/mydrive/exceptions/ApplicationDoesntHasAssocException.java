package pt.tecnico.mydrive.exceptions;

public class ApplicationDoesntHasAssocException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public ApplicationDoesntHasAssocException(){}

	@Override
    public String getMessage(){
		return "Associtaion does not has association.";
	}

}
