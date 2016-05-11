package pt.tecnico.mydrive.exceptions;

public class LoopFoundException extends FileSystemException{
	private static final long serialVersionUID = 1L;

	public LoopFoundException(){}

	@Override
	public String getMessage(){
		return "Loop found in links";
	}

}
