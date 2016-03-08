package pt.tecnico.mydrive.exceptions;


public abstract class FileSystemException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FileSystemException(){}
	
	public FileSystemException(String msg){
		super(msg);
	}
}

