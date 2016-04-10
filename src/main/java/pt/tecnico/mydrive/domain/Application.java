package pt.tecnico.mydrive.domain;
import org.jdom2.Element;
import org.joda.time.DateTime;

public class Application extends Application_Base {
    
    public Application() {
        super();
    }

    public Application(String name, String permission, Integer fileid, DateTime timestamp, User owner, String content) {
    	set_name(name);
    	set_permission (permission);
    	set_fileid(fileid);
    	set_timestamp(timestamp);
    	setOwner(owner);
        set_content(content);   
   } 
  
    
public Element xmlExport() {
		
        Element app = new Element("app");
        app.setAttribute("id", Integer.toString(get_fileid()));
       
        app.addContent(super.xmlExport());
        
        return app;
    }
}
