package pt.tecnico.mydrive.domain;

import java.lang.reflect.*;
import java.util.*;

import pt.tecnico.mydrive.exceptions.AccessDeniedException;
import pt.tecnico.mydrive.exceptions.CantReadDirectoryException;
import pt.tecnico.mydrive.exceptions.CantWriteToDirectoryException;
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
import pt.tecnico.mydrive.exceptions.RunException;
import pt.tecnico.mydrive.exceptions.InvalidFileNameException;
import pt.tecnico.mydrive.exceptions.GuestDoesntHasWritePermitionException;
import pt.tecnico.mydrive.service.dto.FileDto;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.jdom2.Element;


public class FileSystem extends FileSystem_Base {

		public FileSystem() {

			SuperUser root = new SuperUser("root", "***", "Super user", "rwxdr-x-");
			Guest guest = new Guest();

			ResetIdseq();

			String mask = root.get_mask();

			IncrementIdseq();
			Directory maindir = new Directory( "/" , get_idseq() , new DateTime(), mask , (User)root );
			IncrementIdseq();
			maindir.createSubDirectory("home", (User)root, get_idseq(),new DateTime() );


			Directory home = (Directory) maindir.getFile("home");
			IncrementIdseq();
			home.createSubDirectory("root",root,get_idseq(), new DateTime());

			Directory main = (Directory) home.getFile("root");
			root.setHomedirectory(main);



        setRoot(root);
        setGuest(guest);
        setMaindir(maindir);  

    }


		public String changeCurrentDirectory(Login login, User user, String path) throws FileNotFoundException, AccessDeniedException {

			Directory dirtobechanged ;

			if (path.startsWith("/")) {
				dirtobechanged = getMaindir();
			}

			else{

			dirtobechanged = login.getCurrentdirectory();

			}

			String[] dirs = path.split("/");

					for (int i=1; i<dirs.length;i++){

						for (File file: dirtobechanged.getFilesSet()){

							if (file.get_name().equals(dirs[i]) ){

								if(!((file.getOwner().get_username()).equals(user.get_username()))){ //nao deixa ler ficheiros de outros users
										throw new AccessDeniedException(file.getOwner().get_username());
								}

								dirtobechanged = (Directory) file;

							}
							else{

								throw new FileNotFoundException(dirs[i]);

							}
						}
					}


			login.setCurrentdirectory(dirtobechanged);

			return path;
		}


		public void createUser(String username) throws InvalidUserNameException, UserNameAlreadyExistsException{



			  User usr = new User(username);

			  for(User usrtmp : getUsersSet()){


				if(usrtmp.equals(usr)){

					throw new UserNameAlreadyExistsException(username);
				}
			  }

			 getUsersSet().add(usr);
	

		}

		public String readFile(Directory dir, User user, String filename)throws CantReadDirectoryException, FileNotFoundException, PermitionException{
			


				File file = dir.getFile(filename);


				if(!((file.getOwner().get_username()).equals(user.get_username()))){ //nao deixa ler ficheiros de outros users
					
					throw new AccessDeniedException(file.getOwner().get_username());
				}

				if(!(file.get_permission().equals(user.get_mask()))){  //permissao que nao me deixa escrever
					
					throw new PermitionException(file.get_permission());
				}


				return file.readfile();
		
		}



		public void writeToFile(Directory dir, User user, String filename, String content) throws CantWriteToDirectoryException, FileNotFoundException, PermitionException, AccessDeniedException{
			
			
	


				File file = dir.getFile(filename);


				if(!(file.get_permission().equals(user.get_mask()))){  //permissao que nao me deixa escrever
					
					throw new PermitionException(file.get_permission());
				}

				if(!((file.getOwner().get_username()).equals(user.get_username()))){
					
					throw new AccessDeniedException(file.getOwner().get_username());
				}

				file.writefile(content); //posso fazer assim?


		}


	public void createFileDirectory(Directory curdir, User user, String filename, String type)throws GuestDoesntHasWritePermitionException, InvalidPathSizeException, InvalidContentException, InvalidTypeException, FileAlreadyExistsException,PermitionException{
		String path = filename + curdir.get_name();
		Directory maindir = getMaindir();

			int bars = 0;
			//calcula o tamanho do path todo + o nome do ficheiro a acrescentar
			while((!curdir.getDirectory().isEqual(maindir))){


				path += curdir.get_name();
				bars++; //a barra nao faz parte do nome da directoria, tenho de contar a parte
			}

			if(!(curdir.hasFile(filename))){




			if(!(user.hasWritePermission(curdir))){ throw new PermitionException(curdir.get_permission());}

				if ((path.length()+bars)>=1024){ throw new InvalidPathException(path);}


				IncrementIdseq();
				DateTime dt = new DateTime();

				curdir.createSubDirectory(filename,user,get_idseq(), dt);

			}

	}


	public void createFile (Directory curdir, User user, String filename, String type, String content)throws GuestDoesntHasWritePermitionException, InvalidPathSizeException, InvalidTypeException , InvalidContentException, InvalidTypeException, FileAlreadyExistsException,PermitionException{

		String path = filename + curdir.get_name(); //o que é isto??
		Directory maindir = getMaindir();


		int bars = 0;

		
		//calcula o tamanho do path todo + o nome do ficheiro a acrescentar
		/*while((!curdir.getDirectory().isEqual(maindir))){

			path += curdir.get_name();
			bars++; //a barra nao faz parte do nome da directoria, tenho de contar a parte
		} */


			if(!(curdir.hasFile(filename))){

			 if(!(user.hasWritePermission(curdir))){ throw new PermitionException(curdir.get_permission());}

				if ((path.length()+bars)>=1024){ throw new InvalidPathException(path);} 

			IncrementIdseq();
			//DateTime dt = new DateTime();

		    curdir.createFile(type, filename,user,get_idseq(), new DateTime() ,content);  


		 }
		
			/*if(type.equals("directory")){
				if(content.equals("")){ //directorias nao tem conteudo
					Directory direct = new Directory(filename, get_idseq(), dt,user.get_mask(),user,dir);
					dir.addFiles(direct);


				curdir.createFile(type, filename,user,get_idseq(),dt,content);

				/*if(type.equals("directory")){
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

				*/


			
		}


		public List<FileDto> listDirectory(Directory dir, User usr)throws PermitionException{



			if(usr.isRoot() || usr.hasReadPermission(dir)){
					
					return dir.listDirectory();
			  }
		 
			else{
				
			throw new PermitionException("This user: " + usr.get_name() + " has no permission to list current directory ");}
			

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


		public void cleanup() {
			
			for (User u: getUsersSet()){
				
				u.remove();
			}
		}



		public void executeFile(long token, String path, String[] args)throws FileNotFoundException{
			 
			Directory auxdir = getMaindir();
			String[] auxpath = path.split("/");
			 
			int i = 1;
			
			for(File file : auxdir.getFilesSet()){
				
				if(file==null)
					 throw new InvalidPathException (path);
				 
				
				if(!(file.get_name().equals(auxpath [i])))
					 throw new FileNotFoundException("No such file or directory: " + file.get_name());
				
				else{ 
					 //se for app ou link executar, senao passar ao proximo
					 if(file.isDir()){
						 auxdir = (Directory) file;
						 i++;
					 }
					 
					 else{
						 TextFile txt = (TextFile) file;
						 String content = txt.get_content();
						 if(args.length>0) run(content, args);
						
									 
							
					}
				}
						 
			}
		}
			 
		
 
 
	 
	 public void run(String content, String []args){
		 Method method;
		 Class<?> cls;
	 }
		
 /******************************PLEASE DON'T CROSS THIS LINE: HAZARD, POSSIBLE FATAL DAMAGE**************************************/
 
 
            //Dare me
  
/*
    public void removeFileByPath(User user, String path) throws FileNotFoundException, PermitionException{


		Directory parent = Directoryfrompath(path);

		String[] token = path.split("/");

		for (File file: parent.getFilesSet()){



				if (file.get_name().equals(token[token.length-1])){

					if(user.hasDeletePermission(file) && user.hasDeletePermission(parent)){

						file.remove();
					}
					else{

						throw new PermitionException("This user: " + user.get_name() + " has no permission to delete this file. ");

					}
				}

				else{

					throw new FileNotFoundException("No such file or directory: " + token[token.length-1]);

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

					throw new FileNotFoundException("No such file or directory: " + name);

				}

		}



	}



	/*

	public void createTextFile(String name, String permission, int fileid, DateTime timestamp, User owner, String content, Directory cd ){



			IncrementIdseq();
			cd.createTextFile(name, permission, get_idseq(), timestamp, owner, content);
	}

*/

/*
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
						currentdir.createSubDirectory(token[i],owner,get_idseq(), new DateTime());

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
				currentdir.createSubDirectory(token[i],owner,get_idseq(),new DateTime());

				for (File newfile: currentdir.getFilesSet()){

					if (newfile.get_name().equals(token[i])){

						currentdir = (Directory) newfile;

						break;

					}
				}


    		}
    	}

	}

	*/

	

/*


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


	*/


		
}
