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
import pt.tecnico.mydrive.exceptions.LoopFoundException;
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
	
		private ArrayList<File> visitedlinks;

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
		
		public File getFile (String path)throws InvalidPathException, FileNotFoundException{
			Directory auxdir = getMaindir();
			String[] auxpath = path.split("/");
			int i = 1;
			
			for(File file : auxdir.getFilesSet()){
				
				if(file==null)
					 throw new InvalidPathException (path);
				 
				
				if(!(file.get_name().equals(auxpath [i])))
					 throw new FileNotFoundException("No such file or directory: " + file.get_name());
				
				 //se for app ou link retornar, senao passar ao proximo
			    if(file.isDir()){
			    	auxdir = (Directory) file;
			    	i++;			 
				}
			    else
			    	return file;
			}
			return null;
		}
		
		public ArrayList getVisitedLinks(){
	    	return visitedlinks;
	    }
		
		public void executeFile(User user, long token, String path, String[] args)throws FileNotFoundException, LoopFoundException, InvalidPathException,
				AccessDeniedException{
			
			if(path.startsWith("/")){
				throw new InvalidPathException(path);
			}
			
			File file = getFile(path);
			if(file == null){
				throw new FileNotFoundException ("file not found");
			}
			
			if(user.get_username()!=file.getOwner().get_username() && !(user.hasExecutePermission(file))){
				throw new AccessDeniedException(user.get_username());
			}
			
			if(args.length > 0 ){
				try{
					 visitedlinks.clear();
					 file.run(user, args);	
				 }catch (ClassNotFoundException | SecurityException | NoSuchMethodException | IllegalArgumentException | 
						 IllegalAccessException | InvocationTargetException e){}
			 
			}
		}
		
		public boolean hasFile(User user, String filename)throws FileNotFoundException, InvalidPathException{
			
			if(user.getFilesystem().getFile(filename) == null){
				return false;
			}else
				return true;
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

}
