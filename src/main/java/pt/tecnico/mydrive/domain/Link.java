package pt.tecnico.mydrive.domain;
import org.jdom2.Element;
import org.joda.time.DateTime;

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
    public boolean isApp(){
   	 return false;
    }
    @Override
    public boolean isLink(){
    	return true;
    }

}
