package pt.tecnico.mydrive.domain;

import java.util.*;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.ImportDocumentException;
import pt.tecnico.mydrive.exceptions.InvalidUserNameException;
import pt.tecnico.mydrive.exceptions.UserNameAlreadyExistsException;
import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;

import org.joda.time.DateTime;
import org.jdom2.Element;


public class FileSystem extends FileSystem_Base {

    public FileSystem() {

        SuperUser root = new SuperUser("root", "***", "Super user", "rwxdr-x-");

		ResetIdseq();

        String mask = root.get_mask();

        IncrementIdseq();
        Directory maindir = new Directory( "/" , get_idseq() , new DateTime(), mask , (User)root );
        IncrementIdseq();
        maindir.createSubDirectory("home", get_idseq(), (User)root,maindir);


        Directory home = (Directory) maindir.getFile("home");
        IncrementIdseq();
        home.createSubDirectory("root",get_idseq(),root, home);

        Directory main = (Directory) home.getFile("root");
        root.setHomedirectory(main);


        setRoot(root);
        setMaindir(maindir);
    }

    public Directory changeCurrentDirectory(Login login, User user, String path) throws FileNotFoundException {

    	Directory currentdir = login.getCurrentdirectory(); 
    	String[] token = path.split("/");

    	for (int i=1; i<token.length;i++){

    		for (File file: currentdir.getFilesSet()){

				if (file.get_name().equals(token[i])){

					currentdir = (Directory) file;

				}
				else{

					throw new FileNotFoundException(token[i]);

				}
    	}
    }
    	return currentdir;
	}


    public void createUser(String username) throws InvalidUserNameException, UserNameAlreadyExistsException{


        try {
          User usr = new User(username);
         for(User usrtmp : getUsersSet()){
          if(usrtmp.equals(usr)){
            throw new UserNameAlreadyExistsException(username);
          }
        }
         getUsersSet().add(usr);
      }
      catch(InvalidUserNameException e){ throw e; }

    }


    public void removeFile(String path) throws FileNotFoundException{


		Directory parent = Directoryfrompath(path);

		String[] token = path.split("/");

		for (File file: parent.getFilesSet()){



				if (file.get_name().equals(token[token.length-1])){

					file.remove();  /* necessario verificar permissoes? */
					DecrementIdseq();

				}
				else{

					throw new FileNotFoundException(token[token.length-1]);}

		}

	}

	public void createTextFile(String name, String permission, int fileid, DateTime timestamp, User owner, String content, Directory cd ){


			/* Falta tratar permissoes, etc, .. */
			IncrementIdseq();
			cd.createTextFile(name, permission, get_idseq(), timestamp, owner, content);
	}

	public void createDirectory(User owner,String path){


		Directory currentdir = getMaindir() ;
    	String[] token = path.split("/");

    	for (int i=1; i<token.length;i++){

    		if(currentdir.getFilesSet().size()!=0){

				for (File file: currentdir.getFilesSet()){

					if (file.get_name().equals(token[i])){

						currentdir = (Directory) file;
					}
					else{

						IncrementIdseq();
						currentdir.createSubDirectory(token[i],get_idseq(),owner,currentdir);

						for (File newfile: currentdir.getFilesSet()){

								if (newfile.get_name().equals(token[i])){

								currentdir = (Directory) newfile;

								break;

								}
						}

						break;
						}

	    		}
    		}
    		else{

    			IncrementIdseq();
				currentdir.createSubDirectory(token[i],get_idseq(),owner,currentdir);

				for (File newfile: currentdir.getFilesSet()){

					if (newfile.get_name().equals(token[i])){

						currentdir = (Directory) newfile;

						break;

					}
				}


    		}
    	}

	}

	//TODO token e mascara do user
	public void createFile(Directory dir, User user, String filename, String type, String content){
		IncrementIdseq();
		DateTime dt = new DateTime();
		if(type.equals("directory")){
			Directory direct = new Directory(filename, get_idseq(), dt,"rwxd----",user,dir);
			dir.addFiles(direct);
		}
		else if(type.equals("textfile")){
			TextFile txt = new TextFile(filename, "rwxd----", get_idseq(), dt, user, content);
			dir.addFiles(txt);
		}
		else if(type.equals("app")){
			Application app = new Application(filename, "rwxd----", get_idseq(), dt, user, content);
			dir.addFiles(app);
		}
		else if(type.equals("link")){
			//TODO testar se o content e um path valido
			Link link = new Link(filename, "rwxd----", get_idseq(), dt, user, content);
			dir.addFiles(link);
		}
	}

	public Directory Directoryfrompath(String path){

		int i;

		String[] token = path.split("/");

		Directory aux = getMaindir();

		for(i=1; i<token.length-1; i++){

			for (File file: aux.getFilesSet()){

				if (file.get_name().equals(token[i])){

					aux = (Directory) file;


				}

			}

		}

		return aux;
	}

		public String printFiles(String path){


			Directory dir = Directoryfrompath(path);

			String[] token = path.split("/");

			for (File file: dir.getFilesSet()){

				if (file.get_name().equals(token[token.length-1])){

					dir = (Directory) file;

				}

			}

			return dir.printFiles();

	}

	public String readfile(Login login, User user, String name) throws FileNotFoundException {
	
		Directory currentdir = getMaindir() ;
		TextFile tf = new TextFile();

			for (File file: currentdir.getFilesSet()){

				if (file.get_name().equals(name)){

					tf = (TextFile)file;

				}
		}
      return tf.readfile();
	}

	public void writefile (Login login, User user, String name, String content) throws FileNotFoundException {
	
		Directory currentdir = getMaindir() ;
		TextFile tf = new TextFile();

			for (File file: currentdir.getFilesSet()){

				if (file.get_name().equals(name)){

					tf = (TextFile)file;

				}
		}
       tf.writefile(content);
	}


	public Element xmlExport() {


		Element element = new Element("filesytem");

		Element UsersElement = new Element("users");


		for (User user: getUsersSet())
		    UsersElement.addContent(user.xmlExport());

		element.addContent(UsersElement);
		return element;
	    }

	public User getUserByUsername(String username) {


        for (User user : getUsersSet()) {


            if (user.get_username().equals(username)) {
                return user;
            }
        }
        return null;
    }

	public void xmlImport(Element element){ /*throws ImportDocumentException */

		Element userElem = element.getChild("users");

		for (Element node: userElem.getChildren("user")) {


		    String username = node.getAttribute("username").getValue();

		    User user = getUserByUsername(username);

		    if (user == null){ // Does not exist
		    		user = new User(username);
		    }

		    user.xmlImport(node);
		}
	}



	public void IncrementIdseq(){

		set_idseq(get_idseq()+1);
	}

	public void DecrementIdseq(){

		set_idseq(get_idseq()-1);
	}

	public void ResetIdseq(){

		set_idseq(0);
	}

  public User getUserbyUsername(String username) throws UserDoesNotExistException{
    for( User user: getUsersSet()){
      if( user.get_username().equals(username) ){
        return user;
      }
    }
    throw new UserDoesNotExistException(username);
  }
}
