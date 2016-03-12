package pt.tecnico.mydrive.domain;

import org.jdom2.Element;

public class File extends File_Base {
    
    public File() {
        super();
    }
    
    
    public Element xmlExport() {
        Element element = new Element("files");
        
        for(File file:getFilesSet()){
        	element.addContent(file.xmlExport());
        }
        
        return element;
    }
    
    
}
