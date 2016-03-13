package pt.tecnico.mydrive.domain;

public class FileSystem extends FileSystem_Base {

    public FileSystem() {
        super();
    }

    public void removeFile(String path, Directory maindir) throws InvalidFileNameException{
		
		String[] tokens = path.split("/");
		
		Directory aux = maindir;
		
		for(int i=0; i<result.length-1; i++){
			
			for (File file: aux.getFilesSet()){
				
				if (file.getName().equals(token[i])){
					
					aux = (Directory) file;
				}
			
			}
			
		
		}
		
		for (File file: aux.getFilesSet()){
				
				if (file.getName().equals(token[i+1])){
					
					file.remove();
				}
				else{
					
					throw new InvalidFileNameException(token[i+1]);}
			
		}
		
	}
		
		
		
		
		

 

    public String list_Files(String path, Directory maindir){

	Set<Files> _files = maindir.getFiles();
	/*for(String i : _files){
		if(_files.get)
	}
	return .list_Files();*/
  return "";//so para compilar por enquanto @HenriqueCarlos
   }

}
