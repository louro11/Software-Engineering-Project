package pt.tecnico.mydrive.domain;

import org.jdom2.Element;
import org.joda.time.DateTime;

public class Directory extends Directory_Base {
    
    public Directory() {
        super();
    }

    public Element xmlExport() {
        Element element = new Element("directory");
        element.setAttribute("name", getName());
        element.setAttribute("owner", getOwner().getName());
        element.setAttribute("perm", getPermission());
        ///element.setAttribute("path", get());
       // element.setAttribute("date", DateTime.toString(getTimestamp()));
        element.setAttribute("id", Integer.toString(getFileid())); 
        
        /*
        for(File file : getFilesSet()){
        	
        	if()
        	
        	element.addContent(file.xmlExport());
        }
        */
        return element;
        
    }
    
}
