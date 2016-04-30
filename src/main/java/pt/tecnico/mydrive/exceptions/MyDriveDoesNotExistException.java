package pt.tecnico.mydrive.exceptions;

public class MyDriveDoesNotExistException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public MyDriveDoesNotExistException(){}

	@Override
    public String getMessage(){
		return "MyDrive does not exist.";
	}

}
