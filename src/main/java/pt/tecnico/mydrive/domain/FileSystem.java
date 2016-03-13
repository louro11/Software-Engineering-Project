package pt.tecnico.mydrive.domain;

import java.util.*;
import pt.tecnico.mydrive.exceptions.InvalidFileNameException;

public class FileSystem extends FileSystem_Base {

    public FileSystem() {
        super();
    }

    public void removeFile(String path, Directory maindir) throws InvalidFileNameException{

		
		Directory parent = Directoryfrompath(path, maindir);
		
		String[] token = path.split("/");

		for (File file: parent.getFilesSet()){

				if (file.get_name().equals(token[token.length])){

					file.remove();
				}
				else{

					throw new InvalidFileNameException(token[token.length]);}

		}

	}


	
	public Directory Directoryfrompath(String path, Directory maindir){
		
		int i;
		
		String[] token = path.split("/");
		
		Directory aux = maindir;

		for(i=0; i<token.length-1; i++){

			for (File file: aux.getFilesSet()){

				if (file.get_name().equals(token[i])){

					aux = (Directory) file;
					
					
				}

			}
		}
		
		return aux;
		
	}
		





  /*  public String list_Files(String path, Directory maindir){

	Set<Files> _files = maindir.getFiles();
	for(String i : _files){
		if(_files.get)
	}
	return .list_Files();

}*/

}
