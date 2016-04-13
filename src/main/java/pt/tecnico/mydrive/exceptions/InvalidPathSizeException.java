package pt.tecnico.mydrive.exceptions;

public class InvalidPathSizeException extends FileSystemException{

	
	private static final long serialVersionUID = 1L;
	
	
	public InvalidPathSizeException (){
	
	}
	
	@Override
    public String getMessage(){
		return "This path size is invalid";
	}

}
