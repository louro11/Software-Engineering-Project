package pt.tecnico.mydrive.exceptions;

public class InvalidTypeException extends FileSystemException {
	
	private static final long serialVersionUID = 1L;

	private String _type;

	public InvalidTypeException(String type){
		_type = type;
	}

	public String getInvalidName() {
        return _type;
    }

	@Override
    public String getMessage(){
		return "This type: " + _type + "is inavalid";
	}
}

