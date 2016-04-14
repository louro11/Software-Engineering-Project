package pt.tecnico.mydrive.exceptions;

public class FileAlreadyExistsException extends FileSystemException{
	
	private static final long serialVersionUID = 1L;

	private String _filename;

	public FileAlreadyExistsException(String filename){
		_filename = filename;
	}

	public String getFilename() {
        return _filename;
    }

	@Override
    public String getMessage(){
		return "This file: " + _filename + "already exists";
	}
}
