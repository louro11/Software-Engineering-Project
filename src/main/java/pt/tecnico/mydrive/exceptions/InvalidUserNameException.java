package pt.tecnico.mydrive.exceptions;



public class InvalidUserNameException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	private String invalidName;

	public InvalidUserNameException(String name){
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
