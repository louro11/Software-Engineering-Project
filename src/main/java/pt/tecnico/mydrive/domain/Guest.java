package pt.tecnico.mydrive.domain;

import org.jdom2.Element;
import org.joda.time.DateTime;

import pt.tecnico.mydrive.exceptions.GuestDoesntHasPasswordException;

public class Guest extends Guest_Base {

    public Guest(){
     
	    set_username("nobody");
      set_name("Guest");
      set_mask("rxwdr-x-");
    }

    public Element xmlExport(){
    	
    	return super.xmlExport();

    }


   @Override
   public String get_password()throws GuestDoesntHasPasswordException{
      throw new GuestDoesntHasPasswordException();
   }

   @Override
   public void set_password(String password)throws GuestDoesntHasPasswordException{
      throw new GuestDoesntHasPasswordException();
   }

   @Override
    public boolean timeout(DateTime datetime){
      return true;
    
  }


}
