package pt.tecnico.mydrive.domain;

import org.joda.time.DateTime;

import java.util.Scanner;
import java.io.IOException;
import org.jdom2.Element;



public class EnvironmentVar extends EnvironmentVar_Base {

    public EnvironmentVar() {
        
        super();
    }
    
    
     public EnvironmentVar(String name, String value) {
        
        set_name(name);
    	set_value(value);
    
    }

    @Override
    public void setLogin(Login login) {
        if (login == null) {
            super.setLogin(null);
            return;
        }
		super.setLogin(login);
    
    }


    public void remove(){


      setLogin(null);
 
  	  deleteDomainObject();

    }
    
    
    public String getDescription(){


      return this.get_name() + "=" + this.get_value();
 
  	}
    
    

}


