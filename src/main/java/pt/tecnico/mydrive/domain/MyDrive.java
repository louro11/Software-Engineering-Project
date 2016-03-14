package pt.tecnico.mydrive.domain;


import org.joda.time.DateTime;

import pt.ist.fenixframework.FenixFramework;
import java.io.File;
import pt.tecnico.mydrive.exceptions.FileNotFoundException;

import org.jdom2.Element;
import org.jdom2.Document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.tecnico.mydrive.domain.FileSystem;


	public class MyDrive extends MyDrive_Base {

		public MyDrive(){
			
			FileSystem fs = new FileSystem();

			setCurrentuser(fs.getRoot());
			setCurrentdirectory(fs.getMaindir());
			setFilesystem(fs);
		}
    


	public String PrintFiles(String path){

		return getFilesystem().PrintFiles(path);

	}

	public void changeCurrentDirectory(String path){
		setCurrentdirectory(getFilesystem().changeCurrentDirectory(path));
	}

	public void createTextFile(String name, String content ){

		getFilesystem().createTextFile(name, getCurrentuser().get_mask(), 1, new DateTime(), getCurrentuser(), content, getCurrentdirectory());
	}
	public void createDirectory(String name, String path){
		getFilesystem().createDirectory(name, getCurrentuser(), path);
	}

	public String readfile(String path){
		return getFilesystem().readfile(path);
	}

	public void removeFile(String path){
		try{ 
			
			User current = getCurrentuser();
			getFilesystem().removeFile(path,current);
		}
		catch (FileNotFoundException e){}
	}
		
	public static MyDrive getInstance() {
       
        MyDrive mydrive = FenixFramework.getDomainRoot().getMydrive();
        //if (mydrive != null)
        	return mydrive;
    }
    
    public Document xmlExport() {
        Element element = new Element("mydrive");
        Document doc = new Document(element);
        
        element.addContent(getFilesystem().xmlExport());

        return doc;
    }

}
