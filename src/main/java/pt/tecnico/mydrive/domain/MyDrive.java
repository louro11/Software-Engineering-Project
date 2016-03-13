package pt.tecnico.mydrive.domain;

public class MyDrive extends MyDrive_Base {

    public MyDrive(){
    	FileSystem fs = new FileSystem();

    	setCurrentuser(fs.getRoot());
    	setCurrentdirectory(fs.getMaindir());
    	setFilesystem(fs);
    }
    
}
