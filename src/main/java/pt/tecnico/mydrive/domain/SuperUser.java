package pt.tecnico.mydrive.domain;
import org.jdom2.Element;

public class SuperUser extends SuperUser_Base {

    public SuperUser() {
       
        super();
   
    }

    public SuperUser(String username, String password, String name, String mask){
     
	  set_username(username);
      set_password(password);
      set_name(name);
      set_mask(mask);
    }

    public Element xmlExport(){
    	
    	return super.xmlExport();

    }
    public boolean isRoot(){
		
		return true;
	}

}
