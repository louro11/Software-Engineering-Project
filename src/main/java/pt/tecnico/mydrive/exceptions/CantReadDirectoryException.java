package pt.tecnico.mydrive.exceptions;

public class CantReadDirectoryException extends FileSystemException{
	private static final long serialVersionUID = 1L;
	
	private String _filename;
	
	public CantReadDirectoryException(String filename){
		_filename = filename;
	}
	
	public String getFilename() {
        return _filename;
    }

	@Override
    public String getMessage(){
		return "Can't read directory "+_filename;
	}
}