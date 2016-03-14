package pt.tecnico.mydrive.exceptions;

import pt.tecnico.mydrive.exceptions.FileSystemException;

public class ImportDocumentException extends FileSystemException {

    private static final long serialVersionUID = 1L;

    public ImportDocumentException() {
   
    }
    
    @Override
    public String getMessage(){
		return "Error in importing XML";
	}
}
