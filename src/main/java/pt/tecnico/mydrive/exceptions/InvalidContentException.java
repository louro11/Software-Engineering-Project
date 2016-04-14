package pt.tecnico.mydrive.exceptions;

public class InvalidContentException extends FileSystemException{
	private static final long serialVersionUID = 1L;

	private String _content;

	public InvalidContentException(String content){
		_content = content;
	}

	public String getContent() {
        return _content;
    }

	@Override
    public String getMessage(){
		return "The content \"" + _content + "\" is invalid for thi type of file. The content must be empty";
	}
}
