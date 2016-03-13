package pt.tecnico.mydrive.domain;

import java.util.*;
import pt.tecnico.mydrive.exceptions.InvalidFileNameException;

public class FileSystem extends FileSystem_Base {

    public FileSystem() {
        super();
    }

    public void removeFile(String path, Directory maindir) throws InvalidFileNameException{

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

		for (File file: aux.getFilesSet()){

				if (file.get_name().equals(token[i+1])){

					file.remove();
				}
				else{

					throw new InvalidFileNameException(token[i+1]);}

		}

	}








  /*  public String list_Files(String path, Directory maindir){

	Set<Files> _files = maindir.getFiles();
	for(String i : _files){
		if(_files.get)
	}
	return .list_Files();

}*/

}
