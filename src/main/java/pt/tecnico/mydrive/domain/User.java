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
		Element user = new Element("user");
		user.setAttribute("username", get_username());
		Element pass = new Element("password");
		pass.setAttribute("password", get_password());
		Element usrName = new Element(get_name());
		Element mask = new Element(get_mask());
		
		Directory dir = getHomedirectory();
		Element homedir = new Element(dir.get_name());
		
		for(File file: dir.getFilesSet()){
			homedir.addContent(dir.xmlExport());
		}
		return homedir;
	}

}
