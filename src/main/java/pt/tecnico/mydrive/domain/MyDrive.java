package pt.tecnico.mydrive.domain;


import org.joda.time.DateTime;

import pt.ist.fenixframework.FenixFramework;
import java.io.File;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.tecnico.mydrive.exceptions.UserDoesNotExistException;
import pt.tecnico.mydrive.exceptions.LoginDoesNotExistException;
import pt.tecnico.mydrive.exceptions.LoginIsInvalidException;
import pt.ist.fenixframework.FenixFramework;

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
				setCurrentdirectory(getFilesystem().getMaindir());
		}

	public String printFiles(String path){

		return getFilesystem().printFiles(path);

	}

        public void changeCurrentDirectory(long token, String path){
		try{

			Login login = getLoginbyToken(token);

			User user = login.getUser();


			getFilesystem().changeCurrentDirectory(login, user, path);

		}
		catch (FileNotFoundException e){System.out.println(e.getMessage());}
		catch (LoginDoesNotExistException e){}

	}

	public void createFile(String filename, String type, String content){ //token
		Directory dir = getCurrentdirectory();
		User user = getCurrentuser();
		getFilesystem().createFile(dir, user, filename, type, content);
	}

	public void createTextFile(String name, String content ){

		getFilesystem().createTextFile(name, getCurrentuser().get_mask(), 1, new DateTime(), getCurrentuser(), content, getCurrentdirectory());
	}
	public void createDirectory(String path){
		getFilesystem().createDirectory(getCurrentuser(), path);
	}

	public String readfile(long token, String name){
	try{
		Login login = getLoginbyToken(token);
		User user = login.getUser();

		return getFilesystem().readfile(login, user, name);
	}
	catch (LoginDoesNotExistException e){return "Login does not exist";}
	}

	public void writefile(long token, String name, String content){
	try{
		Login login = getLoginbyToken(token);
		User user = login.getUser();

		 getFilesystem().writefile(login, user, name, content);
	}
	catch (LoginDoesNotExistException e){}
	}

	public void removeFile(String path){
		try{

			//User current = getCurrentuser();
			getFilesystem().removeFile(path);
		}
		catch (FileNotFoundException e){}
	}

	public static MyDrive getInstance() {

        MyDrive mydrive = FenixFramework.getDomainRoot().getMydrive();
        if (mydrive != null)
        	return mydrive;
        return new MyDrive();
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


		public long loginUser(String username, String password){
			try{

				User user = getFilesystem().getUserbyUsername(username);

				Login login;

				if( ( user.get_password() ).equals( password )){
					login = new Login (user);

					for(Login log: getLoginsSet()){

						DateTime now = new DateTime();

						if( now.isAfter(log.get_timeout())){
							getLoginsSet().remove(log);
						}

					}

					getLoginsSet().add(login);

					return login.get_token();
				}
				}

			catch( UserDoesNotExistException e ){
				System.out.println( e.getMessage() );
			}
			return 0;
			}

	public Login getLoginbyToken(long token) throws LoginDoesNotExistException LoginIsInvalidException {

    	for( Login login: getLoginsSet()){

      		if( login.get_token()==token ){

						DateTime now = new DateTime();

						if( now.isAfter(log.get_timeout())){

							throw new LoginIsInvalidException();

						}

						else{

        			return login;

						}

      		}

    	}

    	throw new LoginDoesNotExistException();

	}

	}
