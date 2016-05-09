package pt.tecnico.mydrive.domain;

import org.jdom2.Element;
import org.joda.time.DateTime;
import java.util.Date;

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



    @Override
    public boolean timeout(DateTime datetime){

    //retorna false (timeout) se a diferença for superior a 2 (2horas)

    Date date = new Date();
    int currentminutes = date.getMinutes();
    int relativeminutes = datetime.toDate().getMinutes();


    return ((currentminutes - relativeminutes) < 10 );
    
  }

}
