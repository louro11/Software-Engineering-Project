package pt.tecnico.mydrive.exceptions;

public class DirectoryNotFoundException extends FileSystemException{

	private static final long serialVersionUID = 1L;

	private String dirName;

	public DirectoryNotFoundException(){}

	public String getDirName() {
        return dirName;
    }

	@Override
    public String getMessage(){
		return "The directory " + getDirName() + " was not found";
	}

}
