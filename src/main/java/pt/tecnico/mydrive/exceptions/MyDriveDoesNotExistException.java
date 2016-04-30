package pt.tecnico.mydrive.exceptions;

public class MydriveDoesNotExistException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public MydriveDoesNotExistException(){}

	@Override
    public String getMessage(){
		return "Mydrive does not exist.";
	}

}
