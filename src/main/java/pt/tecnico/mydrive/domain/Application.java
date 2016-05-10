package pt.tecnico.mydrive.domain;
import org.jdom2.Element;
import org.joda.time.DateTime;

import pt.tecnico.mydrive.exceptions.ApplicationDoesntHasAssocException;

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


   @Override
   public void setAssociation(Association assoc) throws ApplicationDoesntHasAssocException{
    throw new ApplicationDoesntHasAssocException();
   }

   @Override
   public Association getAssociation() throws ApplicationDoesntHasAssocException{
    throw new ApplicationDoesntHasAssocException();
   }

    public Element xmlExport() {
		
        Element app = new Element("app");
        app.setAttribute("id", Integer.toString(get_fileid()));
       
        app.addContent(super.xmlExport());
        
        return app;
    }

}
