package pt.tecnico.mydrive.domain;

import org.jdom2.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jdom2.Element;

public class File extends File_Base {

    public File() {
    	super();
    }
    public File(String name, String permission, Integer fileid, DateTime timestamp, User owner) {
    	set_name(name);
    	set_permission (permission);
    	set_fileid(fileid);
    	set_timestamp(timestamp);
    	setOwner(owner);

    }

    //como herda de File_base ja tem o getName

    /*
    public boolean isCDiable(){};

    public boolean isAppendable(){};
    */

	
	 public Element xmlExport(){
		 
		 	Element file = new Element("file");
		 	
		 	Element name = new Element("name");
		 	name.addContent(get_name());
		 	Element owner = new Element("owner");
		 	owner.addContent(getOwner().get_name());
		 	
	        Element perm = new Element("perm");
	        perm.addContent(get_permission());
	        
	        //TODO
	        Element path = new Element("path"); //tenho que calcular o path
	        path.addContent(getOwner().getHomedirectory().get_name());
	        
	        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	        String date = fmt.print(get_timestamp());
	        Element time = new Element(date);
	        
	        file.addContent(name);
	        file.addContent(owner);
	        file.addContent(perm);
	        file.addContent(path);
	        file.addContent(time);    
	        
		 return file; 
	 }


    //public abstract void xmlImport(Element fileElement);

    public void remove(){}

    public String readfile(){return "";}
}
