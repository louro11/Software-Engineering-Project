package pt.tecnico.mydrive.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import pt.ist.fenixframework.FenixFramework;
import java.io.File;

import pt.tecnico.mydrive.exceptions.AccessDeniedException;
import pt.tecnico.mydrive.exceptions.CantReadDirectoryException;
import pt.tecnico.mydrive.exceptions.CantWriteToDirectoryException;
import pt.tecnico.mydrive.exceptions.FileAlreadyExistsException;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.InvalidContentException;
import pt.tecnico.mydrive.exceptions.InvalidPathSizeException;
import pt.tecnico.mydrive.exceptions.InvalidTypeException;
import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.LoginIsInvalidException;
import pt.tecnico.mydrive.exceptions.PermitionException;

import pt.tecnico.mydrive.exceptions.WrongPasswordException;

import pt.tecnico.mydrive.exceptions.TokenAlreadyExistsException;

import pt.ist.fenixframework.FenixFramework;
import pt.tecnico.mydrive.service.dto.FileDto;

import org.jdom2.Element;
import org.jdom2.Document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.tecnico.mydrive.domain.FileSystem;


	public class MyDrive extends MyDrive_Base {


		public MyDrive(){

			//a noção de diretoria atual deixa de ser do mydrive e passa a ser do Login!!!

			setRoot(FenixFramework.getDomainRoot());
			
			if(this.getFilesystem()==null) {
				
				setFilesystem(new FileSystem());}
				setCurrentuser(getFilesystem().getRoot());
			}


		public static MyDrive getInstance() {

			MyDrive mydrive = FenixFramework.getDomainRoot().getMydrive();

			if (mydrive != null)
				return mydrive;
			
			return new MyDrive();
		}


/****************************SERVICES FUNCTIONS***************************/


		public List<FileDto> listDirectory(long token)throws LoginDoesNotExistException, PermitionException{

				Login login = getLoginbyToken(token);


				Directory dir = login.getCurrentdirectory();
				User usr = login.getUser();
				
				return getFilesystem().listDirectory(dir, usr);
			
		}


        public String changeCurrentDirectory(long token, String path) throws FileNotFoundException, LoginDoesNotExistException, AccessDeniedException{

		
				Login login = getLoginbyToken(token);

				User user = login.getUser();

				return getFilesystem().changeCurrentDirectory(login , user, path);


		}



        public String readFile(long token, String filename)throws LoginDoesNotExistException, CantReadDirectoryException, PermitionException, AccessDeniedException, FileNotFoundException{

        		
        		Login login = getLoginbyToken(token);
        		Directory dir = login.getCurrentdirectory();

        		User user = login.getUser();
		
        		return getFilesystem().readFile(dir, user, filename);

        	
        }



        public void writeToFile(long token, String filename, String content) throws LoginDoesNotExistException, CantWriteToDirectoryException, PermitionException, AccessDeniedException, FileNotFoundException{

				Login login = getLoginbyToken(token);
				Directory dir = login.getCurrentdirectory();

				User user = login.getUser();
				getFilesystem().writeToFile(dir, user, filename, content);			
        }


		public void createFile(long token, String filename, String type, String content) throws InvalidPathSizeException, LoginDoesNotExistException, InvalidContentException,InvalidTypeException,FileAlreadyExistsException, PermitionException{

				System.err.println("chegou ao createFile");	

				Login login = getLoginbyToken(token);
				Directory dir = login.getCurrentdirectory();

				User user = login.getUser();

				if (type.equals("directory")){

					if(content.equals(""))
					getFilesystem().createFileDirectory(dir,user,filename,type);

					else{throw new InvalidContentException(content);}

				}

				else{ getFilesystem().createFile(dir, user, filename, type, content); }

			
		}


		/** nao tinhamos nenhum create_user no mydrive, estou a inventar ass:rafa **/



		public void createUser(String username){

			getFilesystem().createUser(username);


		/** password default: username
		 * 	nome default: username;
            mask = "rwxd----";
            homedir = "/home/" + username;


            * */



		}





		public Document xmlExport() {

			Element element = new Element("mydrive");
			Document doc = new Document(element);

			element.addContent(getFilesystem().xmlExport());

			return doc;
		}
		
		
		

		public void xmlImport(Element element) {

			element.getChild("filesystem");

			if(getFilesystem()==null){

				FileSystem fs = new FileSystem();
				fs.xmlImport(element);}

			else{

				getFilesystem().xmlImport(element);}

		}




		public long loginUser(String username, String password)throws UserDoesNotExistException, WrongPasswordException{

		
				User user = getFilesystem().getUserbyUsername(username);
				Login login;
				login = new Login(user, password);
				CheckToken(login);
				UpdateLoginList();
				getLoginsSet().add(login);  //override do add!!!!!!! /*TODO*/

				return login.get_token();

		}

		/*public Login getLoginbyToken(long token) throws LoginDoesNotExistException, LoginIsInvalidException {

			
			
			for( Login login: getLoginsSet()){

				if( login.get_token()==token ){

							DateTime now = new DateTime();

							if( login.isAfter(login.get_timeout())){

								throw new LoginIsInvalidException();

							}

							else{

						return login;

							}

				}

			}

    	  throw new LoginDoesNotExistException();

	  } */

	  	public Login getLoginbyToken(long token) throws LoginDoesNotExistException, LoginIsInvalidException {
	
			
			for( Login login: getLoginsSet()){

				if( login.get_token()==token ){

							User userlogged = login.getUser();
							DateTime datelogged = login.get_timeout();
							//a root nunca deixa de estar logada, é atribuido apenas num novo token
							if(userlogged.isRoot()){loginUser("root","rwxdr-x-");}
							else if(!userlogged.timeout(datelogged)){ throw new LoginIsInvalidException();}
							else{ return login; }
				}

			}

    	  throw new LoginDoesNotExistException();

	  }

	/*	public void UpdateLoginList(){

				for(Login log: getLoginsSet()){

					DateTime now = new DateTime();

					if( now.isAfter(log.get_timeout())){
						getLoginsSet().remove(log);
					}
				}
			} */

		public void UpdateLoginList(){

				for(Login log: getLoginsSet()){

					User userlogged = log.getUser();
					DateTime datelogged = log.get_timeout();
					//a root nunca deixa de estar logada, é atribuido apenas num novo token
					if(userlogged.isRoot()){loginUser("root","rwxdr-x-");}  
					else if( ! userlogged.timeout(datelogged)){
						getLoginsSet().remove(log);
					}
				}
			}


		public void CheckToken(Login l) throws TokenAlreadyExistsException{

			for(Login log: getLoginsSet()){

				if( l.get_token() == log.get_token()){
					throw new TokenAlreadyExistsException();
				}

		}

	}

		public void executeFile(long token, String path, String[] args){
			
			getFilesystem().executeFile(token, path, args);
			
		}


		 public int getFileNameByUser(String userName) throws UserDoesNotExistException {
			// TODO: mockup example
			return 0;
		}
	
	


 /********************************** NNEEEWWWWW STTUUUUFFFFF *********************************/
 
 
	 public List<EnvironmentVar> addEnvironmentvar(long token, String name, String value) {
	
		// TODO:XXX
		// verificar se ja existe, se sim, redefinir valores
		// permissoes do user atual (not sure)
		// suposto retornar lista atual de variaveis separadas por '=' (ughh peanurs)
				
				Login login = getLoginbyToken(token);
				
				
				for( EnvironmentVar var: login.getVars() ){

					if( var.get_name().equals(name) ){
						
						//redefinir valor variavel e retornar lista

						var.set_value(value);
						
						return login.listVariables();
					}
				
				}
				
				//cria, adiciona e retorna lista 
					
				EnvironmentVar variable = new EnvironmentVar(name,value);
						
				login.addVars(variable);
				
				return login.listVariables();
				
			
	}
	
	
	
	
	/******************************PLEASE DON'T CROSS THIS LINE: HAZARD, POSSIBLE FATAL DAMAGE**************************************/
	
	/*

		public void deleteFileByPath(long token, String path) throws LoginDoesNotExistException, FileNotFoundException, PermitionException  {


			try{

				Login login = getLoginbyToken(token);

				User user = login.getUser();

				getFilesystem().removeFileByPath(user,path);
			}



			catch (LoginDoesNotExistException e){ throw e;}
			catch (FileNotFoundException e){throw e;}
			catch (PermitionException e){throw e;}

		}


		public void deleteFileByName(long token, String name) throws LoginDoesNotExistException, FileNotFoundException, PermitionException{


			try{

				Login login = getLoginbyToken(token);

				User user = login.getUser();

				Directory current = login.getCurrentdirectory();

				getFilesystem().removeFileByName(user,current,name);
			}



			catch (LoginDoesNotExistException e){ throw e; }
			catch (FileNotFoundException e){ throw e; }
			catch (PermitionException e){ throw e; }

		}

	*/
	
	
	
	


}
