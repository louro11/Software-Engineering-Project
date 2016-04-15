package pt.tecnico.mydrive.domain;

import java.util.*;

import pt.tecnico.mydrive.exceptions.FileAlreadyExistsException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.ImportDocumentException;
import pt.tecnico.mydrive.exceptions.InvalidContentException;
import pt.tecnico.mydrive.exceptions.InvalidPathException;
import pt.tecnico.mydrive.exceptions.InvalidPathSizeException;
import pt.tecnico.mydrive.exceptions.InvalidTypeException;
import pt.tecnico.mydrive.exceptions.InvalidUserNameException;
import pt.tecnico.mydrive.exceptions.UserNameAlreadyExistsException;
import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;
import pt.tecnico.mydrive.exceptions.PermitionException;


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



    public void removeFileByPath(User user, String path) throws FileNotFoundException, PermitionException{


		Directory parent = Directoryfrompath(path);

		String[] token = path.split("/");

		for (File file: parent.getFilesSet()){



				if (file.get_name().equals(token[token.length-1])){

					if(user.hasDeletePermission(file)){

						file.remove();
					}
					else{

						throw new PermitionException("This user: " + user.get_name() + " has no permission to delete this file. ");

					}
				}

				else{

					throw new FileNotFoundException(token[token.length-1]);

				}

		}



	}


	public void removeFileByName(User user, Directory current, String name) throws FileNotFoundException, PermitionException{


		//Directory parent = Directoryfrompath(path);

		//String[] token = path.split("/");

		for (File file: current.getFilesSet()){



				if (file.get_name().equals(name)){

					if(user.hasDeletePermission(file) && user.hasDeletePermission(current)){

						file.remove();
					}
					else{

						throw new PermitionException("This user: " + user.get_name() + " has no permission to delete this file. ");

					}
				}

				else{

					throw new FileNotFoundException(name);

				}

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


	public void createFile(Directory dir, User user, String filename, String type, String content)
			throws InvalidPathSizeException, InvalidContentException, InvalidTypeException, FileAlreadyExistsException{

		String path = filename + dir.get_name(); // / esta no filename? no.
		Directory maindir = getMaindir();
		Directory curdir=dir;

		int bars = 0;
		//calcula o tamanho do path todo + o nome do ficheiro a acrescentar
		while((!curdir.getParent().isEqual(maindir))){

			path += curdir.get_name();
			bars++; //a barra nao faz parte do nome da directoria, tenho de contar a parte
		}

		for (File file : dir.getFilesSet()){
			if(file.get_name().equals(filename)){
				throw new FileAlreadyExistsException(filename);
			}
		}

		if((path.length() + bars)<=1024){
			IncrementIdseq();
			DateTime dt = new DateTime();
			if(type.equals("directory")){
				if(content.equals("")){ //directorias nao tem conteudo
					Directory direct = new Directory(filename, get_idseq(), dt,user.get_mask(),user,dir);
					dir.addFiles(direct);
				}else
					throw new InvalidContentException(content);
			}
			else if(type.equals("textfile")){
				TextFile txt = new TextFile(filename, user.get_mask(), get_idseq(), dt, user, content);
				dir.addFiles(txt);
			}
			else if(type.equals("app")){
				Application app = new Application(filename, user.get_mask(), get_idseq(), dt, user, content);
				dir.addFiles(app);
			}
			else if(type.equals("link")){
				curdir = maindir;
				String[] token = path.split("/");
		    	for (int i=1; i<token.length;i++){
		    		if(curdir.getFilesSet().size()!=0){
						for (File file: curdir.getFilesSet()){
							if (file.get_name().equals(token[i])){
								curdir = (Directory) file;
							}else throw new InvalidContentException(content);	
						}
		    		}else throw new InvalidContentException(content);
		    	}
		    	Link link = new Link(filename, user.get_mask(), get_idseq(), dt, user, content);
				dir.addFiles(link);
			}else
				throw new InvalidTypeException(type);
		}
		else throw new InvalidPathSizeException();
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

		public String listDirectory(Directory dir, User usr)throws PermitionException{


	     if(usr.isRoot() || (usr.hasReadPermission(dir) && dir.getOwner().equals(usr))){
			       return dir.listDirectory();
           }
      else{
        throw new PermitionException("This user: " + usr.get_name() + " has no permission to list current directory ");
      }

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
