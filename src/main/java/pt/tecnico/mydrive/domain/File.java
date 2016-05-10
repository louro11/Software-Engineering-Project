package pt.tecnico.mydrive.domain;

import java.lang.reflect.InvocationTargetException;

import org.jdom2.Element;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import pt.tecnico.mydrive.exceptions.ApplicationDoesntHasAssocException;
import org.jdom2.Element;

public abstract class File extends File_Base {

    public File() {
    	
    	super();
   
   
    }
    public File(String name, String permission, Integer fileid, DateTime timestamp, User owner) throws ApplicationDoesntHasAssocException{
    	
    	
    	set_name(name);
    	set_permission (permission);
    	set_fileid(fileid);
    	set_timestamp(timestamp);
    	setOwner(owner);
        //cada vez que é criado um ficheiro é criada uma associação à sua extensão
        Association assoc = new Association((name.split("."))[0],this);
        setAssociation(assoc);

    }

    /******************* metodos abstratos *****************/

    public abstract String readfile();

    public abstract void writefile(String content);

   


    /*******************************************************/

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


    public void xmlImport(Element fileElement){
    	
    	
    	
    /*  set_name(new String(fileElement.getchild("name").getValue().getBytes("UTF-8")));
        String username = new String(fileElement.getchild("owner").getValue().getBytes("UTF-8"));

        Directory dir = new Directory(username, 0, new DateTime(),"rwxd----", this.getOwner(),this);
        set_owner(new User(username, username, username, "rwxd----", dir));
        set_permission(new String(fileElement.getchild("perm").getValue().getBytes("UTF-8")));
        set_path(new String(fileElement.getchild("path").getValue().getBytes("UTF-8")));
        set_timestamp(new DateTime());
*/
        //TODO

    }

    //isto e para fazer override     
    public void remove(){} 
     
    public boolean isDir(){
    	return false;
    }
    public boolean isApp(){
    	return false;
    }

    public boolean isEqual(File file){
		
		
		if(this.get_fileid().equals(file.get_fileid())){
			
			return true;
		}
		
		return false;
	}
	
    public void runApp(String[] args)throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
    IllegalArgumentException, IllegalAccessException, InvocationTargetException{}
    
    
    public File runLink(String[]args){
    	return null;
    }


    

}

