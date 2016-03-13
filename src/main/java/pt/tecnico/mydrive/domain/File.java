package pt.tecnico.mydrive.domain;
import org.joda.time.DateTime;
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


    public abstract void xmlImport(Element fileElement);

    public void remove(){}

    public String readfile(){return "";}


	 public Element xmlExport();

}
