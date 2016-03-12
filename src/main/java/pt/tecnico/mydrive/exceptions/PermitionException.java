package pt.tecnico.mydrive.exceptions;

public class PermitionException extends FileSystemException{
	
	private static final long serialVersionUID = 1L;
	
	private String permition;
	
	public PermitionException(String permition){
		permition = permition;
	}
	
	public String getPermition() {
        return permition;
    }

	@Override
    public String getMessage(){
		return "Operation failed. This ententy is in" + permition + "mode";
	}

}