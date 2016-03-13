package pt.tecnico.mydrive.domain;

import pt.tecnico.mydrive.exceptions.InvalidUserNameException;

import org.jdom2.Element;

public class User extends User_Base {

    public User(){
      super();
    }

    public User(String username, String password, String name, String mask) throws InvalidUserNameException {
        set_password(password);
        set_name(name);
        set_mask(mask);
        if( username.isEmpty() || username == null){
          throw new InvalidUserNameException("username is empty");
        }
        else{
             char c;
             for (int i = 0; i < username.length(); i++) {
                    c = username.charAt(i);
                    if(Character.isLetterOrDigit(c)){
                      set_username(username);
                    }
                    else{
                      throw new InvalidUserNameException("username contains wrong Character");
                    }

              }
        }
      }

	public Element xmlExport() {
		Element element = new Element("user");
		element.setAttribute("username", get_username());
		element.setAttribute("password", get_password());
		element.setAttribute("name", get_name());
		//element.setAttribute("phoneNumber", Integer.toString(getPhoneNumber()));
		Directory dir = getHomedirectory();
		//element.setAttribute("homedir", dir.getName()); //path
		element.setAttribute("mask", get_mask());
		
		element.addContent(dir.xmlExport());
		
		return element;
	    }

}
