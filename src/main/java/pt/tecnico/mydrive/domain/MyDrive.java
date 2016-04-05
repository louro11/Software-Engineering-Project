package pt.tecnico.mydrive.domain;


import org.joda.time.DateTime;

import pt.ist.fenixframework.FenixFramework;
import java.io.File;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;
import pt.ist.fenixframework.FenixFramework;

import org.jdom2.Element;
import org.jdom2.Document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.tecnico.mydrive.domain.FileSystem;


	public class MyDrive extends MyDrive_Base {

		public MyDrive(){
			setRoot(FenixFramework.getDomainRoot());
			if(getFilesystem() == NULL) setFilesystem(new FileSystem());

			setCurrentuser(getFilesystem().getRoot());
			setCurrentdirectory(getFilesystem().getMaindir());
			
		}
    


	public String printFiles(String path){

		return getFilesystem().printFiles(path);

	}

	public void changeCurrentDirectory(String path){
		try{ 
			setCurrentdirectory(getFilesystem().changeCurrentDirectory(path));
			
		}
		catch (FileNotFoundException e){System.out.println(e.getMessage());}
		
	}
	
	/**CHANGES***/
	
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

	public String readfile(String path){
		return getFilesystem().readfile(path);
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

}
