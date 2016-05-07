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

			try{
				Login login = getLoginbyToken(token);


				Directory dir = login.getCurrentdirectory();
				User usr = login.getUser();
				return getFilesystem().listDirectory(dir, usr);
			}catch (LoginDoesNotExistException e){ throw e;}
				catch (PermitionException e) {throw e;}


		}




        public String changeCurrentDirectory(long token, String path) throws FileNotFoundException, LoginDoesNotExistException, AccessDeniedException{

			try{

				Login login = getLoginbyToken(token);

				User user = login.getUser();

				return getFilesystem().changeCurrentDirectory(login , user, path);


			}
			catch (FileNotFoundException e){throw e;}
			catch (LoginDoesNotExistException e){throw e;}
			catch (AccessDeniedException e){throw e;}
		}



        public String readFile(long token, String filename)throws LoginDoesNotExistException, CantReadDirectoryException,
        PermitionException, AccessDeniedException, FileNotFoundException{

        	try{
        		Login login = getLoginbyToken(token);
        		Directory dir = login.getCurrentdirectory();

        		User user = login.getUser();

        		return getFilesystem().readFile(dir, user, filename);

        	}catch(LoginDoesNotExistException e){
        		throw e;
        	}catch (CantReadDirectoryException e){
				throw e;
			}catch (PermitionException e){
				throw e;
			}catch (AccessDeniedException e){
				throw e;
			}
        }



        public void writeToFile(long token, String filename, String content) throws LoginDoesNotExistException,
        CantWriteToDirectoryException, PermitionException, AccessDeniedException, FileNotFoundException{


        	try{

				Login login = getLoginbyToken(token);
				Directory dir = login.getCurrentdirectory();

				User user = login.getUser();
				getFilesystem().writeToFile(dir, user, filename, content);


			}catch (LoginDoesNotExistException e){
				throw e;
			}catch (FileNotFoundException e){
				throw e;
			}catch (CantWriteToDirectoryException e){
				throw e;
			}catch (PermitionException e){
				throw e;
			}catch (AccessDeniedException e){
				throw e;
			}
        }


		public void createFile(long token, String filename, String type, String content) throws InvalidPathSizeException,
		LoginDoesNotExistException, InvalidContentException,InvalidTypeException,FileAlreadyExistsException, PermitionException{

			try{

				Login login = getLoginbyToken(token);
				Directory dir = login.getCurrentdirectory();

				User user = login.getUser();

				if(type.equals("directory")){

					getFilesystem().createFileDirectory(dir,user,filename,type);}

				else{

				    getFilesystem().createFile(dir, user, filename, type, content); }


			}catch (LoginDoesNotExistException e){
				throw e;
			}catch (PermitionException e){
				throw e;
			}catch (InvalidPathSizeException e){
				throw e;
			}catch (InvalidContentException e){
				throw e;
			}catch (InvalidTypeException e){
				throw e;
			}catch (FileAlreadyExistsException e){
				throw e;
			}
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




		public long loginUser(String username, String password)throws UserDoesNotExistException {

			try{

				User user = getFilesystem().getUserbyUsername(username);

				Login login;

				login = new Login(user, password);

				CheckToken(login);

				UpdateLoginList();

				getLoginsSet().add(login);  //override do add!!!!!!! /*TODO*/

				return login.get_token();

			}

			catch( UserDoesNotExistException e ){
				throw e; //System.out.println( e.getMessage() );
			}

			catch( TokenAlreadyExistsException e ){
				throw e; //System.out.println( e.getMessage() );
			}

			//return 0;
			}

		public Login getLoginbyToken(long token) throws LoginDoesNotExistException, LoginIsInvalidException {

			for( Login login: getLoginsSet()){

				if( login.get_token()==token ){

							DateTime now = new DateTime();

							if( now.isAfter(login.get_timeout())){

								throw new LoginIsInvalidException();

							}

							else{

						return login;

							}

				}

			}

    	throw new LoginDoesNotExistException();

	}

	public void UpdateLoginList(){

		for(Login log: getLoginsSet()){

			DateTime now = new DateTime();

			if( now.isAfter(log.get_timeout())){
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
		
		try{
				
				
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
			
			catch (LoginDoesNotExistException e){ throw e;}
			catch (PermitionException e) {throw e;}
		
	}


}
