package pt.tecnico.mydrive.domain;

import java.util.*;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import org.joda.time.DateTime;
import org.jdom2.Element;


public class FileSystem extends FileSystem_Base {

   /* public FileSystem() {
        super();
    }*/

    public FileSystem() {

    
		
        SuperUser root = new SuperUser("root", "***", "Super user", "rwxdr-x-");

        String mask = root.get_mask();
        Directory maindir = new Directory( "/" , 0 , new DateTime(), mask , (User)root );
        maindir.createSubDirectory("home", (User)root,maindir); 
        Directory home = (Directory) maindir.getFile("home");
        home.createSubDirectory("root", root, home);
        Directory main = (Directory) home.getFile("root");
        root.setHomedirectory(main);	


        setRoot(root);
        setMaindir(maindir);
    }

    public void removeFile(String path, User current) throws FileNotFoundException{

		
		Directory parent = Directoryfrompath(path);
		
		String[] token = path.split("/");

		for (File file: parent.getFilesSet()){

				if (file.get_name().equals(token[token.length])){
				
					file.remove();  /* necessario verificar permissoes? */
				}
				else{

					throw new FileNotFoundException(token[token.length]);}

		}

	}

	public void createTextFile(String name, String permission, int fileid, DateTime timestamp, User owner, String content, Directory cd ){
			
			cd.createTextFile(name, permission, fileid, timestamp, owner, content);
	}
	
	public void createDirectory(String name, User owner, String path){
		Directory parent = Directoryfrompath(path);
		parent.createSubDirectory(name, owner, parent);
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
			

			Directory dir = Directoryfrompath(path);

			String[] token = path.split("/");

			for (File file: dir.getFilesSet()){

				if (file.get_name().equals(token[token.length-1])){

					dir = (Directory) file;
					
				}

			}

			String s = dir.PrintFiles();
		
		return s;
		
	}

	public String readfile(String path){
		int i;
		Directory aux = Directoryfrompath (path);
		TextFile tf = new TextFile();

		String[] token = path.split("/");


		for(i=0; i<token.length-1; i++){

			for (File file: aux.getFilesSet()){

				if (file.get_name().equals(token[token.length-1])){

					tf = (TextFile)file;	
					
				}

			}

		}

		return tf.readfile();

	}		

	public Element xmlExport() {
		Element element = new Element("filesytem");

		Element UsersElement = new Element("users");
		element.addContent(UsersElement);
		
		for (User user: getUsersSet())
		    UsersElement.addContent(user.xmlExport());
		
		return element;
	    }


}
