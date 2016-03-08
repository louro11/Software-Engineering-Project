package pt.tecnico.mydrive.exceptions;

public class InvalidUserNameExecption extends FileSystemException{
	
	private static final long serialVersionUID = 1L;
	
	private String invalidName;
	
	public InvalidNameExecption(String name){
		invalidName = name;
	}
	
	public String getInvalidName() {
        return invalidName;
    }

	@Override
    public String getMessage(){
		return "The name" + invalidName + "is inavalid. The name must contain only leters and numbers";
	}

}
