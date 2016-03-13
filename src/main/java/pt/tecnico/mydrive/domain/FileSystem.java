package pt.tecnico.mydrive.domain;

public class FileSystem extends FileSystem_Base {
    
    public FileSystem() {
        super();
    }
    
    public void removeFile(String path) {
		
		String[] tokens = path.split("/");
		
		for(int i=0; i<result.length; i++){
			
			
	
			System.out.println(result[i]);
		
		}
     
 }

    public String list_Files(String path, Directory maindir){
	
	Set<Files> _files = maindir.getFiles();
	for(String i : _files){ 
		if(_files.get
	}
	return .list_Files();
   }	
    
}
