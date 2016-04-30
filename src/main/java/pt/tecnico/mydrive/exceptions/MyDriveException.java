package pt.tecnico.mydrive.exceptions;

public class MyDriveException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	public MyDriveException(){}

	@Override
    public String getMessage(){
		return "MyDrive does not exist.";
	}

}
