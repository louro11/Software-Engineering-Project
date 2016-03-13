package pt.tecnico.mydrive.domain;

public class MyDrive extends MyDrive_Base {

    public MyDrive(){
    	FileSystem fs = new FileSystem();

    	setCurrentuser(fs.getRoot());
    	setCurrentdirectory(fs.getMaindir());
    	setFilesystem(fs);
    }
    


public String PrintFiles(String path){

	return getFilesystem().PrintFiles(path);

}

public String readfile(String path){
	return getFilesystem().readfile(path);
}

public void removeFile(String path){

	getFilesystem().removeFile(path);
}

}