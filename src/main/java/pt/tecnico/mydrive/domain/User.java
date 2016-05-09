package pt.tecnico.mydrive.domain;

import pt.tecnico.mydrive.exceptions.ImportDocumentException;
import pt.tecnico.mydrive.exceptions.InvalidUserNameException;
import pt.tecnico.mydrive.exceptions.GuestDoesntHasDeletePermitionException;
import pt.tecnico.mydrive.exceptions.GuestDoesntHasWritePermitionException;
import pt.tecnico.mydrive.exceptions.InvalidPasswordLengthException;



import java.io.UnsupportedEncodingException;

import org.jdom2.Element;
import org.joda.time.DateTime;
import java.util.Date;

public class User extends User_Base {

    public User(){


      super();


    }

    public User(String username, String password, String name, String mask, Directory dir) throws InvalidPasswordLengthException, InvalidUserNameException {

      //Done close issue #1
      if(password.length() >= 8){
        set_password(password);
      }
      else{
        throw new InvalidPasswordLengthException();
      }
        set_name(name);
        set_mask(mask);
        setHomedirectory(dir);
        if( username.isEmpty() || username == null){
            throw new InvalidUserNameException("username is empty");
          }

        else{

               char c;
               for (int i = 0; i < username.length(); i++) {
                      c = username.charAt(i);
                      if(!Character.isLetterOrDigit(c)){
                   	   throw new InvalidUserNameException("username contains wrong Character");
                      }
                }
               set_username(username);
          }
      }

    public User(String username){

    	 if( username.isEmpty() || username == null){

             throw new InvalidUserNameException("username is empty");
           }

         else{
                char c;

                for (int i = 0; i < username.length(); i++) {
                       c = username.charAt(i);
                       if(!Character.isLetterOrDigit(c)){
                    	   throw new InvalidUserNameException("username contains wrong Character");
                       }
                 }

                set_username(username);
                if(username.length() >= 8){
                  set_password(username);
                }
                else{
                  throw new InvalidPasswordLengthException();
                }
                set_name(username);
            	set_mask("rwxd----");
            	Directory dir = new Directory();
            	dir.set_name(username);
            	setHomedirectory(dir);
           }

    }


    public boolean equals(User u){

          return u.get_username().equals(this.get_username()) && u.get_name().equals(this.get_name())
                  && u.get_password().equals(this.get_password()) && u.get_mask().equals(this.get_mask())
                  && u.getHomedirectory().get_name().equals(this.getHomedirectory().get_name()); //TODO:FIXME:XXX

    }

	public Element xmlExport() {
		Element user = new Element("user");
		user.setAttribute("username", get_username());
		Element pass = new Element("password");
		pass.addContent(get_password());
		Element usrName = new Element("name");
		usrName.addContent(get_name());

		Directory dir = getHomedirectory();
		Element homedir = new Element("home");
		homedir.addContent(dir.get_name());

		Element mask = new Element("mask");
		mask.addContent(get_mask());

		Element files = new Element("files");

		for(File file: dir.getFilesSet()){
			files.addContent(dir.xmlExport());
		}

		user.addContent(pass);
		user.addContent(usrName);
		user.addContent(homedir);
		user.addContent(mask);
		user.addContent(files);

		return homedir;
	}

	public File getFileByName(String filename) {
        for (File file : getHomedirectory().getFilesSet()) {
            if (file.get_name().equals(filename)) {
                return file;
            }
        }
        return null;
    }

	public void xmlImport(Element element) throws ImportDocumentException{

		try{

			set_password(new String(element.getChild("pass").getValue().getBytes("UTF-8")));
			set_name(new String(element.getChild("name").getValue().getBytes("UTF-8")));
			set_mask(new String(element.getChild("mask").getValue().getBytes("UTF-8")));
			Directory dir = new Directory();
			dir.set_name(new String(element.getChild("home").getValue().getBytes("UTF-8")));
			setHomedirectory(dir);

			Element filesElem = element.getChild("files");

			for (Element node: filesElem.getChildren("app")) {
			    if(getFileByName(node.getChild("name").getValue())==null){
			    	String filename = new String(node.getChild("name").getValue().getBytes("UTF-8"));
			    	DateTime dt = new DateTime();
			    	String owner = get_username();
			    	Application app = new Application(filename, "rwxd----", 0,dt, this, " ");
			    	getHomedirectory().addFiles(app);
			    }
			    else{
			    	getHomedirectory().addFiles(getFileByName(node.getChild("name").getValue()));
			    }
			}
			for (Element node: filesElem.getChildren("dir")) {
				if(getFileByName(node.getChild("name").getValue())==null){
			    	String filename = new String(node.getChild("name").getValue().getBytes("UTF-8"));
			    	DateTime dt = new DateTime();
			    	String owner = get_username();
			    	Directory directory = new Directory(filename,0,dt, "rwxd----", this);
			    	getHomedirectory().addFiles(directory);
			    }
			    else{
			    	getHomedirectory().addFiles(getFileByName(node.getChild("name").getValue()));
			    }
			}
			for (Element node: filesElem.getChildren("PlainFile")) {
				if(getFileByName(node.getChild("name").getValue())==null){
			    	String filename = new String(node.getChild("name").getValue().getBytes("UTF-8"));
			    	DateTime dt = new DateTime();
			    	String owner = get_username();
			    	TextFile txt = new TextFile(filename,"rwxd----",0,dt, this, " ");
			    	getHomedirectory().addFiles(txt);
			    }
			    else{
			    	getHomedirectory().addFiles(getFileByName(node.getChild("name").getValue()));
			    }
			}

			for (Element node: filesElem.getChildren("link")) {
				if(getFileByName(node.getChild("name").getValue())==null){
			    	String filename = new String(node.getChild("name").getValue().getBytes("UTF-8"));
			    	DateTime dt = new DateTime();
			    	String owner = get_username();
			    	Link link = new Link(filename,"rwxd----",0,dt, this, " ");
			    	getHomedirectory().addFiles(link);
			    }
			    else{
			    	getHomedirectory().addFiles(getFileByName(node.getChild("name").getValue()));
			    }
			}

		} catch (UnsupportedEncodingException e) {
            throw new ImportDocumentException();
		}

	}


	//as permissoes fazemos por override dos metodos? ou é melhor assim?
	
	public boolean hasPermission(File f, int position, String perm) {
		

		User owner = f.getOwner();
		String file_permissions = f.get_permission();

		if(file_permissions.length() == 8){

			if (this.equals(owner) || this.isRoot()){

				return ((file_permissions.substring(position,position+1)).equals(perm));}

			else{

				return ((file_permissions.substring(position+4,position+5)).equals(perm));}

		}
		else{

			return false;}

	}

	public void remove() {
       /* for (File f: getHomedirectory().getFilesSet())
            f.remove();
        setMyDrive(null); */
        deleteDomainObject();
    }



	public boolean hasReadPermission(File f){


		return hasPermission(f,0,"r");

	}

	public boolean hasWritePermission(File f)throws GuestDoesntHasWritePermitionException{

		if(get_name().equals("Guest")){throw new GuestDoesntHasWritePermitionException();}
		

		return hasPermission(f,1,"w");

	}

	public boolean hasExecutePermission(File f){

		return hasPermission(f,2,"x");

	}

	
	public boolean hasDeletePermission(File f)throws GuestDoesntHasDeletePermitionException{
		if(get_name().equals("Guest")){throw new GuestDoesntHasDeletePermitionException();}
		

		return hasPermission(f,3,"d");

	}

  
    public boolean timeout(DateTime datetime){

    //retorna false (timeout) se a diferença for superior a 2 (2horas)

    Date date = new Date();
    int currenthour = date.getHours();
    int relativehour = datetime.toDate().getHours();
    

    return ((currenthour - relativehour) < 2 );
    
  }

	public boolean isRoot(){

		return false;
	}

}
