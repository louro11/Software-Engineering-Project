package pt.tecnico.mydrive.exceptions;

public class InvalidNameException extends FileSystemException{
	
	private static final long serialVersionUID = 1L;
	
	private String invalidName;
	
	public InvalidNameException(String name){
		invalidName = name;
	}
	
	public String getInvalidName() {
        return invalidName;
    }

	@Override
    public String getMessage(){
		return "This name already exists in this directory";
	}

}
