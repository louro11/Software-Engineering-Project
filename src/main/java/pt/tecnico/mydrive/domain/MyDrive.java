package pt.tecnico.mydrive.domain;

public class MyDrive extends MyDrive_Base {
    
    public MyDrive() {
        super();
    }
    


public String PrintFiles(String path){

	return getFilesystem.PrintFiles(path);

}

public String readfile(String path){
	return getFilesystem.readfile(path);
}

public void removeFile(String path){

	return getFilesystem.removeFile(path);
}

}