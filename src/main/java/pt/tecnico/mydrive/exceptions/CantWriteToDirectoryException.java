package pt.tecnico.mydrive.exceptions;

public class CantWriteToDirectoryException extends FileSystemException{
	private static final long serialVersionUID = 1L;
	
	private String _filename;
	
	public CantWriteToDirectoryException(String filename){
		_filename = filename;
	}
	
	public String getFilename() {
        return _filename;
    }

	@Override
    public String getMessage(){
		return "Can't write to directory "+_filename;
	}
}
