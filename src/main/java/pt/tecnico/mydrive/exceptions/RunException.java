package pt.tecnico.mydrive.exceptions;

public class RunException extends FileSystemException{
	
	private static final long serialVersionUID = 1L;

	
	public RunException() {
	}
	
	@Override
	public String getMessage(){
		return "Runtime Error!";
	}
	
}
