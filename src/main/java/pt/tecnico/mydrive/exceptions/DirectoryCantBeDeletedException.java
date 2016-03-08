package pt.tecnico.mydrive.exceptions;

public class DirectoryCantBeDeletedNameExecption extends FileSystemException{
	
	private static final long serialVersionUID = 1L;
	
	public DirectoryCantBeDeletedException(){}

	@Override
    public String getMessage(){
		return "This directory can't be deleted because it is not empty";
	}

}
