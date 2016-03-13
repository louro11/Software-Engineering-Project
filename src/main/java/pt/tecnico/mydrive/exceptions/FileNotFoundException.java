package pt.tecnico.mydrive.exceptions;

public class FileNotFoundException extends FileSystemException{
	
	private static final long serialVersionUID = 1L;
	
	public FileNotFoundException(){}

	@Override
    public String getMessage(){
		return "File not found";
	}

}