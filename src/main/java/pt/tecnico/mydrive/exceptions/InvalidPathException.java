package pt.tecnico.mydrive.exceptions;

public class InvalidPathException extends FileSystemException{
	
	private static final long serialVersionUID = 1L;
	
	private String invalidPath;
	
	public InvalidPathException (String path){
		invalidPath = path;
	}
	
	public String getInvalidName() {
        return invalidPath;
    }

	@Override
    public String getMessage(){
		return "This path" + invalidPath + "is invalid";
	}

}
