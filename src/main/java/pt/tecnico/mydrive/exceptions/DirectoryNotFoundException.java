package pt.tecnico.mydrive.exceptions;

public class DirectoryNotFoundException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	private String dirName;

	public DirectoryNotFoundException(String name){

		dirName = name;

	}

	public String getDirName() {
        return fileName;
    }

	@Override
    public String getMessage(){
		return "The directory " + dirName + " was not found";
	}

}
