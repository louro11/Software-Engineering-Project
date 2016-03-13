package pt.tecnico.mydrive.domain;

import org.jdom2.Element;

public class User extends User_Base {
    
    public User() {
        super();
    }
    
    
    public Element xmlExport() {
        Element element = new Element("user");
        element.setAttribute("username", getUsername());
        element.setAttribute("password", getPassword());
        element.setAttribute("name", getName());
        //element.setAttribute("phoneNumber", Integer.toString(getPhoneNumber()));
        //element.setAttribute("homedir", getHomedirectory());
        element.setAttribute("mask", getMask());

        Directory dir = null;
        
        element.addContent(dir.xmlExport());
        
        return element;
    }
    
}
