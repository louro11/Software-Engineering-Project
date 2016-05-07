package pt.tecnico.mydrive.exceptions;

public class PermitionException extends FileSystemException{
	
	private static final long serialVersionUID = 1L;
	
	private String _permition;
	
	public PermitionException(String permition){
		_permition = permition;
	}
	
	public String getPermition() {
        return _permition;
    }

	@Override
    public String getMessage(){
		return "Operation failed. This ententy is in" + _permition + "mode";
	}

}