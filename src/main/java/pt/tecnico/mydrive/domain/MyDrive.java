package pt.tecnico.mydrive.domain;

import pt.ist.fenixframework.FenixFramework;
import java.io.File;

import org.jdom2.Element;
import org.jdom2.Document;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pt.tecnico.mydrive.domain.FileSystem;

import pt.tecnico.mydrive.exceptions.UserNameAlreadyExistsException; //criacao do user lanca esta excepcao

public class MyDrive extends MyDrive_Base {
    
	// Set de filesystems
	
    public MyDrive() {
        super();
    }
    
    public static MyDrive getInstance() {
        MyDrive mydrive = FenixFramework.getDomainRoot().getMydrive();
        //if (mydrive != null)
        	return mydrive;
    }
    
    public Document xmlExport() {
        Element element = new Element("mydrive");
        Document doc = new Document(element);

        for (FileSystem fs: getFileSystemSet()) //filesystem tem de ter um set de user
            element.addContent(fs.xmlExport());

        return doc;
    }
    
}
