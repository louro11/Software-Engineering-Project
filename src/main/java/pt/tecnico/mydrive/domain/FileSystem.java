package pt.tecnico.mydrive.domain;

import java.util.*;
import pt.tecnico.mydrive.exceptions.InvalidFileNameException;
import org.joda.time.DateTime;


public class FileSystem extends FileSystem_Base {

    public FileSystem() {
        super();
    }

    public FileSystem() {

    	//String name, Integer fileid, DateTime timestamp, String permission, User owner, Directory parent )
		
		//String username, String password, String name, String mask





		
        Superuser root = new SuperUser("root", "***", "Super user", "rwxdr-x-");


        Directory maindir = Directory("/",0,new DateTime(), root.get_mask() ,root, this);
        maindir.createSubDirectory("home", root,maindir); 
        Directory home = (Directory) maindir.getFile("home");
        home.createSubDirectory("root", root, home);
        Directory main = (Directory) home.getFile("root");
        root.setHomedirectory(main);	


        setRoot(root);
        setMaindir(maindir);
    }

    public void removeFile(String path, Directory maindir) throws InvalidFileNameException{

		
		Directory parent = Directoryfrompath(path);
		
		String[] token = path.split("/");

		for (File file: parent.getFilesSet()){

				if (file.get_name().equals(token[token.length])){

					file.remove();
				}
				else{

					throw new InvalidFileNameException(token[token.length]);}

		}

	}


	
	public Directory Directoryfrompath(String path){
		
		int i;
		
		String[] token = path.split("/");
		
		Directory aux = this.getMaindir();

		for(i=0; i<token.length-1; i++){

			for (File file: aux.getFilesSet()){

				if (file.get_name().equals(token[i])){

					aux = (Directory) file;
					
					
				}

			}

		}

		return aux;
	}

		public String PrintFiles(String path){
			

			Diretory dir = Directoryfrompath(path);

			String[] token = path.split("/");

			for (File file: dir.getFilesSet()){

				if (file.get_name().equals(token[token[token.lenght-1]])){
:
					dir = (Directory) file;
					
				}

			}

			String s = dir.PrintFiles();
		
		return s;
		
	}

	public String readfile(String path){
		int i;
		Directory aux = Directoryfrompath (path, maindir);

		String[] token = path.split("/");


		for(i=0; i<token.length-1; i++){

			for (File file: aux.getFilesSet()){

				if (file.get_name().equals(token[i])){

					aux = file;
					
					
				}

			}

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
