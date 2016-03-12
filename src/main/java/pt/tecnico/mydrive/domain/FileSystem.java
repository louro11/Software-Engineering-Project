package pt.tecnico.mydrive.domain;

import org.jdom2.Element;


public class FileSystem extends FileSystem_Base {
    
    public FileSystem() {
        super();
    }
    
    public Element xmlExport() {
        Element element = new Element("filesytem");

        Element UsersElement = new Element("users");
        element.addContent(UsersElement);
        
        for (User user: getUsersSet())
            UsersElement.addContent(user.xmlExport());
		
        return element;
    }
    
}
