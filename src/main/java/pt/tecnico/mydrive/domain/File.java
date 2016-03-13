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
		 	Element owner = new Element(getOwner().get_name());
	        Element perm = new Element(get_permission());
	        Element path = new Element(get_name()); //tenho que calcular o path, fuck that
	        
	        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	        String date = fmt.print(get_timestamp());
	        Element time = new Element(date);
	        
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
