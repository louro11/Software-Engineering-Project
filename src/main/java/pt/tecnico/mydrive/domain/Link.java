package pt.tecnico.mydrive.domain;
import java.lang.reflect.InvocationTargetException;

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
    
    
    int limit = 10;
    int counter = 0;
    
    @Override
    public void run(User user, String[] args)throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
    	IllegalArgumentException, IllegalAccessException, InvocationTargetException, LoopFoundException,RunException{
    	
    	String path = get_content();
    	File file = user.getFilesystem().getFile(path);
    	//loop between links not specified in the rules
		 //I assumed that the maximum amount of times that a link can be executed is 10d
		if(counter > limit){    
			 throw new LoopFoundException();
		}
		counter++;
    	file.run(user, args);
    	
    }

}
