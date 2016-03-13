package pt.tecnico.mydrive.domain;

public class FileSystem extends FileSystem_Base {

    public FileSystem() {
        super();
    }

    public void removeFile(String path) {

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
