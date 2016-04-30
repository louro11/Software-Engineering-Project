package pt.tecnico.mydrive.exceptions;

public class FileNotFoundException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	private String fileName;

	public FileNotFoundException(String name){

		fileName = name;

	}

	public String getFileName() {
        return fileName;
    }

	@Override
    public String getMessage(){
		return "The file " + fileName + " was not found";
	}

}
