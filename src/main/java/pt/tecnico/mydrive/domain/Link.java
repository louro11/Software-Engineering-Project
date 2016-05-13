package pt.tecnico.mydrive.domain;
import java.awt.List;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.jdom2.Element;
import org.joda.time.DateTime;

import pt.tecnico.mydrive.exceptions.LoopFoundException;
import pt.tecnico.mydrive.exceptions.RunException;

public class Link extends Link_Base {
    
	
    public Link() {
        super();
    }

    public Link(String name, String permission, Integer fileid, DateTime timestamp, User owner, String content) {
    	set_name(name);
    	set_permission (permission);
    	set_fileid(fileid);
    	set_timestamp(timestamp);
    	setOwner(owner);
        set_content(content);
    }
    
    
    public Element xmlExport() {
		
        Element link = new Element("link");
        link.setAttribute("id", Integer.toString(get_fileid()));
       
        link.addContent(super.xmlExport());
        
        return link;
    }
    
    @Override
    public boolean isDir(){
    	return false;
    }
    

    
    @Override
    public void run(User user, String[] args)throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
    	IllegalArgumentException, IllegalAccessException, InvocationTargetException, LoopFoundException,RunException{
    	
    	String path = get_content();
    	File file = user.getFilesystem().getFile(path);
    	//loop between links not specified in the rules
    	//filesystem stores the links that are being visited
    	if(user.getFilesystem().getVisitedLinks().contains(this))
			 throw new LoopFoundException();
    	else{
    		user.getFilesystem().getVisitedLinks().add(this); //isto e capaz de nao funcionar :c
    		file.run(user, args);
    	}
    	
    }

}
